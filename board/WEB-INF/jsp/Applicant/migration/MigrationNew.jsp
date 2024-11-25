<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Applicant's Migration Choice</title>
<link href="/board/resources/css/applicant.css" rel="stylesheet" />
 <link rel="icon" type="image/x-icon" href="/board/resources/_images/favicon.ico"/>
 <link rel="shortcut icon" type="image/x-icon" href="/board/resources/_images/favicon.ico" />
<link href='/board/resources/css/font.css' rel='stylesheet' type='text/css' />
<link href="/board/resources/_stylesheet/RequiredContentDesign.css" rel="stylesheet" />
<link href="/board/resources/_stylesheet/ApplicantRequiredContentDesign.css" rel="stylesheet" />
<script type="text/javascript" src="/board/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/board/resources/js/defiant-latest.min.js"></script>
<script type="text/javascript" src="/board/resources/js/application.js"></script>
<script charset="UTF-8" type="text/javascript" src="/board/resources/_script/ApplicantMainContentAnimation.js"></script>
<style>
.rounded-corner tr.release_slip td {
    background: #cecece none repeat scroll 0 0;
    border-top: 1px solid #fff;
    color: black;
    padding: 8px;
    padding-left: 0px;
}

.rounded-corner tr.selected_college td {
    background: #4CAF50 none repeat scroll 0 0;
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
    
    <h2 class="mainHeader">Admission Information</h2> 
    
    <div class="successMessageDiv" id="finalResultDiv">
  <center>
    <div class="successResultMessage" style="font-size: 16px">&#2437;&#2477;&#2495;&#2472;&#2472;&#2509;&#2470;&#2472;! &#2468;&#2497;&#2478;&#2495; (
      <s:property value="applicant.ssc_info.student_name" />
      )  &#2472;&#2495;&#2478;&#2509;&#2472;&#2503;&#2480; &#2453;&#2482;&#2503;&#2460;&#2503; &#2477;&#2480;&#2509;&#2468;&#2495;&#2480; &#2460;&#2472;&#2509;&#2479; &#2478;&#2472;&#2507;&#2472;&#2496;&#2468; &#2489;&#2527;&#2503;&#2459;&#2404;</div>
    <table class="CollegeDetailsTable" id="selection_row_table_1" style="width: 675px">
      <tbody>
        <tr>
          <th width="40%">College Name </th>
          <th width="10%">EIIN </th>
          <th width="10%">Shift</th>
          <th width="10%">Version</th>
          <th width="20%">Group</th>
          <th width="10%">Quota</th>
          <th width="10%">Merit</th>
        </tr>
        <tr>
          <td align="left"><s:property value="admission_result.college_name" /></td>
          <td align="center"><s:property value="admission_result.eiin" /></td>
          <td align="center"><s:property value="admission_result.shift_name" /></td>
          <td align="center"><s:property value="admission_result.version_name" /></td>
          <td align="center"><s:property value="admission_result.group_name" /></td>
          <td align="left"><s:property value="admission_result.quota_type" /></td>
          <td align="left" style="color: red"><s:property value="admission_result.merit_type" />, <s:property value="admission_result.approvedstatus" /></td>
        </tr>
        <tr></tr>
      </tbody>
    </table>
  </center>
</div>
<img src="/board/resources/images/warning_message.jpg" border="0" style="width: 750px;height: 56px; margin:0px; padding: 0px; margin-left: 4px;" width="750" height="56"/>
<div class="choiceListDiv">        
    <h2 class="mainHeader">Choice List</h2>
 	<div class="sidebar_section_text">  
	<form name="applicationForm" id="applicationForm">  
		<input type="hidden" name="applicant.application_id"  value="<s:property value='applicant.application_id' />" />
		<input type="hidden" name="applicant.ssc_info.roll"  value="<s:property value='applicant.ssc_info.roll' />" />
		<input type="hidden" name="applicant.ssc_info.board_id" value="<s:property value='applicant.ssc_info.board_id' />" />
		<input type="hidden" name="applicant.ssc_info.passing_year"  value="<s:property value='applicant.ssc_info.passing_year' />" />                 
		<input type="hidden" name="applicant.ssc_info.gpa" id="p_gpa"  value="<s:property value='applicant.ssc_info.gpa' />"  />
		<input type="hidden" name="applicant.ssc_info.gender_id" id="p_gender" value="<s:property value='applicant.ssc_info.gender_id' />"  />
		<input type="hidden" name="applicant.ssc_info.group_id" id="p_group_id" value="<s:property value='applicant.ssc_info.group_id' />"  />
		<input type="hidden" name="applicant.ssc_info.eiin" id="p_eiin"  value="<s:property value='applicant.ssc_info.eiin' />"  />
		<input type="hidden" name="applicant.ssc_info.gender_id" id="p_gender" value="<s:property value='applicant.ssc_info.gender_id' />"  />	
        			 
