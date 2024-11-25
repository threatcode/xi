package edu.action.board;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

import edu.action.common.BaseAction;
import edu.dto.IpAddressDTO;
import edu.dto.board.BoardDTO;
import edu.dao.college.ListOfStudentOfCollegeDAO;

public class SaveCollegeSVG extends BaseAction{
	
	private static final long serialVersionUID = 2417433175132213892L;
	
	private String  eiin;
	
	private String collegeName;
	private String districtID;
	private String thanaID;
	private String metro;
	private String zilasader;
	private String govt;
	private String active;
	private String sqelig;
	private String mobile; 
	private String receive;
	
	private String[] shiftid;	
	private String[] verid;	
	private String[] groupid;	
	private String[] gender;	
	private String[] seat;	
	private String[] gpa;	
	private String[] ownGpa;	
	private String[] spQuota;	
	private String[] spGpa;	
	private String[] reserve;
	
	private String[] gpap;	
	private String[] ownGpap;
	private String[] spGpap;
	
	private String boardId;
	private String helpBoard;
	private IpAddressDTO ipAddress;
	
	private String userId;
	
	
	public String execute() throws Exception
	{
		
		
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}	
		
		Gson gson = new Gson();		
		String json="";
		
		String st="";
		
		
		String[] chk = {"Y", "N"};
		
		String [] shf ={"1","2","3","11","12"};
		
		String [] ver ={"1","2"};
		
		String [] gru ={"0","2","4","5","6","7","8","9","10","12"};
		
		String [] gen ={"C","F","M"};
		
		
		if(collegeName.trim()=="" || collegeName.isEmpty())
			st  = st + "College name is empty.<br> ";	
		
		
		if(!Arrays.asList( chk ).contains( metro ))
			st  = st + "Invalid Metro.<br> ";	
		
		if(!Arrays.asList( chk ).contains( zilasader ))
			st  = st + "Invalid Zila Sader.<br> ";	
		
		if(!Arrays.asList( chk ).contains( govt ))
			st  = st + "Invalid IsGovt.<br> ";
		
		if(!Arrays.asList( chk ).contains( sqelig ))
			st  = st + "Invalid IsSqEligible.<br> ";
		
		
		if(!StringUtils.isNumeric(mobile)|| mobile.length()!=11 )
			st  = st + "Invalid Mobile No.<br> ";
		
		
		
		
		for(int j = 0; j<shiftid.length; j++ )
		{
			
			if (shiftid[j].equalsIgnoreCase("-1")||!Arrays.asList( shf ).contains( shiftid[j] )){
				st  = st + "Select Shift Name at row no: "+(j+1)+".<br> ";							
			}
			if (verid[j].equalsIgnoreCase("-1") || !Arrays.asList( ver ).contains( verid[j] )){
				st  = st + "Select Version Name at row no: "+(j+1)+". <br>  ";							
			}
			if (groupid[j].equalsIgnoreCase("-1") || !Arrays.asList( gru ).contains( groupid[j] )){
				st  = st + "Select Group Name at row no: "+(j+1)+". <br> ";							
			}
			if (gender[j].equalsIgnoreCase("-1") || !Arrays.asList( gen ).contains( gender[j] )){
				st  = st + "Select Gender at row no: "+(j+1)+". <br> ";							
			}
			
			
			if(seat[j].trim().equalsIgnoreCase("")||seat[j].isEmpty()){
				st  = st + "Seat must be greater than or equal to zero  at row no: "+(j+1)+". <br> ";}
			else if (Integer.parseInt(seat[j])<0){
				st  = st + "Seat must be greater than or equal to zero  at row no: "+(j+1)+". <br> ";							
			}
			
			
			if (gpa[j].trim().equalsIgnoreCase("")||gpa[j].isEmpty()){
				st  = st + "GPA must be greater than zero and less than 5 at row no: "+(j+1)+". <br> ";							
			}
			else if (Double.parseDouble(gpa[j])<0 || Double.parseDouble(gpa[j])>5.0 ){
				st  = st + "GPA must be greater than zero and less than 5 at row no: "+(j+1)+". <br> ";							
			}
			
			if (Double.parseDouble(gpa[j])>Double.parseDouble(gpap[j]) ){
				st  = st + "GPA must be less or equal to previous at row no: "+(j+1)+". <br> ";							
			}
			
			
			
			if (ownGpa[j].trim().equalsIgnoreCase("")||ownGpa[j].isEmpty()){
				st  = st + "Own GPA must be greater than zero and less than 5 at row no: "+(j+1)+". <br> ";							
			} 
			else if (Double.parseDouble(ownGpa[j])<0 || Double.parseDouble(ownGpa[j])>5.0){
				st  = st + "Own GPA must be greater than zero and less than 5 at row no: "+(j+1)+". <br> ";							
			}
			
			if (Double.parseDouble(ownGpa[j])>Double.parseDouble(ownGpap[j]) ){
				st  = st + "Own GPA must be less or equal to previous at row no: "+(j+1)+". <br> ";							
			}
			
			
			if (Double.parseDouble(spGpa[j])>Double.parseDouble(spGpap[j]) ){
				st  = st + "SQ GPA must be less or equal to previous at row no: "+(j+1)+". <br> ";							
			}
			
			
		}
		
