<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Applicant's Account</title>
 <link rel="icon" type="image/x-icon" href="/board/resources/_images/favicon.ico"/>
 <link rel="shortcut icon" type="image/x-icon" href="/board/resources/_images/favicon.ico" />
<link href="/board/resources/css/applicant.css" rel="stylesheet" />
<link href='/board/resources/css/font.css' rel='stylesheet' type='text/css' />
<link href="/board/resources/_stylesheet/ApplicantRequiredContentDesign.css" rel="stylesheet" />
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

.cButton {
	-moz-box-shadow:inset 0px 1px 0px 0px #e184f3;
	-webkit-box-shadow:inset 0px 1px 0px 0px #e184f3;
	box-shadow:inset 0px 1px 0px 0px #e184f3;
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #c123de), color-stop(1, #a20dbd));
	background:-moz-linear-gradient(top, #c123de 5%, #a20dbd 100%);
	background:-webkit-linear-gradient(top, #c123de 5%, #a20dbd 100%);
	background:-o-linear-gradient(top, #c123de 5%, #a20dbd 100%);
	background:-ms-linear-gradient(top, #c123de 5%, #a20dbd 100%);
	background:linear-gradient(to bottom, #c123de 5%, #a20dbd 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#c123de', endColorstr='#a20dbd',GradientType=0);
	background-color:#c123de;
	-moz-border-radius:6px;
	-webkit-border-radius:6px;
	border-radius:6px;
	border:1px solid #a511c0;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:20px;
	font-weight:bold;
	padding:6px 24px;
	text-decoration:none;
	text-shadow:0px 1px 0px #9b14b3;
}
.cButton:hover {
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #a20dbd), color-stop(1, #c123de));
	background:-moz-linear-gradient(top, #a20dbd 5%, #c123de 100%);
	background:-webkit-linear-gradient(top, #a20dbd 5%, #c123de 100%);
	background:-o-linear-gradient(top, #a20dbd 5%, #c123de 100%);
	background:-ms-linear-gradient(top, #a20dbd 5%, #c123de 100%);
	background:linear-gradient(to bottom, #a20dbd 5%, #c123de 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#a20dbd', endColorstr='#c123de',GradientType=0);
	background-color:#a20dbd;
}
.cButton:active {
	position:relative;
	top:1px;
}
</style>
</head>
<body>
<div id="panelwrap">
  	
	<%@include file="Header.jsp" %>      
                    
    <div class="center_content">  
 
    <div id="right_wrap">
    <div id="right_content">             
   
   	<h2 class="mainHeader">Result</h2>
    
    <div class="sidebar_section_text" style="height: 275px">
		<s:if test="%{admission_result.tmp_status == 'KO'}">
		   <div class="successMessageDiv red" id="finalResultDiv">
  <div class="successResultMessage red" style="margin-left: auto; margin-right: auto;"> Sorry, you are not in Merit List. You are advised to follow the Next Merit List. </div>
</div>
		</s:if>
		<s:if test="%{admission_result.tmp_status == 'OK'}">
		   <center>
		    <!-- 
		   	   <img src="/board/resources/images/congratulations.gif" width="300" height="150" />
		   	    -->
		   </center>
		   
		   
		   
		   
		   
<%-- 		   <table width="630px">
		    <tr>
		    	<td colspan="2" align="left" style="font-size: 14px;font-weight: bold;">
		    		You (<s:property value="applicant.application_id" />) are selected for the following:
		    	</td>
		    	<td>
		    		<s:if test='#session.admissionResult.auto_migration=="Y"'>
		    			<!--<span style="color: green; font-size: 15px;">College Migration Applied</span>-->
		    		</s:if>
		    	</td>
		    </tr>
		   	<tr>
		   		<td width="130px" align="left" style="font-size: 14px;font-weight: bold;">College Name :</td>
		   		<td width="250px" align="left" style="font-size: 14px;"><s:property value="admission_result.college_name" /></td>
		   		<s:if test="%{admission_result.priority_order != 1}">
			   			<!--<td width="250px" align="right" rowspan="5"><input class="cButton" type="button" style="width: 270px;height: 60px;" onclick="confirmForward()" value="Request College Migration" /></td>-->
		   		</s:if>
		   	</tr>
		   	<tr><td align="left" style="font-size: 14px;font-weight: bold;">Shift :</td><td align="left" style="font-size: 14px;"><s:property value="admission_result.shift_name" /></td></tr>
		   	<tr><td align="left" style="font-size: 14px;font-weight: bold;">Version :</td><td align="left" style="font-size: 14px;"><s:property value="admission_result.version_name" /></td></tr>
		   	<tr><td align="left" style="font-size: 14px;font-weight: bold;">Group :</td><td align="left" style="font-size: 14px;"><s:property value="admission_result.group_name" /></td></tr>
		   	<tr>
		   		<td align="left" style="font-size: 14px;font-weight: bold;">Merit Type :</td>
		   		<td align="left" style="font-size: 14px;">
		   			<s:property value="admission_result.merit_type" />&nbsp;<s:property value="admission_result.quota_type" />		   					   		
		   		</td>
		   	</tr>
		   </table> --%>
		   <div class="successMessageDiv" id="finalResultDiv">
  <center>
    <div class="successResultMessage">&#2437;&#2477;&#2495;&#2472;&#2472;&#2509;&#2470;&#2472;! &#2468;&#2497;&#2478;&#2495; (
      <s:property value="applicant.ssc_info.student_name" />
      )  &#2472;&#2495;&#2478;&#2509;&#2472;&#2503;&#2480; &#2453;&#2482;&#2503;&#2460;&#2503; &#2477;&#2480;&#2509;&#2468;&#2495;&#2480; &#2460;&#2472;&#2509;&#2479; &#2478;&#2472;&#2507;&#2472;&#2496;&#2468; &#2489;&#2527;&#2503;&#2459;&#2404;</div>
    <table class="CollegeDetailsTable" id="selection_row_table" style="width: 675px">
      <tbody>
        <tr>
          <th width="40%">College Name </th>
          <th width="10%">EIIN </th>
          <th width="10%">Shift</th>
          <th width="10%">Version</th>
          <th width="20%">Group</th>
          <th width="10%">Quota</th>
          <th width="10%">Merit Type</th>
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
		</s:if>
		<s:if test="%{admission_result.tmp_status == 'DB_CONNECTION_ISSUE'}">
			<font style="font-size: 20px;font-weight:bold;color: black;">
		   	 Server is fully loaded. Please wait a bit and try again.
		   </font>
		</s:if>
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
function confirmForward(){
	var strconfirm = confirm("Are you sure you want to change to your upper priority college?");
	if (strconfirm == true)
    {
        window.location="migrationForm.action";
    } else {
    	return;
    }
}
</script>
    	
</body>
</html>