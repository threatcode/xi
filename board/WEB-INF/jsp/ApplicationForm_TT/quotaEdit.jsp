<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="loader_div">
</div>
<div class="box_container" style="height:291px;margin-top: 8px;" id="personal_info_div">

<div class="box_header">
Personal Information
</div>

<div class="box_body" style="min-height:140px;">
<input type="hidden" name="applicant.application_id" id="application_id_old"  value="<s:property value='applicant.application_id' />" />
<input type="hidden"  name="applicant.application_info.mobile_number" id="mobile_number_web"   value="<s:property value='applicant.application_id' />" />	
  <div id="personal_info_div" style="padding-top: 10px;padding-left: 10px;">
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
		  </div>
	  </div>
  
	  <div class="box_row" style="background-color: #151B54;color: white;text-align: left;font-weight: bold;padding-top: 2px;font-size: 15px;height: 22px;">
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
	   
</div>
				
  </div>
  
</div>

<div class="box_footer">
 <input type="button" value="Edit Quota" onclick="editQuota()" style="width:100px;" />
</div>

</div>
<script type="text/javascript">



   function OptionValueSelect(){
//       var a='${requestScope.collegeInfoDTO.thanaID}';
 //      var b='${requestScope.collegeInfoDTO.thanaName}';
//var aa='<s:property value='applicant.application_info.quota_ff'/>';
//alert(aa);
document.getElementById("quotaFF").value='<s:property value='applicant.application_info.quota_ff'/>';
document.getElementById("quotaEQ").value='<s:property value='applicant.application_info.quota_eq'/>';
document.getElementById("quotaBKSP").value='<s:property value='applicant.application_info.quota_bksp'/>';
document.getElementById("quotaEX").value='<s:property value='applicant.application_info.quota_expatriate'/>';

      

        //document.getElementById("quotaEX").value="${requestScope.collegeInfoDTO.thanaID}";  
       // document.getElementById("scholarshipDegree").value='${sessionScope.user.boardId}';
   }
   window.onload=setTimeout("OptionValueSelect()",100);
   </script>