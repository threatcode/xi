<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=10">
		<!-- <script type="text/javascript" src="../resources/js/jquery.min.js"></script> -->
		<title>XI Class Admission Online Application Form </title>
		
		
		<link rel="icon" type="image/x-icon" href="../resources/_images/favicon.ico"/>
		<link rel="shortcut icon" type="image/x-icon" href="../resources/_images/favicon.ico" />
		<script charset="UTF-8" type="text/javascript" src="../resources/_script/jquery.js"></script>
		
		
		<script charset="UTF-8" type="text/javascript" src="../resources/js/defiant-latest.min.js"></script>
		<script charset="UTF-8" type="text/javascript" src="../resources/js/numeric.js"></script>
		<%-- <script type="text/javascript" src="../resources/js/applicationNew.js"></script> --%>
		
		

		<%@ taglib prefix="s" uri="/struts-tags"%>
		
		<link href="/board/resources/css/style.css" rel="stylesheet" />
		<link href="/board/resources/_stylesheet/RequiredContentDesign.css" rel="stylesheet" type="text/css" />
		<script charset="UTF-8" type="text/javascript" src="/board/resources/_script/ApplicantMainContentAnimation.js"></script>
		
	</head>

	<body style="margin: 0px; padding: 0px;">
		<center>

			<div class="baner headerDivApp" style="display: block;">
				<div style="padding-top: 5px;">
					<h3 class="mHeading">
						XI Class Admission System (Session: 2020-2021)
					</h3>
				</div>
				<span class="sHeading">
				Online College Admission Migration
				</span>
			</div>
			
			
         <form id="ApplicantCheckForm" method="post" action="applicantCheckValidity">
			<jsp:include page="loginSscInfo.jsp"></jsp:include>			
         </form>	
			
		</center>
		
		
			<script type="text/javascript">
				//clearAll();
			</script>

<%--<link href="/board/resources/_stylesheet/Structure.css" rel="stylesheet" type="text/css" />--%>

<%--<script type="text/javascript" src="_script/jquery.js"></script>
--%>





			
	    <div class="popupWindow" id="popupWindow">
	        <a href="" class="closeIIS">
	            <img src="/board/resources/_images/closeButton.png" class="closeIISButton" title="Close Window" alt="Close" width="24px" height="24px"/>
	        </a>
	        
	    </div>			
		 
	</body>
</html>