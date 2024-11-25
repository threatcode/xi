<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>
       <c:if test="${not empty requestScope.successMessage}">
			<script type="text/javascript">
				alert('${requestScope.successMessage}');
			</script>       
        </c:if>

<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2> Special Quota Applicants:</h2>
			<div class="sidebar_section_text">
<center>
 <span>Special Quota Applicants:</span>
<br/><br/>
<!---- body ----->		
                            <form action="grantSpecialQuota" method="post" id="spesialQuotaGrantForm" name="spesialQuotaGrantForm">
                            <div class="box-body table-responsive">
                                <table id="example1" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <!-- <th>APPLICATION ID</th> -->
                                            <th>APPLICANT NAME</th>
                                            <th>FATHER NAME</th>
                                            <th>SSC ROLL NO</th>
                                            <th>PASSSING YEAR</th>
                                            <th>BOARD NAME</th>
                                            <th>STATUS</th>
                                            <th>APPROVE/ REVOKE</th>

                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                 
			
				        <c:forEach items="${requestScope.specialQuotaStudentList}" var="specialQuotaStudent">
							<tr>
								<%-- <td>${specialQuotaStudent.applicationID}</td> --%>
								<td>${specialQuotaStudent.applicantName}</td>
								<td>${specialQuotaStudent.fatherName}</td>
								<td>${specialQuotaStudent.sscRollNo}</td>
								<td>${specialQuotaStudent.sscPassingYear}</td>
								<td>${specialQuotaStudent.boardName}</td>
<td>
<c:if test="${specialQuotaStudent.specialQuotaGrant=='Y'}">
Approved
</c:if>

<c:if test="${specialQuotaStudent.specialQuotaGrant=='N'}">
Pending
</c:if>
</td>

	<td>
	<c:if test="${specialQuotaStudent.specialQuotaGrant=='N'}">
	<input type="checkbox" class="boSelect" name="applicationID" value="${specialQuotaStudent.applicationID}">
	</c:if>
	
	<c:if test="${specialQuotaStudent.specialQuotaGrant=='Y'}">
	<input type="checkbox" class="boSelect" name="applicationID" value="${specialQuotaStudent.applicationID}">
	</c:if>
	</td>
								

							</tr>		
						</c:forEach>
 

                                </table>
                               
                                  
                            
                                 <div style="width:280px;float: right;padding-top:10px;right-padding:0cm">
                                 	<input type="hidden" name="formAction" id="formAction" value="" />
                                  	<!-- <button class="btn bg-parpel" style="width:140px">Approve Special Quota</button> -->
                                  	<div>
                                    <input style="width:180px;padding: 1px 12px;" class="btn btn-info" name="approve" value="Approve Special Quota" onclick="submitApproveForm('approve')" />
                                  	</div>
                                  	&nbsp;
                                  	<div>
                                    <input type="button" style="width:180px;padding: 1px 12px;" class="btn btn-danger" name="disApprove" value="Revoke Approval" onclick="submitApproveForm('disapprove')" />
                                  	</div>


                                </div>
                               
                      <!--       <button class="btn bg-green">Approve Special Quota</button> -->
                            </form>
                            
                            <form action="downloadsqCSV" method="post" id="downloadCSV" name="downloadCSV">
                            
                            <div style="width:100px;float: left;right-padding:0cm">
                                  <button style="width:180px;padding: 1px 12px;" class="btn btn-info" onclick="downloadsqCSV()">Download CSV</button>
                                </div>
                            </form>
                            
                            </div>
                            <!-- /.box-body -->
                        <!-- </div> -->
                        <!-- /.box -->
                   <!--  </div> -->
            
<hr>
       <%--  <c:if test="${requestScope.successMessage!=''}"> --%>
       <c:if test="${not empty requestScope.successMessage}">
            <div class="alert alert-success">
				<button class="close" type="button" data-dismiss="alert">×</button>
				<span class="entypo-thumbs-up"></span>
				<strong>Successful!!</strong>
			  	${requestScope.successMessage}
			</div>
        </c:if>
        <%--  <c:if test="${requestScope.errorMessage!=''}"> --%>
