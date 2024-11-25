var loadTimeChoices=[];
var submitTimeChoices=[];
var submitTimePriorities=[];

$(document).ready(function(){
	$(document).on('click', '.submitMigration', function(event)
		{
			//alert($(this)
			event.stopPropagation();
			event.preventDefault();
			submitMigrationNew();
			return false;
		});
		$(document).on('click', '.infoPopUpButton.submitMigration.disabledMigrationSubmitButton', function(event)
		{
			event.stopPropagation();
			event.preventDefault();
			return false;
		});
		$(document).on('click', 'img.closeIISButton', function(event)
		{
		  	event.preventDefault();
		  	removePopWindowMask();
		});
		$(document).on('click', '.loginMigration', function(event)
		{
			//alert($(this)
			if(validateUserInfo()==false)
			{
				event.stopPropagation();
				event.preventDefault();
				return false;
			}
			else
			{
				return true;
			}
		});
	//alert("login");	
	setTimeout(function() { saveLoadTimeSelections(); }, 100);
});



function showPopUpWindowWithMask(htmlElement, popUpWindowWidth, popUpWindowHeight)
{
	$(htmlElement).insertAfter('.closeIIS');
	$('.closeIISButton').attr('style', '');
	var cssChangeObj=
	{
		"height" : popUpWindowHeight+"px",
		"width"	 : popUpWindowWidth+"px"
	}
	$('div#CollegeDetailsPopUpContainerDiv').css(cssChangeObj);
	$('.popupWindow').css(cssChangeObj);
	var currentScrollPosition=$(document).scrollTop();
	var leftD=Math.ceil(($(window).width()/2)-((popUpWindowWidth+24)/2));
	var topD=Math.ceil(($(window).height()/2)-((popUpWindowHeight+24)/2));
	if(leftD<0)
		leftD=0;
	if(topD<0)
		topD=0;
	leftD=leftD+"px";
	topD=topD+"px";
	$(".popupWindow").css("top", topD);
	$(".popupWindow").css("left", leftD);
	$('.popupWindow').fadeIn(600);
	$('body').append('<div id="mask"></div>');
	$('#mask').fadeIn(300);
	//$('body').scrollTop( currentScrollPosition );

}
$(document).keyup(function(e)
{
  if (e.keyCode == 27) 
  {
  	 if($('#mask').length > 0)
	 {
	 	removePopWindowMask();
							
	 }
  }
});

function removePopWindowMask()
{
	$('#mask , .popupWindow').fadeOut(500 , function() {
															//window.alert($(this).html());
															$('#mask').hide(); 
															$('#CollegeDetailsPopUpContainerDiv').hide();
													});
}

/* New Function written May 09, 2017 */
function removePopWindowMaskN()
{
	$('#mask , .popupWindow').fadeOut(500 , function() {
															//window.alert($(this).html());
															$('#mask').hide(); 
															$('#CollegeDetailsPopUpContainerDiv').hide().remove();
													});
}

function uniqueArraySort(inputArray)
{
    var outputArray = [];
    
    for (var i = 0; i < inputArray.length; i++)
    {
        if ((jQuery.inArray(inputArray[i], outputArray)) == -1)
        {
            outputArray.push(inputArray[i]);
        }
    }
    outputArray.sort();
    return outputArray;
}
function removePopWindowMaskWA()
{
	$('#mask , .popupWindow').fadeOut(0 , function() {
															//window.alert($(this).html());
															$('#mask').hide(); 
															$('#CollegeDetailsPopUpContainerDiv').hide();
													});
}

/* New Function written May 09, 2017 */
function removePopWindowMaskWAN()
{
	$('.popupWindow').hide();
															//window.alert($(this).html());
	$('#mask').hide().remove(); 
															$('#CollegeDetailsPopUpContainerDiv').remove();
												
}

function sortNumber(a,b) {
	return a - b;
}


function isGivenPriorityOkSS(testingPriorities)
{
	testingPriorities.sort(sortNumber);
	//console.log(testingPriorities);
	for(var i=0;i<testingPriorities.length;i=i+1)
	{
		if(testingPriorities[i]!=i+1)
		{
			return false
		}
	}				
	return true;				
}

function isInGivenArray(currentElement, searchArray)
{
	for(var i=0;i<searchArray.length;i=i+1)
	{
		if(searchArray[i]==currentElement)
		{
			return true;
		}
	}				
	return false;				
}

