<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp"%>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2>Admission Cancel</h2>
			<div class="sidebar_section_text">
				<center>

					<!---- body ----->
					<div class="row">
						<div class="col-lg-12">
							<div class="box">

								<center>


			           <div class="box_row" style="background-color: #7a378b;color: white;text-align: left;font-weight: bold;padding-top: 0px;font-size: 16px;height: 22px;">
							<center>:: Applicant Information ::</center>
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
												onclick="pinRetrival()" name="searchApplicant"
												value="Search" style="width:150px;padding: 2px 12px;"
												class="btn btn-success" /> 
												
												<input type="button"
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
				<center>
				<div id="successResultDiv">
				</div>
				</center>

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


$("#loader").html('');
$("#successResultDiv").html('');

}



function scrollToBottom(){
	$('html, body').animate({scrollTop:$(document).height()}, 'slow');
}
    function pinRetrival() {
       
       $('#successResultDiv').html('');

       if($("#ssc_roll").val()==""){
            alert("Give Your SSC Roll.");return;
        }
        if($("#ssc_reg").val()==""){
            alert("Give Your SSC Registration No.");return;
        }
        
       if($("#ssc_board").val()=="none"){
            alert("Select Your SSC Board.");return;
        }
        if($("#ssc_year").val()=="none"){
            alert("Select Your SSC Passing Year.");return;
        }


         $('#loader').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "cancelAdmissionSearch",
            dataType: 'text',
            async   : false,
            data    : {eiinCode:"Hossain" ,ssc_roll: $("#ssc_roll").val().trim(),ssc_reg: $("#ssc_reg").val().trim(),ssc_board: $("#ssc_board").val().trim(),
                       ssc_year: $("#ssc_year").val().trim()}

        }).done(function (msg) {
        
                $('#loader').html('');
                $('#successResultDiv').html('');
                $("#successResultDiv").html(msg);
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



	
		function scrollToBottom() {
		$('html, body').animate({
			scrollTop : $(document).height()
		}, 'slow');
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