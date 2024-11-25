<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Contact Number Change</title>
<link rel="icon" type="image/x-icon" href="../resources/_images/favicon.ico"/>
<link rel="shortcut icon" type="image/x-icon" href="../resources/_images/favicon.ico" />
		
<link href="/board/resources/css/applicant.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Belgrano'
	rel='stylesheet' type='text/css' />
<script charset="UTF-8" type="text/javascript" src="/board/resources/_script/jquery.js"></script>

<script charset="UTF-8" type="text/javascript" src="/board/resources/js/application_TT.js"></script>


<link href="/board/resources/_stylesheet/RequiredContentDesign.css" rel="stylesheet" type="text/css" />
		<script charset="UTF-8" type="text/javascript" src="/board/resources/_script/mainContentAnimation.js"></script>
<style>

#loginpanelwrap{width:500px; margin:30px auto auto auto; background-color:#FFFFFF;
-moz-border-radius:10px;-webkit-border-radius:10px;-khtml-border-radius:10px; 
border-radius:10px;border:6px #c0cdd2 solid;
}
.NB_row{width:460px;float:left;clear:both;margin:0 0 10px 0;}
.button {
	display: inline-block;
	border-radius: 4px;
	background-color: #6e7b8b;
	border: none;
	color: #FFFFFF;
	text-align: center;
	font-size: 28px;
	padding: 0px;
	width: 150px;
	height: 50px; transition : all 0.5s;
	cursor: pointer;
	margin: 5px;
	transition: all 0.5s;
}

.button span {
	cursor: pointer;
	display: inline-block;
	position: relative;
	transition: 0.5s;
}

.button span:after {
	content: ' >';
	position: absolute;
	opacity: 0;
	top: 0;
	right: -20px;
	transition: 0.5s;
}

.button:hover span {
	padding-right: 25px;
}

.button:hover span:after {
	opacity: 1;
	right: 0;
}

