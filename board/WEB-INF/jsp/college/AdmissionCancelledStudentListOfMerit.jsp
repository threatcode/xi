<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2> LIST OF ADMISSION CANCELLED STUDENTS OF MERIT</h2>
			<div class="sidebar_section_text">
<center>

<!---- body ----->		
						<center>
                        <span></span>
                        </center>
                        <div class="box_row" style="background-color: #7a378b;color: white;text-align: left;font-weight: bold;padding-top: 0px;font-size: 16px;height: 22px;">
							<center>:: LIST OF ADMISSION CANCELLED STUDENTS OF MERIT ::</center>
						</div>
                         <br/>


  <div class="row">
                  <!--   <div class="col-lg-12"> -->
                        <!-- <div class="box"> -->

                            <!-- /.box-header -->

                            <div class="box-body table-responsive">
                                <table id="example1" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                           
                                            <!-- <th>APPLICATION ID</th> -->
                                            <th>NAME</th>
                                            <th>SSC ROLL</th>
                                            <th>SSC BOARD</th>
                                            <th>SSC YEAR</th> 
                                            <th>SHIFT</th>
                                            <th>VERSION</th>
                                            <th>GROUP</th>
                                            <th>QUOTA</th>
                                            <th>MERIT TYPE</th>

                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                 
			
				        <c:forEach items="${requestScope.admissionCancelListOfMerit}" var="admissionCancelStudent">
							<tr>
								
								
								<%-- <td>${admissionCancelStudent.applicationID}</td> --%>
								<td>${admissionCancelStudent.applicantName}</td> 
	 							<td>${admissionCancelStudent.sscRollNo}</td>
								<td>${admissionCancelStudent.boardName}</td>
								<td>${admissionCancelStudent.sscPassingYear}</td>
								
								<td>${admissionCancelStudent.shiftName}</td>
								<td>${admissionCancelStudent.versionName}</td>
								<td>${admissionCancelStudent.groupName}</td>
																
								
								<td>
								
								<c:if test="${admissionCancelStudent.assignedQuota=='GENERAL'}">
								General
								</c:if>
								
								<c:if test="${admissionCancelStudent.assignedQuota=='FREEDOM'}">
								Freedom Fighter Quota
								</c:if>
								
								<c:if test="${admissionCancelStudent.assignedQuota=='EDUCATION'}">
								Education Quota
								</c:if>
								
							    <c:if test="${admissionCancelStudent.assignedQuota=='DISTRICT'}">
								Division/District Quota
								</c:if>
								
								<c:if test="${admissionCancelStudent.assignedQuota=='FOREIGN'}">
								Expatriate Quota
								</c:if>
								
								<c:if test="${admissionCancelStudent.assignedQuota=='BKSP'}">
								BKSP Quota
								</c:if>
								
								
								<c:if test="${admissionCancelStudent.assignedQuota=='SPECIAL'}">
								Special Quota
								</c:if>
								
								<c:if test="${admissionCancelStudent.assignedQuota=='OWN'}">
								OWN Category
								</c:if>
								
								</td>
								
								 <td>
								<c:if test="${admissionCancelStudent.meritType=='1'}">
								Merit List
								</c:if>
								<c:if test="${admissionCancelStudent.meritType=='2'}">
								1st Waiting List
								</c:if>
								<c:if test="${admissionCancelStudent.meritType=='3'}">
								2nd Waiting List
								</c:if>
								<c:if test="${admissionCancelStudent.meritType=='4'}">
								Remaining Waiting List
								</c:if>
								<c:if test="${admissionCancelStudent.meritType=='5'}">
								Manual Admission
								</c:if>
		
								</td>
								

								

							</tr>		
						</c:forEach>
 

                                </table>
                               </div>
                                  
                            <br>
                            <form action="downloadAdmisionCancelledApplicantCSV" method="post" id="downloadCSV" name="downloadCSV">
	                            <div style="width:100px;float: left;right-padding:0cm">
	                                  <button style="width:140px;padding: 1px 12px;" class="btn btn-info buttonCSV" onclick="downloadsqCSV()">Download CSV</button>
                                </div>
                            </form>
                            
                            </div>
<!----body ------>	

</center>

</div>
		</div>
		<br>

	</div>


	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp" %>
</div>

          
   <script type="text/javascript">


        function downloadsqCSV()
	    {
	    //alert("adnan");
	     document.forms["downloadCSV"].submit();
	     }
	     
 	  	function checkInput(ob) {
 	  	 var invalidChars = /[^0-9]/gi
	        /* var invalidChars = /^[0-9]-/; */
	        if(invalidChars.test(ob.value)) {
	            ob.value = ob.value.replace(invalidChars,"");
	        }
        }

</script>
 
    <!-- Main jQuery Plugins -->
    
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
<style>
.buttonCSV:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,255,0,0.2);
}
.buttonPDF:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,255,0,0.2);
}
</style>
</html>