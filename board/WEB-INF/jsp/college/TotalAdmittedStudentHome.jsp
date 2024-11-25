<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2>ADMITTED STUDENT LIST</h2>
			<div class="sidebar_section_text">
			<div class="box_row" style="background-color: #473c8b;color: white;text-align: left;font-weight: bold;padding-top: 0px;font-size: 16px;height: 22px;">
										<center>:: DOWNLOAD ADMITTED STUDENT LIST ::</center>
									</div>
<center>

<!---- body ----->		
<%--                         <center>
                        <span>::DOWNLOAD ADMITTED STUDENT LIST::</span>
                        </center> --%>
                         <br/>
<center>
<form action="totalSVGAdmittedAction" name="totalSVGAdmittedCSV" id="totalSVGAdmittedCSV" method="post">
<table style="width:400px;font-size:14px"  class="table table-bordered table-striped cf">
<tr style="background-color: #f0f8ff;">
<td style="color:black;"><strong>Shift </strong></td>
<td style="color:black;"><strong>Version</strong></td>
<td style="color:black;"><strong>Group</strong></td>
<td style="color:black;"><strong>Download</strong></td>
</tr>

<tr>
<td>
<select name="shiftId" id="shiftId">
<option value="none" selected>Select Shift</option>
<c:forEach items="${shiftList}" var="shift">
<option value="${shift.shiftId}">${shift.shiftName}
</option>
</c:forEach>

</select>
</td>

<td>
<select name="versionId" id="versionId">
<option value="none" selected>Select Version</option>
<c:forEach items="${versionList}" var="version">
<option value="${version.versionId}">${version.versionName}
</option>
</c:forEach>
</select>
</td>

<td>
<select name="groupId" id="groupId" onchange="changeGroup()">
<option value="none" selected>Select Group</option>
<c:forEach items="${groupList}" var="group">
<option value="${group.groupId}">${group.groupName}
</option>
</c:forEach>

</select>
<input type="hidden" name="groupName" id="groupName" />
</td>
<td>
<!--<button class="btn bg-green"  type="button" onclick="downloadResult()">Download Result</button>-->
<button style="width:100px;padding: 1px 12px;" class="btn btn-info buttonCSV" type="button" onclick="downloadadmittedCSV()">Download </button>
</td>
 
 </tr>
</table>
</form>
</center>                        
	
<!----body ------>	

</center>

</div>
		</div>
		<br>

	</div>


	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp" %>
</div>

          
<script type="text/javascript">

    function downloadadmittedCSV() {

       if($("#shiftId").val()=="none"){
            alert("Select a Shift.");
            return;
        }
        if($("#versionId").val()=="none"){
            alert("Select a version.");
            return;
        }
		if($("#groupId").val()=="none"){
            alert("Select a group.");
            return;
        }
            
          document.forms["totalSVGAdmittedCSV"].submit();
                
      }
      
        function changeGroup(){
      	$("#groupName").val($("#groupId option:selected").text());
      	//alert($("#groupName").val());
      }


</script>		
<%@ include file="/WEB-INF/jsp/template/footerLite.jsp" %>

</div>
</body>
<style>
.buttonCSV:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,255,0,0.2);
}
.buttonPDF:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,255,0,0.2);
}
</style>
</html>