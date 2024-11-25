<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Upload XLS</title>
<link href="/board/resources/css/applicant.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Belgrano' rel='stylesheet' type='text/css' />
</head>
<body>
<div id="loginpanelwrap">
  	
	<div class="loginheader">
    <div class="logintitle">
    <font size="4" ><b><a href="javascript:void(0)">Upload XLS</a></b>
    </font></div>
    </div>
<hr/>
    <div class="loginform">

        
        


  <form action="readexcel" method="post" enctype="multipart/form-data">
      <div style="width: 400px">
         <label for="myFile"><b>Upload XLS file</b></label>
      
      <input type="file" name="myFile" />
      <br/><br/>
      <input type="submit" value="Upload" />
      </div>
   </form>
       
        
</div>

<s:if test="hasActionErrors()">
   <div class="errors">
      <s:actionerror/>
   </div>
</s:if>
<s:if test="hasActionMessages()">
   <div class="welcome">
      <s:actionmessage/>
   </div>
</s:if>
 
</div>
<script type='text/javascript' src="/board/resources/js/jquery.min.js"></script>


   	
</body>
</html>




