<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String url=(String)request.getServletPath();    	
%>
<style type="text/css">
.current_activity{
background-color: #ededed;
}
</style>
<h2 class="SidebarHeader">Browse categories</h2>    
<ul>

    	<li><a id="a_result" href="ApplicantViewResult.action">Result</a></li>
    	 <!-- 
    	<li><a href="releaseSlipReadOnly.action" >Your Chooses</a></li>
    	<li><a href="releaseSlip.action" >Change Your Chooses</a></li>
    	-->
    	 <li><a href="migrationNew" >Migration</a></li>
    	<!--             
    	 <li><a href="migrationForm.action" >Only Migration</a></li>
    	  -->
    	  <%--
    <s:if test='#session.releaseSlipEligibility=="Y"'>
    	<li><a href="javascript:void(0)" onclick="showPopUp('releaseSlipReport')">Print Release Slip</a></li>
    </s:if>
     --%>
    <!-- <li><a id="a_basicInfo" href="applicantPhotoChangeView.action">Upload Photo</a></li> -->
    <li><a id="a_basicInfo" href="applicantInfoReadOnly.action">Basic Information</a></li>
    <!-- 
    <li><a id="a_choiceList" href="choiceListReadOnly.action">Choice List</a></li>          
    <li><a href="javascript:void(0)" onclick="showPopUp('choiceListReport')">Print Previous Choice List</a></li>
    -->
    <!-- <li><a href="logout.action">Logout</a></li> -->
     <li><a href="appLogout.action">Logout</a></li>
</ul>
<script type="text/javascript">
function showPopUp(actionName){
	window.open(actionName,'1433313485446','width=780,height=850,toolbar=0,menubar=0,location=0,status=1,scrollbars=1,resizable=0,left=0,top=0');
}
</script>   
<script type="text/javascript">
	<%
	if(url.contains("ApplicantHome.jsp") || url.contains("ApplicantHomeReadOnly.jsp")){
		out.println("$('#a_basicInfo').addClass('current_activity');");
	}
	else if(url.contains("ChoiceList.jsp") || url.contains("ChoiceListReadOnly.jsp")){
		out.println("$('#a_choiceList').addClass('current_activity');");
	}
	else if(url.contains("Result.jsp")){
		out.println("$('#a_result').addClass('current_activity');");
	}
	%>
</script>   