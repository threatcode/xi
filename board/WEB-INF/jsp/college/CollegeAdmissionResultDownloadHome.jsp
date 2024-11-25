<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp"%>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2>RESULT DOWNLOAD</h2>
			<div class="sidebar_section_text">
				<center>
					<div class="box_row"
						style="background-color: #00868b;color: white;text-align: left;font-weight: bold;padding-top: 0px;font-size: 16px;height: 22px;">
						<center>:: RESULT DOWNLOAD ::</center>
					</div>
					<br />
					<br />
					<!---- body ----->
					<center>
						<form action="admissionResultReportAction"
							name="resultDownloadForm" id="resultDownloadForm" method="post"
							enctype="multipart/form-data">
							<table style="width:400px;font-size:14px"
								class="table table-bordered table-striped cf">
								<tr style="background-color: #f0f8ff;">
									<td style="color:black;"><strong>Shift </strong></td>
									<td style="color:black;"><strong>Version</strong></td>
									<td style="color:black;"><strong>Group</strong></td>
									<td style="color:black;"><strong>Merit Type</strong></td>
									<td style="color:black;"><strong>Download</strong></td>

								</tr>

								<tr>
									<td><select name="shiftId" id="shiftId">
											<option value="none" selected>Select Shift</option>
											<c:forEach items="${shiftList}" var="shift">
												<option value="${shift.shiftId}">${shift.shiftName}
												</option>
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
												<option value="${group.groupId}">${group.groupName}
												</option>
											</c:forEach>

									</select></td>
									<td><select name="meritType" id="meritType">
											<option value="none" selected>Select Merit Type</option>
											<option value="1">Merit List</option>
											<option value="2">1st Waiting List</option>
											<option value="3">2nd Waiting List</option>  
											<option value="4">Remaining Waiting List</option>
											<option value="5">Manual Admission</option>
											<!-- <option value="1-OUT">1st Merit Migration[OUT]</option>
<option value="2">1st Merit Migration[IN]</option>

<option value="3">2nd Merit List</option>
<option value="3-OUT">2nd Merit Migration[OUT]</option>
<option value="4">2nd Merit Migration[IN]</option>

<option value="5">Release Slip</option>
<option value="9">4th Phase</option>
<option value="15">BTEB 2nd Phase</option> -->

									</select> <input type="hidden" name="groupName" id="groupName" /></td>
									<td>
										<!--<button class="btn bg-green"  type="button" onclick="downloadResult()">Download Result</button>-->
										<input type="submit" style="width:140px;padding: 1px 12px;"
										class="btn btn-info buttonPDF" onclick="return downloadResult()"
										value="PDF Download">
									</td>

								</tr>
							</table>

						</form>

						<br>

<!-- 
						<form action="admissionResultCSV" name="resultDownloadForm"
							id="resultDownloadForm" method="post"
							enctype="multipart/form-data">
							<table style="width:400px;font-size:14px"
								class="table table-bordered table-striped cf">
								<tr style="background-color: #f5fffa;">
									<td style="color:black;"><strong>Shift </strong></td>
									<td style="color:black;"><strong>Version</strong></td>
									<td style="color:black;"><strong>Group</strong></td>
									<td style="color:black;"><strong>Merit Type</strong></td>
									<td style="color:black;"><strong>Download</strong></td>

								</tr>

								<tr>
									<td><select name="shiftIdCSV" id="shiftIdCSV">
											<option value="none" selected>Select Shift</option>
											<c:forEach items="${shiftList}" var="shift">
												<option value="${shift.shiftId}">${shift.shiftName}
												</option>
											</c:forEach>

									</select></td>

									<td><select name="versionIdCSV" id="versionIdCSV">
											<option value="none" selected>Select Version</option>
											<c:forEach items="${versionList}" var="version">
												<option value="${version.versionId}">${version.versionName}
												</option>
											</c:forEach>
									</select></td>

									<td><select name="groupIdCSV" id="groupIdCSV"
										onchange="changeGroupCSV()">
											<option value="none" selected>Select Group</option>
											<c:forEach items="${groupList}" var="group">
												<option value="${group.groupId}">${group.groupName}
												</option>
											</c:forEach>

									</select></td>
									<td><select name="meritTypeCSV" id="meritTypeCSV">
											<option value="none" selected>Select Merit Type</option>
											<option value="1">Merit List</option>
											<option value="2">1st Waiting List</option>
											

									</select> <input type="hidden" name="groupNameCSV" id="groupNameCSV" />
									</td>
									<td>
										
										<input type="submit" style="width:140px;padding: 1px 12px;"
										class="btn btn-info buttonCSV" onclick="return downloadResultCSV()"
										value="CSV Download">
									</td>

								</tr>
							</table>

						</form>
 -->

					</center>

					<!----body ------>

				</center>

			</div>
		</div>
		<br>

	</div>


	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp"%>
</div>


<script type="text/javascript">
	function downloadResult() {

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
		if ($("#meritType").val() == "none") {
			alert("Select a merit.");
			return false;
		}

		         
		 var formData = new FormData($('form')[0]);
		 $.ajax({
		 url: 'admissionResultReportAction',		
		 type: 'POST',
		 data: formData,
		 async: false,
		 cache: false,
		 contentType: false,
		 processData: false,
		 success: function(data) {



		 },
		 error: function(e) {

		 alert(e);
		 }
		 }); 

	}

	function downloadResultCSV() {

		if ($("#shiftIdCSV").val() == "none") {
			alert("Select a Shift.");
			return false;
		}
		if ($("#versionIdCSV").val() == "none") {
			alert("Select a version.");
			return false;
		}
		if ($("#groupIdCSV").val() == "none") {
			alert("Select a group.");
			return false;
		}
		if ($("#meritTypeCSV").val() == "none") {
			alert("Select a merit.");
			return false;
		}

	}

	function changeGroup() {
		$("#groupName").val($("#groupId option:selected").text());
		//alert($("#groupName").val());
	}

	function changeGroupCSV() {
		$("#groupNameCSV").val($("#groupIdCSV option:selected").text());
		//alert($("#groupName").val());
	}
</script>
<%@ include file="/WEB-INF/jsp/template/footerLite.jsp"%>

</div>
</body>
<style>
.buttonCSV:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,255,0,0.2);
}
.buttonPDF:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,255,0,0.2);
}
</style>
</html>