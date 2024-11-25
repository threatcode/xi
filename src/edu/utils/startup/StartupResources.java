package edu.utils.startup;

import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.dao.GroupDAO;
import edu.dao.application.DistrictDAO;
import edu.dao.college.CollegeDAO;

public class StartupResources extends HttpServlet {

	private static final long serialVersionUID = 8259789890450877706L;
	Hashtable<String, String> boardHT=new Hashtable<String, String>();  
	Hashtable<String, String> boardFN=new Hashtable<String, String>();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		  
	  }
	
	public void init(ServletConfig config)  throws ServletException
	{					
		
		
		config.getServletContext().setAttribute("ALL_COLLEGE_MAP_OPEN_ADMISSION",CollegeDAO.getAllColleges_OpenAdmission());
		config.getServletContext().setAttribute("ALL_COLLEGE_MAP_DIST_OPEN_ADMISSION",CollegeDAO.getAllCollegesDist_OpenAdmission());
//		
		config.getServletContext().setAttribute("ALL_COLLEGE_MAP",CollegeDAO.getAllColleges());
		config.getServletContext().setAttribute("ALL_COLLEGE_MAP_DIST",CollegeDAO.getAllCollegesDist());
//		
		config.getServletContext().setAttribute("EIIN_COLLEGE_MAP",CollegeDAO.getEiinCollegeMap());
//		
		config.getServletContext().setAttribute("ELIGIBILITY_MAP",CollegeDAO.getCollegeEligibilityList());
		config.getServletContext().setAttribute("COLLEGE_ELIGIBILITY",CollegeDAO.getCollegeEligibility());
//		
		config.getServletContext().setAttribute("SVG_MAP",CollegeDAO.getSVGlist());
		config.getServletContext().setAttribute("GROUP_SET",GroupDAO.getGroupMapSet());
//		
//		
		config.getServletContext().setAttribute("SVG_LIST",GroupDAO.getCSVGlist());
//				
//		
		config.getServletContext().setAttribute("DISTRICT_LIST",DistrictDAO.getDistrictlist());
//		
		config.getServletContext().setAttribute("THANA_LIST",DistrictDAO.getThanalist());
		
		
		
		config.getServletContext().setAttribute("ALL_COLLEGE_MAP_DIST_BOARD",CollegeDAO.getAllCollegesDistBOARD());
		
		
		
		/* com by toraf
		config.getServletContext().setAttribute("hasManualEntrylist",CollegeDAO.hasManualEntrylist());
		
		config.getServletContext().setAttribute("hasManualEntrylistTime", new Date(System.currentTimeMillis()));
		*/
		
//		ApplicationDAO adao=new ApplicationDAO();
//		adao.generateOtpNew("1234485");
		boardHT.put("dha", "10");  
		boardHT.put("com", "11");  
		boardHT.put("raj", "12");  
		boardHT.put("jes", "13");  
		boardHT.put("chi", "14");  
		boardHT.put("bar", "15");  
		boardHT.put("syl", "16");  
		boardHT.put("din", "17");  
		boardHT.put("mad", "18");  
		boardHT.put("tec", "19");  
		boardHT.put("bou", "20");  
		config.getServletContext().setAttribute("BoardTable", boardHT);
		boardFN.put("10", "DHAKA");  
		boardFN.put("11", "COMILLA");  
		boardFN.put("12", "RAJSHAHI");  
		boardFN.put("13", "JESSORE");  
		boardFN.put("14", "CHITTAGONG");  
		boardFN.put("15", "BARISAL");  
		boardFN.put("16", "SYLHET");  
		boardFN.put("17", "DINAJPUR");  
		boardFN.put("18", "MADRASAH");  
		boardFN.put("19", "TEC");  
		boardFN.put("20", "BOU");  
		config.getServletContext().setAttribute("BoardTableFN", boardFN);
		
	}
}
