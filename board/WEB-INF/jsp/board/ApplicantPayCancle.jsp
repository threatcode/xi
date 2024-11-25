<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp"%>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2>Cancel Payment</h2>
			<div class="sidebar_section_text">
				<center>

					<!---- body ----->
					<div class="row">
						<div class="col-lg-12">
							<div class="box">

								<center>

									<div class="box_row"
										style="background-color: #4876ff;color: white;text-align: left;font-weight: bold;padding-top: 0px;font-size: 21px; width: 60%; box-shadow: 0px 0px 8px #888888; padding: 6px; border-color: #7ACBFF;	border-radius: 6px;	border-style: outset inset inset outset; border-width: 2px;">
										<center>Applicant Information</center>
									</div>
									</br>


									<table style="width:400px;font-size:12px"
										class="table table-bordered table-striped cf">
										<tr style="background-color: #f0f8ff;">
											<td>SSC ROLL:</td>
											<td><input type="text" id="ssc_roll" name="ssc_roll"
												style="width:150px;" maxlength="15" /></td>
										</tr>
										<tr>
											<td>SSC BOARD:</td>
											<td><select style="width:150px;" id="ssc_board"
												name="ssc_board">
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
										<tr style="background-color: #f0f8ff;">
											<td>PASSING YEAR:</td>
											<td><select id="ssc_year" name="ssc_year">
													<option value="none">Select Year</option>
													<option value="2020">2020</option>
													<option value="2019">2019</option>
													<option value="2018">2018</option>
													
													
											</select></td>
										</tr>
										<tr>
											<td>REGISTRATION NO.:</td>
											<td><input type="text" id="ssc_reg" name="ssc_reg"
												style="width:150px;" maxlength="15" /></td>
										</tr>

										
										<tr style="background-color: #f0f8ff;">


											<td colspan="2" align="center"><input type="button"
												onclick="searchApplicant()" name="searchApplicant"
												value="Search" style="width:150px;padding: 2px 12px;"
												class="btn btn-success" /> <input type="button"
												onclick="clearAll()" name="clear" value="Clear"
												style="width:150px;padding: 2px 12px;" class="btn btn-info" />
											</td>

										</tr>
									</table>
									<br />
									
									







								</center>

							</div>
						</div>
					
					
					</div>
					
					<!----body ------>
					<div id="loader"></div>
				</center>
				<div id="showapplicant"></div>

				<br>
			</div>
		</div>
		<br>

	</div>



	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp"%>
</div>



<script type="text/javascript">

	function clearAll() 
{
$("#ssc_roll").val("");
$("#ssc_board").val("none");
$("#ssc_year").val("none");
$("#ssc_reg").val("");



$("#p_ssc_roll_hidden").val("");
$("#p_board_id").val("");
$("#p_board_name_hidden").val("");
$("#p_passing_year_hidden").val("");
$("#p_ssc_reg").val("");


$("#loader").html('');
$("#showapplicant").html('');

}



	function searchApplicant() {

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
		
		  


	 $('#loader').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));
	 
			$.ajax({
				url : 'searchApplicantFake_pay',
				type : 'POST',
				data : {
					ssc_roll : $("#ssc_roll").val().trim(),
					ssc_board : $("#ssc_board").val().trim(),
					ssc_year : $("#ssc_year").val().trim(),
					ssc_reg : $("#ssc_reg").val().trim()
					},
				success : function(msg) {
				
				    
				    $("#loader").html('');
                    $("#showapplicant").html('');
                    $("#showapplicant").html(msg);
                    
                    
                    scrollToBottom();
					
				},
				error : function(e) {
				$("#showapplicant").html('');
				$("#showapplicant").html(msg);
				alert(e);
					
				}
			});
		}



	

	function smsSendRecoveryData() {
		document.forms["smsSendRecoveryData"].submit();
	}
	
		function scrollToBottom() {
		$('html, body').animate({
			scrollTop : $(document).height()
		}, 'slow');
	}
</script>


<script type="text/javascript">
	

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


</script>
<%@ include file="/WEB-INF/jsp/template/footerLite.jsp"%>

</div>
</body>
</html>