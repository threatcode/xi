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
<div id="panelwrap">
  	
	<%@include file="../Header.jsp" %>        
                    
    <div class="center_content" style="min-height: 500px;">  
 
    <div id="right_wrap">
    <div id="right_content">             
    <h2>Choice List</h2> 
         
<table class="rounded-corner" id="selection_row_table">
    <thead>
    	<tr id="header">
		 	<th width="5%" align="center">SL.</th>
		 	<th width="41%" align="left" style="padding-left: 2px;">College Name</th>
		 	<th width="9%" align="center">EIIN</th>
		 	<th width="9%" align="left" style="padding-left: 2px;">Shift</th>
		 	<th width="9%" align="left" style="padding-left: 2px;">Version</th>
		 	<th width="9%" align="left" style="padding-left: 2px;">Group</th>
		 	<th width="4%" align="center">SQ</th>
		 	<th width="5%" align="center">EQ</th>
		 	<th width="9%" align="center">Priority</th>		 	
        </tr>
    </thead>
        
    <tbody>
 	     
		
        <s:iterator value="choice" status="indx">
       		<tr class="release_slip" id="row_<s:property value='#indx.count' />">
	            <td align="center"><s:property value="#indx.count" /></td>
	            <td align="left"><s:property value="college_name"/></td>
	            <td align="center"><s:property value="eiin"/></td>
	            <td align="left"><s:property value="shift_name"/></td>
	            <td align="left"><s:property value="version_name"/></td>
	            <td align="left"><s:property value="group_name"/></td>	            
	            <td align="center"><s:property value="special_quota"/></td>
	            <td align="center"><s:property value="education_quota"/></td>
	            <td align="center"><s:property value='priority' /></td>                  	
        	</tr>        
        </s:iterator>
    	        
    </tbody>
</table>

<!-- 
	<div class="form_sub_buttons" style="width: 100%;padding-left: 250px">		
		<a href="javascript:void(0)" class="button green" onclick="window.location='releaseSlip.action'" style="width: 200px;height: 30px;font-size: 21px;">Edit Release Slip</a>
    </div>

	<img src="/board/resources/images/warning_message.jpg" border="0" style="width: 750px;height: 56px;" width="750"  height="60"/><br/>
 -->
     </div>
     </div><!-- end of right content-->
                     
                    
    <div class="sidebar" id="sidebar">
    <%@include file="../LeftSideBar.jsp" %>
   
    </div>             
   
    
    <div class="clear"></div>
    </div> <!--end of center_content-->
    <%@include file="../Footer.jsp" %>


</div>
		
		
</body>
</html>