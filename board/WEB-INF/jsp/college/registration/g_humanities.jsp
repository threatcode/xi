	<input name="sub4" id="sub4" type="hidden">
	<input name="sub5" id="sub5" type="hidden">
	<input name="sub6" id="sub6" type="hidden">
	<input name="sub7" id="sub7" type="hidden">
	
<table style="border: 1px solid black;margin-right: auto;margin-left: 69px;width: 80%;">

	<tr><th style="padding-left: 40px;text-align: left;height: 40px" colspan="2"><font size="4" color="#34495e"><u>Compulsory Subject</u></font>
	<tr><td style="padding-left: 10px;height: 30px">1<td>Bangla    (101, 102)
	<tr><td style="padding-left: 10px;height: 30px">2<td>English   (107, 108)
	<tr><td style="padding-left: 10px;height: 30px">3<td>ICT	   (275)
	<tr><td style="padding-left: 10px;height: 30px">4<td><select id="sub4s" name="sub4s"  style="width: 242px" onchange="sub4change()"  tabindex="7">
											<option value="" selected>Select Compulsory Subject</option>
														<c:forEach items="${session.collegeSubject}" var="cs" varStatus="loop">
															<c:if test="${cs.group_id=='2'}">
																<c:if test="${cs.status=='A'}">
																	<option value="${cs.sub_id}">${cs.sub_name}</option>
																</c:if>		
															</c:if>			
														</c:forEach>												
											<!-- 
    										<option value="304">History</option>
    										<option value="267">Islamic History & Culture</option>	
    										<option value="269">Civics & Good Governnance</option>
    										<option value="109">ECONOMICS</option>
    										<option value="117">SOCIOLOGY</option>
    										<option value="271">Social Work</option>
    										<option value="125">GEOGRAPHY</option>
    										<option value="121">LOGIC</option>    	
    										-->									    										    										    										    																
										</select>	
	<tr><td style="padding-left: 10px;height: 30px">5<td><select id="sub5s" name="sub5s"  style="width: 242px" onchange="sub5change()"  tabindex="8">
											<option value="" selected>Select Compulsory Subject</option>
														<c:forEach items="${session.collegeSubject}" var="cs" varStatus="loop">
															<c:if test="${cs.group_id=='2'}">
																<c:if test="${cs.status=='A'}">
																	<option value="${cs.sub_id}">${cs.sub_name}</option>
																</c:if>		
															</c:if>			
														</c:forEach>								
										</select>	
	<tr><td style="padding-left: 10px;height: 30px">6<td><select id="sub6s" name="sub6s"  style="width: 242px" onchange="sub6change()"  tabindex="9">
											<option value="" selected>Select Compulsory Subject</option>
														<c:forEach items="${session.collegeSubject}" var="cs" varStatus="loop">
															<c:if test="${cs.group_id=='2'}">
																<c:if test="${cs.status=='A'}">
																	<option value="${cs.sub_id}">${cs.sub_name}</option>
																</c:if>		
															</c:if>			
														</c:forEach>		 							
										</select>	
			<tr><td height="30"><td height="30">
				<tr><td height="30"><td height="30">
	<tr><th style="padding-left: 40px;text-align: left;height: 40px" colspan="2"><font size="4" color="#34495e"><u>Optional Subject</u></font>
	
	<tr><td style="padding-bottom: 20px;padding-left: 10px;height: 30px">7<td><select id="sub7s" name="sub7s"  style="width: 242px" onchange="sub7change()" tabindex="10">
											<option value="" selected>Select Optional Subject</option>
														<c:forEach items="${session.collegeSubject}" var="cs" varStatus="loop">
															<c:if test="${cs.group_id=='2'}">
																	<option value="${cs.sub_id}">${cs.sub_name}</option>
															</c:if>			
														</c:forEach>	
											<!-- 
    										<option value="304">History</option>
    										<option value="267">Islamic History & Culture</option>	
    										<option value="269">Civics & Good Governnance</option>
    										<option value="109">ECONOMICS</option>
    										<option value="117">SOCIOLOGY</option>
    										<option value="271">Social Work</option>
    										<option value="125">GEOGRAPHY</option>
    										<option value="121">LOGIC</option>     	
    										<option value="249">STUDIES OF ISLAM</option> 
    										<option value="123">PSYCHOLOGY</option> 
    										<option value="129">STATISTICS</option>
    										<option value="239">AGRI. STUDIES</option> 
    										<option value="290">Anthropology</option> 
    										<option value="273">Home Science</option>
    										<option value="225">ARTS & CRAFTS</option> 
    										<option value="151">MILITARY SCIENCE</option> 
    										<option value="215">LIGHT SONG</option> 
    										<option value="133">ARABIC</option> 
    										<option value="137">SHANSKRIT</option>
    										<option value="139">PALI</option>		
    										-->
										</select>		
	
		
