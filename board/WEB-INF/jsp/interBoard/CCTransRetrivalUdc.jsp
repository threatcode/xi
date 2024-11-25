<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Payment Info</title>
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
							Payment Information</a></b> </font>
			</div>
		</div>
		<hr />
		<div class="loginform">

			<div class="loginform_row">
				<label>Transaction ID </label> <label class="colonLabel"> : </label> <input type="text"
					id="trans_id" style="width:150px;" 
					class="loginform_input" required />
			</div>
			

			<div class="loginform_row">
				<label>Operator</label> <label class="colonLabel"> : </label> <select
					style="width:156px;" id="operator">
				    <option value="none" selected="selected">Select Operator</option>
				    <option value="BKash">BKash</option>
					<option value="NAGAD">Nagad</option>
					<option value="Rocket">Rocket</option>
					<option value="SBL">Sonali Bank</option>
					<option value="SC">SureCash</option>
					<option value="TT" >TeleTalk</option>
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
   

       if($("#trans_id").val()==""){
            //alert("Give Your SSC Roll.");
            var alertMessage="Give Transaction ID.";
				var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			    return;
        }
        
        
       if($("#operator").val()=="none"){
            //alert("Select Your SSC Board.");
            var alertMessage="Select Operator name.";
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
            url     : "CCretrivedTransUdc",
            dataType: 'text',
            async   : false,
            data    : {trans_id: $("#trans_id").val().trim(),operator: $("#operator").val().trim()}

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




