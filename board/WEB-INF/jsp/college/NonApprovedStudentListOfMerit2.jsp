<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>
<style>
#modelNoSelection {
/* position:absolute;
background:#F5F5DC; */
/* width:400px;
height:400px; */
/* border:5px solid #000;
z-index: 9002; */
}
</style>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2>LIST OF NON-APPROVED STUDENTS</h2>
			<div class="sidebar_section_text">
<center>

<!---- body ----->		

<%--                 <center>

                        <span>:: BULK RECEIVE::</span>
               
 <form  action="uploadXLSNonApprovedapplicant" id="FormXLSapplicant" method="post" enctype="multipart/form-data">
			<table  style="width:400px;font-size:14px"  class="table table-bordered table-striped cf">
                <tr >
                  <td>
                   
				     <input type="file" name="myFile" id="myFile" />
				     
				    
                  </td>
                  <td colspan="1" align="right">
                  <input type="submit"  value="Upload XLS" class="btn btn-info" style="width:90px;padding: 1px 12px;" />

                  </td>
                  
				</tr>
				
				<tr>
				<td colspan="2" align="center">
				 <label><a href="/board/resources/Admission Receive Template.xlsx">XLS Template Download</a></label>
				</td>
				</tr>
			</table>
</form>
			</center>
			<br/> --%>
			  <c:if test="${not empty requestScope.ErrorMassage}">
<%--   <div class="alert alert-danger">
				<button class="close" type="button" data-dismiss="alert">×</button>
				<span class="entypo-attention"></span>
				<strong>Error!!</strong>
			  	${requestScope.ErrorMassage}
			  	</div> --%>
			  	
 <div class="alert alert-info">
<button class="close" type="button" data-dismiss="alert">×</button>
<span class="entypo-info-circled"></span>
<strong>Message:</strong>
  ${requestScope.ErrorMassage}
</div>
			  	
<!-- 			
   <div data-color="rgb(255, 255, 255)" data-color-format="hex"  data-colorpicker-guid="4">
     ${requestScope.ErrorMassage}
   </div> -->
  </c:if>
  
 


<%-- 						<center>                       
                        <span>::LIST OF NON-APPROVED STUDENTS OF 1ST MERIT::</span>                           
<!--                         <h2 class="text-green">
                        <span>Release Slip Receive Time is Over..!!!</span>
                            </h2> -->
                        </center> --%>
 
   <div class="row">                        <br/>
