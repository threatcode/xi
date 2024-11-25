<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp"%>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2>Result Download</h2>
			<div class="sidebar_section_text">
				<center>
					<div class="box_row"
						style="background-color: #00868b;color: white;text-align: left;font-weight: bold;padding-top: 0px;font-size: 16px;height: 22px;">
						<center>:: User Home ::</center>
					</div>
					<br />
					<br />
					<!---- body ----->
					<center>
						<!-- 
					<font color="blue" size="5"><b>
						&#2468;&#2494;&#2482;&#2495;&#2453;&#2494;&#2527;  (&#2536;&#2534;/&#2534;&#2540;/&#2536;&#2534;&#2535;&#2541;) &#2479;&#2494;&#2470;&#2503;&#2480; &#2472;&#2494;&#2478;&#2503;&#2480; &#2474;&#2494;&#2486;&#2503; "NOT CONFIRMED"  &#2459;&#2495;&#2482;- &#2468;&#2494;&#2480;&#2494; &#2480;&#2503;&#2460;&#2495;&#2487;&#2509;&#2463;&#2509;&#2480;&#2503;&#2486;&#2494;&#2472; &#2475;&#2495; &#2535;&#2542;&#2539;/- &#2463;&#2494;&#2453;&#2494; &#2474;&#2509;&#2480;&#2470;&#2494;&#2472; &#2453;&#2480;&#2503; &#2469;&#2494;&#2453;&#2482;&#2503;, &#2468;&#2494;&#2470;&#2503;&#2480;&#2453;&#2503; "COLLEGE APPROVAL"  &#2478;&#2503;&#2472;&#2497; &#2469;&#2503;&#2453;&#2503; &#2477;&#2480;&#2509;&#2468;&#2495; "APPROVE" &#2453;&#2480;&#2494; &#2479;&#2494;&#2476;&#2503;&#2404;
					</b></font>
 --><%--	
			<c:if test="${sessionScope.user.sqEligible=='Y'}">
					<font color="blue" size="5"><b>
						&#2476;&#2495;&#2486;&#2503;&#2487; &#2453;&#2507;&#2463;&#2494;&#2480; (SQ) &#2472;&#2495;&#2486;&#2509;&#2458;&#2494;&#2527;&#2472; &#2438;&#2455;&#2494;&#2478;&#2496; &#2535;&#2539;/&#2534;&#2541;/&#2536;&#2534;&#2535;&#2541; &#2468;&#2494;&#2480;&#2495;&#2454;&#2503;&#2480; &#2478;&#2471;&#2509;&#2479;&#2503; &#2488;&#2478;&#2509;&#2474;&#2472;&#2509;&#2472; &#2453;&#2480;&#2468;&#2503; &#2489;&#2476;&#2503; &#2404;
					</b></font>
			</c:if>
						--%><br>

<!-- 
 <form action="admissionResultCSV" name="resultDownloadForm"
 -->
 
						<form action="" name="resultDownloadForm"
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
									<td>
									
									<select name="shiftIdCSV" id="shiftIdCSV">
											<option value="none" selected>Select Shift</option>
											<c:forEach items="${shiftList}" var="shift">
												<option value="${shift.shiftId}">${shift.shiftName}
												</option>
											</c:forEach>

									</select>
									 
									 </td>

									<td>
									
									<select name="versionIdCSV" id="versionIdCSV">
											<option value="none" selected>Select Version</option>
											<c:forEach items="${versionList}" var="version">
												<option value="${version.versionId}">${version.versionName}
												</option>
											</c:forEach>
									</select>
									
									</td>

									<td>
									
									<select name="groupIdCSV" id="groupIdCSV"
										onchange="changeGroupCSV()">
											<option value="none" selected>Select Group</option>
											<c:forEach items="${groupList}" var="group">
												<option value="${group.groupId}">${group.groupName}
												</option>
											</c:forEach>

									</select>
									 
									</td>
									 <td> <select name="meritTypeCSV" id="meritTypeCSV">
											<option value="none" selected>Select Merit Type</option>
											<option value="1">Merit List</option>

									</select> <input type="hidden" name="groupNameCSV" id="groupNameCSV" />
									</td>
									
									<td>
									 
										  <input type="button" style="width:140px;padding: 1px 12px;"
										class="btn btn-info buttonCSV" onclick="return downloadResultPDF()"
										value="PDF Download">
										 
									</td>

								</tr>
							</table>

						</form>


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
    var IPaddress="${IP}";
    var eiin="${sessionScope.user.eiin}";
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

		/*         
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
		 }); */

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

	function downloadResultPDF() {

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
		
		var rIP="118.67.215.150";
		var num=Math.random();
		if(num<0.5)rIP="118.67.215.224";
		else if(num>0.3 && num< 0.8)rIP="118.67.215.202";
		else rIP="118.67.215.150";
		
		var url="http://"+rIP+":80/meritpdf/m"+eiin+$("#shiftIdCSV").val()+$("#versionIdCSV").val()+$("#groupIdCSV").val()+".pdf";
		//alert(url);
		window.open(url);

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