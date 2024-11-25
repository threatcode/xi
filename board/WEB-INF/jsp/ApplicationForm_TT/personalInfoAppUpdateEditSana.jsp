<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="box_container" style="min-height:198px;margin-top: 8px;" id="personal_info_div">

<div class="box_header">
Personal Information
</div>

<div class="box_body" style="min-height:190px;">
<input type="hidden" name="applicant.application_id" id="application_id_old"  value="<s:property value='applicant.application_id' />" />
<input type="hidden"  name="applicant.application_info.mobile_number" id="mobile_number_web"   value="<s:property value='applicant.application_id' />" />	
  <div id="personal_info_div"  style="padding-top: 10px;padding-left: 10px;height: 500px">
	  <div class="box_row">
		  <div style="float: left;width: 30%;">
		    <label style="width: 80px;">Roll No.</label>
			<input type="text" name="applicant.ssc_info.roll" id="p_ssc_roll" class="readonly" disabled="disabled" value="<s:property value='applicant.ssc_info.roll' />" />
			
		  </div>
		  <div style="float: left;width: 33%;">    
		    <label style="width: 100px;">Board</label>
			<input type="text" name="applicant.ssc_info.board_name" id="p_board_name" class="readonly" disabled="disabled" value="<s:property value='applicant.ssc_info.board_name' />" />
			<input type="hidden" name="applicant.ssc_info.board_id" id="p_board_id" value="<s:property value='applicant.ssc_info.board_id' />" />
			<input type="hidden" name="applicant.ssc_info.group_id" id="p_group_id" value="<s:property value='applicant.ssc_info.group_id' />" />
			<input type="hidden" name="applicant.ssc_info.reg_no" id="p_ssc_reg" value="<s:property value='applicant.ssc_info.reg_no' />" />
		  </div>
		  <div style="float: left;width: 36%;">
		    <label style="width: 120px;">Passing Year</label>
			<input type="text" name="applicant.ssc_info.passing_year" id="p_passing_year" class="readonly" disabled="disabled" value="<s:property value='applicant.ssc_info.passing_year' />" />
		  </div>
	  </div>
	  <div class="box_row">
		  <div style="float: left;width: 30%;">
		    <label style="width: 80px;">Name</label>
			<input type="text" name="applicant.ssc_info.student_name" id="p_student_name" class="readonly" disabled="disabled" value="<s:property value='applicant.ssc_info.student_name' />" />
			
		  </div>
		  <div style="float: left;width: 33%;">    
		    <label style="width: 100px;">Father's Name</label>
			<input type="text" name="applicant.ssc_info.father_name" id="p_father_name" class="readonly" disabled="disabled" value="<s:property value='applicant.ssc_info.father_name' />"  />
		  </div>
		  <div style="float: left;width: 36%;">
		    <label style="width: 120px;">Mother's Name</label>
			<input type="text" name="applicant.ssc_info.mother_name" id="p_mother_name" class="readonly" disabled="disabled" value="<s:property value='applicant.ssc_info.mother_name' />"  />
		  </div>
	  </div>
	  <div class="box_row">
		  <div style="float: left;width: 30%;">
		    <label style="width: 80px;">Gender</label>
			<input type="text" name="applicant.ssc_info.gender_name" id="p_gender_name" class="readonly" disabled="disabled" value="<s:property value='applicant.ssc_info.gender_name' />"  />
			<input type="hidden" name="applicant.ssc_info.gender_id" id="p_gender" class="readonly" value="<s:property value='applicant.ssc_info.gender_id' />"  />			
		  </div>

		  <div style="float: left;width: 33%;">    
		    <label style="width: 100px;">GPA</label>
			<input type="text" name="applicant.ssc_info.gpa" id="p_gpa"  class="readonly" disabled="disabled" value="<s:property value='applicant.ssc_info.gpa' />"  />			
			<input type="hidden" name="applicant.ssc_info.gpa_exc4th" id="p_gpa_exc4th"  class="readonly"  value="<s:property value='applicant.ssc_info.gpa_exc4th' />"  />
			<input type="hidden" name="applicant.ssc_info.eiin" id="p_eiin"  class="readonly"  value="<s:property value='applicant.ssc_info.eiin' />"  />			
		  </div>
		  <div style="float: left;width: 36%;">
		    <label style="width: 120px;">Application ID</label>
		    <input type="text" name="application_id" id="application_id"  class="readonly" disabled="disabled" value="<s:property value='applicant.application_id' />" />
		    <!-- 
		    <input type="hidden" name="applicant.application_info.quota_ff" id="p_eiin"  class="readonly"  value="<s:property value='applicant.application_info.quota_ff' />"  />	
		    <input type="hidden" name="applicant.application_info.quota_eq" id="p_eiin"  class="readonly"  value="<s:property value='applicant.application_info.quota_eq' />"  />	
		    <input type="hidden" name="applicant.application_info.quota_bksp" id="p_eiin"  class="readonly"  value="<s:property value='applicant.application_info.quota_bksp' />"  />	
		    <input type="hidden" name="applicant.application_info.quota_expatriate" id="p_eiin"  class="readonly"  value="<s:property value='applicant.application_info.quota_expatriate' />"  />	
		     -->
		  </div>
	  </div>
	  <div class="box_row">
		  <div style="float: left;width: 30%;">    
		    <label style="width: 80px;">&#2486;&#2495;&#2453;&#2509;&#2487;&#2494; &#2453;&#2507;&#2463;&#2494;</label>
			<input type="text" class="readonly" disabled="disabled" maxlength="5" value="<s:property value='applicant.application_info.quota_eq' />" />
		  </div>
		  <div style="float: left;width: 33%;">
		    <label style="width: 100px;">&#2474;&#2509;&#2480;&#2476;&#2494;&#2488;&#2496; &#2453;&#2507;&#2463;&#2494;</label>
			<input type="text" class="readonly" disabled="disabled" maxlength="5" value="<s:property value='applicant.application_info.quota_expatriate' />" />
		  </div>
		  <div style="float: left;width: 36%;">
		    <label style="width: 120px;">&#2478;&#2497;&#2453;&#2509;&#2468;&#2495;&#2479;&#2507;&#2470;&#2509;&#2471;&#2494; &#2453;&#2507;&#2463;&#2494;</label>
  			<input type="text" class="readonly" disabled="disabled" maxlength="5" value="<s:property value='applicant.application_info.quota_ff' />" />
		  </div>

	  </div>
	  
	  
	  <!-- quata edit -->
	   <div class="box_row" style="clear:both; float: left;width: 50%;text-align: left">
					<table>
						<tr>
							<td style="border: 0px"><a href="#" id="FreedomFighterQuota">
									&#2478;&#2497;&#2453;&#2509;&#2468;&#2495;&#2479;&#2507;&#2470;&#2509;&#2471;&#2494;
									&#2453;&#2507;&#2463;&#2494; (Freedom Fighter Quota) </a></td>
							<td style="border: 0px"><input type="checkbox"
								name="applicant.application_info.quota_ff" value="Y" 
								<s:if test='applicant.application_info.quota_ff == "Yes"'> checked </s:if>
								id="FreedomFighterQuotaCheckBox"></td>
						</tr>
						<!--
						<tr>
							<td style="border: 0px"><a href="#" id="ExpatriateQuota">
									&#2474;&#2509;&#2480;&#2476;&#2494;&#2488;&#2496;
									&#2453;&#2507;&#2463;&#2494; (Expatriate Quota) </a></td>
							<td style="border: 0px"><input type="checkbox"
								name="applicant.application_info.quota_expatriate" value="Y"
								<s:if test='applicant.application_info.quota_expatriate == "Yes"'> checked </s:if>
								id="ExpatriateQuotaCheckBox"></td>
						</tr>
						-->
						<!-- <td style="border: 0px">
				    			&#2476;&#2495;&#2453;&#2503;&#2447;&#2488;&#2474;&#2495; &#2453;&#2507;&#2463;&#2494; (BKSP Quota)
				    		</td>
				    		<td style="border: 0px">
								<input type="checkbox" name="applicant.application_info.quota_bksp" value="Y">
							</td>
						-->
					</table>
				</div>
				
				<div style="float: left;width: 50%;text-align: left;display: none">
					<table>
						<tr>
							<td style="border: 0px"><a href="#" id="EducationQuota">
									&#2486;&#2495;&#2453;&#2509;&#2487;&#2494;
									&#2453;&#2507;&#2463;&#2494; (Education Quota) </a></td>
							<td style="border: 0px"><input type="checkbox"
								name="applicant.application_info.quota_eq" value="Y"
								<s:if test='applicant.application_info.quota_eq == "Yes"'> checked </s:if>
								id="EducationQuotaCheckBox"></td>
						</tr>
						<tr>
							<td style="border: 0px"></td>
							<td style="border: 0px"></td>
						</tr>
					</table>
				</div>
				
				
	  <!-- Quata edit end -->
	  
	  
	  <div class="box_row merit_row">
                              <div style="float: left;width: 45%;">
                                    <label style="width: 120px;">Merit Position</label>
                                    <input type="text"
						name="applicant.mpos" class="readonly"
						disabled="disabled"
						value="<s:property value='applicant.mpos' />" />
                              </div>
                              <!--
				<div style="float: left;width: 45%;">
					<label style="width: 200px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Merit Position (All Boards)</label> <input type="text"
						name="applicant.mpos" class="readonly"
						disabled="disabled"
						value="<s:property value='applicant.apos' />" /> 
				</div>
				-->
                        </div>

