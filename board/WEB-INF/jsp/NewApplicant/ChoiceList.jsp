<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Applicant's Account</title>
<link href="/board/resources/css/applicant.css" rel="stylesheet" />
<link href='/board/resources/css/font.css' rel='stylesheet' type='text/css' />
<script type="text/javascript" src="../resources/js/jquery.min.js"></script>
<script type="text/javascript" src="../resources/js/defiant-latest.min.js"></script>
<script type="text/javascript" src="../resources/js/applicationNew.js"></script>

<style>
.rounded-corner tr.paid td {
    background: #8ace9c none repeat scroll 0 0;
    border-top: 1px solid #fff;
    color: black;
    padding: 8px;
    padding-left: 0px;
}

.rounded-corner tr.unpaid td {
    background: #ed4b4b none repeat scroll 0 0;
    border-top: 1px solid #fff;
    color: #fff;
    padding: 8px;
    padding-left: 0px;
}
</style>
</head>
<body>
<s:set name="web_payment" value='%{"N"}' />
<div id="panelwrap">
  	
	<%@include file="Header.jsp" %>     
                    
    <div class="center_content" style="min-height: 500px;">  
 
    <div id="right_wrap">
    <div id="right_content">             
    <h2>Choice List</h2> 
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
          
           <s:if test='payment_status == "Y"'>
           	<s:if test='application_type == "W"'>
           		<s:set name="web_payment" value='%{"Y"}' />
           	</s:if>
           </s:if>
           
           <s:if test='payment_status == "Y"'>
        		<tr  class='paid' id="row_<s:property value='#indx.count' />">
        	</s:if>        	
        	<s:else>
        		<tr  class='unpaid' id="row_<s:property value='#indx.count' />">
        	</s:else>

	            <td align="center"><s:property value="#indx.count" /></td>
	            <td align="left"><s:property value="college_name"/></td>
	            <td align="center"><s:property value="eiin"/></td>
	            <td align="left"><s:property value="shift_name"/></td>
	            <td align="left"><s:property value="version_name"/></td>
	            <td align="left"><s:property value="group_name"/></td>	            
	            <td align="center"><s:property value="special_quota"/></td>
	            <td align="center"><s:property value="education_quota"/></td>	      
	            <td align="center">
	            <input type="text" name='choice[<s:property value='#indx.index' />].priority' id='priority_<s:property value='#indx.count' />' style="width: 30px;text-align: center;" value="<s:property value='priority' />" />
	            </td>      
            	<td align="center">

            	    <input type='hidden' name="choice[<s:property value='#indx.index' />].eiin" value="<s:property value='eiin'/>" />
		  			<input type='hidden' name="choice[<s:property value='#indx.index' />].shift_id" value="<s:property value='shift_id'/>" />
		  			<input type='hidden' name="choice[<s:property value='#indx.index' />].version_id" value="<s:property value='version_id'/>" />
		  			<input type='hidden' name="choice[<s:property value='#indx.index' />].group_id" value="<s:property value='group_id'/>" />
		  			<input type='hidden' name="choice[<s:property value='#indx.index' />].special_quota" value="<s:property value='special_quota'/>" />
		  			<input type='hidden' name="choice[<s:property value='#indx.index' />].education_quota" value="<s:property value='education_quota'/>" />
		  			<input type='hidden' name="choice[<s:property value='#indx.index' />].via" value="<s:property value='via'/>" />
            	    
            		<s:if test='payment_status == "N"'>            			
            				<img id='img_delete_<s:property value="#indx.index" />' src="/board/resources/images/trash.gif" border="0" style='cursor:pointer;cursor: pointer;' onclick="deleteChoice(<s:property value="#indx.count" />,'A')" />            			
            		</s:if>
            		<script type="text/javascript">
	            		 	college_map[<s:property value="eiin"/>] = "<s:property value='eiin'/>";
	            	</script>
            	</td>        
          
           
        	</tr>
        
        </s:iterator>
    	        
    </tbody>
</table>
</form>

	<div style="width: 57%;float: left;padding-top: 20px;">	
     	<div style="width: 30px;height: 15px;background-color: #ed4b4b;float: left;"></div>
    	<div style="float: left;margin-left: 10px;">Unpaid Applications</div>
    	<div style="width: 30px;height: 15px;background-color: #8ace9c;float: left;margin-left: 20px;"></div>
    	<div style="float: left;margin-left: 10px;">Paid Applications</div>
	</div>
	
	<div class="form_sub_buttons" style="width: 100%;padding-left: 300px;">
		<a href="javascript:void(0)" class="button green" onclick="newUpdateChoiceList()" style="width: 115px;height: 30px;font-size: 20px;">Save </a>	
    </div>
    
    
	<img src="/board/resources/images/warning_message.jpg" border="0" style="width: 750px;height: 56px;" width="750"  height="60"/><br/>
      
     </div>
     </div><!-- end of right content-->
                     
                    
    <div class="sidebar" id="sidebar">
    	<%@include file="LeftSideBar.jsp" %>
   
   		<jsp:include page="CollegeSelectionParams.jsp"></jsp:include>  
        
    </div>             
   
    
    <div class="clear"></div>
    </div> <!--end of center_content-->
    <%@include file="Footer.jsp" %>

</div>
		
		
</body>
</html>