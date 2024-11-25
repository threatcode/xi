<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %> --%>
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

<center>

<!---- body ----->		



  <div class="row">
                  <!--   <div class="col-lg-12"> -->
                        <!-- <div class="box"> -->

                            <!-- /.box-header -->
                            <form action="reApproveStudentOfMeritByBoard" method="post" id="reApproveStudentOfMeritForm" name="reApproveStudentOfMeritForm" onsubmit="return validateForm()" >
                          <input type="hidden" name="hiddenShiftID" id="hiddenShiftID" value="${requestScope.hiddenShiftID}" />
                          <input type="hidden" name="hiddenVersionID" id="hiddenVersionID" value="${requestScope.hiddenVersionID}" />
                          <input type="hidden" name="hiddenGroupID" id="hiddenGroupID" value="${requestScope.hiddenGroupID}" />
                          <input type="hidden" name="hiddenMerit" id="hiddenMerit" value="${requestScope.hiddenMerit}" />
                            
                            <div class="box-body table-responsive">
                                <table id="example1" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th>COLLEGE</th>
                                            <th>NAME</th>
                                            <th>SSC ROLL</th>
                                            <th>SSC BOARD</th>
                                            <th>SSC YEAR</th> 
                                            <th>SHIFT</th>
                                            <th>VERSION</th>
                                            <th>GROUP</th>
                                            <th>QUOTA</th>
                                           <th>MERIT TYPE</th>
                                            <th>APPROVE</th>
                                            

                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                 
			
				        <c:forEach items="${requestScope.admissionCancelledStudentListOfMerit}" var="cancelledStudent" varStatus="loop">
							<tr>
								
								
								<%-- <td>${cancelledStudent.applicationID}</td> --%>
								<%-- <td>${loop.index+1}</td>  --%>
								<td>${cancelledStudent.collegeName}</td>
								<td>${cancelledStudent.applicantName}</td> 
	 							<td>${cancelledStudent.sscRollNo}</td>
								<td>${cancelledStudent.boardName}</td>
								<td>${cancelledStudent.sscPassingYear}</td>
								
								<td>${cancelledStudent.shiftName}</td>
								<td>${cancelledStudent.versionName}</td>
								<td>${cancelledStudent.groupName}</td>
															
								
								<td>
								
								<c:if test="${cancelledStudent.assignedQuota=='GENERAL'}">
								General
								</c:if>
								
								<c:if test="${cancelledStudent.assignedQuota=='FREEDOM'}">
								Freedom Fighter Quota
								</c:if>
								
								<c:if test="${cancelledStudent.assignedQuota=='EDUCATION'}">
								Education Quota
								</c:if>
								
							    <c:if test="${cancelledStudent.assignedQuota=='DISTRICT'}">
								Division/District Quota
								</c:if>
								
								<c:if test="${cancelledStudent.assignedQuota=='FOREIGN'}">
								Expatriate Quota
								</c:if>
								
								<c:if test="${cancelledStudent.assignedQuota=='BKSP'}">
								BKSP Quota
								</c:if>
								
								
								<c:if test="${cancelledStudent.assignedQuota=='SPECIAL'}">
								Special Quota
								</c:if>
								
								<c:if test="${cancelledStudent.assignedQuota=='OWN'}">
								OWN Category
								</c:if>
								
								</td>
								
								
							  <td>
								<c:if test="${cancelledStudent.meritType=='1'}">
								Merit List
								</c:if>
								<c:if test="${cancelledStudent.meritType=='2'}">
								1st Waiting List
								</c:if>
								<c:if test="${cancelledStudent.meritType=='3'}">
								2nd Waiting List
								</c:if>
								<c:if test="${cancelledStudent.meritType=='4'}">
								Remaining Waiting List
								</c:if>
								<c:if test="${cancelledStudent.meritType=='5'}">
								Manual Admission
								</c:if>
	
								
								</td>


	<td>
	<input type="checkbox" class="boSelect" name="applicationID" value="${cancelledStudent.applicationID}#${cancelledStudent.meritType}#${cancelledStudent.shiftID}#${cancelledStudent.versionID}#${cancelledStudent.groupId}#${cancelledStudent.eiinCode}#${loop.index}#${cancelledStudent.assignedQuota}">
	</td> 
								

							</tr>		
						</c:forEach>
 

                                </table>
                               </div>
                                  
                            
                                 <div style="width:140px;float: right;right-padding:0cm">
                                 	<input type="hidden" name="formAction" id="formAction" value="" />
                                  	<br>
                                  	<input type="button" style="width:140px;padding: 1px 12px;" class="btn btn-danger buttonCancel" name="receive" value="Receive Admission" onclick="submitApproveForm()" />                                 	
        
                                </div>
                               
                      <!--       <button class="btn bg-green">Approve Special Quota</button> -->
                            </form>
                            