.button:hover {
	box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0
		rgba(0, 0, 255, 0.19);
}
</style>
</head>
<body>
	<div id="loginpanelwrap">

		<div class="loginheader">
			<div class="logintitle">
				<font size="4"><b><a href="javascript:void(0)">
							Wrong Contact Number Change for Applicants</a></b> </font>
			</div>
		</div>
		<hr />
		<div class="loginform">

			<div class="loginform_row">
				<label>SSC Roll </label> <label class="colonLabel"> : </label > <input type="text"
					id="ssc_roll" style="width:150px;" 
					class="loginform_input" required />
			</div>

			<div class="loginform_row">
				<label>SSC Reg no. </label> <label class="colonLabel"> : </label> <input type="text"
					id="ssc_reg" style="width:150px;" 
				    class="loginform_input" required />
			</div>

			<div class="loginform_row">
				<label>SSC Board</label> <label class="colonLabel"> : </label> <select
					style="width:156px;" id="ssc_board" onchange="reloadYear(this.value)">
					<option value="none">Select Board</option>
					<option value="15">Barishal</option>
					<option value="20">BOU</option>
					<option value="14">Chattogram</option>
					<option value="11">Cumilla</option>
					<option value="10">Dhaka</option>
					<option value="17">Dinajpur</option>
					<option value="13">Jashore</option>
					<option value="18">Madrasah</option>
					<option value="21">Mymensingh</option>
					<option value="12">Rajshahi</option>
					<option value="16">Sylhet</option>
					<option value="19">TEC</option>

				</select>
			</div>

			<div class="loginform_row">
				<label>Passing Year</label> <label class="colonLabel"> : </label> <select id="ssc_year"
					style="width:156px;">
					<option value="">Select Year</option>
					<option value="2020">2020</option>
					<option value="2019">2019</option>
					<option value="2018">2018</option>
					<!--
					<option value="2017">2017</option>					 
					<option value="2016">2016</option>					
					<option value="2013">2013</option>
					<option value="2012">2012</option>
					<option value="2011">2011</option>
					<option value="2010">2010</option>
					<option value="2009">2009</option>
					<option value="2008">2008</option>
					<option value="2007">2007</option>
					<option value="2006">2006</option>
					 -->
				</select>
			</div>
			<div class="loginform_row">
				<label>Mother Name </label> <label class="colonLabel"> : </label> <input type="text"
					id="mother_name" style="width:150px;" 
				    class="loginform_input" required />
			</div>


			<div class="NB_row"> 
			<p style="color: blue;font-weight: bold;">			
			 <font color="red">
			 &#2438;&#2474;&#2472;&#2495; &#2463;&#2494;&#2453;&#2494; &#2474;&#2509;&#2480;&#2470;&#2494;&#2472;&#2503;&#2480; &#2488;&#2478;&#2527; &#2479;&#2503; Contact No.  &#2470;&#2495;&#2527;&#2503;&#2459;&#2495;&#2482;&#2503;&#2472; &#2468;&#2494;&#2468;&#2503; &#2488;&#2478;&#2509;&#2477;&#2476;&#2468; &#2453;&#2507;&#2472; &#2465;&#2495;&#2460;&#2495;&#2463;&#2503;&#2480; (&#2488;&#2480;&#2509;&#2476;&#2494;&#2471;&#2495;&#2453; &#2468;&#2495;&#2472;&#2463;&#2495;) &#2477;&#2497;&#2482; &#2469;&#2494;&#2453;&#2494;&#2527; &#2437;&#2472;&#2482;&#2494;&#2439;&#2472;&#2503; &#2438;&#2476;&#2503;&#2470;&#2472; &#2453;&#2480;&#2468;&#2503; &#2474;&#2494;&#2480;&#2459;&#2503;&#2472; &#2472;&#2494;&#2404; &#2438;&#2474;&#2472;&#2495; &#2438;&#2474;&#2472;&#2494;&#2480; &#2488;&#2464;&#2495;&#2453; Contact No. &#2463;&#2495; &#2472;&#2495;&#2478;&#2509;&#2472;&#2503; &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480;&#2497;&#2472;&#2404;
			 
			  </font>
			</p>
			</div>

			<div class="loginform_row">
				<label> <font color="red">*</font> Contact No. </label> <label class="colonLabel"> : </label> 
				<input	type="text" maxlength="11" style="width:150px;"
					onkeyup="checkInput(this)" id="mobile" class="loginform_input"
					onchange="validateMobileNumber(this.value)" required />					
									
			</div>
			<div class="loginform_row">
				<label> <font color="red">*</font> Retype Contact No. </label> <label class="colonLabel"> : </label> 
				<input	type="text" maxlength="11" style="width:150px;"
					onkeyup="checkInput(this)" id="mobile1" class="loginform_input"
					onchange="validateMobileNumber(this.value)" required />					
									
			</div>
			
			<div class="loginform_row">
			
			<label>Verification Code </label> <label class="colonLabel"> : </label>
			<img id="captchaImg" src="captcha" alt="Captcha Image" height="30">
			<input type="text" id="captchaText" name="captchaText" style="width: 70px;text-align: center;"/>	 
			
			</div>
			
					

			<center>
			<br>
				<button type="button" onclick="pinRetrival()" class="button"
					style="vertical-align:middle">
					<span>Submit </span>
				</button>
			</center>

		</div>

		<center>
			<div id="PinRetrive"></div>
		</center>
	</div>
	<script type="text/javascript">
