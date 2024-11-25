<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Applicant's Account</title>
<link href="/board/resources/css/applicant.css" rel="stylesheet" />
<link href='/board/resources/css/font.css' rel='stylesheet' type='text/css' />
<script type="text/javascript" src="/board/resources/js/jquery.min.js"></script>
<style>

.sidebar_section_text{
padding-top:15px !important;
padding-bottom:20px !important;
margin-left:0px !important;
width:726px !important;
}
.form_row{
width: 735px !important;
}
label{
width: 80px !important;
} 
</style>
</head>
<body>
<div id="panelwrap">
  	
		<%@include file="Header.jsp" %>   
                    
    <div class="center_content">  
 
    <div id="right_wrap">
		<div id="right_content">

			<h2>
				Applicant's Photo
			</h2>
			<div class="sidebar_section_text" style="height: 310px">
				<!--  Start -->
<form id="changePhotoForm" name="changePhotoForm" enctype="multipart/form-data" method="post">
<div class="box_container" style="height: 180px;margin-top: 10px;" id="photo_upload_div">
<div class="box_header">
Photo Upload
</div>
<div class="box_body" style="min-height:200px;">

  <div id="personal_info_div" style="padding-top: 10px;padding-left: 10px;">
	  <div class="box_row">
            <table border="0" width="30%" height="20px;">

 <tr>
<td  align="left"
style="font-weight: bold;border: none;">
<div id="preview" style="float:left; width:120px; height:150px; ">
<img id="no-image"class="historyimage" src="${sessionScope.user.photoUrl}" alt="No Photo" width="120" height="150" />
  </div>
</td>

<td>
            <div style="border:1px dashed grey; width:260px;height:150px;float:right;margin-left:10px;margin-top:25px;padding:5px;">
                <br/><font color='green' size="2px"><b>Size, format and resolution - </b></font><br/><br/>
                <font color='blue' size="2px">Height:</font> <font color='red' size="2px">150px;</font><br/>
               <font color='blue' size="2px"> Width:</font> <font color='red' size="2px">120px;</font><br/>
                <font color='blue' size="2px">Image Format:</font> <font color='red' size="2px">jpg</font><br/>
               <font color='blue' size="2px"> Maximum File Size:</font> <font color='red' size="2px"> 50 Kb</font><br/>
            </div>
</td>
</tr>
<tr>
<td  colspan="2" align="left"
style="font-weight: bold;border: none; text-align: bottom;" >
<input type="file" id="fileUpload" name="fileUpload" onchange="displayPreview1(this.files);" />
<br/><br/>
 <div style="width:100px;float: left;left-padding:100px;">
   <button style="width:150px; padding: 1px 12px; left-ppadding:10px;" class="addSelectedCollege" id="change_photo"  onclick="changePhoto()">Upload Photo</button>
 </div>
</td>
</tr>

</table>
			
     </div>
	
				
  </div>
  
</div>

<div class="box_footer">
 
</div>
</div>
</form>				
<!--  End -->
			</div>
		</div>
		

		
	</div>          
                    
    <div class="sidebar" id="sidebar">
    <%@include file="LeftSideBar.jsp" %>
   
    </div>             
   
    
    <div class="clear"></div>
    </div> <!--end of center_content-->
    
	<%@include file="Footer.jsp" %>

</div>
 <script type="text/javascript">
  
  
  
  
// preview and size validation
var _URL = window.URL || window.webkitURL;
	
function displayPreview(files) {
	$('#preview').html("");
	var file = files[0];
	var img = new Image();
	var width = img.width;
	var height = img.height;
	var size = file.size / 1024;
//alert(width+":"+height);
	img.onload = function() {

	if (width>120 || height>150 || size>50) {
		alert("exceed W/H px OR Size = "+ size);
		$('#myFile').val("");
	return false;
	}
	
$('#preview').append(img);
};

img.src = _URL.createObjectURL(file);

}

function displayPreview1(files) {

	var formData = new FormData($('form')[0]);
		 $.ajax({
			 	url: 'applicantPhotoValidation.action',		
			 	type: 'POST',
			    data: formData,
			    async: false,
			    cache: false,
			    contentType: false,
			    processData: false,
			 	success: function(data) {

			 	 if(data=='success')
			 	 {
			 	 displayPreview(files);
	             enableField("create_account");
			 	 }
	                		 	
			 	else
			 	{
			 	$("#fileUpload").val("");
			 	$('#preview').html("<img id='theImg' src='/BTEB_WEB/resources/images/dummy_120x150.gif'/>");
			 				 	
			 	 if(data=="error#Image with 120 px Width and 150 px Height are allowed to upload.")
			 	 {
			 	 alert(data);
			 	 }
			 	 
			 	 else if(data=="error#Not a Valid type. Only Supported Image Type is '.jpg'")
			 	 {
			 	 alert(data);
			 	 }
			 	 
			 	  else if(data=="error#Maximum allowed Photo size is 50 Kb")
			 	 {
			 	 alert(data);
			 	 }
			 	 
			 	  else if(data=="error#Probelm in Uploading the Image")
			 	 {
			 	 alert(data);
			 	 }
			 	 	
			 	return false;
			 		
			     }

			 	},
			 	error: function(e) {
	            enableField("create_account");
			 	alert(e);
			 	}
		});

}



function changePhoto(){

if($("#fileUpload").val()=="" || $("#fileUpload").val()==null)
{
alert("Please upload a new valid photo to change..!!");
return false;
}


if(confirm("Do you really want to change  photo ?"))
{
	 var formData = new FormData($('form')[0]);
		 $.ajax({
			 	url: 'changePhoto.action',		
			 	type: 'POST',
			    data: formData,
			    async: false,
			    cache: false,
			    contentType: false,
			    processData: false,
			 	success: function(response) {
			 	//var a=msg.message;
			 	///a=JSON.stringify(a);
			 	//cosole.log(msg.message);			 	
			 	alert(response);
			 	},
			 	error: function(response) {
			 	//var a=JSON.stringify(response);
			 	var arr = [];
			 	var arr2 = [];
                json = JSON.stringify(response);
                arr = json.split(/:/);
                st=arr[4];
                arr2=st.split(/"/);
			 	alert(arr2[1]);
			 	}
		});
}
else
    return false;	
	 
}
</script>
    	
</body>
</html>