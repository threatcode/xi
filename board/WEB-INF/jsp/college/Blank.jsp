<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp"%>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2>User Home</h2>
			<div class="sidebar_section_text">
				<center>
					<div class="box_row"
						style="background-color: #00868b;color: white;text-align: left;font-weight: bold;padding-top: 0px;font-size: 16px;height: 22px;">
						<center>:: User Home ::</center>
					</div>
					<br />
					<br />
<!-- 
						<form action="" name="resultDownloadForm"
							id="resultDownloadForm" method="post"
							enctype="multipart/form-data">
							<table style="width:400px;font-size:14px"
								class="table table-bordered table-striped cf">
								<tr style="background-color: #f5fffa;">
									<td style="color:black;"><strong>Shift </strong></td>
									<td style="color:black;"><strong>Version</strong></td>
									<td style="color:black;"><strong>Group</strong></td>
									<td style="color:black;"><strong>Merit Type</strong></td>
									<td style="color:black;"><strong>Download</strong></td>

								</tr>

								<tr>
									<td>
									
									<select name="shiftIdCSV" id="shiftIdCSV">
											<option value="none" selected>Select Shift</option>
											<c:forEach items="${shiftList}" var="shift">
												<option value="${shift.shiftId}">${shift.shiftName}
												</option>
											</c:forEach>

									</select>
									 
									 </td>

									<td>
									
									<select name="versionIdCSV" id="versionIdCSV">
											<option value="none" selected>Select Version</option>
											<c:forEach items="${versionList}" var="version">
												<option value="${version.versionId}">${version.versionName}
												</option>
											</c:forEach>
									</select>
									
									</td>

									<td>
									
									<select name="groupIdCSV" id="groupIdCSV"
										onchange="changeGroupCSV()">
											<option value="none" selected>Select Group</option>
											<c:forEach items="${groupList}" var="group">
												<option value="${group.groupId}">${group.groupName}
												</option>
											</c:forEach>

									</select>
									 
									</td>
									 <td> <select name="meritTypeCSV" id="meritTypeCSV">
											<option value="none" selected>Select Merit Type</option>
											<option value="1">Merit List</option>

									</select> <input type="hidden" name="groupNameCSV" id="groupNameCSV" />
									</td>
									
									<td>
									 
										  <input type="button" style="width:140px;padding: 1px 12px;"
										class="btn btn-info buttonCSV" onclick="return downloadResultPDF()"
										value="PDF Download">
										  
									</td>

								</tr>
							</table>

						</form>
 -->

					</center>

					<!----body ------>

				</center>

			</div>
		</div>
		<br>

	</div>


	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp"%>
</div>

