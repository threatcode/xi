<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %> --%>
<script type="text/javascript" src="resources/js/printThis.js"></script>


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
                            <form action="cancelApproveStudentOfMerit" method="post" id="cancelApproveStudentOfMeritForm" name="cancelApproveStudentOfMeritForm" onsubmit="return validateForm()" >
                          <input type="hidden" name="hiddenShiftID" id="hiddenShiftID" value="${requestScope.hiddenShiftID}" />
                          <input type="hidden" name="hiddenVersionID" id="hiddenVersionID" value="${requestScope.hiddenVersionID}" />
                          <input type="hidden" name="hiddenGroupID" id="hiddenGroupID" value="${requestScope.hiddenGroupID}" />
                          <input type="hidden" name="hiddenMerit" id="hiddenMerit" value="${requestScope.hiddenMerit}" />
                            
                            <div class="box-body table-responsive" style="width: 99%;">
                                <table id="example1" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                             <th>SL. NO</th>
                                            <th>NAME</th>
                                            <th>SSC ROLL</th>
                                            <th>SSC BOARD</th>
                                            <th>SSC YEAR</th> 
                                            <th>SHIFT</th>
                                            <th>VERSION</th>
                                            <th>GROUP</th>
                                            <th>QUOTA</th>
                                            <th>MERIT TYPE</th>
                                            <!-- <th>CANCEL</th> -->
                                            

                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                 
			
				        <c:forEach items="${requestScope.approvedStudentListOfMerit}" var="approvedStudent" varStatus="loop">
							<tr>
								
								
								<%-- <td>${approvedStudent.applicationID}</td> --%>
								<td>${loop.index+1}</td> 
								<td>${approvedStudent.applicantName}</td> 
	 							<td>${approvedStudent.sscRollNo}</td>
								<td>${approvedStudent.boardName}</td>
								<td>${approvedStudent.sscPassingYear}</td>
								
								<td>${approvedStudent.shiftName}</td>
								<td>${approvedStudent.versionName}</td>
								<td>${approvedStudent.groupName}</td>
															
								
								<td>${approvedStudent.assignedQuota}

								</td>
								
								
							  <td>
								<c:if test="${approvedStudent.meritType=='1'}">
								First Merit List
								</c:if>
								<c:if test="${approvedStudent.meritType=='2'}">
								Second Merit List
								</c:if>
								<c:if test="${approvedStudent.meritType=='3'}">
								Third Merit List
								</c:if>
								<c:if test="${approvedStudent.meritType=='4'}">
								Fourth Merit List
								</c:if>
								<c:if test="${approvedStudent.meritType=='5'}">
								Fifth Merit List
								</c:if>
								<c:if test="${approvedStudent.meritType=='6'}">
								Sixth Merit List
								</c:if>
								
								</td>

 
