<%@ taglib prefix="s" uri="/struts-tags"%>
		<s:if test="%{admission_result.tmp_status == 'KO'}">
		   <font style="font-size: 16px;font-weight:bold;color: red;">
		   	Sorry, you are not in the first Merit List. You are advised to follow the 2nd Merit List.
		   </font>
		</s:if>
		<s:if test="%{admission_result.tmp_status == 'OK'}">
		   <!--<center><img src="/board/resources/images/congratulations.gif" width="300" height="150" /></center>-->
		   <table width="600px" border="0">
		    <tr>
		    	<td colspan="2" align="left" style="font-size: 14px;font-weight: bold;">
		    		You (<s:property value="admission_result.application_id" />) are selected for the following:
		    	</td>
		    	<s:if test='#session.admissionResult.auto_migration=="Y"'>
		    		<td rowspan="4">
		    			<span style="color: green; font-size: 15px;">College Migration Applied</span>
		    		</td>
		    	</s:if>
		    </tr>
		   	<tr>
		   		<td width="130px" align="left" style="font-size: 14px;font-weight: bold;">College Name :</td>
		   		<td width="250px" align="left" style="font-size: 14px;"><s:property value="admission_result.college_name" /></td>
		   	</tr>
		   	<tr><td align="left" style="font-size: 14px;font-weight: bold;">Shift :</td><td align="left" style="font-size: 14px;"><s:property value="admission_result.shift_name" /></td></tr>
		   	<tr><td align="left" style="font-size: 14px;font-weight: bold;">Version :</td><td align="left" style="font-size: 14px;"><s:property value="admission_result.version_name" /></td></tr>
		   	<tr><td align="left" style="font-size: 14px;font-weight: bold;">Group :</td><td align="left" style="font-size: 14px;"><s:property value="admission_result.group_name" /></td></tr>
		   	<tr>
		   		<td align="left" style="font-size: 14px;font-weight: bold;">Merit Type :</td>
		   		<td align="left" style="font-size: 14px;">
			   		<s:if test="%{admission_result.quota_type == null}">
			   			<s:property value="admission_result.merit_type" />
			   		</s:if>
			   		<s:if test="%{admission_result.quota_type != ''}">
			   			<s:property value="admission_result.quota_type" />
			   		</s:if>		   		 	   					   		
		   		</td>
		   	</tr>
		   	<s:if test="%{admission_result.priority_order != 1}">
		   			<!--<td width="250px" align="left" colspan="2"><span style="font-size: 18px; color: green;">Want to apply for college migration <strong><a href="http://app.xiclassadmission.gov.bd/board/login" >Click Here</a></strong> </span></td>-->
		   	</s:if>
		   </table>
		   
		</s:if>