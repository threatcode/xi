<%@ taglib prefix="s" uri="/struts-tags"%>
<style>
.box_header {
	height: 25px;
	background: #F7F7F7; /* Old browsers */
	background: -moz-linear-gradient(top, #F7F7F7 0%, #EDEDED 100%);
	/* FF3.6+ */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #F7F7F7),
		color-stop(100%, #EDEDED) ); /* Chrome,Safari4+ */
	background: -webkit-linear-gradient(top, #F7F7F7 0%, #EDEDED 100%);
	/* Chrome10+,Safari5.1+ */
	background: -o-linear-gradient(top, #F7F7F7 0%, #EDEDED 100%);
	/* Opera 11.10+ */
	background: -ms-linear-gradient(top, #F7F7F7 0%, #EDEDED 100%);
	/* IE10+ */
	background: linear-gradient(top, #F7F7F7 0%, #EDEDED 100%); /* W3C */
	color: #10257f;
	padding-left: 5px;
	text-align: left;
	font-weight: bold;
	font-size: 13px;
	font-style: normal;
	font-variant: normal;
	font-weight: bold;
	padding-top: 2px;
}

.box_body {
	background-color: #F5F5DC;
	padding: 0px;
}

.box_footer {
	height: 30px;
	padding-top: 2px;
	padding-right: 15px;
	background: #F7F7F7; /* Old browsers */
	background: -moz-linear-gradient(top, #E5E4E2 0%, #EDEDED 100%);
	/* FF3.6+ */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #E5E4E2),
		color-stop(100%, #EDEDED) ); /* Chrome,Safari4+ */
	background: -webkit-linear-gradient(top, #E5E4E2 0%, #EDEDED 100%);
	/* Chrome10+,Safari5.1+ */
	background: -o-linear-gradient(top, #E5E4E2 0%, #EDEDED 100%);
	/* Opera 11.10+ */
	background: -ms-linear-gradient(top, #E5E4E2 0%, #EDEDED 100%);
	/* IE10+ */
	background: linear-gradient(top, #E5E4E2 0%, #EDEDED 100%); /* W3C */
	text-align: right;
}

.box_container {
	width: 890px;
	border: 1px solid #D1D0CE;
}

.readonly {
	width: 170px;
	height: 20px;
	border: 1px solid grey;
	padding: 1px 2px;
	font-size: 14px;
}

.box_row {
	margin-top: 10px;
}

#personal_info_div .box_row {
	height: 28px;
}
.button {
	display: inline-block;
	padding: 15px 25px;
	font-size: 17px;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	outline: none;
	color: #fff;
	background-color: #4CAF50;
	border: none;
	border-radius: 15px;
	box-shadow: 0 9px #999;
}

.button:hover {
	background-color: #3e8e41
}

.button:active {
	background-color: #3e8e41;
	box-shadow: 0 5px #666;
	transform: translateY(4px);
}

.button4 {
	border-radius: 12px;
	background-color: #4CAF50;
	font-size: 17px;
	color: #fff;
}
</style>
<div id="loader_div"></div>
<div class="box_container" style="height:300px;margin-top: 8px;"
	id="personal_info_div">

	<div class="box_header">Personal Information</div>

	<div class="box_body" style="margin-top: 0px;margin-bottom: 0px;">
		<input type="hidden" name="applicant.application_id"
			id="application_id_old"
			value="<s:property value='applicant.application_id' />" /> 
			<input type="hidden" name="mobile_number_web"
			id="mobile_number_web"/>
		<div id="personal_info_div"
			style="padding-top: 5px;padding-left: 10px;padding-bottom: 0px; min-height: 242px;">
			<div class="box_row">
				<div style="float: left;width: 30%;">
					<label style="width: 80px;">Roll No.</label> <input type="text"
						name="applicant.ssc_info.roll" id="p_ssc_roll" class="readonly"
						disabled="disabled"
						value="<s:property value='applicant.ssc_info.roll' />" />
						
						<input type="hidden" id="p_ssc_roll_hidden" 
						value="<s:property value='applicant.ssc_info.roll' />" />

				</div>
				<div style="float: left;width: 33%;">
					<label style="width: 100px;">Board</label> <input type="text"
						name="applicant.ssc_info.board_name" id="p_board_name"
						class="readonly" disabled="disabled"
						value="<s:property value='applicant.ssc_info.board_name' />" /> 
						
						<input type="hidden" id="p_board_name_hidden"
						value="<s:property value='applicant.ssc_info.board_name' />" />
						
						<input
						type="hidden" name="applicant.ssc_info.board_id" id="p_board_id"
						value="<s:property value='applicant.ssc_info.board_id' />" /> 
						<input
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
						
						
						<input
						type="hidden" id="p_passing_year_hidden" 
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
					<label style="width: 100px;">Father</label> <input type="text"
						name="applicant.ssc_info.father_name" id="p_father_name"
						class="readonly" disabled="disabled"
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
						value="<s:property value='applicant.ssc_info.gpa_exc4th' />" /> <input
						type="hidden" name="applicant.ssc_info.eiin" id="p_eiin"
						class="readonly"
						value="<s:property value='applicant.ssc_info.eiin' />" />
				</div>
				<div style="float: left;width: 36%;">
					<label style="width: 120px;">NID Number</label> <input
						type="text" name="nid" id="nid"
						class="readonly" disabled="disabled"
						value="<s:property value='applicant.application_info.nid_number' />" />
				</div>
			</div>

			<div class="box_row"
				style="background-color: #151B54;color: white;text-align: left;font-weight: bold; padding-top: 0px;font-size: 16px; padding: 3px 12px 3px 12px;">
				A message will be sent to the following mobile number for
				<s:property value='applicant.ssc_info.student_name' />
			</div>
			<div class="box_row">

				<div style="float: left;width: 50%;">
					<label style="width: 180px;">New Mobile Number</label> <input
						type="text" name="new_mobile_number" id="new_mobile_number"
						class="readonly" disabled="disabled" />
				</div>
			</div>





		</div>

	</div>

	<div class="box_footer">
		<input type="button" onclick="deleteApplication()"
			name="deleteApplication" value="Delete Application"
			style="width:150px;padding: 1px 12px;" class="btn btn-danger" />
	</div>

</div>
<script type="text/javascript">


	function deleteApplication() {
        
		if (document.getElementById("p_ssc_roll_hidden").value == "") {
			alert("Please Give SSC ROLL");
			return;
		}

		if (document.getElementById("p_board_name_hidden").value == "") {
			alert("Please Select a Board");
			return;
		}
		if (document.getElementById("p_passing_year_hidden").value == "") {
			alert("Please Select a Year");
			return;
		}

		if (document.getElementById("p_ssc_reg").value == "") {
			alert("Please Give Registration Number");
			return;
		}

		if (document.getElementById("mobile_number_web").value == ""
				|| document.getElementById("mobile_number_web").value.length < 11) {
			alert("Please give your correct mobile number");
			return;
		}



		var x = confirm("Are you sure you want to delete application ?");
		if (x) {
			$.ajax({
				url : 'deleteApplication_TT',
				type : 'POST',
				data : {
					ssc_roll :  $("#p_ssc_roll_hidden").val().trim(),
					ssc_board : $("#p_board_id").val().trim(),
					boardName : $("#p_board_name_hidden").val().trim(),
					ssc_year :  $("#p_passing_year_hidden").val().trim(),
					ssc_reg :   $("#p_ssc_reg").val().trim(),
					mobilenumber : $("#mobile_number_web").val().trim(),
				},
				success : function(response) {

					alert(response.message);
					
					 $("#ssc_roll").val("");
					$("#ssc_board").val("none");
					$("#ssc_year").val("none");
					$("#ssc_reg").val("");
					$("#mobilenumber").val("");
					$("#retypemobilenumber").val("");
					
					$("#p_ssc_roll_hidden").val("");
					$("#p_board_id").val("");
					$("#p_board_name_hidden").val("");
					$("#p_passing_year_hidden").val("");
					$("#p_ssc_reg").val("");
					$("#mobile_number_web").val("");
					
					
					$("#loader").html('');
                    $("#showapplicant").html('');
					

				},
				error : function(e) {
					alert(response.message);

				}
			});

		}

	}

</script>