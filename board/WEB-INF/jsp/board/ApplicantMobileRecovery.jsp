<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp"%>


<script charset="UTF-8" type="text/javascript" src="/board/resources/js/application_TT.js"></script>

<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2>Applicant Mobile Number Change</h2>
			<div style="background-color: #4876ff;color: white;text-align: left;font-weight: bold;padding-top: 0px;font-size: 21px; width: 60%; box-shadow: 0px 0px 8px #888888; padding: 6px; border-color: #7ACBFF;	border-radius: 6px;	border-style: outset inset inset outset; border-width: 2px; margin: 20px auto;">
										<center>Applicant Information</center>
			</div>
			<div class="sidebar_section_text">
				<center>

					<!---- body ----->
					<div class="row">
						<div class="col-lg-12">
							<div class="box">

								<center>

									<table style="width:400px;font-size:12px"
										class="table table-bordered table-striped cf">
										<tr style="background-color: #f5fffa;">
											<td>SSC ROLL:</td>
											<td><input type="text" id="ssc_roll" name="ssc_roll"
												style="width:150px;" maxlength="15" /></td>
										</tr>
										<tr>
											<td>SSC BOARD:</td>
											<td><select style="width:150px;" id="ssc_board"
												name="ssc_board"   onchange="reloadYear(this.value)">
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

											</select></td>
										</tr>
										<tr style="background-color: #f5fffa;">
											<td>PASSING YEAR:</td>
											<td><select id="ssc_year" name="ssc_year">
													<option value="none">Select Year</option>
													<option value="2020" selected="selected">2020</option>
													<option value="2019">2019</option>
													<option value="2018">2018</option>
													
											</select></td>
										</tr>
										<tr>
											<td>REGISTRATION NO.:</td>
											<td><input type="text" id="ssc_reg" name="ssc_reg"
												style="width:150px;" maxlength="15" /></td>
										</tr>

										<tr style="background-color: #f5fffa;">
											<td>NEW MOBILE NUMBER:</td>
											<td><input type="text" maxlength="11"
												style="width:150px;" onkeyup="checkInput(this)"
												id="mobilenumber" name="mobilenumber"
												onchange="validateMobileNumber(this.value)" /></td>
										</tr>

										<tr>
											<td>RETYPE MOBILE NUMBER:</td>
											<td><input type="text" maxlength="11"
												style="width:150px;" onkeyup="checkInput(this)"
												id="retypemobilenumber" name="retypemobilenumber"
												onchange="revalidateMobileNumber(this.value)" /></td>
										</tr>

										<tr style="background-color: #f5fffa;">
											<td colspan="2" align="center"><input type="button"
												onclick="updateMobile()" name="updateMobile"
												value="Update Mobile Number"
												style="width:180px;padding: 1px 12px;" class="btn btn-success" />
												
												<input type="button"
												onclick="clearAll()" name="clear"
												value="Clear" style="width:150px;padding: 2px 12px;"
												class="btn btn-info" />
												
												</td>
										</tr>
									</table>
									<br />
									<%--  		<c:if test="${requestScope.successMessage!=''}">
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
        </c:if> --%>
								</center>

							</div>
						</div>
					</div>


					<%--   <div class="row">
    <!--                 <div class="col-lg-12">
                        <div class="box"> -->

                            <!-- /.box-header -->
                            <form action="deleteapplicantRecoveryData" method="post" id="spesialQuotaGrantForm" name="spesialQuotaGrantForm">
                            <div class="box-body table-responsive">
                                <table id="example1" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th>STUDENT NAME</th>
                                            <th>SSC ROLL</th>
                                            <th>PASSSING YEAR</th>
                                            <th>BOARD NAME</th>
                                            <th>REGISTRATION NO</th>
                                            <th>MOBILE NO.</th>
                                            <th>DELETE</th>

                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                 
			
				        <c:forEach items="${requestScope.applicantRecoverydataList}" var="applicantRecoverydata">
							<tr>
								<td>${applicantRecoverydata.applicantName}</td>
								<td>${applicantRecoverydata.sscRollNo}</td>
								<td>${applicantRecoverydata.sscPassingYear}</td>
								<td>${applicantRecoverydata.boardName}</td>
								<td>${applicantRecoverydata.registrationNumber}</td>
								<td>${applicantRecoverydata.mobilenumber}</td>


	<td>

	<input type="checkbox" class="boSelect" name="applicationID" value="${applicantRecoverydata.sscRollNo}#${applicantRecoverydata.boardID}#${applicantRecoverydata.sscPassingYear}">

	

	</td>
								

							</tr>		
						</c:forEach>
   
                                </table>
                                <div style="width:140px;float: right;right-padding:0cm">
                                  <button style="width:150px;padding: 1px 12px;" class="btn btn-danger">Delete From the List</button>
                                </div>
                            </div>
                            
                            </form>
                            <!-- /.box-body -->
