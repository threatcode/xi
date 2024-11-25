<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>

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

<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<div class="toogle_wrap">
				<div class="toggle_container">
					<p>
						<font style="color: black; font-size:20px;">Pin Retrival</font>				
					</p>
				</div>				
			</div>
		</div>
	<div id="loginpanelwrap">

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
		
	<!---- body ----->			
	</div>


	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp" %>
</div>


<%@ include file="/WEB-INF/jsp/template/footerLite.jsp" %>

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

     $('#PinRetrive').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "CCretrivedPin",
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


    }

</script>


</body>
</html>