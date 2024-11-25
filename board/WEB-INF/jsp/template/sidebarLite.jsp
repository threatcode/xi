<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="sidebar" id="sidebar">
	<h2>Browse Activities</h2>
	<!-- style="background-color: #ededed" -->
	<ul>

	<!-- ##########################################################################################################################
		 ########################################################################################################################## -->
		<c:if test="${sessionScope.role=='badmin'}">
			<li><a class="tooltip-tip" href="boardDashBoard"> <i class="fontello-desktop-1"></i> <span>Dashboard</span>
			</a></li>
			<li><a class="tooltip-tip " href="pinretrivalBoard"> <i class=" fontello-location-1"></i>
					<span> Security Code Recovery</span>
			</a></li>
			<li><a class="tooltip-tip " href="dateWiseApplicationStatBoard"> <i class="icon-graph-bar"></i>
					<span>Date-Wise Statistics</span>
			</a></li>
			<li><a class="tooltip-tip " href="districtUnderBoard4"> <i class=" fontello-location-1"></i>
					<span>College Scenario</span>
			</a></li> 
			<li><a class="tooltip-tip " href="applicantRecoveryMobile"> <i class=" fontello-location-1"></i>
					<span>Update Mobile Number</span>
			</a></li>
			<li><a class="tooltip-tip " href="applicantRecoveryData"> <i class=" fontello-location-1"></i>
					<span>Cancel Application</span>
			</a></li>
			<li><a class="tooltip-tip " href="eiinLoad"> <i class=" fontello-location-1"></i>
					<span>College Subject</span>
			</a></li>			
			
		</c:if>	

	<!-- ##########################################################################################################################
		 ########################################################################################################################## -->
		<c:if test="${sessionScope.role=='bgeneral'}">
			<li><a class="tooltip-tip" href="boardDashBoard"> <i class="fontello-desktop-1"></i> <span>Dashboard</span>
			</a></li>
			<li><a class="tooltip-tip " href="ccPinRetrival"> <i class=" fontello-location-1"></i>
					<span> Security Code Recovery</span>
			</a></li>
			<li><a class="tooltip-tip " href="dateWiseApplicationStatBoard"> <i class="icon-graph-bar"></i>
					<span>Date-Wise Statistics</span>
			</a></li>
			<li><a class="tooltip-tip " href="districtUnderBoard4"> <i class=" fontello-location-1"></i>
					<span>College Scenario</span>
			</a></li> 
			<li><a class="tooltip-tip " href="applicantRecoveryMobile"> <i class=" fontello-location-1"></i>
					<span>Update Mobile Number</span>
			</a></li>
			<li><a class="tooltip-tip " href="applicantRecoveryData"> <i class=" fontello-location-1"></i>
					<span>Cancel Admission</span>
			</a></li>
			<li><a class="tooltip-tip " href="eiinLoad"> <i class=" fontello-location-1"></i>
					<span>College Subject</span>
			</a></li>			
			
		</c:if>	

	<!-- ##########################################################################################################################
		 ########################################################################################################################## -->
		<c:if test="${sessionScope.role=='board'}">
<%-- 			<li><a class="tooltip-tip" href="boardDashBoard"> <i class="fontello-desktop-1"></i> <span>Dashboard</span>
			</a></li> --%>
			<li><a class="tooltip-tip " href="svgLand"> <i class="icon-graph-bar"></i>
					<span>SVG Edit</span>
			</a></li>
