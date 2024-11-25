<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %> --%>
<style>
#modelNoSelection {
/* position:absolute;
background:#F5F5DC; */
/* width:400px;
height:400px; */
/* border:5px solid #000;
z-index: 9002; */
}
</style>

<center>

<!---- body ----->		

<c:if test="${not empty requestScope.ErrorMassage}">

			  	
 <div class="alert alert-info">
<button class="close" type="button" data-dismiss="alert">×</button>
<span class="entypo-info-circled"></span>
<strong>Message:</strong>
  ${requestScope.ErrorMassage}
</div>
			  	

  </c:if>

   <div class="row">                      
 		Name		: ${requestScope.lststudent.student_name} <br>
 		Father Name	: ${requestScope.lststudent.father_name} <br><br>
 		<font color="red"> Mobile Number : </font>
 		<input type="text" id="mnumber" name="mnumber"  maxlength="11"> <br><br><br>
 		<c:if test="${not empty requestScope.lststudent}">
 			<input type="button" style="width:140px;padding: 1px 12px;" class="btn btn-info" onclick="addStudent()" value="Add Student" >
 		</c:if>
        
</div>
<!----body ------>	

</center>


<script type="text/javascript" >

	function abc() {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
             // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
             // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    }

</script> 

<link rel="stylesheet" type="text/css" href="/board/resources/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/board/resources/css/dataTables.bootstrap.css" />


<script type="text/javascript" language="javascript" src="/board/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript" src="/board/resources/js/dataTables.bootstrap.js"></script>


    <style>
.buttonCancel:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(255,0,0,0.2);
}
.buttonCSV:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,255,0,0.2);
}
</style>
