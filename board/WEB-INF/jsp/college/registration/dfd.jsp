<center>
	<table style="width: 400px; font-size: 14px;padding-bottom: 0px" class="table table-bordered table-striped cf">
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
					<input type="hidden" name="groupName" id="groupName" />
				</select>
			</td>
		</tr>
	</table>

	<div id="loader"></div>
	<div id="showapplicant"></div>
</center>
<script type="text/javascript">
	  function changeGroup(){
      	$("#groupName").val($("#groupId option:selected").text());
      	searchStudent();
      }
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
        if($("#meritType").val()=="none"){
            alert("Select a merit.");return false;
        }
        /*
        if($("#meritType").val()==='4' && $("#sscroll").val().trim()===""){
            alert("Enter the SSC roll no. of a student from the remaining waiting list");return false;
        }
        */
        $("#showapplicant").html('');
         $('#loader').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "getDataForRegistration",
            dataType: 'text',
            async   : false,
            data    : {

                eiinCode:"Hossain" ,pShift_id: $("#shiftId").val(),pVersion_id: $("#versionId").val(),
                pGroup_id: $("#groupId").val(),pMerit: '',pRoll: $("#sscroll").val(),
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