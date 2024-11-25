<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Applicant's Account</title>
<link href="/board/resources/css/applicant.css" rel="stylesheet" />
<link href='/board/resources/css/font.css' rel='stylesheet' type='text/css' />
<script type="text/javascript" src="../resources/js/jquery.min.js"></script>
<style>

.sidebar_section_text{
padding-top:15px !important;
padding-bottom:20px !important;
margin-left:0px !important;
width:726px !important;
}
.form_row{
width: 735px !important;
}
label{
width: 80px !important;
} 
</style>
</head>
<body>
<div id="panelwrap">
  	
	<%@include file="Header.jsp" %>         
                    
    <div class="center_content">  
 
    <div id="right_wrap">
    <div id="right_content">             
   
   <h2>Applicant's Information</h2> 
    <div class="sidebar_section_text" style="height: 318px">
		<!--  Start -->
             
            <div class="form_row">
	            <label>Roll</label>
	            <input type="text" value="<s:property value='applicant.ssc_info.roll'/>" class="form_input"  style="width: 150px;" disabled="disabled"/>
	            <label style="margin-left: 10px;">Board</label>
	            <input type="text" value="<s:property value='applicant.ssc_info.board_name'/>" class="form_input" name=""  style="width: 150px;" disabled="disabled"/>
	            <label style="margin-left: 10px;">Passing Year</label>
	            <input type="text" value="<s:property value='applicant.ssc_info.passing_year'/>" class="form_input" name=""  style="width: 150px;" disabled="disabled"/>
            </div>
            <div class="form_row">
	            <label>Name</label>
	            <input type="text" value="<s:property value='applicant.ssc_info.student_name'/>" class="form_input" name=""  style="width: 150px;" disabled="disabled"/>
	            <label style="margin-left: 10px;">Father</label>
	            <input type="text" value="<s:property value='applicant.ssc_info.father_name'/>" class="form_input" name=""  style="width: 150px;" disabled="disabled"/>
	            <label style="margin-left: 10px;">Mother</label>
	            <input type="text" value="<s:property value='applicant.ssc_info.mother_name'/>" class="form_input" name=""  style="width: 150px;" disabled="disabled"/>
            </div>
            <div class="form_row">
	            <label>Gender</label>
	            <input type="text" value="<s:property value='applicant.ssc_info.gender_name'/>" class="form_input" name=""  style="width: 150px;" disabled="disabled"/>
	            <label style="margin-left: 10px;">GPA</label>
	            <input type="text" value="<s:property value='applicant.ssc_info.gpa'/>" class="form_input" name=""  style="width: 150px;" disabled="disabled"/>
	            <label style="margin-left: 10px;"></label>	           
            </div>
            
      <div class="form_row" style="background-color: #bad7e6;color: #22425e;text-align: left;font-weight: bold;padding-top: 10px;font-size: 15px;height: 32px;">
	  	You will receive all admission related information in this contact number.
	  </div>
            
            
            <div class="form_row">
	            <label>Mobile</label>
	            <input type="text" value="<s:property value='applicant.application_info.mobile_number'/>"  class="form_input" name=""  style="width: 150px;" disabled="disabled"/>
            </div>

	  <div class="form_row" style="background-color: #bad7e6;color: #22425e;text-align: left;font-weight: bold;padding-top: 10px;font-size: 15px;height: 32px;">
	  Do you want to apply for Quota for College Admission ?
	  </div>

            <div class="form_row">
	            <label style="float: left;">Quota</label>
				<select name="quota_YN" id="quota_YN" style="float:left;" onchange="showHideQuotaType(this.value)">
				  <option value="N" <s:if test='applicant.application_info.quota_yn=="N"'> selected="selected" </s:if>>No</option>
				  <option value="Y" <s:if test='applicant.application_info.quota_yn=="Y"'> selected="selected" </s:if>>Yes</option>
				</select>
				
				<div id="quota_type_div" style="float: left;margin-left: 2%;">
				
            	<label style="float: left;">Quota Type</label>
		            <div style="margin-top:6px;font-weight: bold;float: left;color: green;">		            
		            <input type="checkbox" name="applicant.application_info.quota_ff" id="quota_ff" value="Y" <s:if test='applicant.application_info.quota_ff=="Y"'> checked="checked" </s:if>/> Freedom Fighter
		            </div>
	            </div>
	            
            </div>
            
		<!--  End -->
    </div>
	<div class="form_sub_buttons" style="width: 100%;padding-left: 300px;">
		<a href="javascript:void(0)" class="button green" onclick="updateQuotaInformation();" style="width: 108px;height: 35px;font-size: 27px;">Save</a>
    </div>
    
    <img src="/board/resources/images/warning_message.jpg" border="0" style="width: 750px;height: 60px;" width="750"  height="60"/>
    
    
        <div class="toogle_wrap">
            <div class="trigger"><a href="javascript:void(0)">Notice</a></div>

            <div class="toggle_container">
			<p>
        		<font color="red">Please pay the application fees by Teletalk Mobile :</font>        		
        		<br/>
        		<b><font color='red'>CAD</font></b> {SPACE} <b><font color='red'>WEB</font></b> {SPACE} <b><font color='red'>APPLICATION_ID</font></b> 
        		
        		and send it to 16222 Number;
        		
			</p>
            </div>
        </div>
      
     </div>
     </div><!-- end of right content-->
                     
                    
    <div class="sidebar" id="sidebar">
    <%@include file="LeftSideBar.jsp" %>
   
    </div>             
   
    
    <div class="clear"></div>
    </div> <!--end of center_content-->
    
	<%@include file="Footer.jsp" %>

</div>
<script type="text/javascript">

function showHideQuotaType(quota_YN){

	if(quota_YN=="Y")
		$("#quota_type_div").show();
	else {
	    document.getElementById("quota_ff").checked=false;
		$("#quota_type_div").hide();
	}
}

function updateQuotaInformation(){
	
var quota_YN=document.getElementById("quota_YN").value;
if(quota_YN=="N"){	
	document.getElementById("quota_ff").checked=false;
}
else{
	if(document.getElementById("quota_ff").checked==false)
	 {
	  alert("Please select quota type");
	  return;
	 }
}

if(document.getElementById("quota_ff").checked==true)
	quota_ff="Y";
else 	
   quota_ff="N";


   
$.ajax({
	  url: 'updateQuotaInfo.action',
	  type: 'POST',
	  data: {quota_YN:quota_YN,quota_ff:quota_ff},
	  success: function(data) {		
		alert(data);
		if(data=="Successfully Updated Quota Information.")
		{
			window.location='applicantInfoReadOnly.action';
		}
	  },
	  error: function(e) {
	  }
});
	
}


<s:if test='applicant.application_info.quota_yn=="N"'>
 document.getElementById("quota_type_div").style.display="none";
</s:if>
</script>
    	
</body>
</html>