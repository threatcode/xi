<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="sidebar" id="sidebar">
	<h2>Browse Activities</h2>
	<!-- style="background-color: #ededed" -->
	<ul>
		<c:if test="${sessionScope.role=='college'}">
		<!-- 
		
			<li><a class="tooltip-tip "
				href="registration"
				title="Register Student"> <i
					class=" fontello-location-1"></i> <span>Registration</span>
			</a></li>
			<li><a class="tooltip-tip "
				href="registrationupdate"
				title="Update Registration"> <i
					class=" fontello-location-1"></i> <span>Update Registration</span>
			</a></li>
		
		
			<li><a class="tooltip-tip" href="collegeHome"
				title="collegeDashBoard"> <i class="fontello-desktop-1"></i> <span>Home</span>
			</a></li>
			<li><a class="tooltip-tip "	href="collegeresult" title="collegeresult"> 
			  <i class=" fontello-location-1"></i> <span>Result Download</span>
			</a></li> 
			<li><a class="tooltip-tip" href="collegeDashBoard"
				title="collegeDashBoard"> <i class="fontello-desktop-1"></i> <span>Dashboard</span>
			</a></li>
			<li><a class="tooltip-tip " href="collegeCourse"
				title="collegeCourse"> <i class=" fontello-location-1"></i> <span>College Course Info.</span>
			</a></li>
		    <li><a class="tooltip-tip " href="dateWiseApplicationStatCollege" title="dateWiseApplicationStatCollege"> <i
					class="icon-graph-bar"></i> <span>Date-Wise Statistics</span>
			</a></li>
			<li><a class="tooltip-tip " href="shiftVersionGroupApplicationNumberOfCollege" title="shiftVersionGroupApplicationNumberOfCollege"> <i
					class=" fontello-location-1"></i> <span>S-V-G Application Stat.</span>
			</a></li>
			<c:if test="${sessionScope.user.sqEligible=='Y'}">
				<li><a class="tooltip-tip " href="specialQuotaStudentList"
					title="specialQuotaStudentList"> <i class=" fontello-doc-1"></i>
						<span>SQ Approval</span>
				</a></li>
				<li><a class="tooltip-tip " href="searchsqstudent"
					title="searchsqstudent"> <i class=" fontello-doc-1"></i> <span>Add
							New SQ Students</span>
				</a></li>
			</c:if>
			<li><a class="tooltip-tip "
				href="registrationupdate"
				title="Update Registration"> <i
					class=" fontello-location-1"></i> <span>Print Registration</span>
			</a></li>

			<li><a class="tooltip-tip " href="educationQuotaStudentList"
				title="educationQuotaStudentList"> <i class=" fontello-doc-1"></i>
					<span>EQ Approval</span>
			</a>
			<li><a class="tooltip-tip " target="_blank"
				href="/board/resources/staticpdf/SQ_EQ_Manual.pdf"
				title="Quota Receive Manual"> <i class=" fontello-doc-1"></i> <span>Quota
						Receive manual</span>
			</a></li> 
			<li><a class="tooltip-tip " href="listOfApplicant"
				title="listOfApplicant"> <i class=" fontello-th-list"></i> <span>List
						of Applicant</span>
			</a></li>              
            <li><a class="tooltip-tip "
				href="nonApprovedStudentListOfMerit2"
				title="nonApprovedStudentListOfMerit2"> <i
					class=" fontello-location-1"></i> <span>Receive Admission -2</span>
			</a></li>
			<li><a class="tooltip-tip " target="_blank"
				href="/board/resources/staticpdf/ReceiveAdmissionManual.pdf"
				title="Admission Receive Manual"> <i class=" fontello-doc-1"></i>
					<span>Receive Manual</span>
			</a></li>
			<li><a class="tooltip-tip "
				href="totalApprovedStudentListOfMerit" title="Total Received List">
					<i class=" fontello-location-1"></i> <span>Total Received
						List</span>
			</a></li>
			<li><a class="tooltip-tip " href="collegeSVGload"
				title="collegeSVGload"> <i class=" fontello-location-1"></i> <span>Result
						Download</span>
			</a></li> 
			<li><a class="tooltip-tip "
				href="collegeSVGloadReceiveAdmissionHome"
				title="collegeSVGloadReceiveAdmissionHome"> <i
					class=" fontello-location-1"></i> <span>College Approval</span>
			</a></li> 
			<li><a class="tooltip-tip "
				href="collegeSVGloadReceivedAdmissionHome"
				title="collegeSVGloadReceivedAdmissionHome"> <i
					class=" fontello-location-1"></i> <span>Approved List</span>
			</a></li> 			
			<li><a class="tooltip-tip "
				href="collegeSVGloadReceivedAdmissionHome"
				title="collegeSVGloadReceivedAdmissionHome"> <i
					class=" fontello-location-1"></i> <span>Cancel Admission</span>
			</a></li>
           <li><a class="tooltip-tip " href="changeSVGHome"
				title="Student Group Change"> <i class=" fontello-location-1"></i>
					<span>Student Group Change</span>
			</a></li>
	 		<li><a class="tooltip-tip " href="collegeSVGloadReceivedAdmissionHome"
					title="collegeSVGloadReceivedAdmissionHome"> <i
						class=" fontello-location-1"></i> <span>Approved List</span>
			</a></li>
			<c:if test="${sessionScope.hasManualEntry=='Yes'}">
					<li><a class="tooltip-tip "
						href="registrationManual"
						title="Register Student"> <i
							class=" fontello-location-1"></i> <span>Registration From Manual Entry</span>
					</a></li>
					<li><a class="tooltip-tip "
						href="regpdateManual"
						title="Update Registration"> <i
							class=" fontello-location-1"></i> <span>Update Registration From Manual Entry</span>
					</a></li>
			</c:if>	
	 		<li><a class="tooltip-tip " href="collegeSVGloadReceivedAdmissionHome"
					title="collegeSVGloadReceivedAdmissionHome"> <i
						class=" fontello-location-1"></i> <span>Cancel Panel</span>
				</a></li>
			<li><a class="tooltip-tip " href="admissionCancelList"
				title="Admission Cancel"> <i class=" fontello-location-1"></i> <span>
						Cancel List</span>
			</a></li>

			<li><a class="tooltip-tip " href="collegeSVGloadTotalAdmitted"
				title="Total Admitted List"> <i class=" fontello-location-1"></i>
					<span>Admitted Student List</span>
			</a></li> 
			
		-->
			<li><a class="tooltip-tip "
				href="registration"
				title="Register Student"> <i
					class=" fontello-location-1"></i> <span>Registration</span>
			</a></li>
			<li><a class="tooltip-tip "
				href="registrationupdate"
				title="Update Registration"> <i
					class=" fontello-location-1"></i> <span>Update Registration</span>
			</a></li>
			<li><a class="tooltip-tip "
				href="registrationupdate"
				title="Update Registration"> <i
					class=" fontello-location-1"></i> <span>Print Registration</span>
			</a></li>




		<c:if test="${sessionScope.role=='admin'}">
			<li><a class="tooltip-tip" href="adminDashBoard"
				title="adminDashBoard"> <i class="fontello-desktop-1"></i> <span>Dashboard</span>
			</a></li>

			<li><a class="tooltip-tip " href="boardDashBoardsearhByAdmin"
				title="boardDashBoardsearhByAdmin"> <i
					class=" fontello-location-1"></i> <span>Board Dash-Board</span>
			</a></li>

			<li><a class="tooltip-tip "
				href="dateWiseApplicationStatInterBoard"
				title="dateWiseApplicationStatInterBoard"> <i
					class="icon-graph-bar"></i> <span>Date-Wise Statistics</span>
			</a></li>



			<li><a class="tooltip-tip " href="districtUnderBoard"
				title="districtUnderBoard"> <i class=" fontello-location-1"></i>
					<span>College Scenario</span>
			</a></li>
			
			<li><a class="tooltip-tip " href="ccPinRetrival"
				title="districtUnderBoard"> <i class=" fontello-location-1"></i>
					<span> Security Code Recovery</span>
			</a></li>
			
			<li><a class="tooltip-tip " href="adminDashBoardPayment"
				title="adminDashBoardPayment"> <i class=" fontello-location-1"></i>
					<span> Payment Statistics</span>
			</a></li>
			
			
		</c:if>
		<c:if test="${sessionScope.role=='college3'}">
			<li><a class="tooltip-tip " href="collegeDEForm" title="collegeDEForm"> <i
					class=" fontello-location-1"></i><span>Data Entry Form</span>
			</a></li>
			<li><a class="tooltip-tip " href="collegeDataEntry" title="collegeDataEntry"> <i
					class=" fontello-location-1"></i><span>Student List</span>
			</a></li>
