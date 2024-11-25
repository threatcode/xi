<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %> --%>
<style>
#modelNoSelection {
/* position:absolute;
background:#F5F5DC; */
/* width:400px;
height:400px; */
/* border:5px solid #000;
z-index: 9002; */
}
</style>

<center>

<!---- body ----->		

<c:if test="${not empty requestScope.ErrorMassage}">

			  	
 <div class="alert alert-info">
<button class="close" type="button" data-dismiss="alert">×</button>
<span class="entypo-info-circled"></span>
<strong>Message:</strong>
  ${requestScope.ErrorMassage}
</div>
			  	

  </c:if>

<hr> 
   <div class="row">                      
                            <div class="box-body table-responsive">
                                <table id="example1" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <!-- <th>APPLICATION ID</th> -->
                                            <th>SL. NO</th>
                                            <th>NAME</th>
                                            <th>SSC ROLL</th>
                                            <th>SSC BOARD</th>
                                            <th>SSC YEAR</th> 
                                            <s:if test="!(#request.userInfo.eiin.equalsIgnoreCase('131962') || #request.userInfo.eiin.equalsIgnoreCase('108274') || #request.userInfo.eiin.equalsIgnoreCase('108259') )">
                                            	<th>Action</th>
                                            </s:if>
                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                 
			
				        <c:forEach items="${requestScope.lstAlreadyAdded}" var="alreadyAddedDTO" varStatus="loop">
							<tr>
								<td>${loop.index+1}</td> 
								<td>${alreadyAddedDTO.applicantName}</td> 
	 							<td>${alreadyAddedDTO.sscRollNo}</td>
								<td>${alreadyAddedDTO.boardName}</td>
								<td>${alreadyAddedDTO.sscPassingYear}</td>
								<s:if test="!(#request.userInfo.eiin.equalsIgnoreCase('131962') || #request.userInfo.eiin.equalsIgnoreCase('108274') || #request.userInfo.eiin.equalsIgnoreCase('108259') )">
									<td>
										<input style="background-color: red; color: white" type="button" id="${loop.index+1}" name="${alreadyAddedDTO.sscRollNo}###${alreadyAddedDTO.boardID}###${alreadyAddedDTO.sscPassingYear}###${alreadyAddedDTO.applicantName}###${alreadyAddedDTO.applicationID}" value="Delete" onclick="deleteData(this)">
									</td>
								</s:if>									
								
							</tr>		
						</c:forEach>
 

                                </table>
                               </div>
        
</div>
<!----body ------>	

</center>


<script type="text/javascript" >
	var myTable;
	$(document).ready(function() {
		myTable = $('#example1').dataTable();
		$('#example2').dataTable();
	} );
	
	function deleteData1(tmp)
	{
		var res = tmp.name.split("###");
		alert('Do you want to delete student ('+res[0]+','+res[3]+')!!!');
	}		
	function deleteData(tmp)
	{
		//alert(tmp.name);
		var res = tmp.name.split("###");
		if(!confirm('Do you want to delete student ('+res[0]+','+res[3]+')!!!'))
		{
			return false;
		}
        $.ajax({
            type    : "POST",
            url     : "deleteAdded",
            dataType: 'text',
            async   : false,
            data    : {

                eiinCode: tmp.name ,pShift_id: $("#shiftId").val(),applicationID:res[4],pVersion_id: $("#versionId").val(),
                pGroup_id: $("#groupId").val()
            }
        }).done(function (msg) {
                    $("#loader").html('');
                    $("#showapplicant").html('');
                    $("#showapplicant").html(msg);
                })
                .always(function () {
                    //$('#sw-val-step-3').unmask();
                })
                .fail(function (data) {
                    if (data.responseCode)
                        alert(data.responseCode);
                });
                
	}
</script> 

<link rel="stylesheet" type="text/css" href="/board/resources/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/board/resources/css/dataTables.bootstrap.css" />


<script type="text/javascript" language="javascript" src="/board/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript" src="/board/resources/js/dataTables.bootstrap.js"></script>


    <style>
.buttonCancel:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(255,0,0,0.2);
}
.buttonCSV:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,255,0,0.2);
}
</style>
