<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>XICA 2018: College SVG</title>
<link rel="icon" type="image/x-icon" href="/board/resources/_images/favicon.ico"/>
<link href="/resources/css/college.css" rel="stylesheet" />
<link href='/resources/css/font.css' rel='stylesheet' type='text/css' />
<link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/dataTables.bootstrap.css" />

<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/defiant-latest.min.js"></script>


<script type="text/javascript" language="javascript" src="/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" language="javascript" src="/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript" src="/resources/js/dataTables.bootstrap.js"></script>


<link href="/board/resources/_stylesheet/RequiredContentDesign.css" rel="stylesheet" />
<link href="/board/resources/_stylesheet/ApplicantRequiredContentDesign.css" rel="stylesheet" />
</head>
<body>




<div id="panelwrap" style="width: 95%">
  
  <div class="header">
   
    <div class="header_right">SVG 
    </div> 
       
  </div>    
   
    
  <div class="center_content"   >
  
	<div id="right_wrap" >
	
		<div id="right_content"  style="margin: 0 0 0 2px">

			<h2>College SVG</h2>					
			<div class="sidebar_section_text">
			<center>
			<br />
				<div style="padding-left: 370px">
					<div style="float: left;">
								<label for="distID">Board Name</label>
								<select name="boardId" id="boardId">
									<option value="none" selected>Select Board</option>
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
						
		
								<button style="width:100px;padding: 1px 12px;" class="btn btn-info"  type="button"
									onclick="fetchCollegeSVG()">Search
								</button>
					
					</div>

				</div>

				
			
			</center>

			</div>

		</div>
	
<!---- body ----->		
				<div id="showSVG" align="center">
				
				</div>
		
		
		
		
<!----body ------>	
	</div>

	
</div>


<script type='text/javascript' src="/board/resources/js/jquery.min.js"></script>

<script type="text/javascript">


    function fetchCollegeSVG() {   
         
         $('#showSVG').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : 'POST',
            url     : "viewSVG",
            dataType: 'text',
            async   : false,
            data    : {

                boardId    : $("#boardId").val()
            }
        }).done(function (msg) {
                    $("#showSVG").html(msg);
                })
                .always(function () {
                    //$('#sw-val-step-3').unmask();
                })
                .fail(function (data) {
                    if (data.responseCode)
                        alert(data.responseCode);
                });


    }   //End of fetchApplicationInformation


</script>	


<%@ include file="/WEB-INF/jsp/template/footerLite.jsp" %>

</div>
</body>
</html>    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    