<!-- 			
			<li><a class="tooltip-tip "
				href="registrationManual"
				title="Register Student"> <i
					class=" fontello-location-1"></i> <span>Registration</span>
			</a></li>
			<li><a class="tooltip-tip "
				href="regpdateManual"
				title="Update Registration"> <i
					class=" fontello-location-1"></i> <span>Update Registration</span>
			</a></li>
 -->						
		</c:if>



		<c:if test="${sessionScope.role=='board'}">
		
			<li><a class="tooltip-tip " href="applicantRecoveryData"
				title="applicantRecoveryData"> <i class=" fontello-location-1"></i>
					<span>Cancel Application</span>
			</a></li>
			
			 <li><a class="tooltip-tip" href="boardDashBoard"
				title="boardDashBoard"> <i class="fontello-desktop-1"></i> <span>Dashboard</span>
			</a></li>


			<li><a class="tooltip-tip " href="svgLand"
				title="dateWiseApplicationStatBoard"> <i class="icon-graph-bar"></i>
					<span>SVG Edit</span>
			</a></li>
			
			<li><a class="tooltip-tip " href="ccPinRetrival"
				title="districtUnderBoard"> <i class=" fontello-location-1"></i>
					<span> Security Code Recovery</span>
			</a></li>

			<li><a class="tooltip-tip " href="dateWiseApplicationStatBoard"
				title="dateWiseApplicationStatBoard"> <i class="icon-graph-bar"></i>
					<span>Date-Wise Statistics</span>
			</a></li>


			<li><a class="tooltip-tip " href="districtUnderBoard4"
				title="districtUnderBoard4"> <i class=" fontello-location-1"></i>
					<span>College Scenario</span>
			</a></li> 


		</c:if>

		<c:if test="${sessionScope.role=='boardentry'}">
	 		<li><a class="tooltip-tip " href="eiinManualEntry"
				title="Manual Student Entry"> <i class=" fontello-location-1"></i>
					<span>Manual Student Entry</span>
			</a></li>
	 		<li><a class="tooltip-tip " href="eiinManualShow"
				title="Manual Entry List"> <i class=" fontello-location-1"></i>
					<span>Manual Entry List</span>
			</a></li>
		</c:if>			



		<c:if test="${sessionScope.role=='boardadmin'}">
		 
		  
		  <li><a class="tooltip-tip " href="districtUnderBoard5"
				title="applicantRecoveryData"> <i class=" fontello-location-1"></i>
					<span>Registration College Wise</span>
			</a></li>
			
		
