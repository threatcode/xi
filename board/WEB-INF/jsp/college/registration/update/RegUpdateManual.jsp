<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>

<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">
			<h2 align="center"> 
				<font size="5" color="#0489B1">
					Registration Update Panel
				</font>
			<!-- 
				<button class="btn" id="btndfd" type="button" onclick="dfd()" style="background-color: lightyellow">Data From Database</button>
				<button class="btn" id="btnmde"  type="button" onclick="mde()" style="background-color: lightyellow">Manual Data Entry</button>
				 -->
			</h2>
			<div class="sidebar_section_text" style="display: none" id="dfd">
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
            url     : "getManualDataForRegUpdate",
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
			</div>
			<div class="sidebar_section_text" style="display: none" id="mde">
				Data By Manual
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp" %>
</div>
	<%@ include file="/WEB-INF/jsp/template/footerLite.jsp" %>
<script type="text/javascript">
	function dfd(){
		document.getElementById('dfd').style.display = "block";
		document.getElementById('mde').style.display = "none";
		//document.getElementById('btndfd').style.fontSize="20px";
		//document.getElementById('btnmde').style.fontSize="14px";
		//document.getElementById('btndfd').style.color="green";
		//document.getElementById('btnmde').style.color="black";
	}
	function mde(){
		document.getElementById('dfd').style.display = "none";
		document.getElementById('mde').style.display = "block";
		//document.getElementById('btnmde').style.fontSize="20px";
		//document.getElementById('btndfd').style.fontSize="14px";
		//document.getElementById('btnmde').style.color="green";
		//document.getElementById('btndfd').style.color="black";
	}
	dfd()
</script>	


  	<!-- Popup modal div start -->
    	<div id="modelConfirm" class="modal fade" data-backdrop="static" data-keyboard="false" role="dialog">
		  <div class="modal-dialog">		
		    <!-- Modal content-->
		    <div class="modal-content" style="color: black;">
		      <div class="modal-header">
		         <button type="button" class="close" data-dismiss="modal">&times;</button> 
		         <h5 class="modal-title" ><font style="color: green;"> <span id="modelConfirmHead"></span></font></h5>
		      </div>
		      <div class="modal-body">
              <div>
              <span id="modelConfirmBody" style="display: block;height: 100px;overflow-y: scroll;"></span></div>             
		      </div>
		      
		      <div class="modal-footer">
		      	<button type="button" class="btn btn-danger" data-ok="modal" onclick="SubmitData();">Submit Data</button>
		        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		
		  </div>
		</div>
    <!-- Popup modal div end -->    	
	
</body>
</html>


