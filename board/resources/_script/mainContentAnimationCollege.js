$(document).ready(function(){
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
			removePopWindowMask();
			submitApplication_College();
			return false;
		});
		$(document).on('click', '.continueToSubmitEditSelection', function(event)
		{
			event.preventDefault();
			removePopWindowMask();
			submitApplicationEdit_TT();
			return false;
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
			var currrentSelectionHTML='<div id="CollegeDetailsContainerDiv"> <div id="warningMessage"> &#x986;&#x9AA;&#x9A8;&#x9BE;&#x9B0; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2468;&#2469;&#2509;&#2479; &#2488;&#2494;&#2476;&#2471;&#2494;&#2472;&#2503; &#2474;&#2480;&#2496;&#2453;&#2509;&#2487;&#2494; &#x995;&#x9B0;&#x9C7;&#x99B;&#x9C7;&#x9A8;&#x20;&#x995;&#x9BF;&#xA;? &#2447;&#2453;&#2476;&#2494;&#2480; &#2488;&#2494;&#2476;&#2478;&#2495;&#2463; &#2453;&#2480;&#2482;&#2503; &#2477;&#2476;&#2495;&#2487;&#2509;&#2479;&#2468;&#2503; &#2453;&#2507;&#2472; &#2474;&#2480;&#2495;&#2476;&#2480;&#2509;&#2468;&#2472; &#2453;&#2480;&#2494; &#2479;&#2494;&#2476;&#2503; &#2472;&#2494;&#2404; <br> Did you check your provided information carefully? Once you submit, no change can be made in future. </div> <div id="currentCollegeSelection"> Selected Colleges : </div> <div class="CollegeDetailsTableDiv"> <table class="CollegeDetailsTable"> <tbody> <tr> <th>SL.</th> <th>College Name</th> <th>EIIN</th> <th>Shift</th> <th>Version</th> <th>Group</th> <th>SQ</th> </tr>';
			$('#selection_row_table').find('tr').each(function (rowIndex, r) 
			{
				var tempRowData ='';
				$(this).find('td').each(function (colIndex, c) 
				{
					if(colIndex<7)
						tempRowData+='<td>'+c.textContent+'</td>';
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
			if(rowCount<1)
			{
				alert("Please add at least one college.!!!!");
			}
			else
			{
				var popUpWindowHeight= 500;
				var popUpWindowWidth=750;
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
			var alreadyAddedRowCount=0;
			var currrentSelectionHTML='<div id="CollegeDetailsContainerDiv"> <div id="warningMessage"> &#2468;&#2507;&#2478;&#2494;&#2480; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468; &#2468;&#2469;&#2509;&#2479; &#2488;&#2494;&#2476;&#2471;&#2494;&#2472;&#2503; &#2474;&#2480;&#2496;&#2453;&#2509;&#2487;&#2494; &#2453;&#2480;&#2503;&#2459; &#2453;&#2495;? &#2447;&#2453;&#2476;&#2494;&#2480; &#2488;&#2494;&#2476;&#2478;&#2495;&#2463; &#2453;&#2480;&#2482;&#2503; &#2477;&#2476;&#2495;&#2487;&#2509;&#2479;&#2468;&#2503; &#2453;&#2507;&#2472; &#2474;&#2480;&#2495;&#2476;&#2480;&#2509;&#2468;&#2472; &#2453;&#2480;&#2494; &#2479;&#2494;&#2476;&#2503; &#2472;&#2494;&#2404; <br> Did you check your provided information carefully? Once you submit, no change can be made in future. </div> <div id="currentCollegeSelection"> Selected Colleges : </div> <div class="CollegeDetailsTableDiv"><table class="CollegeDetailsTable"> <tbody> <tr> <th>SL.</th> <th>College Name</th> <th>EIIN</th> <th>Shift</th> <th>Version</th> <th>Group</th> <th>SQ</th> </tr>';
			$('#selection_row_table').find('tr').each(function (rowIndex, r) 
			{
				var tempRowData ='';
				$(this).find('td').each(function (colIndex, c) 
				{
					if(colIndex<7)
						tempRowData+='<td>'+c.textContent+'</td>';
					else if(colIndex==7)
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
			if((rowCount-alreadyAddedRowCount)<1)
			{
				alert("Please add at least one college.!!!!");
			}
			else
			{
				var popUpWindowHeight= 500;
				var popUpWindowWidth=750;
				currrentSelectionHTML+='</tbody> </table> </div><a href="#" class="infoPopUpButton big closeSubmitPreview"><span>x</span>Continue To Edit</a> <a href="#" class="infoPopUpButton big red continueToSubmitEditSelection"><span>&#10004;</span>Submit Application</a> </div>';
				$('#CollegeDetailsPopUpContainerDiv').remove();
				var y='<div id="CollegeDetailsPopUpContainerDiv">'+currrentSelectionHTML+"</div>";
				showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			}
			return false;
		});
		
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
			var popUpWindowHeight= 450;
			var popUpWindowWidth=450;
			var popUpHTML=$("#popUpDiv1").html();
			$('#CollegeDetailsPopUpContainerDiv').remove();
			var y='<div id="CollegeDetailsPopUpContainerDiv">'+popUpHTML+"</div>";
			showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
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
			var popUpWindowHeight= 450;
			var popUpWindowWidth=450;
			var popUpHTML=$("#popUpDiv2").html();
			$('#CollegeDetailsPopUpContainerDiv').remove();
			var y='<div id="CollegeDetailsPopUpContainerDiv">'+popUpHTML+"</div>";
			showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
		});
		$(document).on('click', '#EducationQuota', function(event)
		{
			event.preventDefault();
			var popUpWindowHeight= 450;
			var popUpWindowWidth=450;
			var popUpHTML=$("#popUpDiv3").html();
			$('#CollegeDetailsPopUpContainerDiv').remove();
			var y='<div id="CollegeDetailsPopUpContainerDiv">'+popUpHTML+"</div>";
			showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
			return false;
		});
		$(document).on('click', '#EducationQuotaCheckBox', function(event)
		{
			var popUpWindowHeight= 450;
			var popUpWindowWidth=450;
			var popUpHTML=$("#popUpDiv3").html();
			$('#CollegeDetailsPopUpContainerDiv').remove();
			var y='<div id="CollegeDetailsPopUpContainerDiv">'+popUpHTML+"</div>";
			showPopUpWindowWithMask(y, popUpWindowWidth, popUpWindowHeight);
		});
		$(document).on('click', '.collgeNameLink.linkEnabled, .infoPopUpButton.moreDetailsButton', function(event)
		{
			event.preventDefault();
			//alert('asdasd');
			if(currentCollegeName.length>0 && currentCollegeEIIN.length>0 && sshowsift.length>0 && sshowversion.length>0 && sshowgroup.length>0 && sshowgender.length>0)
			{
				var popUpWindowHeight= 500;
				var popUpWindowWidth=750;
				var collegeDetailsHTML='<div id="CollegeDetailsContainerDiv"> <div id="CollegeName">College Name : <span>'+currentCollegeName+'</span></div> <div id="CollegeEIIN">College EIIN : <span>'+currentCollegeEIIN+'</span></div> <div id="CollegeDetails">College Details :</div> <div class="CollegeDetailsTableDiv"> <table class="CollegeDetailsTable"> <tr> <th>Shift</th> <th>Versions</th> <th>Group</th> <th>Gender</th> <th>Seat Count</th> </tr>';
				for(var i=0; i<sshowsift.length; i++)
				{
					if(i%2==0)
					{
						collegeDetailsHTML+='<tr><td>'+sshowsift[i]+'</td> <td>'+sshowversion[i]+'</td> <td>'+sshowgroup[i]+'</td> <td>'+sshowgender[i]+'</td> <td>'+sshowseatcount[i]+'</td> </tr>';
					}
					else
					{
						collegeDetailsHTML+='<tr class="CollegeDetailsTableEvenRows"><td>'+sshowsift[i]+'</td> <td>'+sshowversion[i]+'</td> <td>'+sshowgroup[i]+'</td> <td>'+sshowgender[i]+'</td> <td>'+sshowseatcount[i]+'</td> </tr>';
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
				var compalinHolderHTML='<form id="complainForm"><h3>à¦¸à¦®à¦¸à§�à¦¯à¦¾ à¦šà¦¿à¦¹à§�à¦¨à¦¿à¦¤ à¦•à¦°à§�à¦¨</h3><div class="styled-select blue semi-square"><select id="problemType"><option value="defaultSelection">à¦¯à§‡à¦•à§‹à¦¨ à¦�à¦•à¦Ÿà¦¿ à¦¨à¦¿à¦°à§�à¦¬à¦¾à¦šà¦¨ à¦•à¦°à§�à¦¨</option><option value="wrongUnit">à¦•à¦²à§‡à¦œà§‡à¦° à¦¬à¦¿à¦­à¦¾à¦— à¦ à¦¿à¦• à¦¨à¦¾à¦‡</option><option value="wrongMedium">à¦•à¦²à§‡à¦œà§‡à¦° à¦ªà¦¡à¦¼à¦¾à¦²à§‡à¦–à¦¾à¦° à¦®à¦¾à¦§à§�à¦¯à¦® à¦ à¦¿à¦• à¦¨à¦¾à¦‡</option><option value="wrongSeatCount">à¦•à¦²à§‡à¦œà§‡à¦° à¦›à¦¾à¦¤à§�à¦°-à¦›à¦¾à¦¤à§�à¦°à§€à¦° à¦†à¦¸à¦¨ à¦¤à¦¥à§�à¦¯ à¦­à§�à¦²</option><option value="wrongType">à¦•à¦²à§‡à¦œà§‡à¦° à¦°à¦•à¦® à¦­à§�à¦²</option></select></div><div id="problemDescriptionHolder"></div></form>';
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
					problemDescriptionHTML+='<div id="ExistsUnitContainerDiv"><h4 class="notExistsUnitHeader">à¦¨à¦¿à¦šà§‡à¦° à¦¬à¦¿à¦­à¦¾à¦—à¦—à§�à¦²à§‹ à¦•à¦²à§‡à¦œà§‡ à¦†à¦›à§‡, à¦•à¦¿à¦¨à§�à¦¤à§� à¦‰à¦²à§�à¦²à§‡à¦– à¦•à¦°à¦¾ à¦¹à¦¯à¦¼ à¦¨à¦¿</h4>';
					for(var i=0; i<notExistingGroupsArray.length; i++)
					{
						problemDescriptionHTML+='<input type="checkbox" name="ExistUnit" value="'+notExistingGroupsArray[i]+'">'+notExistingGroupsArray[i]+'<br>';
					}
					problemDescriptionHTML+='</div>';
				}
				problemDescriptionHTML+='</div><a href="#" class="infoPopUpButton big"><span>âœ“</span>&#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2453;&#2480;&#2497;&#2472; </a>';
				$('#problemDescriptionHolder').empty().stop().css('opacity', '0').html(problemDescriptionHTML).animate({opacity: 1}, 300); 
			}
			else if(currentSelectedItem=="wrongMedium")
			{
				var problemDescriptionHTML='<div id="wrongMediumOptions"><h3>à¦¬à¦¿à¦­à¦¾à¦—</h3><div class="styled-select blue semi-square"><select id="wrongMediumUnit"><option value="defaultSelection">à¦¯à§‡à¦•à§‹à¦¨ à¦�à¦•à¦Ÿà¦¿ à¦¨à¦¿à¦°à§�à¦¬à¦¾à¦šà¦¨ à¦•à¦°à§�à¦¨</option>'
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
				var problemDescriptionHTML='<div id="wrongSeatCountOptions"><h3>&#2486;&#2495;&#2475;&#2463;</h3><div class="styled-select blue semi-square"><select id="wrongSeatCountShift"><option value="defaultSelection">à¦¯à§‡à¦•à§‹à¦¨ à¦�à¦•à¦Ÿà¦¿ à¦¨à¦¿à¦°à§�à¦¬à¦¾à¦šà¦¨ à¦•à¦°à§�à¦¨</option>';
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
					subProblemDescriptionHTML+='<div id="ExistsMediumContainerDiv"><h4 class="notExistsMediumHeader">à¦¨à¦¿à¦šà§‡à¦° à¦®à¦¾à¦§à§�à¦¯à¦®à¦—à§�à¦²à§‹ à¦•à¦²à§‡à¦œà§‡ à¦†à¦›à§‡, à¦•à¦¿à¦¨à§�à¦¤à§� à¦‰à¦²à§�à¦²à§‡à¦– à¦•à¦°à¦¾ à¦¹à¦¯à¦¼ à¦¨à¦¿</h4>';
					for(var i=0; i<notExistingVersionArray.length; i++)
					{
						subProblemDescriptionHTML+='<input type="checkbox" name="ExistMedium" value="'+notExistingVersionArray[i]+'">'+notExistingVersionArray[i]+'<br>';
					}
					subProblemDescriptionHTML+='</div>';
				}
				subProblemDescriptionHTML+='<a href="#" class="infoPopUpButton big"><span>âœ“</span>&#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2453;&#2480;&#2497;&#2472; </a>';
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
				var wrongSeatCountSubOptionsHTML='<h3>&#2478;&#2494;&#2471;&#2509;&#2479;&#2478;</h3><div class="styled-select blue semi-square"><select id="wrongSeatCountMedium"><option value="defaultSelection">à¦¯à§‡à¦•à§‹à¦¨ à¦�à¦•à¦Ÿà¦¿ à¦¨à¦¿à¦°à§�à¦¬à¦¾à¦šà¦¨ à¦•à¦°à§�à¦¨</option>';
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
				var wrongSeatCountSubSubOptionsHTML='<h3>&#2476;&#2495;&#2477;&#2494;&#2455;</h3><div class="styled-select blue semi-square"><select id="wrongSeatCountUnit"><option value="defaultSelection">à¦¯à§‡à¦•à§‹à¦¨ à¦�à¦•à¦Ÿà¦¿ à¦¨à¦¿à¦°à§�à¦¬à¦¾à¦šà¦¨ à¦•à¦°à§�à¦¨</option>';
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
						subProblemDescriptionHTML='<h4 class="wrongSeatCountHeader"> &#2474;&#2509;&#2480;&#2453;&#2499;&#2468; &#2438;&#2488;&#2472;&#2488;&#2434;&#2454;&#2509;&#2479;&#2494; '+'<input type="text" id="realSeatCount" name="realSeatCount"> à¦¹à¦¬à§‡</h4><a href="#" class="infoPopUpButton big"><span>âœ“</span>&#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2453;&#2480;&#2497;&#2472; </a>';
					}
					else
					{
						subProblemDescriptionHTML='<h4 class="wrongSeatCountHeader">&#2476;&#2480;&#2509;&#2468;&#2478;&#2494;&#2472;&#2503; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2495;&#2468; &#2453;&#2482;&#2503;&#2460;&#2503;&#2480; '+currentSelectedShift+' &#2486;&#2495;&#2475;&#2463;&#2503;&#2480; '+currentSelectedMedium+' &#2478;&#2494;&#2471;&#2509;&#2479;&#2478;&#2503;&#2480; '+currentSelectedUnit+' &#2476;&#2495;&#2477;&#2494;&#2455;&#2503; &#2438;&#2488;&#2472; &#2488;&#2434;&#2454;&#2509;&#2479;&#2494; '+currentSeatCount+' &#2441;&#2482;&#2509;&#2482;&#2503;&#2454; &#2453;&#2480;&#2494; &#2489;&#2479;&#2492;&#2503;&#2459;&#2503;, &#2453;&#2495;&#2472;&#2509;&#2468;&#2497; &#2474;&#2509;&#2480;&#2453;&#2499;&#2468; &#2438;&#2488;&#2472;&#2488;&#2434;&#2454;&#2509;&#2479;&#2494; '+'<input type="text" id="realSeatCount" name="realSeatCount"> à¦¹à¦¬à§‡</h4><a href="#" class="infoPopUpButton big"><span>âœ“</span>&#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2453;&#2480;&#2497;&#2472; </a>';
					}
					$('#subProblemDescriptionHolder').empty().stop().css('opacity', '0').html(subProblemDescriptionHTML).animate({opacity: 1}, 100); 	
				}
				else if(currentlySelectedProblemType=="wrongType")
				{	
					var subProblemDescriptionHTML='<div id="wrongTypeOptions"><input type="radio" name="wrongTypeOption" id="wrongTypeOption1" value="male" />&#2447;&#2454;&#2494;&#2472;&#2503; &#2486;&#2497;&#2471;&#2497;&#2478;&#2494;&#2468;&#2509;&#2480; &#2459;&#2494;&#2468;&#2509;&#2480;&#2470;&#2503;&#2480; &#2474;&#2524;&#2494;&#2480; &#2476;&#2509;&#2479;&#2476;&#2488;&#2509;&#2469;&#2494; &#2438;&#2459;&#2503;<br><input type="radio" name="wrongTypeOption" id="wrongTypeOption2" value="female" />&#2447;&#2454;&#2494;&#2472;&#2503; &#2486;&#2497;&#2471;&#2497;&#2478;&#2494;&#2468;&#2509;&#2480; &#2459;&#2494;&#2468;&#2509;&#2480;&#2496;&#2470;&#2503;&#2480; &#2474;&#2524;&#2494;&#2480; &#2476;&#2509;&#2479;&#2476;&#2488;&#2509;&#2469;&#2494; &#2438;&#2459;&#2503;<br><input type="radio" name="wrongTypeOption" id="wrongTypeOption3" value="combined" />&#2447;&#2454;&#2494;&#2472;&#2503; &#2459;&#2494;&#2468;&#2509;&#2480; &#2447;&#2476;&#2434; &#2459;&#2494;&#2468;&#2509;&#2480;&#2496; &#2441;&#2477;&#2527;&#2503;&#2480;&#2439; &#2474;&#2524;&#2494;&#2480; &#2476;&#2509;&#2479;&#2476;&#2488;&#2509;&#2469;&#2494; &#2438;&#2459;&#2503;</div><a href="#" class="infoPopUpButton big"><span>âœ“</span>&#2438;&#2474;&#2472;&#2494;&#2480; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455;&#2463;&#2495; &#2472;&#2495;&#2476;&#2472;&#2509;&#2471;&#2472; &#2453;&#2480;&#2497;&#2472; </a>';
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


