<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2>Add New SQ Student</h2>
			<div class="sidebar_section_text">
<center>

<!---- body ----->		
            <center>
            <table width="100%" border="0" style="border: none;" id="ssc_input_table">
	  	  	<tr>
	  	  		<td width="25%" align="center" style="font-weight: bold;border: none;">Roll No.</td>
	  	  		<td width="25%" align="center" style="font-weight: bold;border: none;">Board</td>
	  	  		<td width="25%" align="center" style="font-weight: bold;border: none;">Passing Year</td>
	  	  		<td width="25%" align="center" style="font-weight: bold;border: none;">Reg No.</td> 
	  	  	</tr>
	  	  	<tr>
	  	  		<td align="center" style="border: none;"><input type="text" id="ssc_roll" name="ssc_roll" style="width: 135px;text-align: center;" maxlength="15"   required/></td>
	  	  		<td align="center" style="border: none;">
	  	  			<select style="width:120px;" id="ssc_board" name="ssc_board" onchange="reloadYear(this.value)" style="width: 135px;">
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
	  	  			<select style="width:135px;" id="ssc_year" name="ssc_year" style="width: 135px;">
				     <option value="">Select Year</option>
				     <option value="2020">2020</option>
				     <option value="2019">2019</option>
				     <option value="2018">2018</option>
				     <!--
				     <option value="2017">2017</option>				    
				     <option value="2016">2016</option>
				     <option value="2015">2015</option> 
				     <option value="2014">2014</option>
				     <option value="2013">2013</option>
				     <option value="2012">2012</option>
				     <option value="2011">2011</option>
				     <option value="2010">2010</option>
				     <option value="2009">2009</option>
				     <option value="2008">2008</option>
				      -->	
				    </select>
	  	  		</td>
	  	  		<td align="center" style="border: none;"><input type="text" id="ssc_reg" name="ssc_reg" style="width: 135px;text-align: center;" maxlength="10" required/></td>
	  	  		
	  	  		
	  	  	</tr>
	  	  	<tr>
	  	  	<td></td>
	  	  	<td></td>
	  	  	<td></td>
	  	  	<td>&nbsp</td>
	  	  	</tr>
	  	  	<tr >
	  	  	<td></td>
	  	  	<td></td>
	  	  	<td></td>
			<td align="center" style="border: none;"><input type="button" style="width:100px;padding: 1px 12px;" class="btn btn-info" onclick="searchsqstudent()" name="searchsqstudent" value="SEARCH"  /></td>
			</tr>
	  	  	
	  	  	
	  	  </table>
            
			<!-- <table  style="width:400px;"  class="table table-bordered table-striped cf">
				<tr >
					<td>Roll No:</td>
					<td><input type="text" id="applicationID" name="applicationID" style="width:150px;" maxlength="15"   required/></td>
					
					
				</tr >

                <tr >
					<td colspan="2" align="right"><input type="button" style="width:100px;padding: 1px 12px;" class="btn btn-info" onclick="searchsqstudent()" name="searchsqstudent" value="SEARCH"  /></td>
				</tr>
			</table> -->

			</center>
<div id="showsqstudentInfo">

</div>			
<!----body ------>	

</center>

</div>
		</div>
		<br>

	</div>


	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp" %>
</div>

   <script type="text/javascript">

      function searchsqstudent() {

       	  if(document.getElementById("ssc_roll").value==""||document.getElementById("ssc_board").value==""||document.getElementById("ssc_year").value==""||document.getElementById("ssc_reg").value=="")
        {
          // alert("Please Give Application ID of student");
           alert("Please Fill All Information");
           return;
        }

       $('#showsqstudentInfo').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "shownewsqstudent",
            dataType: 'text',
            async   : false,
            data    : {

                ssc_roll: $("#ssc_roll").val(),ssc_board: $("#ssc_board").val(),ssc_year: $("#ssc_year").val(),ssc_reg: $("#ssc_reg").val()
            }
        }).done(function (msg) {
                    $("#showsqstudentInfo").html(msg);
                })
                .always(function () {
                    //$('#sw-val-step-3').unmask();
                })
                .fail(function (data) {
                    if (data.responseCode)
                        alert(data.responseCode);
                });

 }
     //End of fetchApplicationInformation


</script>	
    <script type="text/javascript">

 
 	  	function checkInput(ob) {
 	  	 var invalidChars = /[^0-9]/gi
	        /* var invalidChars = /^[0-9]-/; */
	        if(invalidChars.test(ob.value)) {
	            ob.value = ob.value.replace(invalidChars,"");
	        }
        }

</script>


<%@ include file="/WEB-INF/jsp/template/footerLite.jsp" %>

</div>
</body>
</html>