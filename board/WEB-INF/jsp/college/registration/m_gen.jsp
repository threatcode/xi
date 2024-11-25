		<input name="sub7" id="sub7" type="hidden">
	
<table style="border: 1px solid black;margin-right: auto;margin-left: 69px;width: 80%;">

	<tr><th style="padding-left: 40px;text-align: left;height: 40px" colspan="2"><font size="4" color="#34495e"><u>Compulsory Subject</u></font>
	<tr><td style="padding-left: 10px;height: 30px">1<td>Bangla 					(236, 237)
	<tr><td style="padding-left: 10px;height: 30px">2<td>English 					(238, 239)
	<tr><td style="padding-left: 10px;height: 30px">3<td>ICT 						(240)
	<tr><td style="padding-left: 10px;height: 30px">4<td>Quran Mazid				(201)
	<tr><td style="padding-left: 10px;height: 30px">5<td>Hadith & Usul-E-Hadith		(202)
	<tr><td style="padding-left: 10px;height: 30px">6<td>Al Fiquh 					(203, 204)
	<tr><td style="padding-left: 10px;height: 30px">7<td>Arabic 					(205, 206)
	<tr><td style="padding-left: 10px;height: 30px">8<td>Islamic History			(209)
	<tr><td style="padding-left: 10px;height: 30px">9<td>Balagat & Mantiq	 		(210)	
			<tr><td height="30"><td height="30">
				<tr><td height="30"><td height="30">
	<tr><th style="padding-left: 40px;text-align: left;height: 40px" colspan="2"><font size="4" color="#34495e"><u>Optional Subject</u></font>
	
	<tr><td style="padding-bottom: 20px;padding-left: 10px;height: 30px">10<td><select id="sub7s" name="sub7s"  style="width: 242px" onchange="sub7change()" tabindex="8">
											<option value="" selected>Select Optional Subject</option>
														<c:forEach items="${session.collegeSubject}" var="cs" varStatus="loop">
															<c:if test="${cs.group_id=='9'}">
																	<option value="${cs.sub_id}">${cs.sub_name}</option>
															</c:if>			
														</c:forEach>	
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