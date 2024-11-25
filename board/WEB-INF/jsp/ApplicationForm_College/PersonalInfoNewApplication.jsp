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
<div class="box_container" style="margin-top: 2px;"
	id="personal_info_div">

	<div class="box_header">Personal Information</div>

	<div class="box_body" style="margin-top: 0px;margin-bottom: 0px;">
		<input type="hidden" name="applicant.application_id"
			id="application_id_old"
			value="<s:property value='applicant.application_id' />" />
		<div id="personal_info_div"
			style="padding-top: 5px;padding-left: 10px;padding-bottom: 0px; min-height:250px;">
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
					<label style="width: 120px;"></label>
				</div>
			</div>

			<div class="box_row sub_box_header"
				style="background-color: #151B54;color: white;text-align: left;font-weight: bold; font-size: 15px; height: 22px; padding-left: 10px;">
				<!-- Please enter your mobile number carefully. You will receive all admission related information in this contact number.-->
				&#2478;&#2507;&#2476;&#2494;&#2439;&#2482;
				&#2472;&#2494;&#2478;&#2509;&#2476;&#2494;&#2480; (&#2535;&#2535;
				&#2465;&#2495;&#2460;&#2495;&#2463;) Mobile Number (11 Digits)
			</div>
			<div class="box_row sub_box_container">
				<div
					style="float: none;width: 100%;text-align: left;padding-bottom: 5px">
					&#x9B6;&#x9BF;&#x995;&#x9CD;&#x9B7;&#x9BE;&#x9B0;&#x9CD;&#x9A5;&#x9C0;&#x9B0;&#x20;
					&#2478;&#2507;&#2476;&#2494;&#2439;&#2482;
					&#2472;&#2478;&#2509;&#2476;&#2480;
					&#2488;&#2494;&#2476;&#2471;&#2494;&#2472;&#2503;
					&#2474;&#2498;&#2480;&#2467; &#x995;&#x9B0;&#x9C1;&#x9A8;&#x20;&#2404;
					&#2447;&#2463;&#2495;
					&#2437;&#2468;&#2509;&#2479;&#2472;&#2509;&#2468;
					&#2455;&#2497;&#2480;&#2497;&#2468;&#2509;&#2476;&#2474;&#2498;&#2480;&#2509;&#2467;&#2404;
					&#x9B6;&#x9BF;&#x995;&#x9CD;&#x9B7;&#x9BE;&#x9B0;&#x9CD;&#x9A5;&#x9C0;&#x9B0;&#x20; &#2488;&#2453;&#2482;
					&#2477;&#2480;&#2509;&#2468;&#2495;
					&#2488;&#2478;&#2509;&#2474;&#2480;&#2509;&#2453;&#2495;&#2468;
					&#2468;&#2469;&#2509;&#2479; &#2447;&#2439;
					&#2472;&#2478;&#2509;&#2476;&#2480;&#2503;
					&#2474;&#2494;&#2464;&#2494;&#2472;&#2507;
					&#2489;&#2476;&#2503;&#2404; &#2447;&#2439;
					&#2472;&#2478;&#2509;&#2476;&#2480;&#2503;&#2480;
					&#2476;&#2494;&#2479;&#2492;&#2507;&#2478;&#2503;&#2463;&#2509;&#2480;&#2495;&#2453;
					&#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472;
					&#2488;&#2478;&#2509;&#2474;&#2472;&#2509;&#2472;
					&#2489;&#2451;&#2527;&#2494;
					&#2437;&#2468;&#2509;&#2479;&#2494;&#2476;&#2486;&#2509;&#2479;&#2453;&#2404;
					<br /> Please enter the mobile number of the student carefully. This is very
					important. All  admission related information of the student will be sent to
					this number. Biometric registration of this number is essential.
				</div>
				<div style="float: left;width: 50%;text-align: left">
					<label style="width: 120px;">Mobile No.</label> <input type="text"
						class="readonly" maxlength="11" value=""
						name="applicant.application_info.mobile_number" id="mobile_number" />
				</div>
				<div style="float: left;width: 50%;text-align: left">
					<label style="width: 120px;">Re-type Mobile No.</label> <input
						type="text" class="readonly" maxlength="11" value=""
						name="applicant.application_info.confirm_mobile_number"
						id="confirm_mobile_number" />
				</div>
			</div>
			<div
				style="float: none; position: relative; width: 100%; clear: both; height: 15px;">
			</div>



		</div>

	</div>

	<div class="box_footer" style="margin-top: 0px">
		<input type="button" value="Next" onclick="collegeSelection()"
			style="width:100px;" />
	</div>

</div>



