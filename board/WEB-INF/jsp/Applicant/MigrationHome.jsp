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
.cUButton {
	-moz-box-shadow:inset 0px 1px 0px 0px #97c4fe;
	-webkit-box-shadow:inset 0px 1px 0px 0px #97c4fe;
	box-shadow:inset 0px 1px 0px 0px #97c4fe;
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #3d94f6), color-stop(1, #1e62d0));
	background:-moz-linear-gradient(top, #3d94f6 5%, #1e62d0 100%);
	background:-webkit-linear-gradient(top, #3d94f6 5%, #1e62d0 100%);
	background:-o-linear-gradient(top, #3d94f6 5%, #1e62d0 100%);
	background:-ms-linear-gradient(top, #3d94f6 5%, #1e62d0 100%);
	background:linear-gradient(to bottom, #3d94f6 5%, #1e62d0 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#3d94f6', endColorstr='#1e62d0',GradientType=0);
	background-color:#3d94f6;
	-moz-border-radius:6px;
	-webkit-border-radius:6px;
	border-radius:6px;
	border:1px solid #337fed;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:15px;
	font-weight:bold;
	padding:6px 24px;
	text-decoration:none;
	text-shadow:0px 1px 0px #1570cd;
}
.cUButton:hover {
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #1e62d0), color-stop(1, #3d94f6));
	background:-moz-linear-gradient(top, #1e62d0 5%, #3d94f6 100%);
	background:-webkit-linear-gradient(top, #1e62d0 5%, #3d94f6 100%);
	background:-o-linear-gradient(top, #1e62d0 5%, #3d94f6 100%);
	background:-ms-linear-gradient(top, #1e62d0 5%, #3d94f6 100%);
	background:linear-gradient(to bottom, #1e62d0 5%, #3d94f6 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#1e62d0', endColorstr='#3d94f6',GradientType=0);
	background-color:#1e62d0;
}
.cUButton:active {
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
   
   <h2>Admission Information</h2> 
    <div class="sidebar_section_text" style="height: 200px">
		<!--  Start -->
             
            <div class="form_row">
	            <label>College Name</label>
	            <input type="text" value="<s:property value='#session.admissionResult.college_name'/>" class="form_input"  style="width: 395px;" disabled="disabled"/>
	            <label style="margin-left: 10px;">Merit Type</label>
	            <input type="text" value="<s:property value='#session.admissionResult.merit_type'/>" class="form_input" name=""  style="width: 150px;" disabled="disabled"/>
            </div>
            <div class="form_row">
	            <label>Shift</label>
	            <input type="text" value="<s:property value='#session.admissionResult.shift_name'/>" class="form_input" name=""  style="width: 150px;" disabled="disabled"/>
	            <label style="margin-left: 10px;">Version</label>
	            <input type="text" value="<s:property value='#session.admissionResult.version_name'/>" class="form_input" name=""  style="width: 150px;" disabled="disabled"/>
	            <label style="margin-left: 10px;">Group</label>
	            <input type="text" value="<s:property value='#session.admissionResult.group_name'/>" class="form_input" name=""  style="width: 150px;" disabled="disabled"/>
            </div>
            <div class="form_row" style="background-color: #151B54;color: white;text-align: left;font-weight: bold;padding-top: 2px;font-size: 15px;height: 22px;">
	  			Do you want to apply for Migration to your upper Priority College?
	  		</div>
	  		<form action="submitMigrationForm" onsubmit="return validateForm()" method="post">
            <div class="form_row">            
              <div style="width: 30%;float:left; ">
            	<label style="margin-left: 10px;">Migration</label>
            	
            	<s:if test='#session.admissionResult.auto_migration=="Y"'>
            		Yes <input type="radio" name="autoMigrationYN" value="Y"/>
            		No <input type="radio" name="autoMigrationYN" value="N" checked="checked"  />
            	</s:if>
            	<s:if test='#session.admissionResult.auto_migration=="N"'>
            		Yes <input type="radio" name="autoMigrationYN" value="Y" checked="checked"  />
            		No <input type="radio" name="autoMigrationYN" value="N"/>
            	</s:if>
            </div>	
            
	            <div style="width: 55%;float:left; ">
	            <input type="submit" class="cUButton" value="Confirm College Migration" />
	            </div> 
            </div>
            </form> 
		<!--  End -->
    </div>
	<div class="form_sub_buttons" style="width: 100%;">
	 	<!--<center>
			<input type="button" class="cButton" value="Go to Result"  onclick="window.location='viewResult.action'" style="width: 200px;height: 60px;"/>			
		</center>	-->		
    </div>
    <!--<img src="/board/resources/images/warning_message.jpg" border="0" style="width: 750px;height: 56px;" width="750"  height="60"/>-->
    
    
        <div class="toogle_wrap">
            <div class="trigger"><a href="javascript:void(0)">Notice</a></div>

            <div class="toggle_container">
			<p>
        		<font color="red" size="3px">
        		To complete your migration request click on "Confirm College Migration" button. <br/>
        		If you change your Migration Status to <b>Yes</b>.
        		<br/> Your might get chance to a higher priority of your given choice list. And your current admission will be canceled
        		</font>        		
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
function validateForm() {
    var x = confirm("Are you sure want to change college Migration status?");
    if (x == true) {
        return true;
    } else 
    	return false;
}

</script>    	
</body>
</html>