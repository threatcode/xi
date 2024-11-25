<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="box_container" style="min-height: 870px;margin-top: 8px;" id="college_selection_div_edit">
<img src="/board/resources/images/warning_message.png" style="margin-top: 6px; width: 587px;height: 68px;  margin-bottom: 3px "/>
	<div class="box_header_inc">
		<b class="alignleft">&#2453;&#2482;&#2503;&#2460; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; College Selection</b>
	</div>
	<div class="box_body sub_box_container" style="margin-top: 0px;margin-bottom: 0px;">	
			<div id="college_selection_alert" style="float: none; vertical-align: top;margin-top: 0px;text-align: left" >
					&#2438;&#2474;&#2472;&#2494;&#2480; &#2474;&#2459;&#2472;&#2509;&#2470;&#2503;&#2480; &#2453;&#2509;&#2480;&#2478; &#2437;&#2472;&#2497;&#2488;&#2494;&#2480;&#2503; &#2438;&#2476;&#2503;&#2470;&#2472;&#2455;&#2497;&#2482;&#2507;&#2480; Priority &#2488;&#2503;&#2463; &#2453;&#2480;&#2468;&#2503; &#2489;&#2476;&#2503;&#2404; &#2438;&#2474;&#2472;&#2494;&#2480; &#2488;&#2476;&#2458;&#2503;&#2527;&#2503; &#2474;&#2459;&#2472;&#2509;&#2470;&#2503;&#2480; &#2438;&#2476;&#2503;&#2470;&#2472;&#2503;&#2480; Priority &#2489;&#2476;&#2503; 1, &#2470;&#2509;&#2476;&#2495;&#2468;&#2496;&#2527; &#2474;&#2459;&#2472;&#2509;&#2470;&#2503;&#2480; &#2438;&#2476;&#2503;&#2470;&#2472;&#2503;&#2480; Priority &#2489;&#2476;&#2503; 2- &#2447;&#2477;&#2494;&#2476;&#2503; &#2438;&#2474;&#2472;&#2494;&#2480; Priority &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480;&#2497;&#2472;&#2404; &#2438;&#2474;&#2472;&#2495; &#2438;&#2474;&#2472;&#2494;&#2480; &#2478;&#2503;&#2471;&#2494;&#2488;&#2509;&#2469;&#2494;&#2472; (&#2441;&#2474;&#2480;&#2503; &#2474;&#2509;&#2480;&#2470;&#2468;&#2509;&#2468;) &#2476;&#2495;&#2476;&#2503;&#2458;&#2472;&#2494; &#2453;&#2480;&#2503; &#2453;&#2482;&#2503;&#2460; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2472; &#2451; Priority &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480;&#2482;&#2503; &#2468;&#2494; &#2438;&#2474;&#2472;&#2494;&#2480; &#2453;&#2494;&#2457;&#2509;&#2453;&#2509;&#2487;&#2495;&#2468; &#2453;&#2482;&#2503;&#2460;&#2503; &#2477;&#2480;&#2509;&#2468;&#2495;&#2480; &#2460;&#2472;&#2509;&#2479; &#2488;&#2489;&#2494;&#2527;&#2453; &#2489;&#2476;&#2503;&#2404;
 <br/>
<br/>Set Priority 1  to your most preferred application, Priority 2 to your second preferred application and so on.
If you consider your merit position (mentioned above) for giving priority to your application, it will help you to get your desired admission.
			</div>
	</div>
