<%@ taglib prefix="s" uri="/struts-tags"%>


<style>
#popUpDiv1 {
	position: absolute;
	background: #F5F5DC;
	width: 400px;
	height: 400px;
	border: 5px solid #000;
	z-index: 9002;
}

#popUpDiv2 {
	position: absolute;
	background: #F5F5DC;
	width: 400px;
	height: 400px;
	border: 5px solid #000;
	z-index: 9002;
}

#popUpDiv3 {
	position: absolute;
	background: #F5F5DC;
	width: 400px;
	height: 400px;
	border: 5px solid #000;
	z-index: 9002;
	overflow: scroll;
}
</style>
<script type="text/javascript">
	var mpos = '<s:property value='applicant.mpos' />';
	var bpos = mpos.split(",")[0];
	var apos = mpos.split(",")[1];
</script>

<div class="box_container" style="margin-top: 2px;"
	id="personal_info_div">

	<div class="box_header">Personal Information</div>

	<div class="box_body" style="margin-top: 0px;margin-bottom: 0px;">
		<input type="hidden" name="applicant.application_id"
			id="application_id_old"
			value="<s:property value='applicant.application_id' />" />
		<div id="personal_info_div"
			style="padding-top: 5px;padding-left: 10px;padding-bottom: 0px; min-height: 409px;">
			<div class="box_row">
				<div style="float: left;width: 30%;">
					<label style="width: 80px;">Roll No.</label> <input type="text"
						name="applicant.ssc_info.roll" id="p_ssc_roll" class="readonly"
						disabled="disabled"
						value="<s:property value='applicant.ssc_info.roll' />" />

				</div>
				<div style="float: left;width: 33%;">
					<label style="width: 100px;">Board</label> <input type="text"
						name="applicant.ssc_info.board_name" id="p_board_name"
						class="readonly" disabled="disabled"
						value="<s:property value='applicant.ssc_info.board_name' />" /> <input
						type="hidden" name="applicant.ssc_info.board_id" id="p_board_id"
						value="<s:property value='applicant.ssc_info.board_id' />" /> <input
						type="hidden" name="applicant.ssc_info.group_id" id="p_group_id"
						value="<s:property value='applicant.ssc_info.group_id' />" /> <input
						type="hidden" name="applicant.ssc_info.reg_no" id="p_ssc_reg"
						value="<s:property value='applicant.ssc_info.reg_no' />" />
				</div>
				<div style="float: left;width: 36%;">
					<label style="width: 120px;">Passing Year</label> <input
						type="text" name="applicant.ssc_info.passing_year"
						id="p_passing_year" class="readonly" disabled="disabled"
						value="<s:property value='applicant.ssc_info.passing_year' />" />
				</div>
			</div>
			<div class="box_row">
				<div style="float: left;width: 30%;">
					<label style="width: 80px;">Name</label> <input type="text"
						name="applicant.ssc_info.student_name" id="p_student_name"
						class="readonly" disabled="disabled"
						value="<s:property value='applicant.ssc_info.student_name' />" />

				</div>
				<div style="float: left;width: 33%;">
					<label style="width: 100px;">Father's Name</label> <input
						type="text" name="applicant.ssc_info.father_name"
						id="p_father_name" class="readonly" disabled="disabled"
						value="<s:property value='applicant.ssc_info.father_name' />" />
				</div>
				<div style="float: left;width: 36%;">
					<label style="width: 120px;">Mother's Name</label> <input
						type="text" name="applicant.ssc_info.mother_name"
						id="p_mother_name" class="readonly" disabled="disabled"
						value="<s:property value='applicant.ssc_info.mother_name' />" />
				</div>
			</div>
			<div class="box_row">
				<div style="float: left;width: 30%;">
					<label style="width: 80px;">Gender</label> <input type="text"
						name="applicant.ssc_info.gender_name" id="p_gender_name"
						class="readonly" disabled="disabled"
						value="<s:property value='applicant.ssc_info.gender_name' />" />
					<input type="hidden" name="applicant.ssc_info.gender_id"
						id="p_gender" class="readonly"
						value="<s:property value='applicant.ssc_info.gender_id' />" />
				</div>

				<div style="float: left;width: 33%;">
					<label style="width: 100px;">GPA</label> <input type="text"
						name="applicant.ssc_info.gpa" id="p_gpa" class="readonly"
						disabled="disabled"
						value="<s:property value='applicant.ssc_info.gpa' />" /> <input
						type="hidden" name="applicant.ssc_info.gpa_exc4th"
						id="p_gpa_exc4th" class="readonly"
						value="<s:property value='applicant.ssc_info.gpa_exc4th' />" />
					<input type="hidden" name="applicant.ssc_info.eiin" id="p_eiin"
						class="readonly"
						value="<s:property value='applicant.ssc_info.eiin' />" />
				</div>
				<div style="float: left;width: 36%;">
					<!-- 
					<label style="width: 120px;">Birth Date</label> <input type="text"
						name="applicant.ssc_info.birth_date" id="p_birth_date" class="readonly"
						value="<s:property value='applicant.ssc_info.birth_date' />" /> 
					 -->
				</div>
			</div>
			<div class="box_row merit_row">
				<div style="float: right;width: 30%;display: none" id="agediv">
					<label style="width: 200px;color: red;font-size: 28px;">Age <= 22 </label> 
				</div>
				<div style="float: left;width: 45%;">
					<label style="width: 120px;margin-top: 5px;">Merit Position </label> <input type="text"
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
			<div class="box_row sub_box_header"
				style="background-color: #151B54;color: white;text-align: left;font-weight: bold; font-size: 15px; height: 22px; padding-left: 10px;">
				&#2478;&#2507;&#2476;&#2494;&#2439;&#2482; &#2472;&#2494;&#2478;&#2509;&#2476;&#2494;&#2480; (&#2535;&#2535; &#2465;&#2495;&#2460;&#2495;&#2463;) &#2447;&#2476;&#2434; &#2437;&#2477;&#2495;&#2477;&#2494;&#2476;&#2453;&#2503;&#2480; &#2460;&#2494;&#2468;&#2496;&#2527; &#2474;&#2480;&#2495;&#2458;&#2527;&#2474;&#2468;&#2509;&#2480; &#2472;&#2494;&#2478;&#2509;&#2476;&#2494;&#2480; (Mobile Number (11 Digits) and the NID Number of your Guardian)
			</div>
			<div class="box_row sub_box_container">
				<div style="float: none;width: 100%;text-align: left;padding-bottom: 5px">
					&#2474;&#2509;&#2480;&#2479;&#2492;&#2507;&#2460;&#2472;&#2496;&#2479;&#2492; &#2437;&#2480;&#2509;&#2469; &#2474;&#2480;&#2495;&#2486;&#2507;&#2471; &#2453;&#2480;&#2494;&#2480; &#2488;&#2478;&#2527; &#2438;&#2474;&#2472;&#2495; &#2438;&#2474;&#2472;&#2494;&#2480; &#2479;&#2503; Contact &#2478;&#2507;&#2476;&#2494;&#2439;&#2482; &#2472;&#2478;&#2509;&#2476;&#2480; &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480;&#2503;&#2459;&#2503;&#2472; &#2488;&#2503;&#2463;&#2495; &#2488;&#2494;&#2476;&#2471;&#2494;&#2472;&#2503; &#2474;&#2498;&#2480;&#2467; &#2453;&#2480;&#2497;&#2472;&#2404; &#2447;&#2463;&#2495; &#2437;&#2468;&#2509;&#2479;&#2472;&#2509;&#2468; &#2455;&#2497;&#2480;&#2497;&#2468;&#2509;&#2476;&#2474;&#2498;&#2480;&#2509;&#2467;&#2404; &#2438;&#2474;&#2472;&#2494;&#2480; &#2477;&#2480;&#2509;&#2468;&#2495; &#2488;&#2478;&#2509;&#2474;&#2480;&#2509;&#2453;&#2495;&#2468; &#2488;&#2453;&#2482; &#2468;&#2469;&#2509;&#2479; &#2447;&#2439; &#2472;&#2478;&#2509;&#2476;&#2480;&#2503; &#2474;&#2494;&#2464;&#2494;&#2472;&#2507; &#2489;&#2476;&#2503;&#2404; &#2447;&#2439; &#2472;&#2478;&#2509;&#2476;&#2480;&#2503;&#2480; &#2476;&#2494;&#2479;&#2492;&#2507;&#2478;&#2503;&#2463;&#2509;&#2480;&#2495;&#2453; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2488;&#2478;&#2509;&#2474;&#2472;&#2509;&#2472; &#2489;&#2451;&#2527;&#2494; &#2437;&#2468;&#2509;&#2479;&#2494;&#2476;&#2486;&#2509;&#2479;&#2453;&#2404; &#2470;&#2527;&#2494; &#2453;&#2480;&#2503; &#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2477;&#2494;&#2476;&#2453;&#2503;&#2480; &#2460;&#2494;&#2468;&#2496;&#2527; &#2474;&#2480;&#2495;&#2458;&#2527;&#2474;&#2468;&#2509;&#2480; &#2472;&#2478;&#2509;&#2476;&#2480;&#2451; &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480;&#2497;&#2472; &#2447;&#2476;&#2434; &#2468;&#2494;&#2433;&#2480; (&#2479;&#2494;&#2433;&#2480; &#2460;&#2494;&#2468;&#2496;&#2527; &#2474;&#2480;&#2495;&#2458;&#2527;&#2474;&#2468;&#2509;&#2480; &#2472;&#2478;&#2509;&#2476;&#2480; &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480;&#2459;&#2503;&#2472;) &#2488;&#2494;&#2469;&#2503; &#2438;&#2474;&#2472;&#2494;&#2480; &#2488;&#2478;&#2509;&#2474;&#2480;&#2509;&#2453; &#2441;&#2482;&#2509;&#2482;&#2503;&#2454; &#2453;&#2480;&#2497;&#2472;&#2404; &#2477;&#2480;&#2509;&#2468;&#2495;&#2480; &#2488;&#2478;&#2527; &#2474;&#2498;&#2480;&#2467;&#2453;&#2499;&#2468; &#2460;&#2494;&#2468;&#2496;&#2527; &#2474;&#2480;&#2495;&#2458;&#2527;&#2474;&#2468;&#2509;&#2480; &#2472;&#2478;&#2509;&#2476;&#2480; &#2479;&#2494;&#2458;&#2494;&#2439; &#2453;&#2480;&#2494; &#2489;&#2468;&#2503; &#2474;&#2494;&#2480;&#2503; &#2447;&#2476;&#2434; &#2460;&#2494;&#2468;&#2496;&#2527; &#2474;&#2480;&#2495;&#2458;&#2527;&#2474;&#2468;&#2509;&#2480; &#2472;&#2478;&#2509;&#2476;&#2480; (&#2437;&#2477;&#2495;&#2477;&#2494;&#2476;&#2453;&#2503;&#2480;) &#2474;&#2498;&#2480;&#2467; &#2453;&#2480;&#2494; &#2469;&#2494;&#2453;&#2482;&#2503; &#2477;&#2480;&#2509;&#2468;&#2495; &#2474;&#2509;&#2480;&#2453;&#2509;&#2480;&#2495;&#2527;&#2494; &#2488;&#2489;&#2460;&#2468;&#2480; &#2489;&#2476;&#2503;&#2404;
					<br /> Please enter your mobile number carefully. You should provide the same contact mobile phone number which you have given earlier as your contact mobile number during application fee payment. This is very important. All your admission related information will be sent to this number. Biometric registration of this number is essential. Please enter the NID number of your guardian mentioning your relationship with him/her. During final admission process, the NID may be verified. Application with NID number (of guardian) is expected to have a smoother admission process.
					<br/><br/>
				</div>
				<div style="float: left;width: 50%;text-align: left;height: 30px;">
					<label style="width: 234px; color: #002b58; ">Mobile No.</label> <input type="text" onkeydown="return numericOnly(event)"
						class="readonly" maxlength="11" value=""
						name="applicant.application_info.mobile_number" id="mobile_number" />
				</div>
				<div style="float: left;width: 50%;text-align: left;height: 30px;">
					<label style="width: 120px; color: #002b58;">Re-type Mobile No.</label> <input  onkeydown="return numericOnly(event)"
						type="text" class="readonly" maxlength="11" value=""
						name="applicant.application_info.confirm_mobile_number"
						id="confirm_mobile_number" />
				</div>
				<div style="float: left;width: 50%;text-align: left;padding-top: 5px;">
					
					<!-- 
					<label style="width: 120px; color: #002b58;">Re-type NID No.</label> <input  onkeydown="return numericOnly(event)"
						type="text" class="readonly" maxlength="17" value=""
						name="applicant.application_info.confirm_nid_number"
						id="confirm_nid_number" />
					 -->
				</div>
			</div>
			<div class="box_row sub_box_container">
				<div style="float: left;width: 27%;text-align: left;height: 30px;">
					<label style="width: 250px; color: #002b58; ">NID No. of your Guardian <font color="red">(Optional)</font> </font> </label> 
				</div>
				<div style="float: left;width: 73%;text-align: left;">
					<input type="text"  onkeydown="return numericOnly(event)"
						class="readonly" maxlength="17" value=""
						name="applicant.application_info.nid_number" id="nid_number" />
				</div>
			</div>
			<div class="box_row sub_box_container">
				<div style="float: left;width: 27%;text-align: left;height: 30px;">
					<label style="width: 250px; color: #002b58; ">Relationship with your Guardian</label> 
				</div>
				<div style="float: left;width: 73%;text-align: left;">
					<select name="applicant.application_info.nidholder" id="nidholder" >
						<option value="Father">Father</option>
						<option value="Mother">Mother</option>
					</select>
				</div>
			</div>
			<div
				style="float: none; position: relative; width: 100%; clear: both; height: 15px;" id="mobileinfo">
			</div>
			<div class="box_row sub_box_header"
				style="background-color: #151B54;color: white;text-align: left;font-weight: bold; font-size: 15px;height: 22px;padding-left: 10px; ">
				<!-- Do you want to apply Quota for College Admission ? -->
				&#2453;&#2507;&#2463;&#2494;
				&#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; <font
					color="yellow">(&#2479;&#2470;&#2495;
					&#2438;&#2474;&#2472;&#2494;&#2480; &#2460;&#2472;&#2509;&#2479;
					&#2453;&#2507;&#2463;&#2494;
					&#2474;&#2509;&#2480;&#2479;&#2507;&#2460;&#2509;&#2479;
					&#2489;&#2479;&#2492; )</font> Quota Selection <font color="yellow">(If
					quota is applicable for you)</font>
			</div>
			<div class="box_row sub_box_container">
				<div
					style="float: none;width: 100%;text-align: left;padding-bottom: 5px">
					&#2470;&#2479;&#2492;&#2494; &#2453;&#2480;&#2503; &#2438;&#2474;&#2472;&#2494;&#2480; &#2460;&#2472;&#2509;&#2479; &#2474;&#2509;&#2480;&#2479;&#2507;&#2460;&#2509;&#2479; &#2453;&#2507;&#2463;&#2494; &#2454;&#2497;&#2476; &#2488;&#2494;&#2476;&#2471;&#2494;&#2472;&#2503; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; &#2453;&#2480;&#2497;&#2472;&#2404; <br /> Please
					select the quota applicable for you very carefully.</div>
				<div style="float: left;width: 50%;text-align: left">
					<table>
						<tr>
							<td style="border: 0px"><a href="#" id="FreedomFighterQuota">
									&#2478;&#2497;&#2453;&#2509;&#2468;&#2495;&#2479;&#2507;&#2470;&#2509;&#2471;&#2494;
									&#2453;&#2507;&#2463;&#2494; (Freedom Fighter Quota) </a></td>
							<td style="border: 0px"><input type="checkbox"
								name="applicant.application_info.quota_ff" value="Y"
								id="FreedomFighterQuotaCheckBox"></td>
						</tr>
						<!-- 
						<tr>
							<td style="border: 0px"><a href="#" id="ExpatriateQuota">
									&#2474;&#2509;&#2480;&#2476;&#2494;&#2488;&#2496;
									&#2453;&#2507;&#2463;&#2494; (Expatriate Quota) </a></td>
							<td style="border: 0px"><input type="checkbox"
								name="applicant.application_info.quota_expatriate" value="Y"
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
								id="EducationQuotaCheckBox"></td>
						</tr>
						<tr>
							<td style="border: 0px"></td>
							<td style="border: 0px"></td>
						</tr>
					</table>
				</div>

			</div>

		</div>

	</div>

	<div class="box_footer" style="margin-top: 0px">
		<input type="button" value="Next" onclick="collegeSelection()" id="pnext" style="width:100px;" />
	</div>

