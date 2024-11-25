<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "edu.action.application.ApplicationSvgHtml" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=10">
<title>XICA 2019: HTML View of SVG</title>


<link rel="icon" type="image/x-icon"
	href="resources/_images/favicon.ico" />
<link href="resources/css/college.css" rel="stylesheet" />
<link href='resources/css/font.css' rel='stylesheet' type='text/css' />
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/dataTables.bootstrap.css" />
<link rel="stylesheet" href="resources/css/tabledata/jquery.dataTables.css" type="text/css"></link>
<%-- <script charset="UTF-8" type="text/javascript" --%>
<%-- 	src="resources/js/svg_application_nasir.js"></script> --%>
<%-- <script type="text/javascript" src="resources/js/defiant-latest.min.js"></script> --%>
<%-- <script type="text/javascript" language="javascript" --%>
<%-- 	src="resources/js/bootstrap.min.js"></script> --%>
<%-- <script type="text/javascript" language="javascript" --%>
<%-- 	src="resources/js/jquery.dataTables.min.js"></script> --%>
<%-- <script type="text/javascript" language="javascript" --%>
<%-- 	src="resources/js/dataTables.bootstrap.js"></script> --%>

<%-- <script charset="UTF-8" type="text/javascript" src="../resources/_script/jquery.js"></script> --%>

<script charset="UTF-8" type="text/javascript"
	src="resources/js/svg_application_nasir.js"></script>
	
<%-- <script charset="UTF-8" type="text/javascript" src="../resources/js/numeric.js"></script> --%>
<%-- <script charset="UTF-8" type="text/javascript" src="../resources/js/dynamicMoving.js"></script> --%>
<%-- <script charset="UTF-8"  type="text/javascript" src="/board/resources/_script/mainContentAnimation.js"></script> --%>

<script charset="UTF-8" type="text/javascript" src="resources/_script/jquery.js"></script>
		
		
		<script charset="UTF-8" type="text/javascript" src="resources/js/defiant-latest.min.js"></script>

		<script charset="UTF-8" type="text/javascript" src="resources/js/numeric.js"></script>
		<script charset="UTF-8" type="text/javascript" src="resources/js/dynamicMoving.js"></script>
		<script charset="UTF-8"  type="text/javascript" src="resources/_script/mainContentAnimation.js"></script>

<link
	href="resources/_stylesheet/ApplicantRequiredContentDesign.css"
	rel="stylesheet" />
	
<link href="resources/_stylesheet/RequiredContentDesign.css"
	rel="stylesheet" />

	<script charset="UTF-8"  type="text/javascript" src="resources/_script/mainContentAnimation.js"></script>	

	<style>
	
		#cpatchaTextBox{
			padding: 12px 20px;
		    display: inline-block;
		    border: 1px solid #ccc;
		    border-radius: 4px;
		    box-sizing: border-box;
		}

