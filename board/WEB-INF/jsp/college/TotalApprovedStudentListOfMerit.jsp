<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2> LIST OF TOTAL APPROVED STUDENTS OF MERIT</h2>
			<div class="sidebar_section_text">
<center>

<!---- body ----->		
						<center>
                        
                        <span>:: LIST OF TOTAL APPROVED STUDENTS OF MERIT::</span>
                            
                        </center>
                         <br/>


  <div class="row">
                  <!--   <div class="col-lg-12"> -->
                        <!-- <div class="box"> -->

                            <!-- /.box-header -->
                            
                            <div class="box-body table-responsive">
                                <table id="example1" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                                                             
                                           <!--  <th>APPLICATION ID</th> -->
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
                                 
			
				        <c:forEach items="${requestScope.totalApprovedStudentListOfMerit}" var="approvedStudent">
							<tr>
								
								
								<%-- <td>${approvedStudent.applicationID}</td> --%>
								<td>${approvedStudent.applicantName}</td> 
	 							<td>${approvedStudent.sscRollNo}</td>
								<td>${approvedStudent.boardName}</td>
								<td>${approvedStudent.sscPassingYear}</td>
								
								<td>${approvedStudent.shiftName}</td>
								<td>${approvedStudent.versionName}</td>
								<td>${approvedStudent.groupName}</td>
								
								
								

								
								
								<td>
								<c:if test="${approvedStudent.assignedQuota=='GEN'}">
								General
								</c:if>
								<c:if test="${approvedStudent.assignedQuota=='D_G'}">
								General
								</c:if>
								<c:if test="${approvedStudent.assignedQuota=='E_G'}">
								General
								</c:if>
								<c:if test="${approvedStudent.assignedQuota=='F_G'}">
								General
								</c:if>
								<c:if test="${approvedStudent.assignedQuota=='S_G'}">
								General
								</c:if>
								
								
								<c:if test="${approvedStudent.assignedQuota=='FQ'}">
								Freedom Fighter
								</c:if>
								
								<c:if test="${approvedStudent.assignedQuota=='EQ'}">
								Education Quota
								</c:if>
								
							    <c:if test="${approvedStudent.assignedQuota=='DQ'}">
								Division Quota
								</c:if>
								
								<c:if test="${approvedStudent.assignedQuota=='ZQ'}">
								Zilla Quota
								</c:if>
								
								<c:if test="${approvedStudent.assignedQuota=='SQ'}">
								Special Quota
								</c:if>
								
								<c:if test="${approvedStudent.assignedQuota=='OWN'}">
								OWN Category
								</c:if>
								
								</td>
								
								
								
						        <td>
								<c:if test="${approvedStudent.meritType=='1'}">
								1st Merit List
								</c:if>
								<c:if test="${approvedStudent.meritType=='2'}">
								1st Merit Migration[IN]
								</c:if>
								<c:if test="${approvedStudent.meritType=='3'}">
								2nd Merit List
								</c:if>
								<c:if test="${approvedStudent.meritType=='4'}">
								2nd Merit Migration[IN]
								</c:if>
								<c:if test="${approvedStudent.meritType=='5'}">
								1st Release Slip
								</c:if>
								<c:if test="${approvedStudent.meritType=='9'}">
								4th Phase
								</c:if>
								
								
								</td>

					

							</tr>
									
						</c:forEach>
 

                                </table>
                               </div>
                                  
                            

                               
                      <!--       <button class="btn bg-green">Approve Special Quota</button> -->
                           
                            
                            <form action="downloadTotalApprovedApplicantCSV" method="post" id="downloadCSV" name="downloadCSV">
	                            <div style="width:100px;float: left;right-padding:0cm">
	                                  <button style="width:140px;padding: 1px 12px;" class="btn btn-info" onclick="downloadsqCSV()">Download CSV</button>
                                </div>
                            </form>

                            </div>
                            <!-- /.box-body -->
                        <!-- </div> -->
                        <!-- /.box -->
                   <!--  </div> -->
            
<hr>
        <c:if test="${requestScope.successMessage!=''}">
            <div class="alert alert-success">
				<button class="close" type="button" data-dismiss="alert">×</button>
				<span class="entypo-thumbs-up"></span>
				<strong>Successful!!</strong>
			  	${requestScope.successMessage}
			</div>
        </c:if>
        <c:if test="${requestScope.errorMessage!=''}">
            <div class="alert alert-danger">
				<button class="close" type="button" data-dismiss="alert">×</button>
				<span class="entypo-attention"></span>
				<strong>Error!!</strong>
			  	${requestScope.errorMessage}
			</div>
        </c:if>                        
	
<!----body ------>	

</center>

</div>
		</div>
		<br>

	</div>


	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp" %>
</div>

          
	
   <script type="text/javascript">
	function submitCancelForm()
	{
	document.getElementById("cancelApproveStudentOfMeritForm").submit();
	}
	function validateForm() {
	    var x = confirm("Are you sure to \"Cancel Admission\" for these applicants?");
	    if (x == true) {
	        return true;
	    } else 
	    	return false;
	}

     //End of fetchApplicationInformation

function uploadXLSapplicants()
{
  var FormData = $('#FormXLSapplicant').serializeArray();
  $.ajax({
            type    : "POST",
            url     : "uploadXLSapplicant",
            dataType: 'text/html',
            async   : false,
            data    : FormData
        }).done(function (msg) {
                    $("#showsqstudentInfo").html(msg);
                })
                .always(function () {
                    //$('#sw-val-step-3').unmask();
                })
                .fail(function (data) {
                    if (data.responseCode)
                        alert(data.responseCode);
                });
}


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
<%@ include file="/WEB-INF/jsp/template/footerLite.jsp" %>

</div>
</body>
</html>