
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.action.common.BaseAction;
import edu.dao.college.AdmissionDAO;
import edu.dao.college.ApplicationDAO;
import edu.dao.college.CollegeDAO;
import edu.dao.college.SscDataDAO;
import edu.dto.application.ApplicantDTO;
import edu.dto.application.ChoiceDTO;
import edu.dto.application.ResponseDTO;
import edu.dto.application.SVGDTO;
import edu.dto.application.ThanaDTO;
import edu.dto.college.ApplicantInfoDTO;
import edu.dto.college.CollegeDTO;

public class Application extends BaseAction {

	private static final long serialVersionUID = 5591077098464741028L;
	private String txid;
	private String ssc_roll;
	private String ssc_board;
	private String ssc_year;
	private String ssc_reg;
	private String user_captcha;
	private String tx_captcha;
	private String java_captcha;
	private String ssc_mother;
	private String eiin;
	private String shift_id;
	private String version_id;
	private String group_id;
	private String district_id;
	private String thana_id;
	private String helper_board_id;
	private List<ChoiceDTO> choice;
	private ApplicantDTO applicant;
	private String college_search_type;
	private String security_code;
	private String application_id;
	private String merit_type;
	private String confirm_mobile_number;

	private String quota_ff;
	private String quota_eq;
	private String quota_bksp;
	private String quota_expatriate;

