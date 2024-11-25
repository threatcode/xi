<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Arrays"%>
<style>
div.transbox {
  margin: 30px;
  background-color: #f0f8ff;
  border: 1px solid black;
  opacity: 0.6;
  filter: alpha(opacity=60); /* For IE8 and earlier */
}

div.transbox p {
  margin: 5%;
  font-weight: bold;
  color: #000000;
}
</style>

<%-- Applicat Name:${requestScope.applicantName}  --%>
<center>
<c:if test="${requestScope.applicationID==' '}">
	<div class="transbox" style="width:400px;">
	<p><font size="3" color="red">Your given SSC information do not match</font></p>
  </div>

</c:if>
<c:if test="${requestScope.applicationID!=' '}">
	<%-- <strong>Application ID:</strong>
<font size="2" color="blue">${requestScope.applicationID} </font> 
<br> --%>
	<div class="transbox" style="width:400px;">
	<p><strong>  &nbsp</strong>
    <font size="3" color="green">${requestScope.pin}</font></p>
  </div>
</c:if>
</center>
<br>
<br>
<br>