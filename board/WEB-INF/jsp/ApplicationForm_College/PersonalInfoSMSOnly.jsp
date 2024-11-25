<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="box_container" style="min-height:198px;margin-top: 8px;" id="personal_info_div">

<div class="box_header">
Personal Information
</div>

<div class="box_body" style="min-height:190px;">
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
		    
		    <input type="hidden" name="applicant.application_info.quota_ff" id="p_eiin"  class="readonly"  value="<s:property value='applicant.application_info.quota_ff' />"  />	
		    <input type="hidden" name="applicant.application_info.quota_eq" id="p_eiin"  class="readonly"  value="<s:property value='applicant.application_info.quota_eq' />"  />	
		    <input type="hidden" name="applicant.application_info.quota_bksp" id="p_eiin"  class="readonly"  value="<s:property value='applicant.application_info.quota_bksp' />"  />	
		    <input type="hidden" name="applicant.application_info.quota_expatriate" id="p_eiin"  class="readonly"  value="<s:property value='applicant.application_info.quota_expatriate' />"  />	
		    
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
	<input type="button" onclick="showPopUp('choiceListReport_TT')" value="Print Application"/>
<!-- 
 <input type="button" value="Next" onclick="collegeSelectionEdit()" style="width:100px;" />
 --> 
</div>

</div>


<script type="text/javascript">
function showPopUp(actionName){
	//window.open(actionName+'?application_id='+$('#application_id_old').val(),'1433313485446','width=780,height=850,toolbar=0,menubar=0,location=0,status=1,scrollbars=1,resizable=0,left=0,top=0');
	window.open(actionName,'1433313485446','width=780,height=850,toolbar=0,menubar=0,location=0,status=1,scrollbars=1,resizable=0,left=0,top=0');
}
</script>   


