<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
						
	

        <!-- SIDE MENU -->
        <div class="wrap-sidebar-content">
            <div id="skin-select">
                <a id="toggle">
                    <span class="fa icon-menu"></span>
                </a>

                <div class="skin-part">
                    <div id="tree-wrap">
                        <div id="menuwrapper" class="side-bar">
                            <ul id="menu-showhide" class="topnav">
                                <li class="devider-title">
                                    <h3>
                                        <span>Activities</span>
                                    </h3>
                                </li>
                                <c:if test="${sessionScope.role=='college'}">
                                
                                 
                                 <li>
	                                    <a class="tooltip-tip" href="collegeDashBoard" title="collegeDashBoard">
	                                        <i class="fontello-desktop-1"></i>
	                                        <span>Dashboard</span>
	                                    </a>
	                                </li>
                                  
                                     
                                  <li>
                                    <a class="tooltip-tip " href="listOfApplicant" title="listOfApplicant">
                                        <i class=" fontello-th-list"></i>
                                        <span>List of Applicant</span>
                                    </a>
                                   </li> 

		                                   <li>
		                                    <a class="tooltip-tip " href="specialQuotaStudentList" title="specialQuotaStudentList">
		                                        <i class=" fontello-doc-1"></i>
		                                        <span>SQ Approval</span>
		                                    </a>
		                                   </li>
		                                  <li>
		                                    <a class="tooltip-tip " href="educationQuotaStudentList" title="educationQuotaStudentList">
		                                        <i class=" fontello-doc-1"></i>
		                                        <span>EQ Approval</span>
		                                    </a>
		                                   </li> 
                    			 
                                 
