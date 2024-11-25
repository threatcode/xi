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
    
           
 
	<div class="successMessageDiv" id="finalResultDiv">
  <center>
    <div class="successResultMessage">Your Migration Request Received Successfully</div>
  </center>
</div>
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
<script type="text/javascript">
$("#selection_box").css("margin-top","10px");
</script>		
		
</body>
</html>