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
	
	<s:if test="(#request.appInfo.sscRegNo == null||#request.appInfo.sscRegNo == '' )">
 		 No information found
    
	</s:if>
	<s:elseif test="(#request.appInfo == null||#request.appInfo == '' )">
	    No information found.
	</s:elseif>
	<s:else> 	
		<table class="tg">
  			<tr> <td>Reg No.: </td> <td> <s:property value="#request.appInfo.sscRegNo"/> 	</td></tr>
  			<tr> <td> Name : </td>    		<td> 		<s:property value="#request.appInfo.applicantName"/> 		</td></tr>
  			<tr> <td>Father : </td>          		<td> 		<s:property value="#request.appInfo.fatherName"/>   		</td></tr>
  			<tr> <td>Mother : </td>     		<td>			<s:property value="#request.appInfo.motherName"/> 		</td></tr>
  			<tr> <td>Gender: </td>   		<td>			<s:property value="#request.appInfo.gender"/> 		</td></tr>
  			<tr> <td>Group: </td>   		<td>			<s:property value="#request.appInfo.groupName"/> 		</td></tr>
  			<tr> <td>GPA: </td>   		<td>			<s:property value="#request.appInfo.gpa"/> 		</td></tr>
  			<tr> <td>Contact No. : </td>   		<td>			<s:property value="#request.appInfo.mobilenumber"/> 		</td></tr>
  			<tr> <td>Transaction No. : </td>   		<td>			<s:property value="#request.appInfo.paymentStatus"/> 		</td></tr>
  			
  			<tr> <td><strong>Security Code :</strong> </td>  
  			<td> <strong>  <font color="red" size="14"> 
  			<s:property value="#request.appInfo.scode"/> 
  			</font>
  			</strong>		
  			</td>
  			
  			</tr>
  			
  			<tr> <td><strong>Application Status : </strong></td>   		
  				<td><strong><s:property value="#request.appInfo.applicationType"/> 	</strong>	</td></tr>
		  
	  </table>
	



	</s:else>

  </div>


<br>
<br>
<br>