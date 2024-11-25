package edu.action.college;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;

import com.lowagie.text.*;
import com.lowagie.text.Font;

import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;


import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
//import org.apache.commons.lang3.text.WordUtils;


/*import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
//import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;*/

import edu.action.common.BaseAction;
import edu.dao.college.AdmissionDAO;
import edu.dto.college.CollegeDTO;
import edu.dto.college.ResultDTO;

public class CollegeResultPdf1 extends BaseAction  {

	private static final long serialVersionUID = 1L;
	private String shiftId;
	private String versionId;
	private String groupId;
	private String meritType;
	private String groupName;
	
	public String downloadResultPdf()  throws Exception {
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		
		// step 1
	    Document document = new Document(PageSize.A4.rotate());
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ServletOutputStream out = response.getOutputStream();
	    try {
	    	// step 2
	    	
	    	
	    	PdfWriter writer = PdfWriter.getInstance(document, baos);
//            EndPageEventAdmissionResult eEvent = new EndPageEventAdmissionResult();
//            writer.setPageEvent(eEvent);
//            eEvent.setDisplayValue(shiftId+"#"+versionId+"#"+groupName);
	    	// step 3
		    document.open();
		    // step 4

		 /*   PdfPTable header = 	headerTable(userDto.getEiin(), userDto.getCollege_name(), userDto.getBoard_name(), shiftId, versionId, groupName, document.getPageNumber()+1);	    
		    document.add(header);*/
		    
		    
		   EndPageEventAdmissionResult eEvent = new EndPageEventAdmissionResult();
           writer.setPageEvent(eEvent);
  
           eEvent.setDisplayValue(userDto.getEiin()+"#"+userDto.getBoard_name()+"#"+shiftId+"#"+versionId+"#"+groupName+"#"+document.getPageNumber()+1);
           // document.newPage();
         //   eEvent.setMeritType(resultType);
		    
		    
		/*    PdfPTable dataTable = PdfResultTable(userDto.getEiin(),shiftId,versionId,groupId,meritType);
		    document.add(dataTable);
		    */
		    
	        PdfPTable table = new PdfPTable(8);
	        table.setWidthPercentage(100);
	        table.setWidths(new int[]{1, 1, 1, 1, 1, 2, 1, 1});
	        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		  	table.addCell("Sl. No.");
		    table.addCell("Application ID");
		    table.addCell("SSC ROLL");
		    table.addCell("SSC BOARD");
		    table.addCell("SSC YEAR");
		    table.addCell("NAME");
		    table.addCell("GPA");
		    table.addCell("STATUS");
		  	table.setHeaderRows(1);
		  	AdmissionDAO admDao = new AdmissionDAO();
		  	List<ResultDTO> resultList = null;
		  	resultList = admDao.getResultData(userDto.getEiin(),shiftId,versionId,groupId,meritType);
		  	if(resultList == null){
		  		PdfPCell cell = new PdfPCell(new Paragraph("No Record Available"));
		  		cell.setColspan(8);
		  		table.addCell(cell);
		  	} 
		  	else {
		  		int a = 1;
			  	for(ResultDTO rDto: resultList){
			  		
			  	//	document.add(table);

			  		eEvent.setDisplayValue(userDto.getEiin()+"#"+userDto.getBoard_name()+"#"+shiftId+"#"+versionId+"#"+groupName+"#"+document.getPageNumber()+1);
                    document.newPage();
                    
			  		table.addCell(Integer.toString(a));
			  		table.addCell(rDto.getApplicationId());
			  		table.addCell(rDto.getSscRoll());
			  		table.addCell(rDto.getSscBoard());
			  		table.addCell(rDto.getSscYear());
			  		table.addCell(rDto.getApplicantName());
			  		table.addCell(rDto.getApplicantGpa());
			  		if(rDto.getApplicantQuota().equalsIgnoreCase("N")){
			  			if(rDto.getApplicantMerit().equalsIgnoreCase("1"))
			  				table.addCell("1st Merit");
			  		} else {
			  			table.addCell(rDto.getApplicantQuota());
			  		}
			  		a++;
			  	}
		  	}
		  	
		  	document.add(table);
		    
		 /*   PdfPTable footerTable = footerTable();
		    document.add(footerTable);*/
		    
		    // step 5
		    document.close();
		    		    
		  
		} catch (Exception e) {
			// TODO: handle exception
		}
	    
	 /*   catch (IOException e){
            out.close();
            e.printStackTrace();
        }
        catch (DocumentException e){
            out.close();
            e.printStackTrace();
        }*/

	    
	    response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename="+userDto.getEiin()+"_result.pdf"); // open in new tab or window           
        
     //   ServletOutputStream os = response.getOutputStream();
        out.write(baos.toByteArray());
        out.flush();
        out.close();
	    return null;
	    
	    
	}
	
