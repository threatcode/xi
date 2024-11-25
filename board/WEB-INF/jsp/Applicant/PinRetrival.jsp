<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Security Code Retrieval</title>
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
							Security Code Recovery for Applicants</a></b> </font>
			</div>
		</div>
		<hr />
		<div class="loginform">

			<div class="loginform_row">
				<label>SSC Roll </label> <label class="colonLabel"> : </label> <input type="text"
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
					style="width:156px;" id="ssc_board"  onchange="reloadYear(this.value)">
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
			<label>Mother's Name </label> <label class="colonLabel"> : </label> <input type="text"
				id="mname" style="width:150px;" 
			    class="loginform_input" required />
			    
			</div>
			
			<div class="loginform_row">
			<label>Transaction No.</label> <label class="colonLabel"> : </label> <input type="text"
				id="trn_no" style="width:150px;" 
			    class="loginform_input" required />
			    
			</div>
			

			<div class="loginform_row">
				<label> <font color="red">*</font> Contact No. </label> <label class="colonLabel"> : </label> <input
					type="text" maxlength="11" style="width:150px;"
					onkeyup="checkInput(this)" id="mobile" class="loginform_input"
					onchange="validateMobileNumber(this.value)" required />
					
					
					<div class="NB_row"> 
			<p style="color: blue;font-weight: bold;">			
			 <font color="red"> (*) </font>&#x985;&#x9A8;&#x9B2;&#x9BE;&#x987;&#x9A8;&#x9C7;&#x20;&#x986;&#x9AC;&#x9C7;&#x9A6;&#x9A8;&#x9C7;&#x9B0;&#x20;&#x9B8;&#x9AE;&#x9DF;&#x20;&#x9AF;&#x9C7;&#x20;&#x43;&#x6F;&#x6E;&#x74;&#x61;&#x63;&#x74;&#x20;&#x4E;&#x75;&#x6D;&#x62;&#x65;&#x72;&#x20;&#x9AC;&#x9CD;&#x9AF;&#x9AC;&#x9B9;&#x9C3;&#x9A4;&#x20;&#x9B9;&#x9DF;&#x9C7;&#x99B;&#x9C7;&#x20;&#x964;
			</p>
			</div>
					
			</div>
			
			<div class="loginform_row">
			<label>Verification Code </label> <label class="colonLabel"> : </label>
			<img id="captchaImg" src="captcha" alt="Captcha Image" height="30">
			<input type="text" id="captchaText" name="captchaText" style="width: 70px;text-align: center;"/>	 
			</div>
			
			<div class="NB_row">
					<label style="color: red;font-weight: bold;">
					&#x989;&#x9B2;&#x9CD;&#x9B2;&#x9C7;&#x996;&#x9CD;&#x9AF;&#x20;&#x9AF;&#x9C7;&#x2C;&#x20;&#x9B6;&#x9C1;&#x9A7;&#x9C1;&#x20;&#x9AE;&#x9BE;&#x9A4;&#x9CD;&#x9B0;&#x20;&#x98F;&#x995;&#x9AC;&#x9BE;&#x9B0;&#x987;&#x20;&#x53;&#x65;&#x63;&#x75;&#x72;&#x69;&#x74;&#x79;&#x20;&#x43;&#x6F;&#x64;&#x65;&#x20;&#x9AA;&#x9C1;&#x9A8;&#x983;&#x9B0;&#x9C1;&#x9A6;&#x9CD;&#x9A7;&#x9BE;&#x9B0;&#x20;&#x995;&#x9B0;&#x9BE;&#x20;&#x9AF;&#x9BE;&#x9AC;&#x9C7;&#x964;	
					</label>					 
			</div>
			

			<center>
			<br>
				<button type="button" onclick="pinRetrival()" class="button"
					style="vertical-align:middle">
					<span>Submit </span>
				</button>
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
            //alert("Give Your SSC Roll.");
            var alertMessage="Give Your SSC Roll.";
				var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			    return;
        }
        if($("#ssc_reg").val()==""){
            //alert("Give Your SSC Registration No.");
            var alertMessage="Give Your SSC Registration No.";
				var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			    return;
        }
        
       if($("#ssc_board").val()=="none"){
            //alert("Select Your SSC Board.");
            var alertMessage="Select Your SSC Board.";
				var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			    return;
        }
        if($("#ssc_year").val()==""){
            //alert("Select Your SSC Passing Year.");
            var alertMessage="Select Your SSC Passing Year.";
				var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			    return;
        }
        
         if($("#mname").val()==""){
            //alert("Give your previously given Mobile Number.");
            var alertMessage="Give your Mother's Name.";
				var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			    return;
        } 
        
         if($("#trn_no").val()==""){
            //alert("Give your previously given Mobile Number.");
            var alertMessage="Give your Transaction No.";
				var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			    return;
        } 
        
        

       if($("#mobile").val()==""){
            //alert("Give your previously given Mobile Number.");
            var alertMessage="Give your previously given Mobile Number.";
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
            url     : "retrivedPin",
            dataType: 'text',
            async   : false,
            data    : {mname:$("#mname").val().trim() ,trn_no:$("#trn_no").val().trim() ,ssc_roll: $("#ssc_roll").val().trim(),ssc_reg: $("#ssc_reg").val().trim(),ssc_board: $("#ssc_board").val().trim(),
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
							//$("#captchaText").css("border", "2px solid red");
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
                    //location.reload(true);
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
</script>
<div class="popupWindow" id="popupWindow">
	        <a href="" class="closeIIS">
	            <img src="/board/resources/_images/closeButton.png" class="closeIISButton" title="Close Window" alt="Close" width="24px" height="24px"/>
	        </a>
	        
	    </div>	
</body>
</html>