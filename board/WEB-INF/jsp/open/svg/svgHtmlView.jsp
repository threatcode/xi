<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=10">
<title>XICA 2020: HTML View of SVG</title>


<link rel="icon" type="image/x-icon" href="resources/_images/favicon.ico" />
<link href="resources/css/college.css" rel="stylesheet" />




<link rel="stylesheet" href="resources/css/tabledata/jquery.dataTables.css" type="text/css"></link>


<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<link
	href="resources/_stylesheet/ApplicantRequiredContentDesign.css"
	rel="stylesheet" />
	
<link href="resources/_stylesheet/RequiredContentDesign.css"
	rel="stylesheet" />

<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
</head>


<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <body onload="createCaptcha()"> -->

<body>
<div id="panelwrap">
	<div align="center">
		<div align="center" style="min-height: 500px;">
			<div style="padding-top: 50px;">
			
				<input type='hidden' name='abc' id='abc' value=''> <label>Board</label>
				<select id="helper_board_id" onchange="fetchDistrict();">
			
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

					
				</select> <label>District</label> <select id="district"
					onchange="fetchCollegeList();">
				</select> <label>College</label> <select id="college_id" onchange="setThana()">
					<option value="">Select College</option>
				</select> 
			</div>
			<br>
	<div>

		<div style="font-size: 20px; color: maroon;"></div>
		<br />
		<p style="font-size: 20px; font-family: sans-serif; font-style: italic; color: #061BFC;">College Information</p>
		<table class="display dataTable" id="collegeTable" style="width: 80%;">
			<tr>
				<th width="10%">College</th>
				<td colspan="5">
					<input type="text" readonly size="50" id="dCollegeName" value=''>
				</td>
			</tr>

			<tr>
				<th width="10%">District</th>
				<td width="20%"><input type="text" readonly size="50" id="dDistrict" value=''></td>
				<th width="10%">Thana</th>
				<td colspan="3"><input type="text" readonly size="50" id="dThana" value=''></td>
			</tr>
			
		</table>

	</div>
	
	
				
			<div id="showSVG">
				  				
			</div>			
			<br>
		</div>
	</div>


	<script type="text/javascript">
		
		function fetchDistrict() {
			$('#college_id').find('option').remove();
			clearThana();
			var boardId = helper_board_id.value;

			//Dhaka Board-->13 Districts
			var distDhaka = [ [ "", "Select District" ], [ "3026", "Dhaka" ],
					[ "3029", "Faridpur" ], [ "3033", "Gazipur" ],
					[ "3035", "Gopalganj" ], [ "3048", "Kishorgonj" ],
					[ "3056", "Manikganj" ], [ "3054", "Madaripur" ],
					[ "3059", "Munshiganj" ], [ "3068", "Narsingdi" ],
					[ "3067", "Narayanganj" ], [ "3082", "Rajbari" ],
					[ "3086", "Shariatpur" ], [ "3093", "Tangail" ] ];
			//Dhaka Board-->6 Districts
			var distComilla = [ [ "", "Select District" ],
					[ "2012", "Brahmanbaria" ], [ "2013", "Chandpur" ],
					[ "2019", "Comilla" ], [ "2030", "Feni" ],
					[ "2051", "Lakshmipur" ], [ "2075", "Noakhali" ] ];
			//Rajshahi Board-->8 Districts
			var distRajshahi = [ [ "", "Select District" ],
					[ "5010", "Bogra" ], [ "5070", "Chapai Nawabganj" ],
					[ "5038", "Joypurhat" ], [ "5064", "Naogaon" ],
					[ "5069", "Natore" ], [ "5076", "Pabna" ],
					[ "5081", "Rajshahi" ], [ "5088", "Sirajganj" ] ];
			//Jessore Board--> 10 Districts
			var distJessore = [ [ "", "Select District" ],
					[ "4001", "Bagerhat" ], [ "4018", "Chuadanga" ],
					[ "4041", "Jessore" ], [ "4044", "Jhenaidah" ],
					[ "4047", "Khulna" ], [ "4050", "Kushtia" ],
					[ "4055", "Magura" ], [ "4057", "Meherpur" ],
					[ "4065", "Narail" ], [ "4087", "Satkhira" ] ];
			//Chittagong Board --> 5 Districts
			var distChittagong = [ [ "", "Select District" ],
					[ "2003", "Bandarban" ], [ "2015", "Chittagong" ],
					[ "2022", "Cox's Bazar" ], [ "2046", "Khagrachari" ],
					[ "2084", "Rangamati" ] ];
			//Barisal Board--> 6 Districts
			var distBarisal = [ [ "", "Select District" ],
					[ "1004", "Barguna" ], [ "1006", "Barisal" ],
					[ "1009", "Bhola" ], [ "1042", "Jhalokati" ],
					[ "1078", "Patuakhali" ], [ "1079", "Pirojpur" ] ];
			//Sylhet Board--> 4 Districts
			var distSylhet = [ [ "", "Select District" ],
					[ "6036", "Habiganj" ], [ "6058", "Maulvibazar" ],
					[ "6090", "Sunamganj" ], [ "6091", "Sylhet" ] ];
			//Mymensingh--> 4 Districts
			var distMymensingh = [ [ "", "Select District" ],
					[ "3039", "Jamalpur" ], [ "3061", "Mymensingh" ],
					[ "3072", "Netrakona" ], [ "3089", "Sherpur" ] ];
			//Dinajpur--> 8 Districts
			var distDinajpur = [ [ "", "Select District" ],
					[ "5027", "Dinajpur" ], [ "5032", "Gaibandha" ],
					[ "5049", "Kurigram" ], [ "5052", "Lalmonirhat" ],
					[ "5073", "Nilphamari" ], [ "5077", "Panchagarh" ],
					[ "5085", "Rangpur" ], [ "5094", "Thakurgaon" ] ];
			//Dinajpur--> 64 Districts
			var distMadrasah = [ [ "", "Select District" ],
					[ "4001", "Bagerhat" ], [ "2003", "Bandarban" ],
					[ "1004", "Barguna" ], [ "1006", "Barisal" ],
					[ "1009", "Bhola" ], [ "2012", "Brahmanbaria" ],
					[ "5010", "Bogra" ], [ "2013", "Chandpur" ],
					[ "5070", "Chapai Nawabganj" ], [ "2015", "Chittagong" ],
					[ "4018", "Chuadanga" ], [ "2019", "Comilla" ],
					[ "2022", "Cox's Bazar" ], [ "3026", "Dhaka" ],
					[ "5027", "Dinajpur" ], [ "3029", "Faridpur" ],
					[ "2030", "Feni" ], [ "5032", "Gaibandha" ],
					[ "3033", "Gazipur" ], [ "3035", "Gopalganj" ],
					[ "6036", "Habiganj" ], [ "3039", "Jamalpur" ],
					[ "4041", "Jessore" ], [ "1042", "Jhalokati" ],
					[ "4044", "Jhenaidah" ], [ "5038", "Joypurhat" ],
					[ "2046", "Khagrachari" ], [ "4047", "Khulna" ],
					[ "3048", "Kishorgonj" ], [ "5049", "Kurigram" ],
					[ "4050", "Kushtia" ], [ "2051", "Lakshmipur" ],
					[ "5052", "Lalmonirhat" ], [ "3056", "Manikganj" ],
					[ "3054", "Madaripur" ], [ "4055", "Magura" ],
					[ "6058", "Maulvibazar" ], [ "4057", "Meherpur" ],
					[ "3059", "Munshiganj" ], [ "3061", "Mymensingh" ],
					[ "5064", "Naogaon" ], [ "3067", "Narayanganj" ],
					[ "4065", "Narail" ], [ "3068", "Narsingdi" ],
					[ "5069", "Natore" ], [ "3072", "Netrakona" ],
					[ "5073", "Nilphamari" ], [ "2075", "Noakhali" ],
					[ "5076", "Pabna" ], [ "5077", "Panchagarh" ],
					[ "1078", "Patuakhali" ], [ "1079", "Pirojpur" ],
					[ "3082", "Rajbari" ], [ "5081", "Rajshahi" ],
					[ "2084", "Rangamati" ], [ "5085", "Rangpur" ],
					[ "4087", "Satkhira" ], [ "3086", "Shariatpur" ],
					[ "3089", "Sherpur" ], [ "5088", "Sirajganj" ],
					[ "6090", "Sunamganj" ], [ "6091", "Sylhet" ],
					[ "3093", "Tangail" ], [ "5094", "Thakurgaon" ] ];

			var dist = document.getElementById('district');

			document.getElementById('district').innerHTML = "";

			if (boardId == 10) {
				for ( var i = 0; i < distDhaka.length; i++) {
					var opt = document.createElement('option');
					opt.innerHTML = distDhaka[i][1];
					opt.value = distDhaka[i][0];
					dist.appendChild(opt);
				}
			} else if (boardId == 11) {
				for ( var i = 0; i < distComilla.length; i++) {
					var opt = document.createElement('option');
					opt.innerHTML = distComilla[i][1];
					opt.value = distComilla[i][0];
					dist.appendChild(opt);
				}
			} else if (boardId == 12) {
				for ( var i = 0; i < distRajshahi.length; i++) {
					var opt = document.createElement('option');
					opt.innerHTML = distRajshahi[i][1];
					opt.value = distRajshahi[i][0];
					dist.appendChild(opt);
				}
			} else if (boardId == 13) {
				for ( var i = 0; i < distJessore.length; i++) {
					var opt = document.createElement('option');
					opt.innerHTML = distJessore[i][1];
					opt.value = distJessore[i][0];
					dist.appendChild(opt);
				}
			} else if (boardId == 14) {
				for ( var i = 0; i < distChittagong.length; i++) {
					var opt = document.createElement('option');
					opt.innerHTML = distChittagong[i][1];
					opt.value = distChittagong[i][0];
					dist.appendChild(opt);
				}
			} else if (boardId == 15) {
				for ( var i = 0; i < distBarisal.length; i++) {
					var opt = document.createElement('option');
					opt.innerHTML = distBarisal[i][1];
					opt.value = distBarisal[i][0];
					dist.appendChild(opt);
				}
			} else if (boardId == 16) {
				for ( var i = 0; i < distSylhet.length; i++) {
					var opt = document.createElement('option');
					opt.innerHTML = distSylhet[i][1];
					opt.value = distSylhet[i][0];
					dist.appendChild(opt);
				}
			} else if (boardId == 17) {
				for ( var i = 0; i < distDinajpur.length; i++) {
					var opt = document.createElement('option');
					opt.innerHTML = distDinajpur[i][1];
					opt.value = distDinajpur[i][0];
					dist.appendChild(opt);
				}
			} else if (boardId == 21) {
				for ( var i = 0; i < distMymensingh.length; i++) {
					var opt = document.createElement('option');
					opt.innerHTML = distMymensingh[i][1];
					opt.value = distMymensingh[i][0];
					dist.appendChild(opt);
				}
			} else if (boardId == 18) {
				for ( var i = 0; i < distMadrasah.length; i++) {
					var opt = document.createElement('option');
					opt.innerHTML = distMadrasah[i][1];
					opt.value = distMadrasah[i][0];
					dist.appendChild(opt);
				}
			}

		}
		function clearThana()
		{
			$('#dCollegeName').val('');$('#dDistrict').val('');$('#dThana').val('');$('#showSVG').html('');return;
		}
		function setThana()
		{
			if($('#college_id').val()=='')
			{
				$('#dCollegeName').val('');$('#dDistrict').val('');$('#dThana').val('');$('#showSVG').html('');return;
			}
			for ( var i = 0; i < colleges.length; i++) {
				if($('#college_id').val()==colleges[i].eiin)
				{
					$('#dCollegeName').val(colleges[i].college_name);$('#dDistrict').val($("#district option:selected").html());$('#dThana').val(colleges[i].thana_name);
				}
			}
			fetchCollegeSVG();
		}
		var colleges;
		function setCollegeSelectBoxData(data) {
			$('#showSVG').html('');
			$('#college_id').find('option').remove();
			colleges = data.colleges;
			var collegeId = document.getElementById('college_id');
			var opt = document.createElement('option');
			opt.innerHTML = '-- Select College --';
			opt.value = '';
			collegeId.appendChild(opt);
			for ( var i = 0; i < colleges.length; i++) {
				opt = document.createElement('option');
				opt.innerHTML = colleges[i].college_name;
				opt.value = colleges[i].eiin;
				collegeId.appendChild(opt);
			}
			
		}

		function clearSelectBox() {
			for ( var i = 0; i < arguments.length; i++) {
				$("#" + arguments[i]).find('option:gt(0)').remove();
			}
		}
		var functions='';
		function fetchCollegeList() {
			functions='fetchCollegeList';
			clearThana();
			showPopUp1('Input Captcha','Please type captcha text.');
			var d = new Date();
			$("#captchaImg").attr("src", "captcha?"+d.getTime());			
		}
		function fetchCollegeSVG() {
			functions='fetchCollegeSVG';
			showPopUp1('Input Captcha','Please type captcha text.');
			var d = new Date();
			$("#captchaImg").attr("src", "captcha?"+d.getTime());			
		

		} 	