	public PdfPTable PdfResultTable(String eiin, String shift, String version, String group, String meritType) throws DocumentException {
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{1, 1, 1, 1, 1, 2, 1, 1});
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	  	table.addCell("Sl. No.");
	    table.addCell("Application ID");
	    table.addCell("SSC ROLL");
	    table.addCell("SSC BOARD");
	    table.addCell("SSC YEAR");
	    table.addCell("NAME");
	    table.addCell("GPA");
	    table.addCell("STATUS");
	  	table.setHeaderRows(1);
	  	AdmissionDAO admDao = new AdmissionDAO();
	  	List<ResultDTO> resultList = null;
	  	resultList = admDao.getResultData(eiin, shift, version, group, meritType);
	  	if(resultList == null){
	  		PdfPCell cell = new PdfPCell(new Paragraph("No Record Available"));
	  		cell.setColspan(8);
	  		table.addCell(cell);
	  	} else {
	  		int a = 1;
		  	for(ResultDTO rDto: resultList){
		  		table.addCell(Integer.toString(a));
		  		table.addCell(rDto.getApplicationId());
		  		table.addCell(rDto.getSscRoll());
		  		table.addCell(rDto.getSscBoard());
		  		table.addCell(rDto.getSscYear());
		  		table.addCell(rDto.getApplicantName());
		  		table.addCell(rDto.getApplicantGpa());
		  		if(rDto.getApplicantQuota().equalsIgnoreCase("N")){
		  			if(rDto.getApplicantMerit().equalsIgnoreCase("1"))
		  				table.addCell("1st Merit");
		  		} else {
		  			table.addCell(rDto.getApplicantQuota());
		  		}
		  		a++;
		  	}
	  	}

        return table;
    }

	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getMeritType() {
		return meritType;
	}

	public void setMeritType(String meritType) {
		this.meritType = meritType;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
		
}

class EndPageEventAdmissionResult extends PdfPageEventHelper
{
    protected ServletContext servlet =null;
    protected PdfTemplate total;
    protected BaseFont helv;
    protected PdfPTable footer;
    private String header="";
    private String displayValue=null;
    private String meritType=null;
 

    public void addheader(String header)
    {
        this.header = header;
    }



   @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(100, 100);
        total.setBoundingBox(new Rectangle(-20,-20,100,100));

        try{
            helv=BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.WINANSI,BaseFont.NOT_EMBEDDED);
        }catch(Exception e){
            throw new ExceptionConverter(e);
        }

    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        PdfContentByte cb = writer.getDirectContent();

        footer = new PdfPTable(5);
        footer.setTotalWidth(700);
        footer.setWidthPercentage(80);

        try
        {
            footer.setWidths(new float[] {20,14,30,14,20});
            footer.setHorizontalAlignment(Element.ALIGN_CENTER);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        PdfPCell pcell=null;

        Font f10nornal = new Font(Font.TIMES_ROMAN,10,Font.NORMAL);

        pcell=new PdfPCell(new Paragraph("",f10nornal));		//Prepared by
        pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
        pcell.setMinimumHeight(50f);
        pcell.setBorderWidth(0);
        footer.addCell(pcell);


        pcell=new PdfPCell(new Paragraph("Page-"+document.getPageNumber(),f10nornal));
        pcell.setColspan(3);
        pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pcell.setMinimumHeight(50f);
        pcell.setBorderWidth(0);
        footer.addCell(pcell);


        pcell=new PdfPCell(new Paragraph("",f10nornal));	//Verified by
        pcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        pcell.setMinimumHeight(50f);
        pcell.setBorderWidth(0);
        footer.addCell(pcell);

        footer.writeSelectedRows(0, -1, (document.right()-document.left()-600)/2-document.leftMargin(),document.bottom()+5,cb);
       
    }

