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

<div class="reportHeader" style="font-weight: bold;">
	<center>
		<font style="color: rgb(0, 31, 150); "> XI Class Admission System (Session: 2020-2021)</font><br/>
		<font style="font-size: 22px;">Applicant's Information Summary</font>
	</center>
</div>
<div style="float: right;">
<b>Processed on : </b><%= new java.text.SimpleDateFormat("dd MMM, YYYY HH:mm:ss").format(new java.util.Date()) %>
<!-- 
	<b>Processed on : </b> 3rd June, 2015 17:36:35
 -->
</div>
<p class="header" style="padding-bottom: 10px;clear: both;font-weight: bold;">
Basic Information : 
</p>

<table border="1" cellpadding="4" cellspacing="0" style="border-collapse: collapse;border: 1px solid black; margin-left:auto; margin-right: auto;" width="750px" />
	<tr>
		<td width="120" align="left">National ID.</td>
		<td width="80" id="print_app_id"  align="left" style="border-right: 4px solid black;">
		********
		<%-- <s:property value="applicant.application_info.nid_number"/> --%>
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
	       <s:if test='applicant.application_info.quota_eq=="Y"'>
	          EQ,
	       </s:if>
	       <s:if test='applicant.application_info.quota_expatriate=="Y"'>
	          PQ
	       </s:if>
	       
	          <!-- Chekc all three -->
	             <s:if test='applicant.application_info.quota_ff=="N"'>
	              <s:if test='applicant.application_info.quota_eq=="N"'>
	                <s:if test='applicant.application_info.quota_expatriate=="N"'>
	                 None
	                </s:if>
	              </s:if>
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

<p class="header" style="padding-bottom: 10px;margin-top: 30px;font-weight: bold;">
Application Information : 
</p>

<table id="table_choice_list" border="1" cellpadding="4" cellspacing="0" style="border-collapse: collapse;border: 1px solid black;margin-left:auto; margin-right: auto;" width="750px" />

	<tr>
		<td width="200" style="text-align: left;font-weight: bold;">College Name</td>
		<td width="15" style="text-align: center;font-weight: bold;">EIIN</td>
		<td width="50" style="text-align: center;font-weight: bold;">Shift</td>
		<td width="50" style="text-align: center;font-weight: bold;">Version</td>
		<td width="50" style="text-align: center;font-weight: bold;">Group</td>
		<td width="6"  style="text-align: center;font-weight: bold;">SQ</td>
		<!-- <td width="6"  style="text-align: center;font-weight: bold;">GQ</td> -->
		<td width="6"  style="text-align: center;font-weight: bold;">Type</td>
		<td width="6"  style="text-align: center;font-weight: bold;">Priority</td>
	</tr>
	<s:iterator value="choice" status="indx">
	      <tr>
	            <td align="left"><s:property value="college_name"/></td>
	            <td style="text-align: center;"><s:property value="eiin"/></td>
	            <td style="text-align: center;"><s:property value="shift_name"/></td>
	            <td style="text-align: center;"><s:property value="version_name"/></td>
	            <td style="text-align: center;"><s:property value="group_name"/></td>	            
	            <td style="text-align: center;"><s:if test='special_quota=="N"'>N</s:if>
	            <s:else>Y</s:else></td>
	            <!-- 
	            <td style="text-align: center;"><s:if test='gb_quota=="N"'>N</s:if>
	            <s:else>Y</s:else></td>
	            -->
	            <td style="text-align: center;"><s:property value="application_type"/></td>	
	            <td style="text-align: center;"><s:property value="priority"/></td>	
	            </td>
	            
	      </tr>
	</s:iterator>
	
</table>
<p id="boutext" style="padding-bottom: 10px;margin-top: 30px;" width="750px"></p>
<s:if test="applicant.ssc_info.board_name == 'BOU'">
    <script type="text/javascript">
    	document.getElementById("boutext").innerHTML='* The age of a student passing from Bangladesh Open University must not be more than 22 years on 31 December 2019, that is, the student must be born on or after 1 January 1998.';
    </script>
</s:if>			
<!-- 
<center>
<div style="width: 700px;border: 2px dashed black;height: 35px;padding-top: 10px;font-size: 18px;font-weight: bold;margin-top: 20px;">

Please make sure about Shift, Version, Group, Quota and Prioirty
</div>
</center>
 -->
<br/><br/>
<center>
<!-- <div style="text-align: right;float: right;">
BANGLADESH INTER-BOARD COORDINATION SUB-COMMITTEE<br/>
</div> -->
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
