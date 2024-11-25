<%@taglib prefix="s" uri="/struts-tags"%>
<style type="text/css">
.addMoreButton {
	-moz-box-shadow:inset 0px 1px 0px 0px #e184f3;
	-webkit-box-shadow:inset 0px 1px 0px 0px #e184f3;
	box-shadow:inset 0px 1px 0px 0px #e184f3;
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #c123de), color-stop(1, #a20dbd));
	background:-moz-linear-gradient(top, #c123de 5%, #a20dbd 100%);
	background:-webkit-linear-gradient(top, #c123de 5%, #a20dbd 100%);
	background:-o-linear-gradient(top, #c123de 5%, #a20dbd 100%);
	background:-ms-linear-gradient(top, #c123de 5%, #a20dbd 100%);
	background:linear-gradient(to bottom, #c123de 5%, #a20dbd 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#c123de', endColorstr='#a20dbd',GradientType=0);
	background-color:#c123de;
	-moz-border-radius:6px;
	-webkit-border-radius:6px;
	border-radius:6px;
	border:1px solid #a511c0;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:15px;
	font-weight:bold;
	padding:6px 24px;
	text-decoration:none;
	text-shadow:0px 1px 0px #9b14b3;
}
.addMoreButton:hover {
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #a20dbd), color-stop(1, #c123de));
	background:-moz-linear-gradient(top, #a20dbd 5%, #c123de 100%);
	background:-webkit-linear-gradient(top, #a20dbd 5%, #c123de 100%);
	background:-o-linear-gradient(top, #a20dbd 5%, #c123de 100%);
	background:-ms-linear-gradient(top, #a20dbd 5%, #c123de 100%);
	background:linear-gradient(to bottom, #a20dbd 5%, #c123de 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#a20dbd', endColorstr='#c123de',GradientType=0);
	background-color:#a20dbd;
}
.addMoreButton:active {
	position:relative;
	top:1px;
}

</style>

<s:if test='%{#web_payment == "N"}'>

<div id="selection_box" style="float: left; vertical-align: top;margin-top: 0px;margin-left: 2px;padding-left: 2px;">  	
  	<div style="background-color: #c1ccd4;padding-top: 2px;padding-bottom: 5px;padding-right: 2px;border-radius:5px;">
  	
	<div style="border: 2px solid white;padding-top: 6px;margin-left: 2px;padding-bottom: 5px;">	
	  	<div class="selection_row">
		    <label>Board</label>
		     <select id="helper_board_id" onchange="fetchCollegeList('by_district')">
			 	<option value="">Select Board</option>
			 	<option value="1">General Board</option>
			 	<option value="2">Madrasha Board</option>
			 	<option value="3">Technical Board</option>

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
	
	<!--<div style="font-weight: bold;color: red;margin-top: 5px;padding-bottom: 5px;text-align: center;">OR</div>-->
	
	<div class="selection_row" style="border: 2px solid white;padding-top: 10px;margin-left: 2px;padding-bottom: 5px;">  	
	    <label>EIIN</label>
	     <input type="text" id="eiin" readonly="readonly" value="" onchange="fetchCollegeList('by_eiin')" style="width: 145px;"/>
	</div>
	
	
	
	</div>
	
	<div style="border: 2px solid #d96060;padding-top: 6px;margin-left: 2px;margin-top: 15px;padding-bottom: 5px;">
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
	    <label style="font-size: 12px;">Special Quota</label>
		<select id="special_quota">
		</select> 
	</div>
	<div class="selection_row" style="vertical-align: top;">
	    <label style="font-size: 12px;">Education Quota</label>
		<select id="education_quota">
			<option value="N">No</option>
			<option value="Y">Yes</option>			
		</select> 
	</div>
	
	</div>
	<div style="margin-top: 5px;font-weight: bold;font-size: 15px;">
		<font color="blue">Total Available Seat: <input type="text" id="available_seat" disabled="disabled" style="width: 40px;text-align: center;color: green;font-weight: bold;" /><br/>		
		</font> 
	</div>

	<div class="selection_row" style="text-align: center;margin-left:14px;">
		<input type="button" class="addMoreButton"  value="Add College" id="add_choice" onclick="addChoice('S')" style='margin-right:10px;width:142px;height: 50px;font-size: 18px;'/>
	</div>
  </div>	
</s:if>  