<%--                             <form action="downloadApprovedApplicantCSV" method="post" id="downloadCSV" name="downloadCSV">
                           <input type="hidden" name="hiddenShiftIDCSV" id="hiddenShiftIDCSV" value="${requestScope.hiddenShiftID}" />
                          <input type="hidden" name="hiddenVersionIDCSV" id="hiddenVersionIDCSV" value="${requestScope.hiddenVersionID}" />
                          <input type="hidden" name="hiddenGroupIDCSV" id="hiddenGroupIDCSV" value="${requestScope.hiddenGroupID}" />
                          <input type="hidden" name="hiddenMeritCSV" id="hiddenMeritCSV" value="${requestScope.hiddenMerit}" />
                            
	                            <div style="width:100px;float: left;right-padding:0cm">
	                            <br>
	                                  <button style="width:150px;padding: 1px 12px;" class="btn btn-success buttonCSV">Download CSV</button>
	                                  <!-- <button style="width:150px;padding: 1px 12px;" class="btn btn-info" onclick="downloadsqCSV()">Download CSV</button> -->
                                </div>
                            </form>--%>
                            <br><br> 
<%--                             <div style="float: left; margin-left: 15px;">
                            	<font style="color: red; font-size: 16px;">
                            	Cancel one <STRONG>"Admission"</STRONG> at a time.<br/>
                            	<strong>N.B.</strong> If you cancel an <strong>"Admission"</strong> once, you will not be able to <strong>"Receive"</strong> his/her admission again.
                            	</font>
                            </div> --%>
                            </div>
                            <!-- /.box-body -->
                        <!-- </div> -->
                        <!-- /.box -->
                   <!--  </div> -->
            

    

<!----body ------>	

</center>



  	<!-- Popup modal div start -->
    	<div id="modelWindow" class="modal fade" data-backdrop="static" data-keyboard="false" role="dialog">
		  <div class="modal-dialog">		
		    <!-- Modal content-->
		    <div class="modal-content" style="color: black;">
		      <div class="modal-header">
		         <button type="button" class="close" data-dismiss="modal">&times;</button> 
		         <h5 class="modal-title" ><font style="color: red;"> <span id="modelWindowHead"></span></font></h5>
		      </div>
		      <div class="modal-body">
              <div>
              <span id="modelWindowBody" style="display: block;height: 100px;overflow-y: scroll;"></span></div>             
		      </div>
		      
		      <div class="modal-footer">
		        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		
		  </div>
		</div>
    <!-- Popup modal div end -->
  	<!-- Popup modal div start -->
    	<div id="modelConfirm" class="modal fade" data-backdrop="static" data-keyboard="false" role="dialog">
		  <div class="modal-dialog">		
		    <!-- Modal content-->
		    <div class="modal-content" style="color: black;">
		      <div class="modal-header">
		         <button type="button" class="close" data-dismiss="modal">&times;</button> 
		         <h5 class="modal-title" ><font style="color: green;"> <span id="modelConfirmHead"></span></font></h5>
		      </div>
		      <div class="modal-body">
              <div>
              <span id="modelConfirmBody" style="display: block;height: 100px;overflow-y: scroll;"></span></div>             
		      </div>
		      
		      <div class="modal-footer">
		      	<button type="button" class="btn btn-danger" data-ok="modal" onclick="SubmitData();">Submit Data</button>
		        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		
		  </div>
		</div>
    <!-- Popup modal div end -->    	
  
	

             <!-- Main jQuery Plugins -->
<script type="text/javascript" >
	var myTable;
	$(document).ready(function() {
		myTable = $('#example1').dataTable();
		$('#example2').dataTable();
	} );
	