<%-- <c:if test="${not empty requestScope.nonApprovedStudentListOfMerit}"> --%>


                  <!--   <div class="col-lg-12"> -->
                        <!-- <div class="box"> -->

                            <!-- /.box-header -->
                            <form action="receiveNonApproveStudentOfMerit" method="post" id="receiveNonApproveStudentOfMeritForm" name="receiveNonApproveStudentOfMeritForm">
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
                                           <!--  <th>MERIT TYPE</th> -->
                                            <th>PIN NUMBER</th>
                                            <th>APPROVE</th>
                                            

                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                 
			
				        <c:forEach items="${requestScope.nonApprovedStudentListOfMerit}" var="nonApprovedStudent" varStatus="loop">
							<tr>
								<%-- <td>${nonApprovedStudent.applicationID}</td> --%>
								<td>${nonApprovedStudent.applicantName}
								<input type="hidden" value="${nonApprovedStudent.applicantName}" id="hidden_student_name_${loop.index+1}" name="hidden_student_name" >
								<input type="hidden" value="${nonApprovedStudent.sscRollNo}" id="hidden_ssc_roll_${loop.index+1}" name="hidden_ssc_roll" >								
								<input type="hidden" value="${nonApprovedStudent.pinNumber}" size="3" id="hidden_pinNumber_${loop.index+1}" name="hidden_pinNumber" maxlength="5">
								</td> 
	 							<td>${nonApprovedStudent.sscRollNo}</td>
								<td>${nonApprovedStudent.boardName}</td>
								<td>${nonApprovedStudent.sscPassingYear}</td>
								
								<td>${nonApprovedStudent.shiftName}</td>
								<td>${nonApprovedStudent.versionName}</td>
								<td>${nonApprovedStudent.groupName}</td>
								
								
								
								<%-- <td>
								<c:if test="${nonApprovedStudent.meritType=='1'}">
								1st Merit 
								</c:if>
								<c:if test="${nonApprovedStudent.meritType=='2'}">
								2nd Merit 
								</c:if>
								
								</td>
                                <td>
                               <span class="status-metro status-suspended title="Suspended">${nonApprovedStudent.admitStatus}</span>
                                </td> --%>
								<td>
								
								<c:if test="${nonApprovedStudent.assignedQuota=='GEN'}">
								General
								</c:if>
								<c:if test="${nonApprovedStudent.assignedQuota=='D_G'}">
								General
								</c:if>
								<c:if test="${nonApprovedStudent.assignedQuota=='E_G'}">
								General
								</c:if>
								<c:if test="${nonApprovedStudent.assignedQuota=='F_G'}">
								General
								</c:if>
								<c:if test="${nonApprovedStudent.assignedQuota=='S_G'}">
								General
								</c:if>
								
								
								<c:if test="${nonApprovedStudent.assignedQuota=='FQ'}">
								Freedom Fighter
								</c:if>
								
								<c:if test="${nonApprovedStudent.assignedQuota=='EQ'}">
								Education Quota
								</c:if>
								
							    <c:if test="${nonApprovedStudent.assignedQuota=='DQ'}">
								Division Quota
								</c:if>
								
								<c:if test="${nonApprovedStudent.assignedQuota=='ZQ'}">
								Zilla Quota
								</c:if>
								
								<c:if test="${nonApprovedStudent.assignedQuota=='SQ'}">
								Special Quota
								</c:if>
								
								<c:if test="${nonApprovedStudent.assignedQuota=='OWN'}">
								OWN Category
								</c:if>
								
								</td>
								
