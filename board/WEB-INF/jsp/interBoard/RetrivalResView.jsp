<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Arrays"%>


<style>
div.transbox {
  margin: 20px;
  background-color: #f0f8ff;
  border: 1px solid black;
  opacity: 0.9;
  filter: alpha(opacity=90); /* For IE8 and earlier */
}

div.transbox p {
  margin: 5%;
  font-weight: bold;
  color: #000000;
}

div.cities {
    background-color: white;
    color: black;
    margin: 0px;
    padding: 20px;
} 


.tg  {border-collapse:collapse;border-spacing:0;border-color:#aabcfe;}
.tg td{font-family:Arial, sans-serif;font-size:14px;padding:6px 10px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;}
.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:6px 10px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#039;background-color:#b9c9fe;}
.tg .tg-rbys{font-size:12px;font-family:"Palatino Linotype", "Book Antiqua", Palatino, serif !important;;vertical-align:top}



</style>


	<div class="cities">
	
	<s:if test="(#request.appInfo.sscRegNo == null||#request.appInfo.sscRegNo == ''||#request.appInfo == null  )">
 		 No information found.
    
	</s:if>

	<s:else> 
	
		<table class="tg">
  			
  			<tr> <td> Name : </td>    		<td> 		<s:property value="#request.appInfo.applicantName"/> 		</td></tr>
  			<tr> <td>Science Rank : </td>          		<td> <s:property value="#request.appInfo.srank"/>   		</td></tr>
  			<tr> <td>All Rank : </td>          		<td> <s:property value="#request.appInfo.arank"/>   		</td></tr>
  			<tr> <td>SSC EIIN : </td>     		<td>	<s:property value="#request.appInfo.eiinCode"/> 		</td></tr>
  			 <tr> <td>CONTACT NO : </td>     		<td>	<s:property value="#request.appInfo.mobilenumber"/> 		</td></tr>
  			 
  			 <tr>  <td colspan="2">   </td>  			 </tr> 		
  			  			  
	  </table>
	
	<br>
		
		<table class="tg">
		
			 <thead>
                                        <tr>
                                       		 <th>EIIN</th>
                                            <th>College</th>
                                            <th>Shift</th>
                                            <th>Version</th>
                                            <th>Group</th>
                                            <th>Priority</th>
                                            <th>Last Rank</th>

                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                    
                                    <c:forEach items="${requestScope.svgList}" var="svg">
										<tr>
											<td>${svg.eiin}</td>
											<td>${svg.college_name}</td>
											<td>${svg.shift_id}</td>
											<td>${svg.version_id}</td>
											<td>${svg.group_id}</td>
											<td>${svg.priority}</td>
											<td>${svg.maxrank}</td>
											
										</tr>		
									</c:forEach>
									</tbody>
									
		</table>

	</s:else>

  </div>


<br>
<br>
<br>