/* 		function submitCancelForm()
	{
	document.getElementById("cancelApproveStudentOfMeritForm").submit();
	}
	function validateForm() {
	    var x = confirm("Are you sure to \"Cancel Admission\" for these applicants?");
	    if (x == true) {
	        return true;
	    } else 
	    	return false;
	} */
</script> 

<script type="text/javascript">
   function SubmitData()
   {   		
		$('#modelConfirm').modal('toggle');
		$.ajax({
		 	url: 'reApproveStudentOfMeritByBoard',		
		 	type: 'POST',
		 	async: true,
			cache: false,
		 	data: {eiinCode :	"Hossain" ,dataString:dataString},
		 	success: function(msg) {
		 			var modelWindowHead = "Admission approval status of following applicants are given bellow.";
		 			var modelWindowBody = '';
		 			var deleteRows = new Array();
		 			for(var i=0;i<msg.received.length;i++)
		 			{
		 				modelWindowBody += msg.received[i].appname + '  :   ' + msg.received[i].message + '<br/>';
		 				if(msg.received[i].message=="Successfully approved.")
		 				{
		 					deleteRows.push(msg.received[i].rowIndex);		 					
		               	}		 				
		 			}
		 			deleteRows.sort(function(a, b){return b-a});
		 			for(var i=0;i<deleteRows.length;i++)
		 			{
		 				myTable.fnDeleteRow(deleteRows[i]);
		 			}
					document.getElementById('modelWindowHead').innerHTML = modelWindowHead;
					document.getElementById('modelWindowBody').innerHTML = modelWindowBody; 
	               	$('#modelWindow').modal('show');
		 	},
		 	error: function(e) {
		 	}
 		});   
   	 
   }
   var aPos;
    $('#example1 tr td').click(function () {
            aPos = myTable.fnGetPosition(this);
    });
	var dataString= '';
	function submitApproveForm()
	{
	
		var idSelector = function() { return this.id; };
		var checkboxID = $(":checkbox:checked").map(idSelector).get();
	    myArr = Array.prototype.slice.apply( checkboxID );
		if(myArr.length>0)
		{
		
			var names = $('td:eq(1)', myTable.fnGetNodes());

			var allCheckBox = new Array();
			$(myTable.fnGetNodes()).find(':checkbox').each(function () {
						        $this = $(this);
						        allCheckBox.push($this);
						    });
								    
	
			var modelConfirmHead = "Following applicant will be approved, are you sure? ";
			var modelWindowBody = '';
			var modelConfirmBody = '';
			dataString = '';
			for(var i = 0;i < allCheckBox.length; i++)
			{
				if(allCheckBox[i][0].checked)
				{
					//console.log(names[i].textContent + " " +allCheckBox[i][0].value + " " +allTextBox[i][0].value);
					
						modelConfirmBody += names[i].textContent + "<br/>"; 
						dataString += allCheckBox[i][0].value+'#'+myTrim(names[i].textContent)+'#'+myTable.fnGetPosition(names[i])[0]
									+ '###';
					
				}
			}
			if(myTrim(modelWindowBody)!='')
			{
						document.getElementById('modelWindowHead').innerHTML = modelWindowHead;
						document.getElementById('modelWindowBody').innerHTML = modelWindowBody; 
		               	$('#modelWindow').modal('show');
		               	return false;
			}
			else
			{
						document.getElementById('modelConfirmHead').innerHTML = modelConfirmHead;
						document.getElementById('modelConfirmBody').innerHTML = modelConfirmBody; 
		               	$('#modelConfirm').modal('show');
		               	return false;
			}
		}
		else 
		{
			document.getElementById('modelWindowHead').innerHTML = "Error Message :";
			document.getElementById('modelWindowBody').innerHTML = "Please select applicants from the list";
			$('#modelWindow').modal('show');
		}
	
	}
	
	
	function myTrim(x) {
    	return x.replace(/^\s+|\s+$/gm,'');
	}

</script>

<link rel="stylesheet" type="text/css" href="/board/resources/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/board/resources/css/dataTables.bootstrap.css" />


<script type="text/javascript" language="javascript" src="/board/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript" src="/board/resources/js/dataTables.bootstrap.js"></script>
<style>
.buttonCSV:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,255,0,0.2);
}
.buttonCancel:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(255,0,0,0.2);
}
</style>