<%-- 								 <td>
								<c:if test="${nonApprovedStudent.meritType=='1'}">
								1st Merit List
								</c:if>
								<c:if test="${nonApprovedStudent.meritType=='2'}">
								1st Merit Migration[IN]
								</c:if>
								<c:if test="${nonApprovedStudent.meritType=='3'}">
								2nd Merit List
								</c:if>
								<c:if test="${nonApprovedStudent.meritType=='4'}">
								2nd Merit Migration[IN]
								</c:if>
								<c:if test="${nonApprovedStudent.meritType=='5'}">
								1st Release Slip
								</c:if>
								<c:if test="${nonApprovedStudent.meritType=='9'}">
								4th Phase
								</c:if>
								<c:if test="${nonApprovedStudent.meritType=='15'}">
								BTEB 2nd Phase
								</c:if>
								
								</td> --%>

	<td>
	<input type="text" size="3" id="pinNumber_${loop.index+1}" name="pinNumber" maxlength="5">
	</td>
	<td>
	<input type="checkbox" class="boSelect" id="${loop.index+1}" name="applicationID" value="${nonApprovedStudent.applicationID}#${nonApprovedStudent.meritType}#${nonApprovedStudent.shiftID}#${nonApprovedStudent.versionID}#${nonApprovedStudent.groupId}#${loop.index}">
	</td>
								

							</tr>		
						</c:forEach>
 

                                </table>
                               </div>
                                  
                            <br>
                                 <div style="width:140px;float: right;right-padding:0cm">
                                 	<input type="hidden" name="formAction" id="formAction" value="" />
                                  	<!-- <button class="btn bg-parpel" style="width:140px">Approve Special Quota</button> -->
                                  	<input type="button" style="width:140px;padding: 1px 12px;" class="btn btn-info" name="approve" value="Receive Admission" onclick="submitApproveForm()" />
                                <!-- 	<input type="button" class="btn bg-parpel" name="disApprove" value="Revoke Approval" onclick="submitApproveForm('disapprove')" /> -->
                                </div>
                               
                      <!--       <button class="btn bg-green">Approve Special Quota</button> -->
                            </form>

                            <form action="downloadnonApprovedApplicantCSV" method="post" id="downloadCSV" name="downloadCSV">
                            <div style="width:100px;float: left;right-padding:0cm">
                                  <button style="width:150px;padding: 1px 12px;" class="btn btn-info" onclick="downloadsqCSV()">Download CSV</button>
                                </div>
                            </form>
                           <div style="float: left; margin-left: 180px;">
                           		<font style="color: blue; font-size: 20px;">
                           			To cancel any applicant's admission, <a href="approvedStudentListOfMerit">Click Here</a>
                           		</font> 
                           </div> 
                            
  <%--                           </c:if> --%>
                            <!-- /.box-body -->
                        <!-- </div> -->
                        <!-- /.box -->
                   <!--  </div> -->
  <br/><hr> <br/>       

        <%-- <c:if test="${requestScope.successMessage!=''}"> --%>
        <c:if test="${not empty requestScope.successMessage}">
            <div class="alert alert-success">
				<button class="close" type="button" data-dismiss="alert">×</button>
				<span class="entypo-thumbs-up"></span>
				<strong>Successful!!</strong>
			  	${requestScope.successMessage}
			</div>
        </c:if>
       <%--  <c:if test="${requestScope.errorMessage!=''}"> --%>
        
        <c:if test="${not empty requestScope.errorMessage}">
            <div class="alert alert-danger">
				<button class="close" type="button" data-dismiss="alert">×</button>
				<span class="entypo-attention"></span>
				<strong>Error!!</strong>
			  	${requestScope.errorMessage}
			</div>
        </c:if>
         

			
			<c:if test="${not empty requestScope.showNonApproveStudentOfMeritList}">
			
			           <form action="receiveNonApproveStudentOfMerit" method="post" id="receiveNonApproveStudentOfMeritForm" name="receiveNonApproveStudentOfMeritForm">
                            <div class="box-body table-responsive">
                                <table style="width:1000px;font-size:12px;" border="1"   class="table table-bordered table-striped cf">
                                    <thead>
                                        <tr>
                                            <th>SL NO.</th>
                                            <th>APPLICATION ID</th>
                                            <th>NAME</th>
                                            <th>SSC ROLL</th>
                                            <th>SSC BOARD</th>
                                            <th>SSC YEAR</th> 
                                            <th>SHIFT</th>
                                            <th>VERSION</th>
                                            <th>GROUP</th>
                                         <!--    <th>MERIT</th>
                                            <th>STATUS</th> -->
                                            <th>QUOTA</th>
                                            <th>APPROVE</th>
                                            

                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                 
			
				        <c:forEach items="${requestScope.showNonApproveStudentOfMeritList}" var="nonApprovedStudent" varStatus="loop">
							<tr>
							    <td>${loop.index+1}</td>
								<td>${nonApprovedStudent.applicationID}</td>
								<td>${nonApprovedStudent.applicantName}</td> 
	 							<td>${nonApprovedStudent.sscRollNo}</td>
								<td>${nonApprovedStudent.boardName}</td>
								<td>${nonApprovedStudent.sscPassingYear}</td>
								
								<td>${nonApprovedStudent.shiftName}</td>
								<td>${nonApprovedStudent.versionName}</td>
								<td>${nonApprovedStudent.groupName}</td>
								
								
								
								<%-- <td>
								<c:if test="${nonApprovedStudent.meritType=='1'}">
								1st Merit 
								</c:if>
								<c:if test="${nonApprovedStudent.meritType=='2'}">
								2nd Merit 
								</c:if>
								
								</td>
                                <td>
                               <span class="status-metro status-suspended title="Suspended">${nonApprovedStudent.admitStatus}</span>
                                </td> --%>
								<td>
								<c:if test="${nonApprovedStudent.assignedQuota=='GEN'}">
								General
								</c:if>
								<c:if test="${nonApprovedStudent.assignedQuota=='D_G'}">
								General
								</c:if>
								<c:if test="${nonApprovedStudent.assignedQuota=='E_G'}">
								General
								</c:if>
								<c:if test="${nonApprovedStudent.assignedQuota=='F_G'}">
								General
								</c:if>
								<c:if test="${nonApprovedStudent.assignedQuota=='S_G'}">
								General
								</c:if>
								
								
								<c:if test="${nonApprovedStudent.assignedQuota=='FQ'}">
								Freedom Fighter
								</c:if>
								
								<c:if test="${nonApprovedStudent.assignedQuota=='EQ'}">
								Education Quota
								</c:if>
								
							    <c:if test="${nonApprovedStudent.assignedQuota=='DQ'}">
								Division Quota
								</c:if>
								
								<c:if test="${nonApprovedStudent.assignedQuota=='ZQ'}">
								Zilla Quota
								</c:if>
								
								<c:if test="${nonApprovedStudent.assignedQuota=='SQ'}">
								Special Quota
								</c:if>
								
								<c:if test="${nonApprovedStudent.assignedQuota=='OWN'}">
								OWN Category
								</c:if>
								
								</td>


	<td>
	<input type="checkbox" class="boSelect" checked="checked" name="applicationID" value="${nonApprovedStudent.applicationID}#${nonApprovedStudent.meritType}">
	</td>
								

							</tr>		
						</c:forEach>
 

                                </table>
                               </div>
                                  
                            
                                 <div style="width:140px;float: right;right-padding:0cm">
                                 	<input type="hidden" name="formAction" id="formAction" value="" />
                                  	<input type="button" style="width:180px;padding: 1px 12px;" class="btn btn-info" name="approve" value="Receive Admission" onclick="submitApproveForm()" />
                                <!-- 	<input type="button" class="btn bg-parpel" name="disApprove" value="Revoke Approval" onclick="submitApproveForm('disapprove')" /> -->
                                </div>
                               
                      <!--       <button class="btn bg-green">Approve Special Quota</button> -->
                            </form>
			
 </c:if>         
