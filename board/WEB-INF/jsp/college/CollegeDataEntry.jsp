<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>

<script>
	function changeMerit(tmp)
	{
		$("#showapplicant").html('');
		$("#sscroll").val('');
		if(tmp.value==='4')
		{
			document.getElementById('thsscroll').style.display = "block";
			document.getElementById('tdsscroll').style.display = "block";
		}
		else
		{
			document.getElementById('thsscroll').style.display = "none";
			document.getElementById('tdsscroll').style.display = "none";
		}
	}
</script>


<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2> Already Added Student</h2>
			<div class="sidebar_section_text">
			<div class="box_row" style="background-color: #1874cd;color: white;text-align: left;font-weight: bold;padding-top: 0px;font-size: 16px;height: 22px;">
						</div>

<br/>
<!---- body ----->		
 <center>

<table style="width:400px;font-size:14px"  class="table table-bordered table-striped cf">
<tr>
<td style="color:black;"><strong>Shift </strong></td>
<td style="color:black;"><strong>Version</strong></td>
<td style="color:black;"><strong>Group</strong></td>
<td style="color:black;"><strong>Action</strong></td>
</tr>

<tr>
<td>
<select name="shiftId" id="shiftId">
<option value="none" selected>Select Shift</option>
<c:forEach items="${shiftList}" var="shift">
<option value="${shift.shiftId}">${shift.shiftName}
</option>
</c:forEach>

</select>
</td>

<td>
<select name="versionId" id="versionId">
<option value="none" selected>Select Version</option>
<c:forEach items="${versionList}" var="version">
<option value="${version.versionId}">${version.versionName}
</option>
</c:forEach>
</select>
</td>

<td>
<select name="groupId" id="groupId" onchange="changeGroup()">
<option value="none" selected>Select Group</option>
<c:forEach items="${groupList}" var="group">
<option value="${group.groupId}">${group.groupName}
</option>
</c:forEach>

</select>
</td>

<td style="display: none" id="tdsscroll">
	<input type="text" id="sscroll" name="sscroll" maxlength="20">
</td>
<td>
<!--<button class="btn bg-green"  type="button" onclick="downloadResult()">Download Result</button>-->
<input type="submit" style="width:140px;padding: 1px 12px;" class="btn btn-info" onclick="return searchStudent()" value="Search" >
</td>
 
 </tr>
</table>
<br/>


<div id="loader">

</div>
</center>                          
	
<!----body ------>	


<div id="showapplicant">

</div>

<br>
</div>
		</div>
		

	</div>


	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp" %>
</div>
  	<!-- Popup modal div start -->
    	<div id="myModal" class="modal fade" data-backdrop="static" data-keyboard="false" role="dialog">
		  <div class="modal-dialog">
		
		    <!-- Modal content-->
		    <div class="modal-content" style="color: black;">
		      <div class="modal-header">
		        <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
		        <button type="button" id="signOut" class="btn btn-info" style="float: right;" onclick="Signout()" >Sign Out</button>
		        <h4 class="modal-title" >College/Madrasah Mobile Number Entry/Update</h4>
		      </div>
		      <div class="modal-body">
		      	<div>Existing Mobile No.  <input type="text" name="eMobile" id="eMobile" style="width: 120px;" value="${sessionScope.user.collegeMobile}" readonly="readonly" disabled="disabled" /> </div>
		        <br/>
		        <div>Please provide/ verify the mobile no. of the Principal or a member of the Admission Committee.</div>
		        <div style="margin-top: 10px;">
			        Mobile No. &nbsp;&nbsp;<input type="password" name="nMobile" id="nMobile" style="width: 120px;" maxlength="11" oncopy="return false" onpaste="return false" />
			        &nbsp;&nbsp; 
			        Re-type  Mobile No. &nbsp;&nbsp;<input type="text" name="retypeMobile" id="retypeMobile" style="width: 120px;" maxlength="11" oncopy="return false" onpaste="return false" />
		        </div>
		        <div style="margin-top: 10px;">
		        	<div style="color: #FE2E64; margin-bottom: 5px;">Comments: You will get an One Time Password (OTP) in your given mobile number.</div>
		        	<input type="button" class="btn btn-primary" name="saveMobile" id="saveMobile" onclick="newOtpRequest()" value="Request for an OTP" />
		        </div>
		        <div>
		        	
		        </div>
		      </div>
		      <div class="modal-footer">
		      OTP: &nbsp;&nbsp;
		        	<input type="text" name="otp" id="otp" /> &nbsp;&nbsp;
		        	<input type="button" class="btn btn-danger" name="validateMobile" id="validateMobile" onclick="validateOtp()" value="Validate & Save" />
		        <!--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>-->
		      </div>
		    </div>
		
		  </div>
		</div>
    	<!-- Popup modal div end -->