/*    @Override
    public void onStartPage(PdfWriter writer, Document document) {


        PdfContentByte cb = writer.getDirectContent();

        cb.saveState();
        String text = header;
        float textBase = document.top()+5;
        float textSize = helv.getWidthPoint(text, 12);
        cb.beginText();
        cb.setFontAndSize(helv, 10);
        if ((writer.getPageNumber() > 1))
        {
            cb.setTextMatrix((document.getPageSize().getWidth()/2)-(textSize/2), textBase);
            cb.showText(text);
            cb.endText();
            cb.addTemplate(total,(document.getPageSize().getWidth()/2)-(textSize/2), textBase);
        }
        else
        {
            cb.setTextMatrix(document.left()+50, textBase);
            cb.showText("");
            cb.endText();
            cb.addTemplate(total, document.right() + textSize, textBase);

        }

        cb.restoreState();

        try{
        	
        	String[] valArr=displayValue.split("#");
        	
    		PdfPTable header = new PdfPTable(2);
    	    header.setWidthPercentage(100);
    	    header.setWidths(new int[]{1,1});
    	    Font font = new Font(Font.HELVETICA, 14, Font.BOLD, Color.black);
    	    
    	    Paragraph p = new Paragraph(String.format("Page %d", valArr[4]));
    	    PdfPCell cellTwo = new PdfPCell(p);
    	    cellTwo.setColspan(2);
    	    cellTwo.setVerticalAlignment(Element.ALIGN_TOP);
    	    cellTwo.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	    cellTwo.setBorder(Rectangle.NO_BORDER);
    	    header.addCell(cellTwo);
    	    
    	    p = new Paragraph("BOARD OF INTERMEDIATE AND SECONDARY EDUCATION, "+valArr[4], font);
    	    PdfPCell cellOne = new PdfPCell(p);
    	    cellOne.setColspan(2);
    	    cellOne.setFixedHeight(30f);
    	    cellOne.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    cellOne.setBorder(Rectangle.NO_BORDER);
    	    header.addCell(cellOne);
    	    
    	    p = new Paragraph("List for HSC Admission (Session 2014-15)");
    	    PdfPCell cellThree = new PdfPCell(p);
    	    cellThree.setColspan(2);
    	    cellThree.setFixedHeight(25f);
    	    cellThree.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    cellThree.setBorder(Rectangle.NO_BORDER);
    	    header.addCell(cellThree);
    	    
    	    p = new Paragraph("COLLEGE NAME: "+valArr[4]+"  ("+valArr[4]+")");
    	    PdfPCell cellFour = new PdfPCell(p);
    	    cellFour.setColspan(2);
    	    cellFour.setFixedHeight(30f);
    	    cellFour.setBorder(Rectangle.NO_BORDER);
    	    header.addCell(cellFour);
    	    if(valArr[4].equalsIgnoreCase("1"))
    	    	valArr[4] = "Morning";
    	    else if(valArr[4].equalsIgnoreCase("2"))
    	    	valArr[4] = "Day";
    	    else if(valArr[4].equalsIgnoreCase("3"))
    	    	valArr[4] = "Evening";
    	    
    	    if(valArr[4].equalsIgnoreCase("1"))
    	    	valArr[4] = "Bangla";
    	    else if(valArr[4].equalsIgnoreCase("2"))
    	    	valArr[4] = "English";
    	    	
    	    p = new Paragraph(valArr[4] +" ("+valArr[4]+")             GROUP: "+valArr[4]);
    	    PdfPCell cellFive = new PdfPCell(p);
    	    cellFive.setColspan(2);
    	    cellFive.setFixedHeight(30f);
    	    cellFive.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    cellFive.setBorder(Rectangle.NO_BORDER);
    	    header.addCell(cellFive);




        }catch(Exception ex){

        }


    }*/

    String getDisplayValue() {
        return displayValue;
    }

    void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    String getMeritType() {
        return meritType;
    }

    void setMeritType(String meritType) {
        this.meritType = meritType;
    }
}
