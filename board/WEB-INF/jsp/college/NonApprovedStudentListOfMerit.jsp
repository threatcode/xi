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

<c:if test="${not empty requestScope.ErrorMassage}">

			  	
 <div class="alert alert-info">
<button class="close" type="button" data-dismiss="alert">×</button>
<span class="entypo-info-circled"></span>
<strong>Message:</strong>
  ${requestScope.ErrorMassage}
</div>
			  	

  </c:if>

<hr> 
   <div class="row">                      

                            <form method="post" id="receiveNonApproveStudentOfMeritForm" name="receiveNonApproveStudentOfMeritForm">
                          <input type="hidden" name="hiddenShiftID" id="hiddenShiftID" value="${requestScope.hiddenShiftID}" />
                          <input type="hidden" name="hiddenVersionID" id="hiddenVersionID" value="${requestScope.hiddenVersionID}" />
                          <input type="hidden" name="hiddenGroupID" id="hiddenGroupID" value="${requestScope.hiddenGroupID}" />
                          
                            <div class="box-body table-responsive" style="width: 99%;">
                                <table id="example1" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <!-- <th>APPLICATION ID</th> -->
                                            <th width:>SL. NO</th>
                                            <th>NAME</th>
                                            <th>SSC ROLL</th>
                                            <th>SSC BOARD</th>
                                            <th>SSC YEAR</th> 
                                            <th>SHIFT</th>
                                            <th>VERSION</th>
                                            <th>GROUP</th>
                                            <th>QUOTA</th>
                                            <!-- <th>MERIT TYPE</th>  -->
                                            <th>PAYMENT</th>
                                            <th>APPROVE</th>
                                            

                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                 
			
				        <c:forEach items="${requestScope.nonApprovedStudentListOfMerit}" var="nonApprovedStudent" varStatus="loop">
							<tr>
								<%-- <td>${nonApprovedStudent.applicationID}</td> --%>
								<td>${loop.index+1}</td> 
								<td>${nonApprovedStudent.applicantName}</td> 
	 							<td>${nonApprovedStudent.sscRollNo}</td>
								<td>${nonApprovedStudent.boardName}</td>
								<td>${nonApprovedStudent.sscPassingYear}</td>
								
								<td>${nonApprovedStudent.shiftName}</td>
								<td>${nonApprovedStudent.versionName}</td>
								<td>${nonApprovedStudent.groupName}
								<input type="hidden" value="${nonApprovedStudent.applicantName}" id="hidden_student_name_${loop.index+1}" name="hidden_student_name" >
								<input type="hidden" value="${nonApprovedStudent.sscRollNo}" id="hidden_ssc_roll_${loop.index+1}" name="hidden_ssc_roll" >								
								<%-- <input type="hidden" value="${nonApprovedStudent.pinNumber}" size="3" id="hidden_pinNumber_${loop.index+1}" name="hidden_pinNumber" maxlength="5"> --%>
								</td>
								
								
								<td>
									${nonApprovedStudent.assignedQuota}
								
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
		<c:if test="${nonApprovedStudent.ispaid=='YES'}">
			<font color="green"><b>Confirmed</b></font>
		</c:if>
		<c:if test="${nonApprovedStudent.ispaid=='NO'}">
			<font color="red"><b>Not Confirmed</b></font>
		</c:if>
	</td>
	<td>
		<c:if test="${nonApprovedStudent.ispaid=='YES'}">
			<div style="display: block">
				<input type="checkbox" class="boSelect" id="${loop.index+1}" name="applicationID" value="${nonApprovedStudent.applicationID}#${nonApprovedStudent.meritType}#${nonApprovedStudent.shiftID}#${nonApprovedStudent.versionID}#${nonApprovedStudent.groupId}#${loop.index}">
			</div>
		</c:if>
		<c:if test="${nonApprovedStudent.ispaid=='NO'}">
			<div style="display: none">
				<input type="checkbox" class="boSelect" id="${loop.index+1}" name="applicationID" value="${nonApprovedStudent.applicationID}#${nonApprovedStudent.meritType}#${nonApprovedStudent.shiftID}#${nonApprovedStudent.versionID}#${nonApprovedStudent.groupId}#${loop.index}">
			</div>
		</c:if>
	</td>
								

							</tr>		
						</c:forEach>
 

                                </table>
                               </div>
                                  
                            <br>
                                 <div style="float: right;right-padding:0cm">
                                 	<input type="hidden" name="formAction" id="formAction" value="" />
                                  	<!-- <button class="btn bg-parpel" style="width:140px">Approve Special Quota</button> -->
                                  	<input type="button" style="font-size: 18px; font-weight: bold; padding: 12px 18px; margin-right: 15px;  box-shadow: 0px 0px 9px #888888;" class="btn btn-danger buttonCancel" name="approve" value="Receive Admission" onclick="submitApproveForm()" />
                                <!-- 	<input type="button" class="btn bg-parpel" name="disApprove" value="Revoke Approval" onclick="submitApproveForm('disapprove')" /> -->
                                </div>
                               
                      <!--       <button class="btn bg-green">Approve Special Quota</button> -->
                            </form>

                            <form action="downloadnonApprovedApplicantCSV" method="post" id="downloadCSV" name="downloadCSV">
                            
                          <input type="hidden" name="hiddenShiftIDCSV" id="hiddenShiftIDCSV" value="${requestScope.hiddenShiftID}" />
                          <input type="hidden" name="hiddenVersionIDCSV" id="hiddenVersionIDCSV" value="${requestScope.hiddenVersionID}" />
                          <input type="hidden" name="hiddenGroupIDCSV" id="hiddenGroupIDCSV" value="${requestScope.hiddenGroupID}" />
                            
                            <div style="float: left; right-padding:0cm">
								<!-- 
                                  <button style="font-size: 18px; font-weight: bold; padding: 12px 18px; margin-left: 15px;  box-shadow: 0px 0px 9px #888888;" class="btn btn-success buttonCSV" onclick="downloadsqCSV()">Download CSV</button>
								 -->                                  
                             </div>
                            </form>
                           <div style="float: left; margin-left: 180px;">
                           		<!-- 
                           		<font style="color: blue; font-size: 20px;">
                           			To cancel any applicant's admission, <a href="collegeSVGloadReceivedAdmissionHome">Click Here</a>
                           		</font>
                           		 --> 
                           </div> 

  <br/>     

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
         

			
        