	public String getApplication_id() {
		return application_id;
	}

	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}

	public String getMerit_type() {
		return merit_type;
	}

	public void setMerit_type(String merit_type) {
		this.merit_type = merit_type;
	}

	public String loadApplication() {

		CollegeDTO userDto = (CollegeDTO) session.get("user");
		if (userDto == null) {
			return "input";
		}

		/*
		 * ((SessionMap<String,Object>)session).invalidate();
		 * //session.put("one", "5");
		 * //session.put("two", "7");
		 * 
		 * int a = (int) (Math.random() * 10);
		 * int b = (int) (Math.random() * 10);
		 * int sum = a+b;
		 * 
		 * request.setAttribute("one", a);
		 * request.setAttribute("two", b);
		 * request.setAttribute("caperror", " ");
		 * String finalSum=Integer.toString(sum);
		 * 
		 * session.put("finalSum", finalSum);
		 */
		request.setAttribute("userInfo", userDto);

		return SUCCESS;
	}

	public String applicationInfoCheck() {
		// delwar task
		SscDataDAO sscDAO = new SscDataDAO();
		applicant = sscDAO.getApplication(ssc_roll, ssc_board, ssc_year, ssc_reg);
		if (applicant == null)
			return "invalid_info";
		else if (applicant.getApplication_id() != null && !applicant.getApplication_id().equalsIgnoreCase(""))
			return "already_applied"; // here should applicant choices
		else
			return SUCCESS;
	}

	public String applicationInfoCheck_College() {

		CollegeDTO userDto = (CollegeDTO) session.get("user");
		if (userDto == null) {
			return "input";
		}
		request.setAttribute("userInfo", userDto);

		// ((SessionMap<String,Object>)session).invalidate();
		SscDataDAO sscDAO = new SscDataDAO();
		applicant = sscDAO.getApplication_TT(ssc_roll, ssc_board, ssc_year, ssc_reg);
		if (applicant.getMsg().equalsIgnoreCase("nossc")) {
			request.setAttribute("error", "SSC");
			return "invalid_info";
		}

		else if (applicant.getMsg().equalsIgnoreCase("newapplication")) {
			session.put("ssc_roll", ssc_roll);
			session.put("ssc_board", ssc_board);
			session.put("ssc_year", ssc_year);
			session.put("ssc_reg", ssc_reg);
			return "newapplication";
		} else if (applicant.getMsg().equalsIgnoreCase("notAdmitted")) {
			session.put("ssc_roll", ssc_roll);
			session.put("ssc_board", ssc_board);
			session.put("ssc_year", ssc_year);
			session.put("ssc_reg", ssc_reg);
			return "notAdmitted";
		} else if (applicant.getMsg().equalsIgnoreCase("alreadyAdmitted")) {
			// SscDataDAO sscDao=new SscDataDAO();

			request.setAttribute("ssc_roll_app", ssc_roll);
			request.setAttribute("ssc_board_app", ssc_board);
			request.setAttribute("ssc_year_app", ssc_year);
			request.setAttribute("ssc_reg_app", ssc_reg);
			// applicant.getApplication_id();
			// System.out.println("id="+applicant.getApplication_id());
			ApplicantDTO ap = new ApplicantDTO();
			ap = sscDAO.getAdmittedInformation(applicant.getApplication_id());

			request.setAttribute("studentname", ap.getStudent_name());
			request.setAttribute("collegeName", ap.getCollegeName());
			request.setAttribute("ceiin", ap.getEiin());
			request.setAttribute("shift", ap.getShift());
			request.setAttribute("version", ap.getVersion());
			request.setAttribute("group", ap.getGroup());

			/*
			 * System.out.println("---"+ap.getCollegeName());
			 * System.out.println("---"+ap.getGroup());
			 * System.out.println("---"+ap.getEiin());
			 */

			session.put("ssc_roll", ssc_roll);
			session.put("ssc_board", ssc_board);
			session.put("ssc_year", ssc_year);
			session.put("ssc_reg", ssc_reg);
			return "alreadyAdmitted";
		}

		else
			return "invalid_info";

	}

	public String applicationInfoCheckTx_College() {
		// Raju task
		String resultType = "";
		String checkSum = (String) session.get("finalSum");
		if (!tx_captcha.equalsIgnoreCase(checkSum)) {
			((SessionMap<String, Object>) session).invalidate();
			int a = (int) (Math.random() * 10);
			int b = (int) (Math.random() * 10);
			int sum = a + b;
			request.removeAttribute("one");
			request.removeAttribute("two");
			request.setAttribute("one", a);
			request.setAttribute("two", b);
			String finalSum = Integer.toString(sum);
			session.put("finalSum", finalSum);
			return null;
		}
		if (tx_captcha.equalsIgnoreCase(checkSum)) {
			session.remove("finalSum");
			// SscDataDAO sscDAO=new SscDataDAO();
			/////////////////////// get TT information
			// ////////////////////////////////////////
			try {
				String urlParameters = "track_id=" + URLEncoder.encode(txid, "UTF-8");
				String targetURL = "http://114.130.64.36:9999/CAD/buetpaymentcheck.php";
				URL url;
				HttpURLConnection connection = null;
				url = new URL(targetURL);
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				connection.setRequestProperty("Content-Length", "" +
						Integer.toString(urlParameters.getBytes().length));
				connection.setRequestProperty("Content-Language", "en-US");

				connection.setUseCaches(false);
				connection.setDoInput(true);
				connection.setDoOutput(true);
				// Send request
				DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
				wr.writeBytes(urlParameters);
				wr.flush();
				wr.close();

				// Get Response
				InputStream is = connection.getInputStream();
				BufferedReader rd = new BufferedReader(new InputStreamReader(is));
				String line;
				StringBuffer response = new StringBuffer();
				System.out.println("####################### TT Response ##############################");
				while ((line = rd.readLine()) != null) {
					response.append(line);
					response.append('\r');
				}
				if (response.toString().startsWith("</reply>PAID</reply>")) {
					return "wait";
				} else {
					return "notwait";
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "invalid_info";
			}

		} else
			return "invalid_info";

	}

	public String showPersonalInfo_College() {

		SscDataDAO sscDAO = new SscDataDAO();
		applicant = sscDAO.getApplicationWId_TT(ssc_roll, ssc_board, ssc_year, ssc_reg);
		return SUCCESS;
	}

	public String showPersonalInfo_SMS() {

		SscDataDAO sscDAO = new SscDataDAO();
		applicant = sscDAO.getApplicationWId_TT(ssc_roll, ssc_board, ssc_year, ssc_reg);
		return SUCCESS;
	}

	public boolean mobile_validation_College(String confirm_mobile_number) {
		boolean tmp = true;
		Gson gson = new Gson();
		String json = "";
		System.out.println("mobile = " + confirm_mobile_number);
		Pattern patt1 = Pattern.compile("^(?:(\\+|00)?88)?01[15-9]\\d{8}$"); // "^(?:\\+?88)?01[15-9]\\d{8}$"
		Matcher matcher = patt1.matcher(confirm_mobile_number);
		if (matcher.matches()) {
			System.out.println("Valid");
			json = gson.toJson("Valid");
			setJsonResponse(json);
			tmp = true;

		} else {
			json = gson.toJson("inValid");
			setJsonResponse(json);
			System.out.println("Not Valid");
			tmp = false;
		}
		return tmp;
	}

	private boolean isValidMobile(String mobileNumber) {
		boolean tmp = true;
		if (mobileNumber.length() != 11)
			tmp = false;
		if (!(mobileNumber.substring(0, 3).equalsIgnoreCase("011")
				|| mobileNumber.substring(0, 3).equalsIgnoreCase("015")
				|| mobileNumber.substring(0, 3).equalsIgnoreCase("016")
				|| mobileNumber.substring(0, 3).equalsIgnoreCase("017")
				|| mobileNumber.substring(0, 3).equalsIgnoreCase("018")
				|| mobileNumber.substring(0, 3).equalsIgnoreCase("019")))
			tmp = false;
		return tmp;
	}

	public String editChoiceList_College() {

		CollegeDAO collegeDAO = new CollegeDAO();
		choice = collegeDAO.getChoiceList_TT(application_id);
		return SUCCESS;
	}

	public String editQuota_College() {
		boolean response = false;
		CollegeDAO collegeDAO = new CollegeDAO();
		response = collegeDAO.editQuota(quota_ff, quota_eq, quota_bksp, quota_expatriate, application_id);

		return SUCCESS;
	}

	public String getApplicantForCancel() {
		SscDataDAO sscDAO = new SscDataDAO();
		applicant = sscDAO.getApplicantForCancel(ssc_roll, ssc_board, ssc_year);
		if (applicant == null) {
			try {
				response.setContentType("text/html");
				response.getWriter().write("Not Valid Admission");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} else {
			try {
				response.setContentType("text/html");
				response.getWriter().write("<input type=\"hidden\" id=\"application_id\" value=\""
						+ applicant.getApplication_id() + "\">");
				response.getWriter()
						.write("<input type=\"hidden\" id=\"merit_type\" value=\"" + applicant.getMerit_type() + "\">");
				response.getWriter().write("<table width=\"600px\">");
				response.getWriter()
						.write("<tr><td>Application Id</td><td>Student Name</td><td>EIIN</td><td>Merit</td>");
				response.getWriter()
						.write("<tr><td>" + applicant.getApplication_id() + "</td><td>" + applicant.getStudent_name() +
								"</td><td>" + applicant.getEiin() + "</td><td>" + applicant.getMerit() + "</td>");
				response.getWriter().write("</table>");
				response.getWriter()
						.write("<br/><input type=\"button\" onclick=\"cadmission()\" value=\"Cancel Admission\">");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	public String saveCancelAdmission() {
		System.out.println("Cancel : " + application_id);
		// System.out.println(merit_type);
		try {
			ApplicationDAO applicationDAO = new ApplicationDAO();
			response.setContentType("text/html");
			if (applicationDAO.cancelAdmission(application_id, merit_type))
				response.getWriter().write("Admission Cancel Successfully");
			else
				response.getWriter().write("Cannot Cancel Admission");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String applicationInfoCheckNew() {
		SscDataDAO sscDAO = new SscDataDAO();
		applicant = sscDAO.getApplicationNew(ssc_roll, ssc_board, ssc_year, ssc_reg, ssc_mother);
		if (applicant == null)
			return "invalid_info";
		else if (applicant.getApplication_id() != null && !applicant.getApplication_id().equalsIgnoreCase(""))
			return "already_applied";
		else
			return SUCCESS;
	}

	public String getThanas() {
		HashMap<String, ArrayList<ThanaDTO>> ThanaMap = (HashMap<String, ArrayList<ThanaDTO>>) ServletActionContext
				.getServletContext().getAttribute("THANA_LIST");

		ArrayList<ThanaDTO> thanaList = ThanaMap.get(district_id);
		Gson gson = new Gson();
		String json = gson.toJson(thanaList);
		json = "{\"thana\":" + json + "}";
		setJsonResponse(json);
		return null;

	}

	public String getColleges() {

		CollegeDTO userDto = (CollegeDTO) session.get("user");

		HashMap<String, ArrayList<CollegeDTO>> collegeMap = (HashMap<String, ArrayList<CollegeDTO>>) ServletActionContext
				.getServletContext().getAttribute("ALL_COLLEGE_MAP_OPEN_ADMISSION");
		HashMap<String, ArrayList<CollegeDTO>> collegeMapDist = (HashMap<String, ArrayList<CollegeDTO>>) ServletActionContext
				.getServletContext().getAttribute("ALL_COLLEGE_MAP_DIST_OPEN_ADMISSION");

		if (college_search_type.equalsIgnoreCase("by_eiin")) {
			HashMap<String, CollegeDTO> cMap = (HashMap<String, CollegeDTO>) ServletActionContext.getServletContext()
					.getAttribute("EIIN_COLLEGE_MAP");
			CollegeDTO college = cMap.get(eiin);
			this.district_id = college.getDist_id();
			this.helper_board_id = college.getHelper_board_id();
		}

		/*** This Portion is for BTEB Second time Application Purpose ***/
		// if(!helper_board_id.equalsIgnoreCase("3")){
		// setJsonResponse("{\"colleges\":"+"{}"+",\"dist_id\"
		// :\""+"{}"+"\",\"helper_board_id\":\""+"{}"+"\"}");
		// return null;
		// }
		/*----- End ----*/
		Gson gson;
		String json = null;
		if (college_search_type.equalsIgnoreCase("by_thana")) {
			ArrayList<CollegeDTO> collegeList = collegeMap.get(userDto.getEiin() + thana_id + helper_board_id);
			/*
			 * for (int i = 0; i < collegeList.size(); i++)
			 * {
			 * if(collegeList.get(i)==null)
			 * collegeList.remove(i);
			 * }
			 */
			gson = new Gson();
			json = gson.toJson(collegeList);
			json = "{\"colleges\":" + json + ",\"thana_id\" :\"" + thana_id + "\",\"helper_board_id\":\""
					+ helper_board_id + "\"}";
		}
		if (college_search_type.equalsIgnoreCase("by_district")) {
			ArrayList<CollegeDTO> collegeList = collegeMapDist.get(userDto.getEiin() + district_id + helper_board_id);
			gson = new Gson();
			json = gson.toJson(collegeList);
			json = "{\"colleges\":" + json + ",\"district_id\" :\"" + district_id + "\",\"helper_board_id\":\""
					+ helper_board_id + "\"}";
		}

		setJsonResponse(json);
		return null;

	}

	public String getCollegeSeat() {
		Gson gson = new Gson();
		ResponseDTO response = new ResponseDTO();
		AdmissionDAO collegSVGseatDAO = new AdmissionDAO();
		ApplicantInfoDTO seatInfo = collegSVGseatDAO.getSeatInfo(shift_id, version_id, group_id, eiin);
		response.setTotal_seat(seatInfo.getTotalSeat());
		response.setAvailable_seat(seatInfo.getAvailableSeat());
		String json = gson.toJson(response);
		setJsonResponse(json);
		return null;
	}

	public String getCollegeSVG() {
		HashMap<String, SVGDTO> svgList = (HashMap<String, SVGDTO>) ServletActionContext.getServletContext()
				.getAttribute("SVG_MAP");
		SVGDTO svg = svgList.get(eiin);

		String shift = "\"shift\":" + svg.getShiftList();
		String version = "\"version\":" + svg.getShiftVersionList().toString().replaceAll("=", ":");
		version = version.replaceAll("\\[", "\"");
		version = version.replaceAll("\\]", "\"");
		version = version.replaceAll(":\\{", ":\\{\"");
		version = version.replaceAll(":\"", "\":\"");
		version = version.replaceAll("\", ", "\", \"");

		String group = "\"group\":" + svg.getVersionGroupList().toString().replaceAll("=", ":");
		group = group.replaceAll("\\[", "\"");
		group = group.replaceAll("\\]", "\"");
		group = group.replaceAll(":\\{", ":\\{\"");
		group = group.replaceAll(":\"", "\":\"");
		group = group.replaceAll("\", ", "\", \"");

		String sqYN = "\"special_quota\":" + svg.getGroupEQList().toString().replaceAll("=", ":");
		sqYN = sqYN.replaceAll("\\[", "\"");
		sqYN = sqYN.replaceAll("\\]", "\"");
		sqYN = sqYN.replaceAll(":\\{", ":\\{\"");
		sqYN = sqYN.replaceAll(":\"", "\":\"");
		sqYN = sqYN.replaceAll("\", ", "\", \"");

		String availableSeat = "\"available_seat\":" + svg.getSvgAvailableSeat().toString().replaceAll("=", ":");
		availableSeat = availableSeat.replaceAll("\\[", "\"");
		availableSeat = availableSeat.replaceAll("\\]", "\"");
		availableSeat = availableSeat.replaceAll(":\\{", ":\\{\"");
		availableSeat = availableSeat.replaceAll(":\"", "\":\"");
		availableSeat = availableSeat.replaceAll("\", ", "\", \"");

		HashMap<String, String> college_eligiblity = (HashMap<String, String>) ServletActionContext.getServletContext()
				.getAttribute("COLLEGE_ELIGIBILITY");
		String eligibility = "\"eligibility\": [" + college_eligiblity.get(eiin) + "]";
		String svgString = "{" + shift + "," + version + "," + group + "," + sqYN + "," + availableSeat + ","
				+ eligibility + "}";

		// System.out.println("JSON is here = "+group);

		setJsonResponse(svgString);
		return null;
	}

	public String submitApplication() {
		Gson gson = new Gson();
		ResponseDTO response = new ResponseDTO();
		// String
		// captcha_value=(String)request.getSession().getAttribute("servlet_captcha");
		// if(!security_code.equalsIgnoreCase(captcha_value)){
		//
		// response.setStatus("INVALID_CAPTCHA");
		//
		// }
		// else{
		ApplicationDAO applicationDAO = new ApplicationDAO();
		response = applicationDAO.validateApplication(applicant, choice, "NEW");

		if (response.getStatus().equalsIgnoreCase("VALID"))
			response = applicationDAO.saveApplication(applicant, choice, getIpAddressDTO());
		// }

		System.out.println("Application Submission Status :<<" + response.getStatus() + ">> || <<"
				+ response.getApplication_id() + ">>");
		String json = gson.toJson(response);
		setJsonResponse(json);
		return null;

	}

	public String submitApplication_College() {
		ResponseDTO response = new ResponseDTO();
		Gson gson = new Gson();
		/*
		 * if(applicant.getApplication_id()==null)
		 * {
		 * System.out.println("Please refresh your browser and submit again.!!!");
		 * response.setStatus("INVALID");
		 * response.setMessage("Please refresh your browser and submit again.!!!");
		 * String json = gson.toJson(response);setJsonResponse(json);return null;
		 * }
		 * 
		 * if(!isValidMobile(applicant.getApplication_info().getMobile_number()))
		 * {
		 * System.out.println("Please suuply valid mobile number.!!!");
		 * response.setStatus("INVALID");
		 * response.setMessage("Please suuply valid mobile number.!!!");
		 * String json = gson.toJson(response);setJsonResponse(json);return null;
		 * }
		 */
		for (int i = 0; i < choice.size(); i++) {
			if (choice.get(i) == null)
				choice.remove(i);
		}
		Set<String> uniqueChoiceSet = new HashSet<String>();
		for (int i = 0; i < choice.size(); i++) {
			ChoiceDTO tmpchoice = choice.get(i);
			uniqueChoiceSet.add(tmpchoice.getEiin() + "" + tmpchoice.getShift_id() + "" + tmpchoice.getVersion_id() + ""
					+ tmpchoice.getGroup_id() + "" + tmpchoice.getSpecial_quota());
		}

		if (choice.size() > 1) {
			System.out.println("You can select only 1 (one) choice .");
			response.setMessage("You can select only 1 (one) choice .");
			response.setStatus("INVALID");
			String json = gson.toJson(response);
			setJsonResponse(json);
			return null;
		}
		if (choice.size() < 1) {
			System.out.println("You have to  select  1 (one) choice .");
			response.setMessage("You have to  select  1 (one) choice .");
			response.setStatus("INVALID");
			String json = gson.toJson(response);
			setJsonResponse(json);
			return null;
		}

		if (uniqueChoiceSet.size() != choice.size()) {
			System.out
					.println("Sorry, you have manipulated your Application Data. We can't accept your application.!!!");
			response.setStatus("ERROR");
			response.setMessage(
					"Sorry, you have manipulated your Application Data. We can't accept your application.!!!");
			String json = gson.toJson(response);
			setJsonResponse(json);
			return null;
		}
		Set<String> choiceEiinSet = new HashSet<String>();
		for (int i = 0; i < choice.size(); i++) {
			choiceEiinSet.add(choice.get(i).getEiin());
		}
		if (choiceEiinSet.size() > 1) {
			System.out.println("You can select only 1 (one)  college.");
			response.setMessage("You can select only 1 (one)  college..");
			response.setStatus("INVALID");
			String json = gson.toJson(response);
			setJsonResponse(json);
			return null;
		}

		/*
		 * if(applicant.getApplication_info().getQuota_ff()==null)applicant.
		 * getApplication_info().setQuota_ff("N");
		 * if(applicant.getApplication_info().getQuota_eq()==null)applicant.
		 * getApplication_info().setQuota_eq("N");
		 * if(applicant.getApplication_info().getQuota_bksp()==null)applicant.
		 * getApplication_info().setQuota_bksp("N");
		 * if(applicant.getApplication_info().getQuota_expatriate()==null)applicant.
		 * getApplication_info().setQuota_expatriate("N");
		 */

		ApplicationDAO applicationDAO = new ApplicationDAO();
		response = applicationDAO.validateApplication_TT(applicant, choice);

		if (response.getStatus().equalsIgnoreCase("VALID")) {
			response = applicationDAO.saveApplication_TT(applicant, choice, getIpAddressDTO());
			/*
			 * if(response.getStatus().equalsIgnoreCase("OK"))
			 * ((SessionMap<String,Object>)session).invalidate();
			 */

		}

		System.out.println("Application Submission Status :<<" + response.getStatus() + ">> || <<"
				+ response.getApplication_id() + ">>");
		String json = gson.toJson(response);
		setJsonResponse(json);
		return null;

	}

	public String newApplicationForm() {
		return SUCCESS;
	}

	public String submitNewApplication() {
		Gson gson = new Gson();
		ResponseDTO response = new ResponseDTO();
		ApplicationDAO applicationDAO = new ApplicationDAO();
		response = applicationDAO.validateApplicationNew(applicant, choice, "NEW");

		if (response.getStatus().equalsIgnoreCase("VALID")) {
			response = applicationDAO.saveNewApplication(applicant, choice, getIpAddressDTO());
			System.out.println("Application Submission Status(2nd Phase) :<<" + response.getStatus() + ">> || <<"
					+ response.getApplication_id() + ">>");
		}
		String json = gson.toJson(response);
		setJsonResponse(json);
		return null;

	}

	public String choiceListReport_College() {
		if (application_id != null) {
			CollegeDAO collegeDAO = new CollegeDAO();
			setApplicantInfo(application_id);
			choice = collegeDAO.getChoiceList(application_id);
		}
		return SUCCESS;
	}

	public void setApplicantInfo(String applicant_id) {
		SscDataDAO sscDAO = new SscDataDAO();
		applicant = sscDAO.getApplication(applicant_id);
		session.put("applicant_info", applicant);
	}

	public String getSsc_roll() {
		return ssc_roll;
	}

	public void setSsc_roll(String sscRoll) {
		ssc_roll = sscRoll;
	}

	public String getSsc_board() {
		return ssc_board;
	}

	public void setSsc_board(String sscBoard) {
		ssc_board = sscBoard;
	}

	public String getSsc_year() {
		return ssc_year;
	}

	public void setSsc_year(String sscYear) {
		ssc_year = sscYear;
	}

	public String getEiin() {
		return eiin;
	}

	public void setEiin(String eiin) {
		this.eiin = eiin;
	}

	public List<ChoiceDTO> getChoice() {
		return choice;
	}

	public void setChoice(List<ChoiceDTO> choice) {
		this.choice = choice;
	}

	public ApplicantDTO getApplicant() {
		return applicant;
	}

	public void setApplicant(ApplicantDTO applicant) {
		this.applicant = applicant;
	}

	public String getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(String districtId) {
		district_id = districtId;
	}

	public String getCollege_search_type() {
		return college_search_type;
	}

	public void setCollege_search_type(String collegeSearchType) {
		college_search_type = collegeSearchType;
	}

	public String getHelper_board_id() {
		return helper_board_id;
	}

	public void setHelper_board_id(String helperBoardId) {
		helper_board_id = helperBoardId;
	}

	public String getSecurity_code() {
		return security_code;
	}

	public void setSecurity_code(String securityCode) {
		security_code = securityCode;
	}

	public String getSsc_reg() {
		return ssc_reg;
	}

	public void setSsc_reg(String sscReg) {
		ssc_reg = sscReg;
	}

	public String getSsc_mother() {
		return ssc_mother;
	}

	public void setSsc_mother(String sscMother) {
		ssc_mother = sscMother;
	}

	public String getUser_captcha() {
		return user_captcha;
	}

	public void setUser_captcha(String user_captcha) {
		this.user_captcha = user_captcha;
	}

	public String getJava_captcha() {
		return java_captcha;
	}

	public void setJava_captcha(String java_captcha) {
		this.java_captcha = java_captcha;
	}

	public String getConfirm_mobile_number() {
		return confirm_mobile_number;
	}

	public void setConfirm_mobile_number(String confirm_mobile_number) {
		this.confirm_mobile_number = confirm_mobile_number;
	}

	public String getQuota_ff() {
		return quota_ff;
	}

	public void setQuota_ff(String quota_ff) {
		this.quota_ff = quota_ff;
	}

	public String getQuota_eq() {
		return quota_eq;
	}

	public void setQuota_eq(String quota_eq) {
		this.quota_eq = quota_eq;
	}

	public String getQuota_bksp() {
		return quota_bksp;
	}

	public void setQuota_bksp(String quota_bksp) {
		this.quota_bksp = quota_bksp;
	}

	public String getQuota_expatriate() {
		return quota_expatriate;
	}

	public void setQuota_expatriate(String quota_expatriate) {
		this.quota_expatriate = quota_expatriate;
	}

	public String getTx_captcha() {
		return tx_captcha;
	}

	public void setTx_captcha(String tx_captcha) {
		this.tx_captcha = tx_captcha;
	}

	public String getTxid() {
		return txid;
	}

	public void setTxid(String txid) {
		this.txid = txid;
	}

	public String getThana_id() {
		return thana_id;
	}

	public void setThana_id(String thana_id) {
		this.thana_id = thana_id;
	}

	public String getShift_id() {
		return shift_id;
	}

	public void setShift_id(String shift_id) {
		this.shift_id = shift_id;
	}

	public String getVersion_id() {
		return version_id;
	}

	public void setVersion_id(String version_id) {
		this.version_id = version_id;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

}
