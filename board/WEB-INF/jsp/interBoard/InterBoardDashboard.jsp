<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>


<script type="text/javascript">
  var timeout = setTimeout("location.reload(true);",300000);
  function resetTimeout() {
    clearTimeout(timeout);
    timeout = setTimeout("location.reload(true);",300000);
  }
</script>


<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<div class="toogle_wrap">
				<div class="toggle_container">
					<p>
						<font style="color: black; font-size:20px;">${sessionScope.user.college_name} Dash-Board.</font>				
					</p>
				</div>				
			</div>
		</div>
		<!---- body ----->		


<%-- 

		<div style="width: 100%">
			<div id="right_content" style="width: 30%; margin-left: 260px; float: left;">
				<h2>Total Applicants</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.totalApplicant}
				</div>			
			</div>
			<div id="right_content" style="width: 30%; float: right; margin-left: 0px; margin-right: 50px">
				<h2>Total Applications</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.totalApplication}
				</div>			
			</div>
		</div>
		
		<div style="width: 100%">
			<div id="right_content" style="width: 30%; margin-left: 260px; float: left;">
				<h2>Total Web Applicants</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.webApplicant}
				</div>			
			</div>
			<div id="right_content" style="width: 30%; float: right; margin-left: 0px; margin-right: 50px">
				<h2>Total Web Applications</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.webApplication}
				</div>			
			</div>
		</div>
		<div style="width: 100%">
			<div id="right_content" style="width: 30%; margin-left: 260px; float: left;">
				<h2>Total SMS Applicants</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.smsApplicant}
				</div>			
			</div>
			<div id="right_content" style="width: 30%; float: right; margin-left: 0px; margin-right: 50px">
				<h2>Total SMS Applications</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.smsApplication}
				</div>			
			</div>
		</div>
		 --%>
		
		
		
		
			<div style="width: 100%">
			
			
				<div id="right_content" style="width: 30%; margin-left: 260px; float: left;">
					<h2>Total Applicants</h2>
					<div class="sidebar_section_text" style="font-size: 30px" align="center">
						${requestScope.totalApplicant}
					</div>			
				</div>
			
				<%-- <div id="right_content" style="width: 30%; float: right; margin-left: 0px; margin-right: 50px">
					<h2>Total Application</h2>
					<div class="sidebar_section_text" style="font-size: 30px" align="center">
						${requestScope.totalApplication}
					</div>			
				</div> --%>
			
			
			
				<div id="right_content" style="width: 30%; float: right; margin-left: 0px; margin-right: 50px">
					<h2 style="background-color: #bae6c3">Total Payment</h2>
					<div class="sidebar_section_text" style="font-size: 30px" align="center">
						${requestScope.totalpayment}
					</div>			
				</div>
			
			
		</div>
		
		
		
		<%-- <div style="width: 100%">
			
			
				<div id="right_content" style="width: 30%; margin-left: 550px;">
				<h2 style="background-color: #bae6c3">Total Payment</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.totalpayment}
				</div>			
			</div>
			
			
		</div> --%>
		
		
		
		<div style="width: 100%">			
			
				<div id="right_content" style="width: 30%; margin-left: 260px; float: left;">
				<h2  style="background-color: #e6beba">Teletalk</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.teletalk}
				</div>			
			</div>
			
			<div id="right_content" style="width: 30%; float: right; margin-left: 0px; margin-right: 50px">
				<h2 style="background-color: #e6beba">Sonali Bank</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.sbl}
				</div>			
			</div>
			
		</div>
		
		<div style="width: 100%">			
			
				<div id="right_content" style="width: 30%; margin-left: 260px; float: left;">
				<h2  style="background-color: #e6beba">Bkash</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.bkash}
				</div>			
			</div>
			
			<div id="right_content" style="width: 30%; float: right; margin-left: 0px; margin-right: 50px">
				<h2 style="background-color: #e6beba">Nagad</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.nagad}
				</div>			
			</div>
			
		</div>
		
		<div style="width: 100%">			
			
				<div id="right_content" style="width: 30%; margin-left: 260px; float: left;">
				<h2  style="background-color: #e6beba">Sure Cash</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.surecash}
				</div>			
			</div>
			
			<div id="right_content" style="width: 30%; float: right; margin-left: 0px; margin-right: 50px">
				<h2 style="background-color: #e6beba">Rocket</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.rocket}
				</div>			
			</div>
			
		</div>
		
		
		
	<!---- body ----->			
	</div>


	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp" %>
</div>

<%@ include file="/WEB-INF/jsp/template/footerLite.jsp" %>

</div>
</body>
</html>