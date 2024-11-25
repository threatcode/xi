<%@ include file="/WEB-INF/jsp/template/header.jsp" %>
<%@ include file="/WEB-INF/jsp/template/sidebar.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Arrays"%>


<div>
<label for="distID">Districts:</label>
<select name="districtID" id="districtID">
<option value="none" selected>Select District</option>

<c:forEach items="${districtinfoList}" var="districts">
<option value="${districts.districtID}">${districts.districtName}
</option>
</c:forEach>

</select>
</div>
<hr> 
<br/>
<div>
<label for="eiinCode">Colleges:</label>
<select name="eiinCode" id="eiinCode">
<option value="none" selected>Select College</option>

<%-- <c:forEach items="${collegeinfoList}" var="colleges">
<option value="${colleges.eiinCode}">${colleges.collegeName}(${colleges.eiinCode})
</option>
</c:forEach> --%>

</select>
</div>
<hr> 
<br/>
<div >
<button class="btn bg-green"  type="button"
onclick="fetchCollegeDashBoard()">Fetch College Dash-Board
</button>
 </div>
 <hr>
<div id="showCollegeDashBoard">

</div>

<script type='text/javascript' src="/board/resources/js/jquery.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){

$( "#districtID" ).change(function() {
 
  $('#showCollegeDashBoard').html('');
  $('#eiinCode').html('');
  var searchcollege= {
                        "districtID": $("#districtID").val()
                        
                    };
                    
                                                     
    $.ajax({
                    
                        url: "searchCollegesaction",
                        data: JSON.stringify(searchcollege),
                        dataType: 'json',
                        contentType: 'application/json',
                        type: 'POST',
                        async: true,
                        success: function (res) {
                            console.log(res.eiinCodeList.length);
                            for (var i = 0; i < res.eiinCodeList.length; i++) {
                                console.log(" " + res.eiinCodeList[i]);
                                $('#eiinCode').append(
                                    '<option value="'+res.eiinCodeList[i]+'" >' + res.collegeNamelist[i]+"("+res.eiinCodeList[i]+")" +'</option>'); 
                                    
                            }
                        }
                    });                    
                    
                    
  
  
  
  
});
}); 

</script>	
	
<script type="text/javascript">

    function fetchCollegeDashBoard() {


       if($("#districtID").val()=="none"){
            alert("Select a District.");return;
        }
        if($("#eiinCode").val()=="none"){
            alert("Select a College.");return;
        }

        $.ajax({
            type    : "POST",
            url     : "collegeDashBoardUnderBoard",
            dataType: 'text',
            async   : false,
            data    : {

                eiinCode    : $("#eiinCode").val()
            }
        }).done(function (msg) {
                    $("#showCollegeDashBoard").html(msg);
                })
                .always(function () {
                    //$('#sw-val-step-3').unmask();
                })
                .fail(function (data) {
                    if (data.responseCode)
                        alert(data.responseCode);
                });


    }   //End of fetchApplicationInformation


</script>	

 
<%@ include file="/WEB-INF/jsp/template/footer.jsp" %>
