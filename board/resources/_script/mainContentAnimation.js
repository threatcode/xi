var numbers = ["", "One", "Two", "Three", "Four", "Five", "Six", "Seven"];
var numbersb = ["", "&#2535;", "&#2536;", "&#2537;", "&#2538;", "&#2539;", "&#2540;", "&#2541;"];
var numbersbf = ["", "&#2447;&#2453;", "&#2470;&#2497;&#2439;", "&#2468;&#2495;&#2472;", "&#2458;&#2494;&#2480;", "&#2474;&#2494;&#2433;&#2458;", "&#2459;&#2479;&#2492;", "&#2488;&#2494;&#2468;"];
var numbersj = ["Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty", "Twenty one", "Twenty two", "Twenty three", "Twenty four", "Twenty five"];
var numbersbfj = ["&#2486;&#2498;&#2472;&#2509;&#2479;", "&#2447;&#2453;", "&#2470;&#2497;&#2439;", "&#2468;&#2495;&#2472;", "&#2458;&#2494;&#2480;", "&#2474;&#2494;&#2433;&#2458;", "&#2459;&#2479;&#2492;", "&#2488;&#2494;&#2468;", "&#2438;&#2463;", "&#2472;&#2479;&#2492;", "&#2470;&#2486;", "&#2447;&#2455;&#2494;&#2480;", "&#2476;&#2494;&#2480;", "&#2468;&#2503;&#2480;", "&#2458;&#2508;&#2470;&#2509;&#2470;", "&#2474;&#2472;&#2503;&#2480;", "&#2487;&#2507;&#2482;", "&#2488;&#2468;&#2503;&#2480;&#2507;", "&#2438;&#2464;&#2494;&#2480;", "&#2441;&#2472;&#2495;&#2486;", "&#2476;&#2495;&#2486;", "&#2447;&#2453;&#2497;&#2486;", "&#2476;&#2494;&#2439;&#2486;", "&#2468;&#2503;&#2439;&#2486;", "&#2458;&#2476;&#2509;&#2476;&#2495;&#2486;", "&#2474;&#2433;&#2458;&#2495;&#2486;"];

