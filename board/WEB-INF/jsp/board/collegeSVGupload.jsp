<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp"%>
<div class="center_content">
	<div id="right_wrap">
		
		<!-- 1st Form -->
		<div id="right_content">
			

			<h2>College Upload</h2>
			<div class="sidebar_section_text">
				<center>
				<s:actionerror />
					<br />


					<div style="padding-left: 250px">
					

					
					
					<s:form action="uploadAction" method="POST" enctype="multipart/form-data">
 
						<s:file name="uploadFile" label="Choose File" size="40" />
						 
						<s:submit value="Upload" name="submit" />
					 
					</s:form>
					

					</div>

					<br />
					<hr>
					<div style="padding-left:30px">





						<div id="showSVG"></div>


						<br>
				</center>

			</div>
			
		</div>
		<br>
		
		
		<!----nasir add ----->
		<!-- 2nd Form -->
		<div id="right_content">
			
			<h2>SVG Upload</h2>
			<div class="sidebar_section_text">
				<center>
					<br />


					<div style="padding-left: 200px">
					
					<s:form id = "svgUpload" name = "svgUpload" method="post" action="uploadSVG">
					
						<div style="float: left;">
							<label for="distID">Excel File</label> <input type="text"
								name="eiinCode" id="eiinCode" />



							<button style="width:100px;padding: 1px 12px;"
								class="btn btn-info" type="button" onclick="fetchCollegeSVG()">Upload
							</button>




						</div>

					</s:form>
					</div>

					<br />
					<hr>
					<div style="padding-left:30px">





						<div id="showSVG"></div>


						<br>
				</center>

			</div>
			
		</div>
	
		<!----nasir end ------>
	</div>
	


	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp"%>
</div>
<script type='text/javascript' src="/board/resources/js/jquery.min.js"></script>

<script type="text/javascript">
	function fetchThana(distictid, thanaid) {
		$('#thanaID').html('');

		$.ajax({

			url : "searchThana",
			data : {
				districtID : distictid
			},
			type : 'POST',
			async : true,
			success : function(res) {
				//console.log(res.eiinCodeList.length);
				$('#thanaID').append(
						'<option value="none">Select Thana</option>');
				for ( var i = 0; i < res.thanaIdList.length; i++) {

					if (res.thanaIdList[i] == thanaid) {
						$('#thanaID').append(
								'<option value="'+res.thanaIdList[i]+'" selected>'
										+ res.thanaNameList[i] + '</option>');
					} else {
						$('#thanaID').append(
								'<option value="'+res.thanaIdList[i]+'" >'
										+ res.thanaNameList[i] + '</option>');
					}
				}

			}
		});

	}

	function fetchCollegeSVG() {

		$('#showSVG').prepend($('<img>', {
			id : 'theImg',
			src : '/board/resources/images/loading1.gif'
		}));

		$.ajax({
			type : 'POST',
			url : "svgShow",
			dataType : 'text',
			async : false,
			data : {

				eiinCode : $("#eiinCode").val()
			}
		}).done(function(msg) {
			$("#showSVG").html(msg);
		}).always(function() {
			//$('#sw-val-step-3').unmask();
		}).fail(function(data) {
			if (data.responseCode)
				alert(data.responseCode);
		});

	} //End of fetchApplicationInformation
</script>


<%@ include file="/WEB-INF/jsp/template/footerLite.jsp"%>


</body>
</html>