package edu.action.board;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.struts2.dispatcher.SessionMap;

import nl.captcha.Captcha;
import nl.captcha.gimpy.BlockGimpyRenderer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.NumbersAnswerProducer;
import edu.action.common.BaseAction;
import edu.dao.board.ApplicationStatBoardDAO;
import edu.dao.college.ListOfStudentOfCollegeDAO;
import edu.dto.board.BoardDTO;
import edu.dto.board.CollegeInfoDTO;
import edu.dto.board.DistrictinfoDTO;
import edu.dto.college.CollegeDTO;
import edu.dto.college.ListOfStudentOfCollegeDTO;
import edu.utils.connection.ConnectionManager;

public class CollegeSVGEntry extends BaseAction{
	
	private String eiinCode;
	private String boardCode;	//added by nasir for html view of svg
	private String districtID;
	
	private String user_captcha; //added for captcha
	
	private CollegeDTO collegeDTO;

	ArrayList<String> thanaIdList = new ArrayList<String>();
	ArrayList<String> thanaNameList = new ArrayList<String>();
	
	List<ListOfStudentOfCollegeDTO> svgList = new ArrayList<ListOfStudentOfCollegeDTO>() ;

	private static final long serialVersionUID = 2417433175132213892L;
	
	
	private Map<Integer,String> shift = new TreeMap<Integer,String>();  
	private Map<Integer,String> versi = new TreeMap<Integer,String>(); 
	private Map<Integer,String> group = new TreeMap<Integer,String>();
	
	
	private Map<String,String> mgender = new TreeMap<String,String>();
	
	public String svgShowCollege() {
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		
		ApplicationStatBoardDAO districtinfoDAO = new ApplicationStatBoardDAO();
		List<DistrictinfoDTO> districtinfoList = districtinfoDAO.getDistrictinfo();
		request.setAttribute("districtinfoList", districtinfoList);

		ListOfStudentOfCollegeDAO svgDAO = new ListOfStudentOfCollegeDAO();
		
		collegeDTO=svgDAO.getCollegeDTO(eiinCode);
		
		
		if(collegeDTO!=null){
			if(!userDto.getBoardId().equalsIgnoreCase(collegeDTO.getBoard_id()))
				return "error";
		}
			
		
		
		svgList = svgDAO.getSVGCollege(eiinCode);
		shift=svgDAO.getShift(eiinCode);
		versi=svgDAO.getVersion();
		group=svgDAO.getGroups(Integer.parseInt(userDto.getBoardId()));
		mgender=svgDAO.getGender();
		
		//request.setAttribute("svgList", svgList);
		request.setAttribute("userInfo", userDto);
		
				
		return "success";

		
	}
	
	
	// @Action(value = "captcha", fault = {@Result(type="stream", params =
		// {"contentType", "image/jpeg"})})
//		private InputStream inputStream;
//
//		public String captcha() {
//			try {
//				Captcha captcha = new Captcha.Builder(160, 40)
//						.addText(new NumbersAnswerProducer())
//						.gimp(new BlockGimpyRenderer()).build();
//
//				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//				// write the image
//				CaptchaServletUtil.writeImage(outputStream, captcha.getImage());
//				// store the answer for this in session
//				session.put("CorrectAnswer", captcha.getAnswer());
//				
//				System.out.println("Recreated captcha: " + captcha.getAnswer());
//				// return image
//				inputStream = new ByteArrayInputStream(outputStream.toByteArray());
//				return SUCCESS;
//			} catch (Exception e) {
//				e.printStackTrace();
//				return null;
//			}
//
//		}
	
//	private InputStream inputStream;
	/**
	 * @author nasir
	 * To show svg info of college
	 * @param eiinCode and boardCode
	 * @return Thana
	 */
	public String showCollege(){
		
		String checkSum = (String) session.get("CorrectAnswer");
		boolean checkCaptcha = false;
		
	
		checkCaptcha = user_captcha.equalsIgnoreCase(checkSum);
		
		if (!checkCaptcha) {
			try {
				response.setContentType("text/html");
				response.getWriter().write("ce");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
		if (checkCaptcha){
			ListOfStudentOfCollegeDAO svgDAO = new ListOfStudentOfCollegeDAO();	
			collegeDTO=svgDAO.getCollegeDTOforHtmlSvg(eiinCode); //Retrieving College Info		
			svgList = svgDAO.getSVGCollegeForHtmlView(eiinCode);	//Retrieving SVG of the college		
			versi=svgDAO.getVersion();
			if(collegeDTO !=null){
				group=svgDAO.getGroups(Integer.parseInt(collegeDTO.getEiin()));
			}
			request.setAttribute("svgList", svgList);
			
			((SessionMap<String, Object>) session).invalidate();
			
			return "success";
		} else
			return "invalid_info";
		
		
	}
	
	public String districtinfo(){
		
		
		ApplicationStatBoardDAO districtinfoDAO = new ApplicationStatBoardDAO();
		List<DistrictinfoDTO> districtinfoList = districtinfoDAO.getDistrictinfo();
		request.setAttribute("districtinfoList", districtinfoList);

		return "success";

	}
	
	public String districtWisecollegeinfo(){
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}

		ApplicationStatBoardDAO collegeinfoDAO = new ApplicationStatBoardDAO();
		List<CollegeInfoDTO> collegeinfoList = collegeinfoDAO.getCollegeInfo(userDto.getBoardId());
		request.setAttribute("collegeinfoList", collegeinfoList);
		request.setAttribute("userInfo", userDto);
		return "success";

	}
	

	
	public String collegeinfo(){
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}

		ApplicationStatBoardDAO collegeinfoDAO = new ApplicationStatBoardDAO();
		List<CollegeInfoDTO> collegeinfoList = collegeinfoDAO.getCollegeInfo(userDto.getBoardId());
		request.setAttribute("collegeinfoList", collegeinfoList);
		request.setAttribute("userInfo", userDto);
		return "success";

	}
	

	
	
