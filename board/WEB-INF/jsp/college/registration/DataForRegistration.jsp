<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="/board/resources/template/js/datepicker/datepicker.css">
<script src="/board/resources/template/js/datepicker/bootstrap-datepicker.js"></script>
<style>
	#modelNoSelection { /* position:absolute;
		background:#F5F5DC; */ /* width:400px;
		height:400px; */ /* border:5px solid #000;
		z-index: 9002; */	
	}
	.modal-fullscreen {
	  background: rgba(255, 255, 255, 0.95);
	}
	.modal-fullscreen .modal-content {
	  background: transparent;
	  border: 0;
	  -webkit-box-shadow: none;
	  box-shadow: none;
	}
	.modal-backdrop.modal-backdrop-fullscreen {
	  background: #ffffff;
	}
	
	
	/* .modal-fullscreen size: we use Bootstrap media query breakpoints */
	
	.modal-fullscreen .modal-dialog {
	  margin: 0;
	  margin-right: auto;
	  margin-left: auto;
	  width: 100%;
	}
	tr.spaceUnder>td {
	  padding-bottom: 1em;
	}
	
#rcorners2 {
    border-radius: 25px;
    border: 2px solid #73AD21;
    width: 950px;
    height: 53px;    
}

</style>
	<!---- body ----->
	<c:if test="${not empty requestScope.ErrorMassage}">
		<div class="alert alert-info">
			<button class="close" type="button" data-dismiss="alert">×</button>
			<span class="entypo-info-circled"></span>
			<strong>Message:</strong> ${requestScope.ErrorMassage}
		</div>
	</c:if>

	<div class="row" style="padding-top: 0px">
		<input type="hidden" name="hiddenShiftID" id="hiddenShiftID" value="${requestScope.hiddenShiftID}" />
		<input type="hidden" name="hiddenVersionID" id="hiddenVersionID" value="${requestScope.hiddenVersionID}" />
		<input type="hidden" name="hiddenGroupID" id="hiddenGroupID" value="${requestScope.hiddenGroupID}" />
		<div class="box-body table-responsive">
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
						<th>ACTION</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.nonRegistered}" var="nonApprovedStudent" varStatus="loop">
						<tr>
							<td>${loop.index+1}</td>
							<td>${nonApprovedStudent.applicantName}</td>
							<td>${nonApprovedStudent.sscRollNo}</td>
							<td>${nonApprovedStudent.boardName}</td>
							<td>${nonApprovedStudent.sscPassingYear}</td>
							<td>${nonApprovedStudent.shiftName}</td>
							<td>${nonApprovedStudent.versionName}</td>
							<td>${nonApprovedStudent.groupName}
								<input type="hidden" value="${nonApprovedStudent.applicantName}"
									id="hidden_student_name_${loop.index+1}"
									name="hidden_student_name">
								<input type="hidden" value="${nonApprovedStudent.sscRollNo}"
									id="hidden_ssc_roll_${loop.index+1}" name="hidden_ssc_roll">
							</td>
							<td id=td${loop.index+1}>
								<c:if test="${nonApprovedStudent.registered=='Y'}"> Already Registered </c:if>
								<c:if test="${nonApprovedStudent.registered=='N'}">
									<button class="btn" onclick="showData(this)" style="width: 140px;background-color: lightblue"  id="${loop.index+1}" name="${nonApprovedStudent.sscRollNo}###${nonApprovedStudent.boardID}###${nonApprovedStudent.sscPassingYear}###${nonApprovedStudent.applicantName}###${nonApprovedStudent.applicationID}###${nonApprovedStudent.boardName}###${nonApprovedStudent.sscReg}">
										Register Student
									</button>
								</c:if>
							</td>
						</tr>
					</c:forEach>
			</table>
		</div>

