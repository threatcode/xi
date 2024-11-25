<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<div class="toogle_wrap">
				<div class="toggle_container">
					<p>
						<font style="color: black; font-size:20px;"><%-- DASHBOARD OF [ ${sessionScope.user.college_name} ] --%></font>				
					</p>
				</div>				
			</div>
		</div>
		<!---- body ----->		

		<div style="width: 100%">
			<div id="right_content" style="width: 25%; margin-left: 260px; float: left;">
				<h2>Total Applicants</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.totalApplicant}
				</div>			
			</div>
			<div id="right_content" style="width: 25%; float: right; margin-left: 0px; margin-right: 50px">
				<h2>Total Applications</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.totalApplication}
				</div>			
			</div>
		</div>
		
		<div style="width: 100%">
			<div id="right_content" style="width: 25%; margin-left: 260px; float: left;">
				<h2>Total Online Applicants</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.webApplicant}
				</div>			
			</div>
			<div id="right_content" style="width: 25%; float: right; margin-left: 0px; margin-right: 50px">
				<h2>Total Online Applications</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.webApplication}
				</div>			
			</div>
		</div>
		<div style="width: 100%">
			<div id="right_content" style="width: 25%; margin-left: 260px; float: left;">
				<h2>Total SMS Applicants</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.smsApplicant}
				</div>			
			</div>
			<div id="right_content" style="width: 25%; float: right; margin-left: 0px; margin-right: 50px">
				<h2>Total SMS Applications</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.smsApplication}
				</div>			
			</div>
		</div>
		
	<!---- body ----->			
	</div>


	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp" %>
</div>


<script type="text/javascript">


</script>
<%@ include file="/WEB-INF/jsp/template/footerLite.jsp" %>

</div>
</body>
</html>