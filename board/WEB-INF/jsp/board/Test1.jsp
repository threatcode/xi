
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="/board/resources/css/tabledata/jquery.dataTables.css" type="text/css"></link>
<link rel="stylesheet" href="/board/resources/js/jquery.validity.1.2.0/jquery.validity.css" type="text/css"></link>


<script type="text/javascript" src="/board/resources/js/jquery.validity.1.2.0/jQuery.validity.js"></script>




<SCRIPT lang="javascript">

	 $(function() { 
	 
	 		//$.validity.setup({outputMode:"summary"});
	 		
	 		
	 		
	 		var table = document.getElementById("svgTable");               
	 		
                $("form").validity(function() {                    
                      
                      for(var i = 1; i < table.rows.length; i++) {  
                        
                        $("#shiftid_"+i)
                        	.equalCheck("-1","Select Shift Name");  
                        
                        $("#verid_"+i)
                        	.equalCheck("-1","Select Version Name");
                        
                        $("#groupid_"+i)
                        	.equalCheck("-1","Select Group Name");
                        	
                        
                        $("#seat_"+i)
	                        .require()
	                        .match("integer")
	                        .range(1, 999);
                        
                        $("#gpa_"+i)
	                        .require()
	                        .match("number")
	                        .range(0.5, 5);
	                                        
                        
                      }
                      
                      alert("input------------>"+ $('input[name^="svg"]'));
                      
                     $('input[name^="svg"]')
                        .distinct("SVG is repeated.");
                        
                        
                        
                        
                        
                });
            });


	function checkSVG()
	{
		var table = document.getElementById("svgTable");               
	 		
                $("form").validity(function() {                    
                      
                      for(var i = 1; i < table.rows.length; i++) {  
                        
                        $("#shiftid_"+i)
                        	.equalCheck("-1","Select Shift Name");  
                        
                        $("#verid_"+i)
                        	.equalCheck("-1","Select Version Name");
                        
                        $("#groupid_"+i)
                        	.equalCheck("-1","Select Group Name");
                        	
                        
                        $("#seat_"+i)
	                        .require()
	                        .match("integer")
	                        .range(1, 999);
                        
                        $("#gpa_"+i)
	                        .require()
	                        .match("number")
	                        .range(0.5, 5);
	                                        
                        
                      }
                      
                      alert("input------------>"+ $('input[name^="svg"]'));
                      
                     $('input[name^="svg"]')
                        .distinct("SVG is repeated.");
                        
                        
                        
                        
                        
                });
			
	}

					
 	  function addRow(tableID) 
        {
                var table = document.getElementById(tableID);

                var rowCount = table.rows.length;
                var row = table.insertRow(rowCount);
                var counts = rowCount - 1;

                var cell1 = row.insertCell(0);
                var sid = document.createElement("select");
                sid.setAttribute("name", "shiftid");
                sid.setAttribute("id", "shiftid_"+rowCount);  
                sid.setAttribute("style", "width: 70px");  
                
                
                 try {
				        sid.addEventListener('change', function(){
				         setSVG(rowCount);checkSVG()
				        }, false)
				    } 
				    catch (e) {
				        sid.attachEvent('onchange', function(){
				            setSVG(rowCount)
				        }, false)
				    }             	
                
                try {
				        sid.addEventListener('change', function(){
				         checkSVG()
				        }, false)
				    } 
				    catch (e) {
				        sid.attachEvent('onchange', function(){
				            checkSVG()
				        }, false)
				    }        
                
                                                                             
					var opElement=document.createElement("option");
					opElement.setAttribute("value","-1");
					opElement.appendChild(document.createTextNode("Select"));	
					opElement.setAttribute("selected","selected");
					sid.appendChild(opElement);
				
				<s:iterator value="shift">
					var opElement=document.createElement("option");
					opElement.setAttribute("value",'<s:property value="key"/>');
					opElement.appendChild(document.createTextNode('<s:property value="value" />'));	
					
					sid.appendChild(opElement);				
				</s:iterator>	
				cell1.appendChild(sid);	
				
				
                var cell2 = row.insertCell(1);                
				var verid = document.createElement("select");
                verid.setAttribute("name", "verid");
                verid.setAttribute("id", "verid_"+rowCount);
                verid.setAttribute("style", "width: 70px");
                               
                      try {
				        verid.addEventListener('change', function(){
				         setSVG(rowCount)
				        }, false)
				    } 
				    catch (e) {
				        verid.attachEvent('onchange', function(){
				            setSVG(rowCount)
				        }, false)
				    }                     
                         
                         
                     try {
				        verid.addEventListener('change', function(){
				          checkSVG()
				        }, false)
				    } 
				    catch (e) {
				        verid.attachEvent('onchange', function(){
				             checkSVG()
				        }, false)
				    }                  
                         
                              
                               
					var opElement=document.createElement("option");
					opElement.setAttribute("value","-1");
					opElement.appendChild(document.createTextNode("Select"));	
					opElement.setAttribute("selected","selected");
					verid.appendChild(opElement);
				
				<s:iterator value="versi">
					var opElement=document.createElement("option");
					opElement.setAttribute("value",'<s:property value="key"/>');
					opElement.appendChild(document.createTextNode('<s:property value="value" />'));	
					
					verid.appendChild(opElement);				
				</s:iterator>	
				cell2.appendChild(verid);	



                var cell3 = row.insertCell(2);
                cell3.id = "itemcol_" + rowCount;
				var groupid = document.createElement("select");
                groupid.setAttribute("name", "groupid");
                groupid.setAttribute("id", "groupid_"+rowCount);
                groupid.setAttribute("style", "width: 130px");
                
                  try {
				        groupid.addEventListener('change', function(){
				         setSVG(rowCount)
				        }, false)
				    } 
				    catch (e) {
				        groupid.attachEvent('onchange', function(){
				            setSVG(rowCount)
				        }, false)
				    }             
                
					var opElement=document.createElement("option");
					opElement.setAttribute("value","-1");
					opElement.appendChild(document.createTextNode("Select"));	
					opElement.setAttribute("selected","selected");
					groupid.appendChild(opElement);
				
				<s:iterator value="group">
					var opElement=document.createElement("option");
					opElement.setAttribute("value",'<s:property value="key"/>');
					opElement.appendChild(document.createTextNode('<s:property value="value" />'));	
					
					groupid.appendChild(opElement);				
				</s:iterator>	
				cell3.appendChild(groupid);	


				
				var cell4 = row.insertCell(3);                
				var gender = document.createElement("select");
                gender.setAttribute("name", "gender");
                gender.setAttribute("id", "gender_"+rowCount);
                gender.setAttribute("style", "width: 100px");
                               
					var opElement=document.createElement("option");
					opElement.setAttribute("value","-1");
					opElement.appendChild(document.createTextNode("Select"));	
					opElement.setAttribute("selected","selected");
					gender.appendChild(opElement);
				
				<s:iterator value="mgender">
					var opElement=document.createElement("option");
					opElement.setAttribute("value",'<s:property value="key"/>');
					opElement.appendChild(document.createTextNode('<s:property value="value" />'));	
					
					gender.appendChild(opElement);				
				</s:iterator>	
				cell4.appendChild(gender);			
			
			



                var cell5 = row.insertCell(4);
                var seat = document.createElement("input");
                seat.type = "text";
                seat.name = "seat";
                seat.id = "seat_"+rowCount;
                seat.title="Total Seat"
                seat.setAttribute("class", "short");
                seat.setAttribute("style", "width: 40px");
                cell5.appendChild(seat);
                
                var cell6 = row.insertCell(5);
                var gpa = document.createElement("input");
                gpa.type = "text";
                gpa.name = "gpa";  
                gpa.id = "gpa_"+rowCount;
                gpa.title="Minimum GPA"              
                gpa.setAttribute("class", "short");
                gpa.setAttribute("style", "width: 40px");
                cell6.appendChild(gpa);
                 
                 
                var cell7 = row.insertCell(6);
                var ogpa = document.createElement("input");
                ogpa.type = "text";
                ogpa.name = "ownGpa";  
                ogpa.id = "ownGpa_"+rowCount;        
                ogpa.setAttribute("class", "short");
                ogpa.setAttribute("style", "width: 40px");
                cell7.appendChild(ogpa);  
                 
                 
                var cell8 = row.insertCell(7);
                var spQuota = document.createElement("input");
                spQuota.type = "text";
                spQuota.name = "spQuota";  
                spQuota.id = "spQuota_"+rowCount;        
                spQuota.setAttribute("class", "short");
                spQuota.setAttribute("style", "width: 40px");
                cell8.appendChild(spQuota); 
                 
                 
                var cell9 = row.insertCell(8);
                var spGpa = document.createElement("input");
                spGpa.type = "text";
                spGpa.name = "spGpa";  
                spGpa.id = "spGpa_"+rowCount;        
                spGpa.setAttribute("class", "short");
                spGpa.setAttribute("style", "width: 40px");
                cell9.appendChild(spGpa); 
                 
                 
                var cell10 = row.insertCell(9);
                var reserve = document.createElement("input");
                reserve.type = "text";
                reserve.name = "reserve";  
                reserve.id = "reserve_"+rowCount;        
                reserve.setAttribute("class", "short");
                reserve.setAttribute("style", "width: 40px");
                cell10.appendChild(reserve);  
                 
                 
                 
                 
                var newCell = row.insertCell(10);
				newCell.align = "left";
				a = "<a href=\"#\" onclick=\"return removerow(this)\" ><img src='/board/resources/images/delete_over.png'></a>";				
				newCell.innerHTML = a;
             
             	
             	
             	
             
             
        }
        
        function removerow(element) {
		try {
	
		var r = element.parentNode.parentNode;
		var index = r.sectionRowIndex;
		var table2 = r.parentNode;
		table2.deleteRow(index);
		
		return false;
		}
		catch (e) {
			alert(e);
		}
	}
        
        
        