</div>
<!----body ------>	

</center>

</div>
		</div>
		<br>

	</div>


	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp" %>
</div>

  	<!-- Popup modal div start -->
    	<div id="modelGivePin" class="modal fade" data-backdrop="static" data-keyboard="false" role="dialog">
		  <div class="modal-dialog">
		
		    <!-- Modal content-->
		    <div class="modal-content" style="color: black;">
		      <div class="modal-header">
		         <button type="button" class="close" data-dismiss="modal">&times;</button> 
		        <h5 class="modal-title" ><font style="color: green;">Message:</font> Please give pin number. for:</h5>
		      </div>
		      <div class="modal-body">
              <div>
              <span id="modelGivePinSpan"></span></div>             
		      </div>
		      
		      <div class="modal-footer">
		        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		
		  </div>
		</div>
    	<!-- Popup modal div end -->
    	
    	  	<!-- Popup modal div start -->
    	<div id="modelPinMismatch" class="modal fade" data-backdrop="static" data-keyboard="false" role="dialog">
		  <div class="modal-dialog">
		
		    <!-- Modal content-->
		    <div class="modal-content" style="color: black;">
		      <div class="modal-header">
		         <button type="button" class="close" data-dismiss="modal">&times;</button> 
		      <h5 class="modal-title" ><font style="color: green;">Message:</font> Your given pin number doesn't match for:</h5>
		      </div>
		      <div class="modal-body">
              <div>
              <span id="modelPinMismatchSpan"></span>
              </div>
              
		      </div>
		      
		      <div class="modal-footer">
		        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		
		  </div>
		</div>
    	<!-- Popup modal div end -->
    	
    	  	<!-- Popup modal div start -->
    	<div id="modelNoSelection" class="modal fade" data-backdrop="static" data-keyboard="false" role="dialog">
		  <div class="modal-dialog">
		
		    <!-- Modal content-->
		    <div class="modal-content" style="color: black;">
		      <div class="modal-header">
		         <button type="button" class="close" data-dismiss="modal">&times;</button> 
		        <h4 class="modal-title" ><font style="color: green;">Message :</font></h4>
		      </div>		      <div class="modal-body">
              <div>Please select a check box to approve an applicant.</div>
              
		      </div>
		      
		      <div class="modal-footer">
		        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		
		  </div>
		</div>
    	<!-- Popup modal div end -->

  	<!-- Popup modal div start -->
    	<div id="myModal" class="modal fade" data-backdrop="static" data-keyboard="false" role="dialog">
		  <div class="modal-dialog">
		
		    <!-- Modal content-->
		    <div class="modal-content" style="color: black;">
		      <div class="modal-header">
		         <button type="button" class="close" data-dismiss="modal">&times;</button> 
		      <!--   <button type="button" id="signOut" class="btn btn-info" style="float: right;" onclick="Signout()" >Sign Out</button> -->
		        <h4 class="modal-title" >Admission Approval Message</h4>
		      </div>
		      <div class="modal-body">

		      </div>
		      
		      <div class="modal-footer">