</div>

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
			&#2486;&#2495;&#2453;&#2509;&#2487;&#2494; &#2478;&#2472;&#2509;&#2468;&#2509;&#2480;&#2467;&#2494;&#2482;&#2527;&#2503;&#2480; &#2437;&#2471;&#2435;&#2488;&#2509;&#2468;&#2472; &#2470;&#2474;&#2509;&#2468;&#2480;&#2488;&#2478;&#2498;&#2489; &#2447;&#2476;&#2434; &#2441;&#2458;&#2509;&#2458; &#2478;&#2494;&#2471;&#2509;&#2479;&#2478;&#2495;&#2453; &#2486;&#2495;&#2453;&#2509;&#2487;&#2494; &#2474;&#2509;&#2480;&#2468;&#2495;&#2487;&#2509;&#2464;&#2494;&#2472;&#2503;&#2480; &#2486;&#2495;&#2453;&#2509;&#2487;&#2453;/&#2453;&#2480;&#2509;&#2478;&#2453;&#2480;&#2509;&#2468;&#2494;/&#2453;&#2480;&#2509;&#2478;&#2458;&#2494;&#2480;&#2496;&#2470;&#2503;&#2480; &#2488;&#2472;&#2509;&#2468;&#2494;&#2472;&#2470;&#2503;&#2480; &#2460;&#2472;&#2509;&#2479; &#2447;&#2439; &#2453;&#2507;&#2463;&#2494; &#2474;&#2509;&#2480;&#2479;&#2507;&#2460;&#2509;&#2479; &#2489;&#2476;&#2503;&#2404; &#2453;&#2507;&#2472; &#2486;&#2495;&#2453;&#2509;&#2487;&#2494; &#2474;&#2509;&#2480;&#2468;&#2495;&#2487;&#2509;&#2464;&#2494;&#2472;&#2503; &#2488;&#2509;&#2453;&#2497;&#2482; &#2447;&#2476;&#2434; &#2453;&#2482;&#2503;&#2460; &#2447;&#2453;&#2439; &#2488;&#2494;&#2469;&#2503; &#2469;&#2494;&#2453;&#2482;&#2503; (&#2488;&#2509;&#2453;&#2497;&#2482; &#2447;&#2472;&#2509;&#2465; &#2453;&#2482;&#2503;&#2460;), &#2447; &#2453;&#2509;&#2487;&#2503;&#2468;&#2509;&#2480;&#2503; &#2453;&#2503;&#2476;&#2482; &#2478;&#2494;&#2468;&#2509;&#2480; &#2453;&#2482;&#2503;&#2460; &#2474;&#2480;&#2509;&#2479;&#2494;&#2527;&#2503; &#2472;&#2495;&#2527;&#2507;&#2460;&#2495;&#2468; &#2486;&#2495;&#2453;&#2509;&#2487;&#2453;/&#2453;&#2480;&#2509;&#2478;&#2453;&#2480;&#2509;&#2468;&#2494;/&#2453;&#2480;&#2509;&#2478;&#2458;&#2494;&#2480;&#2495;&#2470;&#2503;&#2480; &#2488;&#2472;&#2509;&#2468;&#2494;&#2472;&#2470;&#2503;&#2480; &#2460;&#2472;&#2509;&#2479; &#2447;&#2439; &#2453;&#2507;&#2463;&#2494; &#2474;&#2509;&#2480;&#2479;&#2507;&#2460;&#2509;&#2479; &#2489;&#2476;&#2503;&#2404; 
