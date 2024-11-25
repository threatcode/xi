<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

				<center>
					<table style="width: 400px; font-size: 14px"
						class="table table-bordered table-striped cf">
						<tr>
							<td style="color: black;">
								<strong>Shift </strong>
							</td>
							<td style="color: black;">
								<strong>Version</strong>
							</td>
							<td style="color: black;">
								<strong>Group</strong>
							</td>
						</tr>

						<tr>
							<td>
								<select name="shiftId" id="shiftId">
									<option value="none" selected>
										Select Shift
									</option>
									<c:forEach items="${shiftList}" var="shift">
										<option value="${shift.shiftId}">
											${shift.shiftName}
										</option>
									</c:forEach>

								</select>
							</td>

							<td>
								<select name="versionId" id="versionId">
									<option value="none" selected>
										Select Version
									</option>
									<c:forEach items="${versionList}" var="version">
										<option value="${version.versionId}">
											${version.versionName}
										</option>
									</c:forEach>
								</select>
							</td>

							<td>
								<select name="groupId" id="groupId" onchange="changeGroup()">
									<option value="none" selected>
										Select Group
									</option>
									<c:forEach items="${groupList}" var="group">
										<option value="${group.groupId}">
											${group.groupName}
										</option>
									</c:forEach>

								</select>
							</td>
							<td>
									<input type="submit" style="width:140px;padding: 1px 12px;" class="btn btn-info" onclick="return searchStudent()" value="Show Student" >
							</td>
						</tr>
					</table>


						<div id="loader1"></div>
						<div id="showapplicant1"></div>
  	<!-- Popup modal div start -->
    	<div id="modelWindow" class="modal fade" data-backdrop="static" data-keyboard="false" role="dialog">
		  <div class="modal-dialog">		
		    <!-- Modal content-->
		    <div class="modal-content" style="color: black;">
		      <div class="modal-header">
		         <button type="button" class="close" data-dismiss="modal">&times;</button> 
		         <h5 class="modal-title" ><font style="color: red;"> <span id="modelWindowHead"></span></font></h5>
		      </div>
		      <div class="modal-body">
              <div>
              <span id="modelWindowBody" style="display: block;height: 300px;overflow-y: scroll;"></span></div>             
		      </div>
		      
		      <div class="modal-footer">
		        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		
		  </div>
		</div>
    <!-- Popup modal div end -->

<%--<c:if test="${sessionScope.user.mobileVerifiedYN=='N'}">
	<script type="text/javascript">
    $(window).load(function(){
        $('#myModal').modal('show');
    });
</script>
</c:if>
--%>




<script type="text/javascript">
	
    function searchStudent() {

       if($("#shiftId").val()=="none"){
            alert("Select a Shift.");return false;
        }
        if($("#versionId").val()=="none"){
            alert("Select a version.");return false;
        }
		if($("#groupId").val()=="none"){
            alert("Select a group.");return false;
        }
        $("#showapplicant1").html('');
         $('#loader1').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "alreadyAddedBoard",
            dataType: 'text',
            async   : false,
            data    : {

                eiin:currentEiin ,shift_id: $("#shiftId").val(),version_id: $("#versionId").val(),
                group_id: $("#groupId").val(),
            }
        }).done(function (msg) {
                    $("#loader1").html('');
                    $("#showapplicant1").html('');
                    $("#showapplicant1").html(msg);
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