function scrollToBottom(){
	$('html, body').animate({scrollTop:$(document).height()}, 'slow');
}		

var arrShift = ["-","Morning","Day","Evening","Morning Male","Day Male"];
var arrVersion = ["-","Bangla","English"]; 
var arrGroup = ["Science","","Humanities","","Agriculture","Home Economics","Islamic Studies","Music","Business Studies","MADRASA - General","MADRASA - Muzzabid","","MADRASA - Science","","","","","","","","","HSCVOC - Agro Machinery","HSCVOC - Automobile","HSCVOC - Building Maintenance and Construction","HSCVOC - Clothing and Garments Finishing","HSCVOC - Computer Operation and Maintenance","HSCVOC - Drafting Civil","HSCVOC - Electrical Works and Maintenance","HSCVOC - Electronic Control and Communication","HSCVOC - Fish Culture and Breeding","HSCVOC - Machine Tools Operation and Maintenance","HSCVOC - Poultry Rearing and Farming","HSCVOC - Refrigeration and Air-conditioning","HSCVOC - Welding and Fabrication","HSCVOC - Industrial Wood Working","HSCVOC - Wet Processing","HSCVOC - Yarn and Fabric Manufacturing","HSCBM - Accounting","HSCBM - Banking","HSCBM - Computer Operation","HSCBM - Entrepreneurship Development","HSCBM - Secretarial Science","DCOM - Shorthand","DCOM - Accounting","HSCVOC - Warehouse and Storekeeping","HSCVOC - Home Economics","","","","",""];
var arrGender = {'C': 'Combined', 'M': 'Male', 'F': 'Female'};

