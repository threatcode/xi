package edu.action.college;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import edu.action.common.BaseAction;
import edu.dao.college.AdmissionDAO;
import edu.dto.college.CollegeDTO;
import edu.dto.college.ResultDTO;

public class CollegeResultPdf extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String shiftId;
	private String versionId;
	private String groupId;
	private String meritType;
	private String groupName;
	
	public PdfPTable generateTable()
	{
        PdfPTable table = null;
        try {
			
			 if (meritType.equalsIgnoreCase("5")) {
				 table = new PdfPTable(6);
			 }
			 else
			 {
				 table = new PdfPTable(7); 
			 }
			 
			table.setWidthPercentage(100);
			if (meritType.equalsIgnoreCase("1")) {
				table.setWidths(new float[] { 1, 2.5f, 2.5f, 2.5f, 5, 1, 3 });
			} else if (meritType.equalsIgnoreCase("2")) {
				table.setWidths(new float[] { 1, 2.5f, 2.5f, 2.5f, 4.5f, 1.5f,
						3 });
			}
			else if (meritType.equalsIgnoreCase("3")) {
				table.setWidths(new float[] { 1, 2.5f, 2.5f, 2.5f, 4.5f, 1.5f,
						3 });
			}
			else if (meritType.equalsIgnoreCase("4")) {
				table.setWidths(new float[] { 1, 2.5f, 2.5f, 2.5f, 4.5f, 1.5f,
						3 });
			}
			else if (meritType.equalsIgnoreCase("5")) {
				table.setWidths(new float[] { 1, 2.5f, 2.5f, 2.5f, 5.5f, 3.5f
						 });
			}
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell("Sl. No.");
			// table.addCell("Application ID");
			table.addCell("SSC/EQUIVALENT ROLL");
			table.addCell("SSC/EQUIVALENT BOARD");
			table.addCell("SSC/EQUIVALENT YEAR");
			table.addCell("NAME");
			if (meritType.equalsIgnoreCase("1")) {
				table.addCell("GPA");
			} else if (meritType.equalsIgnoreCase("2")) {
				table.addCell("WAITING POSITION");
			} else if (meritType.equalsIgnoreCase("3")) {
				table.addCell("WAITING POSITION");
			} else if (meritType.equalsIgnoreCase("4")) {
				table.addCell("WAITING POSITION");
			}
/*			 else if (meritType.equalsIgnoreCase("5")) {
					table.addCell("WAITING POSITION");
				}*/
			table.addCell("SELECTION STATUS");
			table.setHeaderRows(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return table;
	}
	
	
	public PdfPTable PdfResultTable(String eiin, String shift, String version, String group, String meritType) throws DocumentException {
		PdfPTable table = generateTable();
	  	AdmissionDAO admDao = new AdmissionDAO();
	  	List<ResultDTO> resultList = null;
	  	resultList = admDao.getResultData(eiin, shift, version, group, meritType);
	  	if(resultList == null){
	  		PdfPCell cell = new PdfPCell(new Paragraph("No Record Available"));
	  		cell.setColspan(8);
	  		table.addCell(cell);
	  	} else {
	  		int a = 1;
	  		String quota1="";
		  	for(ResultDTO rDto: resultList){
		  		if(!rDto.getApplicantQuota().equalsIgnoreCase(quota1))
		  		{
		  			if(!quota1.equalsIgnoreCase(""))
		  			{
		  				document.add(table);
		  				table = generateTable();
		  				document.newPage();
		  			}
		  			quota1 = rDto.getApplicantQuota();
		  		}
		  		table.addCell(Integer.toString(a));
		  	//	table.addCell(rDto.getApplicationId());
		  		table.addCell(rDto.getSscRoll());
		  		table.addCell(rDto.getSscBoard());
		  		table.addCell(rDto.getSscYear());
		  		//table.addCell(rDto.getApplicantName());
		  		PdfPCell pCell = new PdfPCell(new Paragraph(rDto.getApplicantName()));
		  		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		  		table.addCell(pCell); 
		  		
		  	    if(meritType.equalsIgnoreCase("1"))
		        {
		  	    	table.addCell(rDto.getApplicantGpa());
		        }
		        else if(meritType.equalsIgnoreCase("2")||meritType.equalsIgnoreCase("3")||meritType.equalsIgnoreCase("4"))
		        {
		        	table.addCell(rDto.getMeritPosition());
		        }

/*		  		if(rDto.getApplicantMerit().equalsIgnoreCase("1"))
		  		{
		  		if(rDto.getApplicantQuota().equalsIgnoreCase("GEN") || rDto.getApplicantQuota().equalsIgnoreCase("D_G") || rDto.getApplicantQuota().equalsIgnoreCase("E_G") || rDto.getApplicantQuota().equalsIgnoreCase("F_G")|| rDto.getApplicantQuota().equalsIgnoreCase("S_G"))
		  		{
		  			table.addCell("General");		  		
		  		}	
		  		else {
		  			table.addCell(rDto.getApplicantQuota());
		  		}
		  			
		  		}	*/
		  		
		  		if(rDto.getApplicantMerit().equalsIgnoreCase("1"))
		  		{
		  			table.addCell(rDto.getApplicantQuota());		  			
		  		}
		  		
		  		else if(rDto.getApplicantMerit().equalsIgnoreCase("2")||rDto.getApplicantMerit().equalsIgnoreCase("3")||rDto.getApplicantMerit().equalsIgnoreCase("4"))
		  		{
		  			
		  			
		  			PdfPCell qCell = new PdfPCell(new Paragraph(rDto.getApplicantQuota()));
		  			qCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			  		table.addCell(qCell);	  			
		  		}
		  		else if(rDto.getApplicantMerit().equalsIgnoreCase("5"))
		  		{
		  			
		  			
		  			PdfPCell qCell = new PdfPCell(new Paragraph("GENERAL"));
		  			qCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			  		table.addCell(qCell);	  			
		  		}
		  		
		  		else if(rDto.getApplicantMerit().equalsIgnoreCase("4fdsf"))
		  		{
		  			String quota="";
		  				
		  				quota="GEN";	
			  			  			
		  			  if(rDto.getBq().equalsIgnoreCase("Y"))
			  		{
		  				quota=quota+",BQ";	
			  		}
		  			 if(rDto.getDq().equalsIgnoreCase("Y"))
			  		{
		  				quota=quota+",DQ";	
			  		}
		  			 if(rDto.getEq().equalsIgnoreCase("Y"))
			  		{
		  				quota=quota+",EQ";	
			  		}
		  			 if(rDto.getFq().equalsIgnoreCase("Y"))
			  		{
		  				quota=quota+",FQ";	
			  		}
		  			 if(rDto.getOq().equalsIgnoreCase("Y"))
				  	{
			  			quota=quota+",OQ";	
				  	}
		  			 if(rDto.getPq().equalsIgnoreCase("Y"))
			  		{
		  				quota=quota+",PQ";	
			  		}
		  			 if(rDto.getSq().equalsIgnoreCase("Y"))
			  		{
		  				quota=quota+",SQ";	
			  		}
		  			
		  			
		  			PdfPCell qCell = new PdfPCell(new Paragraph(quota));
		  			qCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			  		table.addCell(qCell);	  				  			
		  		}		  		
		  		
		  		

		  		a++;
		  	}
	  	}

        return table;
    }
	
	String SVG = "";
	
	public PdfPTable headerTable(String collEiin, String collName, String collBoard, 
			String shift, String version, String group, Integer pageNum,String meritType) throws DocumentException {
		PdfPTable header = new PdfPTable(2);
	    header.setWidthPercentage(100);
	    header.setWidths(new int[]{1,1});
	    Font font = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
	    
	    //Paragraph p = new Paragraph(String.format("Page %d", pageNum));
	    Paragraph p = new Paragraph("");
	    PdfPCell cellTwo = new PdfPCell(p);
	    cellTwo.setColspan(2);
	    cellTwo.setVerticalAlignment(Element.ALIGN_TOP);
	    cellTwo.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    cellTwo.setBorder(Rectangle.NO_BORDER);
	    header.addCell(cellTwo);
	    
	    p = new Paragraph("BOARD OF INTERMEDIATE AND SECONDARY EDUCATION, "+collBoard, font);
	    PdfPCell cellOne = new PdfPCell(p);
	    cellOne.setColspan(2);
	    cellOne.setFixedHeight(30f);
	    cellOne.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cellOne.setBorder(Rectangle.NO_BORDER);
	    header.addCell(cellOne);
	    
	   /* if(meritType.equalsIgnoreCase("2"))
	    	p = new Paragraph("2nd List of Candidates for Class XI Admission(Session 2015-16)");
	    else*/
	    p = new Paragraph("List of Candidates for Class XI Admission(Session 2016-17)\n" +
//	    		"Admission must be done in order of waiting position and vacant seat in respective category");
	    		"Admission in vacant seats of a specific category/quota must be done in order of waiting position");
	    PdfPCell cellThree = new PdfPCell(p);
	    cellThree.setColspan(2);
	    cellThree.setFixedHeight(25f);
	    cellThree.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cellThree.setBorder(Rectangle.NO_BORDER);
	    header.addCell(cellThree);
	    
	    p = new Paragraph("COLLEGE NAME: "+collName+"  ("+collEiin+")");
	    PdfPCell cellFour = new PdfPCell(p);
	    cellFour.setColspan(2);
	    cellFour.setFixedHeight(30f);
	    cellFour.setBorder(Rectangle.NO_BORDER);
	    header.addCell(cellFour);
	    
	    
	    if(meritType.equalsIgnoreCase("1"))
	    	meritType = "Merit List";
	    else if(meritType.equalsIgnoreCase("2"))
	    	meritType = "1st Waiting List";
	    else if(meritType.equalsIgnoreCase("3"))
	    	meritType = "2nd Waiting List";
	    else if(meritType.equalsIgnoreCase("4"))
	    	meritType = "Remaining Waiting List";
	    else if(meritType.equalsIgnoreCase("5"))
	    	meritType = "Manual Admission";
/*	    else if(meritType.equalsIgnoreCase("2"))
	    	meritType = "1st Merit Migration[IN]";
	    else if(meritType.equalsIgnoreCase("3"))
	    	meritType = "2nd Merit List";
	    else if(meritType.equalsIgnoreCase("4"))
	    	meritType = "2nd Merit Migration[IN]";
	    
	    else if(meritType.equalsIgnoreCase("5"))
	    	meritType = "Release Slip";
	    
	    else if(meritType.equalsIgnoreCase("9"))
	    	meritType = "4th Phase";
	    else if(meritType.equalsIgnoreCase("15"))
	    	meritType = "BTEB 2nd Phase";
	    
	    else if(meritType.equalsIgnoreCase("1-OUT"))
	    	meritType = "1st Merit Migration[OUT]";
	    
	    else if(meritType.equalsIgnoreCase("3-OUT"))
	    	meritType = "2nd Merit Migration[OUT]";*/
	    
	    SVG += "MERIT: "+meritType + " ";
	    p = new Paragraph("MERIT: "+meritType);
	    PdfPCell cellMerit = new PdfPCell(p);
	  //  cellMerit.setColspan(2);
	    cellMerit.setFixedHeight(30f);
	    cellMerit.setBorder(Rectangle.NO_BORDER);
	    header.addCell(cellMerit);
	    
	    
	    if(shift.equalsIgnoreCase("1"))
	    	shift = "Morning";
	    else if(shift.equalsIgnoreCase("2"))
	    	shift = "Day";
	    else if(shift.equalsIgnoreCase("3"))
	    	shift = "Evening";
	    
	    if(version.equalsIgnoreCase("1"))
	    	version = "Bangla";
	    else if(version.equalsIgnoreCase("2"))
	    	version = "English";
	    	
	    SVG += shift +" ("+version+") GROUP: "+group;
	    p = new Paragraph(shift +" ("+version+")             GROUP: "+group);
	    PdfPCell cellFive = new PdfPCell(p);
	    cellFive.setColspan(3);
	    cellFive.setFixedHeight(30f);
	    cellFive.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cellFive.setBorder(Rectangle.NO_BORDER);
	    header.addCell(cellFive);
	    return header;
	}
	
	public PdfPTable footerTable(String meritType) {

        PdfPTable footer = new PdfPTable(5);
        //footer.setTotalWidth(700);
        footer.setWidthPercentage(100);

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

        Font f10nornal = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
        
        
        if(meritType.equalsIgnoreCase("1"))
        {
        	pcell=new PdfPCell(new Paragraph("N. B.: GENERAL- MERIT,OWN - SAME SCHOOL & COLLEGE,DISTRICT- DIVISIONAL/DISTRICT QUOTA,FREEDOM- FREEDOM FIGHTER QUOTA, EDUCATION- EDUCATION QUOTA, SPECIAL- SPECIAL QUOTA,BKSP- BKSP QUOTA,FOREIGN- EXPATRIATE QUOTA",f10nornal));		   
        }
        if(meritType.equalsIgnoreCase("2")||meritType.equalsIgnoreCase("3"))
        {
        	pcell=new PdfPCell(new Paragraph("N. B.: GENERAL- MERIT,OWN - SAME SCHOOL & COLLEGE,DISTRICT- DIVISIONAL/DISTRICT QUOTA,FREEDOM- FREEDOM FIGHTER QUOTA, EDUCATION- EDUCATION QUOTA, SPECIAL- SPECIAL QUOTA,BKSP- BKSP QUOTA,FOREIGN- EXPATRIATE QUOTA",f10nornal));		   
        }
        else if(meritType.equalsIgnoreCase("4")||meritType.equalsIgnoreCase("5"))
        {
        	pcell=new PdfPCell(new Paragraph("N. B.: GENERAL- GENERAL,OWN - SAME SCHOOL & COLLEGE,DISTRICT- DIVISIONAL/DISTRICT QUOTA,FREEDOM- FREEDOM FIGHTER QUOTA, EDUCATION- EDUCATION QUOTA, SPECIAL- SPECIAL QUOTA,BKSP- BKSP QUOTA,FOREIGN- EXPATRIATE QUOTA",f10nornal));	
        }	
        pcell.setColspan(2);
        pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
        pcell.setMinimumHeight(50f);
        pcell.setBorderWidth(0);
        footer.addCell(pcell);


//        pcell=new PdfPCell(new Paragraph("Page-"+document.getPageNumber(),f10nornal));
//        pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        pcell.setMinimumHeight(50f);
//        pcell.setBorderWidth(0);
//        footer.addCell(pcell);


        pcell=new PdfPCell(new Paragraph("",f10nornal));	//Verified by
        pcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        pcell.setColspan(3);
        pcell.setMinimumHeight(50f);
        pcell.setBorderWidth(0);
        footer.addCell(pcell);

        return footer;
    }
	Document document = new Document(PageSize.A4.rotate());
	public String downloadResultPdf() {
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		
		// step 1
	    
	    try {
	    	// step 2
	    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    	
	    	PdfWriter writer = PdfWriter.getInstance(document, baos);
	    	writer.setCompressionLevel(20);
//            EndPageEventAdmissionResult eEvent = new EndPageEventAdmissionResult();
//            writer.setPageEvent(eEvent);
//            eEvent.setDisplayValue(shiftId+"#"+versionId+"#"+groupName);
	    	// step 3
		    document.open();
		    // step 4
		    
		    PdfPTable header = 	headerTable(userDto.getEiin(), userDto.getCollege_name(), userDto.getBoard_name(), shiftId, versionId, groupName, document.getPageNumber()+1,meritType);	    
		    document.add(header);
		    
		    
		    PdfPTable dataTable = PdfResultTable(userDto.getEiin(),shiftId,versionId,groupId,meritType);
		    document.add(dataTable);
		    
		    PdfPTable footerTable = footerTable(meritType);
		    document.add(footerTable);
		    
		    // step 5
		    document.close();
		    		    
		    response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "attachment;filename="+userDto.getEiin()+"_result.pdf"); // open in new tab or window           
	        
	        ServletOutputStream os = response.getOutputStream();
//	        os.write(baos.toByteArray());
	        os.write(AddPageNumbers(baos.toByteArray(), writer.getPageNumber()));
	        os.flush();
	        os.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	    return null;
	}
	public byte[] AddPageNumbers(byte[] pdf, int pageNumber)
    {
		byte[] finalPdfAsBytes = null;
		try {
			Font smallFont = FontFactory.getFont("Arial", 9, Font.NORMAL);
			int pageCount = pageNumber - 1;
			PdfReader reader = new PdfReader(pdf);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			DataOutputStream output = new DataOutputStream(outputStream);
			PdfStamper stamper = new PdfStamper(reader, output);
			
			for (int i = 1; i <= pageCount; i++) {
				ColumnText.showTextAligned(stamper.getOverContent(i),
						Element.ALIGN_LEFT, new Phrase(SVG.trim() + " Page "+ i + " of " + pageCount,
								smallFont), 10, 10, 0);
			}
			stamper.close();
			finalPdfAsBytes = outputStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalPdfAsBytes;
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

	
	class EndPageEventAdmissionResult extends PdfPageEventHelper
	{
	    protected PdfPTable footer;
	    private String header="";
	    protected BaseFont helv;
	    protected PdfTemplate total;
	    private String displayValue=null;

	    public void addheader(String header)
	    {
	        this.header = header;
	    }

	    @Override
	    public void onOpenDocument(PdfWriter writer, Document document) {
	        
	    }

	    @Override
	    public void onEndPage(PdfWriter writer, Document document) {

	        PdfContentByte cb = writer.getDirectContent();

	        try
	        {
	        	footer = footerTable(meritType);
		        footer.setWidthPercentage(100);
		        footer.setHorizontalAlignment(Element.ALIGN_CENTER);
	        }
	        catch(Exception ex)
	        {
	            ex.printStackTrace();
	        }

	        footer.writeSelectedRows(0, -1, (document.right()-document.left()-600)/2-document.leftMargin(),document.bottom()+5,cb);
	    }

	    @Override
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
	        	CollegeDTO userDto = (CollegeDTO) session.get("user");
	        	String[] valArr=displayValue.split("#");
	            PdfPTable headerTable=null;
	            headerTable = 	headerTable(userDto.getEiin(), userDto.getCollege_name(), userDto.getBoard_name(), valArr[0], valArr[1], valArr[2], document.getPageNumber()+1,meritType);	    
			    
	            document.add(headerTable);

	        }catch(Exception ex){
	        	ex.printStackTrace();
	        }

	    }
	    
	    String getDisplayValue() {
	        return displayValue;
	    }

	    void setDisplayValue(String displayValue) {
	        this.displayValue = displayValue;
	    }

	    
	}
		
}
