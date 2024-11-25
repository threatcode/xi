<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2> Security Verification</h2>
			<div class="sidebar_section_text">
<center>


<!---- body ----->		

<form name="optForm" id="optForm">  
					<input type="hidden" id="from_where" name="from_where" value="<s:property value='from_where'/>" />
					<div class="tabcontent" style="height: 75px;padding-top: 25px;">
				            <div class="form_row" style="width: 720px;">
				            	<label style="width: 216px;font-size: 16px;">One Time Password (OTP):</label>
				            	<input type="text" class="form_input" name="otp" id="otp" style="width: 200px; height: 30px;"/>
				            	<input type="button" name="validateOtpButton" id="validateOtpButton" class="btn btn-primary" onclick="validateOtp()" value="Verify OTP" />
				            </div>
					</div>
					</form>
					
				    <div id="otpRequestButtonDiv1" style="width: 98%;float: left;padding-top: 2px;padding-left:20px;">
					    <p style="color: green; font-size: 22px; padding-bottom: 20px;">
					    	Now you can use your last OTP which was sent from Education Board.
					    </p>
					    <p style="color: blue; font-size: 22px; padding-left:70px;">
					    	or
					    </p>
					    
				    </div>
				    <div id="otpRequestButtonDiv" style="width: 98%;float: left;padding-top: 2px;padding-left:100px;">
					    <input type="button" name="requestOtpButton" id="requestOtpButton" class="btn btn-danger" onclick="newOtpRequest()" value="Click for New OTP" />
				    </div>
				    				    
					<div style="background-color: #F5F5DC; margin-top: 45px;">
				            				
				            <div style="font-size: 14px; margin-top: 15px; margin-left: 15px; margin-right: 15px;">
							<p style="color: black; font-size: 18px; padding-bottom: 20px;margin-left:-10px;">
							<span>
							OTP is a secure way to help the user to view/approve confidential information.        		
				        	</span>	
				        		<br/><br/>
				        		<span>
				        		Using this feature, a user can ask for an OTP by pressing the 'Click for New OTP' button above. As soon as the user asks for 
				        		an OTP, the system will send a SECURE CODE to his/her mobile number verified earlier.				        		
				        		</span>
							</p>
				            </div>
				    </div>





		
<!----body ------>	

</center>

</div>
		</div>
		<br>

	</div>


	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp" %>
</div>
<script type="text/javascript">
function newOtpRequest(){
		$.ajax({
		 	url: 'sendNewCollOtp',	
		 	type: 'POST',
		 	success: function(data) {
		 		alert(data);
		 		$("#otpRequestButtonDiv").hide();
		 	},
		 	error: function(e) {
		 	 
		 	}
		});	 		
}
function validateOtp(){
	if($("#otp").val()=="")
		return;
	$.ajax({
		 	url: 'validateCollOtp',
		 	data:{
		 		otp:	$("#otp").val()
		 	},		
		 	type: 'POST',
		 	success: function(data) {
		 		if(data=="valid"){
		 		    if($("#from_where").val()=="specialQuotaList")
		 				window.location="specialQuotaStudentList";
		 		   else if($("#from_where").val()=="educationalQuotaList")
		 		   		window.location="educationQuotaStudentList";	
		 		   else if($("#from_where").val()=="nonApprovedStudentListOfMerit")
		 		   		window.location="nonApprovedStudentListOfMerit";	
		 		   else if($("#from_where").val()=="approvedStudentListOfMerit")
		 		   		window.location="approvedStudentListOfMerit";
		 		   		
		 		/*    else if($("#from_where").val()=="totalApprovedStudentListOfMerit")
		 		   		window.location="totalApprovedStudentListOfMerit";
		 		   			
		 		   else if($("#from_where").val()=="admissionCancelList")
		 		   		window.location="admissionCancelList"; */
		 		   		
		 		   		
		 		}
		 		else
		 			alert(data);
		 	},
		 	error: function(e) {
		 	 
		 	}
	 });
}
</script>
<%@ include file="/WEB-INF/jsp/template/footerLite.jsp" %>

</div>
</body>
</html>