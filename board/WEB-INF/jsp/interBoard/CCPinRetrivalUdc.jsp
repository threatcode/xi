<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Security Code Retrieval</title>
<link rel="icon" type="image/x-icon" href="../resources/_images/favicon.ico"/>
<link rel="shortcut icon" type="image/x-icon" href="../resources/_images/favicon.ico" />
<link href="resources/css/applicant.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Belgrano'
	rel='stylesheet' type='text/css' />
<script charset="UTF-8" type="text/javascript" src="resources/_script/jquery.js"></script>
<link href="resources/_stylesheet/RequiredContentDesign.css" rel="stylesheet" type="text/css" />
		<script charset="UTF-8" type="text/javascript" src="resources/_script/mainContentAnimation.js"></script>
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
	content: '!';
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
							Applicant All Information</a></b> </font>
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
				<label>SSC Board</label> <label class="colonLabel"> : </label> <select
					style="width:156px;" id="ssc_board">
								     	<option value="none">Select Board</option>
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


				</select>
			</div>

			<div class="loginform_row">
				<label>Passing Year</label> <label class="colonLabel"> : </label> <select id="ssc_year"
					style="width:156px;">
					<option value="none">Select Year</option>
					<option value="2020" selected="selected">2020</option>
					<option value="2019">2019</option>
					<option value="2018">2018</option>
					<!--
					<option value="2017">2017</option>
					<option value="2016">2016</option>
					<option value="2015">2015</option>
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

			
			<center>
			<br>
				<button type="button" onclick="pinRetrival()" class="button"
					style="vertical-align:middle">
					<span>Submit</span>
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
        if($("#ssc_year").val()=="none"){
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

     $('#PinRetrive').prepend($('<img>',{id:'theImg',src:'resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "CCretrivedPinUdc",
            dataType: 'text',
            async   : false,
            data    : {ssc_roll: $("#ssc_roll").val().trim(),ssc_board: $("#ssc_board").val().trim(),
                       ssc_passing_year: $("#ssc_year").val().trim()}

        }).done(function (msg) {
        		$('#PinRetrive').html('');
        		
                   		$("#PinRetrive").html(msg);
                   		scrollToBottom();
                   		
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
                     }
                });


    }   //End of fetchApplicationInformation


	function validateMobileNumber(mobileNumber) {
    var mob = /^(016|019|011|017|018|015)[0-9]{8}$/;

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
	            <img src="resources/_images/closeButton.png" class="closeIISButton" title="Close Window" alt="Close" width="24px" height="24px"/>
	        </a>
	        
	    </div>	
</body>
</html>