function collegeView(tmp)
{
			
		var currrentSelectionHTML='<div id="CollegeDetailsContainerDiv">  <div id="currentCollegeSelection"><p style="font-size: 24px; font-family: sans-serif; font-style: italic; color: #061BFC;">SVG Details</p> </div> <div class="CollegeDetailsTableDiv"> ';
		currrentSelectionHTML+='<table class="CollegeDetailsTable"> <tbody> <tr>  <th>Shift</th> <th>Version</th> <th>Group</th> <th>Gender</th> <th>Total Seat</th></tr>';
		var svgList = tmp.total_seat;
		for (var k in svgList){
			var karr = k.split(",");
			currrentSelectionHTML+='<tr><td>'+arrShift[karr[0]]+'</td> <td>'+arrVersion[karr[1]]+'</td> <td>'+arrGroup[karr[2]]+'</td> <td>'+arrGender[karr[3]]+'</td> <td>'+svgList[k]+'</td></tr>';
		}
		currrentSelectionHTML+='</tbody> </table> </div> </div>';
		
		$('#showSVG').html('');
		$("#showSVG").html(currrentSelectionHTML);
}

  function showPopUp1(head, body)
  {
 		$('#popupDiv1').modal('toggle');
		$('#popupHead1').html(head);
		$('#popupMSG1').html(body);
  }
  function showPopUp(head, body)
  {
 		$('#popupDiv').modal('toggle');
		$('#popupHead').html(head);
		$('#popupMSG').html(body);
  }  
  function checkCaptchaInput()
  {
  	var tmp = false;
  	if($('#user_captcha').val().isNumeric());
  	if($('#user_captcha').val().trim()!='' && $('#user_captcha').val().length==5)
  	{
  		if(functions=='fetchCollegeList')
  		{
			var distId = district.value;
			var boardId = helper_board_id.value;
  			$.ajax({
			 	url: 'getCollegesDist',		
			 	type: 'POST',
			 	async: false,
			 	data: {district_id:distId,board_id:boardId,college_search_type:'by_district',user_captcha:$('#user_captcha').val()},
			 	success: function(data) {
			 		$('#user_captcha').val('');
			 		if(data=='ce')
			 		{
			 			showPopUp('Error Message','Please type captcha text correctly !!!');
			 			$('#district').val('');
			 			$('#popupDiv1').hide();
			 			functions='';
			 		}
			 		else
			 			setCollegeSelectBoxData(data);
			 	},
			 	error: function(e) {
			 		console.log(e);
			 	}
	 		});
  		}
  		if(functions=='fetchCollegeSVG')
  		{
			var eiin = $('#college_id').val().trim();			
			if (eiin == "" || eiin == null || eiin.length != 6) {
				alert("Please provide valid EIIN");
				return;
			}
			$('#showSVG').prepend($('<img>', {
				id : 'theImg',
				src : 'resources/images/loading1.gif'
			}));
			$.ajax({
				  url: 'getCollegeSVGOpen',
				  type: 'POST',
				  data: {eiinCode:eiin,user_captcha:$('#user_captcha').val()},
				  async: true,
				  success: function(data) {
			 		$('#user_captcha').val('');
			 		if(data=='ce')
			 		{
			 			$('#showSVG').html('');
			 			clearThana();
			 			showPopUp('Error Message','Please type captcha text correctly !!!');
			 			$('#college_id').val('');
			 			$('#popupDiv1').hide();
			 			functions='';
			 		}
			 		else
						collegeView(data);
				  },
				  error: function(e) {
					  
				  }
			});  		
  		}
  		tmp=true;
  	}
	else
	{
		alert("Please type captcha correctly!!!");
	}
  	if(tmp)
  		$('#popupDiv1').modal('toggle');	
  }
	function numericOnly(evt)
	{
	         var charCode = (evt.which) ? evt.which : evt.keyCode
	         if(charCode==46 || (charCode>=37 && charCode<=40))
	         	return true;
	         
	         if (charCode > 31 && (charCode < 48 || charCode > 57))
	            return false;
	
	         return true;
	
	}
	</script>


	<%@ include file="/WEB-INF/jsp/template/footerLite.jsp"%>