$(document).ready(function(){
		setErrorPopupMargin();
		$(document).on('click', '.afterApplicationComplainCheckbox', function(event)
		{
			//alert($(this)
			$(this).closest('.afterApplicationComplain').find('.afterApplicationComplainDetails').fadeToggle(400);
		});
		$(document).on('keypress', '.inputDisabledSak', function(event)
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
		$(document).on('click', '.closeSubmitPreview', function(event)
		{
			event.preventDefault();
			removePopWindowMask();
			return false;
		});
		$(document).on('click', '.continueToSubmitSelection', function(event)
		{
			event.preventDefault();
			removePopWindowMaskWA();
			submitApplication_TT();
			return false;
		});
		$(document).on('click', '.continueToSubmitEditSelection', function(event)
		{
			//joy
			//alert("edit app");
			event.preventDefault();
			removePopWindowMaskWA();
			submitApplicationEdit_TT();
			return false;
		});
		
		$(document).on('click', '.infoPopUpButton.submitComplainAp.disabledComplainSubmitButton', function(event)
		{
			event.preventDefault();
			return false;
		});
		$(document).on('click', '.infoPopUpButton.submitComplainAp', function(event)
		{
			
			var afterApplicationComplainContactNo=$( "#afterApplicationComplainContactNo" ).val();
			afterApplicationComplainContactNo=convertNumberToEnglishNumber(afterApplicationComplainContactNo);
			//alert(afterApplicationComplainContactNo);
			var isPreferenceErrorChecked=$("[name='afterApplicationComplain1']").prop('checked');
			var preferenceErrorDetails=$("[name='afterApplicationComplainDetails1']").val();
			var isMobileErrorChecked=$("[name='afterApplicationComplain2']").prop('checked');
			var mobileErrorDetails=$("[name='afterApplicationComplainDetails2']").val();
			mobileErrorDetails=convertNumberToEnglishNumber(mobileErrorDetails);
			var isGenderErrorChecked=$("[name='afterApplicationComplain3']").prop('checked');
			var isOtherErrorChecked=$("[name='afterApplicationComplain4']").prop('checked');
			var otherErrorDetails=$("[name='afterApplicationComplainDetails4']").val();
			//alert(preferenceErrorDetails);
			if(!isPreferenceErrorChecked && !isMobileErrorChecked && !isGenderErrorChecked && !isOtherErrorChecked)
			{
				var popUpWindowHeight= 300;
				var popUpWindowWidth=400;
				var alertMessage="&#2470;&#2527;&#2494; &#2453;&#2480;&#2503; &#2472;&#2498;&#2472;&#2509;&#2479;&#2468;&#2478; &#2447;&#2453;&#2463;&#2495; &#2488;&#2478;&#2488;&#2509;&#2479;&#2494; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; &#2453;&#2480;&#2497;&#2472;";
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			else if(preferenceErrorDetails.length>512 || otherErrorDetails.length>512)
			{
				var popUpWindowHeight= 300;
				var popUpWindowWidth=400;
				var alertMessage="&#2455;&#2480;&#2478;&#2495;&#2482;&#2503;&#2480; &#2476;&#2480;&#2509;&#2467;&#2472;&#2494; &#2539;&#2535;&#2536; &#2437;&#2453;&#2509;&#2487;&#2480; &#2469;&#2503;&#2453;&#2503; &#2476;&#2524; &#2489;&#2468;&#2503; &#2474;&#2494;&#2480;&#2476;&#2503; &#2472;&#2494;&#2404; ";
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			else if(isPreferenceErrorChecked && preferenceErrorDetails=="")
			{
				var popUpWindowHeight= 300;
				var popUpWindowWidth=400;
				var alertMessage="&#2468;&#2497;&#2478;&#2495; &#2441;&#2482;&#2509;&#2482;&#2503;&#2454; &#2453;&#2480;&#2503;&#2459; &#2479;&#2503; &#2468;&#2507;&#2478;&#2494;&#2480; &#2453;&#2482;&#2503;&#2460;&#2503;&#2480; &#2474;&#2459;&#2472;&#2509;&#2470;&#2453;&#2509;&#2480;&#2478;&#2503; &#2468;&#2507;&#2478;&#2494;&#2480; &#2437;&#2460;&#2494;&#2472;&#2509;&#2468;&#2503; &#2453;&#2507;&#2472; &#2474;&#2480;&#2495;&#2476;&#2480;&#2509;&#2468;&#2472; &#2453;&#2480;&#2494; &#2489;&#2527;&#2503;&#2459;&#2503;&#2404; &#2453;&#2495;&#2472;&#2509;&#2468;&#2497; &#2468;&#2497;&#2478;&#2495; &#2468;&#2507;&#2478;&#2494;&#2480; &#2455;&#2480;&#2478;&#2495;&#2482;&#2503;&#2480; &#2453;&#2507;&#2472; &#2476;&#2480;&#2509;&#2467;&#2472;&#2494; &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480; &#2472;&#2495;&#2404; &#2470;&#2527;&#2494; &#2453;&#2480;&#2503; &#2441;&#2482;&#2509;&#2482;&#2503;&#2454;&#2495;&#2468; &#2455;&#2480;&#2478;&#2495;&#2482;&#2503;&#2480; &#2447;&#2453;&#2463;&#2495; &#2476;&#2480;&#2509;&#2467;&#2472;&#2494; &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480;&#2404; ";
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
				
			}
			else if(isMobileErrorChecked && mobileErrorDetails=="")
			{
				var popUpWindowHeight= 300;
				var popUpWindowWidth=400;
				var alertMessage="&#2468;&#2497;&#2478;&#2495; &#2441;&#2482;&#2509;&#2482;&#2503;&#2454; &#2453;&#2480;&#2503;&#2459; &#2479;&#2503; &#2468;&#2507;&#2478;&#2494;&#2480; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2478;&#2507;&#2476;&#2494;&#2439;&#2482; &#2472;&#2478;&#2509;&#2476;&#2480;&#2463;&#2495; &#2488;&#2464;&#2495;&#2453; &#2472;&#2527;&#2404;&#2404; &#2453;&#2495;&#2472;&#2509;&#2468;&#2497; &#2468;&#2497;&#2478;&#2495; &#2468;&#2507;&#2478;&#2494;&#2480; &#2488;&#2464;&#2495;&#2453; &#2453;&#2507;&#2472; &#2478;&#2507;&#2476;&#2494;&#2439;&#2482; &#2472;&#2478;&#2509;&#2476;&#2480; &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480; &#2472;&#2495;&#2404; &#2470;&#2527;&#2494; &#2453;&#2480;&#2503; &#2468;&#2497;&#2478;&#2495; &#2468;&#2507;&#2478;&#2494;&#2480; &#2488;&#2464;&#2495;&#2453; &#2447;&#2453;&#2463;&#2495; &#2478;&#2507;&#2476;&#2494;&#2439;&#2482; &#2472;&#2478;&#2509;&#2476;&#2480; &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480;&#2404;";
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
				
			}
			else if(isMobileErrorChecked && !validateMobileNumber(mobileErrorDetails))
			{
				var popUpWindowHeight= 310;
				var popUpWindowWidth=410;
				var alertMessage="&#2468;&#2497;&#2478;&#2495; &#2441;&#2482;&#2509;&#2482;&#2503;&#2454; &#2453;&#2480;&#2503;&#2459; &#2479;&#2503; &#2468;&#2507;&#2478;&#2494;&#2480; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2478;&#2507;&#2476;&#2494;&#2439;&#2482; &#2472;&#2478;&#2509;&#2476;&#2480;&#2463;&#2495; &#2488;&#2464;&#2495;&#2453; &#2472;&#2527;&#2404;&#2404; &#2453;&#2495;&#2472;&#2509;&#2468;&#2497; &#2468;&#2497;&#2478;&#2495; &#2468;&#2507;&#2478;&#2494;&#2480; &#2488;&#2464;&#2495;&#2453; &#2479;&#2503; &#2478;&#2507;&#2476;&#2494;&#2439;&#2482; &#2472;&#2478;&#2509;&#2476;&#2480;&#2463;&#2495; &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480;&#2503;&#2459; &#2468;&#2494; &#2464;&#2495;&#2453; &#2472;&#2527;&#2404; &#2470;&#2527;&#2494; &#2453;&#2480;&#2503; &#2468;&#2497;&#2478;&#2495; &#2468;&#2507;&#2478;&#2494;&#2480; &#2488;&#2464;&#2495;&#2453; &#2447;&#2453;&#2463;&#2495; &#2478;&#2507;&#2476;&#2494;&#2439;&#2482; &#2472;&#2478;&#2509;&#2476;&#2480; &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480;&#2404;";
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
				
			}
			else if(isOtherErrorChecked && otherErrorDetails=="")
			{
				var popUpWindowHeight= 310;
				var popUpWindowWidth=410;
				var alertMessage="&#2468;&#2497;&#2478;&#2495; &#2441;&#2482;&#2509;&#2482;&#2503;&#2454; &#2453;&#2480;&#2503;&#2459; &#2479;&#2503; &#2468;&#2507;&#2478;&#2494;&#2480; &#2437;&#2509;&#2479;&#2494;&#2474;&#2509;&#2482;&#2495;&#2453;&#2503;&#2486;&#2472;-&#2447; &#2437;&#2472;&#2509;&#2479;&#2494;&#2472;&#2509;&#2479; &#2479;&#2503;&#2453;&#2507;&#2472; &#2455;&#2480;&#2478;&#2495;&#2482; &#2480;&#2527;&#2503;&#2459;&#2503;&#2404; &#2453;&#2495;&#2472;&#2509;&#2468;&#2497; &#2468;&#2497;&#2478;&#2495; &#2468;&#2507;&#2478;&#2494;&#2480; &#2455;&#2480;&#2478;&#2495;&#2482;&#2503;&#2480; &#2453;&#2507;&#2472; &#2476;&#2480;&#2509;&#2467;&#2472;&#2494; &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480; &#2472;&#2495;&#2404; &#2470;&#2527;&#2494; &#2453;&#2480;&#2503; &#2441;&#2482;&#2509;&#2482;&#2503;&#2454;&#2495;&#2468; &#2455;&#2480;&#2478;&#2495;&#2482;&#2503;&#2480; &#2447;&#2453;&#2463;&#2495; &#2476;&#2480;&#2509;&#2467;&#2472;&#2494; &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480;&#2404; ";
				
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
				
			}
			else if(afterApplicationComplainContactNo==null || afterApplicationComplainContactNo=="")
			{
				var popUpWindowHeight= 310;
				var popUpWindowWidth=410;
				var alertMessage="&#2468;&#2497;&#2478;&#2495; &#2468;&#2507;&#2478;&#2494;&#2480; Contact &#2472;&#2478;&#2509;&#2476;&#2480;&#2463;&#2495; &#2474;&#2498;&#2480;&#2467; &#2453;&#2480; &#2472;&#2495;&#2404; &#2474;&#2509;&#2480;&#2479;&#2492;&#2507;&#2460;&#2472;&#2496;&#2479;&#2492; &#2437;&#2480;&#2509;&#2469; &#2474;&#2480;&#2495;&#2486;&#2507;&#2471; &#2453;&#2480;&#2494;&#2480; &#2488;&#2478;&#2527; &#2468;&#2497;&#2478;&#2495; &#2468;&#2507;&#2478;&#2494;&#2480; &#2479;&#2503; Contact &#2478;&#2507;&#2476;&#2494;&#2439;&#2482; &#2472;&#2478;&#2509;&#2476;&#2480; &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480;&#2503;&#2459; &#2488;&#2503;&#2463;&#2495; &#2488;&#2494;&#2476;&#2471;&#2494;&#2472;&#2503; &#2474;&#2498;&#2480;&#2467; &#2453;&#2480;&#2404; ";
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			else if(!validateMobileNumber(afterApplicationComplainContactNo))
			{
				var popUpWindowHeight= 310;
				var popUpWindowWidth=410;
				var alertMessage="&#2468;&#2507;&#2478;&#2494;&#2480; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; Contact &#2472;&#2478;&#2509;&#2476;&#2480;&#2463;&#2495; &#2488;&#2464;&#2495;&#2453; &#2472;&#2527;&#2404; &#2474;&#2509;&#2480;&#2479;&#2492;&#2507;&#2460;&#2472;&#2496;&#2479;&#2492; &#2437;&#2480;&#2509;&#2469; &#2474;&#2480;&#2495;&#2486;&#2507;&#2471; &#2453;&#2480;&#2494;&#2480; &#2488;&#2478;&#2527; &#2468;&#2497;&#2478;&#2495; &#2468;&#2507;&#2478;&#2494;&#2480; &#2479;&#2503; Contact &#2478;&#2507;&#2476;&#2494;&#2439;&#2482; &#2472;&#2478;&#2509;&#2476;&#2480; &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480;&#2503;&#2459; &#2488;&#2503;&#2463;&#2495; &#2488;&#2494;&#2476;&#2471;&#2494;&#2472;&#2503; &#2474;&#2498;&#2480;&#2467; &#2453;&#2480;&#2404;";
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			else
			{
				if(isPreferenceErrorChecked)
					isPreferenceErrorChecked="à¦•à¦²à§‡à¦œà§‡à¦° à¦ªà¦›à¦¨à§�à¦¦à¦•à§�à¦°à¦®à§‡ à¦ªà§�à¦°à¦¾à¦°à§�à¦¥à§€à¦° à¦…à¦œà¦¾à¦¨à§�à¦¤à§‡ à¦•à§‹à¦¨ à¦ªà¦°à¦¿à¦¬à¦°à§�à¦¤à¦¨ à¦•à¦°à¦¾ à¦¹à§Ÿà§‡à¦›à§‡à¥¤";
				else
					isPreferenceErrorChecked="";
				if(isMobileErrorChecked)
					isMobileErrorChecked="à¦ªà§�à¦°à¦¾à¦°à§�à¦¥à§€ à¦ªà§�à¦°à¦¦à¦¤à§�à¦¤ à¦®à§‹à¦¬à¦¾à¦‡à¦² à¦¨à¦®à§�à¦¬à¦°à¦Ÿà¦¿ à¦¸à¦ à¦¿à¦• à¦¨à§Ÿà¥¤";
				else
					isMobileErrorChecked="";
				if(isGenderErrorChecked)
					isGenderErrorChecked="à¦¬à§‹à¦°à§�à¦¡ à¦ªà§�à¦°à¦¦à¦¤à§�à¦¤ Gender à¦¤à¦¥à§�à¦¯à§‡ à¦—à¦°à¦®à¦¿à¦² à¦†à¦›à§‡à¥¤";
				else
					isGenderErrorChecked="";
				if(isOtherErrorChecked)
					isOtherErrorChecked="à¦…à¦¨à§�à¦¯à¦¾à¦¨à§�à¦¯ à¦¯à§‡à¦•à§‹à¦¨ à¦—à¦°à¦®à¦¿à¦²à¥¤";
				else
					isOtherErrorChecked="";
				$(this).addClass("disabledComplainSubmitButton");
				$.ajax(
				{
					  url: 'complain_ap',
					  type: 'POST',
					  async: true,
					  cache:false,
					  ifModified:true,
					  timeout: 90000,
					 
					  data: {isPreferError:isPreferenceErrorChecked, preferErrorDetails:preferenceErrorDetails, isMobileError:isMobileErrorChecked, mobileErrorDetails:mobileErrorDetails, isGenderError:isGenderErrorChecked, isOtherError:isOtherErrorChecked, otherErrorDetails:otherErrorDetails, currentMobileNumber:afterApplicationComplainContactNo},
					  success: function(data) {
						if(data=='Yes')
						{
							var popUpWindowHeight= 300;
							var popUpWindowWidth=400;
							var currrentHTML='<div class="complainSubmitResponse success"> <div class="complainSubmitResponseSymbol"> &#10004; </div> <div class="complainSubmitResponseMessage"> &#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2488;&#2464;&#2495;&#2453;&#2477;&#2494;&#2476;&#2503; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2495;&#2468; &#2489;&#2527;&#2503;&#2459;&#2503;&#2404; <br/><br/></div> </div>';
							

							$('#CollegeDetailsPopUpContainerDiv').remove();
							var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
							showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
							$('.after_application_complain_row_div').html(currrentHTML);
						}
						else
						{
							var popUpWindowHeight= 300;
							var popUpWindowWidth=400;
							var currrentHTML='<div class="complainSubmitResponse unsuccess"> <div class="complainSubmitResponseSymbol"> x </div> <div class="complainSubmitResponseMessage"> &#2470;&#2497;&#2435;&#2454;&#2495;&#2468;, &#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2447;&#2454;&#2472; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2453;&#2480;&#2494; &#2488;&#2478;&#2509;&#2477;&#2476; &#2489;&#2458;&#2509;&#2459;&#2503; &#2472;&#2494;&#2404; <br/><br/></div> </div>';
							

							$('#CollegeDetailsPopUpContainerDiv').remove();
							var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
							showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
							$('.after_application_complain_row_div').html(currrentHTML);
						}
					  },
					  error: function(e) 
					  {
							var popUpWindowHeight= 310;
							var popUpWindowWidth=410;
							var currrentHTML='<div class="complainSubmitResponse unsuccess"> <div class="complainSubmitResponseSymbol"> x </div> <div class="complainSubmitResponseMessage"> &#2470;&#2497;&#2435;&#2454;&#2495;&#2468;, &#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2447;&#2454;&#2472; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2453;&#2480;&#2494; &#2488;&#2478;&#2509;&#2477;&#2476; &#2489;&#2458;&#2509;&#2459;&#2503; &#2472;&#2494;&#2404; <br/><br/></div> </div>';
							

							$('#CollegeDetailsPopUpContainerDiv').remove();
							var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
							showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
							$('.after_application_complain_row_div').html(currrentHTML);
					  }
				});
				return false;
			}
		});
		$(document).on('click', 'form#complainForm .infoPopUpButton.big', function(event)
		{
			event.preventDefault();
			var currentlySelectedProblemType=$( "#problemType option:selected" ).val();
			if(currentlySelectedProblemType=="wrongUnit")
			{
					var wrongExistingGroupsString='';
					$("form#complainForm #notExistsUnitContainerDiv input[type=checkbox]").each(function() 
					{
						if($(this).prop("checked") == true)
						{
							wrongExistingGroupsString+=$(this).val()+'###';
						}
					});
					var wrongNonExistingGroupsString='';
					$("form#complainForm #ExistsUnitContainerDiv input[type=checkbox]").each(function() 
					{
						if($(this).prop("checked") == true)
						{
							wrongNonExistingGroupsString+=$(this).val()+'###';
						}
					});
					if(wrongExistingGroupsString=='' && wrongNonExistingGroupsString=='')
					{
						alert("Please select aleast one");
					}
					else
					{
						var currentCollegeEIINNumber=parseInt(currentCollegeEIIN);
						$.ajax(
						{
							  url: 'wrongGroup',
							  type: 'POST',
							  async: true,
							  cache:false,
							  ifModified:true,
							 
							  data: {college_eiin:currentCollegeEIINNumber, wrongExistingGroups:wrongExistingGroupsString, wrongNonExistingGroups:wrongNonExistingGroupsString},
							  success: function(data) {
								if(data=='Yes')
								{
									if($('#CollegeDetailsPopUpContainerDiv').length > 0)
									{
										var responseMsg='<div class="complainSubmitResponse success"> <div class="complainSubmitResponseSymbol"> &#10004; </div> <div class="complainSubmitResponseMessage"> &#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2488;&#2464;&#2495;&#2453;&#2477;&#2494;&#2476;&#2503; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2495;&#2468; &#2489;&#2527;&#2503;&#2459;&#2503;&#2404; </div> </div>';
										$('#CollegeDetailsPopUpContainerDiv').html(responseMsg);
									}
								}
								else
								{
									if($('#CollegeDetailsPopUpContainerDiv').length > 0)
									{
										var responseMsg='<div class="complainSubmitResponse unsuccess"> <div class="complainSubmitResponseSymbol"> x </div> <div class="complainSubmitResponseMessage"> &#2470;&#2497;&#2435;&#2454;&#2495;&#2468;, &#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2447;&#2454;&#2472; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2453;&#2480;&#2494; &#2488;&#2478;&#2509;&#2477;&#2476; &#2489;&#2458;&#2509;&#2459;&#2503; &#2472;&#2494;&#2404; </div> </div>';
										$('#CollegeDetailsPopUpContainerDiv').html(responseMsg);
									}
								}
							  },
							  error: function(e) 
							  {
								    if($('#CollegeDetailsPopUpContainerDiv').length > 0)
									{
										var responseMsg='<div class="complainSubmitResponse unsuccess"> <div class="complainSubmitResponseSymbol"> x </div> <div class="complainSubmitResponseMessage"> &#2470;&#2497;&#2435;&#2454;&#2495;&#2468;, &#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2447;&#2454;&#2472; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2453;&#2480;&#2494; &#2488;&#2478;&#2509;&#2477;&#2476; &#2489;&#2458;&#2509;&#2459;&#2503; &#2472;&#2494;&#2404; </div> </div>';
										$('#CollegeDetailsPopUpContainerDiv').html(responseMsg);
									}
							  }
						});
					}

	
			}
			else if(currentlySelectedProblemType=="wrongMedium")
			{
				var wrongExistingVersionsString='';
					$("form#complainForm #notExistsMediumContainerDiv input[type=checkbox]").each(function() 
					{
						if($(this).prop("checked") == true)
						{
							wrongExistingVersionsString+=$(this).val()+'###';
						}
					});
					var wrongNonExistingVersionsString='';
					$("form#complainForm #ExistsMediumContainerDiv input[type=checkbox]").each(function() 
					{
						if($(this).prop("checked") == true)
						{
							wrongNonExistingVersionsString+=$(this).val()+'###';
						}
					});
					if(wrongExistingVersionsString=='' && wrongNonExistingVersionsString=='')
					{
						alert("Please select aleast one");
					}
					else
					{
						var currentCollegeEIINNumber=parseInt(currentCollegeEIIN);
						var currentSelectedGroup=$("#wrongMediumUnit option:selected").val();
						$.ajax(
						{
							  url: 'wrongVersion',
							  type: 'POST',
							  async: true,
							  cache:false,
							  ifModified:true,
							  					 
							  data: {college_eiin:currentCollegeEIINNumber, Group:currentSelectedGroup, wrongExistingVersions:wrongExistingVersionsString, wrongNonExistingVersions:wrongNonExistingVersionsString},
							  success: function(data) {
								if(data=='Yes')
								{
									if($('#CollegeDetailsPopUpContainerDiv').length > 0)
									{
										var responseMsg='<div class="complainSubmitResponse success"> <div class="complainSubmitResponseSymbol"> &#10004; </div> <div class="complainSubmitResponseMessage"> &#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2488;&#2464;&#2495;&#2453;&#2477;&#2494;&#2476;&#2503; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2495;&#2468; &#2489;&#2527;&#2503;&#2459;&#2503;&#2404; </div> </div>';
										$('#CollegeDetailsPopUpContainerDiv').html(responseMsg);
									}
								}
								else
								{
									if($('#CollegeDetailsPopUpContainerDiv').length > 0)
									{
										var responseMsg='<div class="complainSubmitResponse unsuccess"> <div class="complainSubmitResponseSymbol"> x </div> <div class="complainSubmitResponseMessage"> &#2470;&#2497;&#2435;&#2454;&#2495;&#2468;, &#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2447;&#2454;&#2472; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2453;&#2480;&#2494; &#2488;&#2478;&#2509;&#2477;&#2476; &#2489;&#2458;&#2509;&#2459;&#2503; &#2472;&#2494;&#2404; </div> </div>';
										$('#CollegeDetailsPopUpContainerDiv').html(responseMsg);
									}
								}
							  },
							  error: function(e) 
							  {
								    if($('#CollegeDetailsPopUpContainerDiv').length > 0)
									{
										var responseMsg='<div class="complainSubmitResponse unsuccess"> <div class="complainSubmitResponseSymbol"> x </div> <div class="complainSubmitResponseMessage"> &#2470;&#2497;&#2435;&#2454;&#2495;&#2468;, &#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2447;&#2454;&#2472; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2453;&#2480;&#2494; &#2488;&#2478;&#2509;&#2477;&#2476; &#2489;&#2458;&#2509;&#2459;&#2503; &#2472;&#2494;&#2404; </div> </div>';
										$('#CollegeDetailsPopUpContainerDiv').html(responseMsg);
									}
							  }
						});
					}
			}
			else if(currentlySelectedProblemType=="wrongSeatCount")
			{
					var givenSeatCount=$('#realSeatCount').val();
					if(givenSeatCount=='')
					{
						alert("Please input the correct seat count");
					}
					givenSeatCount=parseInt(givenSeatCount);
					if(givenSeatCount<0)
					{
						alert("Seat Count cannot be negative");
					}
					else if(isNaN(givenSeatCount))
					{
						alert("Seat Count should be a number");
					}
					else
					{
						var currentCollegeEIINNumber=parseInt(currentCollegeEIIN);
						var currentSelectedShift=$("#wrongSeatCountShift option:selected").val();
						var currentSelectedVersion=$("#wrongSeatCountMedium option:selected").val();
						var currentSelectedGroup=$("#wrongSeatCountUnit option:selected").val();
						$.ajax(
						{
							  url: 'wrongSeatCount',
							  type: 'POST',
							  async: true,
							  cache:false,
							  ifModified:true,
						 
							  data: {college_eiin:currentCollegeEIINNumber, shift:currentSelectedShift, Version:currentSelectedVersion, Group:currentSelectedGroup, correctSeatCount:givenSeatCount},
							  success: function(data) {
								if(data=='Yes')
								{
									if($('#CollegeDetailsPopUpContainerDiv').length > 0)
									{
										var responseMsg='<div class="complainSubmitResponse success"> <div class="complainSubmitResponseSymbol"> &#10004; </div> <div class="complainSubmitResponseMessage"> &#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2488;&#2464;&#2495;&#2453;&#2477;&#2494;&#2476;&#2503; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2495;&#2468; &#2489;&#2527;&#2503;&#2459;&#2503;&#2404; </div> </div>';
										$('#CollegeDetailsPopUpContainerDiv').html(responseMsg);
									}
								}
								else
								{
									if($('#CollegeDetailsPopUpContainerDiv').length > 0)
									{
										var responseMsg='<div class="complainSubmitResponse unsuccess"> <div class="complainSubmitResponseSymbol"> x </div> <div class="complainSubmitResponseMessage"> &#2470;&#2497;&#2435;&#2454;&#2495;&#2468;, &#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2447;&#2454;&#2472; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2453;&#2480;&#2494; &#2488;&#2478;&#2509;&#2477;&#2476; &#2489;&#2458;&#2509;&#2459;&#2503; &#2472;&#2494;&#2404; </div> </div>';
										$('#CollegeDetailsPopUpContainerDiv').html(responseMsg);
									}
								}
							  },
							  error: function(e) 
							  {
								    if($('#CollegeDetailsPopUpContainerDiv').length > 0)
									{
										var responseMsg='<div class="complainSubmitResponse unsuccess"> <div class="complainSubmitResponseSymbol"> x </div> <div class="complainSubmitResponseMessage"> &#2470;&#2497;&#2435;&#2454;&#2495;&#2468;, &#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2447;&#2454;&#2472; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2453;&#2480;&#2494; &#2488;&#2478;&#2509;&#2477;&#2476; &#2489;&#2458;&#2509;&#2459;&#2503; &#2472;&#2494;&#2404; </div> </div>';
										$('#CollegeDetailsPopUpContainerDiv').html(responseMsg);
									}
							  }
						});
					}
			}
			else if(currentlySelectedProblemType=="wrongType")
			{
					var givenCollegeTypeTicked=$("input:radio[name='wrongTypeOption']").is(':checked');
					if(!givenCollegeTypeTicked)
					{
						alert("Please select one");
					}
					else
					{
						var givenCollegeType=$("input:radio[name='wrongTypeOption']:checked").val();
						var currentCollegeEIINNumber=parseInt(currentCollegeEIIN);
						var currentSelectedShift=$("#wrongSeatCountShift option:selected").val();
						var currentSelectedVersion=$("#wrongSeatCountMedium option:selected").val();
						var currentSelectedGroup=$("#wrongSeatCountUnit option:selected").val();
						$.ajax(
						{
							  url: 'wrongGender',
							  type: 'POST',
							  async: true,
							  cache:false,
							  ifModified:true,
						 
							  data: {college_eiin:currentCollegeEIINNumber, shift:currentSelectedShift, Version:currentSelectedVersion, Group:currentSelectedGroup, correctGender:givenCollegeType},
							  success: function(data) {
								if(data=='Yes')
								{
									if($('#CollegeDetailsPopUpContainerDiv').length > 0)
									{
										var responseMsg='<div class="complainSubmitResponse success"> <div class="complainSubmitResponseSymbol"> &#10004; </div> <div class="complainSubmitResponseMessage"> &#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2488;&#2464;&#2495;&#2453;&#2477;&#2494;&#2476;&#2503; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2495;&#2468; &#2489;&#2527;&#2503;&#2459;&#2503;&#2404; </div> </div>';
										$('#CollegeDetailsPopUpContainerDiv').html(responseMsg);
									}
								}
								else
								{
									if($('#CollegeDetailsPopUpContainerDiv').length > 0)
									{
										var responseMsg='<div class="complainSubmitResponse unsuccess"> <div class="complainSubmitResponseSymbol"> x </div> <div class="complainSubmitResponseMessage"> &#2470;&#2497;&#2435;&#2454;&#2495;&#2468;, &#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2447;&#2454;&#2472; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2453;&#2480;&#2494; &#2488;&#2478;&#2509;&#2477;&#2476; &#2489;&#2458;&#2509;&#2459;&#2503; &#2472;&#2494;&#2404; </div> </div>';
										$('#CollegeDetailsPopUpContainerDiv').html(responseMsg);
									}
								}
							  },
							  error: function(e) 
							  {
								    if($('#CollegeDetailsPopUpContainerDiv').length > 0)
									{
										var responseMsg='<div class="complainSubmitResponse unsuccess"> <div class="complainSubmitResponseSymbol"> x </div> <div class="complainSubmitResponseMessage"> &#2470;&#2497;&#2435;&#2454;&#2495;&#2468;, &#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2447;&#2454;&#2472; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2453;&#2480;&#2494; &#2488;&#2478;&#2509;&#2477;&#2476; &#2489;&#2458;&#2509;&#2459;&#2503; &#2472;&#2494;&#2404; </div> </div>';
										$('#CollegeDetailsPopUpContainerDiv').html(responseMsg);
									}
							  }
						});
					}
			}	
			return false;
		});
		$(document).on('click', '#submitButton', function(event)
		{
			event.preventDefault();
			var rowCount=0;
			var boutext1 = '';
			var boutext2 = '';
			if($('#ssc_board').val()=='20')
			{
				boutext1 = '&#2476;&#2494;&#2434;&#2482;&#2494;&#2470;&#2503;&#2486; &#2441;&#2472;&#2509;&#2478;&#2497;&#2453;&#2509;&#2468; &#2476;&#2495;&#2486;&#2509;&#2476;&#2476;&#2495;&#2470;&#2509;&#2479;&#2494;&#2482;&#2527; &#2489;&#2468;&#2503; &#2441;&#2468;&#2509;&#2468;&#2496;&#2480;&#2509;&#2467; &#2486;&#2495;&#2453;&#2509;&#2487;&#2494;&#2480;&#2509;&#2469;&#2496;&#2470;&#2503;&#2480; &#2476;&#2527;&#2488;&#2488;&#2496;&#2478;&#2494; &#2536;&#2534;&#2535;&#2543; &#2488;&#2494;&#2482;&#2503;&#2480; &#2537;&#2535;&#2486;&#2503; &#2465;&#2495;&#2488;&#2503;&#2478;&#2509;&#2476;&#2480; &#2437;&#2472;&#2498;&#2480;&#2509;&#2471;&#2509;&#2476; &#2536;&#2536; &#2476;&#2459;&#2480; &#2489;&#2468;&#2503; &#2489;&#2476;&#2503;, &#2437;&#2480;&#2509;&#2469;&#2494;&#2510; &#2486;&#2495;&#2453;&#2509;&#2487;&#2494;&#2480;&#2509;&#2469;&#2496;&#2470;&#2503;&#2480; &#2460;&#2472;&#2509;&#2478; &#2468;&#2494;&#2480;&#2495;&#2454; &#2535;&#2543;&#2543;&#2542; &#2488;&#2494;&#2482;&#2503;&#2480; &#2535;&#2482;&#2494; &#2460;&#2494;&#2472;&#2497;&#2527;&#2494;&#2480;&#2496; &#2476;&#2494; &#2468;&#2494;&#2480; &#2474;&#2480;&#2503; &#2489;&#2468;&#2503; &#2489;&#2476;&#2503;&#2404;' + 
						'<br><br>';
				boutext2 = 'The age of a student passing from Bangladesh Open University must not be more than 22 years on 31 December 2019, that is, the student must be born on or after 1 January 1998.'+
						'<br><br>';
			}
			var currrentSelectionHTML='<div id="CollegeDetailsContainerDiv"> <div id="warningMessage"> '+
				boutext1 +
				'&#2438;&#2474;&#2472;&#2494;&#2480; &#2438;&#2476;&#2503;&#2470;&#2472;&#2463;&#2495; &#2447;&#2454;&#2472;&#2451; &#2437;&#2488;&#2478;&#2509;&#2474;&#2498;&#2480;&#2509;&#2467; &#2437;&#2476;&#2488;&#2509;&#2469;&#2494;&#2527; &#2480;&#2527;&#2503;&#2459;&#2503;&#2404; &#2438;&#2474;&#2472;&#2494;&#2480; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2468;&#2469;&#2509;&#2479; &#2477;&#2494;&#2482;&#2477;&#2494;&#2476;&#2503; &#2474;&#2480;&#2496;&#2453;&#2509;&#2487;&#2494; &#2453;&#2480;&#2494;&#2480; &#2474;&#2480; Submit &#2476;&#2494;&#2463;&#2472;&#2463;&#2495; Click &#2453;&#2480;&#2482;&#2503;&#2439; &#2453;&#2503;&#2476;&#2482; &#2478;&#2494;&#2468;&#2509;&#2480; &#2438;&#2474;&#2472;&#2494;&#2480; &#2468;&#2469;&#2509;&#2479; &#2488;&#2434;&#2480;&#2453;&#2509;&#2487;&#2495;&#2468; &#2489;&#2476;&#2503; &#2447;&#2476;&#2434; &#2438;&#2476;&#2503;&#2470;&#2472; &#2474;&#2509;&#2480;&#2453;&#2509;&#2480;&#2495;&#2527;&#2494;&#2463;&#2495; &#2488;&#2478;&#2509;&#2474;&#2498;&#2480;&#2509;&#2467; &#2489;&#2476;&#2503;&#2404;  &#2438;&#2476;&#2503;&#2470;&#2472; &#2488;&#2478;&#2509;&#2474;&#2498;&#2480;&#2509;&#2467; &#2489;&#2476;&#2494;&#2480; &#2474;&#2480;&#2503; &#2438;&#2474;&#2472;&#2495; Web Message &#2447;&#2476;&#2434; &#2438;&#2474;&#2472;&#2494;&#2480; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2478;&#2507;&#2476;&#2494;&#2439;&#2482; &#2475;&#2507;&#2472; &#2472;&#2478;&#2509;&#2476;&#2480;&#2503; &#2472;&#2495;&#2486;&#2509;&#2458;&#2495;&#2468;&#2453;&#2480;&#2467; SMS &#2474;&#2494;&#2476;&#2503;&#2472;&#2404;<br/>&#2438;&#2474;&#2472;&#2495; &#2438;&#2474;&#2472;&#2494;&#2480; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2468;&#2469;&#2509;&#2479; &#2438;&#2476;&#2503;&#2470;&#2472; &#2488;&#2478;&#2527;&#2488;&#2496;&#2478;&#2494;&#2480; &#2478;&#2471;&#2509;&#2479;&#2503; &#2488;&#2480;&#2509;&#2476;&#2507;&#2458;&#2509;&#2458; &#2539; (&#2474;&#2494;&#2433;&#2458;) &#2476;&#2494;&#2480; &#2474;&#2480;&#2495;&#2476;&#2480;&#2509;&#2468;&#2472; &#2453;&#2480;&#2468;&#2503; &#2474;&#2494;&#2480;&#2476;&#2503;&#2472;&#2404;  <br/><br/>'+
				boutext2+
				'Your application submission procedure is not completed yet. Please check your provided information carefully before clicking the Submit Button. Your submission will be completed only if you click the Submit Button. After successful submission, you will receive a confirmation message in Web and in your contact mobile phone number.<br/>You can update your information at most 5 (five) times within the application deadline.</div> <div id="currentCollegeSelection"> Selected Colleges : </div> <div class="CollegeDetailsTableDiv"> <table class="CollegeDetailsTable"> <tbody> <tr> <th>College Name</th> <th>EIIN</th> <th>Shift</th> <th>Version</th> <th>Group</th> <th>SQ</th> <th>Priority</th></tr>';
			var currentEIINs=[];
			var currentPriorities=[];
//			console.log($('#selection_row_table'));
//			$('#selection_row_table').find('tr').sort(function(a, b) {
//				console.log(a);
//				console.log(b);
//    			return (a.find('td:eq(8)').children('input').val() - b.find('td:eq(8)').children('input').val());
//			});
			$('#selection_row_table').find('tr').each(function (rowIndex, r) 
			{
				//console.log("7:"+$(this).find('td:eq(7)').children('input').val());
				//console.log("8:"+$(this).find('td:eq(8)').children('input').val());
				var priority = $(this).find('td:eq(7)').children('input').val()
				var tempRowData ='';
				//console.log($(this).find('td'));
				$(this).find('td').each(function (colIndex, c) 
				{
					if(colIndex==2)
						currentEIINs.push(c.textContent);
					if(colIndex>0 && colIndex<7) //number 7 GB quota
						tempRowData+='<td>'+c.textContent+'</td>';
					if(colIndex==8)
					{
						tempRowData+='<td>'+parseInt(priority)+'</td>';
						currentPriorities.push(priority);
					}
					
				});
				if(tempRowData!='')
				{
					rowCount+=1;
					if(rowCount%2==1)
					{
						currrentSelectionHTML+='<tr>'+tempRowData+'</tr>';
					}
					else
					{
						currrentSelectionHTML+='<tr class="CollegeDetailsTableEvenRows">'+tempRowData+'</tr>';
					}
					tempRowData ='';
				}
					
			});
			var uniqueEIINs=uniqueArraySort( currentEIINs );
			if(rowCount<1)
			{
				var popUpWindowHeight= 220;
				var popUpWindowWidth=280;
				var alertMessage="Please add at least one college.";
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			else if(uniqueEIINs.length<5)
			{
				var alertMessage="Application failed. You have selected "+uniqueEIINs.length+" ("+numbers[uniqueEIINs.length]+") different colleges. You need to select at least 5 (five) different colleges"
								+"<br>&#2438;&#2476;&#2503;&#2470;&#2472; &#2476;&#2509;&#2479;&#2480;&#2509;&#2469; &#2489;&#2479;&#2492;&#2503;&#2459;&#2503;&#2404; &#2438;&#2474;&#2472;&#2495;  "+numbersb[uniqueEIINs.length]+" ("+numbersbf[uniqueEIINs.length]+") &#2463;&#2495; &#2438;&#2482;&#2494;&#2470;&#2494; &#2453;&#2482;&#2503;&#2460; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; &#2453;&#2480;&#2503;&#2459;&#2503;&#2472;&#2404;  &#2438;&#2474;&#2472;&#2494;&#2453;&#2503; &#2453;&#2478;&#2474;&#2453;&#2509;&#2487;&#2503; &#2539; (&#2474;&#2494;&#2433;&#2458;)&#2463;&#2495;  &#2438;&#2482;&#2494;&#2470;&#2494; &#2453;&#2482;&#2503;&#2460; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; &#2453;&#2480;&#2468;&#2503; &#2489;&#2476;&#2503;";
				var popUpWindowHeight= 250;
				var popUpWindowWidth=310;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			else if (uniqueEIINs.length>10)
			{
				var alertMessage="Application failed. You have selected "+uniqueEIINs.length+" ("+numbers[uniqueEIINs.length]+") different colleges. You can not select more than 10 (ten) different colleges."+
				"<br>&#2438;&#2476;&#2503;&#2470;&#2472; &#2476;&#2509;&#2479;&#2480;&#2509;&#2469; &#2489;&#2479;&#2492;&#2503;&#2459;&#2503;&#2404; &#2438;&#2474;&#2472;&#2495;  "+
				numbersb[uniqueEIINs.length]+" ("+numbersbf[uniqueEIINs.length]+") &#2463;&#2495; &#2438;&#2482;&#2494;&#2470;&#2494; &#2453;&#2482;&#2503;&#2460; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; &#2453;&#2480;&#2503;&#2459;&#2503;&#2472;&#2404;  &#2438;&#2474;&#2472;&#2495; &#2535;&#2534; (&#2470;&#2486;) &#2476;&#2495;&#2477;&#2495;&#2472;&#2509;&#2472; &#2453;&#2482;&#2503;&#2460; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; &#2453;&#2480;&#2468;&#2503; &#2474;&#2494;&#2480;&#2476;&#2503;&#2472; &#2472;&#2494;&#2404;";
				var popUpWindowHeight= 250;
				var popUpWindowWidth=310;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			else if(!isGivenPriorityOkSS(currentPriorities))
			{
				var popUpWindowHeight= 210;
				var popUpWindowWidth=510;
				var alertMessage="Please correct your priority and then submit again<br>&#2438;&#2474;&#2472;&#2494;&#2480; priority &#2488;&#2464;&#2495;&#2453; &#2453;&#2480;&#2497;&#2472; &#2447;&#2476;&#2434; &#2468;&#2494;&#2480;&#2474;&#2480; &#2438;&#2476;&#2494;&#2480; submit &#2453;&#2480;&#2497;&#2472;";
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			else
			{
				var popUpWindowHeight= 552;
				var popUpWindowWidth=780;
				currrentSelectionHTML+='</tbody> </table> </div> <a href="#" class="infoPopUpButton big closeSubmitPreview"><span>x</span>Continue To Edit</a> <a href="#" class="infoPopUpButton big red continueToSubmitSelection"><span>&#10004;</span>Submit Application</a> </div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentSelectionHTML+"</div>";
				showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			return false;
		});
		$(document).on('click', '#submitEditButton', function(event)
		{
			event.preventDefault();
			var rowCount=0;
			var addedRow=0;
			var i=0;
			submitTimeChoices=[];
			var alreadyAddedRowCount=0;
			var currrentSelectionHTML='<div id="CollegeDetailsContainerDiv"> <div id="warningMessage"> &#2468;&#2507;&#2478;&#2494;&#2480; &#2468;&#2469;&#2509;&#2479; &#2474;&#2480;&#2495;&#2476;&#2480;&#2509;&#2468;&#2472;&#2503;&#2480;/&#2488;&#2434;&#2479;&#2507;&#2460;&#2472;&#2503;&#2480; &#2438;&#2476;&#2503;&#2470;&#2472;&#2463;&#2495; &#2447;&#2454;&#2472;&#2451; &#2437;&#2488;&#2478;&#2509;&#2474;&#2498;&#2480;&#2509;&#2467; &#2437;&#2476;&#2488;&#2509;&#2469;&#2494;&#2527; &#2480;&#2527;&#2503;&#2459;&#2503;&#2404; &#2468;&#2507;&#2478;&#2494;&#2480; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2468;&#2469;&#2509;&#2479; &#2477;&#2494;&#2482;&#2477;&#2494;&#2476;&#2503; &#2474;&#2480;&#2496;&#2453;&#2509;&#2487;&#2494; &#2453;&#2480;&#2494;&#2480; &#2474;&#2480; Submit Button &#2463;&#2495; Click &#2453;&#2480;&#2482;&#2503;&#2439; &#2453;&#2503;&#2476;&#2482; &#2478;&#2494;&#2468;&#2509;&#2480; &#2468;&#2507;&#2478;&#2494;&#2480; &#2488;&#2434;&#2480;&#2453;&#2509;&#2487;&#2495;&#2468; &#2468;&#2469;&#2509;&#2479; &#2474;&#2480;&#2495;&#2476;&#2480;&#2509;&#2468;&#2495;&#2468; &#2489;&#2476;&#2503; &#2404; Web-&#2447; &#2474;&#2509;&#2480;&#2469;&#2478;&#2476;&#2494;&#2480; &#2438;&#2476;&#2503;&#2470;&#2472; &#2488;&#2478;&#2509;&#2474;&#2498;&#2480;&#2509;&#2467; &#2489;&#2476;&#2494;&#2480; &#2474;&#2480;&#2503; &#2468;&#2497;&#2478;&#2495; Web Message &#2447;&#2476;&#2434; &#2468;&#2507;&#2478;&#2494;&#2480; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2478;&#2507;&#2476;&#2494;&#2439;&#2482; &#2475;&#2507;&#2472; &#2472;&#2478;&#2509;&#2476;&#2480;&#2503; &#2472;&#2495;&#2486;&#2509;&#2458;&#2495;&#2468;&#2453;&#2480;&#2467; SMS &#2474;&#2494;&#2476;&#2503;&#2404;<br/>&#2468;&#2497;&#2478;&#2495; &#2468;&#2507;&#2478;&#2494;&#2480; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2468;&#2469;&#2509;&#2479; &#2438;&#2476;&#2503;&#2470;&#2472; &#2488;&#2478;&#2527;&#2488;&#2496;&#2478;&#2494;&#2480; &#2478;&#2471;&#2509;&#2479;&#2503; &#2488;&#2480;&#2509;&#2476;&#2507;&#2458;&#2509;&#2458; &#2539; (&#2474;&#2494;&#2433;&#2458;) &#2476;&#2494;&#2480; &#2474;&#2480;&#2495;&#2476;&#2480;&#2509;&#2468;&#2472; &#2453;&#2480;&#2468;&#2503; &#2474;&#2494;&#2480;&#2476;&#2503;&#2404; <br/><br/>Your application update procedure is not completed yet. Please check your provided information carefully before clicking the Submit Button. Your update will be completed only if you click the Submit Button. After your first successful submission in web, you will receive a confirmation message in Web and in your contact mobile phone number.<br/>You can update your information at most 5 (five) times within the application deadline.</div> <div id="currentCollegeSelection"> Selected Colleges : </div> <div class="CollegeDetailsTableDiv"> <table class="CollegeDetailsTable"> <tbody> <tr> <th>SL.</th> <th>College Name</th> <th>EIIN</th> <th>Shift</th> <th>Version</th> <th>Group</th> <th>SQ</th> <th>Priority</th></tr>';
			var currentEIINs=[];
			var currentPriorities=[];
			if($("[name='applicant.application_info.quota_ff']").prop('checked')==true)
			{
				submitTimeChoices.push([]);
				submitTimeChoices[i][0]="333333";
				submitTimeChoices[i][1]=1;
				i++;
			}
			else
			{
				submitTimeChoices.push([]);
				submitTimeChoices[i][0]="333333";
				submitTimeChoices[i][1]=0;
				i++;
			}
			/*
			if($("[name='applicant.application_info.quota_expatriate']").prop('checked')==true)
			{
				submitTimeChoices.push([]);
				submitTimeChoices[i][0]="999999";
				submitTimeChoices[i][1]=1;
				i++;
			}
			else
			{
				submitTimeChoices.push([]);
				submitTimeChoices[i][0]="999999";
				submitTimeChoices[i][1]=0;
				i++;
			}
			if($("[name='applicant.application_info.quota_eq']").prop('checked')==true)
			{
				submitTimeChoices.push([]);
				submitTimeChoices[i][0]="666666";
				submitTimeChoices[i][1]=1;
				i++;
			}
			else
			{
				submitTimeChoices.push([]);
				submitTimeChoices[i][0]="666666";
				submitTimeChoices[i][1]=0;
				i++;
			}
			*/
			$('#selection_row_table').find('tr').each(function (rowIndex, r) 
			{
				var priority = $(this).find('td:eq(8)').children('input').val()
				var tempRowData ='';
				var ltt='';
				var ltp='';
				$(this).find('td').each(function (colIndex, c) 
				{
					if(colIndex==2)
					{
						currentEIINs.push(c.textContent);
						ltt+=c.textContent;
						//submitTimeChoices.push([]);
						//submitTimeChoices[i][0]=c.textContent;
					}
					if(colIndex==3)
						ltt+=c.textContent;
					if(colIndex==4)
						ltt+=c.textContent;
					if(colIndex==5)
						ltt+=c.textContent;
					if(colIndex<7) //number 7 GB quota
						tempRowData+='<td>'+c.textContent+'</td>';
					if(colIndex==8)
					{
						tempRowData+='<td>'+parseInt(priority)+'</td>';
						currentPriorities.push(priority);
						
						ltp=priority;
						submitTimeChoices.push([]);
						submitTimeChoices[i][0]=ltt;
						submitTimeChoices[i][1]=ltp;
						i++;
					}
					else if(colIndex==9)
					{
						if($(c).has('.newlyAddedRow').length>0)
						{
							addedRow=1;
						}
					}
				});
				if(tempRowData!='')
				{
					if(addedRow!=1)
					{
						alreadyAddedRowCount+=1;
					}
					addedRow=0;
					rowCount+=1;
					if(rowCount%2==1)
					{
						currrentSelectionHTML+='<tr>'+tempRowData+'</tr>';
					}
					else
					{
						currrentSelectionHTML+='<tr class="CollegeDetailsTableEvenRows">'+tempRowData+'</tr>';
					}
					tempRowData ='';
				}
					
			});
			//alert(submitTimeChoices.toString());
			var uniqueEIINs=uniqueArraySort( currentEIINs );
			
			
			
			var isLSArraysSame=compareTwo2DIntArrays(submitTimeChoices, loadTimeChoices);
			
			if(isLSArraysSame==1)
			{
				var popUpWindowHeight= 220;
				var popUpWindowWidth=280;
				var alertMessage="You haven't made any change.";
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			/*if((rowCount-alreadyAddedRowCount)<1)
			{
				var popUpWindowHeight= 210;
				var popUpWindowWidth=270;
				var alertMessage="Please add at least one college.";
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}*/
			else if(uniqueEIINs.length<5)
			{ //joy
				//var alertMessage="Application failed. You have selected "+uniqueEIINs.length+" ("+numbers[uniqueEIINs.length]+") different colleges. You need to select at least 5 (Five) different colleges.";
				var alertMessage="Application failed. You have selected "+uniqueEIINs.length+" ("+numbers[uniqueEIINs.length]+") different colleges. You need to select at least 5 (five) different colleges"
				+"<br>&#2438;&#2476;&#2503;&#2470;&#2472; &#2476;&#2509;&#2479;&#2480;&#2509;&#2469; &#2489;&#2479;&#2492;&#2503;&#2459;&#2503;&#2404; &#2438;&#2474;&#2472;&#2495;  "+numbersb[uniqueEIINs.length]+" ("+numbersbf[uniqueEIINs.length]+") &#2463;&#2495; &#2438;&#2482;&#2494;&#2470;&#2494; &#2453;&#2482;&#2503;&#2460; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; &#2453;&#2480;&#2503;&#2459;&#2503;&#2472;&#2404;  &#2438;&#2474;&#2472;&#2494;&#2453;&#2503; &#2453;&#2478;&#2474;&#2453;&#2509;&#2487;&#2503; &#2539; (&#2474;&#2494;&#2433;&#2458;)&#2463;&#2495;  &#2438;&#2482;&#2494;&#2470;&#2494; &#2453;&#2482;&#2503;&#2460; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; &#2453;&#2480;&#2468;&#2503; &#2489;&#2476;&#2503;";

				var popUpWindowHeight= 250;
				var popUpWindowWidth=310;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			else if (uniqueEIINs.length>10)
			{
				var alertMessage="Application failed. You have selected "+uniqueEIINs.length+" ("+numbers[uniqueEIINs.length]+") different colleges. You can not select more than 10 (ten) different colleges."+
				"<br>&#2438;&#2476;&#2503;&#2470;&#2472; &#2476;&#2509;&#2479;&#2480;&#2509;&#2469; &#2489;&#2479;&#2492;&#2503;&#2459;&#2503;&#2404; &#2438;&#2474;&#2472;&#2495;  "+
				numbersb[uniqueEIINs.length]+" ("+numbersbf[uniqueEIINs.length]+") &#2463;&#2495; &#2438;&#2482;&#2494;&#2470;&#2494; &#2453;&#2482;&#2503;&#2460; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; &#2453;&#2480;&#2503;&#2459;&#2503;&#2472;&#2404;  &#2438;&#2474;&#2472;&#2495; &#2535;&#2534; (&#2470;&#2486;) &#2476;&#2495;&#2477;&#2495;&#2472;&#2509;&#2472; &#2453;&#2482;&#2503;&#2460; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; &#2453;&#2480;&#2468;&#2503; &#2474;&#2494;&#2480;&#2476;&#2503;&#2472; &#2472;&#2494;&#2404;";
				var popUpWindowHeight= 250;
				var popUpWindowWidth=310;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			else if(!isGivenPriorityOkSS(currentPriorities))
			{
				var popUpWindowHeight= 260;
				var popUpWindowWidth=280;
				var alertMessage="Please correct your priority and then submit again<br/>"+
				"&#2438;&#2474;&#2472;&#2494;&#2480; Priority &#2488;&#2464;&#2495;&#2453; &#2453;&#2480;&#2497;&#2472; &#2447;&#2476;&#2434; &#2468;&#2494;&#2480;&#2474;&#2480; &#2438;&#2476;&#2494;&#2480; Submit &#2453;&#2480;&#2497;&#2472;";
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			else
			{
				var popUpWindowHeight= 552;
				var popUpWindowWidth=780;
				currrentSelectionHTML+='</tbody> </table> </div><a href="#" class="infoPopUpButton big closeSubmitPreview"><span>x</span>Continue To Edit</a> <a href="#" class="infoPopUpButton big red continueToSubmitEditSelection"><span>&#10004;</span>Submit Application</a> </div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentSelectionHTML+"</div>";
				showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			return false;
		});
		
		//added by joy
		$(document).on('click', '#submitButtonUpdateEdit', function(event)
		{
			
			event.preventDefault();
			var rowCount=0;
			var addedRow=0;
			var i=0;
			submitTimeChoices=[];
			var alreadyAddedRowCount=0;
			currentUpdateCount=$('#no_of_update').val();
			//var currrentSelectionHTML='<div id="CollegeDetailsContainerDiv"> <div id="warningMessage"> &#2468;&#2507;&#2478;&#2494;&#2480; &#2468;&#2469;&#2509;&#2479; &#2474;&#2480;&#2495;&#2476;&#2480;&#2509;&#2468;&#2472;&#2503;&#2480;/&#2488;&#2434;&#2479;&#2507;&#2460;&#2472;&#2503;&#2480; &#2438;&#2476;&#2503;&#2470;&#2472;&#2463;&#2495; &#2447;&#2454;&#2472;&#2451; &#2437;&#2488;&#2478;&#2509;&#2474;&#2498;&#2480;&#2509;&#2467; &#2437;&#2476;&#2488;&#2509;&#2469;&#2494;&#2527; &#2480;&#2527;&#2503;&#2459;&#2503;&#2404; &#2468;&#2507;&#2478;&#2494;&#2480; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2468;&#2469;&#2509;&#2479; &#2477;&#2494;&#2482;&#2477;&#2494;&#2476;&#2503; &#2474;&#2480;&#2496;&#2453;&#2509;&#2487;&#2494; &#2453;&#2480;&#2494;&#2480; &#2474;&#2480; Submit Button &#2463;&#2495; Click &#2453;&#2480;&#2482;&#2503;&#2439; &#2453;&#2503;&#2476;&#2482; &#2478;&#2494;&#2468;&#2509;&#2480; &#2468;&#2507;&#2478;&#2494;&#2480; &#2488;&#2434;&#2480;&#2453;&#2509;&#2487;&#2495;&#2468; &#2468;&#2469;&#2509;&#2479; &#2474;&#2480;&#2495;&#2476;&#2480;&#2509;&#2468;&#2495;&#2468; &#2489;&#2476;&#2503; &#2404; Web-&#2447; &#2474;&#2509;&#2480;&#2469;&#2478;&#2476;&#2494;&#2480; &#2438;&#2476;&#2503;&#2470;&#2472; &#2488;&#2478;&#2509;&#2474;&#2498;&#2480;&#2509;&#2467; &#2489;&#2476;&#2494;&#2480; &#2474;&#2480;&#2503; &#2468;&#2497;&#2478;&#2495; Web Message &#2447;&#2476;&#2434; &#2468;&#2507;&#2478;&#2494;&#2480; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2478;&#2507;&#2476;&#2494;&#2439;&#2482; &#2475;&#2507;&#2472; &#2472;&#2478;&#2509;&#2476;&#2480;&#2503; &#2472;&#2495;&#2486;&#2509;&#2458;&#2495;&#2468;&#2453;&#2480;&#2467; SMS &#2474;&#2494;&#2476;&#2503;&#2404;<br/>'
			var boutext1 = '';
			var boutext2 = '';
			if($('#ssc_board').val()=='20')
			{
				boutext1 = '&#2476;&#2494;&#2434;&#2482;&#2494;&#2470;&#2503;&#2486; &#2441;&#2472;&#2509;&#2478;&#2497;&#2453;&#2509;&#2468; &#2476;&#2495;&#2486;&#2509;&#2476;&#2476;&#2495;&#2470;&#2509;&#2479;&#2494;&#2482;&#2527; &#2489;&#2468;&#2503; &#2441;&#2468;&#2509;&#2468;&#2496;&#2480;&#2509;&#2467; &#2486;&#2495;&#2453;&#2509;&#2487;&#2494;&#2480;&#2509;&#2469;&#2496;&#2470;&#2503;&#2480; &#2476;&#2527;&#2488;&#2488;&#2496;&#2478;&#2494; &#2536;&#2534;&#2535;&#2543; &#2488;&#2494;&#2482;&#2503;&#2480; &#2537;&#2535;&#2486;&#2503; &#2465;&#2495;&#2488;&#2503;&#2478;&#2509;&#2476;&#2480; &#2437;&#2472;&#2498;&#2480;&#2509;&#2471;&#2509;&#2476; &#2536;&#2536; &#2476;&#2459;&#2480; &#2489;&#2468;&#2503; &#2489;&#2476;&#2503;, &#2437;&#2480;&#2509;&#2469;&#2494;&#2510; &#2486;&#2495;&#2453;&#2509;&#2487;&#2494;&#2480;&#2509;&#2469;&#2496;&#2470;&#2503;&#2480; &#2460;&#2472;&#2509;&#2478; &#2468;&#2494;&#2480;&#2495;&#2454; &#2535;&#2543;&#2543;&#2542; &#2488;&#2494;&#2482;&#2503;&#2480; &#2535;&#2482;&#2494; &#2460;&#2494;&#2472;&#2497;&#2527;&#2494;&#2480;&#2496; &#2476;&#2494; &#2468;&#2494;&#2480; &#2474;&#2480;&#2503; &#2489;&#2468;&#2503; &#2489;&#2476;&#2503;&#2404;<br><br>';
				boutext2 = 'The age of a student passing from Bangladesh Open University must not be more than 22 years on 31 December 2019, that is, the student must be born on or after 1 January 1998.<br><br>';
			}	
			var currrentSelectionHTML='<div id="CollegeDetailsContainerDiv"> <div id="warningMessage"> '+
					boutext1 +
					'&#2438;&#2474;&#2472;&#2494;&#2480; &#2468;&#2469;&#2509;&#2479; &#2474;&#2480;&#2495;&#2476;&#2480;&#2509;&#2468;&#2472;&#2503;&#2480;/&#2488;&#2434;&#2479;&#2507;&#2460;&#2472;&#2503;&#2480; &#2438;&#2476;&#2503;&#2470;&#2472;&#2463;&#2495; &#2447;&#2454;&#2472;&#2451; &#2437;&#2488;&#2478;&#2509;&#2474;&#2498;&#2480;&#2509;&#2467; &#2437;&#2476;&#2488;&#2509;&#2469;&#2494;&#2527; &#2480;&#2527;&#2503;&#2459;&#2503;&#2404; &#2438;&#2474;&#2472;&#2494;&#2480; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2468;&#2469;&#2509;&#2479; &#2477;&#2494;&#2482;&#2477;&#2494;&#2476;&#2503; &#2474;&#2480;&#2496;&#2453;&#2509;&#2487;&#2494; &#2453;&#2480;&#2494;&#2480; &#2474;&#2480; Submit Button&#2463;&#2495; Click &#2453;&#2480;&#2482;&#2503;&#2439; &#2453;&#2503;&#2476;&#2482; &#2478;&#2494;&#2468;&#2509;&#2480; &#2438;&#2474;&#2472;&#2494;&#2480; &#2488;&#2434;&#2480;&#2453;&#2509;&#2487;&#2495;&#2468; &#2468;&#2469;&#2509;&#2479; &#2474;&#2480;&#2495;&#2476;&#2480;&#2509;&#2468;&#2495;&#2468; &#2489;&#2476;&#2503;&#2404;<br/>'
			
			if (currentUpdateCount != undefined && currentUpdateCount != null) 
			{
				currrentSelectionHTML+='<span class="updateCountMessage">&#2438;&#2474;&#2472;&#2495; &#2439;&#2468;&#2495;&#2478;&#2471;&#2509;&#2479;&#2503; &#2438;&#2474;&#2472;&#2494;&#2480; &#2438;&#2476;&#2503;&#2470;&#2472;&#2463;&#2495; '+bengaliNumber[currentUpdateCount]+' ('+numbersbfj[currentUpdateCount]+') &#2476;&#2494;&#2480; &#2438;&#2474;&#2465;&#2503;&#2463; &#2453;&#2480;&#2503;&#2459;&#2503;&#2472;&#2404; </span>';
			}
			
			
			
			currrentSelectionHTML+=' &#2438;&#2474;&#2472;&#2495; &#2438;&#2474;&#2472;&#2494;&#2480; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2468;&#2469;&#2509;&#2479; &#2438;&#2476;&#2503;&#2470;&#2472; &#2488;&#2478;&#2527;&#2488;&#2496;&#2478;&#2494;&#2480; &#2478;&#2471;&#2509;&#2479;&#2503; &#2488;&#2480;&#2509;&#2476;&#2507;&#2458;&#2509;&#2458; &#2539; (&#2474;&#2494;&#2433;&#2458;) &#2476;&#2494;&#2480; &#2474;&#2480;&#2495;&#2476;&#2480;&#2509;&#2468;&#2472; &#2453;&#2480;&#2468;&#2503; &#2474;&#2494;&#2480;&#2476;&#2503;&#2472;&#2404; <br/><br/>'+
					boutext2 + 
					'Your application update procedure is not completed yet. Please check your provided information carefully before clicking the Submit Button. Your update will be completed only if you click the Submit Button.<br/>'
			if (currentUpdateCount != undefined && currentUpdateCount != null) 
			{
				currrentSelectionHTML+='<span class="updateCountMessage">You have already updated your application '+currentUpdateCount+' ('+numbersj[currentUpdateCount]+') '+((currentUpdateCount>1) ? 'times' : 'time')+'.</span>';
			}
			currrentSelectionHTML+=' You can update your information at most 5 (five) times within the application deadline.</div> <div id="currentCollegeSelection"> Selected Colleges : </div> <div class="CollegeDetailsTableDiv"> <table class="CollegeDetailsTable"> <tbody> <tr> <!--<th>SL.</th> --> <th>College Name</th> <th>EIIN</th> <th>Shift</th> <th>Version</th> <th>Group</th> <th>SQ</th> <th>Priority</th></tr>';
			var currentEIINs=[];
			var currentPriorities=[];
			if($("[name='applicant.application_info.quota_ff']").prop('checked')==true)
			{
				submitTimeChoices.push([]);
				submitTimeChoices[i][0]="333333";
				submitTimeChoices[i][1]=1;
				i++;
			}
			else
			{
				submitTimeChoices.push([]);
				submitTimeChoices[i][0]="333333";
				submitTimeChoices[i][1]=0;
				i++;
			}
			/*
			if($("[name='applicant.application_info.quota_expatriate']").prop('checked')==true)
			{
				submitTimeChoices.push([]);
				submitTimeChoices[i][0]="999999";
				submitTimeChoices[i][1]=1;
				i++;
			}
			else
			{
				submitTimeChoices.push([]);
				submitTimeChoices[i][0]="999999";
				submitTimeChoices[i][1]=0;
				i++;
			}
			if($("[name='applicant.application_info.quota_eq']").prop('checked')==true)
			{
				submitTimeChoices.push([]);
				submitTimeChoices[i][0]="666666";
				submitTimeChoices[i][1]=1;
				i++;
			}
			else
			{
				submitTimeChoices.push([]);
				submitTimeChoices[i][0]="666666";
				submitTimeChoices[i][1]=0;
				i++;
			}
			*/
			$('#selection_row_table').find('tr').each(function (rowIndex, r) 
			{
				var priority = $(this).find('td:eq(7)').children('input').val()
				var tempRowData ='';
				var ltt='';
				var ltp='';
				$(this).find('td').each(function (colIndex, c) 
				{
					//var sc
					if(colIndex==2)
					{
						currentEIINs.push(c.textContent);
						ltt+=c.textContent;
						//submitTimeChoices.push([]);
						//submitTimeChoices[i][0]=c.textContent;
					}
					if(colIndex==3)
						ltt+=c.textContent;
					if(colIndex==4)
						ltt+=c.textContent;
					if(colIndex==5)
						ltt+=c.textContent;					
					if(colIndex>0 &&  colIndex<7)  //number 7 GB quota
						tempRowData+='<td>'+c.textContent+'</td>';
					if(colIndex==8)
					{
						tempRowData+='<td>'+parseInt(priority)+'</td>';
						currentPriorities.push(priority);
						
						ltp=priority;
						submitTimeChoices.push([]);
						submitTimeChoices[i][0]=ltt;
						submitTimeChoices[i][1]=ltp;
						i++;
					}
					else if(colIndex==9)
					{
						if($(c).has('.newlyAddedRow').length>0)
						{
							addedRow=1;
						}
					}
				});
				if(tempRowData!='')
				{
					if(addedRow!=1)
					{
						alreadyAddedRowCount+=1;
					}
					addedRow=0;
					rowCount+=1;
					if(rowCount%2==1)
					{
						currrentSelectionHTML+='<tr>'+tempRowData+'</tr>';
					}
					else
					{
						currrentSelectionHTML+='<tr class="CollegeDetailsTableEvenRows">'+tempRowData+'</tr>';
					}
					tempRowData ='';
				}
					
			});
			//alert(submitTimeChoices.toString());
			var uniqueEIINs=uniqueArraySort( currentEIINs );
			
			
			
			var isLSArraysSame=compareTwo2DIntArrays(submitTimeChoices, loadTimeChoices);
			//alert(currentUpdateCount);
			if(currentUpdateCount != undefined && currentUpdateCount != null && currentUpdateCount>5)
			{
				var popUpWindowHeight= 220;
				var popUpWindowWidth=280;
				var alertMessage="You can't update more than 5 (Five) times.";
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			else if(isLSArraysSame==1)
			{
				var popUpWindowHeight= 220;
				var popUpWindowWidth=280;
				var alertMessage="You haven't made any change.";
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			/*if((rowCount-alreadyAddedRowCount)<1)
			{
				var popUpWindowHeight= 210;
				var popUpWindowWidth=270;
				var alertMessage="Please add at least one college.";
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}*/
			else if(uniqueEIINs.length<5)
			{
				var alertMessage="Application failed. You have selected "+uniqueEIINs.length+" ("+numbers[uniqueEIINs.length]+") different colleges. You need to select at least 5 (five) different colleges"
				+"<br>&#2438;&#2476;&#2503;&#2470;&#2472; &#2476;&#2509;&#2479;&#2480;&#2509;&#2469; &#2489;&#2479;&#2492;&#2503;&#2459;&#2503;&#2404; &#2438;&#2474;&#2472;&#2495;  "+numbersb[uniqueEIINs.length]+" ("+numbersbf[uniqueEIINs.length]+") &#2463;&#2495; &#2438;&#2482;&#2494;&#2470;&#2494; &#2453;&#2482;&#2503;&#2460; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; &#2453;&#2480;&#2503;&#2459;&#2503;&#2472;&#2404;  &#2438;&#2474;&#2472;&#2494;&#2453;&#2503; &#2453;&#2478;&#2474;&#2453;&#2509;&#2487;&#2503; &#2539; (&#2474;&#2494;&#2433;&#2458;)&#2463;&#2495;  &#2438;&#2482;&#2494;&#2470;&#2494; &#2453;&#2482;&#2503;&#2460; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; &#2453;&#2480;&#2468;&#2503; &#2489;&#2476;&#2503;";
				var popUpWindowHeight= 250;
				var popUpWindowWidth=310;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			else if (uniqueEIINs.length>10)
			{
				var alertMessage="Application failed. You have selected "+uniqueEIINs.length+" ("+numbers[uniqueEIINs.length]+") different colleges. You can not select more than 10 (ten) different colleges."+
				"<br>&#2438;&#2476;&#2503;&#2470;&#2472; &#2476;&#2509;&#2479;&#2480;&#2509;&#2469; &#2489;&#2479;&#2492;&#2503;&#2459;&#2503;&#2404; &#2438;&#2474;&#2472;&#2495;  "+
				numbersb[uniqueEIINs.length]+" ("+numbersbf[uniqueEIINs.length]+") &#2463;&#2495; &#2438;&#2482;&#2494;&#2470;&#2494; &#2453;&#2482;&#2503;&#2460; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; &#2453;&#2480;&#2503;&#2459;&#2503;&#2472;&#2404;  &#2438;&#2474;&#2472;&#2495; &#2535;&#2534; (&#2470;&#2486;) &#2476;&#2495;&#2477;&#2495;&#2472;&#2509;&#2472; &#2453;&#2482;&#2503;&#2460; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; &#2453;&#2480;&#2468;&#2503; &#2474;&#2494;&#2480;&#2476;&#2503;&#2472; &#2472;&#2494;&#2404;";
				var popUpWindowHeight= 250;
				var popUpWindowWidth=310;
				
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			else if(!isGivenPriorityOkSS(currentPriorities))
			{
				//alert(currentPriorities[1]);
				var popUpWindowHeight= 210;
				var popUpWindowWidth=280;
				var alertMessage="Please correct your priority and then submit again<br/>"+
				"&#2438;&#2474;&#2472;&#2494;&#2480; Priority &#2488;&#2464;&#2495;&#2453; &#2453;&#2480;&#2497;&#2472; &#2447;&#2476;&#2434; &#2468;&#2494;&#2480;&#2474;&#2480; &#2438;&#2476;&#2494;&#2480; Submit &#2453;&#2480;&#2497;&#2472;";
				var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
			    $('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
			    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			else
			{
				var popUpWindowHeight= 552;
				var popUpWindowWidth=780;
				currrentSelectionHTML+='</tbody> </table> </div><a href="#" class="infoPopUpButton big closeSubmitPreview"><span>x</span>Continue To Edit</a> <a href="#" class="infoPopUpButton big red continueToSubmitEditSelection"><span>&#10004;</span>Submit Updated Application</a> </div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentSelectionHTML+"</div>";
				showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			return false;
		});
		$(document).on('click', '#submitButtonSMSOnly', function(event)
				{
					event.preventDefault();
					var rowCount=0;
					var addedRow=0;
					var i=0;
					submitTimeChoices=[];
					var alreadyAddedRowCount=0;
					currentUpdateCount=$('#no_of_update').val();
					//var currrentSelectionHTML='<div id="CollegeDetailsContainerDiv"> <div id="warningMessage"> &#2468;&#2507;&#2478;&#2494;&#2480; &#2468;&#2469;&#2509;&#2479; &#2474;&#2480;&#2495;&#2476;&#2480;&#2509;&#2468;&#2472;&#2503;&#2480;/&#2488;&#2434;&#2479;&#2507;&#2460;&#2472;&#2503;&#2480; &#2438;&#2476;&#2503;&#2470;&#2472;&#2463;&#2495; &#2447;&#2454;&#2472;&#2451; &#2437;&#2488;&#2478;&#2509;&#2474;&#2498;&#2480;&#2509;&#2467; &#2437;&#2476;&#2488;&#2509;&#2469;&#2494;&#2527; &#2480;&#2527;&#2503;&#2459;&#2503;&#2404; &#2468;&#2507;&#2478;&#2494;&#2480; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2468;&#2469;&#2509;&#2479; &#2477;&#2494;&#2482;&#2477;&#2494;&#2476;&#2503; &#2474;&#2480;&#2496;&#2453;&#2509;&#2487;&#2494; &#2453;&#2480;&#2494;&#2480; &#2474;&#2480; Submit Button &#2463;&#2495; Click &#2453;&#2480;&#2482;&#2503;&#2439; &#2453;&#2503;&#2476;&#2482; &#2478;&#2494;&#2468;&#2509;&#2480; &#2468;&#2507;&#2478;&#2494;&#2480; &#2488;&#2434;&#2480;&#2453;&#2509;&#2487;&#2495;&#2468; &#2468;&#2469;&#2509;&#2479; &#2474;&#2480;&#2495;&#2476;&#2480;&#2509;&#2468;&#2495;&#2468; &#2489;&#2476;&#2503; &#2404; Web-&#2447; &#2474;&#2509;&#2480;&#2469;&#2478;&#2476;&#2494;&#2480; &#2438;&#2476;&#2503;&#2470;&#2472; &#2488;&#2478;&#2509;&#2474;&#2498;&#2480;&#2509;&#2467; &#2489;&#2476;&#2494;&#2480; &#2474;&#2480;&#2503; &#2468;&#2497;&#2478;&#2495; Web Message &#2447;&#2476;&#2434; &#2468;&#2507;&#2478;&#2494;&#2480; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2478;&#2507;&#2476;&#2494;&#2439;&#2482; &#2475;&#2507;&#2472; &#2472;&#2478;&#2509;&#2476;&#2480;&#2503; &#2472;&#2495;&#2486;&#2509;&#2458;&#2495;&#2468;&#2453;&#2480;&#2467; SMS &#2474;&#2494;&#2476;&#2503;&#2404;<br/>'
					
					var currrentSelectionHTML='<div id="CollegeDetailsContainerDiv"> <div id="warningMessage"> &#2438;&#2474;&#2472;&#2494;&#2480; &#2468;&#2469;&#2509;&#2479; &#2474;&#2480;&#2495;&#2476;&#2480;&#2509;&#2468;&#2472;&#2503;&#2480;/&#2488;&#2434;&#2479;&#2507;&#2460;&#2472;&#2503;&#2480; &#2438;&#2476;&#2503;&#2470;&#2472;&#2463;&#2495; &#2447;&#2454;&#2472;&#2451; &#2437;&#2488;&#2478;&#2509;&#2474;&#2498;&#2480;&#2509;&#2467; &#2437;&#2476;&#2488;&#2509;&#2469;&#2494;&#2527; &#2480;&#2527;&#2503;&#2459;&#2503;&#2404; &#2438;&#2474;&#2472;&#2494;&#2480; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2468;&#2469;&#2509;&#2479; &#2477;&#2494;&#2482;&#2477;&#2494;&#2476;&#2503; &#2474;&#2480;&#2496;&#2453;&#2509;&#2487;&#2494; &#2453;&#2480;&#2494;&#2480; &#2474;&#2480; Submit Button&#2463;&#2495; Click &#2453;&#2480;&#2482;&#2503;&#2439; &#2453;&#2503;&#2476;&#2482; &#2478;&#2494;&#2468;&#2509;&#2480; &#2438;&#2474;&#2472;&#2494;&#2480; &#2488;&#2434;&#2480;&#2453;&#2509;&#2487;&#2495;&#2468; &#2468;&#2469;&#2509;&#2479; &#2474;&#2480;&#2495;&#2476;&#2480;&#2509;&#2468;&#2495;&#2468; &#2489;&#2476;&#2503;&#2404;<br/>'
					
					if (currentUpdateCount != undefined && currentUpdateCount != null) 
					{
						currrentSelectionHTML+='<span class="updateCountMessage">&#2438;&#2474;&#2472;&#2495; &#2439;&#2468;&#2495;&#2478;&#2471;&#2509;&#2479;&#2503; &#2438;&#2474;&#2472;&#2494;&#2480; &#2438;&#2476;&#2503;&#2470;&#2472;&#2463;&#2495; '+bengaliNumber[currentUpdateCount]+' ('+numbersbfj[currentUpdateCount]+') &#2476;&#2494;&#2480; &#2438;&#2474;&#2465;&#2503;&#2463; &#2453;&#2480;&#2503;&#2459;&#2503;&#2472;&#2404; </span>';
					}
					
					
					
					currrentSelectionHTML+=' &#2438;&#2474;&#2472;&#2495; &#2438;&#2474;&#2472;&#2494;&#2480; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2468;&#2469;&#2509;&#2479; &#2438;&#2476;&#2503;&#2470;&#2472; &#2488;&#2478;&#2527;&#2488;&#2496;&#2478;&#2494;&#2480; &#2478;&#2471;&#2509;&#2479;&#2503; &#2488;&#2480;&#2509;&#2476;&#2507;&#2458;&#2509;&#2458; &#2539; (&#2474;&#2494;&#2433;&#2458;) &#2476;&#2494;&#2480; &#2474;&#2480;&#2495;&#2476;&#2480;&#2509;&#2468;&#2472; &#2453;&#2480;&#2468;&#2503; &#2474;&#2494;&#2480;&#2476;&#2503;&#2472;&#2404; <br/><br/>Your application update procedure is not completed yet. Please check your provided information carefully before clicking the Submit Button. Your update will be completed only if you click the Submit Button.<br/>'
					if (currentUpdateCount != undefined && currentUpdateCount != null) 
					{
						currrentSelectionHTML+='<span class="updateCountMessage">You have already updated your application '+currentUpdateCount+' ('+numbersj[currentUpdateCount]+') '+((currentUpdateCount>1) ? 'times' : 'time')+'.</span>';
					}
					currrentSelectionHTML+=' You can update your information at most 5 (five) times within the application deadline.</div> <div id="currentCollegeSelection"> Selected Colleges : </div> <div class="CollegeDetailsTableDiv"> <table class="CollegeDetailsTable"> <tbody> <tr> <!--<th>SL.</th> --> <th>College Name</th> <th>EIIN</th> <th>Shift</th> <th>Version</th> <th>Group</th> <th>SQ</th> <th>Priority</th></tr>';
					var currentEIINs=[];
					var currentPriorities=[];
					if($("[name='applicant.application_info.quota_ff']").prop('checked')==true)
					{
						submitTimeChoices.push([]);
						submitTimeChoices[i][0]="333333";
						submitTimeChoices[i][1]=1;
						i++;
					}
					else
					{
						submitTimeChoices.push([]);
						submitTimeChoices[i][0]="333333";
						submitTimeChoices[i][1]=0;
						i++;
					}
					if($("[name='applicant.application_info.quota_expatriate']").prop('checked')==true)
					{
						submitTimeChoices.push([]);
						submitTimeChoices[i][0]="999999";
						submitTimeChoices[i][1]=1;
						i++;
					}
					else
					{
						submitTimeChoices.push([]);
						submitTimeChoices[i][0]="999999";
						submitTimeChoices[i][1]=0;
						i++;
					}
					if($("[name='applicant.application_info.quota_eq']").prop('checked')==true)
					{
						submitTimeChoices.push([]);
						submitTimeChoices[i][0]="666666";
						submitTimeChoices[i][1]=1;
						i++;
					}
					else
					{
						submitTimeChoices.push([]);
						submitTimeChoices[i][0]="666666";
						submitTimeChoices[i][1]=0;
						i++;
					}
					$('#selection_row_table').find('tr').each(function (rowIndex, r) 
					{
						var priority = $(this).find('td:eq(7)').children('input').val()
						var tempRowData ='';
						$(this).find('td').each(function (colIndex, c) 
						{
							if(colIndex==2)
							{
								currentEIINs.push(c.textContent);
								submitTimeChoices.push([]);
								submitTimeChoices[i][0]=c.textContent;
							}
							if(colIndex>0 &&  colIndex<7) //number 7 GB quota
								tempRowData+='<td>'+c.textContent+'</td>';
							if(colIndex==7)
							{
								tempRowData+='<td>'+parseInt(priority)+'</td>';
								currentPriorities.push(priority);
								submitTimeChoices[i][1]=priority;
								i++;
							}
							else if(colIndex==8)
							{
								if($(c).has('.newlyAddedRow').length>0)
								{
									addedRow=1;
								}
							}
						});
						if(tempRowData!='')
						{
							if(addedRow!=1)
							{
								alreadyAddedRowCount+=1;
							}
							addedRow=0;
							rowCount+=1;
							if(rowCount%2==1)
							{
								currrentSelectionHTML+='<tr>'+tempRowData+'</tr>';
							}
							else
							{
								currrentSelectionHTML+='<tr class="CollegeDetailsTableEvenRows">'+tempRowData+'</tr>';
							}
							tempRowData ='';
						}
							
					});

					//alert(submitTimeChoices.toString());
					var uniqueEIINs=uniqueArraySort( currentEIINs );
					
					
					
					var isLSArraysSame=compareTwo2DIntArrays(submitTimeChoices, loadTimeChoices);
					//alert(currentUpdateCount);
					if(currentUpdateCount != undefined && currentUpdateCount != null && currentUpdateCount>5)
					{
						var popUpWindowHeight= 220;
						var popUpWindowWidth=280;
						var alertMessage="You can't update more than 5 (Five) times.";
						
						var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
						$('#CollegeDetailsPopUpContainerDiv').remove();
						var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
					    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
					}
					else if(isLSArraysSame==1)
					{
						var popUpWindowHeight= 220;
						var popUpWindowWidth=280;
						var alertMessage="You haven't made any change.";
						
						var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
						$('#CollegeDetailsPopUpContainerDiv').remove();
						var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
					    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
					}

					else if(!isGivenPriorityOkSS(currentPriorities))
					{
						//alert(currentPriorities[1]);
						var popUpWindowHeight= 210;
						var popUpWindowWidth=280;
						var alertMessage="Please correct your priority and then submit again<br/>"+
						"&#2438;&#2474;&#2472;&#2494;&#2480; Priority &#2488;&#2464;&#2495;&#2453; &#2453;&#2480;&#2497;&#2472; &#2447;&#2476;&#2434; &#2468;&#2494;&#2480;&#2474;&#2480; &#2438;&#2476;&#2494;&#2480; Submit &#2453;&#2480;&#2497;&#2472;";
						var currrentHTML='<div class="errorPromptDiv"><div class="promptDivIcon">&#9587;</div><div class="promptDivMessage">'+alertMessage+'</div></div>';
					    $('#CollegeDetailsPopUpContainerDiv').remove();
						var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentHTML+"</div>";
					    showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
					}
					else
					{
						var popUpWindowHeight= 552;
						var popUpWindowWidth=780;
						currrentSelectionHTML+='</tbody> </table> </div><a href="#" class="infoPopUpButton big closeSubmitPreview"><span>x</span>Continue To Edit</a> <a href="#" class="infoPopUpButton big red continueToSubmitEditSelection"><span>&#10004;</span>Submit Updated Application</a> </div>';
						$('#CollegeDetailsPopUpContainerDiv').remove();
						var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentSelectionHTML+"</div>";
						showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
					}
					return false;
				});
		
		
		
		//end by joy
		
		$(document).on('click', '.collgeNameLink.linkDisabled', function(event)
		{
			event.preventDefault();
			return false;
		});
		
		$(document).on('click', '#FreedomFighterQuota', function(event)
		{
			event.preventDefault();
			var popUpWindowHeight= 450;
			var popUpWindowWidth=450;
			var popUpHTML=$("#popUpDiv1").html();
			$('#CollegeDetailsPopUpContainerDiv').remove();
			var y='<div id="CollegeDetailsPopUpContainerDiv">'+popUpHTML+"</div>";
			showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			return false;
		});
		$(document).on('click', '#FreedomFighterQuotaCheckBox', function(event)
		{
			if(document.getElementById('FreedomFighterQuotaCheckBox').checked)
			{
				var popUpWindowHeight= 450;
				var popUpWindowWidth=450;
				var popUpHTML=$("#popUpDiv1").html();
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+popUpHTML+"</div>";
				showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			 //joy  
			 //document.getElementById("errorMessageDiv").style.display="block";
		  	 //callTheDivNew("errorMessageDiv");
	 		//end joy 
		  	 
		});
		$(document).on('click', '#ExpatriateQuota', function(event)
		{
			event.preventDefault();
			var popUpWindowHeight= 450;
			var popUpWindowWidth=450;
			var popUpHTML=$("#popUpDiv2").html();
			$('#CollegeDetailsPopUpContainerDiv').remove();
			var y='<div id="CollegeDetailsPopUpContainerDiv">'+popUpHTML+"</div>";
			showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			return false;
		});
		$(document).on('click', '#ExpatriateQuotaCheckBox', function(event)
		{
			if(document.getElementById('ExpatriateQuotaCheckBox').checked)
			{
				var popUpWindowHeight= 450;
				var popUpWindowWidth=450;
				var popUpHTML=$("#popUpDiv2").html();
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+popUpHTML+"</div>";
				showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			//joy
			 document.getElementById("errorMessageDiv").style.display="block";
		  	 callTheDivNew("errorMessageDiv");
	 		//end joy
		});
		$(document).on('click', '#EducationQuota', function(event)
		{
			event.preventDefault();
			var popUpWindowHeight= 450;
			var popUpWindowWidth=600;
			var popUpHTML=$("#popUpDiv3").html();
			$('#CollegeDetailsPopUpContainerDiv').remove();
			var y='<div id="CollegeDetailsPopUpContainerDiv">'+popUpHTML+"</div>";
			showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			return false;
		});
		$(document).on('click', '#GBQuota', function(event)
		{
			event.preventDefault();
			var popUpWindowHeight= 450;
			var popUpWindowWidth=450;
			var popUpHTML=$("#popUpDiv4").html();
			$('#CollegeDetailsPopUpContainerDiv').remove();
			var y='<div id="CollegeDetailsPopUpContainerDiv">'+popUpHTML+"</div>";
			showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			return false;
		});
		$(document).on('click', '#EducationQuotaCheckBox', function(event)
		{
			if(document.getElementById('EducationQuotaCheckBox').checked)
			{
				var popUpWindowHeight= 450;
				var popUpWindowWidth=450;
				var popUpHTML=$("#popUpDiv3").html();
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+popUpHTML+"</div>";
				showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			//joy
			 document.getElementById("errorMessageDiv").style.display="block";
		  	 callTheDivNew("errorMessageDiv");
	 		//end joy
		});
		$(document).on('change', '#gb_quota', function(event)
		{
			if($('#gb_quota').val()=="Y")
			{
				var popUpWindowHeight= 450;
				var popUpWindowWidth=450;
				var popUpHTML=$("#popUpDiv4").html();
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+popUpHTML+"</div>";
				showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
		});
		$(document).on('click', '.collgeNameLink.linkEnabled, .infoPopUpButton.moreDetailsButton', function(event)
		{
			
			event.preventDefault();
			//alert('asdasd');
			if(currentCollegeName.length>0 && currentCollegeEIIN.length>0 && sshowsift.length>0 && sshowversion.length>0 && sshowgroup.length>0 && sshowgender.length>0)
			{
				var popUpWindowHeight= 500;
				var popUpWindowWidth=750;
				var collegeDetailsHTML='<div id="CollegeDetailsContainerDiv"> <div id="CollegeName">College Name : <span>'+currentCollegeName+'</span></div> <div id="CollegeEIIN">College EIIN : <span>'+currentCollegeEIIN+'</span></div> <div id="CollegeDetails">College Details :</div> <div class="CollegeDetailsTableDiv"> <table class="CollegeDetailsTable"> <tr> <th>Shift</th> <th>Versions</th> <th>Group</th> <th>Gender</th> <th>Total Seat Count</th><th>Remaining Seat Count</th> </tr>';
				for(var i=0; i<sshowsift.length; i++)
				{
					if(i%2==0)
					{
						collegeDetailsHTML+='<tr><td>'+sshowsift[i]+'</td> <td>'+sshowversion[i]+'</td> <td>'+sshowgroup[i]+'</td> <td>'+sshowgender[i]+'</td> <td>'+sshowseatcount[i]+'</td><td>'+sshowAvailableseatcount[i]+'</td> </tr>';
					}
					else
					{
						collegeDetailsHTML+='<tr class="CollegeDetailsTableEvenRows"><td>'+sshowsift[i]+'</td> <td>'+sshowversion[i]+'</td> <td>'+sshowgroup[i]+'</td> <td>'+sshowgender[i]+'</td> <td>'+sshowseatcount[i]+'</td><td>'+sshowAvailableseatcount[i]+'</td> </tr>';
					}
				}
				collegeDetailsHTML+='</table> </div>  </div>';
				//collegeDetailsHTML+='</table> </div> <a href="#" class="infoPopUpButton big red complainButton"><span>x</span>&#2477;&#2497;&#2482; &#2468;&#2469;&#2509;&#2479;&#2503;&#2480; &#2460;&#2472;&#2509;&#2479; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455; &#2453;&#2480;&#2497;&#2472;</a> </div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+collegeDetailsHTML+"</div>";
				showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}			 			 
			return false;
		});
		
		$(document).on('click', '.infoPopUpButton.complainButton', function(event)
		{
			event.preventDefault();
			removePopWindowMaskWA();
			if(currentCollegeName.length>0 && currentCollegeEIIN.length>0 && sshowsift.length>0 && sshowversion.length>0 && sshowgroup.length>0 && sshowgender.length>0)
			{
				var popUpWindowHeight= 470;
				var popUpWindowWidth=590;
				var compalinHolderHTML='<form id="complainForm"><h3>&#2488;&#2478;&#2488;&#2509;&#2479;&#2494; &#2458;&#2495;&#2489;&#2509;&#2472;&#2495;&#2468; &#2453;&#2480;&#2497;&#2472;</h3><div class="styled-select blue semi-square"><select id="problemType"><option value="defaultSelection">&#2479;&#2503;&#2453;&#2507;&#2472; &#2447;&#2453;&#2463;&#2495; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; &#2453;&#2480;&#2497;&#2472;</option><option value="wrongUnit">&#2453;&#2482;&#2503;&#2460;&#2503;&#2480; &#2476;&#2495;&#2477;&#2494;&#2455; &#2464;&#2495;&#2453; &#2472;&#2494;&#2439;</option><option value="wrongMedium">&#2453;&#2482;&#2503;&#2460;&#2503;&#2480; &#2474;&#2465;&#2492;&#2494;&#2482;&#2503;&#2454;&#2494;&#2480; &#2478;&#2494;&#2471;&#2509;&#2479;&#2478; &#2464;&#2495;&#2453; &#2472;&#2494;&#2439;</option><option value="wrongSeatCount">&#2453;&#2482;&#2503;&#2460;&#2503;&#2480; &#2459;&#2494;&#2468;&#2509;&#2480;-&#2459;&#2494;&#2468;&#2509;&#2480;&#2496;&#2480; &#2438;&#2488;&#2472; &#2468;&#2469;&#2509;&#2479; &#2477;&#2497;&#2482;</option><option value="wrongType">&#2453;&#2482;&#2503;&#2460;&#2503;&#2480; &#2480;&#2453;&#2478; &#2477;&#2497;&#2482;</option></select></div><div id="problemDescriptionHolder"></div></form>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+compalinHolderHTML+"</div>";
				showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			return false;
		});
		$(document).on('click', '.infoPopUpButtonDisabled', function(event)
		{
			event.preventDefault();
			return false;
		});
		$(document).on('click', 'a.closeIIS, #mask', function(event)
		{
			removePopWindowMask();
		});
		$(document).on('change', '#problemType', function()
		{
			var currentSelectedItem=$(this).val();
			
			if(currentSelectedItem=="wrongUnit")
			{
				var problemDescriptionHTML='<div id="wrongUnitOptions"><div id="notExistsUnitContainerDiv"><h4 class="notExistsUnitHeader">&#2472;&#2495;&#2458;&#2503;&#2480; &#2476;&#2495;&#2477;&#2494;&#2455;&#2455;&#2497;&#2482;&#2507; &#2453;&#2482;&#2503;&#2460;&#2503; &#2472;&#2494;&#2439;, &#2453;&#2495;&#2472;&#2509;&#2468;&#2497; &#2441;&#2482;&#2509;&#2482;&#2503;&#2454; &#2453;&#2480;&#2494; &#2489;&#2479;&#2492;&#2503;&#2459;&#2503;</h4>';
				var currentlySelectedBoard=$( "#helper_board_id option:selected" ).val();
				if(currentlySelectedBoard==1)
					var allGroupArray=['Science','Business Studies','Humanities','Music','Home Economics','Islamic Studies'];
				else if (currentlySelectedBoard==2)
					var allGroupArray=['MADRASA - General','MADRASA - Science','MADRASA - Muzzabid'];
				else if (currentlySelectedBoard==3)
					var allGroupArray=['HSCVOC - Machine Tools Operation and Maintenance','HSCVOC - Poultry Rearing and Farming','HSCVOC - Refrigeration and Air-conditioning','HSCVOC - Welding and Fabrication','HSCVOC - Industrial Wood Working','HSCVOC - Wet Processing','HSCVOC - Yarn and Fabric Manufacturing','HSCBM - Accounting','HSCBM - Banking','HSCBM - Computer Operation','HSCBM - Entrepreneurship Development','HSCBM - Secretarial Science','DCOM - Shorthand','HSCVOC - Fish Culture and Breeding','HSCVOC - Electronic Control and Communication','HSCVOC - Electrical Works and Maintenance','HSCVOC - Drafting Civil','HSCVOC - Computer Operation and Maintenance','HSCVOC - Clothing and Garments Finishing','HSCVOC - Building Maintenance and Construction','HSCVOC - Automobile','HSCVOC - Agro Machinery','DCOM - Accounting','HSCVOC - Warehouse and Storekeeping','HSCVOC - Home Economics'];
				uniqueGroups=uniqueArraySort( sshowgroup );
				for(var i=0; i<uniqueGroups.length; i++)
				{
					problemDescriptionHTML+='<input type="checkbox" name="notExistUnit" value="'+uniqueGroups[i]+'">'+uniqueGroups[i]+'<br>';
				}
				problemDescriptionHTML+='</div>';
				var notExistingGroupsArray=$(allGroupArray).not(sshowgroup);
				if(notExistingGroupsArray.length>0)
				{
					problemDescriptionHTML+='<div id="ExistsUnitContainerDiv"><h4 class="notExistsUnitHeader">&#2472;&#2495;&#2458;&#2503;&#2480; &#2476;&#2495;&#2477;&#2494;&#2455;&#2455;&#2497;&#2482;&#2507; &#2453;&#2482;&#2503;&#2460;&#2503; &#2438;&#2459;&#2503;, &#2453;&#2495;&#2472;&#2509;&#2468;&#2497; &#2441;&#2482;&#2509;&#2482;&#2503;&#2454; &#2453;&#2480;&#2494; &#2489;&#2479;&#2492; &#2472;&#2495;</h4>';
					for(var i=0; i<notExistingGroupsArray.length; i++)
					{
						problemDescriptionHTML+='<input type="checkbox" name="ExistUnit" value="'+notExistingGroupsArray[i]+'">'+notExistingGroupsArray[i]+'<br>';
					}
					problemDescriptionHTML+='</div>';
				}
				problemDescriptionHTML+='</div><a href="#" class="infoPopUpButton big"><span>&#10003;</span>&#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2453;&#2480;&#2497;&#2472; </a>';
				$('#problemDescriptionHolder').empty().stop().css('opacity', '0').html(problemDescriptionHTML).animate({opacity: 1}, 300); 
			}
			else if(currentSelectedItem=="wrongMedium")
			{
				var problemDescriptionHTML='<div id="wrongMediumOptions"><h3>&#2476;&#2495;&#2477;&#2494;&#2455;</h3><div class="styled-select blue semi-square"><select id="wrongMediumUnit"><option value="defaultSelection">&#2479;&#2503;&#2453;&#2507;&#2472; &#2447;&#2453;&#2463;&#2495; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; &#2453;&#2480;&#2497;&#2472;</option>';
				uniqueGroups=uniqueArraySort( sshowgroup );
				for(var i=0; i<uniqueGroups.length; i++)
				{
					problemDescriptionHTML+='<option value="'+uniqueGroups[i]+'">'+uniqueGroups[i]+'</option>';
				} 
				problemDescriptionHTML+='</select></div><div id="subProblemDescriptionHolder"></div></div>';
				$('#problemDescriptionHolder').empty().stop().css('opacity', '0').html(problemDescriptionHTML).animate({opacity: 1}, 100); 
			}
			else if(currentSelectedItem=="wrongSeatCount" || currentSelectedItem=="wrongType")
			{
				var problemDescriptionHTML='<div id="wrongSeatCountOptions"><h3>&#2486;&#2495;&#2475;&#2463;</h3><div class="styled-select blue semi-square"><select id="wrongSeatCountShift"><option value="defaultSelection">&#2479;&#2503;&#2453;&#2507;&#2472; &#2447;&#2453;&#2463;&#2495; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; &#2453;&#2480;&#2497;&#2472;</option>';
				uniqueShifts=uniqueArraySort( sshowsift );
				for(var i=0; i<uniqueShifts.length; i++)
				{
					problemDescriptionHTML+='<option value="'+uniqueShifts[i]+'">'+uniqueShifts[i]+'</option>';
				}
				problemDescriptionHTML+='</select></div><div id="wrongSeatCountSubOptions"></div></div>';
				$('#problemDescriptionHolder').empty().stop().css('opacity', '0').html(problemDescriptionHTML).animate({opacity: 1}, 100); 
			}
			else
			{
				$('#problemDescriptionHolder').empty();
			}
			
		});

		$(document).on('change', '#wrongMediumUnit', function()
		{
			var currentSelectedItem=$(this).val();
			
			if(currentSelectedItem!="defaultSelection")
			{
				var subProblemDescriptionHTML='';
				var currentVersionsArray = [];
				var allVersionsArray = ["Bangla","English"];
				for(var i=0; i<sshowversion.length; i++)
				{
					if(sshowgroup[i]==currentSelectedItem)
					{
						currentVersionsArray.push(sshowversion[i]);
					}
				}
				currentVersionsArray=uniqueArraySort( currentVersionsArray );
				if(currentVersionsArray.length > 0)
				{
					subProblemDescriptionHTML+='<div id="notExistsMediumContainerDiv"><h4 class="notExistsMediumHeader">&#2472;&#2495;&#2458;&#2503;&#2480; &#2478;&#2494;&#2471;&#2509;&#2479;&#2478;&#2455;&#2497;&#2482;&#2507; &#2453;&#2482;&#2503;&#2460;&#2503; &#2472;&#2494;&#2439;, &#2453;&#2495;&#2472;&#2509;&#2468;&#2497; &#2441;&#2482;&#2509;&#2482;&#2503;&#2454; &#2453;&#2480;&#2494; &#2489;&#2479;&#2492;&#2503;&#2459;&#2503;</h4>';
				
					for(var i=0; i<currentVersionsArray.length; i++)
					{
						subProblemDescriptionHTML+='<input type="checkbox" name="notExistMedium" value="'+currentVersionsArray[i]+'">'+currentVersionsArray[i]+'<br>';
					}
					subProblemDescriptionHTML+='</div>';
				}

				var notExistingVersionArray=$(allVersionsArray).not(currentVersionsArray);
				if(notExistingVersionArray.length>0)
				{
					subProblemDescriptionHTML+='<div id="ExistsMediumContainerDiv"><h4 class="notExistsMediumHeader">&#2472;&#2495;&#2458;&#2503;&#2480; &#2478;&#2494;&#2471;&#2509;&#2479;&#2478;&#2455;&#2497;&#2482;&#2507; &#2453;&#2482;&#2503;&#2460;&#2503; &#2438;&#2459;&#2503;, &#2453;&#2495;&#2472;&#2509;&#2468;&#2497; &#2441;&#2482;&#2509;&#2482;&#2503;&#2454; &#2453;&#2480;&#2494; &#2489;&#2479;&#2492; &#2472;&#2495;</h4>';
					for(var i=0; i<notExistingVersionArray.length; i++)
					{
						subProblemDescriptionHTML+='<input type="checkbox" name="ExistMedium" value="'+notExistingVersionArray[i]+'">'+notExistingVersionArray[i]+'<br>';
					}
					subProblemDescriptionHTML+='</div>';
				}
				subProblemDescriptionHTML+='<a href="#" class="infoPopUpButton big"><span>&#10003;</span>&#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2453;&#2480;&#2497;&#2472; </a>';
				$('#subProblemDescriptionHolder').empty().stop().css('opacity', '0').html(subProblemDescriptionHTML).animate({opacity: 1}, 100); 
			}
			else
			{
				$('#subProblemDescriptionHolder').empty();
			}
		});
		$(document).on('change', '#wrongSeatCountShift', function()
		{
			var currentSelectedItem=$(this).val();
			
			if(currentSelectedItem!="defaultSelection")
			{
				var wrongSeatCountSubOptionsHTML='<h3>&#2478;&#2494;&#2471;&#2509;&#2479;&#2478;</h3><div class="styled-select blue semi-square"><select id="wrongSeatCountMedium"><option value="defaultSelection">&#2479;&#2503;&#2453;&#2507;&#2472; &#2447;&#2453;&#2463;&#2495; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; &#2453;&#2480;&#2497;&#2472;</option>';
				var currentVersions=[];
				for(var i=0; i<sshowversion.length; i++)
				{
					if(sshowsift[i]==currentSelectedItem)
					{
						currentVersions.push(sshowversion[i]);
					}
				}
				
				uniqueVersions=uniqueArraySort( currentVersions );
				for(var i=0; i<uniqueVersions.length; i++)
				{
					wrongSeatCountSubOptionsHTML+='<option value="'+uniqueVersions[i]+'">'+uniqueVersions[i]+'</option>';
				}
				wrongSeatCountSubOptionsHTML+='</select></div><div id="wrongSeatCountSubSubOptions"></div>';
				$('#wrongSeatCountSubOptions').empty().stop().css('opacity', '0').html(wrongSeatCountSubOptionsHTML).animate({opacity: 1}, 100); 				
			}
			else
			{
				$('#subProblemDescriptionHolder').empty();
			}
		});
		$(document).on('change', '#wrongSeatCountMedium', function()
		{
			var currentSelectedMedium=$(this).val();
			var currentSelectedShift=$("#wrongSeatCountShift option:selected").val();;
			
			if(currentSelectedShift!="defaultSelection")
			{
				var wrongSeatCountSubSubOptionsHTML='<h3>&#2476;&#2495;&#2477;&#2494;&#2455;</h3><div class="styled-select blue semi-square"><select id="wrongSeatCountUnit"><option value="defaultSelection">&#2479;&#2503;&#2453;&#2507;&#2472; &#2447;&#2453;&#2463;&#2495; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; &#2453;&#2480;&#2497;&#2472;</option>';
				for(var i=0; i<sshowgroup.length; i++)
				{
					if(sshowversion[i]==currentSelectedMedium && sshowsift[i]==currentSelectedShift)
					{
						wrongSeatCountSubSubOptionsHTML+='<option value="'+sshowgroup[i]+'">'+sshowgroup[i]+'</option>';
					}
				}
				wrongSeatCountSubSubOptionsHTML+='</select></div><div id="subProblemDescriptionHolder"></div>';
				$('#wrongSeatCountSubSubOptions').empty().stop().css('opacity', '0').html(wrongSeatCountSubSubOptionsHTML).animate({opacity: 1}, 100); 
				
				
				
			}
			else
			{
				$('#subProblemDescriptionHolder').empty();
			}
		});
		$(document).on('change', '#wrongSeatCountUnit', function()
		{
			var currentSelectedMedium=$("#wrongSeatCountMedium option:selected").val();
			var currentSelectedShift=$("#wrongSeatCountShift option:selected").val();
			var currentSelectedUnit=$(this).val();
			var currentSeatCount;
			
			if(currentSelectedShift!="defaultSelection")
			{
				var currentlySelectedProblemType=$("#problemType option:selected").val();
				if(currentlySelectedProblemType=="wrongSeatCount")
				{
					currentSeatCount='';
					for(var i=0; i<sshowseatcount.length; i++)
					{
						if(sshowgroup[i]==currentSelectedUnit && sshowsift[i]==currentSelectedShift && sshowversion[i]==currentSelectedMedium)
						{
							currentSeatCount=sshowseatcount[i];
						}
					}
					var subProblemDescriptionHTML;
					if(currentSeatCount=='')
					{
						subProblemDescriptionHTML='<h4 class="wrongSeatCountHeader"> &#2474;&#2509;&#2480;&#2453;&#2499;&#2468; &#2438;&#2488;&#2472;&#2488;&#2434;&#2454;&#2509;&#2479;&#2494; '+'<input type="text" id="realSeatCount" name="realSeatCount"> &#2489;&#2476;&#2503;</h4><a href="#" class="infoPopUpButton big"><span>&#10003;</span>&#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2453;&#2480;&#2497;&#2472; </a>';
					}
					else
					{
						subProblemDescriptionHTML='<h4 class="wrongSeatCountHeader">&#2476;&#2480;&#2509;&#2468;&#2478;&#2494;&#2472;&#2503; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2495;&#2468; &#2453;&#2482;&#2503;&#2460;&#2503;&#2480; '+currentSelectedShift+' &#2486;&#2495;&#2475;&#2463;&#2503;&#2480; '+currentSelectedMedium+' &#2478;&#2494;&#2471;&#2509;&#2479;&#2478;&#2503;&#2480; '+currentSelectedUnit+' &#2476;&#2495;&#2477;&#2494;&#2455;&#2503; &#2438;&#2488;&#2472; &#2488;&#2434;&#2454;&#2509;&#2479;&#2494; '+currentSeatCount+' &#2441;&#2482;&#2509;&#2482;&#2503;&#2454; &#2453;&#2480;&#2494; &#2489;&#2479;&#2492;&#2503;&#2459;&#2503;, &#2453;&#2495;&#2472;&#2509;&#2468;&#2497; &#2474;&#2509;&#2480;&#2453;&#2499;&#2468; &#2438;&#2488;&#2472;&#2488;&#2434;&#2454;&#2509;&#2479;&#2494; '+'<input type="text" id="realSeatCount" name="realSeatCount"> &#2489;&#2476;&#2503;</h4><a href="#" class="infoPopUpButton big"><span>&#10003;</span>&#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2453;&#2480;&#2497;&#2472; </a>';
					}
					$('#subProblemDescriptionHolder').empty().stop().css('opacity', '0').html(subProblemDescriptionHTML).animate({opacity: 1}, 100); 	
				}
				else if(currentlySelectedProblemType=="wrongType")
				{	
					var subProblemDescriptionHTML='<div id="wrongTypeOptions"><input type="radio" name="wrongTypeOption" id="wrongTypeOption1" value="male" />&#2447;&#2454;&#2494;&#2472;&#2503; &#2486;&#2497;&#2471;&#2497;&#2478;&#2494;&#2468;&#2509;&#2480; &#2459;&#2494;&#2468;&#2509;&#2480;&#2470;&#2503;&#2480; &#2474;&#2524;&#2494;&#2480; &#2476;&#2509;&#2479;&#2476;&#2488;&#2509;&#2469;&#2494; &#2438;&#2459;&#2503;<br><input type="radio" name="wrongTypeOption" id="wrongTypeOption2" value="female" />&#2447;&#2454;&#2494;&#2472;&#2503; &#2486;&#2497;&#2471;&#2497;&#2478;&#2494;&#2468;&#2509;&#2480; &#2459;&#2494;&#2468;&#2509;&#2480;&#2496;&#2470;&#2503;&#2480; &#2474;&#2524;&#2494;&#2480; &#2476;&#2509;&#2479;&#2476;&#2488;&#2509;&#2469;&#2494; &#2438;&#2459;&#2503;<br><input type="radio" name="wrongTypeOption" id="wrongTypeOption3" value="combined" />&#2447;&#2454;&#2494;&#2472;&#2503; &#2459;&#2494;&#2468;&#2509;&#2480; &#2447;&#2476;&#2434; &#2459;&#2494;&#2468;&#2509;&#2480;&#2496; &#2441;&#2477;&#2527;&#2503;&#2480;&#2439; &#2474;&#2524;&#2494;&#2480; &#2476;&#2509;&#2479;&#2476;&#2488;&#2509;&#2469;&#2494; &#2438;&#2459;&#2503;</div><a href="#" class="infoPopUpButton big"><span>&#10003;</span>&#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2453;&#2480;&#2497;&#2472; </a>';
					$('#subProblemDescriptionHolder').empty().stop().css('opacity', '0').html(subProblemDescriptionHTML).animate({opacity: 1}, 100);
				}
			}
			else
			{
				$('#subProblemDescriptionHolder').empty();
			}
		});
		disableCollegeInfoComplainButton();
});

function disableCollegeInfoComplainButton()
{
	$('.infoPopUpButton').addClass('infoPopUpButtonDisabled').removeClass('infoPopUpButton');
	$('.collgeNameLink').addClass('linkDisabled').removeClass('linkEnabled').html('<b id="c_name">College Name</b>');
	$("#sshowsift").html('');
	$("#sshowversion").html('');
	$("#sshowgroup").html('');
	$("#sshowgender").html('');
	
	
}
function enableCollegeInfoComplainButton()
{
	$('.infoPopUpButtonDisabled').addClass('infoPopUpButton').removeClass('infoPopUpButtonDisabled');
	$('.collgeNameLink').addClass('linkEnabled').removeClass('linkDisabled');
}

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

function saveLoadTimeQuotas()
{
	var i=0;
	loadTimeChoices=[];
	if($("[name='applicant.application_info.quota_ff']").prop('checked')==true)
	{
		loadTimeChoices.push([]);
		loadTimeChoices[i][0]="333333";
		loadTimeChoices[i][1]=1;
		i++;
	}
	else
	{
		loadTimeChoices.push([]);
		loadTimeChoices[i][0]="333333";
		loadTimeChoices[i][1]=0;
		i++;
	}
	/*
	if($("[name='applicant.application_info.quota_expatriate']").prop('checked')==true)
	{
		loadTimeChoices.push([]);
		loadTimeChoices[i][0]="999999";
		loadTimeChoices[i][1]=1;
		i++;
	}
	else
	{
		loadTimeChoices.push([]);
		loadTimeChoices[i][0]="999999";
		loadTimeChoices[i][1]=0;
		i++;
	}
	if($("[name='applicant.application_info.quota_eq']").prop('checked')==true)
	{
		loadTimeChoices.push([]);
		loadTimeChoices[i][0]="666666";
		loadTimeChoices[i][1]=1;
		i++;
	}
	else
	{
		loadTimeChoices.push([]);
		loadTimeChoices[i][0]="666666";
		loadTimeChoices[i][1]=0;
		i++;
	}
	*/
}
function saveLoadTimeSelections()
{
	var i=1;
	$('#selection_row_table').find('tr').each(function (rowIndex, r) 
	{
		var priority = $(this).find('td:eq(7)').children('input').val()
		var tempRowData ='';
		var ltt='';
		var ltp='';
		$(this).find('td').each(function (colIndex, c) 
		{
			if(colIndex==2)
				ltt+=c.textContent;
			else if(colIndex==3)
				ltt+=c.textContent;
			else if(colIndex==4)
				ltt+=c.textContent;
			else if(colIndex==5)
			{
				ltt+=c.textContent;
				
			}
			else if(colIndex==7)
			{
				ltp=priority;
				loadTimeChoices.push([]);
				loadTimeChoices[i][0]=ltt;
				loadTimeChoices[i][1]=ltp;
				i++;
			}			
			
		});
			
	});
	
	//alert(loadTimeChoices.toString());
}

function compareTwo2DIntArrays(array1, array2)
{
	if(array1.equals(array2))
		return 1;
	else
		return 0;
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

function setErrorPopupMargin()
{
	var errorPopupWindowWidth=990;
	var leftMarginD=Math.ceil(($(window).width()/2)-((errorPopupWindowWidth+0)/2));
	var cssChangeObj=
	{
		"margin-left" : leftMarginD+"px",
	}
	$('div#errorMessageDiv').css(cssChangeObj);
}




//Warn if overriding existing method
if(Array.prototype.equals)
    console.warn("Overriding existing Array.prototype.equals. Possible causes: New API defines the method, there's a framework conflict or you've got double inclusions in your code.");
// attach the .equals method to Array's prototype to call it on any array
Array.prototype.equals = function (array) {
    // if the other array is a falsy value, return
    if (!array)
        return false;

    // compare lengths - can save a lot of time 
    if (this.length != array.length)
        return false;

    for (var i = 0, l=this.length; i < l; i++) {
        // Check if we have nested arrays
        if (this[i] instanceof Array && array[i] instanceof Array) {
            // recurse into the nested arrays
            if (!this[i].equals(array[i]))
                return false;       
        }           
        else if (this[i] != array[i]) { 
            // Warning - two different object instances will never be equal: {x:20} != {x:20}
            return false;   
        }           
    }       
    return true;
}
// Hide method from for-in loops
Object.defineProperty(Array.prototype, "equals", {enumerable: false});