</div>
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
              <span id="modelConfirmBody" style="display: block;height: 300px;overflow-y: scroll;"></span></div>             
		      </div>
		      
		      <div class="modal-footer">
		      	<button type="button" class="btn btn-danger" data-ok="modal" onclick="SubmitData();">Submit Data</button>
		        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		
		  </div>
		</div>
    <!-- Popup modal div end -->    	
  
	
   <script type="text/javascript">
   function SubmitData()
   {   		
		$('#modelConfirm').modal('toggle');
		$.ajax({
		 	url: 'receiveNonApproveStudentOfMerit',		
		 	type: 'POST',
		 	async: true,
			cache: false,
		 	data: {eiinCode:"Hossain" ,dataString:dataString},
		 	success: function(msg) {
		 	
		 	//var availableSeat=parseInt($("#availableSeat").val().trim());
		 	//var counter=parseInt(msg.counter);
		 	//var lastAvailableSeat=availableSeat-counter;
		 	//alert(lastAvailableSeat);
		 	        //$("#availableSeat").val(lastAvailableSeat);
		 			var modelWindowHead = "Approval status of following applicants are given bellow.";
		 			var modelWindowBody = '';
		 			var deleteRows = new Array();
		 			for(var i=0;i<msg.approved.length;i++)
		 			{
		 				modelWindowBody += msg.approved[i].appname + '  :   ' + msg.approved[i].message + '<br/>';
		 				if(msg.approved[i].message=="Successfully approved.")
		 				{
		 					deleteRows.push(msg.approved[i].rowIndex);		 					
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
			var roll_no = $('td:eq(2)', myTable.fnGetNodes());
			var names = $('td:eq(1)', myTable.fnGetNodes());
			var rolls = $('td:eq(2)', myTable.fnGetNodes());
			var boards = $('td:eq(3)', myTable.fnGetNodes());
			var years = $('td:eq(4)', myTable.fnGetNodes());
			var quota = $('td:eq(7)', myTable.fnGetNodes());
			/*
			var allTextBox = new Array();
			$(myTable.fnGetNodes()).find(':text').each(function () {
						        $this = $(this);
						        allTextBox.push($this);
						    });
			*/
			var allCheckBox = new Array();
			$(myTable.fnGetNodes()).find(':checkbox').each(function () {
						        $this = $(this);
						        allCheckBox.push($this);
						    });
								    
	
			var modelWindowHead = "Please give PIN number for the following applicants";
			var modelConfirmHead = "Following applicants will be approved, are you sure? ";
			var modelWindowBody = '';
			var modelConfirmBody = '';
			dataString = '';
			modelConfirmBody +='<table class="table table-bordered table-striped">';
			for(var i = 0;i < allCheckBox.length; i++)
			{
				if(allCheckBox[i][0].checked)
				{
						modelConfirmBody += '<tr>';
						modelConfirmBody += '<td>'+names[i].textContent+'</td>'; 
						modelConfirmBody += '<td>'+rolls[i].textContent+'</td>';
						modelConfirmBody += '<td>'+boards[i].textContent+'</td>';
						modelConfirmBody += '<td>'+years[i].textContent+'</td>';
						modelConfirmBody += '</tr>';
						dataString += allCheckBox[i][0].value+'#'+myTrim(names[i].textContent)+'#'+myTable.fnGetPosition(names[i])[0]
									+'#'+myTrim(quota[i].textContent) + '###';
									//+'#'+allTextBox[i][0].value.trim()+'#'+myTrim(quota[i].textContent) + '###';
						//console.log(myTable.fnGetPosition(names[i]));
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
			$('#modelWindow').modal('show');
		}
	
	}
	
	
	function myTrim(x) {
    	return x.replace(/^\s+|\s+$/gm,'');
	}


	function submitApproveForm1()
	{
	
/* 	var pin = $('td:eq(9)', myTable.fnGetNodes());
	alert(pin.length);
	var allCheckBox = $('td:eq(10)', myTable.fnGetNodes());
	alert(allCheckBox.length);
	
	for(var i = 0;i < allCheckBox.length; i++)
       	{
       		if(allCheckBox[i].checked)
       		{
        		
       		}
       	}
 */

	   var idSelector = function() { return this.id; };
       var checkboxID = $(":checkbox:checked").map(idSelector).get();
       myArr = Array.prototype.slice.apply( checkboxID );
       alert(myArr.length);

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
	

	}
  
	}
	
	else {

	$('#modelNoSelection').modal('show');
	}
	   
	
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
<script type="text/javascript" >
	var myTable;
	$(document).ready(function() {
		myTable = $('#example1').dataTable();
		$('#example2').dataTable();
	} );
</script> 

<link rel="stylesheet" type="text/css" href="/board/resources/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/board/resources/css/dataTables.bootstrap.css" />


<script type="text/javascript" language="javascript" src="/board/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript" src="/board/resources/js/dataTables.bootstrap.js"></script>

<%--     <script src="/board/resources/template/js/datatables/jquery.dataTables.js" type="text/javascript"></script>
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
    </script> --%>
    <style>
.buttonCancel:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(255,0,0,0.2);
}
.buttonCSV:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,255,0,0.2);
}
</style>