<br>
&#2447;&#2439; &#2453;&#2507;&#2463;&#2494;&#2480; &#2437;&#2471;&#2495;&#2453;&#2494;&#2480;&#2496; &#2453;&#2507;&#2472; &#2486;&#2495;&#2453;&#2509;&#2487;&#2494;&#2480;&#2509;&#2469;&#2496;, &#2479;&#2503; &#2453;&#2507;&#2472; &#2453;&#2482;&#2503;&#2460;/&#2488;&#2478;&#2478;&#2494;&#2472;&#2503;&#2480; &#2486;&#2495;&#2453;&#2509;&#2487;&#2494; &#2474;&#2509;&#2480;&#2468;&#2495;&#2487;&#2509;&#2464;&#2494;&#2472;&#2503;, &#2453;&#2507;&#2463;&#2494;&#2480; &#2474;&#2480;&#2509;&#2479;&#2494;&#2527;&#2477;&#2497;&#2453;&#2509;&#2468; &#2489;&#2476;&#2494;&#2480; &#2438;&#2476;&#2503;&#2470;&#2472; &#2453;&#2480;&#2468;&#2503; &#2474;&#2494;&#2480;&#2476;&#2503;&#2404; &#2453;&#2507;&#2472; &#2486;&#2495;&#2453;&#2509;&#2487;&#2494;&#2480;&#2509;&#2469;&#2496;, &#2453;&#2507;&#2472; &#2453;&#2482;&#2503;&#2460;/&#2488;&#2478;&#2478;&#2494;&#2472;&#2503;&#2480; &#2486;&#2495;&#2453;&#2509;&#2487;&#2494; &#2474;&#2509;&#2480;&#2468;&#2495;&#2487;&#2509;&#2464;&#2494;&#2472;&#2503;, &#2486;&#2495;&#2453;&#2509;&#2487;&#2494; &#2453;&#2507;&#2463;&#2494;&#2527; &#2477;&#2480;&#2509;&#2468;&#2495;&#2480; &#2460;&#2472;&#2509;&#2479; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2495;&#2468; &#2489;&#2482;&#2503;, &#2477;&#2480;&#2509;&#2468;&#2495;&#2480; &#2488;&#2478;&#2527; &#2441;&#2474;&#2479;&#2497;&#2453;&#2509;&#2468; &#2474;&#2509;&#2480;&#2478;&#2494;&#2467;&#2474;&#2468;&#2509;&#2480; &#2470;&#2494;&#2454;&#2495;&#2482; &#2453;&#2480;&#2468;&#2503; &#2489;&#2476;&#2503;&#2404;
<br>
<font color="red"><b>
&#2453;&#2507;&#2472; &#2474;&#2509;&#2480;&#2468;&#2495;&#2487;&#2509;&#2464;&#2494;&#2472;&#2503;&#2480; &#2455;&#2477;&#2480;&#2509;&#2472;&#2495;&#2434; &#2476;&#2465;&#2495;&#2480; &#2488;&#2470;&#2488;&#2509;&#2479;&#2470;&#2503;&#2480; &#2488;&#2472;&#2509;&#2468;&#2494;&#2472;&#2470;&#2503;&#2480; &#2460;&#2472;&#2509;&#2479; &#2453;&#2503;&#2476;&#2482;&#2478;&#2494;&#2468;&#2509;&#2480; &#2488;&#2434;&#2486;&#2509;&#2482;&#2495;&#2487;&#2509;&#2463; &#2474;&#2509;&#2480;&#2468;&#2495;&#2487;&#2509;&#2464;&#2494;&#2472;&#2503; &#2455;&#2477;&#2480;&#2509;&#2472;&#2495;&#2434; &#2476;&#2465;&#2495; &#2453;&#2507;&#2463;&#2494; &#2474;&#2509;&#2480;&#2479;&#2507;&#2460;&#2509;&#2479; &#2489;&#2476;&#2503;&#2404; &#2447; &#2453;&#2509;&#2487;&#2503;&#2468;&#2509;&#2480;&#2503; &#2438;&#2476;&#2503;&#2470;&#2472;&#2453;&#2494;&#2480;&#2496; &#2486;&#2495;&#2453;&#2509;&#2487;&#2494; &#2453;&#2507;&#2463;&#2494;&#2527; (Education Quota) &#2463;&#2495;&#2453; &#2472;&#2494; &#2470;&#2495;&#2527;&#2503; &#2453;&#2482;&#2503;&#2460;/&#2488;&#2478;&#2478;&#2494;&#2472;&#2503;&#2480; &#2474;&#2509;&#2480;&#2468;&#2495;&#2487;&#2509;&#2464;&#2494;&#2472; &#2474;&#2459;&#2472;&#2509;&#2470;&#2503;&#2480;  &#2488;&#2478;&#2527; Governing Body Quota-&#2527; Yes &#2488;&#2495;&#2482;&#2503;&#2453;&#2509;&#2463; &#2453;&#2480;&#2476;&#2503;&#2472;&#2404; 
</b></font>
		</div>
	</div>
	<br />
	<br />