<%-- 	  <div class="box_row" style="background-color: #151B54;color: white;text-align: left;font-weight: bold;padding-top: 2px;font-size: 15px;height: 22px;">
	  Do you want to change Quota for College Admission ?
	  </div>
	  <div class="box_row">
	  	  <div style="float: left;width: 33%;">
	      
		    <label style="width: 160px;text-align: left;float:left;">Quota (Freedom Fighter)</label>
		    <!-- <div style="float:left;width:200px;"> -->
				 <select id="quotaFF" name="applicant.application_info.quota_ff" style="width:70px;" >
				  	<option value="N" selected="selected">No</option>
				  	<option value="Y">Yes</option>
				 </select>
			<!-- </div> -->
	 </div>
	 
	
	<div style="float: left;width: 33%;">
	      
		    <label style="width: 160px;text-align: left;float:left;">Quota (Education)</label>
		    <!-- <div style="float:left;width:200px;"> -->
				 <select id="quotaEQ" name="applicant.application_info.quota_eq" style="width:70px;" >
				  	<option value="N" selected="selected">No</option>
				  	<option value="Y">Yes</option>
				 </select>
			<!-- </div> -->
	</div>

	  
	  
	</div>
	
	<div class="box_row">
	<div style="float: left;width: 33%;">
	      
		    <label style="width: 160px;text-align: left;float:left;">Quota (BKSP)</label>
		    <!-- <div style="float:left;width:200px;"> -->
				 <select id="quotaBKSP" name="applicant.application_info.quota_bksp" style="width:70px;" >
				  	<option value="N" selected="selected">No</option>
				  	<option value="Y">Yes</option>
				 </select>
			<!-- </div> -->
	</div>
	  
	  
	<!-- </div> -->
	
	<div style="float: left;width: 33%;">
	      
		    <label style="width: 160px;text-align: left;float:left;">Quota (Expatriate)</label>
		    <!-- <div style="float:left;width:200px;"> -->
				 <select id="quotaEX" name="applicant.application_info.quota_expatriate" style="width:70px;" >
				  	<option value="N" selected="selected">No</option>
				  	<option value="Y">Yes</option>
				 </select>
			<!-- </div> -->
	</div>
	   
