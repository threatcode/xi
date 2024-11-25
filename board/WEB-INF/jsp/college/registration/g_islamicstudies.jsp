	
	<input name="sub7" id="sub7" type="hidden">
	
<table style="border: 1px solid black;margin-right: auto;margin-left: 69px;width: 80%;">

	<tr><th style="padding-left: 40px;text-align: left;height: 40px" colspan="2"><font size="4" color="#34495e"><u>Compulsory Subject</u></font>
	<tr><td style="padding-left: 10px;height: 30px">1<td>Bangla    (101, 102)
	<tr><td style="padding-left: 10px;height: 30px">2<td>English   (107, 108)
	<tr><td style="padding-left: 10px;height: 30px">3<td>ICT	   (275)
	<tr><td style="padding-left: 10px;height: 30px">4<td>Studies of Islam   (249, 250)
	<tr><td style="padding-left: 10px;height: 30px">5<td>Islamic History & Culture(267, 268)
	<tr><td style="padding-left: 10px;height: 30px">5<td>Arabic (133, 134)	
			<tr><td height="30"><td height="30">
				<tr><td height="30"><td height="30">
	<tr><th style="padding-left: 40px;text-align: left;height: 40px" colspan="2"><font size="4" color="#34495e"><u>Optional Subject</u></font>
	
	<tr><td style="padding-bottom: 20px;padding-left: 10px;height: 30px">7<td><select id="sub7s" name="sub7s"  style="width: 242px" onchange="sub7change()" tabindex="8">
											<option value="" selected>Select Optional Subject</option>
														<c:forEach items="${session.collegeSubject}" var="cs" varStatus="loop">
															<c:if test="${cs.group_id=='6'}">
																	<option value="${cs.sub_id}">${cs.sub_name}</option>
															</c:if>			
														</c:forEach>		
											<!-- 
    										<option value="117">SOCIOLOGY</option>
    										<option value="271">Social Work</option>
    										<option value="239">AGRI. STUDIES</option>
    										<option value="273">Home Science</option>
    										<option value="123">PSYCHOLOGY</option>
    										<option value="121">LOGIC</option>
    										<option value="125">GEOGRAPHY</option>					
    										<option value="109">ECONOMICS</option>
    										-->
										</select>		
	
		
</table>



<script type="text/javascript">
// save in database........log rakte hobe
// clear in showdata
	// onchange a check
	// Submit Data te check korte hobe
	//
	function sub7change()
	{
		$("#sub7").val($("#sub7s").val())
	}
	
	function validateData()
	{
		var tmp = true; 
		if($('#esif').val()=='')
		{
			alert('Please enter esif number');
			tmp = false;
		}
		else if($('#sections').val()=='')
		{
			alert('Please select section');
			tmp = false;
		}	
		else if($('#fileUpload').val()=='' && !update)
		{
			alert('Please select a photo');
			tmp = false;
		}
		else if($('#class_roll').val()=='')
		{
			alert('Please enter class roll');
			tmp = false;
		}	
		else if($('#nationality').val()=='')
		{
			alert('Please type nationality');
			tmp = false;
		}
		else if($('#hdistrict').val()=='')
		{
			alert('Please select home district');
			tmp = false;
		}
		else if($('#religion').val()=='')
		{
			alert('Please select religion');
			tmp = false;
		}
		else if($('#sub7s').val()=='')
		{
			tmp = confirm("You do not select optional subject!\n Are you sure you want to continue?");
		}		
		return tmp;
	}
	

</script>