function scrollToBottom(){
	$('html, body').animate({scrollTop:$(document).height()}, 'slow');
}
    function pinRetrival() {
               $('#PinRetrive').html('');
/*     image.src="/board/resources/inages/loading_by_adnan.gif";
    $("#PinRetrive").html(image);  */
   

       if($("#ssc_roll").val()==""){
            //alert("Give your SSC roll.");
            var alertMessage="Give your SSC roll.";
				var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
            
            
            
            
            return;
        }
        if($("#ssc_reg").val()==""){
            //alert("Give your SSC registration no.");
            
             var alertMessage="Give your SSC registration no.";
				var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
            
            
            
            
            
            return;
        }
        
       if($("#ssc_board").val()=="none"){
            //alert("Select your SSC board.");
                         var alertMessage="Select your SSC board.";
				var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			    
            return;
        }
        if($("#ssc_year").val()==""){
            //alert("Select your SSC passing year.");
            var alertMessage="Select your SSC passing year.";
				var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
            return;
        }
        
        if($("#mother_name").val()==""){
            //alert("Select your SSC passing year.");
            var alertMessage="Give your Mother's Name.";
				var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
            return;
        }

       if($("#mobile").val()==""){
            //alert("Give your contact number.");
            var alertMessage="Give your contact number.";
				var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
            
            return;
        } 
        
        if($("#mobile1").val()==""){
            //alert("Re-Type contact cumber.");
            var alertMessage="Re-Type contact number.";
				var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			    return;
        } 
        
        if($("#mobile").val()!=$("#mobile1").val()){
            //alert("Two contact numbers are not same .");
            var alertMessage="Two contact numbers are not same.";
				var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			    return;
        } 
        
        if($("#captchaText").val()==""){
            //alert("Please give verification code.");
            var alertMessage="Please give verification code.";
				var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			    return;
        } 
         $('#PinRetrive').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "retrivedMob",
            dataType: 'text',
            async   : false,
            data    : {mother_name:$("#mother_name").val().trim() ,ssc_roll: $("#ssc_roll").val().trim(),ssc_reg: $("#ssc_reg").val().trim(),ssc_board: $("#ssc_board").val().trim(),
                       ssc_passing_year: $("#ssc_year").val().trim(),given_mobile_number: $("#mobile").val().trim(),captchaText: $("#captchaText").val().trim()}

        }).done(function (msg) {
        		$('#PinRetrive').html('');
        		if(msg=="Captcha Error")
				 	{
							//alert("Please correct verification code.");
							var alertMessage="Please correct verification code.";
				var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
							$("#captchaText").css("border", "2px solid red");
						setTimeout(function() { 		
			 		location.reload();
			 		exit; }, 4000);
						//window.location.reload(true);
				 	}
				 	else
				 	{
        
                   			$("#PinRetrive").html(msg);
                   			scrollToBottom();
                   			$("#captchaImg").attr("src","captcha?"+new Date());
                   		    $("#captchaText").val('');
                   	}
                   
                })
                .always(function () {
                    //$('#sw-val-step-3').unmask();
                })
                .fail(function (data) {
                    if (data.responseCode)
                    {
                        //alert(data.responseCode);
                        var alertMessage=data.responseCode;
				var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			    scrollToBottom();
			    
                     }
                });


    }   //End of fetchApplicationInformation


	function validateMobileNumber(mobileNumber) {
    var mob = /^(013|014|015|016|017|018|019)[0-9]{8}$/;

	    if (mob.test(mobileNumber) == false) {
	      //alert("Invalid Mobile Number");
	      var alertMessage="Invalid Mobile Number";
				var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
	      document.getElementById("mobile").value = "";
	    }
	    
	  }
	  
	  	    function checkInput(ob) {
        var invalidChars = /[^0-9]/gi
        if(invalidChars.test(ob.value)) {
            ob.value = ob.value.replace(invalidChars,"");
        }
        
    }
    
    
function scrollToBottom(){
	$('html, body').animate({scrollTop:$(document).height()}, 'slow');
}
    
</script>
<div class="popupWindow" id="popupWindow">
	        <a href="" class="closeIIS">
	            <img src="/board/resources/_images/closeButton.png" class="closeIISButton" title="Close Window" alt="Close" width="24px" height="24px"/>
	        </a>
	        
	    </div>	
</body>
</html>