<input type="hidden" name="no_of_update" id="no_of_update"  value="<s:property value='#request.no_of_update' />" />
	
    <div class="box_body" style="margin-top: 0px;margin-bottom: 0px;">	
 			<div class="box_header_inc" style="text-align: center;">
				<a href="#" class="collgeNameLink linkDisabled"> <b id="c_name">College Name</b></a>
			</div>
  		<div id="selection_box_container">
  				<div id="selection_box">
					<div id="College_name_selection">	
					  	<div class="selection_row">
						    <label>Board</label>
						     <select id="helper_board_id" disabled>
							 	<option value="">Select Board</option>
							 	<option value="1" selected="selected">General Board</option>
								<option value="2">Madrasah Board</option>		 	
							 </select>
						</div>
						<div class="selection_row">
						    <label>District</label>
						     <select id="district_id" disabled>
							 	<option value="">Select District</option>
							 		<s:iterator  value="%{#application.DISTRICT_LIST}">   
							   			<option value="<s:property value='dist_id'/>"><s:property value="dist_name"/></option>
									</s:iterator>
									
							 </select>
						</div>
						
						<div class="selection_row">
						    <label>Thana</label>
						     <select id="thana_id" disabled>
							 	<option value="">Select Thana</option>
							 </select>
						</div>
						
						<div class="selection_row">
						    <label>College</label>
							<select id="college_id" disabled>
							 	<option value="">Select College</option>
							</select> 
						</div>
					</div>
	
	<!-- <div style="font-weight: bold;color: #10257f;margin-top: 5px;padding-bottom: 5px;">OR</div>
	
	<div class="selection_row" style="border: 2px solid yellow;padding-top: 10px;margin-left: 2px;padding-bottom: 5px;">  	
	    <label>EIIN</label>
	    <input type="text" id="eiin" value="" onblur="fetchCollegeList('by_eiin')" style="width: 145px;"/>
	</div> -->
		
	
					<div id="currentCollegeDetails">
					       <fieldset>
  								<legend>Current College Details</legend>
								<table id="svg_table" width="100%"> 
								  <tr>
								 	<td width="100%" align="left" style="padding-left: 2px;border: 0px">Shift: <span id="sshowsift"></span></td>
								  </tr>
								  <tr>
								 	<td width="100%" align="left" style="padding-left: 2px;border: 0px">Version: <span id="sshowversion"></span></td>
								  </tr>
								  <tr>
								 	<td width="100%" align="left" style="padding-left: 2px;border: 0px">Group: <span id="sshowgroup"></span></td>
								  </tr>
								  <tr>
								 	<td width="100%" align="left" style="padding-left: 2px;border: 0px">Gender: <span id="sshowgender"></td>
								  </tr>			 
								  <tr>
								  	<td style="border: 0px; padding-top: 12px;">
								  	<center>
								  	<a href="#" class="infoPopUpButtonDisabled small moreDetailsButton">&#2454;&#2494;&#2482;&#2495;<br>&#2438;&#2488;&#2472;</a>
									</center>
        <!-- 
        <a href="#" class="infoPopUpButtonDisabled small red complainButton">&#2477;&#2497;&#2482; &#2468;&#2469;&#2509;&#2479;&#2503;&#2480; &#2460;&#2472;&#2509;&#2479; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455; &#2453;&#2480;&#2497;&#2472;</a>
 -->
								  		<%--<input type="button" value="&#2438;&#2480;&#2507; &#2476;&#2495;&#2488;&#2509;&#2468;&#2494;&#2480;&#2495;&#2468;" id="add_choice" style='margin-right:10px;width:120px;'/>
								  		<input type="button" value="&#2477;&#2497;&#2482; &#2468;&#2469;&#2509;&#2479;&#2503;&#2480; &#2460;&#2472;&#2509;&#2479; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455; &#2453;&#2480;&#2497;&#2472;" id="add_choice" style='margin-right:10px;width:120px;'/>--%>
								  	</td>
								  </tr> 
								</table>
							</fieldset> 
					</div>
				</div>
	
	
	
	<!-- new div for svg table start -->
	
	<!-- new div for svg table end -->
	
	
	
	
	
				<div id="currentCollegeDetailsSelection">
					<div class="selection_row">
					    <label>Shift</label>
						<select id="shift_id" disabled>
						 	<option value="">Select Shift</option>
						</select> 
					</div>
					<div class="selection_row">
					    <label>Version</label>
						<select id="version_id" disabled>
						 	<option value="">Select Version</option>
						</select> 
					</div>
					<div class="selection_row">
					    <label>Group</label>
						<select id="group_id" disabled>
						 	<option value="">Select Group</option>
						</select> 
					</div>
					<div class="selection_row" style="vertical-align: middle;">
					    <label style="vertical-align: middle;">Special Quota</label>
						<select id="special_quota" style="vertical-align: middle;" disabled>
						</select> 
					</div>
					<div class="selection_row" style="vertical-align: middle;display: none">
					    <label style="vertical-align: middle;">Governing Body Quota</label></a>
					    <select id="gb_quota" style="vertical-align: middle;" disabled>
					    </select>
					</div>
					</div>
					<div class="selection_row" style="text-align: right;margin-top: 5px;">
						<input type="button" value="Add This College" id="add_choice" style='margin-right:10px;width:120px;' disabled/>
					</div>
  				</div>	  
  				
  				
  				
 <!--  <div style="float:left; width: 2px;border-right: 1px dashed #10257f;height: 100%;"></div> -->
  
				<div id="selected_box_edit" class="collegeEditDiv" style="min-height: 400px;float: left;width: 645px;position: relative;">
      <table class="rounded-corner" id="selection_row_table" style="width: 620px;">

          <tr id="header">
            <th width="5%" align="center">SL.</th>
            <th width="40%" align="left" style="padding-left: 2px;">College Name</th>
            <th width="8%" align="center">EIIN</th>
            <th width="10%" align="left" style="padding-left: 2px;">Shift</th>
            <th width="10%" align="left" style="padding-left: 2px;">Version</th>
            <th width="10%" align="left" style="padding-left: 2px;">Group</th>
            <th width="3%" align="center">SQ</th>
            <!-- <th width="3%" align="center">GQ</th> -->
            <th width="3%" align="center">Priority</th>
            <th width="5%" align="center"></th>
          </tr>
          <s:iterator value="choice" status="indx">
              		<s:if test='application_type == "WEB"'>
                		<script type="text/javascript">
	            		 	college_map[<s:property value="eiin"/>] = "<s:property value='eiin'/>";
	            		</script>   
	            	</s:if>

            <%--            <s:if test='payment_status == "Y"'>
           	<s:if test='application_type == "W"'>
           		<s:set name="web_payment" value='%{"Y"}' />
           	</s:if>
           </s:if> --%>
            <%--   <s:if test='payment_status == "Y"'> --%>
            <tr  class='paid' id="row_<s:property value='#indx.count' />">
              <%-- 	</s:if>  --%>
              <%--         	<s:else>
        		<tr  class='unpaid' id="row_<s:property value='#indx.count' />">
        	</s:else> --%>
              <td align="center"><s:property value="#indx.count" /></td>
              <td align="left"><s:property value="college_name"/></td>
              <td align="center"><s:property value="eiin"/></td>
              <td align="left"><s:property value="shift_name"/></td>
              <td align="left"><s:property value="version_name"/></td>
              <td align="left"><s:property value="group_name"/></td>
              <td align="center"><s:property value="special_quota"/></td>
               <%-- <td align="center"><s:property value="gb_quota"/></td> --%>
              <td align="center">