	public String shiftVersionGroupApplicationNumberOfCollege(){
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}

		ListOfStudentOfCollegeDAO shiftVersionGroupApplicationNumberOfCollegeDAO = new ListOfStudentOfCollegeDAO();
		List<ListOfStudentOfCollegeDTO> shiftVersionGroupApplicationNumberOfCollegeList = shiftVersionGroupApplicationNumberOfCollegeDAO.getlistOfShiftVersionGroupApplicationNumberOfCollege(eiinCode,userDto.getBoardId());
		request.setAttribute("shiftVersionGroupApplicationNumberOfCollegeList", shiftVersionGroupApplicationNumberOfCollegeList);
		request.setAttribute("userInfo", userDto);
		return "success";

			
		          

	}

	public String searchThana(){
	/*	System.out.println("HIT districtID:"+districtID);*/
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}

		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = "SELECT THANA_ID, THANA_NAME  " +
				" FROM MST_THANA where DIST_ID=? " +
				" order by THANA_NAME " ;

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, districtID);
			
			r = stmt.executeQuery();
			
		
			while(r.next())
			{
				thanaIdList.add(r.getString("THANA_ID"));
				thanaNameList.add(r.getString("THANA_NAME"));
				

			}
	/*		System.out.println("eiinCodeList.size="+eiinCodeList.size());
			System.out.println("collegeNamelist.size="+collegeNamelist.size());*/
		} 
		catch (Exception e){e.printStackTrace();}
		finally{
			try{
				stmt.close();
				conn.close();
				} 
			catch (Exception e){
					e.printStackTrace();
				}
			stmt = null;
			conn = null;
		}
		
		return "success";

	}
	
	/**
	 * @author nasir
	 * To show thana name in the html view of svg
	 * @param dist_id
	 * @return Thana
	 */
	public String searchThanaForSVG(){
		/*	System.out.println("HIT districtID:"+districtID);*/

			Connection conn = ConnectionManager.getReadConnection();
			
			String sql = "SELECT THANA_ID, THANA_NAME  " +
					" FROM MST_THANA where DIST_ID=? " +
					" order by THANA_NAME " ;

			PreparedStatement stmt = null;
			ResultSet r = null;
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, districtID);
				
				r = stmt.executeQuery();
				
			
				while(r.next())
				{
					thanaIdList.add(r.getString("THANA_ID"));
					thanaNameList.add(r.getString("THANA_NAME"));
					

				}
		/*		System.out.println("eiinCodeList.size="+eiinCodeList.size());
				System.out.println("collegeNamelist.size="+collegeNamelist.size());*/
			} 
			catch (Exception e){e.printStackTrace();}
			finally{
				try{
					stmt.close();
					conn.close();
					} 
				catch (Exception e){
						e.printStackTrace();
					}
				stmt = null;
				conn = null;
			}
			
			return "success";

		}

	
	public String getDistrictID() {
		return districtID;
	}

	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}

	public String getEiinCode() {
		return eiinCode;
	}


	public void setEiinCode(String eiinCode) {
		this.eiinCode = eiinCode;
	}



	public ArrayList<String> getThanaIdList() {
		return thanaIdList;
	}

	public void setThanaIdList(ArrayList<String> thanaIdList) {
		this.thanaIdList = thanaIdList;
	}

	public ArrayList<String> getThanaNameList() {
		return thanaNameList;
	}

	public void setThanaNameList(ArrayList<String> thanaNameList) {
		this.thanaNameList = thanaNameList;
	}

	public Map<Integer, String> getShift() {
		return shift;
	}

	public void setShift(Map<Integer, String> shift) {
		this.shift = shift;
	}

	public Map<Integer, String> getVersi() {
		return versi;
	}

	public void setVersi(Map<Integer, String> versi) {
		this.versi = versi;
	}

	public Map<Integer, String> getGroup() {
		return group;
	}

	public void setGroup(Map<Integer, String> group) {
		this.group = group;
	}

	public List<ListOfStudentOfCollegeDTO> getSvgList() {
		return svgList;
	}

	public void setSvgList(List<ListOfStudentOfCollegeDTO> svgList) {
		this.svgList = svgList;
	}

	public Map<String, String> getMgender() {
		return mgender;
	}

	public void setMgender(Map<String, String> mgender) {
		this.mgender = mgender;
	}

	public CollegeDTO getCollegeDTO() {
		return collegeDTO;
	}

	public void setCollegeDTO(CollegeDTO collegeDTO) {
		this.collegeDTO = collegeDTO;
	}


	public String getUser_captcha() {
		return user_captcha;
	}


	public void setUser_captcha(String user_captcha) {
		this.user_captcha = user_captcha;
	}

	
//	public String getBoradCode() {
//		return boardCode;
//	}
//
//	public void setBoradCode(String boradCode) {
//		this.boardCode = boradCode;
//	}

	
	
	
	

}
