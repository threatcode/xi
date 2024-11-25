<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp"%>



<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2>APPROVED STUDENTS HOME</h2>
			<div class="sidebar_section_text">
				<div class="box_row" style="background-color: #296C13;color: white;text-align: left;font-weight: bold;font-size: 18px; padding-top: 6px; padding-bottom: 6px; border-radius: 6px; border: 1px solid #00FF84; box-shadow: 0px 0px 12px #888888;">
					<center>Approved Students List</center>
				</div>

				<br />
				<!---- body ----->
				<center>

					<table style="width:400px;font-size:14px"
						class="table table-bordered table-striped cf">
						<tr>
							<td style="color:black;"><strong>Shift </strong></td>
							<td style="color:black;"><strong>Version</strong></td>
							<td style="color:black;"><strong>Group</strong></td>
							<td style="color:black;"><strong>Action</strong></td>

						</tr>

						<tr>
							<td><select name="shiftId" id="shiftId">
									<option value="none" selected>Select Shift</option>
									<c:forEach items="${shiftList}" var="shift">
										<option value="${shift.shiftId}">${shift.shiftName}</option>
									</c:forEach>

							</select></td>

							<td><select name="versionId" id="versionId">
									<option value="none" selected>Select Version</option>
									<c:forEach items="${versionList}" var="version">
										<option value="${version.versionId}">${version.versionName}
										</option>
									</c:forEach>
							</select></td>

							<td><select name="groupId" id="groupId"
								onchange="changeGroup()">
									<option value="none" selected>Select Group</option>
									<c:forEach items="${groupList}" var="group">
										<option value="${group.groupId}">${group.groupName}</option>
									</c:forEach>

							</select></td>
							
							<td>
								<input type="hidden" name="groupName" id="groupName" />
								<!--<button class="btn bg-green"  type="button" onclick="downloadResult()">Download Result</button>-->
								<input type="submit" style="width:140px;padding: 1px 12px;"
								class="btn btn-info" onclick="return searchStudent()"
								value="Search">
							</td>

						</tr>
					</table>
					<br />


					<div id="loader"></div>
				</center>

				<!----body ------>


				<div id="showapplicant"></div>

				<br>
			</div>
		</div>
		<br>

	</div>


	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp"%>
</div>


<script type="text/javascript">
	function searchStudent() {

		if ($("#shiftId").val() == "none") {
			alert("Select a Shift.");
			return false;
		}
		if ($("#versionId").val() == "none") {
			alert("Select a version.");
			return false;
		}
		if ($("#groupId").val() == "none") {
			alert("Select a group.");
			return false;
		}
		$("#showapplicant").html('');

		$('#loader').prepend($('<img>', {
			id : 'theImg',
			src : '/board/resources/images/loading1.gif'
		}));

		$.ajax({
			type : "POST",
			url : "approvedStudentListOfMerit",
			dataType : 'text',
			async : false,
			data : {
				eiinCode :	"Hossain" ,
				pShift_id : $("#shiftId").val(),
				pVersion_id : $("#versionId").val(),
				pGroup_id : $("#groupId").val(),
				pMerit : $("#meritType").val(),
			}
		}).done(function(msg) {
			$("#loader").html('');
			$("#showapplicant").html('');
			$("#showapplicant").html(msg);
		}).always(function() {
			//$('#sw-val-step-3').unmask();
		}).fail(function(data) {
			if (data.responseCode)
				alert(data.responseCode);
		});

	}

	function changeGroup() {
		$("#groupName").val($("#groupId option:selected").text());
		//alert($("#groupName").val());
	}
</script>
<%@ include file="/WEB-INF/jsp/template/footerLite.jsp"%>

</div>
</body>
</html>