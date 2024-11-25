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
<div class="msg_div" style="height: 150p;color: blue;width: 850px;">

<!-- You have already applied. Please <a href="../login.action" style="font-weight: bold;">Click Here</a> to go to Login Page.

Use your Applicaiton No. And Password to Login into your account. -->

<center><font size="4" color="green">
&#2438;&#2474;&#2472;&#2495; &#2439;&#2468;&#2495;&#2478;&#2471;&#2509;&#2479;&#2503;&#2439; &#2438;&#2476;&#2503;&#2470;&#2472; &#2453;&#2480;&#2503;&#2459;&#2503;&#2472; 


<br>
Your have already applied</font></center>
<br><br>

<center>&#2438;&#2474;&#2472;&#2495; &#2488;&#2480;&#2509;&#2476;&#2507;&#2458;&#2509;&#2458; &#2535;&#2534; (&#2470;&#2486;)&#2463;&#2495; &#2438;&#2482;&#2494;&#2470;&#2494; &#2453;&#2482;&#2503;&#2460;/&#2488;&#2478;&#2478;&#2494;&#2472;&#2503;&#2480; &#2474;&#2509;&#2480;&#2468;&#2495;&#2487;&#2509;&#2464;&#2494;&#2472;&#2503; &#2468;&#2469;&#2509;&#2479; &#2489;&#2494;&#2482;&#2472;&#2494;&#2455;&#2494;&#2470; &#2453;&#2480;&#2468;&#2503; &#2437;&#2469;&#2476;&#2494; &#2474;&#2459;&#2472;&#2509;&#2470; &#2479;&#2507;&#2455; &#2453;&#2480;&#2468;&#2503; &#2474;&#2494;&#2480;&#2476;&#2503;&#2472;&#2404; &#2447;&#2453;&#2439; &#2453;&#2482;&#2503;&#2460;&#2503;&#2480; &#2438;&#2482;&#2494;&#2470;&#2494; Shift, Version &#2437;&#2469;&#2476;&#2494; Group &#2447;&#2453;&#2463;&#2495; &#2453;&#2482;&#2503;&#2460; &#2489;&#2495;&#2488;&#2494;&#2476;&#2503; &#2476;&#2495;&#2476;&#2503;&#2458;&#2495;&#2468; &#2489;&#2476;&#2503;
<br>
 <font size="4" color="red">
 You can update or add choices to maximum 10 (ten) different colleges/equivalent institutions. A separate Shift, Version or Group of the same college will be considered as a single college
 </font><br> </center> 
<br/>
<center>
   <div id="div_scode">
   <label for="contactno" style="color: #1D00B1; padding-right: 9px">Contact Number: </label>
      <input type="text" class="readonly" style="text-align: center" maxlength="11" value="" name="applicant.application_info.contactno" id="contactno"  placeholder="Your Mobile Number"/>
      <label for="scode" style="color: #1D00B1; padding-left: 37px; padding-right: 9px">Security Code: </label>
      <input type="text" class="readonly" style="text-align: center" maxlength="6" value="" name="applicant.application_info.scode" id="scode"  placeholder="Your Security Code"/>
   </div>
						<div
				style="float: none; position: relative; width: 100%; clear: both; height: 25px; color:red"  id="scodeinfo">
			</div>
	<input type="button" class="myButton" value="Update Application" onclick="goForUpdateApplicationChooses()"/>
	
</center>
<br>
<%--<center>
	<input type="button" class="myButton" value="Click here to Edit Quota" onclick="showQuotaInfo()"/>
	
</center>--%>


<div class="box_footer" style="padding: 3px; border-radius: 0px 0px 6px 6px">
		<input type="button" value="Previous Personal Information" onclick="showPersonalInfo()" style="width:250px;" />
</div>

<input type="hidden" id="ssc_roll_app" name="ssc_roll_app" value="<%= request.getAttribute("ssc_roll_app") %>" style="width: 135px;text-align: center;"/>
<input type="hidden" id="ssc_board_app" name="ssc_board_app" value="<%= request.getAttribute("ssc_board_app") %>" style="width: 135px;text-align: center;"/>
<input type="hidden" id="ssc_year_app" name="ssc_year_app" value="<%= request.getAttribute("ssc_year_app") %>" style="width: 135px;text-align: center;"/>
<input type="hidden" id="ssc_reg_app" name="ssc_reg_app" value="<%= request.getAttribute("ssc_reg_app") %>" style="width: 135px;text-align: center;"/>


</div>