<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=10">
		<script type="text/javascript" src="../resources/js/jquery.min.js"></script>
		<script type="text/javascript" src="../resources/js/defiant-latest.min.js"></script>
		<script type="text/javascript" src="../resources/js/applicationNew.js"></script>



		<%@ taglib prefix="s" uri="/struts-tags"%>
		
		<link href="/board/resources/css/style.css" rel="stylesheet" />
		<title>Fourth Phase Application</title>
	</head>

	<body style="margin: 0px; padding: 0px;">
		<center>

			<div class="baner" style="display: block;">
				<div style="padding-top: 5px;">
					<h3>
						XI Class Admission System (Session: 2015-2016)
					</h3>
				</div>
				Online Application Form( <b>Fourth Phase</b>)
			</div>

			<jsp:include page="NewSscInfo.jsp"></jsp:include>

			<form name="applicationForm" id="applicationForm">
				<div id="personal_info_div"></div>
				<jsp:include page="NewCollegeSelection.jsp"></jsp:include>
			</form>
			<div id="response_div" class="response_div"></div>
			<p style="height: 50px;"> </p>
		</center>
		<script type="text/javascript">
			clearAll(); 
		</script>
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
	
	
		
		
		 
	</body>
</html>