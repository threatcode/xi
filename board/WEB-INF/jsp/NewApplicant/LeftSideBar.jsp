<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String url=(String)request.getServletPath();    	
%>
<style type="text/css">
.current_activity{
background-color: #ededed;
}
</style>
<h2>Browse categories</h2>    
<ul>
	<li><a id="a_result" href="viewResult.action">Result</a></li>
    <li><a id="a_basicInfo" href="newApplicantInfoReadOnly.action">Basic Information</a></li>
    <li><a id="a_choiceList" href="newChoiceListReadOnly.action">Choice List(4th Phase)</a></li>          
    <li><a href="javascript:void(0)" onclick="showPopUp('newChoiceListReport')">Print Choice List(4th Phase)</a></li>
    <li><a href="logout.action">Logout</a></li>
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
	%>
</script>   