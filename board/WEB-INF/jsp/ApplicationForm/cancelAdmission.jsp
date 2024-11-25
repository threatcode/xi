<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=10">
		<script type="text/javascript" src="../resources/js/jquery.min.js"></script>
		<script type="text/javascript" src="../resources/js/defiant-latest.min.js"></script>
		<script type="text/javascript" src="/board/resources/js/application.js"></script>
		
		

		<%@ taglib prefix="s" uri="/struts-tags"%>
		
		<link href="/board/resources/css/style.css" rel="stylesheet" />
		
	</head>

	<body style="margin: 0px; padding: 0px;">
		<div style="align: right"><a href="logout.action">Logout</a></div>
		<center>

	
			<jsp:include page="SscInfo_CancelAdmission.jsp"></jsp:include>

			<form name="applicationForm" id="applicationForm">
				<div id="personal_info_div"></div>
				<div id="personal_info_div1"></div>
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
<script language="javascript">
document.getElementById("ssc_board").options[5].selected="selected";
document.getElementById("ssc_year").options[1].selected="selected";
function cadmission(){
	$("#personal_info_div1").html("<img src='/board/resources/images/239.gif' />");
	$.ajax({
			 	url: 'saveCancelAdmission',		
			 	type: 'POST',
			 	data: {application_id:$("#application_id").val(),merit_type:$("#merit_type").val()},
			 	success: function(data) {
			 		alert(data);
			 		$("#personal_info_div").html("");
			 		$("#personal_info_div1").html("");
			 		
			 	},
			 	error: function(e) {
			 	}
	});
}
</script>	
</html>