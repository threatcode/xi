<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>

<%-- <script type="text/javascript" src="http://jsearchdropdown.sourceforge.net/jquery-1.7.1.min.js"></script>  
<script type="text/javascript" src="http://jsearchdropdown.sourceforge.net//jquery.searchabledropdown.js"></script>
<script type="text/javascript">
        $(document).ready(function() {
            $("select").searchable();
        });
  </script> 
 --%>


<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2>College Scenario</h2>
			<div class="sidebar_section_text">
<center>
<br/>


<div style="padding-left:80px">

<div style="float: left;">
        <label>Board:</label>
       <select style="width:150px;" id="boardId" >
	     			     	<option value="none">Select Board</option>
				    <option value="15">Barishal</option>
					<option value="20">BOU</option>
					<option value="14">Chattogram</option>
					<option value="11">Cumilla</option>
					<option value="10" selected="selected">Dhaka</option>
					<option value="17">Dinajpur</option>
					<option value="13">Jashore</option>
					<option value="18">Madrasah</option>
					<option value="21">Mymensingh</option>
					<option value="12">Rajshahi</option>
					<option value="16">Sylhet</option>
					<option value="19">TEC</option>
 
	    </select>
</div>



<div style="float: left;padding-left: 10px;">
<label for="distID">Districts:</label>

 <select name="districtID" id="districtID">
<option value="none" selected>Select District</option>

<c:forEach items="${districtinfoList}" var="districts">
<option value="${districts.districtID}">${districts.districtName}
</option>
</c:forEach>

</select> 

<%-- <input list="browsers">
<datalist id="browsers">
<c:forEach items="${districtinfoList}" var="districts">
<option value="${districts.districtID}">${districts.districtName}
</option>
</c:forEach>
</datalist> --%>
</div>

<div style="float: left;padding-left:10px;">
<label for="eiinCode">Colleges:</label>
<select name="eiinCode" id="eiinCode">
<option value="none" selected>Select College</option>

<%-- <c:forEach items="${collegeinfoList}" var="colleges">
<option value="${colleges.eiinCode}">${colleges.collegeName}(${colleges.eiinCode})
</option>
</c:forEach> --%>

</select>
</div>
</div>

<br/>
<hr>
<div style="padding-left:230px">
<div style="float: left;">
<button style="width:200px;padding: 1px 12px;" class="btn btn-info"  type="button"
onclick="fetchCollegeDashBoard()">Fetch College Dash-Board
</button>
</div>

<div style="float: left; padding-left:30px;">
<button style="width:180px;padding: 1px 12px;" class="btn btn-info"  type="button"
onclick="fetchCollegeSVG()">Fetch Result Of College
</button>
</div>

<!-- <div style="float: left; padding-left:30px;">
<button style="width:180px;padding: 1px 12px;" class="btn btn-info"  type="button"
onclick="fetchCollegeapplicantInfo()">Fetch Applicant Info
</button>
</div> -->

</div>
<br/>
<hr>
<!-- 
<div >
<button class="btn bg-green"  type="button"
onclick="fetchCollegeDashBoard()">Fetch College Dash-Board
</button>
 </div>
 <hr>
<div >
<button class="btn bg-green"  type="button"
onclick="fetchCollegeSVG()">Fetch S-V-G Of College
</button>
 </div>
 <hr> 
<div >
<button class="btn bg-green"  type="button"
onclick="fetchCollegeapplicantInfo()">Fetch Applicant Info
</button>
 </div> -->
 
<!--  <table border="0" style="width:50%">
 <tr>
 <td><button style="width:200px;padding: 1px 12px;" class="btn btn-info"  type="button"
onclick="fetchCollegeDashBoard()">Fetch College Dash-Board
</button></td>
 <td><button style="width:200px;padding: 1px 12px;" class="btn btn-info"  type="button"
onclick="fetchCollegeSVG()">Fetch S-V-G Of College
</button></td>
 <td><button class="btn bg-green"  type="button"
onclick="fetchCollegeapplicantInfo()">Fetch Applicant Info
</button>
 </div></td>
 </tr>
 </table>
 <hr> -->
 
 <div id="showCollegeDashBoard">

</div>

 <div id="showSVG">

</div>

<div id="showapplicantInfo">

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

$( "#boardId" ).change(function() {
 
  $('#showSVG').html('');
  $('#showCollegeDashBoard').html('');
  $('#showapplicantInfo').html('');
  $('#eiinCode').html('');
  $('#districtID').html('');
  
  if($("#districtID").val()=="none"){
            //alert("Select a District.");
            return;
        }
                     
    $.ajax({
                    
            url: "searchDistInterBoard",
            data: {boardId: $("#boardId").val()                      
            },
            type: 'POST',
            async: true,
            success: function (res) {
                console.log(res.distIdList.length);
                    $('#districtID').append(
                      '<option value="none" selected>Select District</option>');
                for (var i = 0; i < res.distIdList.length; i++) {
                    console.log(" " + res.distIdList[i]);
                    $('#districtID').append(
                        '<option value="'+res.distIdList[i]+'" >' + res.distNamelist[i]+'</option>'); 
                        
                }
            }
        });
  
});
}); 

</script>	

<script type="text/javascript">

$(document).ready(function(){

$( "#districtID" ).change(function() {
 
  $('#showSVG').html('');
  $('#showCollegeDashBoard').html('');
  $('#showapplicantInfo').html('');
  $('#eiinCode').html('');
  
  if($("#boardId").val()=="none"){
            alert("Select a Board.");return;
        }
                     
    $.ajax({
                    
            url: "searchCollegesactionInterBoard",
            data: {districtID: $("#districtID").val(),boardId: $("#boardId").val()                      
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
   

        if($("#boardId").val()=="none"){
            alert("Select a Board.");return;
        }
        if($("#districtID").val()=="none"){
            alert("Select a District.");return;
        }
        if($("#eiinCode").val()=="none"){
            alert("Select a College.");return;
        }
         $('#showSVG').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "S_V_G_ApplicationNumberOfCollegeSearchByInterBoard",
            dataType: 'text',
            async   : false,
            data    : {eiinCode    : $("#eiinCode").val(),boardId: $("#boardId").val()
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
        
        
        if($("#boardId").val()=="none"){
            alert("Select a Board.");return;
        }
        if($("#districtID").val()=="none"){
            alert("Select a District.");return;
        }
        if($("#eiinCode").val()=="none"){
            alert("Select a College.");return;
        }
        
        $('#showCollegeDashBoard').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "collegeDashBoardSearchByInterBoard",
            dataType: 'text',
            async   : false,
            data    : {

                eiinCode    : $("#eiinCode").val(),boardId: $("#boardId").val()
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
	
<script type="text/javascript">

    function fetchCollegeapplicantInfo() {

      $('#showSVG').html('');
      $('#showCollegeDashBoard').html('');
      

        if($("#boardId").val()=="none"){
            alert("Select a Board.");return;
        }
       if($("#districtID").val()=="none"){
            alert("Select a District.");return;
        }
        if($("#eiinCode").val()=="none"){
            alert("Select a College.");return;
        }
        
        $('#showapplicantInfo').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "listOfApplicantOfCollegeSearchByInterBoard",
            dataType: 'text',
            async   : false,
            data    : {

                eiinCode    : $("#eiinCode").val(),boardId: $("#boardId").val()
            }
        }).done(function (msg) {
                    $("#showapplicantInfo").html(msg);
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
	
<%@ include file="/WEB-INF/jsp/template/footerLite.jsp" %>

</div>
</body>
</html>