
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="/board/resources/css/tabledata/jquery.dataTables.css" type="text/css"></link>


<style>
div.transbox {
  margin: 30px;
  background-color: #f0f8ff;
  border: 1px solid black;
  opacity: 0.6;
  filter: alpha(opacity=60); /* For IE8 and earlier */
}

div.transbox p {
  margin: 5%;
  font-weight: bold;
  color: #000000;
}

table.dataTable th,
table.dataTable td {
  font-size: 12px;
}


table{
  margin: 0 auto;
  width: 500px;
  clear: both;
  border-collapse: collapse;
  table-layout: fixed; // ***********add this
  word-wrap:break-word; // ***********and this
}




</style>



<SCRIPT lang="javascript">

 $(document).ready(function() {
    // Setup - add a text input to each footer cell
  
 
  $('#example tfoot th').each( function () {
        var title = $(this).text();
        
        if(title.trim() ==="")
        {
        }else
        {
        $(this).html( '<input type="text"  placeholder="Search '+title+'" />' );
        }
    } );
  
 
    // DataTable
   var table = $('#example').DataTable();
    
   /* var table = $('#example').removeAttr('width').DataTable( {
        scrollY:        false,
        scrollX:        false,
        scrollCollapse: false,
        paging:         true,
        columnDefs: [
            { width: 10, targets: 0 },
            { width: 10, targets: 1 },
            { width: 10, targets: 2 },
            { width: 100, targets: 3 },
            { width: 10, targets: 4 },
            { width: 10, targets: 5 },
            { width: 10, targets: 6 },
            { width: 10, targets: 7 },
            { width: 10, targets: 8 },
            { width: 10, targets: 9 },
            { width: 10, targets: 10 },
            { width: 10, targets: 11 }
        ],
        columns: [
            { width: 10, targets: 0 },
            { width: 10, targets: 1 },
            { width: 10, targets: 2 },
            { width: 100, targets: 3 },
            { width: 10, targets: 4 },
            { width: 10, targets: 5 },
            { width: 10, targets: 6 },
            { width: 10, targets: 7 },
            { width: 10, targets: 8 },
            { width: 10, targets: 9 },
            { width: 10, targets: 10 },
             { width: 10, targets: 11 }
        ],        
        fixedColumns: true
    } ); 
  */
  
  
  
  
 
    // Apply the search
    table.columns().every( function () {
        var that = this;
 
        $( 'input', this.footer() ).on( 'keyup change', function () {
            if ( that.search() !== this.value ) {
                that
                    .search( this.value )
                    .draw();
            }
        } );
    } );
    
} );
	
	
	
/* 	
	
$(document).ready(function() {
    var table = $('#example').removeAttr('width').DataTable( {
        scrollY:        "300px",
        scrollX:        true,
        scrollCollapse: true,
        paging:         false,
        columnDefs: [
            { width: 200, targets: 0 }
        ],
        fixedColumns: true
    } );
} );	
	 */
	
        
</SCRIPT>


<div >

<p style="font-size: 20px; font-family: sans-serif; font-style: italic;">College Information</p>




<table id="example" class="display" >
 	<thead>
            <tr>
                <th style="width: 15px">District</th>
                <th style="width: 15px">Thana</th>
                <th style="width: 10px">EIIN</th>
                <th style="width: 110px">College Name</th>
                <th style="width: 10px">Shift</th>
                <th style="width: 10px">Version</th>
                <th style="width: 10px">Group</th>
                <th style="width: 10px">Gender</th>
                <th style="width: 10px">Min GPA</th>
                <th style="width: 10px">Own GPA</th>
                <th style="width: 10px">Total Seat</th>
                <th style="width: 10px">Available Seat </th>
			</tr>
 		
 		<tbody>
           
           <s:iterator value="svgList">
            <tr>
				<td  style="width: 15px"> <s:property value="dist" /> </td>
				<td  style="width: 15px"> <s:property value="thana" /> </td>
				<td style="width: 10px"> <s:property value="eiin" /> </td>
				<td style="width: 110px"> <s:property value="collegeName" /> </td>
				<td style="width: 10px"> <s:property value="shiftName" /> </td>
				<td style="width: 10px"> <s:property value="versionName" /> </td>
				<td style="width: 10px"> <s:property value="groupName" /> </td>
				<td style="width: 10px"> <s:property value="gender" /> </td>
				<td  style="width: 10px" align="center"> <s:property value="minGpa" /> </td>
				<td  style="width: 10px" align="center"> <s:property value="ownGpa" /> </td>
				<td  style="width: 10px" align="center"> <s:property value="totalSeat" /> </td>
				<td  style="width: 10px" align="center"> <s:property value="avlSeat" /> </td>		
				
			</tr>		
 		
 		 </s:iterator>
 		
 		
 		</tbody>
        <tfoot>
            <tr>
                <th >District</th>
                <th >Thana</th>
                <th  >EIIN</th>
                <th  >College Name</th>
                <th> </th>
                <th> </th>
                <th> </th>
                <th> </th>
                <th> </th>
                <th> </th>
                <th> </th>
                <th> </th>
            </tr>
        </tfoot>
</table>




</div>


