<%@ taglib prefix="s" uri="/struts-tags"%>
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
	margin-top: 3px;
	margin-bottom: 3px;
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

<img src="/board/resources/images/warning_message.jpg" border="0" style="margin-top: 6px; width: 800px;height: 60px;"/>

<div class="box_container" style="margin-top: 2px;">
	<div class="box_header">
	SSC / Equivalent Examination Information
	</div>

	<div class="box_body" style="margin-top: 0px;margin-bottom: 0px;">
	  
	  <div id="ssc_input_div" style="padding-top: 20px;padding-left: 10px;">
	  
	  
	  	  <table width="100%" border="0" style="border: none;" id="ssc_input_table">
	  	  	<tr>
	  	  		<td width="25%" align="center" style="font-weight: bold;border: none;">Roll No.</td>
	  	  		<td width="25%" align="center" style="font-weight: bold;border: none;">Board</td>
	  	  		<td width="25%" align="center" style="font-weight: bold;border: none;">Passing Year</td>
	  	  		<td width="25%" align="center" style="font-weight: bold;border: none;">Reg No.</td> 
	  	  	</tr>
	  	  	<tr>
	  	  		<td align="center" style="border: none;"><input type="text" name="user.ssc_roll" id="ssc_roll" style="width: 135px;text-align: center;" value="" maxlength="15"/></td>
	  	  		<td align="center" style="border: none;">
	  	  			<select style="width:120px;" name="user.ssc_board" id="ssc_board" onchange="reloadYear(this.value)" style="width: 135px;">
				     <option value="" selected>Select Board</option>
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
					<option value="19">TEC</option></select>
	  	  		</td>
	  	  		<td align="center" style="border: none;">
	  	  			<select style="width:135px;" name="user.ssc_year" id="ssc_year" style="width: 135px;">
				     <option value="" >Select Year</option>
				     <option value="2020">2020</option>
			     <option value="2019">2019</option>
			     <option value="2018">2018</option>
				    </select>
	  	  		</td>
	  	  		<td align="center" style="border: none;"><input type="text" name="user.ssc_reg" id="ssc_reg" style="width: 135px;text-align: center;" maxlength="15" value=""/></td>
	  	  		
	  	  		
	  	  	</tr>
	  	  	<tr>
	  	  		<td align="center" style="border: none;" colspan="4">&nbsp; </td>
	  	  	</tr>
	  	  	<tr>
	  	  		<td width="25%" align="center" style="font-weight: bold;border: none;">&nbsp;</td>
	  	  		<td width="25%" align="center" style="font-weight: bold;border: none;"><label for="contactno" style="color: #1D00B1;">Contact Number: </label></td>
	  	  		<td width="25%" align="center" style="font-weight: bold;border: none;"><label for="scode" style="color: #1D00B1;">Security Code: </label></td>
	  	  		<td width="25%" align="center" style="font-weight: bold;border: none;">&nbsp;</td>
	  	  	</tr>
	  	  	
	  	  	
	  	  	<tr>
	  	  	 <td width="25%" align="center" style="border: none;">&nbsp;</td>
	  	  	<td width="25%" align="center" style="border: none;">
      <input type="text" class="readonly" style="width: 135px; text-align: center;" maxlength="11" value="" name="user.mobile" id="mobile"  placeholder="Your Mobile Number"/></td>
	  	  	<td width="25%" align="center" style="border: none;">
	         
      <input type="text" class="readonly" style="text-align: center" maxlength="6" value="" name="user.scode" id="scode"  placeholder="Your Security Code"/>
	 	    
	        </td>
	       <td width="25%" align="center" style="border: none; padding-right: 9px; text-align: right; font-family: 'Trebuchet MS', Arial, Helvetica, sans-serif;"><FONT COLOR="green" size="4">Verification</FONT><FONT COLOR="red" size="4">*</FONT>
	        </td>
	        
	  	  	</tr>
	  	  	
	  	  	<tr>
	  	  	<td width="25%" align="center" style="font-weight: bold;border: none;" colspan="3">&nbsp;</td>
	  	  	<td align="center" style="border: none;text-align: right; overflow: auto;" colspan="1">
			        <table border="0"><tr><td style="border: 0px">
			        <img id="captchaImg" src="captcha" alt="Captcha Image" height="30">
			        		</td><td style="border: 0px">
			        <input type="text" id="user_captcha" name="user_captcha" style="width: 70px;text-align: center;"/>
			        		</td></tr>
			        </table>       		        
	  	  		</td>
	  	  	</tr>
	  	  </table>
		  
	  </div>
	  
	</div>
	<div class="box_footer" style="height: 39px;">

	 <input type="submit" class="myButton blue loginMigration" value="Click here for Migration" />
	 
	</div>
	
</div>
<div id="response_div" class="response_div" style="display: none;">
<img src='/board/resources/images/239.gif' />
</div>
<s:if test='#request.errormessage=="captchaError"'> 
          <div id="personal_info_div"><div class="msg_div" style="padding:  20px; font-size: 18px; color: red; font-weight: bold">
          &#2470;&#2527;&#2494; &#2453;&#2480;&#2503; &#2453;&#2509;&#2479;&#2494;&#2474;&#2458;&#2494; &#2463;&#2503;&#2453;&#2509;&#2488;&#2463;&#2463;&#2495; &#2488;&#2464;&#2495;&#2453;&#2477;&#2494;&#2476;&#2503; &#2463;&#2494;&#2439;&#2474; &#2453;&#2480;&#2497;&#2472;&#2404;
<br><br>
Please type the Captcha text accurately.
</div></div>
<br>
        </s:if>
<s:if test='#request.errormessage=="infoError"'> 
          <div id="personal_info_div"><div class="msg_div" style="padding:  20px; font-size: 18px; color: red; font-weight: bold">
&#2468;&#2507;&#2478;&#2494;&#2480; &#2470;&#2503;&#2451;&#2479;&#2492;&#2494; &#2468;&#2469;&#2509;&#2479; &#2488;&#2464;&#2495;&#2453; &#2472;&#2479;&#2492;&#2404;
<br><br>
The information you have provided is not correct.
</div></div>
<br>
        </s:if>
