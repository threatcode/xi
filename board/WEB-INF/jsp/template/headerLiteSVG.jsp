<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>XICA 2020: Home</title>
<link rel="icon" type="image/x-icon" href="resources/_images/favicon.ico"/>
<link href="resources/css/college.css" rel="stylesheet" />
<link href='resources/css/font.css' rel='stylesheet' type='text/css' />
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="resources/css/dataTables.bootstrap.css" />

<script charset="UTF-8" type="text/javascript" src="resources/js/svg_application_nasir.js"></script>
<%-- <script type="text/javascript" src="resources/js/jquery.min.js"></script> --%>
<script type="text/javascript" src="resources/js/defiant-latest.min.js"></script>
<%-- <script type="text/javascript" src="resources/js/common.js"></script> --%>
<%-- <script type="text/javascript" language="javascript" src="resources/js/jquery-1.11.1.min.js"></script> --%>
<script type="text/javascript" language="javascript" src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript" language="javascript" src="resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript" src="resources/js/dataTables.bootstrap.js"></script>
<%-- <script type="text/javascript" language="javascript" src="resources/js/bootbox.js"></script> --%>

<link href="resources/_stylesheet/RequiredContentDesign.css" rel="stylesheet" />
<link href="resources/_stylesheet/ApplicantRequiredContentDesign.css" rel="stylesheet" />
</head>
<body>
<div id="panelwrap">
  	
	<div class="header">
    <div class="title"><a href="javascript:void(0)">
                    <c:if test="${sessionScope.role=='board'}">
                      		${sessionScope.user.boardName} 
                    </c:if>
                    <c:if test="${sessionScope.role=='boardadmin'}">
                      		${sessionScope.user.boardName} 
                    </c:if>
					<c:if test="${sessionScope.role=='boardentry'}">
                      		${sessionScope.user.boardName} 
                    </c:if>
					<c:if test="${sessionScope.role=='admin'}">
                      		${sessionScope.user.college_name}
                    </c:if>
                    <c:if test="${sessionScope.role=='udc'}">
                      		${sessionScope.user.userid}
                    </c:if>
					</a>    
    
     </div>    
<!--     <div class="header_right">Welcome <b> -->
<!--  					<c:if test="${sessionScope.role=='college'}"> -->
<!--                           ${sessionScope.user.college_name}(${sessionScope.user.eiin}), Mobile No. ${sessionScope.user.collegeMobile}                           -->
<!--                     </c:if> -->
<!--  					<c:if test="${sessionScope.role=='college3'}"> -->
<!--                           ${sessionScope.user.college_name}(${sessionScope.user.eiin}), Mobile No. ${sessionScope.user.collegeMobile}                           -->
<!--                     </c:if> -->
<!--                     <c:if test="${sessionScope.role=='board'}"> -->
<!--                       		${sessionScope.user.boardName}  -->
<!--                     </c:if> -->
<!--                      <c:if test="${sessionScope.role=='boardadmin'}"> -->
<!--                       		${sessionScope.user.boardName} BOARD ADMIN -->
<!--                     </c:if> -->
<!-- 					<c:if test="${sessionScope.role=='boardentry'}"> -->
<!--                       		${sessionScope.user.boardName} BOARD DATA ENTRY -->
<!--                     </c:if> -->
<!-- 					<c:if test="${sessionScope.role=='admin'}"> -->
<!--                       		${sessionScope.user.college_name} -->
<!--                     </c:if> -->
<!--     </b>, <a href="./logout.action" class="logout">Logout</a> </div>     -->
    </div>    
    <div class="submenu">
    </div>  