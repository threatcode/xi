<%@ include file="/WEB-INF/jsp/template/header.jsp" %>
<%@ include file="/WEB-INF/jsp/template/sidebar.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Arrays"%>

  <div class="row">
                    <div class="col-lg-12">
                        <div class="box">	

            <center>
                <h3 class="text-red">
                          <i class="fontello-doc"></i>
                        <span>:Applicant Information:</span>
                 </h3>

			<table  style="width:400px;font-size:14px"  class="table table-bordered table-striped cf">
				<tr >
					<td>Application ID:</td>
					<td>${requestScope.applicationId}</td>
				</tr >
				<tr>
					<td>Applicant Name:</td>
					<td>${requestScope.studentName}</td>
				</tr>
				<tr>
					<td>Father Name:</td>
					<td>${requestScope.fatherName}</td>
				</tr>
				<tr >
					<td>SSC Roll:</td>
					<td>${requestScope.sscRollNo}</td>
				</tr>
				<tr >
					<td>Board Name:</td>
					<td>${requestScope.boardName}</td>
				</tr>
				<tr >
					<td>Passing Year:</td>
					<td>${requestScope.passingYear}</td>
				</tr>
			</table>
			</center>

              </div>
             </div>
          </div>
<br/>
<hr>

  <div class="row">
                    <div class="col-lg-12">
                        <div class="box">
                        <center>
                        <h3 class="text-red">
                          <i class="fontello-doc"></i>
                        <span>:Application Information:</span>
                            </h3>
                        </center>
                            <div class="box-body table-responsive">
                                <table id="example1" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th>DISTRICT</th>
                                            <th>COLLEGE</th>
                                            <th>SHIFT</th>
                                            <th>VERSION </th>
                                            <th>GROUP</th>
                                            <th>APPLICATION TYPE</th>
                                            <th>PAYMENT STATUS</th>
                                            <th>PRIORITY ORDER</th>

                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                 
			
				        <c:forEach items="${requestScope.applicantCollegeInfoList}" var="applicantCollegeInfo">
							<tr>
								<td>${applicantCollegeInfo.districtName}</td>
								<td>${applicantCollegeInfo.collegeName}</td>
								<td>${applicantCollegeInfo.shiftName}</td>
								<td>${applicantCollegeInfo.versionName}</td>
								<td>${applicantCollegeInfo.groupName}</td>
<%-- 								<td>${applicantCollegeInfo.applicationType}</td> --%>
								
								
<td>
<c:if test="${applicantCollegeInfo.applicationType=='S'}">
SMS
</c:if>

<c:if test="${applicantCollegeInfo.applicationType=='W'}">
ONLINE
</c:if>
</td>								
								
								
<td>
<c:if test="${applicantCollegeInfo.paymentStatus=='Y'}">
<span class="status-metro status-active title="Active">PAID</span>
</c:if>

<c:if test="${applicantCollegeInfo.paymentStatus=='N'}">
<span class="status-metro status-suspended title="Suspended">NON-PAID</span>
</c:if>
</td>

                              <td>${applicantCollegeInfo.priorityOrder}</td>
								

							</tr>		
						</c:forEach>
 

                                    
                                    <tfoot>
                                        <tr>
 											<th>DISTRICT</th>
                                            <th>COLLEGE</th>
                                            <th>SHIFT</th>
                                            <th>VERSION </th>
                                            <th>GROUP</th>
                                            <th>APPLICATION TYPE</th>
                                            <th>PAYMENT STATUS</th>
                                            <th>PRIORITY ORDER</th>

                                        </tr>
                                    </tfoot>
                                </table>
                                
                            </div>


                            <!-- /.box-body -->
                        </div>
                        <!-- /.box -->
                    </div>
                </div>


    <!-- Main jQuery Plugins -->
    
    <script type='text/javascript' src="/board/resources/template/js/jquery.js"></script>

    <script src="/board/resources/template/js/datatables/jquery.dataTables.js" type="text/javascript"></script>
    <script src="/board/resources/template/js/datatables/dataTables.bootstrap.js" type="text/javascript"></script>

    <script src="/board/resources/template/js/footable/js/footable.js?v=2-0-1" type="text/javascript"></script>
    <script src="/board/resources/template/js/footable/js/footable.sort.js?v=2-0-1" type="text/javascript"></script>
    <script src="/board/resources/template/js/footable/js/footable.filter.js?v=2-0-1" type="text/javascript"></script>
    <script src="/board/resources/template/js/footable/js/footable.paginate.js?v=2-0-1" type="text/javascript"></script>
    <script src="/board/resources/template/js/footable/js/footable.paginate.js?v=2-0-1" type="text/javascript"></script>	    
	
   
  <script type="text/javascript">
    (function($) {
        "use strict";
        $("#example1").dataTable();
        $('#example2').dataTable({
            "bPaginate": true,
            "bLengthChange": false,
            "bFilter": false,
            "bSort": true,
            "bInfo": true,
            "bAutoWidth": false
        });
    })(jQuery);

    (function($) {
        "use strict";
        $('.footable-res').footable();
    })(jQuery);

    (function($) {
        "use strict";
        $('#footable-res2').footable().bind('footable_filtering', function(e) {
            var selected = $('.filter-status').find(':selected').text();
            if (selected && selected.length > 0) {
                e.filter += (e.filter && e.filter.length > 0) ? ' ' + selected : selected;
                e.clear = !e.filter;
            }
        });

        $('.clear-filter').click(function(e) {
            e.preventDefault();
            $('.filter-status').val('');
            $('table.demo').trigger('footable_clear_filter');
        });

        $('.filter-status').change(function(e) {
            e.preventDefault();
            $('table.demo').trigger('footable_filter', {
                filter: $('#filter').val()
            });
        });

        $('.filter-api').click(function(e) {
            e.preventDefault();

            //get the footable filter object
            var footableFilter = $('table').data('footable-filter');

            alert('about to filter table by "tech"');
            //filter by 'tech'
            footableFilter.filter('tech');

            //clear the filter
            if (confirm('clear filter now?')) {
                footableFilter.clearFilter();
            }
        });
    })(jQuery);
    </script>

<%@ include file="/WEB-INF/jsp/template/footer.jsp" %>