</div> --%>
				
  </div>
  
</div>

<div class="box_footer">
	<input type="button" onclick="showPopUp('choiceListReport_TT')" value="Print Previous Application"/>
	
 		<input type="button" value="Add More" onclick="collegeSelectionUpdateEdit()" style="width:100px;" />
 	
</div>

</div>


<script type="text/javascript">
function showPopUp(actionName){
	//window.open(actionName+'?application_id='+$('#application_id_old').val(),'1433313485446','width=780,height=850,toolbar=0,menubar=0,location=0,status=1,scrollbars=1,resizable=0,left=0,top=0');
	window.open(actionName,'1433313485446','width=780,height=850,toolbar=0,menubar=0,location=0,status=1,scrollbars=1,resizable=0,left=0,top=0');s
}
</script>   







<div id="popUpDiv1" style="display:none">

	<!-- <a href="#" onclick="popup('popUpDiv1');scrollToBottom();" >Close</a><br/><br/> -->
	<br />
	<br />
	<div
		style="margin-top: 0px;margin-bottom: 0px;margin-left: 5px;margin-right: 5px;font-size: 20px;font-weight: bold;color: blue">
		&#2478;&#2497;&#2453;&#2509;&#2468;&#2495;&#2479;&#2507;&#2470;&#2509;&#2471;&#2494;
		&#2453;&#2507;&#2463;&#2494; (Freedom Fighter Quota)
		<hr>
		<div
			style="text-align: left;font-size: 20px;font-weight: normal;color: black">
			&#2478;&#2497;&#2453;&#2509;&#2468;&#2495;&#2479;&#2507;&#2470;&#2509;&#2471;&#2494;&#2480;
			&#2488;&#2472;&#2509;&#2468;&#2494;&#2472;/&#2488;&#2472;&#2509;&#2468;&#2494;&#2472;&#2503;&#2480;
			&#2488;&#2472;&#2509;&#2468;&#2494;&#2472;&#2470;&#2503;&#2480;
			&#2460;&#2472;&#2509;&#2479; &#2447;&#2439;
			&#2453;&#2507;&#2463;&#2494;
			&#2474;&#2509;&#2480;&#2479;&#2507;&#2460;&#2509;&#2479;
			&#2489;&#2476;&#2503;&#2404; <br />
			<br /> &#2453;&#2507;&#2472;
			&#2486;&#2495;&#2453;&#2509;&#2487;&#2494;&#2480;&#2509;&#2469;&#2496;,
			&#2453;&#2507;&#2472;
			&#2453;&#2482;&#2503;&#2460;/&#2488;&#2478;&#2478;&#2494;&#2472;&#2503;&#2480;
			&#2486;&#2495;&#2453;&#2509;&#2487;&#2494;
			&#2474;&#2509;&#2480;&#2468;&#2495;&#2487;&#2509;&#2464;&#2494;&#2472;&#2503;,
			&#2478;&#2497;&#2453;&#2509;&#2468;&#2495;&#2479;&#2507;&#2470;&#2509;&#2471;&#2494;
			&#2453;&#2507;&#2463;&#2494;&#2527;
			&#2477;&#2480;&#2509;&#2468;&#2495;&#2480;
			&#2460;&#2472;&#2509;&#2479;
			&#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2495;&#2468;
			&#2489;&#2482;&#2503;, &#2477;&#2480;&#2509;&#2468;&#2495;&#2480;
			&#2488;&#2478;&#2527;
			&#2488;&#2472;&#2494;&#2453;&#2509;&#2468;&#2453;&#2480;&#2467;&#2503;&#2480;
			&#2460;&#2472;&#2509;&#2479;,
			&#2478;&#2497;&#2453;&#2509;&#2468;&#2495;&#2479;&#2497;&#2470;&#2509;&#2471;
			&#2476;&#2495;&#2487;&#2527;&#2453;
			&#2478;&#2472;&#2509;&#2468;&#2509;&#2480;&#2467;&#2494;&#2482;&#2527;
			&#2489;&#2468;&#2503;
			&#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468;
			&#2488;&#2472;&#2470;&#2474;&#2468;&#2509;&#2480;
			&#2470;&#2494;&#2454;&#2495;&#2482; &#2453;&#2480;&#2468;&#2503;
			&#2489;&#2476;&#2503;&#2404;
		</div>
	</div>
	<br />
	<br />

