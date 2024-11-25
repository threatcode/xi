<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Admission System Login</title>
<link href="/board/resources/css/applicant.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Belgrano'
	rel='stylesheet' type='text/css' />
</head>
<body>
	<div id="loginpanelwrap">

		<div class="loginheader">
			<div class="logintitle">
				<a href="javascript:void(0)">Admission System Login</a>
			</div>
		</div>

		<form action="checkValidity" method="post">
			<div class="loginform">

				<div class="loginform_row">
					<label><span style="font-size: 14px;">User ID :</span></label> <input
						type="text" name="user.userid" class="loginform_input" value=""
						required />
				</div>
				<div class="loginform_row">
					<label><span style="font-size: 14px;">Password :</span></label> <input
						type="password" name="user.password" class="loginform_input"  title="&#2476;&#2507;&#2480;&#2509;&#2465; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2474;&#2494;&#2488;&#2451;&#2527;&#2494;&#2480;&#2509;&#2465; &#2476;&#2509;&#2479;&#2476;&#2489;&#2494;&#2480; &#2453;&#2480;&#2468;&#2503; &#2489;&#2476;&#2503;&#2404;"
						 
						value="" required />
				</div>
<!-- 
				<div class="loginform_row" style="margin-left: 30px" >
					<span style="font-size: 14px;">
						&#2476;&#2507;&#2480;&#2509;&#2465; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2474;&#2494;&#2488;&#2451;&#2527;&#2494;&#2480;&#2509;&#2465; &#2476;&#2509;&#2479;&#2476;&#2489;&#2494;&#2480; &#2453;&#2480;&#2468;&#2503; &#2489;&#2476;&#2503;&#2404; 
					</span> 
				</div>
 -->				
				<div class="loginform_row">
					<input type="submit" class="loginform_submit" value="Login" />
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




