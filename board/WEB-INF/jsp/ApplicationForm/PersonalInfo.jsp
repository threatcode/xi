<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="box_container" style="height: 326px;margin-top: 8px;" id="personal_info_div">
<div class="box_header">
Personal Information
</div>
<div class="box_body" style="min-height: 215px;">

  <div id="personal_info_div" style="padding-top: 10px;padding-left: 10px;">
	  <div class="box_row">
		  <div style="float: left;width: 30%;">
		    <label style="width: 80px;">Roll No.</label>
			<input type="text" name="applicant.ssc_info.roll" id="p_ssc_roll" class="readonly" disabled="disabled" value="<s:property value='applicant.ssc_info.roll' />" />
			
		  </div>
		  <div style="float: left;width: 33%;">    
		    <label style="width: 100px;">Board</label>
			<input type="text" name="applicant.ssc_info.board_name" id="p_board_name" " class="readonly" disabled="disabled" value="<s:property value='applicant.ssc_info.board_name' />" />
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
			<input type="text" id="p_father_name" class="readonly" disabled="disabled" value="<s:property value='applicant.ssc_info.father_name' />"  />
		  </div>
		  <div style="float: left;width: 36%;">
		    <label style="width: 120px;">Mother's Name</label>
			<input type="text" id="p_mother_name" class="readonly" disabled="disabled" value="<s:property value='applicant.ssc_info.mother_name' />"  />
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
		    <label style="width: 120px;"></label>
		  </div>
	  </div>
	  
	  <div class="box_row" style="background-color: #151B54;color: white;text-align: left;font-weight: bold;padding-top: 2px;font-size: 15px;height: 22px;">
	  Please enter your mobile number carefully. You will receive all admission related information in this contact number.
	  </div>
	  <div class="box_row">
		  <div style="float: left;width: 30%;">
		    <label style="width: 80px;">Mobile No.</label>
			<input type="text"   class="readonly" maxlength="11" value="" name="applicant.application_info.mobile_number" id="mobile_number"/>
			
		  </div> 
		  <div style="float: left;width: 33%;">
		    <label style="width: 100px;">Re-type Mobile</label>
			<input type="text"   class="readonly" maxlength="11" value="" name="applicant.application_info.confirm_mobile_number" id="confirm_mobile_number"/>			
		  </div>		   
		  <div style="float: left;width: 36%;">    
		  </div>		  
	  </div>
	  <div class="box_row" style="background-color: #151B54;color: white;text-align: left;font-weight: bold;padding-top: 2px;font-size: 15px;height: 22px;">
	  Do you want to apply for Quota for College Admission ?
	  </div>
	  <div class="box_row">
	  <div style="float: left;width: 50%;">
	      
		    <label style="width: 160px;text-align: left;float:left;">Quota(Freedom Fighter)</label>
		    <div style="float:left;width:200px;">
				 <select id="quotaYN" name="applicant.application_info.quota_yn" style="width:70px;" onchange="showHideQuotaType(this.value)">
				  	<option value="N" selected="selected">No</option>
				  	<option value="Y">Yes</option>
				 </select>
			</div>
		  </div>
	  <div style="float: left;width: 40%;display: none;" id="quota_type_div">
		    <label style="width: 100px;float: left;padding-left: 7px;">Quota Type</label>
				<div style="float: left;font-size: 14px;color: green;" id="quota_type_selection">
					<input type="checkbox" name="applicant.application_info.quota_ff" id="quota_ff" value="Y"/> Freedom Fighter
				</div>
	  </div>
	</div>
				
  </div>
  
</div>

<div class="box_footer">

 <input type="button" value="Next" onclick="collegeSelection()" style="width:100px;" />
</div>
</div>