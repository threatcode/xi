<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Arrays"%>

						<center>
                        <h3 class="text-red">
                          <i class="fontello-doc"></i>
                        <span>:Special Quota Applicant:</span>
                            </h3>
                        </center>
                         <br/>


 <!--  <div class="row"> -->
                    <!-- <div class="col-lg-12"> -
                   <!--     <div class="box">   
<%--                             <div class="box-header">
                                <!-- tools box -->
                                <div class="pull-right box-tools">

                                    <span class="box-btn" data-widget="collapse"><i class="fa fa-minus"></i>
                                    </span>
                                    <span class="box-btn" data-widget="remove"><i class="fa fa-times"></i>
                                    </span>
                                </div>
                                <h3 class="box-title"><i class="fontello-doc"></i> 
                                    <span>List Of Applicant.</span>
                                </h3>
                            </div> --%>
                            <!-- /.box-header -->
                            <form action="grantSpecialQuota" method="post" id="spesialQuotaGrantForm" name="spesialQuotaGrantForm">
                            <div class="box-body table-responsive">
                                <table id="example2" class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>APPLICATION ID</th>
                                            <th>APPLICANT NAME</th>
                                            <th>SSC ROLL</th>
                                            <th>PASSSING YEAR</th>
                                            <th>BOARD</th>
                                            <th>SHIFT</th>
                                            <th>VERSION</th>
                                            <th>GROUP</th>
                                            <th>STATUS</th>
                                            <th>CHECK</th>

                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                 
			
				        <c:forEach items="${requestScope.newspecialQuotaStudentinfoList}" var="newspecialQuotaStudentinfo">
							<tr>
								<td>${newspecialQuotaStudentinfo.applicationID}</td>
								<td>${newspecialQuotaStudentinfo.applicantName}</td>
								<td>${newspecialQuotaStudentinfo.sscRollNo}</td>
								<td>${newspecialQuotaStudentinfo.sscPassingYear}</td>
								<td>${newspecialQuotaStudentinfo.boardName}</td>
								<td>${newspecialQuotaStudentinfo.shiftName}</td>
								<td>${newspecialQuotaStudentinfo.versionName}</td>
								<td>${newspecialQuotaStudentinfo.groupName}</td>
<td>
<c:if test="${newspecialQuotaStudentinfo.specialQuotaGrant=='Approved'}">
<span class="status-metro status-active title="Active">${newspecialQuotaStudentinfo.specialQuotaGrant}</span>
</c:if>

<c:if test="${newspecialQuotaStudentinfo.specialQuotaGrant=='Pending'}">

<c:if test="${newspecialQuotaStudentinfo.specialQuotaStatus=='Y'}">
<span class="status-metro status-suspended title="Suspended">${newspecialQuotaStudentinfo.specialQuotaGrant}</span>
</c:if>

<c:if test="${newspecialQuotaStudentinfo.specialQuotaStatus=='N'}">
<span class="status-metro status-disabled title="Disabled">Not Applied</span>
</c:if>

</c:if>

</td>

	<td>
	 <c:if test="${newspecialQuotaStudentinfo.specialQuotaGrant=='Pending'}">
	<input type="checkbox" class="boSelect" name="applicationID" value="${newspecialQuotaStudentinfo.applicationID}">
	</c:if>
	
	<c:if test="${newspecialQuotaStudentinfo.specialQuotaGrant=='Approved'}">
	<input type="checkbox" class="boSelect"  disabled="disabled" name="applicationID" value="${newspecialQuotaStudentinfo.applicationID}">
	</c:if>
	</td>
								

							</tr>		
						</c:forEach>
 

                                </table>
                                
                            </div>
                            <div style="width:100px;float: right">
                            
                                  <button class="btn bg-parpel" style="width:100px">Approve</button>
                                </div>
                            </form>
                            <!-- /.box-body -->
                      <!--   </div> -->
                        <!-- /.box -->
<!--                     </div>-->
                 <!-- </div>   -->
              
              <br/><br/>


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
       /*  $("#example1").dataTable(); */
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


