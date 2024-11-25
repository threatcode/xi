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
			style="padding-top: 5px;padding-left: 10px;padding-bottom: 0px; min-height:150px;">
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



			<div
				style="float: none; position: relative; width: 100%; clear: both; height: 15px;">
			</div>



		</div>

	</div>

	<div class="box_footer" style="margin-top: 0px">
		<input type="button" value="Next" onclick="collegeSelection_notAdmitted()"
			style="width:100px;" />
	</div>

</div>






