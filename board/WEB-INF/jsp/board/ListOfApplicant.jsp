<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Arrays"%>

                       <center>
                        <h3 class="text-red">
                          <i class="fontello-doc"></i>
                        <span>:College Applicant Info:</span>
                            </h3>
                        </center>
                         <br/>



                            <div class="box-body table-responsive">
                                <table id="example1" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th>APPLICATION ID</th>
                                            <th>APPLICANT NAME</th>
                                            <th>FATHER NAME</th>
                                            <th>SSC ROLL NO</th>
                                            <th>PASSSING YEAR</th>
                                            <th>BOARD NAME</th>

                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                 
			
				        <c:forEach items="${requestScope.studentList}" var="students">
							<tr>
<td><a href="applicantDetails?applicationID=${students.applicationID}&eiinCode=${students.eiinCode}" target="_blank" >${students.applicationID}</a></td>								
<%-- 								<td>${students.applicationID}</td> --%>
								<td>${students.applicantName}</td>
								<td>${students.fatherName}</td>
								<td>${students.sscRollNo}</td>
								<td>${students.sscPassingYear}</td>
								<td>${students.boardName}</td>

							</tr>		
						</c:forEach>
 

                                    
                                    <tfoot>
                                        <tr>
                                            <th>APPLICATION ID</th>
                                            <th>APPLICANT NAME</th>
                                            <th>FATHER NAME</th>
                                            <th>SSC ROLL NO</th>
                                            <th>PASSSING YEAR</th>
                                            <th>BOARD NAME</th>

                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                            <!-- /.box-body -->
 
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


