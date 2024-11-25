<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp"%>


	<body>
		<div class="center_content">
			<div id="right_wrap">
				
				<!-- 1st Form -->
				<div id="right_content">
					
		
					<h2>College Upload</h2>
					<div class="sidebar_section_text">
						<center>
							<br />
		
		
							<div style="padding-left: 250px">
		 
								<h3>File uploaded successfully...!!!</h3>
			
							    File Name : <s:property value="uploadFileFileName"/> <br>
							    Content Type : <s:property value="uploadFileContentType"/> <br>
							    Temp File Name : <s:property value="uploadFile"/> 
							</div>
						</center>
					</div>
				</div>
			</div>
			<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp"%>
		</div>
		<%@ include file="/WEB-INF/jsp/template/footerLite.jsp"%>
		</div>
	</body>
</html>
 
