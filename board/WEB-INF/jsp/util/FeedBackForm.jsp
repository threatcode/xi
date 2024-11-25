<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Applicant FeedBack</title>
<link href="/board/resources/css/applicant.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Belgrano' rel='stylesheet' type='text/css' />
</head>
<body>
<div id="loginpanelwrap">
  	
	<div class="loginheader">
    <div class="logintitle">
    <font size="4" ><b><a href="javascript:void(0)">Applicant's Feedback/Comment</a></b>
    </font></div>
    </div>
<hr/>
    <div class="loginform">

        <div class="loginform_row">
        <label>SSC Roll: </label>
			<input type="text" id="ssc_roll" style="width:150px;" maxlength="15"  class="loginform_input" required/>
        </div>
        <div class="loginform_row">
        <label>SSC Board:</label>
       <select style="width:150px;" id="ssc_board" >
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
        
         <div class="loginform_row">
        <label>Passing Year</label>
         <select  id="ssc_year">
	     <option value="none">Select Year</option>
	     <option value="2020">2020</option>
			     <option value="2019">2019</option>
			     <option value="2018">2018</option>
	    </select>
        </div>
        
        <div class="loginform_row">
        <label>Mobile No.: </label>
			<input type="text" maxlength="11" style="width:150px;" onkeyup="checkInput(this)"  id="mobile" class="loginform_input"  onchange="validateMobileNumber(this.value)"  required />
        
        </div > 
         
        <div style="padding-left:100px">
        <font size="3" color="blue">(Please state your problem/comment in brief.)</font>
        </div>
        <br/>
          <div class="loginform_row">
          
        <label>Message: </label>
        
<!--         <input type="text"  style="width:250px;height:100px " id="message" class="loginform_input"   required /> -->
			<textarea id="message" name="message" rows="4" cols="30" maxlength="151">
			</textarea > 
        </div>

        
<button   type="button"
onclick="insetFeedBack()">Submit
</button>
       
        
    </div>


<div id="successMessage"></div>
</div>
<script type='text/javascript' src="/board/resources/js/jquery.min.js"></script>
 <script type="text/javascript">

    function insetFeedBack() {
               $('#PinRetrive').html('');
/*     image.src="/board/resources/inages/loading_by_adnan.gif";
    $("#PinRetrive").html(image);  */
   

  /*       var mystring = document.getElementById('message').value; 
       alert (mystring); */
        

        
       if($("#ssc_roll").val()==""){
            alert("Give Your SSC Roll.");return;
        }
       if($("#ssc_board").val()=="none"){
            alert("Select Your SSC Board.");return;
        }
        if($("#ssc_year").val()=="none"){
            alert("Select Your SSC Passing Year.");return;
        }
        
       if($("#mobile").val()==""||$("#mobile").val().length<11){
            alert("Give your Correct Contact Mobile Number.");return;
        } 
        
      
        if($("#message").val().trim()==""){
            alert("Write Your Message");return;
        }  
        
        
     $('#successMessage').prepend($('<img>',{id:'theImg',src:'/board/resources/images/loading1.gif'}));

        $.ajax({
            type    : "POST",
            url     : "insertFeedBack",
            dataType: 'text',
            async   : false,
            data    : {ssc_roll: $("#ssc_roll").val().trim(),ssc_board: $("#ssc_board").val(),ssc_passing_year: $("#ssc_year").val(),mobile_number: $("#mobile").val().trim(),message: $("#message").val().trim()}

        }).done(function (msg) {
                  $("#ssc_roll").val()=="";
                  $ ("#message").val()=="";
                  $('#successMessage').html('');
                   $("#successMessage").html(msg);
                })
                .always(function () {
                    //$('#sw-val-step-3').unmask();
                })
                .fail(function (data) {
                    if (data.responseCode)
                        alert(data.responseCode);
                });


    }   //End of fetchApplicationInformation


	function validateMobileNumber(mobile) {
    var mob = /^(013|014|015|016|017|018|019)[0-9]{8}$/;

	    if (mob.test(mobile) == false) {
	      alert("Invalid Mobile");
	      document.getElementById("mobile").value = "";
	    }
	    
	  }
	  
	    function checkInput(ob) {
        var invalidChars = /[^0-9]/gi
        if(invalidChars.test(ob.value)) {
            ob.value = ob.value.replace(invalidChars,"");
        }
        
    }
    

</script>	   	
</body>
</html>




