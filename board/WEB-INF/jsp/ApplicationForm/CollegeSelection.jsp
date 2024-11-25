<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="box_container" style="height: 601px;display: none;margin-top: 8px;" id="college_selection_div">
<img src="/board/resources/images/warning_message.jpg" border="0" style="width: 800px;height: 60px;" width="800"  height="64"/>

<div class="box_header">
College Selection
</div>
<div class="box_body" style="height: 475px;">
  
  <div id="selection_box" style="float: left; vertical-align: top;margin-top: 0px;">
  	
  	<div style="background-color: #c1ccd4;padding-top: 10px;padding-bottom: 5px;">
  	
	<div style="border: 2px solid yellow;padding-top: 6px;margin-left: 2px;padding-bottom: 5px;">	
	  	<div class="selection_row">
		    <label>Board</label>
		     <select id="helper_board_id" onchange="fetchCollegeList('by_district')">
			 	<option value="">Select Board</option>
			 	<option value="3" selected="selected">Technical Board</option>
<!--
			 	<option value="1">General Board</option>
				<option value="2">Madrasah Board</option>		 	
			 	<option value="3">Technical Board</option>
 -->
			 </select>
		</div>
		<div class="selection_row">
		    <label>District</label>
		     <select id="district_id" onchange="fetchCollegeList('by_district')">
			 	<option value="">Select District</option>
			 		<s:iterator  value="%{#application.DISTRICT_LIST}">   
			   			<option value="<s:property value='dist_id'/>"><s:property value="dist_name"/></option>
					</s:iterator>
					
			 </select>
		</div>
		<div class="selection_row">
		    <label>College</label>
			<select id="college_id" onchange="getCollegeSVGInfo(this.value)">
			 	<option value="">Select College</option>
			</select> 
		</div>
	</div>
	
	<div style="font-weight: bold;color: #10257f;margin-top: 5px;padding-bottom: 5px;">OR</div>
	
	<div class="selection_row" style="border: 2px solid yellow;padding-top: 10px;margin-left: 2px;padding-bottom: 5px;">  	
	    <label>EIIN</label>
	    <input type="text" id="eiin" value="" onblur="fetchCollegeList('by_eiin')" style="width: 145px;"/>
	</div>
	
	
	
	
	</div>
	
	<div style="border: 2px solid #7f9db9;padding-top: 6px;margin-left: 2px;margin-top: 15px;padding-bottom: 5px;">
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
	<div class="selection_row" style="vertical-align: top;">
	    <label>S. Quota</label>
		<select id="special_quota">
		</select> 
	</div>
	</div>
	<div class="selection_row" style="text-align: right;margin-top: 20px;">
		<input type="button" value="Add More College" id="add_choice" onclick="addChoice('O')" style='margin-right:10px;width:120px;'/>
	</div>
  </div>	  
  <div style="float:left; width: 2px;border-right: 1px dashed #10257f;height: 100%;"></div>
  
  <div id="selected_box" style="float: left;width: 645px;position: relative;height: 100%;">
  
			<table  id="selection_row_table" border="1" width="100%"> 
			  <tr>
			 	<th width="5%" align="center">SL.</th>
			 	<th width="40%" align="left" style="padding-left: 2px;">College Name</th>
			 	<th width="8%" align="center">EIIN</th>
			 	<th width="10%" align="left" style="padding-left: 2px;">Shift</th>
			 	<th width="10%" align="left" style="padding-left: 2px;">Version</th>
			 	<th width="10%" align="left" style="padding-left: 2px;">Group</th>
			 	<th width="3%" align="center">SQ</th>
			 	<th width=9%" align="center">Priority</th>
			 	<th width="5%" align="center">Delete</th>
			 </tr>
			</table>
	
			<!-- Captcha Space-->
			
  </div>
  <div id="submitOverlay" style="width: 100%; position: relative; z-index: 50; opacity: 0.81; height: 475px; background-color: grey; display: none">
  	
  </div>
  <!-- 
  <div id="div_allert" style="font-size: 24;color:red;">
   N.B: Please carefully select your college application choices. After submitting your application you are not allowed to change it.
  </div>
  -->
</div>
<div class="box_footer">
 <input type="button" id="submitButton" value="Submit Application" onclick="submitApplication()" />
</div>
</div>
