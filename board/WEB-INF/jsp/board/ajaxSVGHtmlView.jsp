
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<form id="mainform" name="mainform" class="register" method="post"
	action="">
	<div>

		<div id="showMSG" style="font-size: 20px; color: maroon;"></div>
		<br />
		<p style="font-size: 20px; font-family: sans-serif; font-style: italic; color: #061BFC;">College
			Information</p>


<!-- 		<table class="display dataTable" id="collegeTable" style="border:0;"> -->
		<table class="display dataTable" id="collegeTable">
			<tr>
				<th width="10%">College</th>
				<td colspan="5">
					<input type="text" readonly size="50"
						name="collegeName"
						value='<s:property value="collegeDTO.college_name" />'>
<!-- 					<input type="text" readonly size="50" style="width:50%" -->
<!-- 						name="collegeName" -->
<!-- 						value='<s:property value="collegeDTO.college_name" />'> -->
				</td>
			</tr>

			<tr>
				<th width="10%">District</th>
				<td width="20%"><input type="text" readonly size="50"
					name="districtID" id="districtID"
					value='<s:property value="collegeDTO.dist_name" />'></td>
				<th width="10%">Thana</th>
				<td colspan="3"><input type="text" readonly size="50"
					name="thanaID" id="thanaID"
					value='<s:property value="collegeDTO.thana_name" />'></td>
			</tr>
			
		</table>

	</div>


	<br>
	<p
		style="font-size: 20px; font-family: sans-serif; font-style: italic; color: #061BFC;">SVG
		Details</p>

	<input type="hidden" name="eiin" id="eiin"
		value='<s:property value="eiinCode" />'>


	<table class="display dataTable" id="svgTable">

		<tr>
			<th>Shift</th>
			<th>Version</th>
			<th>Group</th>
			<th>Gender</th>
			<th>Total Seat</th>
			<th>Minimum GPA</th>
			<th>Own GPA</th>

		<% int rowcount=1; %>

		<s:set var="counter" value="1" />

		<s:iterator value="svgList">
			<tr>
				<td>
						<input type="text" readonly name="shiftid" id="shiftid_<%=rowcount %>"
							value='<s:property value="shiftName"/>' />
				</td>
				<td>
						<input type="text" readonly name="verid" id="verid_<%=rowcount %>"
						value='<s:property value="versionName"/>' />
				</td>
				<td id="itemcol_<%=rowcount %>">
					
						<input type="text" readonly name="groupid" id="groupid_<%=rowcount %>"
						value='<s:property value="groupName"/>' />
				</td>
				<td>
					<input type="text" readonly name="gender" id="gender_<%=rowcount %>"
						value='<s:property value="gender"/>' />
				</td>
				<td><INPUT type="text" readonly name="seat"
					id="seat_<%=rowcount %>" title="Total Seat" maxlength="4" class="short"
					value='<s:property value="totalSeat" />'
					style="width: 62px;text-align:center;" /></td>
				<td><INPUT type="text" readonly name="gpa"
					id="gpa_<%=rowcount %>" title="Minmum GPA" maxlength="4" class="short"
					value='<s:property value="gpa" />'
					style="width: 88px;text-align:center;" /></td>
				<td><INPUT type="text" readonly name="ownGpa"
					id="ownGpa_<%=rowcount %>" title="Own GPA" maxlength="4" class="short"
					value='<s:property value="ownGpa" />'
					style="width: 60px;text-align:center;" /></td>

			</tr>
			
			<% rowcount++; %>
			<s:set var="counter" value="%{#counter+1}" />

		</s:iterator>


	</table>


	<br>

	<div class="validity-summary-container">

		<ul></ul>

	</div>
	<br>


	<script type="text/javascript"> 

 

</script>


</form>
</div>
