var arrYear = [2020,2019,2018];
var arrYearBOU = [2019,2018,2017,2016];
var arrBTEByear = [2020,2019,2018];

var testVar = "";
var currentlyRequestProcessing=0;

function clearSscInfo(){
	clearField("ssc_roll","user_captcha");
	if(document.getElementById("ssc_reg")){
		clearField("ssc_reg");
	}
	var select_fields=["ssc_year","ssc_board"];	
	setSBoxFirstOption.apply(this, select_fields);
	
}
function setSBoxFirstOption(){
	for (var i = 0; i < arguments.length; i++) {
		$("#"+arguments[i]).val(""); 	  
	  }
}

function clearField()
{	 
	for (var i = 0; i < arguments.length; i++) {
	    $("#"+arguments[i]).val("");
	  }
}
function enableField()
{
	for (var i = 0; i < arguments.length; i++) {
	    $("#"+arguments[i]).attr("disabled", false);
	  }
}
function scrollToBottom(){
	$('html, body').animate({scrollTop:$(document).height()}, 'slow');
}
function scrollToTop(){
	$('html, body').animate({scrollTop:0}, 'slow');
}
function clearHtml()
{	 
	for (var i = 0; i < arguments.length; i++) {
	    $("#"+arguments[i]).html("");
	  }
}
function validateField(){
	
	var isValid=true;
	var element;
	for (var i = 0; i < arguments.length; i++) {
		element=$("#"+arguments[i]);
	if(element && $.trim(element.val())==""){
		cbColor(element,"e");isValid=false;
	}
	else cbColor(element,"v");	  
	}
	return isValid;
}
function cbColor(element,type){
	
	if(type=="e")
		element.css("border", "2px solid red");
	else if(type=="v")
		element.css("border", "1px solid #add9e4");
}
function disableField()
{
	for (var i = 0; i < arguments.length; i++) {
	    $("#"+arguments[i]).attr("disabled", true);  
	  }
}
function myTrim(x) {
	if (typeof x != 'undefined')
    	return x.replace(/^\s+|\s+$/gm,'');
    else
    	return '';
}
function reloadYear(board_id){ 
	$('#ssc_year').children('option').remove();
	$('#ssc_year').append($("<option></option>")
        		   .attr("value","")
        		   .text("Select Year")); 
	var year=[];

    if(board_id==19)
    	year=arrBTEByear;
    else if(board_id==20)
    	year=arrYearBOU;
    else
    	year=arrYear;
	 
	 
	 for (var i=0; i<year.length; i++) {
	 	if(board_id==20)
	 	{
		 	if(year[i]==2019)
				$('#ssc_year')
		         	.append($("<option selected></option>")
		 			.attr("value",year[i])
		 			.text(year[i]));
		 	else
				$('#ssc_year')
		         	.append($("<option></option>")
		 			.attr("value",year[i])
		 			.text(year[i]));
	 	}
	 	else
	 	{
		 	if(year[i]==2020)
				$('#ssc_year')
		         	.append($("<option selected></option>")
		 			.attr("value",year[i])
		 			.text(year[i]));
		 	else
				$('#ssc_year')
		         	.append($("<option></option>")
		 			.attr("value",year[i])
		 			.text(year[i]));
	 	}
	}

}


