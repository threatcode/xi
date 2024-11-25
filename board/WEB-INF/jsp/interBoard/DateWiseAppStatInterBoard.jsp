<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<div class="toogle_wrap">
				<div class="toggle_container">
					<p>
						<font style="color: black; font-size:20px;">Date-Wise Application VS Applicant Status.</font>				
					</p>
				</div>				
			</div>
		</div>
		<br>
<!---- body ----->		
<div id="container" style="width:82.3% ;float: right;">
			
			</div>

                             <center>
                             <div style="width:50%; padding-left: 20px;" >
                             
                             <p class="well">Date-Wise Application VS Applicant Status </p>
                             
                             <table  id="datatable" border="1"  class="table table-bordered table-striped cf" >
                                    <thead>
                                        <tr>
                                            <th>Date</th>
                                            <th>Application</th>
                                            <th>Applicant</th>
                                            

                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                 
			
        <c:forEach items="${requestScope.dateWiseApplicationStatBoardList}" var="dateWiseApplicationStatBoard">
							<tr>
								<td>${dateWiseApplicationStatBoard.appDate}</td>
								<td>${dateWiseApplicationStatBoard.totalApplication}</td>
								<td>${dateWiseApplicationStatBoard.totalApplicant}</td>
								

							</tr>		
						</c:forEach>
 
	 
                                    </tbody>
                                    

                                </table>
                                </div>
                                </center>
		
<!----body ------>	
	</div>


	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp" %>
</div>
<script type='text/javascript' src="/board/resources/js/jquery.min.js"></script>
<script src="/board/resources/3rdParty/highChartJs/highcharts.js"></script>
<script src="/board/resources/3rdParty/highChartJs/modules/data.js"></script>
<script src="/board/resources/3rdParty/highChartJs/modules/exporting.js"></script>
<!-- bootstrap Framework plugins -->
<%-- <script src="/board/resources/bootstrap/js/bootstrap.min.js"></script> --%>
<script type="text/javascript">
    $(function () {

        $('#container').highcharts({
            data: {
                table: document.getElementById('datatable')
            },
            chart: {
                type: 'column'
            },
            title: {
                text: 'Date-Wise Application -Applicant Stat.'
            },
            yAxis: {
                allowDecimals: false,
                title: {
                    text: 'Total Applications'
                }
            },
            tooltip: {
                formatter: function() {
                    return '<b>'+ this.series.name +'</b><br/>'+
                            this.y
                }
            }
        });
    });
</script>
<%@ include file="/WEB-INF/jsp/template/footerLite.jsp" %>

</div>
</body>
</html>