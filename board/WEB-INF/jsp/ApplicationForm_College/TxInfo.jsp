<div class="box_container" style="height: 240px;margin-top: 8px;">
<div class="box_header">
			TeleTalk Transaction Information
</div>
<div class="box_body" style="min-height: 143px;">
  <p id="TransactionError" style="text-align: left;"  >
  	&#2468;&#2507;&#2478;&#2494;&#2480; &#2474;&#2503;&#2478;&#2503;&#2472;&#2509;&#2463; &#2488;&#2434;&#2453;&#2509;&#2480;&#2494;&#2472;&#2509;&#2468; &#2453;&#2507;&#2472;&#2507; &#2468;&#2469;&#2509;&#2479; &#2438;&#2478;&#2494;&#2470;&#2503;&#2480; &#2453;&#2494;&#2459;&#2503; &#2472;&#2503;&#2439;&#2404; &#2468;&#2497;&#2478;&#2495; &#2479;&#2470;&#2495; &#2478;&#2472;&#2503; &#2453;&#2480; &#2479;&#2503; &#2447;&#2463;&#2495; &#2488;&#2464;&#2495;&#2453; &#2472;&#2527;, &#2468;&#2476;&#2503; &#2472;&#2496;&#2458;&#2503;&#2480; &#2476;&#2453;&#2509;&#2488;&#2503; &#2468;&#2507;&#2478;&#2494;&#2480; TXID &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480;&#2404;
  	<br/>
  	We do not have any information regarding your payment. If you think that this is a mistake, please provide your TXID in the following box.
  </p>
  
  <div id="txid_input_div" style="padding-top: 1px;padding-left: 10px;">
  
  
  	  <table width="100%" border="0" style="border: none;" id="txid_input_table" align="center">
  	  	<tr>
  	  		<td width="33%" align="center" style="font-weight: bold;border: none;"></td>
  	  		<td width="33%" align="center" style="font-weight: bold;border: none;">Transaction ID.</td>
  	  		<td width="33%" align="center" style="font-weight: bold;border: none;"></td>
  	  	</tr>
  	  	<tr>
  	  		<td width="33%" align="center" style="font-weight: bold;border: none;"></td>
  	  		<td width="33%" align="center" style="font-weight: bold;border: none;"><input type="text" id="txid" style="width: 135px;text-align: center;" maxlength="8"/></td>
  	  		<td width="33%" align="center" style="font-weight: bold;border: none;"></td>
  	  	</tr>
  	  	<tr>
  	  		<td align="center" style="border: none;" colspan="4">&nbsp; </td>
  	  	</tr>
  	  	
  	  	
  	  	<tr>
  	  	
  	  	<td align="center" style="border: none;"></td>
  	  	<td align="center" style="border: none;">
         
 	    
        </td>
        <td align="center" style="border: none;"></td>
        <td align="center" style="border: none;"><FONT COLOR="green" size="4">Verification*</FONT></td>
  	  	</tr>
  	  	
  	  	<tr>
  	  		<td align="center" style="border: none;" colspan="2">&nbsp; </td>
  	  		<td align="center" style="border: none;text-align: right" colspan="2">
  	  			<FONT COLOR="green" size="4"><span id="three"></span> + <span id="four"></span> = ?</FONT>
		        <input type="text" id="tx_captcha" name="tx_captcha" style="width: 70px;text-align: center;"/>	        
  	  		</td>
  	  	</tr>
  	  	
  	  </table>
	  
  </div>
  
  <script>
  var three1=<%= request.getAttribute("three") %>;
  var four1=<%= request.getAttribute("four") %>;
	document.getElementById("three").innerHTML=three1;
	document.getElementById("four").innerHTML=four1;
 
  </script>
  
  
  <%-- <FONT COLOR="RED">
        <%= (int) (Math.random() * 10) %> + <%=(int) (Math.random() * 10) %> = 
        <%= request.getAttribute("one") %> + <%= request.getAttribute("two") %> = 
  </FONT> --%>
  <%-- <input type="text" id="ssc_user_captcha" name="u_captcha"/>
  <input type="text" id="ssc_java_captcha" name="j_cap" value="<%= request.getAttribute("sum") %>"/> --%>
  
  <!-- <input type="button" value="Check" onclick="v()" style="width:100px;"/> -->
  
  
</div>

<div class="box_footer">

 <input type="button" value="Next" onclick="validateTxInfo()" style="width:100px;"/>
</div>
</div>