<table class="CollegeDetailsTable" id="selection_row_table">
    <thead>
    	<tr id="header">
		 	<th width="5%" align="center">SL.</th>
		 	<th width="36%" align="left" style="padding-left: 2px;">College Name</th>
		 	<th width="9%" align="center">EIIN</th>
		 	<th width="9%" align="left" style="padding-left: 2px;">Shift</th>
		 	<th width="9%" align="left" style="padding-left: 2px;">Version</th>
		 	<th width="9%" align="left" style="padding-left: 2px;">Group</th>
		 	<th width="4%" align="center">SQ</th>
	<th width="9%" align="center">Priority</th>	 	
		 	<th width="5%" align="center">Available Seat</th> 	
		 			 
		 	<th width="5%" align="center">Type</th> 
		 	
        </tr>
    </thead>
   
    <tbody>
 	     
		<% int i=0; %>
        <s:iterator value="choice" status="indx">  
             <% i++;
             if(i%2==0)
             {%>  
             	<tr id="row_<s:property value='#indx.count' />" class="release_slip CollegeDetailsTableEvenRows" >       
             <%}
             else
             {%>
             	<tr id="row_<s:property value='#indx.count' />" class="release_slip" >       
             <%} %>  
       				       		
	            <td align="center" ><s:property value="#indx.count" /></td>
	            <td align="left"><s:property value="college_name"/></td>
	            <td align="center"><s:property value="eiin"/></td>
	            <td align="left"><s:property value="shift_name"/></td>
	            <td align="left"><s:property value="version_name"/></td>
	            <td align="left"><s:property value="group_name"/></td>	            
	            <td align="center"><s:property value="special_quota"/></td>
	           
	            <td align="center">
	            	<input type="text" name='choice[<s:property value='#indx.index' />].priority' id='priority_<s:property value='#indx.count' />' style="width: 30px;text-align: center;" value="<s:property value='priority' />" maxlength="2" />
	            </td>  
	            
	              <td align="center"><s:property value="available_seat"/></td>   
            	<td align="center"><s:property value='application_type'/>
            	    <input type='hidden' name="choice[<s:property value='#indx.index' />].eiin" value="<s:property value='eiin'/>" />
		  			<input type='hidden' name="choice[<s:property value='#indx.index' />].shift_id" value="<s:property value='shift_id'/>" />
		  			<input type='hidden' name="choice[<s:property value='#indx.index' />].version_id" value="<s:property value='version_id'/>" />
		  			<input type='hidden' name="choice[<s:property value='#indx.index' />].group_id" value="<s:property value='group_id'/>" />
		  			<input type='hidden' name="choice[<s:property value='#indx.index' />].special_quota" value="<s:property value='special_quota'/>" />
		  			<input type='hidden' name="choice[<s:property value='#indx.index' />].education_quota" value="<s:property value='education_quota'/>" />
		  			<input type='hidden' name="choice[<s:property value='#indx.index' />].application_type" value="<s:property value='application_type'/>" />
                    <input type='hidden' name="choice[<s:property value='#indx.index' />].mobile_no" value="<s:property value='mobile_no'/>" />
        			 <%--
        			<img id='img_delete_<s:property value="#indx.index" />' src="/board/resources/images/trash.gif" border="0" style='cursor:pointer;cursor: pointer;' onclick="deleteChoice(<s:property value="#indx.count" />,'A')" />
        			 --%>
        			
            	</td>   
            	   
        	</tr>
        
        </s:iterator>
    	        
    </tbody>
</table>
</div>
  </div>
<div class="migrationConfirmation">
<h2 class="mainHeader">Migration Confirmation</h2> 

<div class="sidebar_section_text"> 

<div class="successMessageDiv red" id="finalResultDiv">
  <div class="successResultMessage red">&#2468;&#2497;&#2478;&#2495; &#2453;&#2495; Migration &#2453;&#2480;&#2468;&#2503; &#2439;&#2458;&#2509;&#2459;&#2497;&#2453;?
        
        <s:if test='#session.admissionResult.auto_migration=="Y"'> 
          <input type="radio" name="autoMigrationYN" id="autoMigrationY" value="Y" checked="checked" />
          <label for="autoMigrationY">&#2489;&#2509;&#2479;&#2494;&#2433;</label>
          <input type="radio" name="autoMigrationYN" id="autoMigrationN" value="N"  />
          <label for="autoMigrationN">&#2472;&#2494;</label>
        </s:if>
        <s:if test='#session.admissionResult.auto_migration=="N"'> <input type="radio" name="autoMigrationYN" id="autoMigrationY" value="Y" />
          <label for="autoMigrationY">&#2489;&#2509;&#2479;&#2494;&#2433;</label>
          <input type="radio" name="autoMigrationYN" id="autoMigrationN" value="N"  checked="checked"/>
          <label for="autoMigrationN">&#2472;&#2494;</label>
        </s:if>
  </div>
</div>
</div>


  </form> 
</div>
	<div class="form_sub_buttons" style="width: 100%;padding-left: 200px;">
		<!-- 
			<a href="javascript:void(0)" class="button green" onclick="updateChoiceList()" style="width: 115px;height: 30px;font-size: 20px;">Save </a>
		 -->
		 <a href="#" class="infoPopUpButton big blue submitMigration"><span>&#10003;</span>Confirm College Migration</a>
			       <div style="clear: both">
			      	&nbsp;
			      </div>
			      <div	class="loadingSubmitImage" style="display: none;">
			      		<img src='/board/resources/_images/loadingS.gif' alt="Submitting Migration Request" style="width:150px; height:150px; margin-left: 90px;"/>
					</div>
		</div>
    
    
	<br/>
      
     </div>
     </div><!-- end of right content-->
                     
                    
    <div class="sidebar" id="sidebar">
   		<div style="clear: both;">
   		<%@include file="../LeftSideBar.jsp" %>  
        </div>
        <%--
        <jsp:include page="../CollegeSelectionParams.jsp"></jsp:include>
         --%>
    </div>             
   
    
    <div class="clear"></div>
    </div> <!--end of center_content-->
    <%@include file="../Footer.jsp" %>

</div>
<div class="popupWindow" id="popupWindow">
	        <a href="" class="closeIIS">
	            <img src="/board/resources/_images/closeButton.png" class="closeIISButton" title="Close Window" alt="Close" width="24px" height="24px"/>
	        </a>
	        
	    </div>	
<script type="text/javascript">
$("#selection_box").css("margin-top","10px");
</script>		
		
</body>
</html>