</div>
    	<div id="popupDiv1" class="modal fade modal-ku" data-backdrop="static" data-keyboard="false" role="dialog">
    	
		  <div class="modal-dialog" >		
		    <!-- Modal content-->
		    <div class="modal-content" style="color: black; min-height: 30%;">
		      <div class="modal-header" style="padding: 5px;text-align: center;">
		         <button type="button" class="close" data-dismiss="modal">&times;</button> 
		         <h5 class="modal-title" >
		         	<font color="blue" size="5" > 
		         		<span id="popupHead1"></span>
		         	</font>
		         </h5>
		      </div>
		      <div class="modal-body" style="padding: 5px;text-align: center;">
              <div>
              	<font color="red" size="4" ><span id="popupMSG1" style="display: block;height: 10%;"></span></font>
              	<br/>
              	<img id="captchaImg" alt="Captcha Image" height="30">
		      	<input type="text" id="user_captcha"  onkeydown="return numericOnly(event)" style="width: 200px;text-align: center;" placeholder="Please enter captcha code"/>          
              </div>             
		      </div>
		      
		      <div class="modal-footer">
		      	<span id="popupFooter1">
		        	<button type="button" class="btn btn-danger" onclick="checkCaptchaInput();">Close</button>
		      	</span>
		      </div>
		    </div>
		
		  </div>
		</div>
		
		
    	<div id="popupDiv" class="modal fade modal-ku" data-backdrop="static" data-keyboard="false" role="dialog">
    	
		  <div class="modal-dialog" >		
		    <!-- Modal content-->
		    <div class="modal-content" style="color: black; min-height: 30%;">
		      <div class="modal-header" style="padding: 5px;text-align: center;">
		         <button type="button" class="close" data-dismiss="modal">&times;</button> 
		         <h5 class="modal-title" >
		         	<font color="blue" size="5" > 
		         		<span id="popupHead"></span>
		         	</font>
		         </h5>
		      </div>
		      <div class="modal-body" style="padding: 5px;text-align: center;">
              <div>
              	<font color="red" size="4" ><span id="popupMSG" style="display: block;height: 10%;"></span></font>              	      
              </div>             
		      </div>
		      
		      <div class="modal-footer">
		      	<span id="popupFooter1">
		        	<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		      	</span>
		      </div>
		    </div>
		
		  </div>
		</div>		
</body>
</html>