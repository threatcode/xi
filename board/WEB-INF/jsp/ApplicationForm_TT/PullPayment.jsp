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

<%-- 		<link rel="icon" type="image/x-icon" href="/application/favicon.ico"/>
		<link rel="shortcut icon" type="image/x-icon" href="/application/favicon.ico" />
		<script charset="UTF-8" type="text/javascript" src="/application/jquery.js"></script> --%>
		
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>		
		
		<script charset="UTF-8" type="text/javascript" src="../resources/js/defiant-latest.min.js"></script>
		<script charset="UTF-8" type="text/javascript" src="../resources/js/application_TT.js"></script>
		<script charset="UTF-8" type="text/javascript" src="../resources/js/numeric.js"></script>
		<script charset="UTF-8" type="text/javascript" src="../resources/js/dynamicMoving.js"></script>

<%-- 		<script charset="UTF-8" type="text/javascript" src="/application/defiant-latest.min.js"></script>
		<script charset="UTF-8" type="text/javascript" src="../resources/js/application_TT.js"></script>
		<script charset="UTF-8" type="text/javascript" src="/application/numeric.js"></script>
		<script charset="UTF-8" type="text/javascript" src="/application/dynamicMoving.js"></script> --%>
		
		

		<%@ taglib prefix="s" uri="/struts-tags"%>
		
		<link href="/board/resources/css/style.css" rel="stylesheet" />
		<link href="/board/resources/_stylesheet/RequiredContentDesign.css" rel="stylesheet" type="text/css" />
		<script charset="UTF-8"  type="text/javascript" src="/board/resources/_script/mainContentAnimation.js"></script>
		
		

		
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
				Online Application Form
				</span>
			</div>

			<jsp:include page="SscInfo_payment.jsp"></jsp:include>
           <jsp:include page="TxInfo.jsp"></jsp:include>
			<form name="applicationForm" id="applicationForm">
				<div id="tx_info_div"></div>
				<div id="personal_info_div"></div>
				<div id="college_selection_container"></div>
			</form>
			<div id="response_div" class="response_div"></div>
			<p style="height: 50px;"> </p>
		</center>
		
		<form id="parentChoiceForm" name="parentChoiceForm">
			<input type="hidden" name="print_app_id" id="print_app_id" value=""/>
			<input type="hidden" name="print_password" id="print_password" value=""/>
			<input type="hidden" name="print_app_name" id="print_app_name" value=""/>		
			<input type="hidden" name="print_ff" id="print_ff" value=""/>
			<input type="hidden" name="print_eq" id="print_eq" value=""/>	
			<input type="hidden" name="print_ssc_roll" id="print_ssc_roll"  value=""/>
			<input type="hidden" name="print_ssc_board" id="print_ssc_board" value=""/>
			<input type="hidden" name="print_ssc_year" id="print_ssc_year" value=""/>			
			<input type="hidden" name="print_choice_list" id="print_choice_list" value=""/>			
		</form>	

	





			
	    <div class="popupWindow" id="popupWindow">
	        <a href="" class="closeIIS">
	            <img src="/board/resources/_images/closeButton.png" class="closeIISButton" title="Close Window" alt="Close" width="24px" height="24px"/>
	        </a>
	        
	    </div>			
		 
	</body>
</html>