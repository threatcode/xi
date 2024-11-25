<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="box_container" style="min-height:300px;display: none;margin-top:0px;" id="college_selection_div">


	
    <div class="box_body" style="margin-top: 0px;margin-bottom: 0px;">	
 			<div class="box_header_inc" style="text-align: center;">
				<a href="#" class="collgeNameLink linkDisabled"> <b id="c_name">College Name</b></a>
			</div>
  		<div id="selection_box_container">
  				<div id="selection_box">
<%-- 					<div id="College_name_selection">	
				  	<div class="selection_row">
						    <label>Board</label>
						     <select id="helper_board_id" onchange="fetchCollegeList('by_district')">
							 	<option value="">Select Board</option>
							 	<option value="1" selected="selected">General Board</option>
								<option value="2">Madrasah Board</option>		 	
							 	<option value="3">Technical Board</option>
				 
							 </select>
						</div>
						<div class="selection_row">
						    <label>District</label>
						     <select id="district_id" onchange="fetchTahanList();fetchCollegeList('by_district');">
							 	<option value="">Select District</option>
							 		<s:iterator  value="%{#application.DISTRICT_LIST}">   
							   			<option value="<s:property value='dist_id'/>"><s:property value="dist_name"/></option>
									</s:iterator>
									
							 </select>
						</div>
						
						<div class="selection_row">
						    <label>Thana</label>
						     <select id="thana_id" onchange="fetchCollegeList('by_thana')">
							 	<option value="">Select Thana</option>
							 </select>
						</div> 
						
						<div class="selection_row">
						    <label>College</label>
							<select id="college_id" onchange="getCollegeSVGInfo(this)"><!-- getCollegeSVGInfo(this.value) -->
							 	<option value="">Select College</option>
							</select>
						</div>
						
					</div> --%>
	
	<!-- <div style="font-weight: bold;color: #10257f;margin-top: 5px;padding-bottom: 5px;">OR</div>
	
	<div class="selection_row" style="border: 2px solid yellow;padding-top: 10px;margin-left: 2px;padding-bottom: 5px;">  	
	    <label>EIIN</label>
	    <input type="text" id="eiin" value="" onblur="fetchCollegeList('by_eiin')" style="width: 145px;"/>
	</div> -->
		
	
					<div id="currentCollegeDetails">
					       <fieldset>
  								<legend> College Details</legend>
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
<!-- 								  <tr>
								  	<td style="border: 0px; padding-top: 12px;">
		<a href="#" class="infoPopUpButtonDisabled small moreDetailsButton">&#2438;&#2480;&#2507;<br/>&#2476;&#2495;&#2488;&#2509;&#2468;&#2494;&#2480;&#2495;&#2468;</a>
        <a href="#" class="infoPopUpButtonDisabled small red complainButton">&#2477;&#2497;&#2482; &#2468;&#2469;&#2509;&#2479;&#2503;&#2480; &#2460;&#2472;&#2509;&#2479; &#2437;&#2477;&#2495;&#2479;&#2507;&#2455; &#2453;&#2480;&#2497;&#2472;</a>

								  	</td>
								  </tr> --> 
								</table>
							</fieldset> 
					</div>
				</div>
	
	
	
	<!-- new div for svg table start -->
	
	<!-- new div for svg table end -->
	
	
	
	
	
				<div id="currentCollegeDetailsSelection">
					<div class="selection_row">
					    <label>Shift</label>
						<select id="shift_id" onchange="loadVersions()">
						 	<option value="">Select Shift</option>
						</select> 
					</div>
					<div class="selection_row">
					    <label>Version</label>
						<select id="version_id" onchange="loadGroups()">
						 	<option value="">Select Version</option>
						</select> 
					</div>
					<div class="selection_row">
					    <label>Group</label>
						<select id="group_id" onchange="loadSQ()">
						 	<option value="">Select Group</option>
						</select> 
					</div>
					<div class="selection_row" style="vertical-align: middle;">
					    <label style="vertical-align: middle;">Special Quota</label>
						<select id="special_quota" style="vertical-align: middle;">
						</select> 
					</div>
					<div class="selection_row" style="vertical-align: middle;">
					    <label style="vertical-align: middle;">Total Seat</label>
					    <input type="text" id="live_total_seat" style="vertical-align: middle;width:57%" class="readonly" disabled="disabled"/>

					</div>
					<div class="selection_row" style="vertical-align: middle;">
					    <label style="vertical-align: middle;">Available Seat</label>
						<input type="text" id="live_available_seat" style="vertical-align: middle; width:57%;" class="readonly" disabled="disabled"/>
						
					</div>
					</div>
					<div class="selection_row" style="text-align: right;margin-top: 5px;">
						<input type="button" value="Add This Choice" id="add_choice" onclick="addChoice('O')" style='margin-right:10px;width:120px;'/>
					</div>
  				</div>	  
 <!--  <div style="float:left; width: 2px;border-right: 1px dashed #10257f;height: 100%;"></div> -->
  
				<div id="selected_box" style="float: right;width: 620px;height: 100%;margin-top: 20px;">
				 
						<table  id="selection_row_table" border="1" width="100%"> 
						  <tr>
						 	<th width="5%" align="center">SL.</th>
						 	<th width="40%" align="left" style="padding-left: 2px;">College Name</th>
						 	<th width="8%" align="center">EIIN</th>
						 	<th width="10%" align="left" style="padding-left: 2px;">Shift</th>
						 	<th width="10%" align="left" style="padding-left: 2px;">Version</th>
						 	<th width="10%" align="left" style="padding-left: 2px;">Group</th>
						 	<th width="3%" align="center">SQ</th>
						 	<!-- <th width=9%" align="center">Priority</th> -->
						 	<th width="5%" align="center">Delete</th>
						 </tr>
						</table>
				
						<!-- Captcha Space-->
						
				</div>
			  <div id="submitOverlay" style="width: 100%; position: relative; z-index: 50; opacity: 0.81; height: 475px; background-color: grey; display: none">
			  	
			  </div>
	
		</div>
		<div class="box_footer">
			
		 	<input type="button" id="submitButton" value="Preview"/>
		</div>
	</div>
	<input type="hidden" id="college_name"  name="name"/>
	<input type="hidden" id="college_eiin"  name="eiin"/>
     <script type="text/javascript">	
		function OptionValueSelect() {
				 	document.getElementById("college_name").value ='${sessionScope.user.college_name}';
			 		document.getElementById("college_eiin").value = '${sessionScope.user.eiin}';
			 		document.getElementById("c_name").innerHTML='${sessionScope.user.college_name}'+" ["+'${sessionScope.user.eiin}'+"]";
			 		
			 	//	alert($("#college_eiin").val());
			 		
	}
	window.onload = setTimeout("OptionValueSelect()", 100);
	</script>  	
<%--<div id="popupcontent" style="min-height: 50px;overflow: auto;" class="popupcontent">--%>

