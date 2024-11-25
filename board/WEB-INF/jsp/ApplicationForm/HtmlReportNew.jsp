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
		<b>4th Phase Application</b> <br/>
		<font style="font-size: 22px;">Applicant's Information Summary</font>
	</center>
</div>
<div style="float: right;">
<b>Processed on : </b><%= new java.text.SimpleDateFormat("dd MMM, YYYY HH:mm:ss").format(new java.util.Date()) %>
</div>
<p class="header" style="padding-bottom: 10px;clear: both;">
Basic Information : 
</p>

<table border="1" cellpadding="4" cellspacing="0" style="border-collapse: collapse;border: 1px solid black;" width="750px" />
	<tr>
		<td width="120" align="left">Application Id</td>
		<td width="80" id="print_app_id"  align="left" style="border-right: 4px solid black;"></td>
		
		<td width="150"  align="left">Applicant's Name</td>
		<td width="250" id="print_app_name"  align="left" style="border-right: 4px solid black;"></td>

		<td width="90"  align="left">Quota</td>
		<td width="127" id="print_quota"  align="left"></td>
		
	</tr>
	<tr>
		<td  align="left">Roll</td>
		<td id="print_ssc_roll"  align="left" style="border-right: 4px solid black;"></td>
		<td  align="left">Board</td>
		<td id="print_ssc_board"  align="left" style="border-right: 4px solid black;" width="140"></td>
		<td  align="left" style="width: 120">Passing Year</td>
		<td id="print_ssc_year"  align="left"></td>
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
		<td width="6"  align="center">EQ</td>
		
	</tr>
	
</table>
<br/><br/>
<center>
<div style="width: 700px;border: 2px dashed black;height: 135px;padding-top: 0px;font-size: 18px;font-weight: bold;margin-top: 4px;text-align: left;">
<ul>
	<li>Please make sure about Priority, Shift, Version, Group and Quota</li>
	<li>You can change your priority or quota with Registration Number/OTP (one time password) if needed</li>
	<li>You can get OTP after log-in with your Application ID and Password</li>
	<li>Please do not disclose any Password</li>
	</ul>
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
var pw = window.opener;
	if(pw){
		var choiceForm = pw.document.forms['parentChoiceForm'];
		document.getElementById("print_app_id").innerHTML=choiceForm.elements['print_app_id'].value;
		/*document.getElementById("print_password").innerHTML=choiceForm.elements['print_password'].value;*/
		document.getElementById("print_app_name").innerHTML=choiceForm.elements['print_app_name'].value;

		
		document.getElementById("print_ssc_roll").innerHTML=choiceForm.elements['print_ssc_roll'].value;
		document.getElementById("print_ssc_board").innerHTML=choiceForm.elements['print_ssc_board'].value;
		document.getElementById("print_ssc_year").innerHTML=choiceForm.elements['print_ssc_year'].value;
		var quota_seperator="";
		
		if(choiceForm.elements['print_ff'].value != "" && choiceForm.elements['print_eq'].value != "")
			quota_seperator=", "
		document.getElementById("print_quota").innerHTML=choiceForm.elements['print_ff'].value+quota_seperator+choiceForm.elements['print_eq'].value;

		var choiceList=choiceForm.elements['print_choice_list'].value; 
		choiceList=choiceList.split("@@");
		for(var i=0;i<choiceList.length;i++){
				   var choiceArr=choiceList[i].split("#");
				   var row = [];
    			   row.push("<tr>",
    			   			"<td align=\"center\"><b>"+choiceArr[8]+"</b></td>",
    			   			"<td align=\"left\">"+choiceArr[1]+"</td>",
    			   			"<td align=\"center\">"+choiceArr[2]+"</td>",
    			   			"<td align=\"left\">"+choiceArr[3]+"</td>",
    			   			"<td align=\"left\">"+choiceArr[4]+"</td>",
    			   			"<td align=\"left\">"+choiceArr[5]+"</td>",
    			   			"<td align=\"left\">"+choiceArr[6]+"</td>",
    			   			"<td align=\"center\">"+choiceArr[7]+"</td>)");
    			   			
    			  var row_str=row.join("");
  				  $('#table_choice_list tr').last().after(row_str);
 
		}
		
	}
	
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

