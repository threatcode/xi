<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Admission System Login</title>
<link rel="icon" type="image/x-icon" href="/board/resources/_images/favicon.ico"/>
<link href="/board/resources/css/applicant.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Belgrano'
	rel='stylesheet' type='text/css' />

 <script src="/board/resources/js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
 <script src="/board/resources/js/jquery.md5.min.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript" charset="utf-8">

 
function loginAction(){
	
 $('#hpassword').val($.MD5($('#password').val()));	
  var myForm=document.getElementById("loginForm");
  myForm.action="checkValidity";
  myForm.submit();
}    

function IsNumeric(sText) {
	var ValidChars = "0123456789.";
	var IsNumber = true;
	var Char;
	for (i = 0; i < sText.length && IsNumber == true; i++) {
		Char = sText.charAt(i);
		if (ValidChars.indexOf(Char) == -1) {
			IsNumber = false;
		}
	}
	return IsNumber;
}

 
function numericOnly(evt)
	{
	         var charCode = (evt.which) ? evt.which : evt.keyCode
	         if(charCode==46 || (charCode>=37 && charCode<=40))
	         	return true;
	         
	         if (charCode > 31 && (charCode < 48 || charCode > 57))
	            return false;
	
	         return true;
	
	}
    
  </script>
	

	
</head>


<body>
	<div id="loginpanelwrap">

		<div class="loginheader">
			<div class="logintitle">
				<a href="javascript:void(0)">XIClass Password System</a>
			</div>
		</div>


		<form id="loginForm" action="checkValidity" method="post">
			<div class="loginform">

				<div class="loginform_row">
					<label><span style="font-size: 14px;">EIIN :</span></label> <input
						type="text" name="user.userid" id="userid" class="loginform_input" value=""  onkeydown="return numericOnly(event)"
						required />
				</div>
				<div class="loginform_row" id="collegeInformation">
				
				</div>

 				<s:if test="#session.error != null && #session.error != ''">
 				<center>
 				<label><span style="font-size: 20px;color: red">
 					<s:property value="#session.error"/>
 					</span></label>
 					</center>
 				</s:if>
 				
				<div class="loginform_row">
					<input type="submit" id="md5" class="loginform_submit" value="Reset Password" />
				</div>

				<div class="clear"></div>
			</div>
		</form>

	</div>

	<!--
<a href="pinretrival">
<div id="loginpanelwrap" style="height:40px;padding-top:20px;margin-top:20px;width:31%;text-align:center;font-weight:bold;color:red;font-size:18px;">
<font color="blue">##</font> Application ID and Password  Recovery <font color="blue">##</font>
</div>
</a>
 
 <a href="getfeedback">
<div id="loginpanelwrap" style="height:40px;padding-top:20px;margin-top:20px;width:31%;text-align:center;font-weight:bold;color:red;font-size:18px;">
<font color="blue">##</font> Applicant Feedback Here <font color="blue">##</font>
</div>
</a>
-->

</body>
</html>




