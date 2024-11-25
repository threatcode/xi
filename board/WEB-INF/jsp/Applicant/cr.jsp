<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=10">
		<script type="text/javascript" src="/board/resources/js/jquery.min.js"></script>
		<%@ taglib prefix="s" uri="/struts-tags"%>		
		<link href="/board/resources/css/style.css" rel="stylesheet" />		
	</head>

	<body style="margin: 0px; padding: 0px;">
		<center>

			<div class="baner" style="display: block;">
				<div style="padding-top: 5px;">
					<h3>
						XI Class Admission System (Session: 2015-2016)
					</h3>
				</div>
						Admission Result
			</div>

			<jsp:include page="SscInfo_result.jsp"></jsp:include>

			<div id="response_div" class="response_div"></div>
		</center>		 
		 
	<script type="text/javascript">
	function fetchChoiceList(){
		var valid=validateField("ssc_roll","ssc_board","ssc_year","ssc_reg");
	 	if(valid==true){
		 	
		 	$("#vr").attr("disabled", true);
		 	$("#response_div").show();
		 	$("#response_div").html("<img src='/board/resources/images/239.gif' />");
		 
	 		$.ajax({
		 		url: 'viewResult1.action',		
		 		type: 'POST',
		 		data: {ssc_roll:$("#ssc_roll").val(),ssc_board:$("#ssc_board").val(),ssc_year:$("#ssc_year").val(),ssc_reg:$("#ssc_reg").val()},
		 		success: function(data) {
		 			$("#vr").removeAttr("disabled");
			 		$("#response_div").html(data);
		 		},
		 		error: function(e) {
		 		}
	 			});
	 
		}
	}
	
	function validateField(){
	
	var isValid=true;
	var element;
	for (var i = 0; i < arguments.length; i++) {
		element=$("#"+arguments[i]);
	if(element && $.trim(element.val())==""){
		cbColor(element,"e");isValid=false;
	}
	else cbColor(element,"v");	  
	}
	return isValid;
}
function cbColor(element,type){
	
	if(type=="e")
		element.css("border", "2px solid red");
	else if(type=="v")
		element.css("border", "1px solid #add9e4");
}
	</script>	 
	</body>
</html>
