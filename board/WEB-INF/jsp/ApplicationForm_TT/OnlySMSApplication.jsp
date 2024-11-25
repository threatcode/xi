<style>
.myButton {
	-moz-box-shadow:inset 0px 1px 0px 0px #e184f3;
	-webkit-box-shadow:inset 0px 1px 0px 0px #e184f3;
	box-shadow:inset 0px 1px 0px 0px #e184f3;
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #c123de), color-stop(1, #a20dbd));
	background:-moz-linear-gradient(top, #c123de 5%, #a20dbd 100%);
	background:-webkit-linear-gradient(top, #c123de 5%, #a20dbd 100%);
	background:-o-linear-gradient(top, #c123de 5%, #a20dbd 100%);
	background:-ms-linear-gradient(top, #c123de 5%, #a20dbd 100%);
	background:linear-gradient(to bottom, #c123de 5%, #a20dbd 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#c123de', endColorstr='#a20dbd',GradientType=0);
	background-color:#c123de;
	-moz-border-radius:6px;
	-webkit-border-radius:6px;
	border-radius:6px;
	border:1px solid #a511c0;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:15px;
	font-weight:bold;
	padding:6px 24px;
	text-decoration:none;
	text-shadow:0px 1px 0px #9b14b3;
}
.myButton:hover {
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #a20dbd), color-stop(1, #c123de));
	background:-moz-linear-gradient(top, #a20dbd 5%, #c123de 100%);
	background:-webkit-linear-gradient(top, #a20dbd 5%, #c123de 100%);
	background:-o-linear-gradient(top, #a20dbd 5%, #c123de 100%);
	background:-ms-linear-gradient(top, #a20dbd 5%, #c123de 100%);
	background:linear-gradient(to bottom, #a20dbd 5%, #c123de 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#a20dbd', endColorstr='#c123de',GradientType=0);
	background-color:#a20dbd;
}
.myButton:active {
	position:relative;
	top:1px;
}

</style>
<div class="msg_div" style="height: 150p;color: blue;">

<!-- You have already applied. Please <a href="../login.action" style="font-weight: bold;">Click Here</a> to go to Login Page.

Use your Applicaiton No. And Password to Login into your account. -->

<center><font size="4" color="green">Your have already applied via WEB or SMS.</font></center>
<br><br>
<center>You can also apply online <font size="4" color="red">up to 10 unique colleges after paying online fees.</font><br> .</center>
<br>
<center>
	<input type="button" class="myButton" value="Personal Information" onclick="showPersonalInfoSMS()"/>
	
</center>
<br>
<%--<center>
	<input type="button" class="myButton" value="Click here to Edit Quota" onclick="showQuotaInfo()"/>
	
</center>--%>	

<input type="hidden" id="ssc_roll_app" name="ssc_roll_app" value="<%= request.getAttribute("ssc_roll_app") %>" style="width: 135px;text-align: center;"/>
<input type="hidden" id="ssc_board_app" name="ssc_board_app" value="<%= request.getAttribute("ssc_board_app") %>" style="width: 135px;text-align: center;"/>
<input type="hidden" id="ssc_year_app" name="ssc_year_app" value="<%= request.getAttribute("ssc_year_app") %>" style="width: 135px;text-align: center;"/>
<input type="hidden" id="ssc_reg_app" name="ssc_reg_app" value="<%= request.getAttribute("ssc_reg_app") %>" style="width: 135px;text-align: center;"/>


</div>