</div>
<div id="popUpDiv2" style="display:none">

	<!-- <a href="#" onclick="popup('popUpDiv2');scrollToBottom();" >Close</a> -->
	<br />
	<br />
	<div
		style="margin-top: 0px;margin-bottom: 0px;;margin-left: 5px;margin-right: 5px;font-size: 20px;font-weight: bold;color: blue">
		&#2474;&#2509;&#2480;&#2476;&#2494;&#2488;&#2496;
		&#2453;&#2507;&#2463;&#2494; (Expatriate Quota)
		<hr>
		<div
			style="text-align: left;font-size: 20px;font-weight: normal;color: black">
			&#2474;&#2509;&#2480;&#2476;&#2494;&#2488;&#2496;
			(&#2476;&#2495;&#2470;&#2503;&#2486;&#2503;
			&#2453;&#2480;&#2509;&#2478;&#2480;&#2468;)
			&#2476;&#2494;&#2434;&#2482;&#2494;&#2470;&#2503;&#2486;&#2496;&#2470;&#2503;&#2480;
			&#2488;&#2472;&#2509;&#2468;&#2494;&#2472;&#2470;&#2503;&#2480;
			&#2460;&#2472;&#2509;&#2479; &#2447;&#2439;
			&#2453;&#2507;&#2463;&#2494;
			&#2474;&#2509;&#2480;&#2479;&#2507;&#2460;&#2509;&#2479;
			&#2489;&#2476;&#2503;&#2404; <br />
			<br /> &#2453;&#2507;&#2472;
			&#2486;&#2495;&#2453;&#2509;&#2487;&#2494;&#2480;&#2509;&#2469;&#2496;,
			&#2453;&#2507;&#2472;
			&#2453;&#2482;&#2503;&#2460;/&#2488;&#2478;&#2478;&#2494;&#2472;&#2503;&#2480;
			&#2486;&#2495;&#2453;&#2509;&#2487;&#2494;
			&#2474;&#2509;&#2480;&#2468;&#2495;&#2487;&#2509;&#2464;&#2494;&#2472;&#2503;,
			&#2474;&#2509;&#2480;&#2476;&#2494;&#2488;&#2496;
			&#2453;&#2507;&#2463;&#2494;&#2527;
			&#2477;&#2480;&#2509;&#2468;&#2495;&#2480;
			&#2460;&#2472;&#2509;&#2479;
			&#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2495;&#2468;
			&#2489;&#2482;&#2503;, &#2477;&#2480;&#2509;&#2468;&#2495;&#2480;
			&#2488;&#2478;&#2527;
			&#2476;&#2504;&#2470;&#2503;&#2486;&#2495;&#2453;
			&#2453;&#2480;&#2509;&#2478;&#2488;&#2434;&#2488;&#2509;&#2469;&#2494;&#2472;
			&#2451; &#2474;&#2509;&#2480;&#2476;&#2494;&#2488;&#2496;
			&#2453;&#2482;&#2509;&#2479;&#2494;&#2467;
			&#2478;&#2472;&#2509;&#2468;&#2509;&#2480;&#2467;&#2494;&#2482;&#2527;
			&#2469;&#2503;&#2453;&#2503;
			&#2476;&#2495;&#2470;&#2503;&#2486;&#2503;
			&#2453;&#2480;&#2509;&#2478;&#2480;&#2468;
			(&#2474;&#2495;&#2468;&#2494;/&#2478;&#2494;&#2468;&#2494;)
			&#2488;&#2434;&#2453;&#2509;&#2480;&#2494;&#2472;&#2509;&#2468;
			&#2474;&#2509;&#2480;&#2468;&#2509;&#2479;&#2527;&#2472;
			&#2474;&#2468;&#2509;&#2480; &#2470;&#2494;&#2454;&#2495;&#2482;
			&#2453;&#2480;&#2468;&#2503; &#2489;&#2476;&#2503;&#2404;
		</div>
	</div>
	<br />
	<br />

</div>

<div id="popUpDiv3" style="display:none">

	<br />
	<br />
	<div
		style="margin-top: 0px;margin-bottom: 0px;;margin-left: 5px;margin-right: 5px;font-size: 20px;font-weight: bold;color: blue">
		&#2486;&#2495;&#2453;&#2509;&#2487;&#2494;
		&#2453;&#2507;&#2463;&#2494; (Education Quota)
		<hr>
		<div
			style="text-align: left;font-size: 20px;font-weight: normal;color: black">
			&#2486;&#2495;&#2453;&#2509;&#2487;&#2494;
			&#2478;&#2472;&#2509;&#2468;&#2509;&#2480;&#2467;&#2494;&#2482;&#2527;&#2503;&#2480;
			&#2437;&#2471;&#2435;&#2488;&#2509;&#2468;&#2472;
			&#2470;&#2474;&#2509;&#2468;&#2480;&#2488;&#2478;&#2498;&#2489;
			&#2447;&#2476;&#2434; &#2441;&#2458;&#2509;&#2458;
			&#2478;&#2494;&#2471;&#2509;&#2479;&#2478;&#2495;&#2453;
			&#2486;&#2495;&#2453;&#2509;&#2487;&#2494;
			&#2474;&#2509;&#2480;&#2468;&#2495;&#2487;&#2509;&#2464;&#2494;&#2472;&#2503;&#2480;
			&#2486;&#2495;&#2453;&#2509;&#2487;&#2453;/&#2453;&#2480;&#2509;&#2478;&#2453;&#2480;&#2509;&#2468;&#2494;/&#2453;&#2480;&#2509;&#2478;&#2458;&#2494;&#2480;&#2495;
			&#2447;&#2476;&#2434;
			&#2474;&#2509;&#2480;&#2468;&#2495;&#2487;&#2509;&#2464;&#2494;&#2472;&#2503;&#2480;
			&#2455;&#2477;&#2480;&#2509;&#2472;&#2495;&#2434;
			&#2476;&#2465;&#2495;&#2480;
			&#2488;&#2470;&#2488;&#2509;&#2479;&#2470;&#2503;&#2480;
			&#2488;&#2472;&#2509;&#2468;&#2494;&#2472;&#2470;&#2503;&#2480;
			&#2460;&#2472;&#2509;&#2479; &#2447;&#2439;
			&#2453;&#2507;&#2463;&#2494;
			&#2474;&#2509;&#2480;&#2479;&#2507;&#2460;&#2509;&#2479;
			&#2489;&#2476;&#2503;&#2404; &#2453;&#2507;&#2472;
			&#2486;&#2495;&#2453;&#2509;&#2487;&#2494;
			&#2474;&#2509;&#2480;&#2468;&#2495;&#2487;&#2509;&#2464;&#2494;&#2472;&#2503;
			&#2488;&#2509;&#2453;&#2497;&#2482; &#2447;&#2476;&#2434;
			&#2453;&#2482;&#2503;&#2460; &#2447;&#2453;&#2439;
			&#2488;&#2494;&#2469;&#2503; &#2469;&#2494;&#2453;&#2482;&#2503;
			(&#2488;&#2509;&#2453;&#2497;&#2482; &#2447;&#2472;&#2509;&#2465;
			&#2453;&#2482;&#2503;&#2460;), &#2447;
			&#2453;&#2509;&#2487;&#2503;&#2468;&#2509;&#2480;&#2503;
			&#2453;&#2503;&#2476;&#2482; &#2478;&#2494;&#2468;&#2509;&#2480;
			&#2453;&#2482;&#2503;&#2460;
			&#2474;&#2480;&#2509;&#2479;&#2494;&#2527;&#2503;
			&#2472;&#2495;&#2527;&#2507;&#2460;&#2495;&#2468;
			&#2486;&#2495;&#2453;&#2509;&#2487;&#2453;/&#2453;&#2480;&#2509;&#2478;&#2453;&#2480;&#2509;&#2468;&#2494;/&#2453;&#2480;&#2509;&#2478;&#2458;&#2494;&#2480;&#2495;&#2470;&#2503;&#2480;
			&#2488;&#2472;&#2509;&#2468;&#2494;&#2472;&#2470;&#2503;&#2480;
			&#2460;&#2472;&#2509;&#2479; &#2447;&#2439;
			&#2453;&#2507;&#2463;&#2494;
			&#2474;&#2509;&#2480;&#2479;&#2507;&#2460;&#2509;&#2479;
			&#2489;&#2476;&#2503;&#2404; <br>
			<br> &#2447;&#2439; &#2453;&#2507;&#2463;&#2494;&#2480;
			&#2437;&#2471;&#2495;&#2453;&#2494;&#2480;&#2496;
			&#2453;&#2507;&#2472;
			&#2486;&#2495;&#2453;&#2509;&#2487;&#2494;&#2480;&#2509;&#2469;&#2496;,
			&#2479;&#2503; &#2453;&#2507;&#2472;
			&#2453;&#2482;&#2503;&#2460;/&#2488;&#2478;&#2478;&#2494;&#2472;&#2503;&#2480;
			&#2486;&#2495;&#2453;&#2509;&#2487;&#2494;
			&#2474;&#2509;&#2480;&#2468;&#2495;&#2487;&#2509;&#2464;&#2494;&#2472;&#2503;,
			&#2453;&#2507;&#2463;&#2494;&#2480;
			&#2474;&#2480;&#2509;&#2479;&#2494;&#2527;&#2477;&#2497;&#2453;&#2509;&#2468;
			&#2489;&#2476;&#2494;&#2480; &#2438;&#2476;&#2503;&#2470;&#2472;
			&#2453;&#2480;&#2468;&#2503;
			&#2474;&#2494;&#2480;&#2476;&#2503;&#2404; <br>
			<br> &#2453;&#2507;&#2472;
			&#2486;&#2495;&#2453;&#2509;&#2487;&#2494;&#2480;&#2509;&#2469;&#2496;,
			&#2453;&#2507;&#2472;
			&#2453;&#2482;&#2503;&#2460;/&#2488;&#2478;&#2478;&#2494;&#2472;&#2503;&#2480;
			&#2486;&#2495;&#2453;&#2509;&#2487;&#2494;
			&#2474;&#2509;&#2480;&#2468;&#2495;&#2487;&#2509;&#2464;&#2494;&#2472;&#2503;,
			&#2486;&#2495;&#2453;&#2509;&#2487;&#2494;
			&#2453;&#2507;&#2463;&#2494;&#2527;
			&#2477;&#2480;&#2509;&#2468;&#2495;&#2480;
			&#2460;&#2472;&#2509;&#2479;
			&#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2495;&#2468;
			&#2489;&#2482;&#2503;, &#2477;&#2480;&#2509;&#2468;&#2495;&#2480;
			&#2488;&#2478;&#2527;
			&#2441;&#2474;&#2479;&#2497;&#2453;&#2509;&#2468;
			&#2474;&#2509;&#2480;&#2478;&#2494;&#2467;&#2474;&#2468;&#2509;&#2480;
			&#2470;&#2494;&#2454;&#2495;&#2482; &#2453;&#2480;&#2468;&#2503;
			&#2489;&#2476;&#2503;&#2404;
		</div>
	</div>
	<br />
	<br />


</div>