		ListOfStudentOfCollegeDAO  dbDao =new ListOfStudentOfCollegeDAO();
		
		if(!dbDao.isValidDistThana(districtID, thanaID))
			st  = st + "Invalid District or Thana.<br> ";
		
		if(st!="")
		{
			json = gson.toJson(st);
			setJsonResponse(json);
		}
		else
		{
		
		
			String boardid=userDto.getBoardId(); 
			IpAddressDTO ipDTO=getIpAddressDTO();
			String helpBoard="1";
			
			if(boardid.equalsIgnoreCase("18"))
				helpBoard="2";
			
			setBoardId(boardid);
			setUserId(userDto.getBoardUserId());
			setHelpBoard(helpBoard);
			setIpAddress(ipDTO);
			
			
			
			int i = dbDao.saveSVGCollege(this);
			
			
		
			if(i==1)
			{
				json = gson.toJson("success");
				setJsonResponse(json);
			}
			else
			{
				json = gson.toJson("An Error occurred while updating");
				setJsonResponse(json);
			}
		}
		
		
		return null;	
	}

	
	
	

	public String[] getGpap() {
		return gpap;
	}





	public void setGpap(String[] gpap) {
		this.gpap = gpap;
	}





	public String[] getOwnGpap() {
		return ownGpap;
	}





	public void setOwnGpap(String[] ownGpap) {
		this.ownGpap = ownGpap;
	}





	public String[] getSpGpap() {
		return spGpap;
	}





	public void setSpGpap(String[] spGpap) {
		this.spGpap = spGpap;
	}





	public String getReceive() {
		return receive;
	}





	public void setReceive(String receive) {
		this.receive = receive;
	}





	public String getUserId() {
		return userId;
	}




	public void setUserId(String userId) {
		this.userId = userId;
	}




	public String getBoardId() {
		return boardId;
	}




	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}




	public String getHelpBoard() {
		return helpBoard;
	}




	public void setHelpBoard(String helpBoard) {
		this.helpBoard = helpBoard;
	}




	public IpAddressDTO getIpAddress() {
		return ipAddress;
	}




	public void setIpAddress(IpAddressDTO ipAddress) {
		this.ipAddress = ipAddress;
	}




	public String getEiin() {
		return eiin;
	}


	public void setEiin(String eiin) {
		this.eiin = eiin;
	}


	public String[] getShiftid() {
		return shiftid;
	}


	public void setShiftid(String[] shiftid) {
		this.shiftid = shiftid;
	}


	public String[] getVerid() {
		return verid;
	}


	public void setVerid(String[] verid) {
		this.verid = verid;
	}


	public String[] getGroupid() {
		return groupid;
	}


	public void setGroupid(String[] groupid) {
		this.groupid = groupid;
	}


	public String[] getGender() {
		return gender;
	}


	public void setGender(String[] gender) {
		this.gender = gender;
	}


	public String[] getSeat() {
		return seat;
	}


	public void setSeat(String[] seat) {
		this.seat = seat;
	}


	public String[] getGpa() {
		return gpa;
	}


	public void setGpa(String[] gpa) {
		this.gpa = gpa;
	}


	public String[] getOwnGpa() {
		return ownGpa;
	}


	public void setOwnGpa(String[] ownGpa) {
		this.ownGpa = ownGpa;
	}


	public String[] getSpQuota() {
		return spQuota;
	}


	public void setSpQuota(String[] spQuota) {
		this.spQuota = spQuota;
	}


	public String[] getSpGpa() {
		return spGpa;
	}


	public void setSpGpa(String[] spGpa) {
		this.spGpa = spGpa;
	}


	public String[] getReserve() {
		return reserve;
	}


	public void setReserve(String[] reserve) {
		this.reserve = reserve;
	}


	public String getCollegeName() {
		return collegeName;
	}


	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}


	public String getDistrictID() {
		return districtID;
	}


	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}


	public String getThanaID() {
		return thanaID;
	}


	public void setThanaID(String thanaID) {
		this.thanaID = thanaID;
	}


	public String getMetro() {
		return metro;
	}


	public void setMetro(String metro) {
		this.metro = metro;
	}


	public String getZilasader() {
		return zilasader;
	}


	public void setZilasader(String zilasader) {
		this.zilasader = zilasader;
	}


	public String getGovt() {
		return govt;
	}


	public void setGovt(String govt) {
		this.govt = govt;
	}


	public String getActive() {
		return active;
	}


	public void setActive(String active) {
		this.active = active;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getSqelig() {
		return sqelig;
	}


	public void setSqelig(String sqelig) {
		this.sqelig = sqelig;
	}
	
	
	
	
	
	

}
