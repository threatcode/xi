<div class="box_container" style="margin-top: 8px;">
<div class="box_header">
			Payment Transaction Information
</div>
<div class="box_body" style="min-height: 143px; padding:  20px; font-size: 16px; color: #04213f; font-weight: bold">
  <p id="TransactionError" style="text-align: left;"  >
  	&#2438;&#2474;&#2472;&#2494;&#2480; &#2474;&#2503;&#2478;&#2503;&#2472;&#2509;&#2463; &#2488;&#2434;&#2453;&#2509;&#2480;&#2494;&#2472;&#2509;&#2468; &#2453;&#2507;&#2472;&#2507; &#2468;&#2469;&#2509;&#2479; &#2438;&#2478;&#2494;&#2470;&#2503;&#2480; &#2453;&#2494;&#2459;&#2503; &#2472;&#2503;&#2439;&#2404; &#2438;&#2474;&#2472;&#2495; &#2479;&#2470;&#2495; &#2478;&#2472;&#2503; &#2453;&#2480;&#2503;&#2472; &#2479;&#2503; &#2447;&#2463;&#2495; &#2488;&#2464;&#2495;&#2453; &#2472;&#2527;, &#2468;&#2476;&#2503; &#2472;&#2496;&#2458;&#2503;&#2480; &#2476;&#2453;&#2509;&#2488;&#2503; &#2438;&#2474;&#2472;&#2494;&#2480; TXID &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480;&#2497;&#2472;&#2404;
  	<br/><br/>
  	We do not have any information regarding your payment. If you think that this is a mistake, please provide your TXID in the following box.
  </p>
  
  <div id="txid_input_div" style="padding-top: 1px;padding-left: 10px;">
  
  
  	  <table width="100%" border="0" style="border: none;" id="txid_input_table" align="center">
  	  	<tr>
  	  		<td width="33%" align="center" style="font-weight: bold;border: none; font-size: 17px; color: red; font-weight: bold"">Organization</td>
  	  		<td width="33%" align="left" style="font-weight: bold;border: none;"> 
  	  		<select id="organization" name="organization">
													 <option value="" selected>Select</option>  	  		
													<option value="BKash">BKash</option>
													<option value="NAGAD">Nagad</option>
													<option value="Rocket">Rocket</option>
													<option value="SBL">Sonali Bank</option>
													<option value="SC">SureCash</option>
													<option value="TT">Teletalk</option>
													
													 
													 
							</select> 
						</td>
  	  		<td width="33%" align="center" style="font-weight: bold;border: none;"></td>
  	  	</tr>
  	  	<tr>
  	  		<td width="33%" align="center" style="font-weight: bold;border: none; font-size: 17px; color: red; font-weight: bold">Transaction ID</td>
  	  		<td width="33%" align="left" style="font-weight: bold;border: none;"><input type="text" id="trxid"  autocomplete="off"  style="width: 135px;text-align: center;"/></td>
  	  		<td width="33%" align="center" style="font-weight: bold;border: none;"></td>
  	  	</tr>
  	  	
  	  	
  	  	<tr>
  	  	
  	  	<td align="center" style="border: none;"></td>
  	  	<td align="center" style="border: none;"></td>
        <td width="25%" align="center" style="border: none; padding-right: 5px; text-align: right; font-family: 'Trebuchet MS', Arial, Helvetica, sans-serif;"><FONT COLOR="green" size="4">Verification</FONT>
	        </td>
  	  	</tr>
  	  	</table>
  	  	<table style="width: 100%">
  	  	
  	  	<tr>
  	  		<td align="right" style="border: none;text-align: right:width: 70%">
			        <img id="captchaImg_tx" alt="Captcha Image" height="30">&nbsp;&nbsp;
			        </td><td style="border: none;text-align: left;width: 10%">
			        <input type="text" id="tx_captcha" name="tx_captcha" style="width: 70px;text-align: center;"  height="30"/></td>
  	  	</tr>
  	  	
  	  </table>
	  
  </div>
</div>

<div class="box_footer">

 <input type="button" value="Next" onclick="validateTxInfo()" style="width:100px;"/>
</div>
</div>