<%--                                     <c:if test="${sessionScope.user.sqEligible=='Yes'}">
		                                   <li>
		                                    <a class="tooltip-tip " href="specialQuotaStudentList" title="specialQuotaStudentList">
		                                        <i class=" fontello-doc-1"></i>
		                                        <span>SQ Approval</span>
		                                    </a>
		                                   </li>
		                                  <li>
		                                    <a class="tooltip-tip " href="educationQuotaStudentList" title="educationQuotaStudentList">
		                                        <i class=" fontello-doc-1"></i>
		                                        <span>EQ Approval</span>
		                                    </a>
		                                   </li> 
                    			  	</c:if>     --%>     
                                   
                                    <li>
	                                    <a class="tooltip-tip " target="_blank" href="/board/resources/staticpdf/SQ_EQ_Manual.pdf" title="Quota Receive Manual">
	                                        <i class=" fontello-doc-1"></i>
	                                        <span>Quota Receive manual</span>
	                                    </a>
                                   </li>
                                   
                                <li>
                                    <a class="tooltip-tip " href="searchsqstudent" title="searchsqstudent">
                                        <i class=" fontello-doc-1"></i>
                                        <span>Add New SQ Students</span>
                                    </a>
                                   </li> 
               
                                   
                                
                                  <li>
                                    <a class="tooltip-tip " href="dateWiseApplicationStatCollege" title="dateWiseApplicationStatCollege">
                                        <i class="icon-graph-bar"></i>
                                        <span>Date-Wise Statistics.</span>
                                    </a>
                                </li>
                                
                                
                                   <li>
                                    <a class="tooltip-tip " href="shiftVersionGroupApplicationNumberOfCollege" title="shiftVersionGroupApplicationNumberOfCollege">
                                        <i class=" fontello-location-1"></i>
                                        <span>S-V-G-Application Stat.</span>
                                    </a>
                                </li>
                                
                                   <li>
                                    <a class="tooltip-tip " href="collegeSVGload" title="collegeSVGload">
                                        <i class=" fontello-location-1"></i>
                                        <span>Result Download</span>
                                    </a>
                                </li>
                               
                                 <li>
	                                    <a class="tooltip-tip " target="_blank" href="/board/resources/staticpdf/ReceiveAdmissionManual.pdf" title="Admission Receive Manual">
	                                        <i class=" fontello-doc-1"></i>
	                                        <span>Receive Manual</span>
	                                    </a>
                                   </li>
                                
                                <li>
                                    <a class="tooltip-tip " href="nonApprovedStudentListOfMerit" title="nonApprovedStudentListOfMerit">
                                        <i class=" fontello-location-1"></i>
                                        <span>Receive Admission</span>
                                    </a>
                                </li>
                                
                                 <li>
                                    <a class="tooltip-tip " href="approvedStudentListOfMerit" title="approvedStudentListOfMerit">
                                        <i class=" fontello-location-1"></i>
                                        <span>Received List</span>
                                    </a>
                                </li>
                                
                                 <li>
                                    <a class="tooltip-tip " href="totalApprovedStudentListOfMerit" title="Total Received List">
                                        <i class=" fontello-location-1"></i>
                                        <span>Total Received List</span>
                                    </a>
                                </li>
                                
                                  <li>
                                    <a class="tooltip-tip " href="admissionCancelList" title="Admission Cancel">
                                        <i class=" fontello-location-1"></i>
                                        <span>Total Cancel List</span>
                                    </a>
                                </li>
                                
                                <li>
                                    <a class="tooltip-tip " href="collegeSVGloadTotalAdmitted" title="Total Admitted List">
                                        <i class=" fontello-location-1"></i>
                                        <span>Admitted Student List</span>
                                    </a>
                                </li>
                                
                                 
                                 <li>
                                    <a class="tooltip-tip " href="collegeCourse" title="collegeCourse">
                                        <i class=" fontello-location-1"></i>
                                        <span>College Course Info.</span>
                                    </a>
                                </li>
                                
                                
                                
                                </c:if>
                                <c:if test="${sessionScope.role=='admin'}">
	                                 <li>
		                                    <a class="tooltip-tip" href="adminDashBoard" title="adminDashBoard">
		                                        <i class="fontello-desktop-1"></i>
		                                        <span>Dashboard</span>
		                                    </a>
		                               </li>
		                               
		                                 <li> 
	                                <a class="tooltip-tip " href="boardDashBoardsearhByAdmin" title="boardDashBoardsearhByAdmin">
	                                        <i class=" fontello-location-1"></i>
	                                        <span>Board Dash-Board</span>
	                                    </a>
	                                </li>
		                              
	                                   <li>
	                                    <a class="tooltip-tip " href="dateWiseApplicationStatInterBoard" title="dateWiseApplicationStatInterBoard">
	                                        <i class="icon-graph-bar"></i>
	                                        <span>Date-Wise Statistics</span>
	                                    </a>
	                                </li>
	                                
	                                 
	                                                                
	                                 <li> 
	                                <a class="tooltip-tip " href="districtUnderBoard" title="districtUnderBoard">
	                                        <i class=" fontello-location-1"></i>
	                                        <span>College Scenario</span>
	                                    </a>
	                                </li>
                                </c:if>
                                
                                    <c:if test="${sessionScope.role=='board'}">
	                                 <li>
		                                    <a class="tooltip-tip" href="boardDashBoard" title="boardDashBoard">
		                                        <i class="fontello-desktop-1"></i>
		                                        <span>Dashboard</span>
		                                    </a>
		                               </li>
		                              
	                                   <li>
	                                    <a class="tooltip-tip " href="dateWiseApplicationStatBoard" title="dateWiseApplicationStatBoard">
	                                        <i class="icon-graph-bar"></i>
	                                        <span>Date-Wise Statistics</span>
	                                    </a>
	                                </li>
	                                
	                                                                
	                                 <li> 
	                                <a class="tooltip-tip " href="districtUnderBoard4" title="districtUnderBoard4">
	                                        <i class=" fontello-location-1"></i>
	                                        <span>College Scenario</span>
	                                    </a>
	                                </li>
	                                
	                                   
                                </c:if>
                               
                               <c:if test="${sessionScope.role=='boardadmin'}"> 

	                                <li> 
	                                	<a class="tooltip-tip " href="applicantRecoveryData" title="applicantRecoveryData">
	                                        <i class=" fontello-location-1"></i>
	                                        <span>Fake Application Entry</span>
	                                    </a>
	                                </li>
	                                
                                </c:if>
                                  
                                <li>
                                    <a class="tooltip-tip " href="logout" title="login">
                                        <i class=" fontello-lock-1"></i>
                                        <span>Logout</span>
                                    </a>
                                </li>
                            </ul>
                            
                        </div>
                    </div>
                </div>
            </div>
            <!-- #/skin-select -->
            <!-- END OF SIDE MENU -->

            <!-- Breadcrumb -->
            <div class="sub-board">
                <span class="header-icon"><i class="fontello-home"></i>
                </span>
                <ol class="breadcrumb newcrumb ng-scope">
                    <li>
                        <a href="#">
                            <span>
                            </span>Home</a>
                    </li>
                    <li><a href="#">General Group</a>
                    </li>
                </ol>
                <div class="dark" style="visibility: visible;">
                    <form class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control search rounded id_search" placeholder="Search">
                        </div>
                    </form>
                </div>
            </div>
            <!-- End of Breadcrumb -->






            <!-- CONTENT -->
            <div class="wrap-fluid" id="paper-bg">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="box">
                            <div class="box-header">
                                <!-- tools box -->
                                <div class="pull-right box-tools">

                                    <span class="box-btn" data-widget="collapse"><i class="fa fa-minus"></i>
                                    </span>
                                    <!--<span class="box-btn" data-widget="remove"><i class="fa fa-times"></i>
                                    </span> -->
                                </div>
                                <h3 class="box-title">
                                <i class="fontello-doc"></i><span><!-- Blank Page --> </span>
                                </h3>
                            </div>
                          <!-- /.box-header -->
                         <div class="box-body">
                               