#btnVerify {
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
	-moz-border-radius:3px;
	-webkit-border-radius:3px;
	border-radius:3px;
	border:1px solid #337fed;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:18px;
	font-weight:bold;
	padding:6px 18px;
	text-decoration:none;
	text-shadow:0px 1px 0px #1570cd;
}
#btnVerify:hover {
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #1e62d0), color-stop(1, #3d94f6));
	background:-moz-linear-gradient(top, #1e62d0 5%, #3d94f6 100%);
	background:-webkit-linear-gradient(top, #1e62d0 5%, #3d94f6 100%);
	background:-o-linear-gradient(top, #1e62d0 5%, #3d94f6 100%);
	background:-ms-linear-gradient(top, #1e62d0 5%, #3d94f6 100%);
	background:linear-gradient(to bottom, #1e62d0 5%, #3d94f6 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#1e62d0', endColorstr='#3d94f6',GradientType=0);
	background-color:#1e62d0;
}
#btnVerify:active {
	position:relative;
	top:1px;
}
		
/* 		#btnVerify{ */
/* 			background-color: #4CAF50; */
/* 			background-color: #66d9ff; */
/* 			background-color: #4d79ff; */
/* 		    border: none; */
/* 		    color: white; */
/* 		    padding: 12px 30px; */
/* 		    text-decoration: none; */
/* 		    margin: 4px 2px; */
/* 		    cursor: pointer; */
/* 		    font-weight: bold; */
/* 		    font-size: 16px; */
/* 		} */
		
		canvas{
		  /*prevent interaction with the canvas*/
		  pointer-events:none;
		}		
	</style>
	
</head>


<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <body onload="createCaptcha()"> -->

<body>
<div id="panelwrap">
	<div align="center">
		<div align="center">
			<div style="margin:10px 10px 10px 10px;">
				
				<h2
					style="background-color:#bad7e6;margin:0px; clear:both;
					-moz-border-radius-topleft:6px;-webkit-border-top-left-radius:6px;-khtml-border-top-left-radius:6px;border-top-left-radius:6px;
					-moz-border-radius-topright:6px;-webkit-border-top-right-radius:6px;-khtml-border-top-right-radius:6px;border-top-right-radius:6px;
					color:#22425e; font-size:14px; font-weight:bold; padding:10px 0 10px 15px; text-shadow:1px 1px #DCEEF7;
					border-bottom:1px #ABC7D6 solid;">
					SVG Details
				</h2>
				<div class="sidebar_section_text">
					<center>
						<br />
						
						<div>
							<div id="loadedCaptcha">
								<table border="0">
									<tr>
									<td style="margin:0px;padding:0px; border: 0px">
							        	<img id="captchaImg" src="captcha" alt="Captcha Image" height="30" style="margin:0px;padding:0px;">
  							        </td>
							        		<td style="border: 0px; ">
							        <input type="text" id="user_captcha" placeholder="Captcha" name="u_cap" style="width: 90px;text-align: center; font-size: 17px; font-weight: bold;"/>
							        		</td>
							        </tr>
							      
							        <tr align="center"><td colspan="2"><img id="reloadCaptcha" src="<c:url value='resources/images/refresh.png' />" alt="Reload" onclick="document.getElementById('captchaImg').src='captcha'+'?id='+Math.random();" style="cursor:pointer; display:none;"/></td></tr>
						        </table>	 
							</div>
							<br/>
							
							<br/>
							
							<div id="verifyDiv">
							 <input type="button" value="Verify" id="btnVerify" onclick="validateCaptcha()" style="width:100px;"/>
							</div>
							
<!-- 							<div id="captchaPan" align="center"> -->
<!-- 								<div id="captcha"> -->
<!-- 							    </div> -->
<!-- 							    <input type="text" placeholder="Captcha" id="cpatchaTextBox"/> <br/> -->
<!-- 							    <button type="button" id="btnVerify" onclick="validateCaptcha()">Verify</button>							 -->
<!-- 							</div> -->
<!-- 							<hr/> -->
							<br/>			
							<div id="svgSearchPan" align="center">
							
							</div>
						</div>
						<br />
						
						<s:if test="CorrectAnswer != null && actionMessage != ''">
						    <div class="successMessage">
						    <br/><s:property value="CorrectAnswer"/>
						    </div>
						    <br />
						</s:if>
						<br />
<!-- 						<hr/> -->
							<div style="padding-left:30px">
								<div id="showSVG"></div>
								<br>
							</div>
					</center>
				</div>
			</div>
			<br>
		</div>
	</div>
	
	
<!-- 	Added New -->
	
<!-- 	    <div class="popupWindow" id="popupWindow"> -->
<!-- 	        <a href="" class="closeIIS"> -->
<!-- 	            <img src="/board/resources/_images/closeButton.png" class="closeIISButton" title="Close Window" alt="Close" width="24px" height="24px"/> -->
<!-- 	        </a> -->
	        
<!-- 	    </div>	 -->
	
<!-- 	<link rel="stylesheet" -->
<!-- 		href="resources/css/tabledata/jquery.dataTables.css" -->
<!-- 		type="text/css"></link> -->
<!-- 	<link rel="stylesheet" -->
<!-- 		href="resources/js/jquery.validity.1.2.0/jquery.validity.css" -->
<!-- 		type="text/css"></link> -->


<!-- 	<script type="text/javascript" -->
<!-- 		src="resources/js/jquery.validity.1.2.0/jquery-1.6.4.min.js"></script> -->
<!-- 	<script type="text/javascript" -->
<!-- 		src="resources/js/jquery.validity.1.2.0/jQuery.validity.js"></script> -->


	<script type="text/javascript">
		
		function fetchDistrict() {

			var boardId = helper_board_id.value;

			//Dhaka Board-->13 Districts
			var distDhaka = [ [ "", "Select District" ], [ "3026", "Dhaka" ],
					[ "3029", "Faridpur" ], [ "3033", "Gazipur" ],
					[ "3035", "Gopalganj" ], [ "3048", "Kishorgonj" ],
					[ "3056", "Manikganj" ], [ "3054", "Madaripur" ],
					[ "3059", "Munshiganj" ], [ "3068", "Narsingdi" ],
					[ "3067", "Narayanganj" ], [ "3082", "Rajbari" ],
					[ "3086", "Shariatpur" ], [ "3093", "Tangail" ] ];
			//Dhaka Board-->6 Districts
			var distComilla = [ [ "", "Select District" ],
					[ "2012", "Brahmanbaria" ], [ "2013", "Chandpur" ],
					[ "2019", "Comilla" ], [ "2030", "Feni" ],
					[ "2051", "Lakshmipur" ], [ "2075", "Noakhali" ] ];
			//Rajshahi Board-->8 Districts
			var distRajshahi = [ [ "", "Select District" ],
					[ "5010", "Bogra" ], [ "5070", "Chapai Nawabganj" ],
					[ "5038", "Joypurhat" ], [ "5064", "Naogaon" ],
					[ "5069", "Natore" ], [ "5076", "Pabna" ],
					[ "5081", "Rajshahi" ], [ "5088", "Sirajganj" ] ];
			//Jessore Board--> 10 Districts
			var distJessore = [ [ "", "Select District" ],
					[ "4001", "Bagerhat" ], [ "4018", "Chuadanga" ],
					[ "4041", "Jessore" ], [ "4044", "Jhenaidah" ],
					[ "4047", "Khulna" ], [ "4050", "Kushtia" ],
					[ "4055", "Magura" ], [ "4057", "Meherpur" ],
					[ "4065", "Narail" ], [ "4087", "Satkhira" ] ];
			//Chittagong Board --> 5 Districts
			var distChittagong = [ [ "", "Select District" ],
					[ "2003", "Bandarban" ], [ "2015", "Chittagong" ],
					[ "2022", "Cox's Bazar" ], [ "2046", "Khagrachari" ],
					[ "2084", "Rangamati" ] ];
			//Barisal Board--> 6 Districts
			var distBarisal = [ [ "", "Select District" ],
					[ "1004", "Barguna" ], [ "1006", "Barisal" ],
					[ "1009", "Bhola" ], [ "1042", "Jhalokati" ],
					[ "1078", "Patuakhali" ], [ "1079", "Pirojpur" ] ];
			//Sylhet Board--> 4 Districts
			var distSylhet = [ [ "", "Select District" ],
					[ "6036", "Habiganj" ], [ "6058", "Maulvibazar" ],
					[ "6090", "Sunamganj" ], [ "6091", "Sylhet" ] ];
			//Mymensingh--> 4 Districts
			var distMymensingh = [ [ "", "Select District" ],
					[ "3039", "Jamalpur" ], [ "3061", "Mymensingh" ],
					[ "3072", "Netrakona" ], [ "3089", "Sherpur" ] ];
			//Dinajpur--> 8 Districts
			var distDinajpur = [ [ "", "Select District" ],
					[ "5027", "Dinajpur" ], [ "5032", "Gaibandha" ],
					[ "5049", "Kurigram" ], [ "5052", "Lalmonirhat" ],
					[ "5073", "Nilphamari" ], [ "5077", "Panchagarh" ],
					[ "5085", "Rangpur" ], [ "5094", "Thakurgaon" ] ];
			//Dinajpur--> 64 Districts
			var distMadrasah = [ [ "", "Select District" ],
					[ "4001", "Bagerhat" ], [ "2003", "Bandarban" ],
					[ "1004", "Barguna" ], [ "1006", "Barisal" ],
					[ "1009", "Bhola" ], [ "2012", "Brahmanbaria" ],
					[ "5010", "Bogra" ], [ "2013", "Chandpur" ],
					[ "5070", "Chapai Nawabganj" ], [ "2015", "Chittagong" ],
					[ "4018", "Chuadanga" ], [ "2019", "Comilla" ],
					[ "2022", "Cox's Bazar" ], [ "3026", "Dhaka" ],
					[ "5027", "Dinajpur" ], [ "3029", "Faridpur" ],
					[ "2030", "Feni" ], [ "5032", "Gaibandha" ],
					[ "3033", "Gazipur" ], [ "3035", "Gopalganj" ],
					[ "6036", "Habiganj" ], [ "3039", "Jamalpur" ],
					[ "4041", "Jessore" ], [ "1042", "Jhalokati" ],
					[ "4044", "Jhenaidah" ], [ "5038", "Joypurhat" ],
					[ "2046", "Khagrachari" ], [ "4047", "Khulna" ],
					[ "3048", "Kishorgonj" ], [ "5049", "Kurigram" ],
					[ "4050", "Kushtia" ], [ "2051", "Lakshmipur" ],
					[ "5052", "Lalmonirhat" ], [ "3056", "Manikganj" ],
					[ "3054", "Madaripur" ], [ "4055", "Magura" ],
					[ "6058", "Maulvibazar" ], [ "4057", "Meherpur" ],
					[ "3059", "Munshiganj" ], [ "3061", "Mymensingh" ],
					[ "5064", "Naogaon" ], [ "3067", "Narayanganj" ],
					[ "4065", "Narail" ], [ "3068", "Narsingdi" ],
					[ "5069", "Natore" ], [ "3072", "Netrakona" ],
					[ "5073", "Nilphamari" ], [ "2075", "Noakhali" ],
					[ "5076", "Pabna" ], [ "5077", "Panchagarh" ],
					[ "1078", "Patuakhali" ], [ "1079", "Pirojpur" ],
					[ "3082", "Rajbari" ], [ "5081", "Rajshahi" ],
					[ "2084", "Rangamati" ], [ "5085", "Rangpur" ],
					[ "4087", "Satkhira" ], [ "3086", "Shariatpur" ],
					[ "3089", "Sherpur" ], [ "5088", "Sirajganj" ],
					[ "6090", "Sunamganj" ], [ "6091", "Sylhet" ],
					[ "3093", "Tangail" ], [ "5094", "Thakurgaon" ] ];

			var dist = document.getElementById('district');

			document.getElementById('district').innerHTML = "";

			if (boardId == 10) {
				for ( var i = 0; i < distDhaka.length; i++) {
					var opt = document.createElement('option');
					opt.innerHTML = distDhaka[i][1];
					opt.value = distDhaka[i][0];
					dist.appendChild(opt);
				}
			} else if (boardId == 11) {
				for ( var i = 0; i < distComilla.length; i++) {
					var opt = document.createElement('option');
					opt.innerHTML = distComilla[i][1];
					opt.value = distComilla[i][0];
					dist.appendChild(opt);
				}
			} else if (boardId == 12) {
				for ( var i = 0; i < distRajshahi.length; i++) {
					var opt = document.createElement('option');
					opt.innerHTML = distRajshahi[i][1];
					opt.value = distRajshahi[i][0];
					dist.appendChild(opt);
				}
			} else if (boardId == 13) {
				for ( var i = 0; i < distJessore.length; i++) {
					var opt = document.createElement('option');
					opt.innerHTML = distJessore[i][1];
					opt.value = distJessore[i][0];
					dist.appendChild(opt);
				}
			} else if (boardId == 14) {
				for ( var i = 0; i < distChittagong.length; i++) {
					var opt = document.createElement('option');
					opt.innerHTML = distChittagong[i][1];
					opt.value = distChittagong[i][0];
					dist.appendChild(opt);
				}
			} else if (boardId == 15) {
				for ( var i = 0; i < distBarisal.length; i++) {
					var opt = document.createElement('option');
					opt.innerHTML = distBarisal[i][1];
					opt.value = distBarisal[i][0];
					dist.appendChild(opt);
				}
			} else if (boardId == 16) {
				for ( var i = 0; i < distSylhet.length; i++) {
					var opt = document.createElement('option');
					opt.innerHTML = distSylhet[i][1];
					opt.value = distSylhet[i][0];
					dist.appendChild(opt);
				}
			} else if (boardId == 17) {
				for ( var i = 0; i < distDinajpur.length; i++) {
					var opt = document.createElement('option');
					opt.innerHTML = distDinajpur[i][1];
					opt.value = distDinajpur[i][0];
					dist.appendChild(opt);
				}
			} else if (boardId == 21) {
				for ( var i = 0; i < distMymensingh.length; i++) {
					var opt = document.createElement('option');
					opt.innerHTML = distMymensingh[i][1];
					opt.value = distMymensingh[i][0];
					dist.appendChild(opt);
				}
			} else if (boardId == 18) {
				for ( var i = 0; i < distMadrasah.length; i++) {
					var opt = document.createElement('option');
					opt.innerHTML = distMadrasah[i][1];
					opt.value = distMadrasah[i][0];
					dist.appendChild(opt);
				}
			}

		}

		function setEiin(current) {
			eiinCode.value = college_id.value;
		}

// 		function fetchCollegeList() {
// 			clearSelectBox("college_id");

// 			var distId = district.value;
// 			var boardId = helper_board_id.value;

// 			$.ajax({
// 				url : 'getCollegeList',
// 				type : 'POST',
// 				data : {
// 					district_id : distId,
// 					helper_board_id : boardId
// 				},
// 				success : function(data) {
// 					setCollegeSelectBoxData(data);
// 				},
// 				error : function(jqXHR, textStatus, errorThrown) {
// 					alert(jqXHR);
// 					alert(textStatus);
// 					alert(errorThrown);
// 				}
// 			});
// 		}

		function setCollegeSelectBoxData(data) {
			var colleges = data;
			var collegeId = document.getElementById('college_id');
			for ( var i = 0; i < colleges.length; i++) {
				var opt = document.createElement('option');
				opt.innerHTML = colleges[i].collegeName;
				opt.value = colleges[i].eiinCode;
				collegeId.appendChild(opt);
			}

		}

		function clearSelectBox() {
			for ( var i = 0; i < arguments.length; i++) {
				$("#" + arguments[i]).find('option:gt(0)').remove();
			}
		}

// 		function fetchCollegeSVG() {
			
// 			var reference = abc.value;
// 			alert(reference);
// 			if(reference==''){
// 				alert("Please verify captcha, then search for college");
// 				return;
// 			}
			
// 			var rawEiin = eiinCode.value;
// 			var eiin = rawEiin.trim();

// 			if (eiin == "" || eiin == null || eiin.length != 6) {
// 				alert("Please provide valid EIIN");
// 				return;
// 			}

// 			$('#showSVG').prepend($('<img>', {
// 				id : 'theImg',
// 				src : 'resources/images/loading1.gif'
// 			}));

// 			$.ajax({
// 				type : 'POST',
// 				url : "svgShowByEiin",
// 				dataType : 'text',
// 				async : false,
// 				data : {

// 					eiinCode : eiin
// 				}
// 			}).done(function(msg) {
// 				$("#showSVG").html(msg);
// 				createCaptcha();
// 				document.getElementById("abc").value = "";
// 				document.getElementById('btnVerify').style.visibility = 'visible';
// 			}).always(function() {
// 			}).fail(function(data) {
// 				if (data.responseCode)
// 					alert(data.responseCode);
// 			});

// 		} 
	
		
	</script>


	<%@ include file="/WEB-INF/jsp/template/footerLite.jsp"%>

</div>
<div class="popupWindow" id="popupWindow">
	        <a href="" class="closeIIS">
	            <img src="/board/resources/_images/closeButton.png" class="closeIISButton" title="Close Window" alt="Close" width="24px" height="24px"/>
	        </a>
	        
	    </div>	
</body>
</html>