<%--         <c:if test="${not empty requestScope.errorMessage}">
            <div class="alert alert-danger">
				<button class="close" type="button" data-dismiss="alert">×</button>
				<span class="entypo-attention"></span>
				<strong>Error!!</strong>
			  	${requestScope.errorMessage}
			</div>
        </c:if>  --%>
         
           <div class="row">
                    <div class="col-lg-12">
                        <div class="box">	

            <center>
                <h4 class="text-red">
                        <span>::: Add NEW SQ STUDENT :::</span>
                </h4>

			<table width="90%" border="0" style="border: none;" id="ssc_input_table">
				<!-- <tr >
					<td>APPLICAION ID:</td>
					<td><input type="text" id="applicationID" name="applicationID" style="width:150px;" maxlength="15"   required/></td>
				</tr > -->
				<tr>
	  	  		<td width="25%" align="left" style="font-weight: bold;border: none;">Roll No.</td>
	  	  		<td width="25%" align="left" style="font-weight: bold;border: none;">Board</td>
	  	  		<td width="25%" align="left" style="font-weight: bold;border: none;">Passing Year</td>
	  	  		<td width="25%" align="left" style="font-weight: bold;border: none;">Reg No.</td> 
	  	  		</tr>
	  	  		
	  	  		<tr>
	  	  		<td align="left" style="border: none;"><input type="text" id="ssc_roll" name="ssc_roll" style="width: 135px;text-align: center;" maxlength="15"   required/></td>
	  	  		<td align="left" style="border: none;">
	  	  			<select style="width:120px;" id="ssc_board" name="ssc_board" onchange="reloadYear(this.value)" style="width: 135px;">
				     			     	<option value="">Select Board</option>
				    <option value="15">Barishal</option>
					<option value="20">BOU</option>
					<option value="14">Chattogram</option>
					<option value="11">Cumilla</option>
					<option value="10" selected="selected">Dhaka</option>
					<option value="17">Dinajpur</option>
					<option value="13">Jashore</option>
					<option value="18">Madrasah</option>
					<option value="21">Mymensingh</option>
					<option value="12">Rajshahi</option>
					<option value="16">Sylhet</option>
					<option value="19">TEC</option>

				   </select>
	  	  		</td>
	  	  		<td align="left" style="border: none;">
	  	  			<select style="width:135px;" id="ssc_year" name="ssc_year" style="width: 135px;">
				     <option value="">Select Year</option>
				     <option value="2020">2020</option>
				     <option value="2019">2019</option>
				     <option value="2018">2018</option>
				     <!--
				     <option value="2017">2017</option>				      
				     <option value="2016">2016</option>
				     <option value="2015">2015</option>
				     <option value="2014">2014</option>
				     <option value="2013">2013</option>
				     <option value="2012">2012</option>
				     <option value="2011">2011</option>
				     <option value="2010">2010</option>
				     <option value="2009">2009</option>
				     <option value="2008">2008</option>
				      -->	
				    </select>
	  	  		</td>
	  	  		<td align="left" style="border: none;"><input type="text" id="ssc_reg" name="ssc_reg" style="width: 135px;text-align: center;" maxlength="10" required/></td>
	  	  		
	  	  		
	  	  	</tr>


				<tr>
				<td>&nbsp</td>
				<td>&nbsp</td>
				<td>&nbsp</td>
				<td>&nbsp</td>
				
				</tr>
                <tr >
                  <td colspan="2" align="left">
<!--                     <form  action="uploadXLSapplicant" id="FormXLSapplicant" method="post" enctype="multipart/form-data">
				     <input type="file" name="myFile" id="myFile" />
				     <input type="submit" style="padding: 1px 12px;" class="btn btn-info" value="Upload XLS" />
                       <label><a href="/board/resources/SQ upload template.xlsx">XLS Template</a></label>
				    </form>  -->
                  </td>
                  
                  <td></td>
                  <td align="left">
                  <input type="button" onclick="searchsqstudent1()" name="searchsqstudent" value="SEARCH" style="width:100px;padding: 1px 12px;" class="btn btn-info" />
                  
                  </td>
				</tr>
			</table>

			</center>

              </div>
             </div>
          </div>
