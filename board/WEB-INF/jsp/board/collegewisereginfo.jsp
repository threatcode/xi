<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2>College Scenario</h2>
			<div class="sidebar_section_text">
<center>
<br/>


<div style="padding-left: 200px">
<div style="float: left;">
<label for="distID">Districts:</label>
<select name="districtID" id="districtID">
<option value="none" selected>Select District</option>

<c:forEach items="${districtinfoList}" var="districts">
<option value="${districts.districtID}">${districts.districtName}
</option>
</c:forEach>

</select>
</div>

<div style="float: left;">
<label for="eiinCode">Colleges:</label>
<select name="eiinCode" id="eiinCode">
<option value="none" selected>Select College</option>

<%-- <c:forEach items="${collegeinfoList}" var="colleges">
<option value="${colleges.eiinCode}">${colleges.collegeName}(${colleges.eiinCode})
</option>
</c:forEach> --%>

</select>
</div>
 
  <div style="float: left; padding-left:30px;">
<button style="width:200px;padding: 1px 12px;" class="btn btn-info"  type="button"
onclick="fetchCollegeDashBoard()">Fetch College Dash-Board
</button>
</div>
   

 
  </div>
  

 <div id="showCollegeDashBoard">

</div>

 

<br>
</center>

</div>
		</div>
		<br>
<!---- body ----->		

		
<!----body ------>	
	</div>


	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp" %>
</div>
<script type='text/javascript' src="/board/resources/js/jquery.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){

$( "#districtID" ).change(function() {
 
  $('#showSVG').html('');
  $('#showCollegeDashBoard').html('');
  $('#showapplicantInfo').html('');
  $('#eiinCode').html('');
                     
    $.ajax({
                    
            url: "searchCollegesaction",
            data: {
            	districtID: $("#districtID").val()                        
            },
            type: 'POST',
            async: true,
            success: function (res) {
                console.log(res.eiinCodeList.length);
                    $('#eiinCode').append(
                      '<option value="none" selected>Select College</option>');
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

    function fetchCollegeSVG() {
    
    $('#showapplicantInfo').html('');
    $('#showCollegeDashBoard').html('');
   

       if($("#districtID").val()=="none"){
            alert("Select a District.");return;
        }
        if($("#eiinCode").val()=="none"){
            alert("Select a College.");return;
        }
         $('#showSVG').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "S_V_G_ApplicationNumberOfCollege",
            dataType: 'text',
            async   : false,
            data    : {

                eiinCode    : $("#eiinCode").val()
            }
        }).done(function (msg) {
                    $("#showSVG").html(msg);
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

<script type="text/javascript">

    function fetchCollegeDashBoard() {

        $('#showapplicantInfo').html('');
        $('#showSVG').html('');
        
        
       if($("#districtID").val()=="none"){
            alert("Select a District.");return;
        }
        if($("#eiinCode").val()=="none"){
            alert("Select a College.");return;
        }
        
        $('#showCollegeDashBoard').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "collegeRegDashBoard",
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
	


</script>	
<%@ include file="/WEB-INF/jsp/template/footerLite.jsp" %>

</div>
</body>
</html>