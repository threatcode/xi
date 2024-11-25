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
		height:300px; */ /* border:5px solid #000;
		z-index: 9002; */	
	}

	tr.spaceUnder>td {
	  padding-bottom: 1em;
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
		<div class="box-body table-responsive">
			<table id="example1" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>SL. NO</th>
						<th>Subject ID</th>
						<th>Subject Name</th>
						<th>ACTION</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.lstCDTO}" var="nonApprovedStudent" varStatus="loop">
						<tr>
							<td>${loop.index+1}</td>
							<td>${nonApprovedStudent.sub_id}</td>
							<td>${nonApprovedStudent.sub_name}</td>
							
							<td id=td${loop.index+1}>
									<button class="btn" onclick="deleteSubject(this)" style="width: 140px;background-color: lightblue"  id="${loop.index+1}" name="${nonApprovedStudent.eiin}###${nonApprovedStudent.sub_id}###${nonApprovedStudent.sub_name}">
										Delete Subject
									</button>
							</td>
							
						</tr>
					</c:forEach>
			</table>
			<button class="btn" onclick="addSubject()" style="width: 300px;background-color: lightblue"> Add Subject </button>
		</div>

<script type="text/javascript">

	var peiin='${requestScope.subjectEiin}';
		var update = false;

		$('#modelConfirm').on('shown.bs.modal', function () {
			$('#esif').focus();
			$('#modal-body1').animate({ scrollTop: 0 }, 'slow');
			
		});
		$('#modelConfirm').on('close', function () {
			
		});
		var tdRegCell;
		function deleteSubject(tmp)
		{
			if(confirm("Do you really want to delete this subject ?"))
			{
				tdRegCell = document.getElementById('td'+tmp.id);
				var res = tmp.name.split("###");
			    $.ajax({
			    	url: 'deleteSubject',		
			 		type: 'POST',
			 		async: true,
					cache: false,
			 		data: {eiinCode:"Hossain" ,eiin:res[0],sub_id:res[1]},
			 		success: function(msg) {
			 			alert(msg);
			 			if(msg=='Subject deleted')
						{
								tdRegCell.innerHTML = 'Subject deleted';
						}
					
			 	},
			 	error: function(e) {
			 	}
	
	                });
            }
		
		}
		function addSubject()
		{
			document.getElementById('modelConfirmHead').innerHTML = '<center><font size="4"><b>Add Subject for '+peiin+'</b></font></center>';
			//document.getElementById('modelConfirmBody').innerHTML = 'Body Body Body Body Body '; 
		    $('#modelConfirm').modal('show');
		    showData();
		}
		
		
		function showData()
		{
		    $.ajax({
		    	url: 'getSubjectEiin',		
		 		type: 'POST',
		 		async: true,
				cache: false,
		 		data: {eiinCode:"Hossain" ,eiin:peiin},
		 		success: function(msg) {
		 			var res = msg.split("###");
		 			for (i = 0; i < res.length; i++) {
		 				$('#addedsubject').append('<option value='+res[i]+'>'+res[i+1]+' ('+res[i]+')'+'</option>');
		 				i++;
		 			}
					
		 		},
		 		error: function(e) {
		 		}

                });

		}	
		function addData()
		{
			if(!validateData())
				return false;
			var status = $('#status');	
		    $.ajax({
		    	url: 'addSubject',		
		 		type: 'POST',
		 		async: true,
				cache: false,
		 		data: {eiinCode:"Hossain" ,eiin:peiin, sub_id:$('#addedsubject').val()},
		 		success: function(msg) {
		 			status.html('<font color="red" size="6">'+msg+'</font>');
					if(msg=='Subject added')
					{
						alert(msg);
						$('#modelConfirm').modal('hide');
						$('#addedsubject').val('');
					}
					else //if(xhr.responseText=='Duplicate eSIF')
					{
						alert(msg);
						//$('#modelConfirm').animate({ scrollTop: 400 }, 'slow');
					}
		 		},
		 		error: function(e) {
		 		}

                });

		}
	function validateData()
 	{
 		if($('#addedsubject').val()=='')
 		{
 			alert('Please select subject');
 			return false;
 		}
 		return true;
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
              		<span id="modelConfirmBody" style="display: block;height: 100px;overflow-y: scroll;">
              			<select id="addedsubject" name="addedsubject">
              				<option value="" selected="selected">-- Select Subject --</option>
              			</select>
              		
              		</span>
          		</div>             
		      </div>
		      
		      <div class="modal-footer">
		      	<button type="button" onclick="addData()" class="btn btn-danger" data-ok="modal"  tabindex="11">Add Subject</button>
		        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		      </div>
		      
		    </div>
		
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
			if(xhr.responseText=='Subject added')
			{
				alert(xhr.responseText);
				//$('#modelConfirm').css("display", "block"); 
				//$('#modelConfirm').close();
				$('#modelConfirm').modal('hide');
				$('#addedsubject').val('');
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
	var size = file.size / 1024;
//alert(width+":"+height);
	img.onload = function() {

	if (width>120 || height>150 || size>50) {
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
			 	url: 'applicant/applicantPhotoValidation.action',		
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
			 	 
			 	  else if(data=="error#Maximum allowed Photo size is 50 Kb")
			 	 {
			 	 alert(data);
			 	 }
			 	 
			 	  else if(data=="error#Probelm in Uploading the Image")
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
    	
    			