<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp"%>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2>Colleges yet to confirm admission</h2>
			<div class="sidebar_section_text">
						<div class="box_row" style="background-color: #1874cd;color: white;text-align: left;font-weight: bold;padding-top: 0px;font-size: 16px;height: 22px;">
							<center>:: Colleges yet to confirm admission in merit list ::</center>
						</div>
			<br>
				<center>

					<!---- body ----->
					<div class="row">
						<div class="col-lg-12">
							<div class="box">

								<center>
									<table id="example1" class="table table-bordered table-striped">
										<thead>
											<tr>
												<th>EIIN</th>
												<th>COLLEGE NAME</th>
												<th>Mobile No.</th>
												<th>District Name</th>

											</tr>
										</thead>

										<tbody>


											<c:forEach items="${requestScope.nonReceivedCollegeList}"
												var="colleges">
												<tr>
													<td>${colleges.eiin}</td>
													<td>${colleges.college_name}</td>
													<td>${colleges.collegeMobile}</td>
													<td>${colleges.dist_name}</td>

												</tr>
											</c:forEach>

										</tbody>

									</table>


								</center>
								<form action="downloadnonReceivedListCSV.action" method="post"
									id="downloadCSV" name="downloadCSV">


									<div style="width:100px;float: left;right-padding:0cm">
										<button style="width:150px;padding: 1px 12px;"
											class="btn btn-success buttonCSV" onclick="downloadsqCSV()">Download
											CSV</button>
									</div>

								</form>
							</div>
						</div>
					</div>





					<!----body ------>

				</center>

			</div>
		</div>
		<br>

	</div>



	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp"%>
</div>


<script
	src="/board/resources/template/js/datatables/jquery.dataTables.js"
	type="text/javascript"></script>
<script
	src="/board/resources/template/js/datatables/dataTables.bootstrap.js"
	type="text/javascript"></script>

<script type="text/javascript">
    (function($) {
        "use strict";
        $("#example1").dataTable();
        
    })(jQuery);

    </script>
<%@ include file="/WEB-INF/jsp/template/footerLite.jsp"%>

</div>
</body>
</html>