<!--                         </div>
                        /.box
                    </div>-->
                </div>
 <hr>
<form action="smsSendRecoveryData" name="smsSendRecoveryData" id="smsSendRecoveryData" method="post">
 <button style="width:350px;padding: 1px 12px;" class="btn btn-success" onclick="smsSendRecoveryData()">Send SMS and Remove Already Applied Candidate</button>
 <!-- <input type="button" value="Send SMS" onclick="smsSendRecoveryData()"> -->
</form> --%>


					<!----body ------>

				</center>

			</div>
		</div>
		<br>

	</div>



	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp"%>
</div>

<%-- 			<script
				src="/board/resources/template/js/datatables/jquery.dataTables.js"
				type="text/javascript"></script>
			<script
				src="/board/resources/template/js/datatables/dataTables.bootstrap.js"
				type="text/javascript"></script>

			<script
				src="/board/resources/template/js/footable/js/footable.js?v=2-0-1"
				type="text/javascript"></script>
			<script
				src="/board/resources/template/js/footable/js/footable.sort.js?v=2-0-1"
				type="text/javascript"></script>
			<script
				src="/board/resources/template/js/footable/js/footable.filter.js?v=2-0-1"
				type="text/javascript"></script>
			<script
				src="/board/resources/template/js/footable/js/footable.paginate.js?v=2-0-1"
				type="text/javascript"></script>
			<script
				src="/board/resources/template/js/footable/js/footable.paginate.js?v=2-0-1"
				type="text/javascript"></script> --%>

<script type="text/javascript">

	function clearAll() 
{
$("#ssc_roll").val("");
$("#ssc_board").val("none");
$("#ssc_year").val("none");
$("#ssc_reg").val("");
$("#mobilenumber").val("");
$("#retypemobilenumber").val("");
}

	function updateMobile() {

		if (document.getElementById("ssc_roll").value == "") {
			alert("Please Give SSC ROLL");
			return;
		}

		if (document.getElementById("ssc_board").value == "none") {
			alert("Please Select a Board");
			return;
		}
		if (document.getElementById("ssc_year").value == "none") {
			alert("Please Select a Year");
			return;
		}

		if (document.getElementById("ssc_reg").value == "") {
			alert("Please Give Registration Number");
			return;
		}

		if (document.getElementById("mobilenumber").value == ""
				|| document.getElementById("mobilenumber").value.length < 11) {
			alert("Please give your correct mobile number");
			return;
		}

		if ((document.getElementById("retypemobilenumber").value == ""
				|| document.getElementById("retypemobilenumber").value.length < 11 || document
				.getElementById("retypemobilenumber").value != document
				.getElementById("mobilenumber").value)) {
			alert("Please give your correct Re-type mobile number");
			return;
		}

		var x = confirm("Are you sure you want to update mobile number ?");
		if (x) {
			$.ajax({
				url : 'updateMobile_TT',
				type : 'POST',
				data : {
					ssc_roll : $("#ssc_roll").val().trim(),
					ssc_board : $("#ssc_board").val().trim(),
					boardName : $("#ssc_board option:selected").text().trim(),
					ssc_year : $("#ssc_year").val().trim(),
					ssc_reg : $("#ssc_reg").val().trim(),
					mobilenumber : $("#mobilenumber").val().trim(),
				},
				success : function(response) {

					alert(response.message);
					

	                $("#ssc_roll").val("");
					$("#ssc_board").val("none");
					$("#ssc_year").val("none");
					$("#ssc_reg").val("");
					$("#mobilenumber").val("");
					$("#retypemobilenumber").val("");

				},
				error : function(e) {
					alert(response.message);

				}
			});

		}

	}

	//}

	/* 	function validateField() {

	 var isValid = true;
	 var element;
	 for ( var i = 0; i < arguments.length; i++) {
	 element = $("#" + arguments[i]);
	 if (element && $.trim(element.val()) == "") {
	 cbColor(element, "e");
	 isValid = false;
	 } else
	 cbColor(element, "v");
	 }
	 return isValid;
	 }
	 */
	function CollegeUpdateSubmit() {

		if (document.getElementById("sscRollNo").value == "") {
			alert("Please Give SSC ROLL");
			return;
		}

		if (document.getElementById("boardID").value == "none") {
			alert("Please Select a Board");
			return;
		}
		if (document.getElementById("sscPassingYear").value == "none") {
			alert("Please Select a Year");
			return;
		}

		if (document.getElementById("registrationNumber").value == "") {
			alert("Please Give Registration Number");
			return;
		}
		if (document.getElementById("mobilenumber").value == ""
				|| document.getElementById("mobilenumber").value.length < 11) {
			alert("Please give your correct mobile number");
			return;
		}

		if ((document.getElementById("retypemobilenumber").value == ""
				|| document.getElementById("retypemobilenumber").value.length < 11 || document
				.getElementById("retypemobilenumber").value != document
				.getElementById("mobilenumber").value)) {
			alert("Please give your correct Re-type mobile number");
			return;
		}

		document.forms["NewApplicantFreshDataForm"].submit();
	}

	function smsSendRecoveryData() {
		document.forms["smsSendRecoveryData"].submit();
	}
