<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet"
	href="/board/resources/css/tabledata/jquery.dataTables.css"
	type="text/css"></link>
<!-- <div> -->
<!-- 	<table border="0"><tr><td style="border: 0px"> -->
<!--        <img id="captchaImg2" src="runTimeCaptcha" alt="Captcha Image" height="30"> -->
<!--        		</td><td style="border: 0px"> -->
<!--        <input type="text" id="user_captcha" placeholder="Captcha" name="u_cap" style="width: 80px;text-align: center;"/> -->
<!--        		</td></tr> -->
<!--        </table>	  -->
<!-- </div> -->


<div>

	<input type='hidden' name='abc' id='abc' value=''> <label>Board</label>
	<select id="helper_board_id" onchange="fetchDistrict()">

		<option value="15">Barishal</option>
					<option value="20">BOU</option>
					<option value="14">Chattogram</option>
					<option value="11">Cumilla</option>
					<option value="10" selected="selected">Dhaka</option>
					<option value="17">Dinajpur</option>
					<option value="13">Jashore</option>
					<option value="18">Madrasah</option>
					<option value="21">Mymensingh</option>
					<option value="12">Rajshahi</option>
					<option value="16">Sylhet</option>
					<option value="19">TEC</option>
		
		
	</select> <label>District</label> <select id="district"
		onchange="fetchCollegeList()">
	</select> <label>College</label> <select id="college_id"
		onchange="setEiin(this)">
		<option value="">Select College</option>
	</select> <label for="eiinCode">EIIN</label> <input type="number" id="eiinCode"
		name="eiinCode" minlength="6" />

	<button style="width:180px;padding: 1px 12px;" class="btn btn-info"
		type="button" onclick="fetchCollegeSVG()">Fetch College SVG</button>

</div>

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

		function fetchCollegeList() {
			clearSelectBox("college_id");
			
			var distId = district.value;
			var boardId = helper_board_id.value;
			
			
			
			if ($("#user_captcha").val().trim() == '') {		
			 //var alertMessage="Please give verification code.";
			 
			 fetchDistrict();
			  //document.getElementById('district').innerHTML = "";//testing
			  var popUpWindowHeight= 159;
				var popUpWindowWidth=300;
				var alertMessage="Please give verification code.";
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon" style="width: 92%">&#9587;</div><div class="promptDivMessage" style="width: 92%">'+alertMessage+'</div></div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			 	//alert(alertMessage);
			 return;
			}
			
			var client_captcha = user_captcha.value;
			$.ajax({
				url : 'getCollegeList',
				type : 'POST',
				data : {
					district_id : distId,
					helper_board_id : boardId,
					user_captcha: client_captcha
				},
				success : function(data) {
				if (data == "ce") {
							//var alertMessage="Please give correct verification code.";
							//alert(alertMessage);
							var popUpWindowHeight= 159;
				var popUpWindowWidth=300;
				var alertMessage="Please give correct verification code.";
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon" style="width: 92%">&#9587;</div><div class="promptDivMessage" style="width: 92%">'+alertMessage+'</div></div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
							document.getElementById("user_captcha").value = "";
							$(reloadCaptcha).click();							
							return;

						}else{
							setCollegeSelectBoxData(data);
						}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR);
					alert(textStatus);
					alert(errorThrown);
				}
			});
		}
		
		function updateDiv()
		{ 
		    $( "#loadedCaptcha" ).load(window.location.href + " #loadedCaptcha" );
		}

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
		
		
		function scrollToBottom(){
			$('html, body').animate({scrollTop:$(document).height()}, 'slow');
		}

		function fetchCollegeSVG() {
			
		
			var rawEiin = eiinCode.value;
			var eiin = rawEiin.trim();
			
			if ($("#user_captcha").val().trim() == '') {		
			 var alertMessage="Please give verification code.";
			 
			  var popUpWindowHeight= 159;
				var popUpWindowWidth=300;
			 
			 var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon" style="width: 92%">&#9587;</div><div class="promptDivMessage" style="width: 92%">'+alertMessage+'</div></div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			 //alert(alertMessage);
			
			 
			 return;
			}
			
			var client_captcha = user_captcha.value;
			//alert("client_captcha: " + client_captcha); 

			if (eiin == "" || eiin == null || eiin.length != 6) {
				alert("Please provide valid EIIN");
				return;
			}
			
			//alert("user_captcha: " + user_captcha);
			
			$('#showSVG').prepend($('<img>', {
				id : 'theImg',
				src : 'resources/images/loading1.gif'
			}));
			
			$.ajax({
				type : 'POST',
				url : "svgShowByEiin",
				dataType : 'text',
				async : false,
				data : {

					eiinCode : eiin,
					user_captcha: client_captcha
				}
			}).done(function(msg) {
			if (msg == "ce") {
							var alertMessage="Please give correct verification code.";
							
							 var popUpWindowHeight= 159;
				var popUpWindowWidth=300;
							
							var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon" style="width: 92%">&#9587;</div><div class="promptDivMessage" style="width: 92%">'+alertMessage+'</div></div>';
							$('#CollegeDetailsPopUpContainerDiv').remove();
							var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
						    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
							//alert(alertMessage);
							document.getElementById("user_captcha").value = "";
							$("#showSVG").html('');				//to remove reload animation
							$(reloadCaptcha).click();							
							return;
						}else{
											$("#showSVG").html(msg);
				scrollToBottom();
				document.getElementById("user_captcha").value = "";
				$(reloadCaptcha).click();
						}
			}).always(function() {
			}).fail(function(data) {
				if (data.responseCode)
					alert(data.responseCode);
			});

		} 
	</script>