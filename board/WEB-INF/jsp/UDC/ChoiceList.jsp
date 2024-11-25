<%@ taglib prefix="s" uri="/struts-tags"%>
<table class="rounded-corner" id="selection_row_table" border="1">
    <thead>
    	<tr id="header">
		 	<th width="20%" align="left">Application Id</th>
		 	<th width="36%" align="left">Applicant's Name</th>
		 	<th width="15%" align="left">Quota</th>
		 	<th width="15%" align="left">Passing Year</th>
		 	<th width="15%" align="left">Board</th>
		 	<th width="15%" align="left">Roll</th>
        </tr>
    </thead>
    <tbody>
    	<tr >
		 	<th width="20%" align="left"><s:property value="applicant.application_id"/></th>
		 	<th width="36%" align="left"><s:property value="applicant.ssc_info.student_name"/></th>
		 	<th width="15%" align="left"><s:if test='applicant.application_info.quota_ff=="Y"'>
	         FQ 
	       </s:if>
	       <s:if test='applicant.application_info.quota_ff=="Y" && applicant.application_info.quota_eq=="Y"'>
	         , 
	       </s:if>
	       <s:if test='applicant.application_info.quota_eq=="Y"'>
	          EQ
	       </s:if></th>
		 	<th width="15%" align="left"><s:property value="applicant.ssc_info.passing_year"/></th>
		 	<th width="15%" align="left"><s:property value="applicant.ssc_info.board_name"/></th>
		 	<th width="15%" align="left"><s:property value="applicant.ssc_info.roll"/></th>
        </tr>
    </tbody>

</table>

<br/>
<table class="rounded-corner" id="selection_row_table">
    <thead>
    	<tr id="header">
		 	<th width="5%" align="center">SL.</th>
		 	<th width="36%" align="left" style="padding-left: 2px;">College Name</th>
		 	<th width="9%" align="center">EIIN</th>
		 	<th width="9%" align="left" style="padding-left: 2px;">Shift</th>
		 	<th width="9%" align="left" style="padding-left: 2px;">Version</th>
		 	<th width="9%" align="left" style="padding-left: 2px;">Group</th>
		 	<th width="4%" align="center">SQ</th>
		 	<th width="5%" align="center">Via</th>
		 	<th width="9%" align="center">Priority</th>		 	
		 	<th width="9%" align="center">Payment</th>
        </tr>
    </thead>
   
    <tbody>
 	     
		
        <s:iterator value="choiceList" status="indx">
          
           <s:if test='payment_status == "Y"'>
           	<s:if test='application_type == "W"'>
           		<s:set name="web_payment" value='%{"Y"}' />
           	</s:if>
           </s:if>
           
           <s:if test='payment_status == "Y"'>
        		<tr  style="background-color: green" id="row_<s:property value='#indx.count' />">
        	</s:if>        	
        	<s:else>
        		<tr  style="background-color: red" id="row_<s:property value='#indx.count' />">
        	</s:else>

	            <td align="center"><s:property value="#indx.count" /></td>
	            <td align="left"><s:property value="college_name"/></td>
	            <td align="center"><s:property value="eiin"/></td>
	            <td align="left"><s:property value="shift_name"/></td>
	            <td align="left"><s:property value="version_name"/></td>
	            <td align="left"><s:property value="group_name"/></td>	            
	            <td align="center"><s:property value="special_quota"/></td>
	            <td align="center">
	                 <s:if test='application_type == "W"'>
	            		<img src="/board/resources/images/laptop.png" />   
	            	</s:if>
	            	<s:else>
	            		<img src="/board/resources/images/mobile.png" />
	            	</s:else>	            
	            </td>	      
	            <td align="center">
			            <s:property value='priority' />
	            </td>      
	            <td align="center">
	                 <s:if test='payment_status == "Y"'>
	            		Yes   
	            	</s:if>
	            	<s:else>
	            		No
	            	</s:else>	            
	            </td>	      
            	   
          
           
        	</tr>
        
        </s:iterator>
    	        
    </tbody>
</table>
<br/>
<script type="text/javascript">
var application_id="<s:property value='applicant.application_id' />";
function fetchReport(){
	window.open("udcChoiceListReport.action?application_id="+application_id,'1433313485446','width=780,height=850,toolbar=0,menubar=0,location=0,status=1,scrollbars=1,resizable=0,left=0,top=0');
}
</script>
<input type="button" value="Print Version" onclick="fetchReport()" />