<br/>
<hr>



  <c:if test="${not empty requestScope.ErrorMassage}">
  <div class="alert alert-danger">
				<button class="close" type="button" data-dismiss="alert">×</button>
				<span class="entypo-attention"></span>
				<strong>Error!!</strong>
			  	${requestScope.ErrorMassage}
			  	</div>
<!-- 			
   <div data-color="rgb(255, 255, 255)" data-color-format="hex"  data-colorpicker-guid="4">
     ${requestScope.ErrorMassage}
   </div> -->
  </c:if>
<div id="showsqstudentInfo">
   
</div>

<c:if test="${not empty requestScope.newspecialQuotaStudentinfoList}">
<div id="xlssqstudentInfo">
 
   <form action="approveSpecialQuotaIndividualy" method="post" id="spesialQuotaGrantForm" name="spesialQuotaGrantForm">
                            <div class="box-body table-responsive">
                                <table id="example2" class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>APPLICATION ID</th>
                                            <th>APPLICANT NAME</th>
                                            <th>FATHER NAME</th>
                                            <th>SSC ROLL NO</th>
                                            <th>PASSSING YEAr</th>
                                            <th>BOARD NAME</th>
                                            <th>APPROVE</th>

                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                 
			
				        <c:forEach items="${requestScope.newspecialQuotaStudentinfoList}" var="newspecialQuotaStudentinfo">
							<tr>
								<td>${newspecialQuotaStudentinfo.applicationID}</td>
								<td>${newspecialQuotaStudentinfo.applicantName}</td>
								<td>${newspecialQuotaStudentinfo.fatherName}</td>
								
								<td>${newspecialQuotaStudentinfo.sscRollNo}</td>
								<td>${newspecialQuotaStudentinfo.sscPassingYear}</td>
								<td>${newspecialQuotaStudentinfo.boardName}</td>

								<td>
								 <c:if test="${newspecialQuotaStudentinfo.specialQuotaGrant=='N'}">
								<input type="checkbox" class="boSelect" name="applicationID" value="${newspecialQuotaStudentinfo.applicationID}#${newspecialQuotaStudentinfo.eiinCode}">
								</c:if>
								
								<c:if test="${newspecialQuotaStudentinfo.specialQuotaGrant=='Y'}">
								<input type="checkbox" class="boSelect"  disabled="disabled" name="applicationID" value="${newspecialQuotaStudentinfo.applicationID}#${newspecialQuotaStudentinfo.eiinCode}">
								</c:if>
								</td>
								

							</tr>		
						</c:forEach>
 

                                </table>
                                
                            </div>
                            <div style="width:100px;float: right">
                            
                                  <button style="width:100px;padding: 1px 12px;" class="btn btn-info">Approve</button>
                                  
                                
                            </div>
                            </form>
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

  
   <script type="text/javascript">
   if (!Array.prototype.indexOf) {
	    Array.prototype.indexOf = function(val) {
	        return jQuery.inArray(val, this);
	    };
	}
	
	function SubmitData()
    {
    	var allCheckBox = $('input', myTable.fnGetNodes());   	
    	var approvetd = $('td:eq(5)', myTable.fnGetNodes());	
		$('#modelConfirm').modal('toggle');
		 $.ajax({
		 	url: 'grantSpecialQuota',		
		 	type: 'POST',
		 	data: {applicationID:appid, grant:approvalStatus},
		 	success: function(data) {
		 		if(data.result=="yes") 
		 		{		 			
			       	for(i = 0;i < allCheckBox.length; i++)
			       	{
			       		if(allCheckBox[i].checked)
			       		{
			        		allCheckBox[i].checked = false;
			        		//allCheckBox[i].disabled = true;
			        		if(approvalStatus=='approve')
			        			approvetd[i].innerHTML ='Approved';
			        		else
			        			approvetd[i].innerHTML ='Pending';
			       		}
			       	}
					alert("Approval of applicants has been submitted successfully \n\n You can continue to approve/ revoke.");
      					return false;
		 		}
		 		else
		 		{
					alert("There is network problem, please try again!");
      					return false;
		 		}
		 	},
		 	error: function(e) {
		 	}
	 		});
	}
	
	var appid = '';
	var approvalStatus = '';
   
	function submitApproveForm(status){
		approvalStatus = status;
   		var allCheckBoxC = $('input:checked', myTable.fnGetNodes());
       	if(allCheckBoxC.length==0)
       	{
       		//alert("Please select a check box to approve or revoke an applicant. ");
			document.getElementById('modelWindowHead').innerHTML = "Error Message :";
			document.getElementById('modelWindowBody').innerHTML = "Please select a check box to approve or revoke an applicant.";
			$('#modelWindow').modal('show');
       		return false;
       	}
	
		var names = $('td:eq(0)', myTable.fnGetNodes());
		var approvetd = $('td:eq(5)', myTable.fnGetNodes());
		var allCheckBox = $('input', myTable.fnGetNodes());
       	for(var i = 0;i < approvetd.length; i++)
       	{
       		if(allCheckBox[i].checked)
       		{
	       		if(status=='approve' && approvetd[i].innerHTML.indexOf('Approved')>-1)
	       		{
					document.getElementById('modelWindowHead').innerHTML = "Error Message :";
					document.getElementById('modelWindowBody').innerHTML = "You have selected already approved applicant";
					$('#modelWindow').modal('show');
		       		return false;
	       		}
	       		if(status!='approve' && approvetd[i].innerHTML.indexOf('Pending')>-1)
	       		{
					document.getElementById('modelWindowHead').innerHTML = "Error Message :";
					document.getElementById('modelWindowBody').innerHTML = "You have selected not approved applicant";
					$('#modelWindow').modal('show');
		       		return false;
	       		}
       		}
       	}
		appid = '';
       	for(var i = 0;i < allCheckBox.length; i++)
       	{
       		if(allCheckBox[i].checked)
       		{
        		appid = appid + allCheckBox[i].value + '###';
       		}
       	}

		var modelConfirmBody = "";
		if(status=='approve')
			modelConfirmBody = "Following applicants will be approved, are you sure? <br>";
		else
			modelConfirmBody = "Following applicants will be revoked, are you sure? <br>";
       	for(var i = 0;i < allCheckBox.length; i++)
       	{
       		if(allCheckBox[i].checked)
       		{
        		modelConfirmBody += '    ' + names[i].innerHTML + '<br>';
       		}
       	}		
       	document.getElementById('modelConfirmHead').innerHTML = "Submit Message !!";
		document.getElementById('modelConfirmBody').innerHTML = modelConfirmBody; 
       	$('#modelConfirm').modal('show');
       	return false;
      	
       	
   		
       	
       	
       	


/*
		document.getElementById("formAction").value = status;
		if(status == "disapprove"){
			if(confirm("Are you sure want to revoke approval for these applicants")){
				document.getElementById("spesialQuotaGrantForm").submit();
			}
		} 
		else {
			if(confirm("Are you sure want to approve approval for these applicants")){
				document.getElementById("spesialQuotaGrantForm").submit();
			}
		}
*/		
	}

      function searchsqstudent() {

       	  if(document.getElementById("applicationID").value=="")
        {
           alert("Please Give Application ID of student");
           return;
        }

       $('#showsqstudentInfo').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "shownewsqstudent",
            dataType: 'text',
            async   : false,
            data    : {

                applicationID: $("#applicationID").val()
            }
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
     //End of fetchApplicationInformation
     
     
     function searchsqstudent1() {

       	  if(document.getElementById("ssc_roll").value==""||document.getElementById("ssc_board").value==""||document.getElementById("ssc_year").value==""||document.getElementById("ssc_reg").value=="")
        {
          // alert("Please Give Application ID of student");
           alert("Please Fill All Information");
           return;
        }

       $('#showsqstudentInfo').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "shownewsqstudent",
            dataType: 'text',
            async   : false,
            data    : {

                ssc_roll: $("#ssc_roll").val(),ssc_board: $("#ssc_board").val(),ssc_year: $("#ssc_year").val(),ssc_reg: $("#ssc_reg").val()
            }
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
        
var arrYear = [2020,2019,2018];
var arrYearBOU = [2019,2018,2017,2016];
var arrBTEByear = [2020,2019,2018];
        
        function reloadYear(board_id){ 
//	if(board_id==20)
//		$('#ssc_roll').attr("maxlength","15")
//	else
//		$('#ssc_roll').attr("maxlength","6")
	$('#ssc_year').children('option').remove();
	$('#ssc_year').append($("<option></option>")
        		   .attr("value","")
        		   .text("Select Year")); 
	var year=[]

    if(board_id==19)
    	year=arrBTEByear;
    else if(board_id==20)
    	year=arrYearBOU;
    else
    	year=arrYear;
	 
	 
	 for (var i=0; i<year.length; i++) {
	 	if(board_id==20)
	 	{
		 	if(year[i]==2018)
				$('#ssc_year')
		         	.append($("<option selected></option>")
		 			.attr("value",year[i])
		 			.text(year[i]));
		 	else
				$('#ssc_year')
		         	.append($("<option></option>")
		 			.attr("value",year[i])
		 			.text(year[i]));
	 	}
	 	else
	 	{
		 	if(year[i]==2020)
				$('#ssc_year')
		         	.append($("<option selected></option>")
		 			.attr("value",year[i])
		 			.text(year[i]));
		 	else
				$('#ssc_year')
		         	.append($("<option></option>")
		 			.attr("value",year[i])
		 			.text(year[i]));
	 	}
	}

}

</script>
         
  
<script type="text/javascript" >
	var myTable;
	$(document).ready(function() {
		myTable = $('#example1').dataTable();
		$('#example2').dataTable();
	} );
</script>        
                


    <!-- Main jQuery Plugins -->
    
<link rel="stylesheet" type="text/css" href="/board/resources/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/board/resources/css/dataTables.bootstrap.css" />

<%-- <script type="text/javascript" src="/resources_SVG/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources_SVG/js/defiant-latest.min.js"></script>
<script type="text/javascript" src="/resources_SVG/js/common.js"></script> --%>
<!-- <script type="text/javascript" language="javascript" src="/board/resources/js/jquery-1.11.1.min.js"></script> -->
<script type="text/javascript" language="javascript" src="/board/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript" src="/board/resources/js/dataTables.bootstrap.js"></script>


<%--     <script src="/board/resources/template/js/datatables/jquery.dataTables.js" type="text/javascript"></script>
    <script src="/board/resources/template/js/datatables/dataTables.bootstrap.js" type="text/javascript"></script> --%>

<%--     <script src="/board/resources/template/js/footable/js/footable.js?v=2-0-1" type="text/javascript"></script>
    <script src="/board/resources/template/js/footable/js/footable.sort.js?v=2-0-1" type="text/javascript"></script>
    <script src="/board/resources/template/js/footable/js/footable.filter.js?v=2-0-1" type="text/javascript"></script>
    <script src="/board/resources/template/js/footable/js/footable.paginate.js?v=2-0-1" type="text/javascript"></script>
    <script src="/board/resources/template/js/footable/js/footable.paginate.js?v=2-0-1" type="text/javascript"></script> --%>	    
	
   
<%--   <script type="text/javascript">
	var myTable;
    (function($) {
        "use strict";
        myTable = $("#example1").dataTable();
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

<%@ include file="/WEB-INF/jsp/template/footerLite.jsp" %>

</div>
</body>
</html>