<c:if test="${sessionScope.user.mobileVerifiedYN=='N'}">
<script type="text/javascript">

    $(window).load(function(){
        $('#myModal').modal('show');
    });
    
	function newOtpRequest(){
		if(!validateMobileNumber($("#eMobile").val())){
			alert("Invalid mobile number");
			$("#nMobile").val()== '';
			return;
		}
		$.ajax({
		 	url: 'saveMobileSendOtp',	
		 	data:{mobile:$("#eMobile").val()},	
		 	type: 'POST',
		 	success: function(data) {
		 		alert(data);
		 	},
		 	error: function(e) {
		 	 
		 	}
		});
	}
	function validateOtp(){
		if($("#otp").val().trim()==""){alert('Please type otp!!!');$("#otp").val('');return;}
		if($("#password").val().trim()==""){alert('Please type password!!!');$("#password").val('');return;}
		if($("#cpassword").val().trim()==""){alert('Please type confirm password!!!');$("#cpassword").val('');return;}
		if($("#password").val().trim()!=$("#cpassword").val().trim()){alert('Password and re-type password donot match!!!');return;}
		$.ajax({
			 	url: 'validateCollMobileOtp',
			 	data:{
			 		otp:	$("#otp").val(),
			 		password:	$("#password").val(),
			 		cpassword:	$("#cpassword").val()
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
	function Signout(){
		window.location = "logout";
	}
	function validateMobileNumber(mobileNumber) {
	    var mob = /^(013|014|015|016|017|018|019)[0-9]{8}$/;
	
	    if (mob.test(mobileNumber) == false) {
	      return false;
	    }
	    return true;
	  }    
</script>
</c:if>



<script type="text/javascript">
    var IPaddress="${IP}";
    var eiin="${sessionScope.user.eiin}";
	function downloadResult() {

		if ($("#shiftId").val() == "none") {
			alert("Select a Shift.");
			return false;
		}
		if ($("#versionId").val() == "none") {
			alert("Select a version.");
			return false;
		}
		if ($("#groupId").val() == "none") {
			alert("Select a group.");
			return false;
		}
		if ($("#meritType").val() == "none") {
			alert("Select a merit.");
			return false;
		}

		/*         
		 var formData = new FormData($('form')[0]);
		 $.ajax({
		 url: 'admissionResultReportAction',		
		 type: 'POST',
		 data: formData,
		 async: false,
		 cache: false,
		 contentType: false,
		 processData: false,
		 success: function(data) {



		 },
		 error: function(e) {

		 alert(e);
		 }
		 }); */

	}

	function downloadResultCSV() {

		if ($("#shiftIdCSV").val() == "none") {
			alert("Select a Shift.");
			return false;
		}
		if ($("#versionIdCSV").val() == "none") {
			alert("Select a version.");
			return false;
		}
		if ($("#groupIdCSV").val() == "none") {
			alert("Select a group.");
			return false;
		}
		if ($("#meritTypeCSV").val() == "none") {
			alert("Select a merit.");
			return false;
		}

	}

	function downloadResultPDF() {

		if ($("#shiftIdCSV").val() == "none") {
			alert("Select a Shift.");
			return false;
		}
		if ($("#versionIdCSV").val() == "none") {
			alert("Select a version.");
			return false;
		}
		if ($("#groupIdCSV").val() == "none") {
			alert("Select a group.");
			return false;
		}
		
		
		var rIP="192.144.80.104";
		var num=Math.random();
		if(num<0.5)rIP="192.144.80.104";
		else if(num>0.3 && num< 0.8)rIP="192.144.80.105";
		else rIP="118.67.214.236";
		
		var url="http://"+rIP+":80/meritpdf/m"+eiin+$("#shiftIdCSV").val()+$("#versionIdCSV").val()+$("#groupIdCSV").val()+".pdf";
		//alert(url);
		window.open(url);

	}


	function changeGroup() {
		$("#groupName").val($("#groupId option:selected").text());
		//alert($("#groupName").val());
	}

	function changeGroupCSV() {
		$("#groupNameCSV").val($("#groupIdCSV option:selected").text());
		//alert($("#groupName").val());
	}
</script>
<%@ include file="/WEB-INF/jsp/template/footerLite.jsp"%>

</div>
</body>
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
		        <div style="margin-top: 10px;">
		        	<div style="color: #FE2E64; margin-bottom: 5px;">Comments: You will get an One Time Password (OTP) in your given mobile number.</div>
		        	<input type="button" class="btn btn-primary" name="saveMobile" id="saveMobile" onclick="newOtpRequest()" value="Request for an OTP" />
		        </div>
		        <div>
		        	
		        </div>
		      </div>
		      <div class="modal-footer">
		      <table>
		      		<tr>
		      			<td style="text-align: right;">OTP:</td>
		      			<td style="text-align: left;"><input type="text" name="otp" id="otp" /></td>
		      		</tr>
		      		<tr>
		      			<td style="text-align: right;">Password:</td>
		      			<td style="text-align: left;"><input type="text" name="password" id="password" /></td>
		      		</tr>
		      		<tr>
		      			<td style="text-align: right;">Confirm Password:</td>
		      			<td style="text-align: left;"><input type="text" name="cpassword" id="cpassword" /></td>
		      		</tr>
		      		<tr>
		      			<td style="text-align: center;" colspan="2">
		      				<input type="button" class="btn btn-danger" name="validateMobile" id="validateMobile" onclick="validateOtp()" value="Validate & Save" />
		      			</td>
		      		</tr>
		      	</table>
		      </div>
		    </div>
		
		  </div>
		</div>
    	<!-- Popup modal div end -->


<style>
.buttonCSV:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,255,0,0.2);
}
.buttonPDF:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,255,0,0.2);
}
</style>
</html>