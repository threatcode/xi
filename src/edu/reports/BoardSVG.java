package edu.reports;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import edu.action.common.BaseAction;
import edu.dao.college.CollegeDAO;
import edu.dto.UserDTO;
import edu.dto.college.CollegeDTO;

public class BoardSVG extends BaseAction {
	
	public  ServletContext servletContext;
	private String boardId;
	private String boardName;
	
//	private String boardCode;	//added by nasir for html view of svg
//	private String districtID;
	
	private ArrayList<CollegeDTO> collegeSvgList = new ArrayList<CollegeDTO>();

//	ArrayList<String> thanaIdList = new ArrayList<String>();
//	ArrayList<String> thanaNameList = new ArrayList<String>();
	
	//List<ListOfStudentOfCollegeDTO> svgList = new ArrayList<ListOfStudentOfCollegeDTO>() ;

	private static final long serialVersionUID = 2417433175132213892L;
	
	
//	private Map<Integer,String> shift = new TreeMap<Integer,String>();  
//	private Map<Integer,String> versi = new TreeMap<Integer,String>(); 
//	private Map<Integer,String> group = new TreeMap<Integer,String>();
//	
//	
//	private Map<String,String> mgender = new TreeMap<String,String>();
//	
	//private static final String FILE_NAME = "/tmp/itext.pdf";
	