<c:if test="${sessionScope.user.mobileVerifiedYN=='N'}">
<script type="text/javascript">
    $(window).load(function(){
        $('#myModal').modal('show');
    });
</script>
</c:if>
<script type="text/javascript">
function Signout(){
	window.location = "logout";
}

function newOtpRequest(){
	if(!validateMobileNumber($("#nMobile").val())){
		alert("Invalid mobile number");
		$("#nMobile").val()== '';
		return;
	}
	if(!validateMobileNumber($("#retypeMobile").val())){
		alert("Invalid re-typed mobile number");
		$("#retypeMobile").val()== '';
		return;
	}
	if($("#nMobile").val() == $("#retypeMobile").val()){
		$.ajax({
		 	url: 'saveMobileSendOtp',	
		 	data:{mobile:$("#retypeMobile").val()},	
		 	type: 'POST',
		 	success: function(data) {
		 		alert(data);
		 	},
		 	error: function(e) {
		 	 
		 	}
		});
	} else 
		alert("Mobile numbers do not match");		 		
}
function validateOtp(){
	if($("#otp").val()=="")
		return;
	$.ajax({
		 	url: 'validateCollMobileOtp',
		 	data:{
		 		otp:	$("#otp").val(),
		 		mobile:	$("#retypeMobile").val()
		 	},		
		 	type: 'POST',
		 	success: function(data) {
		 		if(data=="valid"){
		 		    $('#myModal').modal('hide');
		 		    window.location='collegeDashBoard';	
		 		}
		 		else
		 			alert(data);
		 	},
		 	error: function(e) {
		 	 
		 	}
	 });
}

function validateMobileNumber(mobileNumber) {
    var mob = /^(013|014|015|016|017|018|019)[0-9]{8}$/;

    if (mob.test(mobileNumber) == false) {
      return false;
    }
    return true;
  }
</script>
          
             <script type="text/javascript">

    function searchStudent() {

       if($("#shiftId").val()=="none"){
            alert("Select a Shift.");return false;
        }
        if($("#versionId").val()=="none"){
            alert("Select a version.");return false;
        }
		if($("#groupId").val()=="none"){
            alert("Select a group.");return false;
        }
        $("#showapplicant").html('');
         $('#loader').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "alreadyAdded",
            dataType: 'text',
            async   : false,
            data    : {

                eiinCode:"Hossain" ,pShift_id: $("#shiftId").val(),pVersion_id: $("#versionId").val(),
                pGroup_id: $("#groupId").val(),
            }
        }).done(function (msg) {
                    $("#loader").html('');
                    $("#showapplicant").html('');
                    $("#showapplicant").html(msg);
                })
                .always(function () {
                    //$('#sw-val-step-3').unmask();
                })
                .fail(function (data) {
                    if (data.responseCode)
                        alert(data.responseCode);
                });
                
      }
      
      function changeGroup(){
      	$("#groupName").val($("#groupId option:selected").text());
      	//alert($("#groupName").val());
      }

</script>	
<%@ include file="/WEB-INF/jsp/template/footerLite.jsp" %>

</div>
</body>
</html>