function setSelectedIndex(s, v) {

//alert(s+"->"+v);
    for ( var i = 0; i < s.options.length; i++ ) {
        if ( s.options[i].value == v ) {
            s.options[i].selected = true;
            return;
        }
    }
 //alert(s.options.length);   
    
}       
        
        
function setSVG(rowcount){

	//alert(rowcount);
	var shift = document.getElementById("shiftid_"+rowcount);
	var version = document.getElementById("verid_"+rowcount);
	var group = document.getElementById("groupid_"+rowcount);
	
		
	var svg = shift.value+version.value+group.value;
	
	document.getElementById("svg_"+rowcount).value = svg;

}        
        
        
        
</SCRIPT>




<div>
<form id="mainform1" class="register" method="post" action="">

<h5>College Information</h5>


<table class="display dataTable" id="collegeTable"  >
	<tr>
		<th width="10%">College </th> <td colspan="5"> <input type="text" size="50" name="collegeName" value='<s:property value="collegeDTO.college_name" />' > </td>
		
		
		
		
	</tr>

	<tr>
		<th width="10%"> District</th> <td width="20%" ><select name="districtID" id="districtID" onchange="fetchThana(this.value,0);">
										<option value="none" selected>Select District</option>
											<c:forEach items="${districtinfoList}" var="districts">
												<option value="${districts.districtID}">${districts.districtName}
												</option>
											</c:forEach>								
								</select> </td>
		<th width="10%">Thana</th>  <td colspan="3">   <select name="thanaID" id="thanaID"> 	
									</select> </td>
	</tr>



	



	<tr>
		
		<th>IsMetro </th> <td><select name="metro" id="metro" style="width: 55px;">	
								<option value="Y"> Y</option>
								<option value="N"> N</option>
							</select> </td>
		<th>IsZilaSadar </th> <td><select name="zilasader" id="zilasader" style="width: 55px;"> 		
									<option value="Y"> Y</option>
									<option value="N"> N</option>
								</select> </td>	
								
		<th>IsGovt</th><td> <select name="govt" id="govt" style="width: 55px;"> 		
							<option value="Y"> Y</option>
							<option value="N"> N</option>
						</select></td>
					
		</tr>
		<tr>			
														
		<th>IsActive </th> <td> <select name="active" id="active" style="width: 55px;"> 	
						<option value="0"> N</option>
						<option value="1"> Y</option>
					</select> </td>		
					
		<th>Mobile </th> <td colspan="3" > <input type="text" name="mobile" value='<s:property value="collegeDTO.collegeMobile" />' > </td>							
	
	</tr>