	public String svgShowCollege() {
		
		UserDTO userDto = (UserDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		
//		ApplicationStatBoardDAO districtinfoDAO = new ApplicationStatBoardDAO();
//		List<DistrictinfoDTO> districtinfoList = districtinfoDAO.getDistrictinfo();
//		request.setAttribute("districtinfoList", districtinfoList);

		CollegeDAO svgDAO = new CollegeDAO();
		
		collegeSvgList=svgDAO.getCollegeSVGList(boardId);
		
//		String fileName="/mnt/svg"+boardName+".pdf";	//For storing into filepath
		
		String fileName="SVG_"+boardName+".pdf";	//For download
		
//		String boardName = "Dhaka";
		
		
//		FileOutputStream baos = new FileOutputStream(new File(fileName));	//For storing into filepath
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();	//For download
//		Document document = new Document(PageSize.A4);
		Document document = new Document(PageSize.A4.rotate());
		document.setMargins(60,60,60,72);
		PdfPTable ptable = null;
		PdfPCell pcell=null;
		
		
		try {
			ReportFormat eEvent = new ReportFormat(getServletContext(), boardName);
			CollegeDTO collegeDto = new CollegeDTO();
			
			for (int i = 0; i < collegeSvgList.size(); i++){
				collegeDto = collegeSvgList.get(i);
				if(i==0){
					PdfWriter.getInstance(document, baos).setPageEvent(eEvent);					
					document.open();
					
					
					ptable = new PdfPTable(11);		//Assigning Number of Column
					ptable.setWidthPercentage(100);
					
					ptable.setWidths(new float[]{8,10,4,22,5,5,8,7,4,4,4});	//Setting Width of the columns
//					ptable.setWidths(new float[]{15,45,40});
					
					pcell=new PdfPCell(new Paragraph("District",ReportUtil.f9B));
					pcell.setMinimumHeight(16f);					
					pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
					ptable.addCell(pcell);
					
					pcell=new PdfPCell(new Paragraph("Thana",ReportUtil.f9B));
					pcell.setMinimumHeight(16f);
					pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ptable.addCell(pcell);
					
					pcell=new PdfPCell(new Paragraph("EIIN",ReportUtil.f9B));
					pcell.setMinimumHeight(16f);
					pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ptable.addCell(pcell);
					
					pcell=new PdfPCell(new Paragraph("College Name",ReportUtil.f9B));
					pcell.setMinimumHeight(16f);
					pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ptable.addCell(pcell);
					
					pcell=new PdfPCell(new Paragraph("Shift",ReportUtil.f9B));
					pcell.setMinimumHeight(16f);
					pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ptable.addCell(pcell);
					
					pcell=new PdfPCell(new Paragraph("Version",ReportUtil.f9B));
					pcell.setMinimumHeight(16f);
					pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ptable.addCell(pcell);
					
					pcell=new PdfPCell(new Paragraph("Group Name",ReportUtil.f9B));
					pcell.setMinimumHeight(16f);
					pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ptable.addCell(pcell);
					
					pcell=new PdfPCell(new Paragraph("Gender",ReportUtil.f9B));
					pcell.setMinimumHeight(16f);
					pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ptable.addCell(pcell);
					
					pcell=new PdfPCell(new Paragraph("Min GPA",ReportUtil.f9B));
					pcell.setMinimumHeight(16f);
					pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ptable.addCell(pcell);
					
					pcell=new PdfPCell(new Paragraph("Own GPA",ReportUtil.f9B));
					pcell.setMinimumHeight(16f);
					pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ptable.addCell(pcell);
					
					pcell=new PdfPCell(new Paragraph("Seat",ReportUtil.f9B));
					pcell.setMinimumHeight(16f);
					pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ptable.addCell(pcell);
				}
				
				pcell = new PdfPCell(new Paragraph(collegeDto.getDist_name(),ReportUtil.f8));
				pcell.setMinimumHeight(15f);			
				pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				ptable.addCell(pcell);
				
				pcell = new PdfPCell(new Paragraph(collegeDto.getThana_name(),ReportUtil.f8));
				pcell.setMinimumHeight(15f);
				pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				ptable.addCell(pcell);
				
				pcell = new PdfPCell(new Paragraph(collegeDto.getEiin(),ReportUtil.f8));
				pcell.setMinimumHeight(15f);
				pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				ptable.addCell(pcell);
				
				pcell = new PdfPCell(new Paragraph(collegeDto.getCollege_name(),ReportUtil.f8));
				pcell.setMinimumHeight(15f);
				pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				ptable.addCell(pcell);
				
				pcell = new PdfPCell(new Paragraph(collegeDto.getShift(),ReportUtil.f8));
				pcell.setMinimumHeight(15f);
				pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				ptable.addCell(pcell);
				
				pcell = new PdfPCell(new Paragraph(collegeDto.getVersion(),ReportUtil.f8));
				pcell.setMinimumHeight(15f);
				pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				ptable.addCell(pcell);
				
				pcell = new PdfPCell(new Paragraph(collegeDto.getGroup(),ReportUtil.f8));
				pcell.setMinimumHeight(15f);
				pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				ptable.addCell(pcell);
				
				pcell = new PdfPCell(new Paragraph(collegeDto.getGender(),ReportUtil.f8));
				pcell.setMinimumHeight(15f);
				pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				ptable.addCell(pcell);
				
				pcell = new PdfPCell(new Paragraph(collegeDto.getMinGpa(),ReportUtil.f8));
				pcell.setMinimumHeight(15f);
				pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				ptable.addCell(pcell);
				
				pcell = new PdfPCell(new Paragraph(collegeDto.getOwnGpa(),ReportUtil.f8));
				pcell.setMinimumHeight(15f);
				pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				ptable.addCell(pcell);
				
				pcell = new PdfPCell(new Paragraph(collegeDto.getTotalSeat(),ReportUtil.f8));
				pcell.setMinimumHeight(15f);
				pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				ptable.addCell(pcell);
				
			}			
			ptable.setHeaderRows(1);
			document.add(ptable);
			document.close();		
			document.close();
			ReportUtil rptUtil = new ReportUtil();
			rptUtil.downloadPdf(baos, getResponse(),fileName);	//For Download
			//rptUtil.downloadPdf(baos, getResponse(),fileName);	//For storing into filepath
			document=null;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		if(collegeDTO!=null){
//			if(!userDto.getBoardId().equalsIgnoreCase(collegeDTO))
//				return "error";
//		}
		
		
		
		
//		svgList = svgDAO.getSVGCollege(eiinCode);
//		shift=svgDAO.getShift(eiinCode);
//		versi=svgDAO.getVersion();
//		group=svgDAO.getGroups(Integer.parseInt(userDto.getBoardId()));
//		mgender=svgDAO.getGender();
		
		//request.setAttribute("svgList", svgList);
		//request.setAttribute("userInfo", userDto);
		
				
		return null;

		
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	
	
	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	
	
	public ServletContext getServletContext() {
		System.out.println("Servlet Context: " + servletContext);
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	
	
	
}
