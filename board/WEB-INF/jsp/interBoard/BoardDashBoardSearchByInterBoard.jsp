<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2>Board Dash-Board</h2>
			<div class="sidebar_section_text">
<center>
<br/>


<div >
        <label>Board:</label>
       <select style="width:150px;" id="boardId" >
	     			     	<option value="none">Select Board</option>
				    <option value="15">Barishal</option>
					<option value="20">BOU</option>
					<option value="14">Chattogram</option>
					<option value="11">Cumilla</option>
					<option value="10" selected="selected">Dhaka</option>
					<option value="17">Dinajpur</option>
					<option value="13">Jashore</option>
					<option value="18">Madrasah</option>
					<option value="21">Mymensingh</option>
					<option value="12">Rajshahi</option>
					<option value="16">Sylhet</option>
					<option value="19">TEC</option>

	     
	    </select>

</div>

<br/>
<hr>
<div style="padding-left:50px;">
<button style="width:180px;padding: 1px 12px;" class="btn btn-info"   type="button"
onclick="fetchBoardDashBoard()">Fetch Board Dash Board
</button>

</div>
<br/>
<hr>

 
<div id="showBoardDashBoard">

</div>

<br>
</center>

</div>
		</div>
		<br>
<!---- body ----->		

		
<!----body ------>	
	</div>


	<%@ include file="/WEB-INF/jsp/template/sidebarLite.jsp" %>
</div>
<script type='text/javascript' src="/board/resources/js/jquery.min.js"></script>
<script type="text/javascript">

    function fetchBoardDashBoard() {

       if($("#boardId").val()=="none"){
            alert("Select a Board.");return;
        }

       $('#showBoardDashBoard').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "showboardDashBoard",
            dataType: 'text',
            async   : false,
            data    : {

                boardId    : $("#boardId").val()
            }
        }).done(function (msg) {
                    $("#showBoardDashBoard").html(msg);
                })
                .always(function () {
                    //$('#sw-val-step-3').unmask();
                })
                .fail(function (data) {
                    if (data.responseCode)
                        alert(data.responseCode);
                });

 }
     //End of fetchApplicationInformation


</script>	
	
<%@ include file="/WEB-INF/jsp/template/footerLite.jsp" %>

</div>
</body>
</html>