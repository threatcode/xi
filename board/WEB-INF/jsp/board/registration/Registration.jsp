<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>

<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">
			<h2> <!-- 
				<button class="btn" id="btndfd" type="button" onclick="dfd()" style="background-color: lightyellow">Data From Database</button>
				<button class="btn" id="btnmde"  type="button" onclick="mde()" style="background-color: lightyellow">Manual Data Entry</button>
				 -->
			</h2>
			<div class="sidebar_section_text" style="display: none" id="dfd">
					<center>
						<table style="width: 400px; font-size: 14px;padding-bottom: 0px" class="table table-bordered table-striped cf">
							<tr>
								<td>
								<input type="text" id="eiin" name="eiin" onkeyup="changeCName()">
								</td>
								
								<td>
									<button onclick="searchSubject()" style="font-size: 14px; font-weight: bold; padding: 6px 6px; margin-left: 5px;  box-shadow: 0px 0px 9px #888888;" class="btn btn-success buttonCSV">Show Subject</button>
								</td>
							</tr>
						</table>
						<font size="5" color=""><div id="cname"></div></font>
						<div id="loader"></div>
						<div id="showapplicant"></div>
						
					</center>
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
	var eiins = new Array(); 
	var eiinnames = new Array();

<c:forEach items="${lstCDTO}" var="shift">
		eiins.push('${shift.eiin}');
		eiinnames.push('${shift.college_name}');
</c:forEach>

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
<script type="text/javascript">


	  
    function searchSubject() {
    
    	if(!isInArray($("#eiin").val(), eiins) )
    	{
    		alert("This eiin is not in your board!!!");
    		return;
    	}

       if($("#eiin").val()==""){
            alert("Select a eiin.");return false;
        }
        $("#cname").html(eiinnames[eiins.indexOf($("#eiin").val())]);
        
        $("#showapplicant").html('');
         $('#loader').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "getSubjectforEiin",
            dataType: 'text',
            async   : false,
            data    : {

                eiinCode:"Hossain" ,eiin: $("#eiin").val(),
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
      function changeCName()
      {
      	$("#cname").html('');
      	$("#loader").html('');
		$("#showapplicant").html('');
      }
		      
      
      
      
      
      
      
      
      
      
      
      
      
      

function isInArray(value, array) { return array.indexOf(value) > -1; } 
</script>		
</body>
</html>


