package edu.action.applicant;

import org.apache.struts2.dispatcher.SessionMap;

import edu.action.common.BaseAction;
import edu.dao.applicant.RetrivedPinDAO;
import edu.dto.applicant.PinRetrivalDTO;

public class RetrivedPin extends BaseAction{
	
	private static final long serialVersionUID = 5427516708608248271L;
	private String ssc_roll;
	private String ssc_reg;
	private String ssc_board;
	private String ssc_passing_year;
	private String given_mobile_number;
	private String captchaText;
	private String mname;
	private String trn_no;
	
	public String getCaptchaText() {
		return captchaText;
	}

	public void setCaptchaText(String captchaText) {
		this.captchaText = captchaText;
	}

	public String retrivedPin(){
		String checkSum=(String)session.get("CorrectAnswer");
		if(!captchaText.equalsIgnoreCase(checkSum)){
			try {
				response.setContentType("text/html");
				response.getWriter().write("Captcha Error");
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}
		((SessionMap<String,Object>)session).invalidate();
		
		String mess="";
     	RetrivedPinDAO retrivedPinDAO = new RetrivedPinDAO();
     	
		PinRetrivalDTO pinRetrivalDTO=retrivedPinDAO.getRetrivedPin(ssc_roll,ssc_reg,ssc_board,ssc_passing_year,given_mobile_number,mname,trn_no);

		//PinRetrivalDTO pinRetrivalDTO=null;
	
		
	/*	if(pinRetrivalDTO==null){
	    //request.setAttribute("applicantName",pinRetrivalDTO.getApplicantName());
			//request.setAttribute("applicationID"," ");
		    //request.setAttribute("pin"," ");
			mess="&#x9A4;&#x9CB;&#x9AE;&#x9BE;&#x9B0;&#x20;&#x9AA;&#x9CD;&#x9B0;&#x9A6;&#x9A4;&#x9CD;&#x9A4;&#x20;&#x9A4;&#x9A5;&#x9CD;&#x9AF;&#x20;&#x9B8;&#x9A0;&#x9BF;&#x995;&#x20;&#x9A8;&#x9C7;&#x987;&#x964;";
		} else {
			
			if(retrivedPinDAO.isValidSms(given_mobile_number))
	     	{
				mess="Security Code &#2468;&#2507;&#2478;&#2494;&#2480; Contact Number -&#2447; &#2438;&#2455;&#2503; &#2447;&#2453;&#2476;&#2494;&#2480; &#2474;&#2494;&#2464;&#2494;&#2472;&#2507; &#2489;&#2527;&#2503;&#2459;&#2503;&#2404;";
				//mess="&#x53;&#x65;&#x63;&#x75;&#x72;&#x69;&#x74;&#x79;&#x20;&#x43;&#x6F;&#x64;&#x65;&#x20;&#x9A4;&#x9CB;&#x9AE;&#x9BE;&#x9B0;&#x20;&#x43;&#x6F;&#x6E;&#x74;&#x61;&#x63;&#x74;&#x20;&#x4E;&#x75;&#x6D;&#x62;&#x65;&#x72;&#x20;&#x98F;&#x9B0;&#x20;&#x986;&#x997;&#x9C7;&#x20;&#x98F;&#x995;&#x9AC;&#x9BE;&#x9B0;&#x20;&#x9AA;&#x9BE;&#x9A0;&#x9BE;&#x9A8;&#x9CB;&#x20;&#x9B9;&#x9DF;&#x9C7;&#x99B;&#x9C7;&#x964;";
	     	}
			else
			{
				if(retrivedPinDAO.sendSCode(pinRetrivalDTO.getMobile(), pinRetrivalDTO.getPIN(),ssc_roll ) )
				{
						mess="&#x53;&#x65;&#x63;&#x75;&#x72;&#x69;&#x74;&#x79;&#x20;&#x43;&#x6F;&#x64;&#x65;&#x20;&#x9A4;&#x9CB;&#x9AE;&#x9BE;&#x9B0;&#x20;&#x43;&#x6F;&#x6E;&#x74;&#x61;&#x63;&#x74;&#x20;&#x4E;&#x75;&#x6D;&#x62;&#x65;&#x72;&#x20;&#x98F;&#x20;&#x9AA;&#x9BE;&#x9A0;&#x9BE;&#x9A8;&#x9CB;&#x20;&#x9B9;&#x9AC;&#x9C7;&#x964;";
						int ss=RetrivedPinDAO.AppSMSUpdate(pinRetrivalDTO.getApplicationID(), pinRetrivalDTO.getMobile());
				}
				else{
					mess="&#x9A6;&#x9C1;&#x983;&#x996;&#x9BF;&#x9A4;&#x964;&#x20;&#x9AA;&#x9B0;&#x9AC;&#x9B0;&#x9CD;&#x9A4;&#x9C0;&#x9A4;&#x9C7;&#x20;&#x99A;&#x9C7;&#x9B7;&#x9CD;&#x99F;&#x9BE;&#x20;&#x995;&#x9B0;&#x964;";
				}
			}
			
		}*/
						

		
		if(pinRetrivalDTO==null){
			
			mess="&#x9A4;&#x9CB;&#x9AE;&#x9BE;&#x9B0;&#x20;&#x9AA;&#x9CD;&#x9B0;&#x9A6;&#x9A4;&#x9CD;&#x9A4;&#x20;&#x9A4;&#x9A5;&#x9CD;&#x9AF;&#x20;&#x9B8;&#x9A0;&#x9BF;&#x995;&#x20;&#x9A8;&#x9C7;&#x987;&#x964;";
		}
		else {
			
			mess="Your(SSC Roll:"+ssc_roll+") Security Code is :"+pinRetrivalDTO.getPIN();
			
		}
		
		
		
		
		request.setAttribute("mess",mess);
		
		return "success";

	}
	
	public String getSsc_roll() {
		return ssc_roll;
	}
	public void setSsc_roll(String ssc_roll) {
		this.ssc_roll = ssc_roll;
	}
	
	public String getSsc_reg() {
		return ssc_reg;
	}

	public void setSsc_reg(String ssc_reg) {
		this.ssc_reg = ssc_reg;
	}

	public String getSsc_board() {
		return ssc_board;
	}
	public void setSsc_board(String ssc_board) {
		this.ssc_board = ssc_board;
	}
	public String getSsc_passing_year() {
		return ssc_passing_year;
	}
	public void setSsc_passing_year(String ssc_passing_year) {
		this.ssc_passing_year = ssc_passing_year;
	}
	public String getGiven_mobile_number() {
		return given_mobile_number;
	}
	public void setGiven_mobile_number(String given_mobile_number) {
		this.given_mobile_number = given_mobile_number;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getTrn_no() {
		return trn_no;
	}

	public void setTrn_no(String trn_no) {
		this.trn_no = trn_no;
	}
	

}