</table>




 <script type="text/javascript"> 	
 
fetchThana(<s:property value="collegeDTO.dist_id"/>,<s:property value="collegeDTO.thana_id"/>);	
//alert("fetchthana");
setSelectedIndex(document.getElementById('districtID'),<s:property value="collegeDTO.dist_id"/>);  
setSelectedIndex(document.getElementById('active'),<s:property value="collegeDTO.active"/>);  
setSelectedIndex(document.getElementById('govt'),'<s:property value="collegeDTO.govt"/>');
setSelectedIndex(document.getElementById('zilasader'),'<s:property value="collegeDTO.zilasader"/>');
setSelectedIndex(document.getElementById('metro'),'<s:property value="collegeDTO.metro"/>');
	            
		                      
</script>


</form>
</div>

<div>
<form id="mainform" class="register" method="post" action="">

<br><br>
	<h5>SVG Information</h5>
	
	
	

	<table class="display dataTable" id="svgTable" >

        <tr>
                <th>Shift</th>
                <th>Version</th>
                <th>Group</th>
                <th>Gender </th>
                <th>Total Seat</th>
                <th>Minimum GPA</th>
                <th> Own GPA</th> 
                <th>SP. Quota </th>
                <th>Sp. GPA </th>
                <th>Reserve </th>              
                <th> Delete</th>
                
        </tr>
        
   
        
         <% 
        int rowcount=1;        %>
        
        <s:set var="counter" value="1"/>

	<s:iterator value="svgList">
        <tr>
        
             
        
                <td>                
	               	             	                
	                <select name="shiftid" id="shiftid_<%=rowcount %>" style="width: 70px" onchange="setSVG(<%=rowcount %>)">
										<option value="-1" >Select</option>
											<c:forEach items="${shift}" var="shift" >
												<option value="${shift.key}">${shift.value}
												</option>
											</c:forEach>								
								</select>
								
								
							
	             
                </td>
                
                <td> 	                
	                 <select name="verid" id="verid_<%=rowcount %>" style="width: 70px" onchange="setSVG(<%=rowcount %>)">
										<option value="-1" >Select</option>
											<c:forEach items="${versi}" var="versi" >
												<option value="${versi.key}">${versi.value}
												</option>
											</c:forEach>								
								</select>	                
						

                </td>
                <td id="itemcol_<%=rowcount %>">                
	            	                
	                 <select name="groupid" id="groupid_<%=rowcount %>" style="width: 130px"  onchange="setSVG(<%=rowcount %>)">
										<option value="-1" >Select</option>
											<c:forEach items="${group}" var="group" >
												<option value="${group.key}">${group.value}
												</option>
											</c:forEach>								
								</select>                
	                
                </td>
                
                
                <td>
                		<select name="gender" id="gender_<%=rowcount %>" style="width: 100px">  
                				<option value="-1" >Select</option>
                				<c:forEach items="${mgender}" var="mgender" >
												<option value="${mgender.key}">${mgender.value}
												</option>
											</c:forEach>		
                		</select>
                		
                </td>
                
                <td> 
                	<INPUT  type="text" name="seat" id="seat_<%=rowcount %>" title="Total Seat" 
                	class="short" value='<s:property value="totalSeat" />' style="width: 40px"/>  
                </td>
                <td> 
                	<INPUT type="text" name="gpa" id="gpa_<%=rowcount %>" title="Minmum GPA" 
                	class="short" value='<s:property value="gpa" />' style="width: 40px"/> 
                </td>
                 <td> 
                	<INPUT type="text" name="ownGpa" id="ownGpa_<%=rowcount %>" title="Own GPA" 
                	class="short" value='<s:property value="ownGpa" />' style="width: 40px"/> 
                </td>
                
                 <td> 
                	<INPUT type="text" name="spQuota" id="spQuota_<%=rowcount %>"
                	class="short" value='<s:property value="spQuota" />' style="width: 40px"/> 
                </td>
                 <td> 
                	<INPUT type="text" name="spGpa" id="spGpa_<%=rowcount %>" 
                	class="short" value='<s:property value="spGpa" />' style="width: 40px"/> 
                </td>
                
                <td> 
                	<INPUT type="text" name="reserve" id="reserve_<%=rowcount %>" 
                	class="short" value='<s:property value="reserve" />' style="width: 40px"/> 
                </td>
                
                <td>
				<a href="#" onclick='javascript:removerow(this);'> <img src='/board/resources/images/delete_over.png'> </a>
				
				<input type="hidden" name="svg" id="svg_<%=rowcount %>" value='<s:property value="shiftID"/><s:property value="versionID"/><s:property value="groupId"/>'   />
				
				</td> 
											
        </tr>
       
       			 <script type="text/javascript"> 	
		             setSelectedIndex(document.getElementById('shiftid_<%=rowcount %>'),<s:property value="shiftID"/>);  
		             setSelectedIndex(document.getElementById('verid_<%=rowcount %>'),<s:property value="versionID"/>);  
		             setSelectedIndex(document.getElementById('groupid_<%=rowcount %>'),<s:property value="groupId"/>); 
		             setSelectedIndex(document.getElementById('gender_<%=rowcount %>'),'<s:property value="gender"/>');   
		                      
	   		 	</script>
                
        <% rowcount++; %>
        <s:set var="counter" value="%{#counter+1}"/>
        
       </s:iterator>
</table>
<br>


<INPUT type="button" value="Add More" onclick="addRow('svgTable')" class="short"/> 




<div class="validity-summary-container">

<ul></ul>

</div>


<div ><button class="button" >Submit &raquo;</button></div>

</form>

</div>
