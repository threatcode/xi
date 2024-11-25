	<input name="sub6" id="sub6" type="hidden">
	<input name="sub7" id="sub7" type="hidden">
	
<table style="border: 1px solid black;margin-right: auto;margin-left: 69px;width: 80%;">

	<tr><th style="padding-left: 40px;text-align: left;height: 40px" colspan="2"><font size="4" color="#34495e"><u>Compulsory Subject</u></font>
	<tr><td style="padding-left: 10px;height: 30px">1<td>Bangla    (101, 102)
	<tr><td style="padding-left: 10px;height: 30px">2<td>English   (107, 108)
	<tr><td style="padding-left: 10px;height: 30px">3<td>ICT	   (275)
	<tr><td style="padding-left: 10px;height: 30px">4<td>Business Organization   (277, 278)
	<tr><td style="padding-left: 10px;height: 30px">5<td>Accounting (253, 254)
	<tr><td style="padding-left: 10px;height: 30px">6<td><select id="sub6s" name="sub6s"  style="width: 242px" onchange="sub6change()"  tabindex="7">
											<option value="" selected>Select Compulsory Subject</option>
														<c:forEach items="${session.collegeSubject}" var="cs" varStatus="loop">
															<c:if test="${cs.group_id=='8'}">
																<c:if test="${cs.status=='A'}">
																	<option value="${cs.sub_id}">${cs.sub_name}</option>
																</c:if>		
															</c:if>			
														</c:forEach>		
											<!-- 
    										<option value="292">Finance, Banking & Insurance</option>
    										<option value="286">Production Management & Marketing</option>		
    										-->					
										</select>	
			<tr><td height="30"><td height="30">
				<tr><td height="30"><td height="30">
	<tr><th style="padding-left: 40px;text-align: left;height: 40px" colspan="2"><font size="4" color="#34495e"><u>Optional Subject</u></font>
	
	<tr><td style="padding-bottom: 20px;padding-left: 10px;height: 30px">7<td><select id="sub7s" name="sub7s"  style="width: 242px" onchange="sub7change()" tabindex="8">
											<option value="" selected>Select Optional Subject</option>
														<c:forEach items="${session.collegeSubject}" var="cs" varStatus="loop">
															<c:if test="${cs.group_id=='8'}">
																	<option value="${cs.sub_id}">${cs.sub_name}</option>
															</c:if>			
														</c:forEach>		
											<!-- 
    										<option value="292">Finance, Banking & Insurance</option>
    										<option value="286">Production Management & Marketing</option>
    										<option value="296">Human Resource Development</option>
    										<option value="125">GEOGRAPHY</option>
    										<option value="129">STATISTICS</option>
    										<option value="239">AGRI. STUDIES</option>
    										<option value="109">ECONOMICS</option>
    										<option value="273">Home Science</option>
											-->									    										    										    															
										</select>		
	
		
</table>



<script type="text/javascript">
// save in database........log rakte hobe
// clear in showdata
	// onchange a check
	// Submit Data te check korte hobe
	//
	function sub6change()
	{
		if($("#sub6s").val()==$("#sub7s").val())
		{
			$("#sub7s").val('');$("#sub7").val('');
		}
		$("#sub6").val($("#sub6s").val())
	}
	
	function sub7change()
	{
		if($("#sub6s").val()==$("#sub7s").val())
		{
			$("#sub6s").val('');$("#sub6").val('');
		}
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
		else if($('#sub6s').val()=='')
		{
			alert('Please select compulsary subject 6');
			tmp = false;
		}	
		else if($('#sub7s').val()=='')
		{
			tmp = confirm("You do not select optional subject!\n Are you sure you want to continue?");
		}		
		return tmp;
	}
	

</script>