function saveLoadTimeSelections()
{
	
	var i=0;
		loadTimeChoices=[];
	if($("#autoMigrationY").prop('checked')==true)
	{
		loadTimeChoices.push([]);
		loadTimeChoices[i][0]="333333";
		loadTimeChoices[i][1]=1;
		i++;
	}
	else if($("#autoMigrationN").prop('checked')==true)
	{
		loadTimeChoices.push([]);
		loadTimeChoices[i][0]="333333";
		loadTimeChoices[i][1]=0;
		i++;
	}
	$('#selection_row_table').find('tr').each(function (rowIndex, r) 
	{
		//console.log($(this).find('td:eq(7)').children('input').val());
		var priority = $(this).find('td:eq(7)').children('input').val()
		var tempRowData ='';
		$(this).find('td').each(function (colIndex, c) 
		{
			if(colIndex==2)
			{
				loadTimeChoices.push([]);
				loadTimeChoices[i][0]=c.textContent;
			}
			else if(colIndex==7)
			{
				loadTimeChoices[i][1]=priority;
				i++;
			}			
			
		});
		
			
	});
	
	//alert(loadTimeChoices.toString());
}

function saveSubmitTimeSelections()
{
	
	var i=0;
	var j=0;
		submitTimeChoices=[];
	if($("#autoMigrationY").prop('checked')==true)
	{
		submitTimeChoices.push([]);
		submitTimeChoices[i][0]="333333";
		submitTimeChoices[i][1]=1;
		i++;
	}
	else if($("#autoMigrationN").prop('checked')==true)
	{
		submitTimeChoices.push([]);
		submitTimeChoices[i][0]="333333";
		submitTimeChoices[i][1]=0;
		i++;
	}
	$('#selection_row_table').find('tr').each(function (rowIndex, r) 
	{
		//console.log($(this).find('td:eq(7)').children('input').val());
		var priority = $(this).find('td:eq(7)').children('input').val()
		var tempRowData ='';
		$(this).find('td').each(function (colIndex, c) 
		{
			if(colIndex==2)
			{
				submitTimeChoices.push([]);
				submitTimeChoices[i][0]=c.textContent;
			}
			else if(colIndex==7)
			{
				submitTimeChoices[i][1]=priority;
				submitTimePriorities[j]=priority;
				i++;
				j++;
			}			
			
		});
		
			
	});
	
	//alert(submitTimeChoices.toString());
}


function compareTwo2DIntArrays(array1, array2)
{
	if(array1.length!=array2.length)
	{
		return 0;
	}
	else
	{
		for(i=0; i<array1.length; i++)
		{
			if(array1[i].length!=array2[i].length)
			{
				return 0;
			}
			else
			{
				for(j=0; j<array1[i].length; j++)
				{
					if(parseInt(array1[i][j])!=parseInt(array2[i][j]))
					{	
						return 0;
					}
				}
			}
		}
	}
	return 1;
}