</script>

<%-- 			<script type="text/javascript">
				(function($) {
					"use strict";
					$("#example1").dataTable();
					$('#example2').dataTable({
						"bPaginate" : true,
						"bLengthChange" : false,
						"bFilter" : false,
						"bSort" : true,
						"bInfo" : true,
						"bAutoWidth" : false
					});
				})(jQuery);

				(function($) {
					"use strict";
					$('.footable-res').footable();
				})(jQuery);

				(function($) {
					"use strict";
					$('#footable-res2')
							.footable()
							.bind(
									'footable_filtering',
									function(e) {
										var selected = $('.filter-status')
												.find(':selected').text();
										if (selected && selected.length > 0) {
											e.filter += (e.filter && e.filter.length > 0) ? ' '
													+ selected
													: selected;
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
							filter : $('#filter').val()
						});
					});

					$('.filter-api').click(
							function(e) {
								e.preventDefault();

								//get the footable filter object
								var footableFilter = $('table').data(
										'footable-filter');

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
 --%>
<script type="text/javascript">
	function validateMobileNumber(mobilenumber) {
		var mob = /^(013|014|015|016|017|018|019)[0-9]{8}$/;

		if (mob.test(mobilenumber) == false) {
			alert("Invalid Mobile Number");
			document.getElementById("mobilenumber").value = "";
		}

	}

	function revalidateMobileNumber(retypemobilenumber) {
		var mob = /^(013|014|015|016|017|018|019)[0-9]{8}$/;

		if (mob.test(retypemobilenumber) == false) {
			alert("Invalid Re-Type Mobile Number");
			document.getElementById("retypemobilenumber").value = "";
		}

	}

	function checkInput(ob) {
		var invalidChars = /[^0-9]/gi
		/* var invalidChars = /^[0-9]-/; */
		if (invalidChars.test(ob.value)) {
			ob.value = ob.value.replace(invalidChars, "");
		}
	}
	function OptionValueSelect() {
		document.getElementById("ssc_board").value = '${sessionScope.user.boardId}';
	}
	window.onload = setTimeout("OptionValueSelect()", 100);

	$('#mobilenumber').bind("cut copy paste", function(e) {
		e.preventDefault();
	});
	$('#retypemobilenumber').bind("cut copy paste", function(e) {
		e.preventDefault();
	});
</script>
<%@ include file="/WEB-INF/jsp/template/footerLite.jsp"%>

</div>
</body>
</html>