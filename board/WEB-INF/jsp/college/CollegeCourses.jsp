<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2>College Course Info</h2>
			<div class="sidebar_section_text">

<center>

<!---- body ----->			
                                <table id="example1" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                    <th>SHIFT</th>
					<th>VERSION</th>
					<th>GROUP</th>
					<th>GENDER</th>
					<th>MINIMUM GPA</th>
					<th>ALLOTED SEAT</th>
					<th>SPECIAL QUOTA</th>
					<th>SPECIAL QUOTA SEAT (%)</th>
					<th>SPECIAL QUOTA GPA</th>
					<th>GPA FOR THE SAME INSTITUTE</th>

                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                 
			
				        <c:forEach items="${requestScope.courseList}" var="courses">
							<tr>
                                <td>${courses.shift}</td>
								<td>${courses.medium}</td>
								<td>${courses.groupName}</td>
								<td>${courses.gender}</td>
								<td>${courses.minGpa}</td>
								<td>${courses.totalSeat}</td>
								<td>
							    <c:if test="${courses.spcQuota=='Y'}">
								Yes
								</c:if>
								<c:if test="${courses.spcQuota=='N'}">
								No
								</c:if>	
								</td>
								<td>${courses.quotaPercent}</td>
								<td>${courses.quotaGpa}</td>
								<td>${courses.ownGpa}</td>

							</tr>		
						</c:forEach>
 

                                    </tbody>
                                    
                                    <tfoot>

                                    </tfoot>
                                </table>

<!----body ------>	

</center>

</div>
		</div>
		<br>

	</div>


	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp" %>
</div>
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


<%@ include file="/WEB-INF/jsp/template/footerLite.jsp" %>

</div>
</body>
</html>