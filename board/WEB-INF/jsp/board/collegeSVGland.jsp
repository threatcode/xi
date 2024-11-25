<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/template/headerLite.jsp" %>
<div class="center_content">
	<div id="right_wrap">
		<div id="right_content">

			<h2>SVG Edit</h2>
			<div class="sidebar_section_text">
<center>
<br/>


<div style="padding-left: 20px">
<div style="float: left;">
<label for="distID">EIIN</label>
						<input type="text" name="eiinCode" id="eiinCode"/>
						
						

<button style="width:180px;padding: 1px 12px;" class="btn btn-info"  type="button"
onclick="fetchCollegeSVG()">Fetch S-V-G Of College
</button>
				
						
					
								
</div>


</div>

<br/>
<hr>
<div style="padding-left:1px">


 

 
<div id="showSVG">

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

<link rel="stylesheet" href="/board/resources/css/tabledata/jquery.dataTables.css" type="text/css"></link>
<link rel="stylesheet" href="/board/resources/js/jquery.validity.1.2.0/jquery.validity.css" type="text/css"></link>


<script type="text/javascript" src="/board/resources/js/jquery.validity.1.2.0/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="/board/resources/js/jquery.validity.1.2.0/jQuery.validity.js"></script>


<script type="text/javascript">

function fetchThana(distictid, thanaid){
	 $('#thanaID').html('');
	
	 $.ajax({
                    
            url: "searchThana",
            data: {
            	districtID: distictid                        
            },
            type: 'POST',
            async: true,
            success: function (res) {
                //console.log(res.eiinCodeList.length);
                    $('#thanaID').append(
                      '<option value="none">Select Thana</option>');
                for (var i = 0; i < res.thanaIdList.length; i++) {
                   
                   if(res.thanaIdList[i]==thanaid)
                   {
                   		 $('#thanaID').append( '<option value="'+res.thanaIdList[i]+'" selected>' + res.thanaNameList[i] +'</option>'); 
                   }
                   else{
                   		 $('#thanaID').append( '<option value="'+res.thanaIdList[i]+'" >' + res.thanaNameList[i] +'</option>'); 
                    }    
                }
                
 
            }
        });

}







    function fetchCollegeSVG() {   
   
    
      
         $('#showSVG').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : 'POST',
            url     : "svgShow",
            dataType: 'text',
            async   : false,
            data    : {

                eiinCode    : $("#eiinCode").val()
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
    
    
    
    function saveSVG() {   
   
          
        // $('#showSVG').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));
         
      checkSVG();    
       
    var formData = new FormData($('form')[0]);
		 $.ajax({
			 	url: 'saveSvg.action',		
			 	type: 'POST',
			    data: formData,
			    async: false,
			    cache: false,
			    contentType: false,
			    processData: false,
			 	success: function(data) {

			 	 if(data=='success')
			 	 {
				 	  fetchCollegeSVG(); 
				 	  $("#showMSG").html("Successfuly  Updated");	
			 	 }	                		 	
			 	else
			 	{
			 	 $("#showMSG").html(data);		 	 
			 	return false;			 		
			     }
			 	},
			 	error: function(e) {	            
			 	alert(e);
			 	}
		});
    
    
    

    
} 
    
    
    
    


</script>	


<%@ include file="/WEB-INF/jsp/template/footerLite.jsp" %>

</div>
</body>
</html>