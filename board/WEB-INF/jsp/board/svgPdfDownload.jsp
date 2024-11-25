<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/template/headerLiteSVG.jsp"%>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2>SVG Details</h2>
			<div class="sidebar_section_text">
				<center>
					<br />


					<div style="padding-left: 20px">
						<div style="float: left;">
						
							<form action="boardSVGReport" onsubmit="return checkBoard()">
								<input type="hidden" name="boardId" id="boardId" value='0' >
								<input type="hidden" name="boardName" id="boardName" value='' >
	
								<label>Board</label> <select id="helper_board_id" onchange="setBoardInfo(this)">
									<option value="" selected="selected">Select Board</option>
									<option value="15">Barishal</option>
					<option value="20">BOU</option>
					<option value="14">Chattogram</option>
					<option value="11">Cumilla</option>
					<option value="10" >Dhaka</option>
					<option value="17">Dinajpur</option>
					<option value="13">Jashore</option>
					<option value="18">Madrasah</option>
					<option value="21">Mymensingh</option>
					<option value="12">Rajshahi</option>
					<option value="16">Sylhet</option>
					<option value="19">TEC</option>
	
								</select>
								<input style="width:180px;padding: 1px 12px;"
									class="btn btn-info" type="submit" value="Download Pdf" >
							</form>

						</div>
					</div>
					<br />
					<hr>
					<div style="padding-left:30px">





						<div id="showSVG"></div>


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

<link rel="stylesheet"
	href="/board/resources/css/tabledata/jquery.dataTables.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="/board/resources/js/jquery.validity.1.2.0/jquery.validity.css"
	type="text/css"></link>


<script type="text/javascript"
	src="/board/resources/js/jquery.validity.1.2.0/jquery-1.6.4.min.js"></script>
<script type="text/javascript"
	src="/board/resources/js/jquery.validity.1.2.0/jQuery.validity.js"></script>


<script type="text/javascript">

	/*
		Function for setting board info
	*/
	function  setBoardInfo(sel){
		var board = helper_board_id.value;
		
		
		
		document.getElementsByName('boardId')[0].value = board;		
		document.getElementsByName('boardName')[0].value = sel.options[sel.selectedIndex].text;
		
		
		//alert("BoardId: " + boardId.value + " BoardName: " + boardName.value);
		
		
	}
	
	
	function checkBoard(){
		var board = helper_board_id.value;
		if (board==""){
			alert("You haven't choose board");
			return false;
		}else{
			return true;
		}
	}
	

</script>


<%@ include file="/WEB-INF/jsp/template/footerLite.jsp"%>

</div>
</body>
</html>