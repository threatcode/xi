
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Arrays"%>




	
	                     <center>
                        <h4 class="text-red">
                          <i class="fontello-doc"></i>
                        <span>Shift-Version-Group wise Result Info:</span>
                         </h4>
                        </center>
                         <br/>


  
                              <!-- /.box-header -->
                           <div class="box-body table-responsive"> 
                             <!--   <table id="example1" class="table table-bordered table-striped"> -->
                             <table  id="example2" class="table table-bordered table-hover" >
                                    <thead>
                                        <tr>
                                            <th>Shift</th>
                                            <th>Version</th>
                                            
                                            <th>Group</th>
                                            <th>Apply</th>
                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                 
			
				        <c:forEach items="${requestScope.shiftVersionGroupApplicationNumberOfCollegeList}" var="shiftVersionGroupApplicationNumberOfCollege">
							<tr>
								<td>${shiftVersionGroupApplicationNumberOfCollege.shiftName}</td>
								<td>${shiftVersionGroupApplicationNumberOfCollege.versionName}</td>
								<td>${shiftVersionGroupApplicationNumberOfCollege.groupName}</td>
								<td>${shiftVersionGroupApplicationNumberOfCollege.applicationNunber}</td>

							</tr>		
						</c:forEach>
 
                               </tbody>
                                    
<!--                                      <tfoot>
                                        <tr>
                                           <th>Shift</th>
                                            <th>Version</th>
                                            
                                            <th>Group</th>
                                            <th>Application Number</th>

                                        </tr>
                                    </tfoot> -->
                                </table>
                             </div>
                            <!-- /.box-body -->
			

 
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
 

