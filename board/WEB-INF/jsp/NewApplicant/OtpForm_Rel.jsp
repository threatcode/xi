<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Applicant's Account</title>
<link href="/board/resources/css/applicant.css" rel="stylesheet" />
<link href='/board/resources/css/font.css' rel='stylesheet' type='text/css' />
<script type="text/javascript" src="../resources/js/jquery.min.js"></script>

</head>
<body>
<div id="panelwrap">
  	<input type="hidden" id="from_where" name="from_where" value="<s:property value='from_where'/>" />
	<%@include file="Header.jsp" %>                    
    <div class="center_content" style="min-height: 500px;">  
    <div id="right_wrap">
    <div id="right_content">             
    <h2>Security Verification</h2> 
	<form name="applicationForm" id="applicationForm">
	<div class="tabcontent" style="height: 75px;padding-top: 25px;">
	<!-- 
			<div class="form_sub_buttons" style="width: 98%;float: left;padding-top: 2px;padding-left:250px;">
			    <a href="javascript:void(0)" class="button red" onclick="newOtpRequest()" style="font-size: 16px;height: 34px;padding-top:20px;width: 200px;">Request for New OTP</a>
		    </div>
	 -->		    
		    <div id="otpRequestButtonDiv1" style="width: 98%;float: left;padding-top: 2px;padding-left:20px;">
			    <p style="color: red; font-size: 30px; padding-bottom: 20px;">
			    	Registration Number
			    </p>
		    </div> 
            <div class="form_row" style="width: 720px;">
            	<label style="width: 216px;font-size: 16px;">SSC Registration Number:</label>
            	<input type="text" class="form_input" name="otp" id="otp" style="width: 200px;"/>
            	<a href="javascript:void(0)" class="button green" onclick="validateOtp()" style="margin-left: 10px;margin-top: 2px;font-size: 16px;">Verify Reg. Number</a>
            </div>
	</div>
	</form>
	
     </div>
     </div><!-- end of right content-->
                     
                    
    <div class="sidebar" id="sidebar">
    <%@include file="LeftSideBar.jsp" %>
   
    </div>             
   
    
    <div class="clear"></div>
    </div> <!--end of center_content-->
    <%@include file="Footer.jsp" %>


</div>
		
<script type="text/javascript">
function newOtpRequest(){

	$.ajax({
	 	url: 'sendNewOtp.action',		
	 	type: 'POST',
	 	success: function(data) {
	 		alert(data);
	 	},
	 	error: function(e) {
	 	 
	 	}
			});
		 		
}
function validateOtp(){
	if($("#otp").val()=="")
		return;
	$.ajax({
		 	url: 'validateOtpRl.action',
		 	data:{otp:$("#otp").val()},		
		 	type: 'POST',
		 	success: function(data) {
		 		if(data=="valid"){
		 		   if($("#from_where").val()=="choice_info")
		 				window.location="newChoiceList.action";
		 		   else if($("#from_where").val()=="personal_info")
		 		   		window.location="newApplicantInfo.action";	
		 		}
		 		else
		 			alert(data);
		 	},
		 	error: function(e) {
		 	 
		 	}
	 		});
}
</script>		
</body>
</html>