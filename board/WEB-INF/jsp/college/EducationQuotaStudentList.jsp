<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2>::Education Quota Applicants::</h2>
			<div class="sidebar_section_text">
<center>
 <span>::Education Quota Applicants::</span>
<br/><br/>
<!---- body ----->		
                            <form action="grantEducationQuota" method="post" id="educationQuotaGrantForm" name="educationQuotaGrantForm">
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
                                            <th>STATUS</th>
                                            <th>APPROVE</th>

                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                 
			
				        <c:forEach items="${requestScope.educationQuotaStudentList}" var="educationQuotaStudent">
							<tr>
								<td>${educationQuotaStudent.applicationID}</td>
								<td>${educationQuotaStudent.applicantName}</td>
								<td>${educationQuotaStudent.fatherName}</td>
								<td>${educationQuotaStudent.sscRollNo}</td>
								<td>${educationQuotaStudent.sscPassingYear}</td>
								<td>${educationQuotaStudent.boardName}</td>
<td>
<c:if test="${educationQuotaStudent.educationQuotaGrant=='Approved'}">
<span class="status-metro status-active title="Active">${educationQuotaStudent.educationQuotaGrant}</span>
</c:if>

<c:if test="${educationQuotaStudent.educationQuotaGrant=='Pending'}">
<span class="status-metro status-suspended title="Suspended">${educationQuotaStudent.educationQuotaGrant}</span>
</c:if>
</td>

	<td>
	<c:if test="${educationQuotaStudent.educationQuotaGrant=='Pending'}">
	<input type="checkbox" class="boSelect" name="applicationID" value="${educationQuotaStudent.applicationID}">
	</c:if>
	
	<c:if test="${educationQuotaStudent.educationQuotaGrant=='Approved'}">
	<input type="checkbox" class="boSelect"  name="applicationID" value="${educationQuotaStudent.applicationID}">
	</c:if>
	</td>
								

							</tr>		
						</c:forEach>
 


                                </table>
                                
                            
                            <div style="width:310px;float: right;right-padding:0cm">
                            	<input type="hidden" name="formAction" id="formAction" value="" />
                                <!-- <button class="btn bg-parpel" style="width:140px">Approve EQ Quota</button> -->
                                
                                <div>
                                    <input type="button" style="width:200px;padding: 1px 12px;" class="btn btn-info" name="approve" value="Approve Educational Quota" onclick="submitApproveForm('approve')" />
                                  	</div>
                                  	&nbsp;
                                  	<div>
                                    <input type="button" style="width:150px;padding: 1px 12px;" class="btn btn-info" name="disApprove" value="Revoke Approval" onclick="submitApproveForm('disapprove')" />
                                  	</div>
                                
                                
                                
                            </div>
                      <!--       <button class="btn bg-green">Approve Special Quota</button> -->
                            </form>
                                <form action="downloadeqCSV" method="post" id="downloadCSV" name="downloadCSV">
                            
                            <div style="width:100px;float: left;right-padding:0cm">
                                  <button style="width:150px;padding: 1px 12px;" class="btn btn-info" onclick="downloadeqCSV()">Download CSV</button>
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
         
           <div class="row">
                    <div class="col-lg-12">
                        <div class="box">	

            <center>
                <h4 class="text-red">
                        <span>:: Add NEW EQ STUDENT::</span>
                 </h4>

			<table  style="width:400px;font-size:12px"  class="table table-bordered table-striped cf">
				<tr >
					<td>APPLICAION ID:</td>
					<td><input type="text" id="applicationID" name="applicationID"  maxlength="15"  required/></td>
				</tr >



                <tr >
<td colspan="2" align="right"><input type="button" style="width:100px;padding: 1px 12px;" class="btn btn-info" onclick="searchsqstudent()" name="searchsqstudent" value="SEARCH" class="btn btn-info"  /></td>
				</tr>
			</table>

			</center>

              </div>
             </div>
          </div>
<br/>
<hr>


<div id="showeqstudentInfo">

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
   
   function submitApproveForm(status){
		document.getElementById("formAction").value = status;
		if(status == "disapprove"){
			if(confirm("Are you sure want to Revoke Approval for these applicants")){
				document.getElementById("educationQuotaGrantForm").submit();
			}
		} else {
			document.getElementById("educationQuotaGrantForm").submit();
		}		
	}
   
      function searchsqstudent() {

       	  if(document.getElementById("applicationID").value=="")
        {
           alert("Please Give Application ID of student");
           return;
        }

       $('#showeqstudentInfo').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "showneweqstudent",
            dataType: 'text',
            async   : false,
            data    : {

                applicationID: $("#applicationID").val()
            }
        }).done(function (msg) {
                    $("#showeqstudentInfo").html(msg);
                })
                .always(function () {
                    //$('#sw-val-step-3').unmask();
                })
                .fail(function (data) {
                    if (data.responseCode)
                        alert(data.responseCode);
                });

 }
     //End of fetchApplicationInformation


</script>	
    <script type="text/javascript">

         function downloadeqCSV()
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