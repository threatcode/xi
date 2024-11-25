<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.text.DateFormat"%>
<script type="text/javascript" src="../resources/js/jquery.min.js"></script>
<style>
.reportHeader{
font-family: times, Times New Roman, times-roman, georgia, serif;
		font-size: 28px;
		line-height: 40px;
		letter-spacing: -1px;color: #444;
		padding-bottom: 5px;
}
.header{
font-family:Georgia,serif;
	color:black;
	font-variant: small-caps; text-transform: none; margin-bottom: 0;
}
@media print {
    input#printButton {
        display: none;
        }
    }
</style>

<div class="reportHeader" >
	<center>
		XI Class Admission System (Session: 2015-2016)<br/>
		<font style="font-size: 22px;">Applicant's Information Summary</font>
	</center>
</div>
<div style="float: right;">
<b>Processed on : </b><%= new java.text.SimpleDateFormat("dd MMM, YYYY HH:mm:ss").format(new java.util.Date()) %>
<!-- 
	<b>Processed on : </b> 3rd June, 2015 17:36:35
 -->
</div>
<p class="header" style="padding-bottom: 10px;clear: both;">
Basic Information : 
</p>

<table border="1" cellpadding="4" cellspacing="0" style="border-collapse: collapse;border: 1px solid black;" width="750px" />
	<tr>
		<td width="120" align="left">Application Id</td>
		<td width="80" id="print_app_id"  align="left" style="border-right: 4px solid black;">
		<s:property value="applicant.application_id"/>
		</td>
		
		<td width="150"  align="left">Applicant's Name</td>
		<td width="250" id="print_app_name"  align="left" style="border-right: 4px solid black;">
		<s:property value="applicant.ssc_info.student_name"/>
		</td>

		<td width="90"  align="left">Quota</td>
		<td width="127" id="print_quota"  align="left">
			<s:if test='applicant.application_info.quota_ff=="Y"'>
	         FQ 
	       </s:if>
	       <s:if test='applicant.application_info.quota_ff=="Y" && applicant.application_info.quota_eq=="Y"'>
	         , 
	       </s:if>
	       <s:if test='applicant.application_info.quota_eq=="Y"'>
	          EQ
	       </s:if>
		</td>
		
	</tr>
	<tr>
		<td  align="left">Passing Year</td>
		<td id="print_ssc_year"  align="left" style="border-right: 4px solid black;">
			<s:property value="applicant.ssc_info.passing_year"/>
		</td>
		<td  align="left">Board</td>
		<td id="print_ssc_board"  align="left" style="border-right: 4px solid black;" width="140">
			<s:property value="applicant.ssc_info.board_name"/>
		</td>
		<td  align="left" style="width: 120">Roll</td>
		<td id="print_ssc_roll"  align="left">
			<s:property value="applicant.ssc_info.roll"/>
		</td>
	</tr>
</table>

<p class="header" style="padding-bottom: 10px;margin-top: 30px;">
Application Information <b>(with Priority)</b> : 
</p>

<table id="table_choice_list" border="1" cellpadding="4" cellspacing="0" style="border-collapse: collapse;border: 1px solid black;" width="750px" />

	<tr>
		<td width="21" align="center">Priority</td>
		<td width="200" align="left">College Name</td>
		<td width="15" align="center">EIIN</td>
		<td width="50" align="left">Shift</td>
		<td width="50" align="left">Version</td>
		<td width="50" align="left">Group</td>
		<td width="6"  align="center">SQ</td>
		<td width="6"  align="center">Payment</td>
	</tr>
	<s:iterator value="choiceList" status="indx">
	      <tr>
	      <td align="center"><b><s:property value="priority"/></b></td>
	            <td align="left"><s:property value="college_name"/></td>
	            <td align="center"><s:property value="eiin"/></td>
	            <td align="left"><s:property value="shift_name"/></td>
	            <td align="left"><s:property value="version_name"/></td>
	            <td align="left"><s:property value="group_name"/></td>	            
	            <td align="center">
	            <s:if test='special_quota=="N"'>
	             -
	            </s:if>
	            <s:else>
	            Y
	            </s:else>
	            </td>
	            <td align="center">
	                 <s:if test='payment_status == "Y"'>
	            		Yes   
	            	</s:if>
	            	<s:else>
	            		No
	            	</s:else>	            
	            </td>	      
	            
	      </tr>
	</s:iterator>
	
</table>
<br/><br/>
<center>
<div style="width: 700px;border: 2px dashed black;height: 85px;font-size: 18px;font-weight: bold;margin-top: 20px;text-align: left;padding-left: 5px;">

	<li>Please make sure about Prioirty, Shift, Version, Group and Quota</li>
	<li>You can change your priority or quota with OTP (one time password) if needed</li>
	<li>You can get OTP after log-in with your Application ID and Password</li>
	<li>Please do not disclose any Password</li>
</div>
</center>
<br/><br/>
<center>
<div style="text-align: right;float: right;">
BANGLADESH INTER-BOARD COORDINATION SUB-COMMITTEE<br/>
Technical Assistance by : IICT, BUET
</div>
<p style="clear: both;height: 30px;">

</p>
<input type="button" id="printButton" onclick="window.print();" value="Print Choice List" style="font-weight: bold;"/>
</center>

<script type="text/javascript">
$( document ).ready(function() {
  window.onresize = function() 
	{
    	window.resizeTo(780,850);
	}
 window.onclick = function() 
	{	
    	window.resizeTo(780,850);
	}
});
</script>
