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

<div class="reportHeader"  style="font-weight: bold;">
	<center>
		<font style="color: rgb(0, 31, 150); ">XI Class Admission System (Session: 2020-2021)</font><br/>
		<font style="font-size: 22px;">Applicant's Information Summary</font>
	</center>
</div>
<div style="float: right;">
<b>Processed on : </b><%= new java.text.SimpleDateFormat("dd MMM, YYYY HH:mm:ss").format(new java.util.Date()) %>
</div>
<p class="header" style="padding-bottom: 10px;clear: both;font-weight: bold;">
Basic Information : 
</p>

<table border="1" cellpadding="4" cellspacing="0" style="border-collapse: collapse;border: 1px solid black;margin-left:auto; margin-right: auto;" width="750px" />
	<tr>
		<td width="80" align="left">NID</td>
		<td width="120" id="print_app_id"  align="left" style="border-right: 4px solid black;">**********</td>
		
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

<p class="header" style="padding-bottom: 10px;margin-top: 30px;font-weight: bold;">
Application Information <b>(with Priority)</b> : 
</p>

<table id="table_choice_list" border="1" cellpadding="4" cellspacing="0" style="border-collapse: collapse;border: 1px solid black;margin-left:auto; margin-right: auto;" width="750px" />

	<tr>
		<!-- <td width="21" align="center">SL</td> -->
		<td width="200" style="text-align: left;font-weight: bold;">College Name [EIIN]</td>
		<%--<td width="15" style="text-align: center;font-weight: bold;">EIIN</td>--%>
		<td width="40" style="text-align: center;font-weight: bold;">Shift</td>
		<td width="40" style="text-align: center;font-weight: bold;">Version</td>
		<td width="40" style="text-align: center;font-weight: bold;">Group</td>
		<td width="6" style="text-align: center;font-weight: bold;">SQ</td>
		<!-- <td width="6" style="text-align: center;font-weight: bold;">GB</td> -->
		<td width="35" style="text-align: center;font-weight: bold;">Type</td>
		<td width="35" style="text-align: center;font-weight: bold;">Priority</td>
		
	</tr>
	
</table>

<p id="boutext" style="padding-bottom: 10px;margin-top: 30px;" width="750px"></p>
<center>
<!-- 
<div style="width: 700px;border: 2px dashed black;height: 35px;padding-top: 10px;font-size: 18px;font-weight: bold;margin-top: 20px;">

Please make sure about Shift, Version, Group and Quota
</div>
 -->
</center>
<br/><br/>
<center>
<!-- <div style="text-align: right;float: right;">
BANGLADESH INTER-BOARD COORDINATION SUB-COMMITTEE<br/>
Technical Assistance by : IICT, BUET
</div> -->
<div id="delwar">

</div>
<p style="clear: both;height: 30px;">

</p>
<input type="button" id="printButton" onclick="window.print();" value="Print Choice List" style="font-weight: bold;"/>
</center>
<script type="text/javascript">

var pw = window.opener;
	if(pw){
		var choiceForm = pw.document.forms['parentChoiceForm'];
		/*document.getElementById("print_app_id").innerHTML=choiceForm.elements['print_app_id'].value;
		document.getElementById("print_password").innerHTML=choiceForm.elements['print_password'].value;*/
		document.getElementById("print_app_name").innerHTML=choiceForm.elements['print_app_name'].value;

		
		document.getElementById("print_ssc_roll").innerHTML=choiceForm.elements['print_ssc_roll'].value;
		document.getElementById("print_ssc_board").innerHTML=choiceForm.elements['print_ssc_board'].value;
		document.getElementById("print_ssc_year").innerHTML=choiceForm.elements['print_ssc_year'].value;
		
		if(choiceForm.elements['print_ssc_board'].value=='BOU')
			document.getElementById("boutext").innerHTML='* The age of a student passing from Bangladesh Open University must not be more than 22 years on 31 December 2019, that is, the student must be born on or after 1 January 1998.';
		var quota_seperator="";
		//console.log('delwar');
		var str = choiceForm.elements['print_ff'].value.trim();
		str = str.substring(0, str.length - 1);
		if(str=='Non')str='None';
		//console.log(str);
		//console.log('delwar');
		if(choiceForm.elements['print_ff'].value != "" && choiceForm.elements['print_eq'].value != "")
			quota_seperator=", "
		document.getElementById("print_quota").innerHTML=str;//+quota_seperator+choiceForm.elements['print_eq'].value;


		var choiceList=choiceForm.elements['print_choice_list'].value;
		//console.log(choiceList);
		//document.getElementById("delwar").innerHTML=choiceList;
		choiceList=choiceList.split("@@");
		for(var i=0;i<choiceList.length;i++){
				   var choiceArr=choiceList[i].split("#");
				   var row = [];
				   var priorityString = '';
				   //if(choiceArr[7].trim()=='N')
				   	priorityString = choiceArr[8] ;
				   //else
				   	//priorityString = createElementFromHTML(choiceArr[7]).value ;
				   //console.log(choiceArr);
    			   row.push("<tr>",
    			   			"<td style=\"text-align: left;\">"+choiceArr[1]+"</td>",
    			   			"<td style=\"text-align: center;\">"+choiceArr[3]+"</td>",
    			   			"<td style=\"text-align: center;\">"+choiceArr[4]+"</td>",
    			   			"<td style=\"text-align: center;\">"+choiceArr[5]+"</td>",
    			   			"<td style=\"text-align: center;\">"+choiceArr[6]+"</td>",
    			   			"<td style=\"text-align: center;\">"+choiceArr[9]+"</td>",
    			   			"<td style=\"text-align: center;\">"+priorityString+"</td>)");

    			  var row_str=row.join("");
  				  $('#table_choice_list tr').last().after(row_str);
 
		}
		
	}
function createElementFromHTML(htmlString) {
  var div = document.createElement('div');
  div.innerHTML = htmlString.trim();

  // Change this to div.childNodes to support multiple top-level nodes
  return div.firstChild; 
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

