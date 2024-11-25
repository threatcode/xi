
<div class="box_container" style="margin-top: 5px;">
	<div class="box_header">
	SSC / Equivalent Examination Information
	</div>

	<div class="box_body" style="margin-top: 0px;margin-bottom: 0px;">
	  
	  <div id="ssc_input_div" style="padding-top: 20px;padding-left: 10px;">
	  
	  
	  	  <table width="100%" border="0" style="border: none;" id="ssc_input_table">
	  	  	<tr>
	  	  		<td width="25%" align="center" style="font-weight: bold;border: none;">Roll No.</td>
	  	  		<td width="25%" align="center" style="font-weight: bold;border: none;">Board</td>
	  	  		<td width="25%" align="center" style="font-weight: bold;border: none;">Passing Year</td>
	  	  		<td width="25%" align="center" style="font-weight: bold;border: none;">Reg No.</td> 
	  	  	</tr>
	  	  	<tr>
	  	  		<td align="center" style="border: none;"><input type="text" id="ssc_roll" style="width: 135px;text-align: center;" maxlength="15"/></td>
	  	  		<td align="center" style="border: none;">
	  	  			<select style="width:120px;" id="ssc_board" onchange="reloadYear(this.value)" style="width: 135px;">
				     <option value="">Select Board</option>
					<option value="15">Barishal</option>
					<option value="20">BOU</option>
					<option value="14">Chattogram</option>
					<option value="11">Cumilla</option>
					<option value="10" selected="selected">Dhaka</option>
					<option value="17">Dinajpur</option>
					<option value="13">Jashore</option>
					<option value="18">Madrasah</option>
					<option value="21">Mymensingh</option>
					<option value="12">Rajshahi</option>
					<option value="16">Sylhet</option>
					<option value="19">TEC</option>
				    </select>
	  	  		</td>
	  	  		<td align="center" style="border: none;">
	  	  			<select style="width:135px;" id="ssc_year" style="width: 135px;">
				     <option value="">Select Year</option>
				     <option value="2020">2020</option>
				     <option value="2019">2019</option>
				     <option value="2018">2018</option>
				     
				      <!-- 
				     <option value="2016">2016</option>
				     <option value="2015">2015</option>
				     <option value="2014">2014</option>
				    
				     <option value="2013">2013</option>
				     <option value="2012">2012</option>
				     <option value="2011">2011</option>
				     <option value="2010">2010</option>
				     <option value="2009">2009</option>
				     <option value="2008">2008</option>
				     <option value="2007">2007</option>
				     <option value="2006">2006</option>
				     <option value="2005">2005</option>
				      -->	
				    </select>
	  	  		</td>
	  	  		<td align="center" style="border: none;"><input type="text" id="ssc_reg" style="width: 135px;text-align: center;" maxlength="15"/></td>
	  	  		
	  	  		
	  	  	</tr>
	  	  	<tr>
	  	  		<td align="center" style="border: none;" colspan="4">&nbsp; </td>
	  	  	</tr>
	  	  	
	  	  	
	  	  	<tr>
	  	  	
	  	  	<td align="center" style="border: none;"></td>
	  	  	<td align="center" style="border: none;">
	         
	 	    
	        </td>

	  	  	
	  	  </table>
		  
	  </div>
	  

	  
	  
	</div>

	<div class="box_footer">
	
	 <input type="button" value="Next" onclick="validateSscInfo()" style="width:100px;"/>
	</div>
</div>