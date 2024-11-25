<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>
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

		<div style="width: 100%">
			<div id="right_content" style="width: 30%; margin-left: 260px; float: left;">
				<h2>Total Applicant Web Payment</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.totalweb}
				</div>			
			</div>
			
			<div id="right_content" style="width: 30%; float: right; margin-left: 0px; margin-right: 50px">
				<h2>Total Applicant SMS Payment</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.totalsms}
				</div>			
			</div>
		</div>
		
		<div style="width: 100%">
			<div id="right_content" style="width: 30%; margin-left: 260px; float: left;">
				<h2>Total Payment By Teletalk (Web)</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.totalTTweb}
				</div>			
			</div>
			<div id="right_content" style="width: 30%; float: right; margin-left: 0px; margin-right: 50px">
				<h2>Total Payment By Sure Cash</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.totalSC}
				</div>			
			</div>
		</div>
		
			<div style="width: 100%">
			<div id="right_content" style="width: 30%; margin-left: 260px; float: left;">
				<h2>Total Payment By BKash</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.totalBkash}
				</div>			
			</div>
			<div id="right_content" style="width: 30%; float: right; margin-left: 0px; margin-right: 50px">
				<h2>Total Payment By GP</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.totalGP}
				</div>			
			</div>
		</div>
		
		
		
		
		
		
		<%-- <div style="width: 100%">
			<div id="right_content" style="width: 30%; margin-left: 260px; float: left;">
				<h2>All board</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.allboard}
				</div>			
			</div>			
		</div> 
		
		<div style="width: 100%">
			<div id="right_content" style="width: 30%; margin-left: 260px; float: left;">
				<h2>Total Payment DHAKA BOARD</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.dhaka}
				</div>			
			</div>
			<div id="right_content" style="width: 30%; float: right; margin-left: 0px; margin-right: 50px">
				<h2>Total Payment COMILLA BOARD</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.comilla}
				</div>			
			</div>
		</div>
		
		<div style="width: 100%">
			<div id="right_content" style="width: 30%; margin-left: 260px; float: left;">
				<h2>Total Payment RAJSHAHI BOARD</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.rajshahi}
				</div>			
			</div>
			<div id="right_content" style="width: 30%; float: right; margin-left: 0px; margin-right: 50px">
				<h2>Total Payment JESSORE BOARD</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.jessore}
				</div>			
			</div>
		</div>
		
		<div style="width: 100%">
			<div id="right_content" style="width: 30%; margin-left: 260px; float: left;">
				<h2>Total Payment CHITTAGONG BOARD</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.chittagong}
				</div>			
			</div>
			<div id="right_content" style="width: 30%; float: right; margin-left: 0px; margin-right: 50px">
				<h2>Total Payment BARISAL BOARD</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.barisal}
				</div>			
			</div>
		</div>
		
		<div style="width: 100%">
			<div id="right_content" style="width: 30%; margin-left: 260px; float: left;">
				<h2>Total Payment SYLHET BOARD</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.sylhet}
				</div>			
			</div>
			<div id="right_content" style="width: 30%; float: right; margin-left: 0px; margin-right: 50px">
				<h2>Total Payment DINAJPUR BOARD</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.dinajpur}
				</div>			
			</div>
		</div>
		
		<div style="width: 100%">
			<div id="right_content" style="width: 30%; margin-left: 260px; float: left;">
				<h2>Total Payment MADRASHA BOARD</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.madrasha}
				</div>			
			</div>
			<div id="right_content" style="width: 30%; float: right; margin-left: 0px; margin-right: 50px">
				<h2>Total Payment BTEB</h2>
				<div class="sidebar_section_text" style="font-size: 30px" align="center">
					${requestScope.bteb}
				</div>			
			</div>
		</div>
		
		
		--%>
		
		
		
		
		
	<!---- body ----->			
	</div>


	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp" %>
</div>

<%@ include file="/WEB-INF/jsp/template/footerLite.jsp" %>

</div>
</body>
</html>