<%-- 			<li><a class="tooltip-tip " href="ccPinRetrival"> <i class=" fontello-location-1"></i>
					<span> Security Code Recovery</span>
			</a></li>
			<li><a class="tooltip-tip " href="dateWiseApplicationStatBoard"> <i class="icon-graph-bar"></i>
					<span>Date-Wise Statistics</span>
			</a></li>
			<li><a class="tooltip-tip " href="districtUnderBoard4"> <i class=" fontello-location-1"></i>
					<span>College Scenario</span>
			</a></li> 
			<li><a class="tooltip-tip " href="applicantRecoveryMobile"> <i class=" fontello-location-1"></i>
					<span>Update Mobile Number</span>
			</a></li>
			<li><a class="tooltip-tip " href="applicantRecoveryData"> <i class=" fontello-location-1"></i>
					<span>Cancel Admission</span>
			</a></li>
			<li><a class="tooltip-tip " href="eiinLoad"> <i class=" fontello-location-1"></i>
					<span>College Subject</span>
			</a></li>	 --%>			
		</c:if>	
	<!-- ##########################################################################################################################
		 ########################################################################################################################## -->

		<c:if test="${sessionScope.role=='boardadmin'}">
			 <li><a class="tooltip-tip" href="boardDashBoard"> <i class="fontello-desktop-1"></i> <span>Dashboard</span>
			</a></li>
			<li><a class="tooltip-tip " href="applicantRecoveryMobile"> <i class=" fontello-location-1"></i>
					<span>Update Mobile Number</span>
			</a></li>
			<li><a class="tooltip-tip " href="applicantRecoveryData"> <i class=" fontello-location-1"></i>
					<span>Cancel Admission</span>
			</a></li>
			<li><a class="tooltip-tip " href="eiinLoad"> <i class=" fontello-location-1"></i>
					<span>College Subject</span>
			</a></li>			
			
			
		</c:if>	
	<!-- ##########################################################################################################################
		 ########################################################################################################################## -->

		<c:if test="${sessionScope.role=='admin'}">
			<li><a class="tooltip-tip" href="adminDashBoard"> <i class="fontello-desktop-1"></i> <span>Dashboard</span>
			</a></li>

			<li><a class="tooltip-tip" href="resultSta" target="_blank" > <i class="fontello-desktop-1"></i> <span>Applicant Choices</span>
			</a></li>

			<li><a class="tooltip-tip " href="boardDashBoardsearhByAdmin"> <i
					class=" fontello-location-1"></i> <span>Board Dash-Board</span>
			</a></li>

			<li><a class="tooltip-tip "
				href="dateWiseApplicationStatInterBoard"> <i
					class="icon-graph-bar"></i> <span>Date-Wise Statistics</span>
			</a></li>
			<li><a class="tooltip-tip " href="districtUnderBoard"> <i class=" fontello-location-1"></i>
					<span>College Scenario</span>
			</a></li>
			
			
			
			<%-- <li><a class="tooltip-tip " href="adminDashBoardPayment"> <i class=" fontello-location-1"></i>
					<span> Payment Statistics</span>
			</a></li> --%>
		</c:if>	
	<!-- ##########################################################################################################################
		 ########################################################################################################################## -->
		<c:if test="${sessionScope.role=='college'}">

<!--              <li><a class="tooltip-tip "	href="collegeresult">  -->
<!-- 			  <i class=" fontello-location-1"></i> <span>Result Download</span> -->
<!-- 			</a></li>  -->

			<c:if test="${sessionScope.user.recEligible=='Y'}">
            	<li><a class="tooltip-tip "
					href="collegeSVGloadReceiveAdmissionHome"> <i
						class=" fontello-location-1"></i> <span>Receive Admission</span>
				</a></li> 
			</c:if>
            <li><a class="tooltip-tip "
				href="collegeSVGloadReceivedAdmissionHome"> <i
					class=" fontello-location-1"></i> <span>Received Admission</span>
			</a></li>
			<li><a class="tooltip-tip "
				href="registration"> <i
					class=" fontello-location-1"></i> <span>Registration</span>
			</a></li>
			<li><a class="tooltip-tip "
				href="registrationupdate"> <i
					class=" fontello-location-1"></i> <span>Update Registration</span>
			</a></li>
			
			
			
<!-- 			<c:if test="${sessionScope.user.sqEligible=='Y'}"> -->
<!-- 				<li><a class="tooltip-tip " href="specialQuotaStudentList"> <i class=" fontello-doc-1"></i> -->
<!-- 						<span>SQ Approval</span> -->
<!-- 				</a></li> -->
<!-- 				<li><a class="tooltip-tip " href="searchsqstudent"> <i class=" fontello-doc-1"></i> <span>Add -->
<!-- 							New SQ Students</span> -->
<!-- 				</a></li> -->
<!-- 			</c:if> -->

		</c:if>

	<!-- ##########################################################################################################################
		 ########################################################################################################################## -->



		<c:if test="${sessionScope.role=='college3'}">

		</c:if>
		<c:if test="${sessionScope.role=='boardentry'}">
	 		<li><a class="tooltip-tip " href="eiinManualEntry"> <i class=" fontello-location-1"></i>
					<span>Manual Student Entry</span>
			</a></li>
	 		<li><a class="tooltip-tip " href="eiinManualShow"> <i class=" fontello-location-1"></i>
					<span>Manual Entry List</span>
			</a></li>
		</c:if>			
	<!-- ##########################################################################################################################
		 ########################################################################################################################## -->
		<c:if test="${sessionScope.role=='udc'}">
<!-- 			<li><a class="tooltip-tip " href="districtUnderBoard5"> <i class=" fontello-location-1"></i> -->
<!-- 					<span>Registration College Wise</span> -->
<!-- 			</a></li> -->
			<li><a class="tooltip-tip " href="ccPinRetrivalUdc" target="_blank" > <i class=" fontello-location-1"></i>
					<span> Security Code Recovery</span>
			</a></li>
			
			
			<li><a class="tooltip-tip " href="ccTransRetrivalUdc" target="_blank" > <i class=" fontello-location-1"></i>
					<span> Payment Info</span>
			</a></li>
			
			<%-- <li><a class="tooltip-tip" href="svgPdf" > <i class="fontello-desktop-1"></i> <span>SVG Download</span> 
 			</a></li>  --%>
 			
 			
		</c:if>		




		<li><a href="./logout.action">Logout</a></li>
	</ul>

</div>


<div class="clear"></div>