<script type="text/javascript">

		var update = false;
		var has273 = false;
		
		$('#modelConfirm').on('shown.bs.modal', function () {
			$('#esif').focus();
			$('#modal-body1').animate({ scrollTop: 0 }, 'slow');
			
		});
		$('#modelConfirm').on('close', function () {
			
		});
		var tdRegCell;
		
		function showData(tmp)
		{
			tdRegCell = document.getElementById('td'+tmp.id);
			var res = tmp.name.split("###");
		    $.ajax({
		    	url: 'getIndividualData',		
		 		type: 'POST',
		 		async: true,
				cache: false,
		 		data: {eiinCode:"Hossain" ,applicationID:res[4]},
		 		success: function(msg) {
		 			$('#esif').val('');
					$('#sections').val('');
					$('#fileUpload').val('');
					$('#class_roll').val('');
					$('#hdistrict').val('');
					$('#religion').val('');
					$('#photo_upload_div img').attr("src","resources/images/noimageuser.png");
					$('#photo_upload_div img').attr("width","120");
					$('#photo_upload_div img').attr("height","150");
			
					if ($('#sub4s').length){$('#sub4s').val('');$("#sub4").val('');}
					if ($('#sub5s').length){$('#sub5s').val('');$("#sub5").val('');}
					if ($('#sub6s').length){$('#sub6s').val('');$("#sub6").val('');}
					if ($('#sub7s').length){$('#sub7s').val('');$("#sub7").val('');}
			
		 			var res1 = msg.split("###");
                    $('#first_name').val(res[3]);
                    $('#father_name').val(res1[0]);
                    $('#mother_name').val(res1[1]);
                    $('#gpa').val(res1[4]);
                    $('#shift_id').val(res1[7]);
                    $('#gender').val(res1[3]);
                    $('#dob').val(res1[2]);
                    $('#version').val(res1[6]);
                    $('#group_id').val(res1[5]);
                    $('#sessions1').html('<font color="green" size="4"><font color="red">'+res1[8]+'</font></font>');
                    
                    

                    
                    $('#first_name').prop("readonly", true);
                    $('#father_name').prop("readonly", true);
                    $('#mother_name').prop("readonly", true);
                    $('#gpa').prop("readonly", true);
                    $('#shift_id').prop("readonly", true);
                    $('#gender').prop("readonly", true);
                    $('#dob').prop("readonly", true);
                    $('#version').prop("readonly", true);
                    $('#group_id').prop("readonly", true);
                    $('#class_roll').val('');
					$('#religion').val('');
					$('#hdistrict').val('');
					<s:if test="(#request.userInfo.board_id.equalsIgnoreCase('18') )">
 							$('#religion').val('Islam');
 					</s:if>
                    if(res1[3]=='Male')
                    {
                    	if($("#sub7s option[value='273']").length > 0)
                    	{
                    		$("select#sub7s option").filter("[value='273']").remove();
                    		has273 = true;
						}                    		
                    }	
                    else
                    {
                    	if(has273 && $("#sub7s option[value='273']").length == 0)
                    		$('#sub7s').append("<option value='273'>Home Science (273,274)</option>");
                    }
                    if($("#sub7s option[value='180']").length > 0)
                    {
                    	$("select#sub7s option").filter("[value='180']").remove();
                    	$('#sub7s').append("<option value='180,182'>Engg.Draw & Work (180,182)</option>");
                    	$('#sub7s').append("<option value='180,183'>Engg.Draw & Work (180,183)</option>");
                    	$('#sub7s').append("<option value='180,222'>Engg.Draw & Work (180,222)</option>");
                    }				
		 	},
		 	error: function(e) {
		 	}

                });
		    
		    
		    var bar = $('.bar');
			var percent = $('.percent');
			var status = $('#status');
			var percentComplete1 = 0;
			var percentVal1 = percentComplete1 + '%';
	        bar.width(percentVal1)
	        percent.html(percentVal1);
	        status.html('');
	        
		
			//alert(res[6]);
			
			$('#roll_no').val(res[0]);
			$('#board_id').val(res[1]);
			$('#passing_year').val(res[2]);
			$('#applicationID').val(res[4]);
			$('#boardname').val(res[5]);
			$('#sscReg').val(res[6]);
			document.getElementById('modelConfirmHead').innerHTML = '<center><font size="4"><b>Electronic Student Information Form for Class XI (Session 2018-19)</b></font><br>'
				+'<p id="rcorners2"><font size="4" color="#0489B1"><u>SSC Information</u></font><font color="green" size="4"><br/>'
				+'Board : <font color="red">'+res[5]+'</font>&nbsp;Passing Year : <font color="red">'+res[2]+'</font>&nbsp;Roll : <font color="red">'+res[0]+'</font>&nbsp;Reg : <font color="red">'+res[6]+'</font>'
				+'&nbsp;Session : <font color="red"><span id="sessions1"></span></font>'
				+'</font></p></center>';
			//document.getElementById('modelConfirmBody').innerHTML = 'Body Body Body Body Body '; 
		    $('#modelConfirm').modal('show');		
		    
			//$('#modal-body1').scrollTop(0);
			//$('#modelConfirm').on('shown', function () {
    		//	$("#modal-body1").scrollTop(0);
			//});
		}	
			
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
		 	
		 	var availableSeat=parseInt($("#availableSeat").val().trim());
		 	var counter=parseInt(msg.counter);
		 	var lastAvailableSeat=availableSeat-counter;
		 	//alert(lastAvailableSeat);
		 	        $("#availableSeat").val(lastAvailableSeat);
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
			var quota = $('td:eq(7)', myTable.fnGetNodes());
			var allTextBox = new Array();
			$(myTable.fnGetNodes()).find(':text').each(function () {
						        $this = $(this);
						        allTextBox.push($this);
						    });
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
			for(var i = 0;i < allCheckBox.length; i++)
			{
				if(allCheckBox[i][0].checked)
				{
					if(myTrim(allTextBox[i][0].value)=='')                  /////////////   add trim function
					{
						modelWindowBody += names[i].textContent + " (" + roll_no[i].textContent +")" + "<br/>"; 
					}
					else
					{
						modelConfirmBody += names[i].textContent + "<br/>"; 
						dataString += allCheckBox[i][0].value+'#'+myTrim(names[i].textContent)+'#'+myTable.fnGetPosition(names[i])[0]
									+'#'+allTextBox[i][0].value.trim()+'#'+myTrim(quota[i].textContent) + '###';
						//console.log(myTable.fnGetPosition(names[i]));
					}
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
		<script type="text/javascript">
	var myTable;
	$(document).ready(function() {
		myTable = $('#example1').dataTable();
		$('#example2').dataTable();
		
		$('#dob').datepicker({
            format: "dd-mm-yyyy",
            autoclose: true,
            endDate: '-14y'
        });
	} );
	
	$(".modal-fullscreen").on('show.bs.modal', function () {
	  setTimeout( function() {
	    $(".modal-backdrop").addClass("modal-backdrop-fullscreen");
	  }, 0);
	});
	$(".modal-fullscreen").on('hidden.bs.modal', function () {
	  $(".modal-backdrop").addClass("modal-backdrop-fullscreen");
	});
	$(".modal-transparent").on('show.bs.modal', function () {
	  setTimeout( function() {
	    $(".modal-backdrop").addClass("modal-backdrop-transparent");
	  }, 0);
	});
	$(".modal-transparent").on('hidden.bs.modal', function () {
	  $(".modal-backdrop").addClass("modal-backdrop-transparent");
	});
</script>

		<link rel="stylesheet" type="text/css" href="/board/resources/css/bootstrap.min.css" />
		<!-- <link rel="stylesheet" type="text/css" href="/board/resources/template/css/bootstrap.css" /> -->
		<link rel="stylesheet" type="text/css" href="/board/resources/css/dataTables.bootstrap.css" />
		<script type="text/javascript" language="javascript" src="/board/resources/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" language="javascript" src="/board/resources/js/dataTables.bootstrap.js"></script>
		<script type="text/javascript" language="javascript" src="/board/resources/js/multi_jquery.form.js"></script>

		<style>
			.buttonCancel:hover {
				box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0
					rgba(255, 0, 0, 0.2);
			}
			
			.buttonCSV:hover {
				box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0
					rgba(0, 255, 0, 0.2);
			}
			.fade-scale {
			  transform: scale(0);
			  opacity: 0;
			  -webkit-transition: all .25s linear;
			  -o-transition: all .25s linear;
			  transition: all .25s linear;
			}
			
			.fade-scale.in {
			  opacity: 1;
			  transform: scale(1);
			}		
			.anil {
			  transform: scale(0);
			  opacity: 0;
			  -webkit-transition: all 1s linear;/*opening speed*/
			  -o-transition: all 1s linear;/*opening speed*/
			  transition: all 1s linear;/*opening speed*/
			}
			
			.anil.in {
			  opacity: 1;
			  transform: scale(1);/*size of the div to scal after .in class attached on opening modal*/
			}	
		</style>
		
		
		
  	<!-- Popup modal div start -->
    	<div id="modelConfirm" class="modal modal-fullscreen anil" data-backdrop="static" data-keyboard="true" role="dialog" tabindex="-1">
		  <div class="modal-dialog">		
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header" style="min-height: 60px;background: white;">
		         <button type="button" class="close" data-dismiss="modal">&times;</button> 
		         <h5 class="modal-title" ><font style="color: black"> <span id="modelConfirmHead"></span></font></h5>
		      </div>
		      
		      <form action="submitRegData" method="post" enctype="multipart/form-data">
		      		<input name="roll_no" id="roll_no" type="hidden">
		      		<input name="board_id" id="board_id" type="hidden">
		      		<input name="passing_year" id="passing_year" type="hidden">
		      		<input name="applicationID" id="applicationID" type="hidden">
		      		<input name="boardname" id="boardname" type="hidden">
		      		<input name="sscReg" id="sscReg" type="hidden">
		      		<input type="hidden" name="hgroup_id" id="hgroup_id" value="${requestScope.hiddenGroupID}" />
		      		
		      <div id="modal-body1" class="modal-body" style="display: block;height: 500px;overflow-y: scroll;">
	              <div >
	              	<span id="modelConfirmBody">
		      			
<!-- Text input-->
						<table border="0" width="90%">
							<tr>
								<td width="20%">
									<label class="control-label"><font color="blue">eSIF</font></label> 
								</td>
								<td width="30%">
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					  					<input  id="esif" name="esif" placeholder="eSIF Number" class="form-control numbersOnly"  type="text" tabindex="1" maxlength="5">
					  					
					  					
					  				</div>
								</td>	
								
								<td rowspan="5"  width="10%" style="padding-left: 105px">
												<label class="control-label"><font color="blue">Section</font></label> 
												<select id="sections" name="sections" tabindex="2">
    											<option value="" selected="selected">-- Select Section --</option>
    											<option value="A">A</option><option value="B">B</option><option value="C">C</option><option value="D">D</option>
    											<option value="E">E</option><option value="F">F</option><option value="G">G</option><option value="H">H</option>
    											<option value="I">I</option><option value="J">J</option><option value="K">K</option><option value="L">L</option>
    											<option value="M">M</option><option value="N">N</option><option value="O">O</option><option value="P">P</option>
    											<option value="Q">Q</option><option value="R">R</option><option value="S">S</option><option value="T">T</option>
    											<option value="U">U</option><option value="V">V</option><option value="W">W</option><option value="X">X</option>
    											<option value="Y">Y</option><option value="Z">Z</option>
    											</select>
												<form id="changePhotoForm" name="changePhotoForm" enctype="multipart/form-data" method="post">
                                                  <div class="box_container" style="height: 180px;margin-top: 10px;" id="photo_upload_div">
												     <div id="preview" style="float:left; width:120px; height:150px; ">
                                                   <img id="no-image" name="no-image" class="historyimage" src="resources/images/noimageuser.png" alt="No Photo" width="120" height="150" />
                                                </div>
                                             <input type="file" id="fileUpload" name="fileUpload" onchange="displayPreview1(this.files);"  tabindex="2" />
												
                                             
												<!-- 
												<img id="no-image" class="historyimage"
													src="resources/images/noimageuser.png" alt="No Photo"
													width="120" height="150" />
												<input type="file" id="fileUpload" name="fileUpload"
													onchange="displayPreview1(this.files);" />
													 -->
													
													
													 
								</td>
								<td rowspan="4"  width="15%" style="padding-left: 5px">
												<font color='green' size="2px"><b>Allowed
														Dimension / Resolution - </b></font><br /> <b><font color='blue'
													size="2px">Height:</font> 150px</b><br /> <b><font
													color='blue' size="2px"> Width:</font> 120px</b><br /> <font
													color='green' size="2px"><b>Image Format - <font
														color='black' size="2px">jpg</font></b></font><br />
												<!-- <font color='blue' size="2px">Image Format:</font> <br/> -->
												<font color='green' size="2px"><b>Maximum File
														Size - <font color='black' size="2px">15 KB</font>
												</b></font><br />
												<!-- <font color='blue' size="2px"> Maximum File Size:</font>  <br/> -->
												<a target="_blank" href="http://resizeimage.net/"><font
													color='blue' size="2px"><b>[&nbsp;Click here to
															edit your photo online&nbsp;]</b></font></a>
								</td>	
								<td width="25%"></td>
							</tr>								
							<tr>
								<td width="20%">
									<label class="control-label">Student's Name</label> 
								</td>
								<td width="30%">
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					  					<input  id="first_name" name="first_name" placeholder="Student's Name" class="form-control"  type="text">
					  				</div>
								</td>	
								<td width="25%"></td>
							</tr>
							<tr>
								<td width="20%">
									<label class="control-label">Father's Name</label> 
								</td>
								<td width="30%">
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					  					<input  id="father_name" name="father_name" placeholder="Father's Name" class="form-control"  type="text">
					  				</div>
								</td>	
								<td width="25%"></td>
							</tr>							
							<tr>
								<td width="20%">
									<label class="control-label">Mother's Name</label> 
								</td>
								<td width="30%">
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					  					<input  id="mother_name" name="mother_name" placeholder="Mother's Name" class="form-control"  type="text">
					  				</div>
								</td>	
								<td width="25%"></td>
							</tr>							
							<tr class="spaceUnder">
								<td width="20%">
									<label class="control-label">GPA</label> 
								</td>
								<td width="30%">
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-certificate"></i></span>
					  					<input  id="gpa" name="gpa" placeholder="GPA" class="form-control"  type="text">
					  				</div>
								</td>
								<td width="25%"></td>	
							</tr>							
							<tr class="spaceUnder" >
								<td width="20%">
									<label class="control-label"><font color="blue">Class Roll</font></label> 
								</td>
								<td width="30%">
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-transfer"></i></span>
					  					<input  id="class_roll" name="class_roll" placeholder="Class Roll" class="form-control numbersOnly"  type="text"  tabindex="3" maxlength="5">
					  				</div>
								</td>	
								
								<td width="10%" style="padding-right: 57px" align = "right">
									<label class="control-label">Shift</label> 
								</td>
								<td width="15%">
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-bell"></i></span>
					  					<input  id="shift_id" name="shift_id" placeholder="Shift"  class="form-control"  type="text">
					  					
					  				</div>
								</td>	
								<td width="25%"></td>
							</tr>							
							<tr  class="spaceUnder">
								<td width="20%">
									<label class="control-label"><font color="blue">Nationality</font></label> 
								</td>
								<td width="30%">
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-road"></i></span>
										<%@include file="country.jsp" %>
					  					
					  				</div>
								</td>	
								
								<td width="10%" style="padding-right: 37px" align = "right">
									<label class="control-label">Gender</label> 
								</td>
								<td width="15%">
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					  					<input  id="gender" name="gender" placeholder="Gender" class="form-control"  type="text">
					  				</div>
								</td>
								<td width="25%"></td>	
							</tr>							
							<tr class="spaceUnder">
								<td width="20%">
									<label class="control-label"><font color="blue">Home District</font></label> 
								</td>
								<td width="30%">
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-road"></i></span>
					  					<%@include file="district.jsp" %>
					  				</div>
								</td>
									
								<td width="10%" style="padding-right: 30px" align = "right">
									<label class="control-label"><font color="blue">Religion</font></label> 
								</td>
								<td width="15%">
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<s:if test="(#request.userInfo.board_id.equalsIgnoreCase('18') )">
						<select id="religion" name="religion"   class="form-control"  tabindex="6">
    						<option value="Islam" selected="selected">Islam</option>
						</select>	
 					</s:if>	
 					<s:else>									
						<select id="religion" name="religion"   class="form-control"  tabindex="6">
    						<option value="" selected="selected">-- Select Religion --</option>
    						<option value="Islam">Islam</option>
    						<option value="Hinduism">Hinduism</option>
							<option value="Buddhism">Buddhism</option>
    						<option value="Christianity">Christianity</option>
						</select>					 
					</s:else> 							  					
					  				</div>
								</td>
								<td width="25%"></td>	
							</tr>	
														
							<tr class="spaceUnder">
								<td width="20%">
									<label class="control-label">Date of Birth</label> 
								</td>
								<td width="30%">
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
					  					<input  id="dob" name="dob" placeholder="DD/DD/YYYY" class="form-control"  type="text">
					  				</div>
								</td>	
								<td width="10%" style="padding-right: 33px" align = "right">
									<label class="control-label" >Version</label> 
								</td>
								<td width="15%">
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-bell"></i></span>
					  					<input  id="version" name="version" placeholder="Version" class="form-control"  type="text">
					  				</div>
								</td>
								<td width="25%"></td>	
							</tr>
							<tr class="spaceUnder">
								<td width="20%">
									<label class="control-label">Group</label> 
								</td>
								<td width="30%">
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-credit-card"></i></span>
					  					<input  id="group_id" name="group_id" placeholder="Select Group" class="form-control"  type="text">
					  				</div>
								</td>	
								<td width="10%"></td>
								<td width="15%"></td>
								<td width="25%"></td>	
							</tr>							


						</table>
						<s:if test="(#request.hiddenGroupID.equalsIgnoreCase('0') )"> <%@include file="g_science.jsp" %> </s:if>
						<s:if test="(#request.hiddenGroupID.equalsIgnoreCase('2') )"> <%@include file="g_humanities.jsp" %> </s:if>
						<s:if test="(#request.hiddenGroupID.equalsIgnoreCase('8') )"> <%@include file="g_business.jsp" %> </s:if>
						<s:if test="(#request.hiddenGroupID.equalsIgnoreCase('5') )"> <%@include file="g_homescience.jsp" %> </s:if>
						<s:if test="(#request.hiddenGroupID.equalsIgnoreCase('6') )"> <%@include file="g_islamicstudies.jsp" %> </s:if>
						<s:if test="(#request.hiddenGroupID.equalsIgnoreCase('7') )"> <%@include file="g_music.jsp" %> </s:if>
						<s:if test="(#request.hiddenGroupID.equalsIgnoreCase('9') )"> <%@include file="m_gen.jsp" %> </s:if>
						<s:if test="(#request.hiddenGroupID.equalsIgnoreCase('10') )"> <%@include file="m_muz.jsp" %> </s:if>
						<s:if test="(#request.hiddenGroupID.equalsIgnoreCase('12') )"> <%@include file="m_sci.jsp" %> </s:if>
					
	<!-- Select Basic -->

	              	</span>
	              </div>             
		      </div>
		      
		      <div class="modal-footer">
		      	<button type="submit" class="btn btn-danger" data-ok="modal"  tabindex="11">Submit Data</button>
		        <button type="button" class="btn btn-danger" data-dismiss="modal" tabindex="12">Close</button>
		      </div>
		      
		      </form>
		          <div class="progress">
        			<div class="bar"></div >
        			<div class="percent">0%</div >
    			  </div>
		    </div>
			<div id="status"></div>
		  </div>
		</div>
    <!-- Popup modal div end -->    


							

 <script type="text/javascript">
 
 
 (function() {
 	
	var bar = $('.bar');
	var percent = $('.percent');
	var status = $('#status');
	$('form').ajaxForm({
	    beforeSend: function() {
	    	if(!validateData())
	    	{
	    		xhr.abort();
	    		return false;
	    	}
	    	if(!confirm("Do you want to submit this data?"))
	    	{
	    		xhr.abort();
	    		return false;
	    	}	
	    	//console.log($('#first_name').val())
	        status.empty();
	        var percentVal = '0%';
	        bar.width(percentVal)
	        percent.html(percentVal);
	    },
	    beforeSubmit: function() {
	    	//console.log($('#first_name').val())
	        status.empty();
	        var percentVal = '0%';
	        bar.width(percentVal)
	        percent.html(percentVal);
	    },
	    uploadProgress: function(event, position, total, percentComplete) {
	        var percentVal = percentComplete + '%';
	        bar.width(percentVal)
	        percent.html(percentVal);
	    },
	    success: function() {
	        var percentVal = '100%';
	        bar.width(percentVal)
	        percent.html(percentVal);
	    },
		complete: function(xhr) {
			status.html('<font color="red" size="6">'+xhr.responseText+'</font>');
			if(xhr.responseText=='Registration Successfull')
			{
				alert(xhr.responseText);
				//$('#modelConfirm').css("display", "block"); 
				//$('#modelConfirm').close();
				$('#modelConfirm').modal('hide');
				$('#class_roll').val('');
				$('#religion').val('');
				$('#hdistrict').val('');
				tdRegCell.innerHTML = 'Already Registered';
			}
			else //if(xhr.responseText=='Duplicate eSIF')
			{
				alert(xhr.responseText);
				$('#modelConfirm').animate({ scrollTop: 400 }, 'slow');
			}	
		}
	}); 

})();       
 
 
  
  
  
  
// preview and size validation
var _URL = window.URL || window.webkitURL;
	
function displayPreview(files) {
	$('#preview').html("");
	var file = files[0];
	var img = new Image();
	var width = img.width;
	var height = img.height;
	
	img.width=120;
	img.height=150;
	
	var size = file.size / 1024;
//alert(width+":"+height);
	img.onload = function() {
	//rem by delwar
//alert(width+':'+height+':'+size);
	if (width>120 || height>150){// || size>50) {
		alert("exceed W/H px OR Size = "+ size);
		$('#myFile').val("");
	return false;
	}
	
$('#preview').append(img);
};

img.src = _URL.createObjectURL(file);

}

function displayPreview1(files) {

	var formData = new FormData($('form')[0]);
	//alert(formData);
			           
		 $.ajax({
			 	url: 'applicant/applicantPhotoValidation',		
			 	type: 'POST',
			    data: formData,
			    async: false,
			    cache: false,
			    contentType: false,
			    processData: false,
			 	success: function(data) {
//alert('hi');
//alert(data);
			 	 if(data=='success')
			 	 {
			 	 	displayPreview(files);
	             	//enableField("create_account");
	             	$('#modal-body1').animate({ scrollTop: 400 }, 'slow');
			 	 }
	                		 	
			 	else
			 	{
			 	$("#fileUpload").val("");
			 	$('#preview').html("<img id='theImg' src='/board/resources/images/dummy_120x150.gif'/>");
			 				 	
			 	 if(data=="error#Image with 120 px Width and 150 px Height are allowed to upload.")
			 	 {
			 	 alert(data);
			 	 }
			 	 
			 	 else if(data=="error#Not a Valid type. Only Supported Image Type is '.jpg'")
			 	 {
			 	 alert(data);
			 	 }
			 	 
			 	  else if(data=="error#Maximum allowed Photo size is 15 Kb")
			 	 {
			 	 alert(data);
			 	 }
			 	 
			 	 else if(data=="error#Probelm in Uploading the Image")
			 	 {
			 	 alert(data);
			 	 }
			 	 else if(data=="error#Please upload PORTRAIT photo")
			 	 {
			 	 alert(data);
			 	 }
			 	 	
			 	return false;
			 		
			     }

			 	},
			 	error: function(e) {
	            enableField("create_account");
			 	alert(e);
			 	}
		});

}



function changePhoto(){

if($("#fileUpload").val()=="" || $("#fileUpload").val()==null)
{
alert("Please upload a new valid photo to change..!!");
return false;
}


if(confirm("Do you really want to change  photo ?"))
{
	 var formData = new FormData($('form')[0]);
		 $.ajax({
			 	url: 'applicant/changePhoto.action',		
			 	type: 'POST',
			    data: formData,
			    async: false,
			    cache: false,
			    contentType: false,
			    processData: false,
			 	success: function(response) {
			 	//var a=msg.message;
			 	///a=JSON.stringify(a);
			 	//cosole.log(msg.message);			 	
			 	alert(response);
			 	},
			 	error: function(response) {
			 	//var a=JSON.stringify(response);
			 	var arr = [];
			 	var arr2 = [];
                json = JSON.stringify(response);
                arr = json.split(/:/);
                st=arr[4];
                arr2=st.split(/"/);
			 	alert(arr2[1]);
			 	}
		});
}
else
    return false;	
	 
}


function scrollToBottom(){
	$('html, body').animate({scrollTop:$(document).height()}, 'slow');
}
jQuery('.numbersOnly').keyup(function () { 
    this.value = this.value.replace(/[^0-9\.]/g,'');
});
</script>
    	
    			