<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

				<center>
					<table style="width: 400px; font-size: 14px"
						class="table table-bordered table-striped cf">
						<tr>
							<td style="color: black;">
								<strong>Shift </strong>
							</td>
							<td style="color: black;">
								<strong>Version</strong>
							</td>
							<td style="color: black;">
								<strong>Group</strong>
							</td>
						</tr>

						<tr>
							<td>
								<select name="shiftId" id="shiftId">
									<option value="none" selected>
										Select Shift
									</option>
									<c:forEach items="${shiftList}" var="shift">
										<option value="${shift.shiftId}">
											${shift.shiftName}
										</option>
									</c:forEach>

								</select>
							</td>

							<td>
								<select name="versionId" id="versionId">
									<option value="none" selected>
										Select Version
									</option>
									<c:forEach items="${versionList}" var="version">
										<option value="${version.versionId}">
											${version.versionName}
										</option>
									</c:forEach>
								</select>
							</td>

							<td>
								<select name="groupId" id="groupId" onchange="changeGroup()">
									<option value="none" selected>
										Select Group
									</option>
									<c:forEach items="${groupList}" var="group">
										<option value="${group.groupId}">
											${group.groupName}
										</option>
									</c:forEach>

								</select>
							</td>
						</tr>
					</table>
				<center>
					<table style="width: 400px; font-size: 14px"
						class="table table-bordered table-striped cf">
						<tr>
							<td style="color: black;">
								<strong>Roll </strong>
							</td>
							<td style="color: black;">
								<strong>Board</strong>
							</td>
							<td style="color: black;">
								<strong>Year</strong>
							</td>
							<td style="color: black;">
								<strong>Registration</strong>
							</td>
							<s:if test="!(#request.userInfo.eiin.equalsIgnoreCase('131962') || #request.userInfo.eiin.equalsIgnoreCase('108274') || #request.userInfo.eiin.equalsIgnoreCase('108259') )">
								<td style="color: black;">
									<strong>Action</strong>
								</td>
							</s:if>
						</tr>

						<tr>
							<td>
								<input type="text" id="roll_no" name="roll_no">
							</td>
							<td>
								<select name="board_id" id="board_id">
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

							<td>
								<select name="passing_year" id="passing_year" >
								     <option value="">Select Year</option>
								     <option value="2020" selected="selected">2020</option>
								     <option value="2019">2019</option>
								     <option value="2018">2018</option>
								</select>
							</td>
							<td>
								<input type="text" id="reg_no" name="reg_no">
							</td>
							<s:if test="!(#request.userInfo.eiin.equalsIgnoreCase('131962') || #request.userInfo.eiin.equalsIgnoreCase('108274') || #request.userInfo.eiin.equalsIgnoreCase('108259') )">
								<td>
									<input type="submit" style="width:140px;padding: 1px 12px;" class="btn btn-info" onclick="return searchStudent()" value="Show Student" >
								</td>
							</s:if>
							 
						</tr>
					</table>
					<div id="loader1"></div>
					<div id="showapplicant1"></div>
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
		        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		
		  </div>
		</div>
    <!-- Popup modal div end -->

<%--<c:if test="${sessionScope.user.mobileVerifiedYN=='N'}">
	<script type="text/javascript">
    $(window).load(function(){
        $('#myModal').modal('show');
    });
</script>
</c:if>
--%>


<script type="text/javascript">
function Signout(){
	window.location = "logout";
}

function newOtpRequest(){
	if(!validateMobileNumber($("#nMobile").val())){
		alert("Invalid mobile number");
		$("#nMobile").val()== '';
		return;
	}
	if(!validateMobileNumber($("#retypeMobile").val())){
		alert("Invalid re-typed mobile number");
		$("#retypeMobile").val()== '';
		return;
	}
	if($("#nMobile").val() == $("#retypeMobile").val()){
		$.ajax({
		 	url: 'saveMobileSendOtp',	
		 	data:{mobile:$("#retypeMobile").val()},	
		 	type: 'POST',
		 	success: function(data) {
		 		alert(data);
		 	},
		 	error: function(e) {
		 	 
		 	}
		});
	} else 
		alert("Mobile numbers do not match");		 		
}
function validateOtp(){
	if($("#otp").val()=="")
		return;
	$.ajax({
		 	url: 'validateCollMobileOtp',
		 	data:{
		 		otp:	$("#otp").val(),
		 		mobile:	$("#retypeMobile").val()
		 	},		
		 	type: 'POST',
		 	success: function(data) {
		 		if(data=="valid"){
		 		    $('#myModal').modal('hide');
		 		    window.location='collegeDashBoard';	
		 		}
		 		else
		 			alert(data);
		 	},
		 	error: function(e) {
		 	 
		 	}
	 });
}