<!-- 		      OTP: &nbsp;&nbsp;
		        	<input type="text" name="otp" id="otp" /> &nbsp;&nbsp;
		        	<input type="button" class="btn btn-danger" name="validateMobile" id="validateMobile" onclick="validateOtp()" value="Validate & Save" />
		        	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
		        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		
		  </div>
		</div>
    	<!-- Popup modal div end -->

<%--     <script type='text/javascript' src="/board/resources/js/jquery.min.js"></script>
    <!-- Main jQuery Plugins -->
    
    <script type='text/javascript' src="/board/resources/template/js/jquery.js"></script> --%>
	
   <script type="text/javascript">
   
/*     $(window).load(function(){
        $('#myModal').modal('show');
    }); */
	function submitApproveForm()
	{
	


	
	   var idSelector = function() { return this.id; };
       var checkboxID = $(":checkbox:checked").map(idSelector).get();
       myArr = Array.prototype.slice.apply( checkboxID );
//       alert(myArr.length);

	if(myArr.length>0)
	{
	
	for(i=0;i<myArr.length;i++)
	{
	var pin=$("#pinNumber_"+myArr[i]).val();
	var hidden_pin=$("#hidden_pinNumber_"+myArr[i]).val();
	var hidden_student_name=$("#hidden_student_name_"+myArr[i]).val();
	var hidden_ssc_roll=$("#hidden_ssc_roll_"+myArr[i]).val();
	
	
	//alert(hidden_pin);
	if(pin=='')
	{
	/* alert("Please give pin number for, Name:"+hidden_student_name+", S.S.C Roll :"+hidden_ssc_roll); */
	document.getElementById('modelGivePinSpan').innerHTML =" Name: "+hidden_student_name+",<br> S.S.C Roll :"+hidden_ssc_roll; 
	$('#modelGivePin').modal('show');
	return;
	}
	else if(pin!=hidden_pin)
	{
	//alert("Your given pin number doesn't match for, Name:"+hidden_student_name+", S.S.C Roll :"+hidden_ssc_roll+" .Please input correct pin number.");
	document.getElementById('modelPinMismatchSpan').innerHTML =" Name: "+hidden_student_name+",<br>  S.S.C Roll :"+hidden_ssc_roll; 
	$('#modelPinMismatch').modal('show');
	return;
	}
	
	//alert("Pin Number :"+pin);
	}
	document.getElementById("receiveNonApproveStudentOfMeritForm").submit();	  
	}
	
	else {
/* 	alert("Please select a check box to approve an applicant. "); */
	$('#modelNoSelection').modal('show');
	}
	   
	  
	//document.getElementById("receiveNonApproveStudentOfMeritForm").submit();
	
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
    
<%--     <script type='text/javascript' src="/board/resources/template/js/jquery.js"></script>
 --%>
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