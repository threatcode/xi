<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp"%>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2>Admission Receive College List</h2>
			<div class="sidebar_section_text">
			
					<div class="box_row" style="background-color: #473c8b;color: white;text-align: left;font-weight: bold;padding-top: 0px;font-size: 16px;height: 22px;">
										<center>:: Admission Receive College List for Merit List ::</center>
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
                                            <th>MOBILE NO.</th>
                                            <th>DISTRICT NAME</th>
                                            <th>TOTAL RESULT IN MERIT LIST</th>
                                            <th>NOT APPROVED IN MERIT LIST</th>
                                            <th>COLLEGE RECEIVED IN MERIT LIST</th>
                                            <th>ADMISSION CANCELLED IN MERIT LIST</th>
                                            
                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                 
			
								        <c:forEach items="${requestScope.receivedCollegeList}" var="colleges">
											<tr>
												<td>${colleges.eiin}</td>
												<td>${colleges.college_name}</td>
												<td>${colleges.collegeMobile}</td>
												<td>${colleges.dist_name}</td>
												<td>${colleges.totalResult}</td>
												<td>${colleges.notApproved}</td>
												<td>${colleges.collegeRceived}</td>
												<td>${colleges.admissionCancelled}</td>
	
											</tr>		
										</c:forEach>

                                    </tbody>
                                   
                                </table>


								</center>
								<form action="downloadReceivedListCSV.action" method="post"
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