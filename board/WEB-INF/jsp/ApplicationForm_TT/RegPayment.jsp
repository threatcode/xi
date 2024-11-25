<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=10">
		<!-- <script charset="UTF-8" type="text/javascript" src="resources/_script/jquery.js"></script> -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="/board/resources/js/Result.js"></script>		
		<title>XI Class Admission Online Result</title>
		
		
		<link rel="icon" type="image/x-icon" href="../resources/_images/favicon.ico"/>
		<link rel="shortcut icon" type="image/x-icon" href="../resources/_images/favicon.ico" />	
		<link href="/board/resources/css/style.css" rel="stylesheet" />
		<link href="/board/resources/_stylesheet/RequiredContentDesign.css" rel="stylesheet" type="text/css" />
		<script charset="UTF-8" type="text/javascript" src="/board/resources/_script/resultContentAnimation.js"></script>
		<%@ taglib prefix="s" uri="/struts-tags"%>
		<!-- <link href="/board/resources/css/styleResult.css" rel="stylesheet" />  -->
		
<script>

	if (typeof jQuery == 'undefined') {
		document.write('<script charset="UTF-8" type="text/javascript" src="/board/resources/js/jquery.min.js"><\/script>');
	}
	if (typeof testVar == 'undefined') {
		document.write('<script charset="UTF-8" type="text/javascript" src="/board/resources/js/Result.js"><\/script>');
	}
</script>		
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
				Result
				</span>
			</div>

			<jsp:include page="ResultSSC_RegPayment.jsp"></jsp:include>
			<div id="result_info_div"></div>
			<div id="response_div" class="response_div"></div>
<!-- 			
			<div class="resultRedNoteDiv">
				&#2478;&#2472;&#2507;&#2472;&#2496;&#2468; &#2438;&#2476;&#2503;&#2470;&#2472;&#2453;&#2494;&#2480;&#2496;&#2453;&#2503; <font color="red"><b>&#2535;&#2539;/&#2540;/&#2536;&#2534;&#2535;&#2541; &#2468;&#2494;&#2480;&#2495;&#2454; &#2480;&#2494;&#2468; &#2535;&#2535;.&#2539;&#2543;&#2463;&#2494;&#2480; &#2478;&#2471;&#2509;&#2479;&#2503; &#2478;&#2507;&#2476;&#2494;&#2439;&#2482; &#2476;&#2509;&#2479;&#2494;&#2434;&#2453;&#2495;&#2434; &#2458;&#2494;&#2480;&#2509;&#2460; &#2476;&#2494;&#2470;&#2503; &#2535;&#2542;&#2539; &#2463;&#2494;&#2453;&#2494; </b></font>(&#2480;&#2453;&#2503;&#2463; &#2437;&#2469;&#2476;&#2494; &#2463;&#2503;&#2482;&#2495;&#2463;&#2453; &#2437;&#2469;&#2476;&#2494; &#2486;&#2495;&#2451;&#2480;&#2453;&#2509;&#2479;&#2494;&#2486; -&#2447;&#2480; &#2478;&#2494;&#2471;&#2509;&#2479;&#2478;&#2503;) &#2460;&#2478;&#2494; &#2470;&#2495;&#2527;&#2503; &#2477;&#2480;&#2509;&#2468;&#2495; &#2472;&#2495;&#2486;&#2509;&#2458;&#2494;&#2527;&#2472; &#2453;&#2480;&#2468;&#2503; &#2489;&#2476;&#2503;&#2404; <font color="red"><b>&#2437;&#2472;&#2509;&#2479;&#2469;&#2494;&#2527; &#2478;&#2472;&#2507;&#2472;&#2527;&#2472; &#2451; &#2477;&#2480;&#2509;&#2468;&#2495;&#2480; &#2438;&#2476;&#2503;&#2470;&#2472; &#2476;&#2494;&#2468;&#2495;&#2482; &#2489;&#2476;&#2503;&#2404; </b> </font>&#2477;&#2480;&#2509;&#2468;&#2495; &#2472;&#2495;&#2486;&#2509;&#2458;&#2494;&#2527;&#2472;&#2503;&#2480; &#2460;&#2472;&#2509;&#2479; &#2477;&#2480;&#2509;&#2468;&#2495; &#2472;&#2495;&#2480;&#2509;&#2470;&#2503;&#2486;&#2495;&#2453;&#2494; &#2451; website -&#2447; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2472;&#2495;&#2480;&#2509;&#2470;&#2503;&#2486;&#2494;&#2476;&#2482;&#2496; &#2437;&#2472;&#2497;&#2488;&#2480;&#2467; &#2453;&#2480;&#2494;&#2480; &#2474;&#2480;&#2494;&#2478;&#2480;&#2509;&#2486; &#2470;&#2503;&#2527;&#2494; &#2479;&#2494;&#2458;&#2509;&#2459;&#2503;&#2404;
			</div>
 -->			
			<p style="height: 50px;"></p>
			<intput type="hidden" id="asdasdasd" name="asdasdasd">
		</center>
		
			<script type="text/javascript">
				clearSscInfo();
				//$("#asdasdasd").val('${requestScope.asdasdasd}');
			</script>
	</body>
</html>