</table>



<script type="text/javascript">
// save in database........log rakte hobe
// clear in showdata
	// onchange a check
	// Submit Data te check korte hobe
	//304,267	117,271
	function sub4change()
	{
		if($("#sub4s").val()==$("#sub5s").val()){$("#sub5s").val('');$("#sub5").val('');}
		if($("#sub4s").val()==$("#sub6s").val()){$("#sub6s").val('');$("#sub6").val('');}
		if($("#sub4s").val()==$("#sub7s").val()){$("#sub7s").val('');$("#sub7").val('');}
		
		if($("#sub4s").val()=='304' && $("#sub5s").val()=='267'){$("#sub5s").val('');$("#sub5").val('');}
		if($("#sub4s").val()=='267' && $("#sub5s").val()=='304'){$("#sub5s").val('');$("#sub5").val('');}
		if($("#sub4s").val()=='304' && $("#sub6s").val()=='267'){$("#sub6s").val('');$("#sub6").val('');}
		if($("#sub4s").val()=='267' && $("#sub6s").val()=='304'){$("#sub6s").val('');$("#sub6").val('');}
		if($("#sub4s").val()=='304' && $("#sub7s").val()=='267'){$("#sub7s").val('');$("#sub7").val('');}
		if($("#sub4s").val()=='267' && $("#sub7s").val()=='304'){$("#sub7s").val('');$("#sub7").val('');}
		
		if($("#sub4s").val()=='117' && $("#sub5s").val()=='271'){$("#sub5s").val('');$("#sub5").val('');}
		if($("#sub4s").val()=='271' && $("#sub5s").val()=='117'){$("#sub5s").val('');$("#sub5").val('');}
		if($("#sub4s").val()=='117' && $("#sub6s").val()=='271'){$("#sub6s").val('');$("#sub6").val('');}
		if($("#sub4s").val()=='271' && $("#sub6s").val()=='117'){$("#sub6s").val('');$("#sub6").val('');}
		if($("#sub4s").val()=='117' && $("#sub7s").val()=='271'){$("#sub7s").val('');$("#sub7").val('');}
		if($("#sub4s").val()=='271' && $("#sub7s").val()=='117'){$("#sub7s").val('');$("#sub7").val('');}
		
		$("#sub4").val($("#sub4s").val());
	}
	function sub5change()
	{
		if($("#sub5s").val()==$("#sub4s").val()){$("#sub4s").val('');$("#sub4").val('');}
		if($("#sub5s").val()==$("#sub6s").val()){$("#sub6s").val('');$("#sub6").val('');}
		if($("#sub5s").val()==$("#sub7s").val()){$("#sub7s").val('');$("#sub7").val('');}
		
		if($("#sub5s").val()=='304' && $("#sub4s").val()=='267'){$("#sub4s").val('');$("#sub4").val('');}
		if($("#sub5s").val()=='267' && $("#sub4s").val()=='304'){$("#sub4s").val('');$("#sub4").val('');}
		if($("#sub5s").val()=='304' && $("#sub6s").val()=='267'){$("#sub6s").val('');$("#sub6").val('');}
		if($("#sub5s").val()=='267' && $("#sub6s").val()=='304'){$("#sub6s").val('');$("#sub6").val('');}
		if($("#sub5s").val()=='304' && $("#sub7s").val()=='267'){$("#sub7s").val('');$("#sub7").val('');}
		if($("#sub5s").val()=='267' && $("#sub7s").val()=='304'){$("#sub7s").val('');$("#sub7").val('');}
		
		if($("#sub5s").val()=='117' && $("#sub4s").val()=='271'){$("#sub4s").val('');$("#sub4").val('');}
		if($("#sub5s").val()=='271' && $("#sub4s").val()=='117'){$("#sub4s").val('');$("#sub4").val('');}
		if($("#sub5s").val()=='117' && $("#sub6s").val()=='271'){$("#sub6s").val('');$("#sub6").val('');}
		if($("#sub5s").val()=='271' && $("#sub6s").val()=='117'){$("#sub6s").val('');$("#sub6").val('');}
		if($("#sub5s").val()=='117' && $("#sub7s").val()=='271'){$("#sub7s").val('');$("#sub7").val('');}
		if($("#sub5s").val()=='271' && $("#sub7s").val()=='117'){$("#sub7s").val('');$("#sub7").val('');}
		
		$("#sub5").val($("#sub5s").val());
	}	
	function sub6change()
	{
		if($("#sub6s").val()==$("#sub5s").val()){$("#sub5s").val('');$("#sub5").val('');}
		if($("#sub6s").val()==$("#sub4s").val()){$("#sub4s").val('');$("#sub4").val('');}
		if($("#sub6s").val()==$("#sub7s").val()){$("#sub7s").val('');$("#sub7").val('');}
		
		if($("#sub6s").val()=='304' && $("#sub5s").val()=='267'){$("#sub5s").val('');$("#sub5").val('');}
		if($("#sub6s").val()=='267' && $("#sub5s").val()=='304'){$("#sub5s").val('');$("#sub5").val('');}
		if($("#sub6s").val()=='304' && $("#sub4s").val()=='267'){$("#sub4s").val('');$("#sub4").val('');}
		if($("#sub6s").val()=='267' && $("#sub4s").val()=='304'){$("#sub4s").val('');$("#sub4").val('');}
		if($("#sub6s").val()=='304' && $("#sub7s").val()=='267'){$("#sub7s").val('');$("#sub7").val('');}
		if($("#sub6s").val()=='267' && $("#sub7s").val()=='304'){$("#sub7s").val('');$("#sub7").val('');}
		
		if($("#sub6s").val()=='117' && $("#sub5s").val()=='271'){$("#sub5s").val('');$("#sub5").val('');}
		if($("#sub6s").val()=='271' && $("#sub5s").val()=='117'){$("#sub5s").val('');$("#sub5").val('');}
		if($("#sub6s").val()=='117' && $("#sub4s").val()=='271'){$("#sub4s").val('');$("#sub4").val('');}
		if($("#sub6s").val()=='271' && $("#sub4s").val()=='117'){$("#sub4s").val('');$("#sub4").val('');}
		if($("#sub6s").val()=='117' && $("#sub7s").val()=='271'){$("#sub7s").val('');$("#sub7").val('');}
		if($("#sub6s").val()=='271' && $("#sub7s").val()=='117'){$("#sub7s").val('');$("#sub7").val('');}
		
		$("#sub6").val($("#sub6s").val());
	}
	
	function sub7change()
	{
		if($("#sub7s").val()==$("#sub5s").val()){$("#sub5s").val('');$("#sub5").val('');}
		if($("#sub7s").val()==$("#sub6s").val()){$("#sub6s").val('');$("#sub6").val('');}
		if($("#sub7s").val()==$("#sub4s").val()){$("#sub4s").val('');$("#sub4").val('');}
		
		if($("#sub7s").val()=='304' && $("#sub5s").val()=='267'){$("#sub5s").val('');$("#sub5").val('');}
		if($("#sub7s").val()=='267' && $("#sub5s").val()=='304'){$("#sub5s").val('');$("#sub5").val('');}
		if($("#sub7s").val()=='304' && $("#sub6s").val()=='267'){$("#sub6s").val('');$("#sub6").val('');}
		if($("#sub7s").val()=='267' && $("#sub6s").val()=='304'){$("#sub6s").val('');$("#sub6").val('');}
		if($("#sub7s").val()=='304' && $("#sub4s").val()=='267'){$("#sub4s").val('');$("#sub4").val('');}
		if($("#sub7s").val()=='267' && $("#sub4s").val()=='304'){$("#sub4s").val('');$("#sub4").val('');}
		
		if($("#sub7s").val()=='117' && $("#sub5s").val()=='271'){$("#sub5s").val('');$("#sub5").val('');}
		if($("#sub7s").val()=='271' && $("#sub5s").val()=='117'){$("#sub5s").val('');$("#sub5").val('');}
		if($("#sub7s").val()=='117' && $("#sub6s").val()=='271'){$("#sub6s").val('');$("#sub6").val('');}
		if($("#sub7s").val()=='271' && $("#sub6s").val()=='117'){$("#sub6s").val('');$("#sub6").val('');}
		if($("#sub7s").val()=='117' && $("#sub4s").val()=='271'){$("#sub4s").val('');$("#sub4").val('');}
		if($("#sub7s").val()=='271' && $("#sub4s").val()=='117'){$("#sub4s").val('');$("#sub4").val('');}
		
		$("#sub7").val($("#sub7s").val());
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
		else if($('#sub4s').val()=='')
		{
			alert('Please select compulsary subject 4');
			tmp = false;
		}	
		else if($('#sub5s').val()=='')
		{
			alert('Please select compulsary subject 5');
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