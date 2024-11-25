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
                            
                            <div id="validation_mas" style="font-size: x-large;color: red">${requestScope.validation_mas}</div>	
                            
                          <form action="ChangeSVG" method="post" id="cancelApproveStudentOfMeritForm" name="cancelApproveStudentOfMeritForm" onsubmit="return validateForm()" >
                          <input type="hidden" name="application_id" id="application_id" value="${requestScope.application_id}" />
                          <input type="hidden" name="app_eiin" id="app_eiin" value="${requestScope.app_eiin}" />
                          <input type="hidden" name="app_shift_id" id="app_shift_id" value="${requestScope.app_shift_id}" />
                          <input type="hidden" name="app_version_id" id="app_version_id" value="${requestScope.app_version_id}" />
                          <input type="hidden" name="app_group_id" id="app_group_id" value="${requestScope.app_group_id}" />
                            
                            
                           
                            <div class="box-body table-responsive">
                                <table id="example1_joy" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th>NAME</th>
                                            <th>SSC ROLL</th>
                                            <th>SSC BOARD</th>
                                            <th>SSC YEAR</th> 
                                            <th>SHIFT</th>
                                            <th>VERSION</th>
                                            <th>GROUP</th>
                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                 
			
				        <c:forEach items="${requestScope.approvedStudentListOfMerit}" var="approvedStudent" varStatus="loop">
							<tr>
								
								
								<%-- <td>${approvedStudent.applicationID}</td> --%>
								<%-- <td>${loop.index+1}</td>  --%>
								<td>${approvedStudent.applicantName}</td> 
	 							<td>${approvedStudent.sscRollNo}</td>
								<td>${approvedStudent.boardName}</td>
								<td>${approvedStudent.sscPassingYear}</td>
								
								<td>${approvedStudent.shiftName}</td>
								<td>${approvedStudent.versionName}</td>
								<td>${approvedStudent.groupName}</td>
							</tr>		
						</c:forEach>
 

                                </table>
                               </div>
                                  



                 <table style="width:200px;font-size:14px"
								class="table table-bordered table-striped cf">
								<tr style="background-color: #f5fffa;">
									<%--<td style="color:black;"><strong>Shift </strong></td>
									<td style="color:black;"><strong>Version</strong></td>
									 --%>
									<td style="color:black;"><strong>Group</strong></td>
									
								</tr>

								<tr>
								<%--
									<td>
									
									<select name="shiftIdCSV" id="shiftIdCSV">
											<option value="none" selected>Select Shift</option>
											<c:forEach items="${shiftList}" var="shift">
												<option value="${shift.shiftId}">${shift.shiftName}
												</option>
											</c:forEach>

									</select>
									 
									 </td>

									<td>
									
									<select name="versionIdCSV" id="versionIdCSV" >
											<option value="none" selected>Select Version</option>
											<c:forEach items="${versionList}" var="version">
												<option value="${version.versionId}">${version.versionName}
												</option>
											</c:forEach>
									</select>
									
									</td>
                                           --%>
									<td>
									
									<select name="groupIdSVGchange" id="groupIdSVGchange">
											<option value="none" selected>Select Group</option>
											<c:forEach items="${groupList}" var="group">
												<option value="${group.groupId}">${group.groupName}
												</option>
											</c:forEach>

									</select>
									 
									</td>
									 
								</tr>
							</table>
				<div id="loader_svg_change"></div>			
				
                            
                                 <div style="width:280px;float: none;right-padding:0cm">
                                 	<input type="hidden" name="formAction" id="formAction" value="" />
                                  	<br>
                                  	
                                  	<input type="button" style="width:200px;padding: 1px 12px;font-size: x-large;" class="btn btn-danger buttonCancel" name="group_change" value="Change Group" onclick="submitChangeConfirm()" />                                 	
         
                                </div>
                               
                            </form>
    
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





   function OptionValueRemove() {
		var selMenu = document.getElementById("groupIdSVGchange");
         //x.remove(selMenu.selectedIndex+1); 
		/* for (i=0;i<selMenu.length;  i++) {
		   if (selMenu.options[i].value=='0') {
		     selMenu.remove(i);
		   }
		}*/
	}
	
	window.onload = setTimeout("OptionValueRemove()", 200);
	
</script> 

<script type="text/javascript">
  
  



  function submitChangeConfirm()
  {
 
   if(confirm("You are going to change the group"))
   {
    submitChangeGroupForm();
   }
   
  }
	function submitChangeGroupForm()
	{
	  $('#successSvgChangeResultDiv').html('');

       if($("#ssc_roll").val()=="none"){
            alert("Select a Group");return;
        }
        
        $('#loader_svg_change').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "changeSVGSubmit",
            dataType: 'text',
            async   : false,
            data    : {application_id:$("#application_id").val() ,eiinCode: $("#app_eiin").val().trim(),shift_id: $("#app_shift_id").val().trim(),version_id: $("#app_version_id").val().trim(),
                       group_id: $("#app_group_id").val().trim(), new_group_id: $("#groupIdSVGchange").val().trim()}

        }).done(function (msg) {
        
                $('#loader_svg_change').html('');
                $('#successSvgChangeResultDiv').html('');
                $("#successSvgChangeResultDiv").html(msg);
                clearAll();
                   scrollToBottom();
                })
                .always(function () {
                    //$('#sw-val-step-3').unmask();
                })
                .fail(function (data) {
                    if (data.responseCode)
                        alert(data.responseCode);
                });
	 	
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

