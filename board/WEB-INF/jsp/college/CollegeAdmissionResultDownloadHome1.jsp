<%@ include file="/WEB-INF/jsp/template/header.jsp" %>
<%@ include file="/WEB-INF/jsp/template/sidebar.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Arrays"%>

                        <center>
                        <h3 class="text-green">
                        <span>:: RESULT DOWNLOAD ::</span>
                            </h3>
                        </center>
                         <br/>

<center>
<form action="admissionResultReportAction" name="resultDownloadForm" id="resultDownloadForm" method="post">
<table style="width:400px;font-size:14px"  class="table table-bordered table-striped cf">
<tr>
<td style="color:black;"><strong>Shift </strong></td>
<td style="color:black;"><strong>Version</strong></td>
<td style="color:black;"><strong>Group</strong></td>
<td style="color:black;"><strong>Merit Type</strong></td>
<td style="color:black;"><strong>Action</strong></td>

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
</td>
<td>
<select name="meritType" id="meritType">
<option value="none" selected>Select Merit Type</option>
<option value="1">1st Merit List</option>
<option value="1-OUT">1st Merit Migration[OUT]</option>
<option value="2">1st Merit Migration[IN]</option>

<option value="3">2nd Merit List</option>
<!-- <option value="3-OUT">2nd Merit Migration[OUT]</option>
<option value="4">2nd Merit Migration[IN]</option> -->

<option value="5">Release Slip</option>
<option value="9">4th Phase</option>
<option value="15">BTEB 2nd Phase</option>

</select>
<input type="hidden" name="groupName" id="groupName" />
</td>
<td>
<!--<button class="btn bg-green"  type="button" onclick="downloadResult()">Download Result</button>-->
<input type="submit" class="btn bg-green" onclick="return downloadResult()" value="Download Result" >
</td>
 
 </tr>
</table>

</form>
</center>

<script type='text/javascript' src="/board/resources/js/jquery.min.js"></script>



	
<script type="text/javascript">

    function downloadResult() {

       if($("#shiftId").val()=="none"){
            alert("Select a Shift.");return false;
        }
        if($("#versionId").val()=="none"){
            alert("Select a version.");return false;
        }
		if($("#groupId").val()=="none"){
            alert("Select a group.");return false;
        }
        if($("#meritType").val()=="none"){
            alert("Select a merit.");return false;
        }
                
      }
      
      function changeGroup(){
      	$("#groupName").val($("#groupId option:selected").text());
      	//alert($("#groupName").val());
      }

</script>	

 
<%@ include file="/WEB-INF/jsp/template/footer.jsp" %>