<%--               	<input type='text' name='choice[<s:property value="priority"/>].priority' 
              		id='priority_<s:property value="priority"/>' value='<s:property value="priority"/>' style='width:30px;text-align:center;'>
 --%>              	<input type='text' name='choice[<s:property value='#indx.count' />].priority' 
              		id='priority_<s:property value='#indx.count' />' value='<s:property value="priority"/>' style='width:30px;text-align:center;'>
              </td>
              <td align="center">
                  <s:if test='application_type != "WEB"'>
            				<s:property value="application_type"/>
            		</s:if> 
            		<s:if test='application_type == "WEB"'>
            				<s:property value="application_type"/>
            		</s:if>
            		
                <input type='hidden' name="choice[<s:property value='#indx.count' />].eiin" value="<s:property value='eiin'/>" />
                <input type='hidden' name="choice[<s:property value='#indx.count' />].shift_id" value="<s:property value='shift_id'/>" />
                <input type='hidden' name="choice[<s:property value='#indx.count' />].version_id" value="<s:property value='version_id'/>" />
                <input type='hidden' name="choice[<s:property value='#indx.count' />].group_id" value="<s:property value='group_id'/>" />
                <input type='hidden' name="choice[<s:property value='#indx.count' />].special_quota" value="<s:property value='special_quota'/>" />
                <input type='hidden' name="choice[<s:property value='#indx.count' />].gb_quota" value="<s:property value='gb_quota'/>" />
                <input type='hidden' name="choice[<s:property value='#indx.count' />].application_type" value="<s:property value='application_type'/>" />
                <input type='hidden' name="choice[<s:property value='#indx.count' />].mobile_no" value="<s:property value='mobile_no'/>" />
              
              </td>
            </tr>
            
          </s:iterator>
        </tbody>
      </table>
      
      <!-- Captcha Space-->
      
    </div>
			  <div id="submitOverlay" style="width: 100%; position: relative; z-index: 50; opacity: 0.81; height: 475px; background-color: grey; display: none">
			  	
			  </div>
	
		</div>
		<div class="box_footer">
			<input type="button" value="Print Application" onclick="showPopUp('choiceListReport_TT')"/>
		 	<input type="button" style="width: 100px;font-size: medium; " id="submitButtonSMSOnly" value="Next"/>
		</div>
	</div>

<%--<div id="popupcontent" style="min-height: 50px;overflow: auto;" class="popupcontent">--%>


<script type="text/javascript">
function showPopUp(actionName){
	//window.open(actionName+'?application_id='+$('#application_id_old').val(),'1433313485446','width=780,height=850,toolbar=0,menubar=0,location=0,status=1,scrollbars=1,resizable=0,left=0,top=0');
	window.open(actionName,'1433313485446','width=780,height=850,toolbar=0,menubar=0,location=0,status=1,scrollbars=1,resizable=0,left=0,top=0');
}

$('#selection_row_table').off('click');
</script>   