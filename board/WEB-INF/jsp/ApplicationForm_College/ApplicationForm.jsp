<!DOCTYPE html>

<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=10">
		<!-- <script type="text/javascript" src="../resources/js/jquery.min.js"></script> -->
		
		<script charset="UTF-8" type="text/javascript" src="../resources/_script/jquery.js"></script>
		
		<script charset="UTF-8" type="text/javascript" src="../resources/js/defiant-latest.min.js"></script>
		<script charset="UTF-8" type="text/javascript" src="/board//resources/js/application_College.js"></script>
		<%-- <script type="text/javascript" src="../resources/js/applicationNew.js"></script> --%>
		
		

		<%@ taglib prefix="s" uri="/struts-tags"%>
		
		<link href="/board/resources/css/style.css" rel="stylesheet" />
		<link href="/board/resources/_stylesheet/RequiredContentDesign.css" rel="stylesheet" type="text/css" />
		<script charset="UTF-8" type="text/javascript" src="/board/resources/_script/mainContentAnimationCollege.js"></script>
		<style>

		.button:hover {
		box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0
		rgba(0,255,0, 0.10);
		}
		
		input[type=submit] {
    	border: 6px solid #FFF;
   		color: #fff;
    	background: tomato;
    	padding: 10px 20px;
    	border-radius: 90px;
    	font-size:20px;
		}
		input[type=submit]:hover {
  		background: #f44c0e;
		}
		</style>
	
	</head>


	<body style="margin: 0px; padding: 0px;">
		<center>

			<div class="baner" style="display: block;">
				<div style="padding-top: 5px;">
					<h3>XI Class Admission System (Session: 2020-2021)</h3>
					
				</div>
				<h2>Manual Admission Form</h2>

				<form action="collegeSVGloadReceivedAdmissionHome.action">
				<input type="submit" style="width:150px; float:left; padding: 1px 12px; color: white;background-color: green"  class="btn btn-info button"
					value="Home" title="Go Back to Main Page"/>
			    </form>	
			    
			    <form action="./logout.action">
				<input type="submit" style="width:150px; float:right; padding: 1px 12px;color: white; background-color: red" class="btn btn-info button" data-color="rgb(0, 255, 255)" data-color-format="hex"  
					data-colorpicker-guid="4" value="Logout"/>
			    </form>		
<!-- 				<button style="width:150px;float:left; padding: 1px 12px; color: white; background-color: green" class="btn btn-info button" data-color="rgb(255, 0, 255)" data-color-format="hex"  
					data-colorpicker-guid="4" onclick="FormSubmit3()"  type="button">Home</button>
				Open Student Admission Form
				<button style="width:150px;float:right; padding: 1px 12px;color: white; background-color: red" class="btn btn-info button" data-color="rgb(0, 255, 255)" data-color-format="hex"  
					data-colorpicker-guid="4" onclick="FormSubmit3()"  type="button">Logout</button> -->
			
			</div>

			<jsp:include page="SscInfo.jsp"></jsp:include>

			<form name="applicationForm" id="applicationForm">
				<div id="tx_info_div"></div>
				<div id="personal_info_div"></div>
				<jsp:include page="CollegeSelection.jsp"></jsp:include>
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
		
			<script type="text/javascript">
				clearAll();
			</script>







			
	    <div class="popupWindow" id="popupWindow">
	        <a href="" class="closeIIS">
	            <img src="/board/resources/_images/closeButton.png" class="closeIISButton" title="Close Window" alt="Close" width="24px" height="24px"/>
	        </a>
	        
	    </div>			
		 
	</body>
</html>