function validateSscInfo(){
	 enableField("submitButton");
	 $("#submitButton").css('background-color', 'buttonface');
		
	 clearHtml("result_info_div");
	 var valid=validateField("ssc_roll","ssc_board","ssc_year","ssc_reg","user_captcha");
	 if(valid==true && !currentlyRequestProcessing)
	 {
		 //clearHtml("response_div");
		 //$("#response_div").html("<img src='/board/resources/images/loading1.gif' />").fadeToggle(100);
		 $("#response_div").fadeToggle(100);
		 currentlyRequestProcessing=1;
	 	 $.ajax({
			 	url: '/board/showResult',		
			 	type: 'POST',
			 	data: {ssc_roll:myTrim($("#ssc_roll").val()),ssc_board:$("#ssc_board").val(),ssc_year:$("#ssc_year").val(),ssc_reg:myTrim($("#ssc_reg").val()),
			 		user_captcha:myTrim($("#user_captcha").val()),java_captcha:$("#java_captcha").val()},
			 	success: function(data) {
			 		//$("#response_div").html("");
			 		$("#response_div").fadeToggle(18);
			 		//setTimeout(function() { clearCaptcha();}, 500);
			 		$("#captchaImg").addClass("reloadImage").attr("src", "/board/resources/_images/reloadCaptcha1.gif");
			 		clearField("user_captcha");
			 		//clearCaptcha();
				 	if(data=="Captcha Error")
				 	{
					 		//$("#response_div").html("").fadeToggle(18);
				 		    //$("#response_div").fadeToggle(18);
					 		$("#result_info_div").html(data);
					 	    scrollToBottom();
					 	   	currentlyRequestProcessing=0;
							$("#user_captcha").css("border", "2px solid red");
				 	}
				 	else
				 	{ 
				 		//$("#response_div").html("").fadeToggle(18);
				 		//$("#response_div").fadeToggle(18);
				 		$("#result_info_div").html(data);
						disableField("submitButton");
				 	    scrollToBottom();
				 	   	currentlyRequestProcessing=0;
				 	  } 	    
				 	},
				 	error: function(e) {
				 		$("#response_div").fadeToggle(18);
				 		clearCaptcha();
				 	}
		 		});
	 }
	 

}
function validateRegInfo(){
	 enableField("submitButton");
	 $("#submitButton").css('background-color', 'buttonface');
		
	 clearHtml("result_info_div");
	 var valid=validateField("ssc_roll","ssc_board","ssc_year","ssc_reg","user_captcha");
	 if(valid==true && !currentlyRequestProcessing)
	 {
		 //clearHtml("response_div");
		 //$("#response_div").html("<img src='/board/resources/images/loading1.gif' />").fadeToggle(100);
		 $("#response_div").fadeToggle(100);
		 currentlyRequestProcessing=1;
	 	 $.ajax({
			 	url: '/board/showRegPayment_2019',		
			 	type: 'POST',
			 	data: {ssc_roll:myTrim($("#ssc_roll").val()),ssc_board:$("#ssc_board").val(),ssc_year:$("#ssc_year").val(),ssc_reg:myTrim($("#ssc_reg").val()),
			 		user_captcha:myTrim($("#user_captcha").val()),java_captcha:$("#java_captcha").val()},
			 	success: function(data) {
			 		//$("#response_div").html("");
			 		$("#response_div").fadeToggle(18);
			 		//setTimeout(function() { clearCaptcha();}, 500);
			 		$("#captchaImg").addClass("reloadImage").attr("src", "/board/resources/_images/reloadCaptcha1.gif");
			 		clearField("user_captcha");
			 		//clearCaptcha();
				 	if(data=="Captcha Error")
				 	{
					 		//$("#response_div").html("").fadeToggle(18);
				 		    //$("#response_div").fadeToggle(18);
					 		$("#result_info_div").html(data);
					 	    scrollToBottom();
					 	   	currentlyRequestProcessing=0;
							$("#user_captcha").css("border", "2px solid red");
				 	}
				 	else
				 	{ 
				 		//$("#response_div").html("").fadeToggle(18);
				 		//$("#response_div").fadeToggle(18);
				 		$("#result_info_div").html(data);
						disableField("submitButton");
				 	    scrollToBottom();
				 	   	currentlyRequestProcessing=0;
				 	  } 	    
				 	},
				 	error: function(e) {
				 		$("#response_div").fadeToggle(18);
				 		clearCaptcha();
				 	}
		 		});
	 }
	 

}


function clearCaptcha()
{
	var d = new Date();
	$("#captchaImg").attr("src", "/board/captcha?"+d.getTime());
	clearField("user_captcha");
}
function clearButton()
{
	document.getElementById("finalResultDiv").style.visibility='hidden';
	enableField("submitButton");
	var d = new Date();
	$("#captchaImg").attr("src", "/board/captcha?"+d.getTime());
	clearField("ssc_roll","ssc_reg","ssc_board","ssc_year","user_captcha");
	scrollToTop();
	$("#result_info_div").html("");
}
$(document).ready(function(){
	$(document).on('click', '.reloadImage', function() {
		$(this).removeClass("reloadImage");
		clearCaptcha();
		  // Does some stuff and logs the event to the console
		});
});