function validateMobileNumber(mobileNumber) {
    var mob = /^(013|014|015|016|017|018|019)[0-9]{8}$/;

    if (mob.test(mobileNumber) == false) {
      return false;
    }
    return true;
  }
</script>

<script type="text/javascript">
	
    function searchStudent() {

       if($("#shiftId").val()=="none"){
            alert("Select a Shift.");return false;
        }
        if($("#versionId").val()=="none"){
            alert("Select a version.");return false;
        }
		if($("#groupId").val()=="none"){
            alert("Select a group.");return false;
        }
        if($("#roll_no").val().trim()==""){
            alert("Please type a roll no.");return false;
        }
        if($("#board_id").val()==""){
            alert("Select a board.");return false;
        }
		if($("#passing_year").val()==""){
            alert("Select a passing year.");return false;
        }
		if($("#reg_no").val()==""){
            alert("Select a registration no.");return false;
        }
        $("#showapplicant1").html('');
         $('#loader1').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "showStudentManual",
            dataType: 'text',
            async   : false,
            data    : {

                eiinCode:"Hossain" ,pShift_id: $("#shiftId").val(),pVersion_id: $("#versionId").val(),roll_no: $("#roll_no").val(),
                board_id: $("#board_id").val(),passing_year: $("#passing_year").val(),reg_no: $("#reg_no").val(),pGroup_id: $("#groupId").val(),
            }
        }).done(function (msg) {
        			var tmp11 = 'Shift : ' + $('#shiftId').find('option:selected').text()+ '<br>';
        			tmp11 += 'Version : ' + $("#versionId option:selected").text()+ '<br>';
        			tmp11 += 'Group : ' + $("#groupId option:selected").text()+ '<br><br>';
        			document.getElementById('modelWindowHead').innerHTML = 'Student Information';
					document.getElementById('modelWindowBody').innerHTML = tmp11 + msg; 
	               	$('#modelWindow').modal('show');
                    $("#loader1").html('');
                    //$("#showapplicant1").html('');
                    //$("#showapplicant1").html(msg);
                })
                .always(function () {
                    //$('#sw-val-step-3').unmask();
                })
                .fail(function (data) {
                    if (data.responseCode)
                        alert(data.responseCode);
                });
                
      }
      
      function changeGroup(){
      	$("#groupName").val($("#groupId option:selected").text());
      	//alert($("#groupName").val());
      }
      
	function addStudent() {

       if($("#shiftId").val()=="none"){
            alert("Select a Shift.");return false;
        }
        if($("#versionId").val()=="none"){
            alert("Select a version.");return false;
        }
		if($("#groupId").val()=="none"){
            alert("Select a group.");return false;
        }
        if($("#mnumber").val()==""){
            alert("Select type mobile number.");return false;
        }
        var mn = $("#mnumber").val();
        if ( mn.length < 11 || !(mn.startsWith("015") || mn.startsWith("016") || mn.startsWith("017") || mn.startsWith("018") || mn.startsWith("019"))){
        	alert("Select type mobile number correctly.");return false;
        }
        
        $("#showapplicant1").html('');
         $('#loader1').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "addStudentBoard",
            dataType: 'text',
            async   : false,
            data    : {

                eiinCode:"Hossain",eiin: currentEiin,shift_id: $("#shiftId").val(),version_id: $("#versionId").val(),roll_no: $("#roll_no").val(),
                board_id: $("#board_id").val(),passing_year: $("#passing_year").val(),group_id: $("#groupId").val(),mnumber: $("#mnumber").val(),
            }
        }).done(function (msg) {
        			$('#modelWindow').modal('hide');
                    $("#loader1").html('');
                    $("#showapplicant1").html('');
                    $("#showapplicant1").html(msg);
                })
                .always(function () {
                    //$('#sw-val-step-3').unmask();
                })
                .fail(function (data) {
                    if (data.responseCode)
                        alert(data.responseCode);
                });
                
      }

</script>