<%-- 		<li><a class="tooltip-tip " href="applicantRecoveryData"
				title="applicantRecoveryData"> <i class=" fontello-location-1"></i>
					<span>Cancel Application</span>
			</a></li>
			
			
			<li><a class="tooltip-tip " href="applicantRecoveryMobile"
				title="applicantRecoveryMobile"> <i class=" fontello-location-1"></i>
					<span>Update Mobile Number</span>
			</a></li>
		
		  	<li><a class="tooltip-tip " href="pinretrivalBoard"
				title="applicantQuotaEdit"> <i class=" fontello-location-1"></i>
					<span>Security Code Retrieval</span>
			</a></li>  --%>
  <!-- 		
			<li><a class="tooltip-tip " href="pinretrivalBoard"
				title="pinretrivalBoard"> <i class=" fontello-location-1"></i>
					<span>PIN Recovery</span>
			</a></li>
			


			
			
				<li><a class="tooltip-tip " href="admissionCancelBoardHome"
					title="admissionCancelBoardHome"> <i class=" fontello-location-1"></i>
						<span>Admission Cancel</span>
				</a></li>
	 -->			
				
				
			<%-- <li><a class="tooltip-tip " href="admissionReReceiveBoardHome"
				title="admissionReReceiveBoardHome"> <i class=" fontello-location-1"></i>
					<span>Admission Re-Receive</span>
			</a></li>


 
 			
			
			 --%>
			
<!--			
			<li><a class="tooltip-tip " href="applicantDelP"
				title="applicantDelP"> <i class=" fontello-location-1"></i>
					<span>Cancel Payment</span>
			</a></li>
 		

			

			<li><a class="tooltip-tip" href="boardDashBoard"
				title="boardDashBoard"> <i class="fontello-desktop-1"></i> <span>Dashboard</span>
			</a></li>

			<li><a class="tooltip-tip " href="dateWiseApplicationStatBoard"
				title="dateWiseApplicationStatBoard"> <i class="icon-graph-bar"></i>
					<span>Date-Wise Statistics</span>
			</a></li>
 
 			<li><a class="tooltip-tip " href="applicantQuotaEdit"
				title="applicantQuotaEdit"> <i class=" fontello-location-1"></i>
					<span>Applicant Quota Edit</span>
			</a></li> 
 -->
 
 
			<%-- <li><a class="tooltip-tip " href="districtUnderBoard4"
				title="districtUnderBoard4"> <i class=" fontello-location-1"></i>
					<span>College Scenario</span>
			</a></li> --%>
			<li><a class="tooltip-tip " href="eiinLoad"
				title="College Subject"> <i class=" fontello-location-1"></i>
					<span>College Subject</span>
			</a></li>			


		</c:if>
		<li><a href="./logout.action">Logout</a></li>
	</ul>

</div>


<div class="clear"></div>