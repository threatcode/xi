<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=10">
		<script charset="UTF-8" type="text/javascript" src="resources/_script/jquery.js"></script>
		<script charset="UTF-8" type="text/javascript" src="resources/js/Result.js"></script>
		<%@ taglib prefix="s" uri="/struts-tags"%>
		<link href="/board/resources/css/style.css" rel="stylesheet" />
	</head>

	<body style="margin: 0px; padding: 0px;">
		<center>

			<div class="baner" style="display: block;">
				<div style="padding-top: 5px;">
					<h3>
						XI Class Admission System (Session: 2020-2021)
					</h3>
				</div>
				Result
			</div>

			<jsp:include page="ResultSSC.jsp"></jsp:include>
			<div id="result_info_div"></div>
			<div id="response_div" class="response_div"></div>
			<p style="height: 50px;"> </p>
		</center>
		
			<script type="text/javascript">
				clearSscInfo();
			</script>
		 
	</body>
</html>