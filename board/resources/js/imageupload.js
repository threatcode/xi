	
	function uploadImage()
	{
	   
		var image = null;
		$.ajaxFileUpload
		(
			{
				url:'/UGA/imageUpload.do?date='+new Date(),
				secureuri:false,
				fileElementId:'fileToUpload',
				dataType: 'html',				
				success: function (data, status)
				{		
				  if(data=='big size')
				  {
				  		document.getElementById('mainpreview').innerHTML = "<img src='/UGA/resource/images/noImage.jpg'   alt='Upload your Photo here.' />"+
							 					"<input type='hidden' id='noimage' />";
						alert("Maximum allowable file size is 75KB.");
 					 	return;
			      }
				  if(data=='format does not match')
				  {
				  		document.getElementById('mainpreview').innerHTML = "<img src='/UGA/resource/images/noImage.jpg'   alt='Upload your Photo here.' />"+
							 					"<input type='hidden' id='noimage' />";
						alert("Image format must be JPG,JPEG,GIF,BMP.");
 					 	return;
			      }
				  if(data=='black white')
				  {
				  		document.getElementById('mainpreview').innerHTML = "<img src='/UGA/resource/images/noImage.jpg'   alt='Upload your Photo here.' />"+
							 					"<input type='hidden' id='noimage' />";
						alert("Image must be color.");
 					 	return;
			      }
				  if(data=='resolution')
				  {
				  		document.getElementById('mainpreview').innerHTML = "<img src='/UGA/resource/images/noImage.jpg'   alt='Upload your Photo here.' />"+
							 					"<input type='hidden' id='noimage' />";
						alert("Image resolution must be propotional to 300 (15% plus or minus) X 360 (15% plus or minus).");
 					 	return;
			      }
				  if(data=='')
				  {
				  		document.getElementById('mainpreview').innerHTML = "<img src='/UGA/resource/images/noImage.jpg'   alt='Upload your Photo here.' />"+
							 					"<input type='hidden' id='noimage' />";
						alert("There may be problem, pls upload image again.");
 					 	return;
			      }
			      
			      
			      if(data=='Incompatable')
				  {
				  		document.getElementById('mainpreview').innerHTML = "<img src='/UGA/resource/images/noImage.jpg'   alt='Upload your Photo here.' />"+
							 					"<input type='hidden' id='noimage' />";
						alert("Property Mismatch. Try a new Image.");
 					 	return;
			      }
			      
                  		 		   
				  var d3 = data.substring(data.length-25,data.length);
				  var d2 = d3.split("=");
				   
				  if(d2[0] == 'width' || d2[0] == 'height')
				   {
				     d3 = data.substring(0,data.length-25);
				     d3 += '/>';
				    
				   }else d3 = data;
				
		 		if(navigator.appName == 'Opera')
		 		  {
		 		   	
		 		   document.getElementById('mainpreview').innerHTML = "";
		 		   document.getElementById('mainpreview').innerHTML = "<img id= 'ferretp1' src='"+data+"'>";
		 		   document.getElementById('imageCheck').value="Uploaded";
		 		  }
					else
					{
					 document.getElementById('mainpreview').innerHTML = d3.substring(0,4)+' id= "ferretp1" '+d3.substring(5);
					 document.getElementById('imageCheck').value="Uploaded";
					} 
				},
				error: function (data, status, e)
				{
					//alert(e);
					if(data=='Incompatable')
					  {
					  		document.getElementById('mainpreview').innerHTML = "<img src='/UGA/resource/images/noImage.jpg'   alt='Upload your Photo here.' />"+
								 					"<input type='hidden' id='noimage' />";
							alert("Property Mismatch. Try a new Image.");
	 					 	return;
				      }
				}
			}
		)
		
		
		return false;
	
	}
	
/////////////////////////////////////////////////////////////////////////////////////

	function uploadSinature()
	{
	   
		var image = null;
		$.ajaxFileUpload
		(
			{
				url:'/UGA/signatureUpload.do?date='+new Date(),
				secureuri:false,
				fileElementId:'sigToUpload',
				dataType: 'html',				
				success: function (data, status)
				{
				  if(data=='big size')
				  {
				  		document.getElementById('mainpreview').innerHTML = "<img src='/UGA/resource/images/noImage.jpg'   alt='Upload your Photo here.' />"+
							 					"<input type='hidden' id='noimage' />";
						alert("Maximum allowable file size is 20KB.");
 					 	return;
			      }
				  if(data=='format does not match')
				  {
				  		document.getElementById('mainpreview').innerHTML = "<img src='/UGA/resource/images/noImage.jpg'   alt='Upload your Photo here.' />"+
							 					"<input type='hidden' id='noimage' />";
						alert("Image format must be JPG,JPEG,GIF,BMP.");
 					 	return;
			      }
				  if(data=='black white')
				  {
				  		document.getElementById('mainpreview').innerHTML = "<img src='/UGA/resource/images/noImage.jpg'   alt='Upload your Photo here.' />"+
							 					"<input type='hidden' id='noimage' />";
						alert("Image must be color.");
 					 	return;
			      }
				  if(data=='resolution')
				  {
				  		document.getElementById('mainpreview').innerHTML = "<img src='/UGA/resource/images/noImage.jpg'   alt='Upload your Photo here.' />"+
							 					"<input type='hidden' id='noimage' />";
						alert("Image resolution must be propotional to 300 (15% plus or minus) X 80 (15% plus or minus).");
 					 	return;
			      }
				  if(data=='')
				  {
				  		document.getElementById('mainpreview').innerHTML = "<img src='/UGA/resource/images/noImage.jpg'   alt='Upload your Photo here.' />"+
							 					"<input type='hidden' id='noimage' />";
						alert("There may be problem, pls upload image again.");
 					 	return;
			      }			
			      if(data=='Incompatable')
				  {
				  		document.getElementById('mainpreview').innerHTML = "<img src='/UGA/resource/images/noImage.jpg'   alt='Upload your Photo here.' />"+
							 					"<input type='hidden' id='noimage' />";
						alert("Property Mismatch. Try a new Image.");
 					 	return;
			      }			
                  		 		   
				  var d3 = data.substring(data.length-25,data.length);
				  var d2 = d3.split("=");
				  
				  if(d2[0] == 'width' || d2[0] == 'height')
				   {
				     d3 = data.substring(0,data.length-25);
				     d3 += '/>';
				    
				   }else d3 = data;
				
		 		if(navigator.appName == 'Opera')
		 		  {
		 		   	
		 		   document.getElementById('sigpreview').innerHTML = "";
		 		   document.getElementById('sigpreview').innerHTML = "<img id= 'ferretp1' src='"+data+"'>";
		 		   document.getElementById('SignatureCheck').value="Uploaded";
		 		  }
					else
					{
					 document.getElementById('sigpreview').innerHTML = d3.substring(0,4)+' id= "ferretp1" '+d3.substring(5);
					 document.getElementById('SignatureCheck').value="Uploaded";
					 
					} 
				},
				error: function (data, status, e)
				{
					//alert(e);
				  if(data=='Incompatable')
				  {
				  		document.getElementById('mainpreview').innerHTML = "<img src='/UGA/resource/images/noImage.jpg'   alt='Upload your Photo here.' />"+
							 					"<input type='hidden' id='noimage' />";
						alert("Property Mismatch. Try a new Image.");
 					 	return;
			      }			
					
				}
			}
		)
		
		
		return false;
	
	}	