</div>
<div id="popUpDiv4" style="display:none">

	<br />
	<br />
	<div
		style="margin-top: 0px;margin-bottom: 0px;;margin-left: 5px;margin-right: 5px;font-size: 20px;font-weight: bold;color: blue">
		&#2455;&#2477;&#2480;&#2472;&#2495;&#2434; &#2476;&#2465;&#2495; &#2453;&#2507;&#2463;&#2494; (Governing Body)
		<hr>
		<div
			style="text-align: left;font-size: 20px;font-weight: normal;color: black">
			&#2453;&#2507;&#2472; &#2474;&#2509;&#2480;&#2468;&#2495;&#2487;&#2509;&#2464;&#2494;&#2472;&#2503;&#2480; &#2455;&#2477;&#2480;&#2509;&#2472;&#2495;&#2434; &#2476;&#2465;&#2495;&#2480; &#2488;&#2470;&#2488;&#2509;&#2479;&#2470;&#2503;&#2480; &#2488;&#2472;&#2509;&#2468;&#2494;&#2472;&#2470;&#2503;&#2480; &#2460;&#2472;&#2509;&#2479; &#2453;&#2503;&#2476;&#2482;&#2478;&#2494;&#2468;&#2509;&#2480; &#2488;&#2434;&#2486;&#2509;&#2482;&#2495;&#2487;&#2509;&#2463; &#2474;&#2509;&#2480;&#2468;&#2495;&#2487;&#2509;&#2464;&#2494;&#2472;&#2503; &#2455;&#2477;&#2480;&#2509;&#2472;&#2495;&#2434; &#2476;&#2465;&#2495; &#2453;&#2507;&#2463;&#2494; &#2474;&#2509;&#2480;&#2479;&#2507;&#2460;&#2509;&#2479; &#2489;&#2476;&#2503;&#2404; &#2447; &#2453;&#2509;&#2487;&#2503;&#2468;&#2509;&#2480;&#2503; &#2438;&#2476;&#2503;&#2470;&#2472;&#2453;&#2494;&#2480;&#2496; &#2486;&#2495;&#2453;&#2509;&#2487;&#2494; &#2453;&#2507;&#2463;&#2494;&#2527; (Education Quota) &#2463;&#2495;&#2453; &#2472;&#2494; &#2470;&#2495;&#2527;&#2503; &#2453;&#2482;&#2503;&#2460;/&#2488;&#2478;&#2478;&#2494;&#2472;&#2503;&#2480; &#2474;&#2509;&#2480;&#2468;&#2495;&#2487;&#2509;&#2464;&#2494;&#2472; &#2474;&#2459;&#2472;&#2509;&#2470;&#2503;&#2480;  &#2488;&#2478;&#2527; Governing Body Quota-&#2527; Yes &#2488;&#2495;&#2482;&#2503;&#2453;&#2509;&#2463; &#2453;&#2480;&#2476;&#2503;&#2472;&#2404; 
			
		</div>
	</div>
	<br />
	<br />


</div>


