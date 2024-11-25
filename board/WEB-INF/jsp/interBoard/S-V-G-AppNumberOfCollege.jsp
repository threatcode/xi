<%@ include file="/WEB-INF/jsp/template/header.jsp" %>
<%@ include file="/WEB-INF/jsp/template/sidebar.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Arrays"%>

<div>
<label for="eiinCode">Colleges:</label>
<select name="eiinCode" id="eiinCode">
<option value="none" selected>Select College</option>

<c:forEach items="${collegeinfoList}" var="colleges">
<option value="${colleges.eiinCode}">${colleges.collegeName}(${colleges.eiinCode})
</option>
</c:forEach>

</select>
</div>
<hr> 
<br/>
<div >
<button class="btn bg-green"  type="button"
onclick="fetchCollegeSVG()">Fetch S-V-G Of College
</button>
 </div>
 <hr>
<div id="showSVG">

</div>

	
<script type="text/javascript">

    function fetchCollegeSVG() {

        if($("#eiinCode").val()=="none"){
            alert("Select a College.");return;
        }

        $.ajax({
            type    : "POST",
            url     : "S_V_G_ApplicationNumberOfCollege",
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


</script>	

 
<%@ include file="/WEB-INF/jsp/template/footer.jsp" %>