<%-- 	<td>
	<input type="checkbox" class="boSelect" name="applicationID" value="${approvedStudent.applicationID}#${approvedStudent.meritType}#${approvedStudent.shiftID}#${approvedStudent.versionID}#${approvedStudent.groupId}#${loop.index}#${approvedStudent.assignedQuota}"  onclick="submitApproveForm()">
	</td> --%> 
				

							</tr>		
						</c:forEach>
 

                                </table>
                               </div>
                                  
                            
                                 <div style="float: right;right-padding:0cm">
                                 	<input type="hidden" name="formAction" id="formAction" value="" />
                                  	<br>
                                  	 <!-- 
                                  	<input type="button" style="font-size: 18px; font-weight: bold; padding: 12px 18px; margin-right: 15px;  box-shadow: 0px 0px 9px #888888;" class="btn btn-danger buttonCancel" name="cancel" value="Cancel Admission" onclick="submitApproveForm()" />
                                  	 -->
                                </div>
                               
                      <!--       <button class="btn bg-green">Approve Special Quota</button> -->
                            </form>
                            
                            <form action="downloadApprovedApplicantCSV" method="post" id="downloadCSV" name="downloadCSV">
                           <input type="hidden" name="hiddenShiftIDCSV" id="hiddenShiftIDCSV" value="${requestScope.hiddenShiftID}" />
                          <input type="hidden" name="hiddenVersionIDCSV" id="hiddenVersionIDCSV" value="${requestScope.hiddenVersionID}" />
                          <input type="hidden" name="hiddenGroupIDCSV" id="hiddenGroupIDCSV" value="${requestScope.hiddenGroupID}" />
                          <input type="hidden" name="hiddenMeritCSV" id="hiddenMeritCSV" value="${requestScope.hiddenMerit}" />
                            
	                            <div style="float: left;right-padding:0cm">
	                            <br>
	                                  <button style="font-size: 18px; font-weight: bold; padding: 12px 18px; margin-left: 15px;  box-shadow: 0px 0px 9px #888888;" class="btn btn-success buttonCSV">Download CSV</button>
	                                  <!-- <button style="width:150px;padding: 1px 12px;" class="btn btn-info" onclick="downloadsqCSV()">Download CSV</button> -->
                                </div>
                            </form>
                            <br><br>
                            <div style="float: left; margin-left: 15px;">
                            <!-- 
                            	<font style="color: red; font-size: 16px;">
                            	Cancel one <STRONG>"Admission"</STRONG> at a time.<br/>
                            	<strong>N.B.</strong> If you cancel an <strong>"Admission"</strong> once, you will not be able to <strong>"Receive"</strong> his/her admission again.
                            	</font>
							 -->
                            </div>
                            </div>
                            <!-- /.box-body -->
                        <!-- </div> -->
                        <!-- /.box -->
                   <!--  </div> -->
            

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
              <span id="modelWindowBody" style="display: block;height: 300px;overflow-y: scroll;"></span></div>             
		      </div>
		      
		      <div class="modal-footer">
		        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>&nbsp;&nbsp;&nbsp;
		        <button class="btn btn-danger" onclick="myPrintFunction()">Print this page</button>
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
              <span id="modelConfirmBody" style="display: block;height: 300px;overflow-y: scroll;"></span></div>             
		      </div>
		      
		      <div class="modal-footer">
		      	<button type="button" class="btn btn-danger" data-ok="modal" onclick="SubmitData();">Submit Data</button>
		        <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="closeModal();">Close</button>
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
		 	url: 'cancelApproveStudentOfMerit',		
		 	type: 'POST',
		 	async: true,
			cache: false,
		 	data: {eiinCode :	"Hossain" ,dataString:dataString},
		 	success: function(msg) {
		 			var modelWindowHead = "Admission Cancel status of following applicants are given bellow.";
		 			var modelWindowBody = '';
		 			var deleteRows = new Array();
		 			for(var i=0;i<msg.cancelled.length;i++)
		 			{
		 				/*
		 				modelWindowBody += msg.cancelled[i].appname + '  :   ' + msg.cancelled[i].message + '<br/>';
		 				if(msg.cancelled[i].message=="Successfully cancelled.")
		 				{
		 					deleteRows.push(msg.cancelled[i].rowIndex);		 					
		               	}
		               	*/
		               	modelWindowBody += 'Name : <b>' + msg.cancelled[i].appname + ' (Roll:' + msg.cancelled[i].roll +')</b><br/>';
		               	modelWindowBody += '<table class="table table-bordered table-striped"><tr><td>College Name<td>EIIN<td>Shift<td>Version<td>Group<td>Quota';
		               	modelWindowBody += '<tr><td><b>' + msg.cancelled[i].cname + '</b><td><b>' + msg.cancelled[i].eiin + '</b>';
		               	modelWindowBody += '<td><b>' + msg.cancelled[i].shift + '</b><td><b>' + msg.cancelled[i].version + '</b>';
		               	modelWindowBody += '<td><b>' + msg.cancelled[i].group + '</b><td><b>' + msg.cancelled[i].quota + '</b>';
		               	modelWindowBody += '</table><br>';
		               	modelWindowBody += '<font color="red" size="5">' + msg.cancelled[i].message+ '</font>';
		               	
		 				if(msg.cancelled[i].message=="Successfully cancelled.")
		 				{
		 					deleteRows.push(msg.cancelled[i].rowIndex);		 					
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
			var rolls = $('td:eq(2)', myTable.fnGetNodes());
			var boards = $('td:eq(3)', myTable.fnGetNodes());
			var years = $('td:eq(4)', myTable.fnGetNodes());
			var shift_name = $('td:eq(5)', myTable.fnGetNodes());
			var version_name = $('td:eq(6)', myTable.fnGetNodes());
			var group_name = $('td:eq(7)', myTable.fnGetNodes());

			var allCheckBox = new Array();
			$(myTable.fnGetNodes()).find(':checkbox').each(function () {
						        $this = $(this);
						        allCheckBox.push($this);
						    });
								    
	
			var modelConfirmHead = "Following applicants will be cancelled, are you sure? ";
			var modelWindowBody = '';
			var modelConfirmBody = '';
			dataString = '';
			modelConfirmBody +='<table class="table table-bordered table-striped">';
			for(var i = 0;i < allCheckBox.length; i++)
			{
				if(allCheckBox[i][0].checked)
				{
					//console.log(names[i].textContent + " " +allCheckBox[i][0].value + " " +allTextBox[i][0].value);
					
						modelConfirmBody += '<tr>';
						modelConfirmBody += '<td>'+names[i].textContent+'</td>'; 
						modelConfirmBody += '<td>'+rolls[i].textContent+'</td>';
						modelConfirmBody += '<td>'+boards[i].textContent+'</td>';
						modelConfirmBody += '<td>'+years[i].textContent+'</td>';
						modelConfirmBody += '</tr>';
						dataString += allCheckBox[i][0].value+'#'+myTrim(names[i].textContent)+'#'+myTable.fnGetPosition(names[i])[0]
									+'#'+myTrim(shift_name[i].textContent)+'#'+myTrim(version_name[i].textContent)+'#'+myTrim(group_name[i].textContent)
									+ '###';
					
				}
			}
			modelConfirmBody +='</table>';
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
			//$('#modelWindow').modal('show');
		}
	
	}
	function closeModal()
	{
		$('#example1 tbody input[type="checkbox"]').prop('checked', false);
	}
	
	function myTrim(x) {
    	return x.replace(/^\s+|\s+$/gm,'');
	}
	function myPrintFunction() {
		$("#modelWindow").printThis({ 
		    debug: false,              
		    importCSS: true,             
		    importStyle: true,         
		    printContainer: true,       
		    pageTitle: "Admission Cancel Status",             
		    removeInline: false,        
		    printDelay: 333,            
		    header: null,             
		    formValues: true          
		}); 	
    	//window.print();
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

