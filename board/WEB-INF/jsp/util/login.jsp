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
    jQuery(document).ready(function($) {
      $('#md5').click(function(){
        $('#hpassword').val($.MD5($('#password').val()));
      });
    });
    
function isEnterKey(KEYCODE,OBJECT)
{
 if(KEYCODE==13)
 {
   loginAction();
 }
}
 
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

 
	function checkUser()
	{
		if(IsNumeric($('#userid').val().trim()) &&  $('#userid').val().trim().length==6)
		{
			
			//$('#password').val('');
  			$.ajax({
			 	url: 'getCollege',		
			 	type: 'POST',
			 	async: false,
			 	data: {eiin:$('#userid').val().trim()},
			 	success: function(data) {
			 		if(data.mobileVerifiedYN=='N')
			 		{
			 			location.href='loginCollege';
			 		}
			 	},
			 	error: function(e) {
			 		console.log(e);
			 	}
	 		});
		}
	} 
    
  </script>
	

	
</head>


<body>
	<div id="loginpanelwrap">

		<div class="loginheader">
			<div class="logintitle">
				<a href="javascript:void(0)">Admission System Login</a>
			</div>
		</div>
<%-- <script>
function googleTranslateElementInit() {
  new google.translate.TranslateElement({
    pageLanguage: 'es',
    layout: google.translate.TranslateElement.InlineLayout.SIMPLE
  }, 'google_translate_element');
}
</script>
<script src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script> --%>

		<form id="loginForm" action="checkValidity" method="post">
			<div class="loginform">

				<div class="loginform_row">
					<label><span style="font-size: 14px;">EIIN/ User ID :</span></label> <input
						type="text" name="user.userid" id="userid" class="loginform_input" value="" onchange="checkUser()"
						required />
				</div>
				<div class="loginform_row">
					<label><span style="font-size: 14px;">Password :</span></label> <input
						type="password" id="password"  class="loginform_input"  
						 		value="" required  onkeyup="isEnterKey(event.keyCode,this);"/>
						 		
					<input type="hidden"  id="hpassword" name="user.password" value="" />	 		
						 		
				</div>
<!-- 				<div class="loginform_row">
					<label><span style="font-size: 14px;">Password :</span></label> <input
						type="password" name="user.password" class="loginform_input"  title="&#2476;&#2507;&#2480;&#2509;&#2465; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2474;&#2494;&#2488;&#2451;&#2527;&#2494;&#2480;&#2509;&#2465; &#2476;&#2509;&#2479;&#2476;&#2489;&#2494;&#2480; &#2453;&#2480;&#2468;&#2503; &#2489;&#2476;&#2503;&#2404;"
						 placeholder="&#2476;&#2507;&#2480;&#2509;&#2465; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2474;&#2494;&#2488;&#2451;&#2527;&#2494;&#2480;&#2509;&#2465; &#2476;&#2509;&#2479;&#2476;&#2489;&#2494;&#2480; &#2453;&#2480;&#2468;&#2503; &#2489;&#2476;&#2503;&#2404;"
						value="" required />
				</div> -->
<!-- 
				<div class="loginform_row" style="margin-left: 30px" >
					<span style="font-size: 14px;">
						&#2476;&#2507;&#2480;&#2509;&#2465; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2474;&#2494;&#2488;&#2451;&#2527;&#2494;&#2480;&#2509;&#2465; &#2476;&#2509;&#2479;&#2476;&#2489;&#2494;&#2480; &#2453;&#2480;&#2468;&#2503; &#2489;&#2476;&#2503;&#2404; 
					</span> 
				</div>
 -->				
 				<s:if test="#session.error != null && #session.error != ''">
 				<center>
 				<label><span style="font-size: 20px;color: red">
 					<s:property value="#session.error"/>
 					</span></label>
 					</center>
 				</s:if>
 				
				<div class="loginform_row">
					<input type="submit" id="md5" class="loginform_submit" value="Login" />
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




