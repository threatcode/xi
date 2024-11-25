<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Applicant's Account</title>
<link href="/board/resources/css/applicant.css" rel="stylesheet" />
<link href='/board/resources/css/font.css' rel='stylesheet' type='text/css' />
<script type="text/javascript" src="/board/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/board/resources/js/defiant-latest.min.js"></script>
<script type="text/javascript" src="/board/resources/js/application.js"></script>
<style>
.rounded-corner tr.release_slip td {
    background: #cecece none repeat scroll 0 0;
    border-top: 1px solid #fff;
    color: black;
    padding: 8px;
    padding-left: 0px;
}
</style>
</head>
<body>
<s:set name="web_payment" value='%{"N"}' />
<div id="panelwrap">
  	
	<%@include file="../Header.jsp" %>         
                    
    <div class="center_content" style="min-height: 500px;">  
 
    <div id="right_wrap">
    <div id="right_content">             
    <h2>Release Slip(Choice List)</h2>
     
	<form name="applicationForm" id="applicationForm">  
		<input type="hidden" name="applicant.application_id"  value="<s:property value='applicant.application_id' />" />
		<input type="hidden" name="applicant.ssc_info.roll"  value="<s:property value='applicant.ssc_info.roll' />" />
		<input type="hidden" name="applicant.ssc_info.board_id" value="<s:property value='applicant.ssc_info.board_id' />" />
		<input type="hidden" name="applicant.ssc_info.passing_year"  value="<s:property value='applicant.ssc_info.passing_year' />" />                 
		<input type="hidden" name="applicant.ssc_info.gpa" id="p_gpa"  value="<s:property value='applicant.ssc_info.gpa' />"  />
		<input type="hidden" name="applicant.ssc_info.gender_id" id="p_gender" value="<s:property value='applicant.ssc_info.gender_id' />"  />
		<input type="hidden" name="applicant.ssc_info.group_id" id="p_group_id" value="<s:property value='applicant.ssc_info.group_id' />"  />
		<input type="hidden" name="applicant.ssc_info.eiin" id="p_eiin"  value="<s:property value='applicant.ssc_info.eiin' />"  />
        			 
<table class="rounded-corner" id="selection_row_table">
    <thead>
    	<tr id="header">
		 	<th width="5%" align="center">SL.</th>
		 	<th width="36%" align="left" style="padding-left: 2px;">College Name</th>
		 	<th width="9%" align="center">EIIN</th>
		 	<th width="9%" align="left" style="padding-left: 2px;">Shift</th>
		 	<th width="9%" align="left" style="padding-left: 2px;">Version</th>
		 	<th width="9%" align="left" style="padding-left: 2px;">Group</th>
		 	<th width="4%" align="center">SQ</th>
		 	<th width="5%" align="center">EQ</th>
		 	<th width="9%" align="center">Priority</th>		 	
		 	<th width="5%" align="center">Delete</th>
        </tr>
    </thead>
   
    <tbody>
 	     
		
        <s:iterator value="choice" status="indx">          
       		<tr id="row_<s:property value='#indx.count' />" class="release_slip">       		       		
	            <td align="center"><s:property value="#indx.count" /></td>
	            <td align="left"><s:property value="college_name"/></td>
	            <td align="center"><s:property value="eiin"/></td>
	            <td align="left"><s:property value="shift_name"/></td>
	            <td align="left"><s:property value="version_name"/></td>
	            <td align="left"><s:property value="group_name"/></td>	            
	            <td align="center"><s:property value="special_quota"/></td>
	            <td align="center"><s:property value="education_quota"/></td>
	            <td align="center">
	            	<input type="text" name='choice[<s:property value='#indx.index' />].priority' id='priority_<s:property value='#indx.count' />' style="width: 30px;text-align: center;" value="<s:property value='priority' />" maxlength="2" />
	            </td>      
            	<td align="center">
            	    <input type='hidden' name="choice[<s:property value='#indx.index' />].eiin" value="<s:property value='eiin'/>" />
		  			<input type='hidden' name="choice[<s:property value='#indx.index' />].shift_id" value="<s:property value='shift_id'/>" />
		  			<input type='hidden' name="choice[<s:property value='#indx.index' />].version_id" value="<s:property value='version_id'/>" />
		  			<input type='hidden' name="choice[<s:property value='#indx.index' />].group_id" value="<s:property value='group_id'/>" />
		  			<input type='hidden' name="choice[<s:property value='#indx.index' />].special_quota" value="<s:property value='special_quota'/>" />
		  			<input type='hidden' name="choice[<s:property value='#indx.index' />].education_quota" value="<s:property value='education_quota'/>" />
        			<img id='img_delete_<s:property value="#indx.index" />' src="/board/resources/images/trash.gif" border="0" style='cursor:pointer;cursor: pointer;' onclick="deleteChoice(<s:property value="#indx.count" />,'A')" />
        			<script type="text/javascript">
	            		 	college_map[<s:property value="eiin"/>] = "<s:property value='eiin'/>";
	            	</script>
            	</td>        
        	</tr>
        
        </s:iterator>
    	        
    </tbody>
</table>
</form>


	
	<div class="form_sub_buttons" style="width: 100%;padding-left: 300px;">
		<!-- 
			<a href="javascript:void(0)" class="button green" onclick="updateChoiceList()" style="width: 115px;height: 30px;font-size: 20px;">Save </a>
		 -->	
		<a href="javascript:void(0)" class="button green" onclick="updateReleaseSlip()" style="width: 115px;height: 30px;font-size: 20px;">Save </a>
    </div>
    
    
	<img src="/board/resources/images/warning_message.jpg" border="0" style="width: 750px;height: 56px;" width="750"  height="60"/><br/>
      
     </div>
     </div><!-- end of right content-->
                     
                    
    <div class="sidebar" id="sidebar">
    	
   		<jsp:include page="../CollegeSelectionParams.jsp"></jsp:include>

   		<div style="clear: both;padding-top: 25px;">
   		<%@include file="../LeftSideBar.jsp" %>  
        </div>
    </div>             
   
    
    <div class="clear"></div>
    </div> <!--end of center_content-->
    <%@include file="../Footer.jsp" %>

</div>
<script type="text/javascript">
$("#selection_box").css("margin-top","10px");
</script>		
		
</body>
</html>