function convertNumberToEnglishNumber(inputNumber)
{
	//alert(inputNumber);
	var outputNumber="";
	for(var i=0; i<inputNumber.length; i++)
	{
		if(inputNumber[i]>='0' && inputNumber[i]<='9')
		{
			outputNumber+=inputNumber[i];
		}
		else if(banglaNumbersToEnglishNumber.hasOwnProperty(inputNumber[i]))
		{
			outputNumber+=banglaNumbersToEnglishNumber[inputNumber[i]];
		}
		else
		{
			outputNumber+=inputNumber[i];
		}
	}
	//alert(outputNumber);
	return outputNumber;
}
function validateUserInfo(){
 	//alert($("#user_captcha").val().trim());
	 if($("#user_captcha").val().trim()=='')
	 {
	 	var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+"&#2470;&#2479;&#2492;&#2494; &#2453;&#2480;&#2503; &#2479;&#2494;&#2458;&#2494;&#2439;&#2453;&#2480;&#2467;&#2503;&#2480; &#2460;&#2472;&#2509;&#2479; &#2453;&#2509;&#2479;&#2494;&#2474;&#2458;&#2494; &#2463;&#2503;&#2453;&#2509;&#2488;&#2463; &#2482;&#2495;&#2454;&#2497;&#2472;<br/><br/>Please enter the Captcha text for verification"+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
	 	//alert('Please enter the Captcha text as verification');
	 	return false;
	 }
		
	 clearHtml("response_div", "personal_info_div");
	 $("#response_div").hide();
	 
	 var currentMobileNumber=$("#mobile").val();
	 var currentSecurityCode=$("#scode").val();		
	 var valid=checkIsEmpty("ssc_roll","ssc_board","ssc_year","ssc_reg","user_captcha","mobile","scode");
	 
	 if(valid && !validateMobileNumber(currentMobileNumber))
	 {
		 var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+"&#2438;&#2474;&#2472;&#2494;&#2480; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2478;&#2507;&#2476;&#2494;&#2439;&#2482; &#2472;&#2478;&#2509;&#2476;&#2480;&#2463;&#2495; &#2488;&#2464;&#2495;&#2453; &#2472;&#2527;&#2404; &#2474;&#2509;&#2480;&#2479;&#2492;&#2507;&#2460;&#2472;&#2496;&#2479;&#2492; &#2437;&#2480;&#2509;&#2469; &#2474;&#2480;&#2495;&#2486;&#2507;&#2471; &#2453;&#2480;&#2494;&#2480; &#2488;&#2478;&#2527; &#2438;&#2474;&#2472;&#2495; &#2438;&#2474;&#2472;&#2494;&#2480; &#2479;&#2503; Contact &#2478;&#2507;&#2476;&#2494;&#2439;&#2482; &#2472;&#2478;&#2509;&#2476;&#2480; &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480;&#2503;&#2459;&#2503;&#2472; &#2488;&#2503;&#2463;&#2495; &#2488;&#2494;&#2476;&#2471;&#2494;&#2472;&#2503; &#2474;&#2498;&#2480;&#2467; &#2453;&#2480;&#2497;&#2472;&#2404;"+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
				valid=false;
	 	//alert('Please enter the Captcha text as verification');
	 	return false;
	 }
	 if(valid && !isAValidSecurityCode(currentSecurityCode))
	 {
		 var popUpWindowHeight= 240;
				var popUpWindowWidth=300;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+"&#2438;&#2474;&#2472;&#2494;&#2480; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2488;&#2495;&#2453;&#2495;&#2441;&#2480;&#2495;&#2463;&#2495; &#2453;&#2507;&#2465;&#2463;&#2495; &#2488;&#2464;&#2495;&#2453; &#2472;&#2527;&#2404; &#2470;&#2527;&#2494; &#2453;&#2480;&#2503; &#2488;&#2464;&#2495;&#2453; &#2488;&#2495;&#2453;&#2495;&#2441;&#2480;&#2495;&#2463;&#2495; &#2453;&#2507;&#2465; &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480;&#2497;&#2472;&#2404;"+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
	 	//alert('Please enter the Captcha text as verification');
		valid=false;
	 	return false;
	 }
	 //&validateMobileNumber(currentMobileNumber)&isAValidSecurityCode(currentSecurityCode);

	 if(valid==true)
	 {
		 $("#response_div").html("<img src='/board/resources/images/239.gif' />");
		 $("#response_div").show();
		 return true;
	 }
	 else
		 return false;
	 
	 scrollToBottom();
}

function checkIsEmpty()
{
	
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

function validateMobileNumber(mobileNumber) 
{
    var mob = /^(013|014|015|016|017|018|019)[0-9]{8}$/;

    if (mob.test(mobileNumber) == false) {
      return false;
    }
    return true;
}
function isNumberWithoutSign(inputNumber)
{
	if(inputNumber.length<1)
	{
		return false;
	}
	for(i=0; i<inputNumber.length; i++)
	{
		if(inputNumber[i]<'0' || inputNumber[i]>'9')
		{
			return false;
		}
	}
	return true;
}
function isAValidSecurityCode(inputNumber)
{
	if(inputNumber.length!=6)
	{
		return false;
	}
	else
	{
		return isNumberWithoutSign(inputNumber);
	}
}

function cbColor(element,type){
	
	if(type=="e")
		element.css("border", "2px solid red");
	else if(type=="v")
		element.css("border", "1px solid #add9e4");
}
function clearHtml()
{	 
	for (var i = 0; i < arguments.length; i++) {
	    $("#"+arguments[i]).html("");
	  }
}

