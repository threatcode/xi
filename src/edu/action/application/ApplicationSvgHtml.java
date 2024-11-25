package edu.action.application;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.ws.Action;

import nl.captcha.Captcha;
import nl.captcha.gimpy.*;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.NumbersAnswerProducer;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.json.JSONObject;

import edu.utils.upload.UploadResponse;
import edu.utils.upload.UploadToProvider;

import com.cache.dto.Hello;
import com.cache.dto.Result;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.surecash.cad.CadPaymentVerifyByUserReq;
import com.surecash.cad.CadPaymentVerifyReq;
import com.surecash.cad.CadPaymentVerifyResp;
import com.surecash.cad.CadService;

import edu.action.common.BaseAction;
import edu.dao.ResultLog;
import edu.dao.application.ApplicationDAO;
import edu.dao.application.SscDataDAO;
import edu.dao.board.ApplicationStatBoardDAO;
import edu.dao.college.CollegeDAO;
import edu.dto.UserDTO;
import edu.dto.applicant.ResultDTO;
import edu.dto.application.ApplicantDTO;
import edu.dto.application.ChoiceDTO;
import edu.dto.application.ResponseDTO;
import edu.dto.application.SVGDTO;
import edu.dto.application.ThanaDTO;
import edu.dto.board.BoardDTO;
import edu.dto.board.CollegeInfoDTO;
import edu.dto.college.CollegeDTO;

//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class ApplicationSvgHtml extends BaseAction {
	private static final long serialVersionUID = 5591077098464741028L;
	private String trxid;
	private String ssc_roll;
	private String ssc_board;
	private String ssc_year;
	private String ssc_reg;
	private String user_captcha;
	private String tx_captcha;
	private String java_captcha;
	private String ssc_mother;
	private String eiin;
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
	private String contactno;
	private String updateCount;
	private String organization;
	
	private String runTimeCaptcha;
	
	
	
	public String getRunTimeCaptcha() {
		return runTimeCaptcha;
	}

	public void setRunTimeCaptcha(String runTimeCaptcha) {
		this.runTimeCaptcha = runTimeCaptcha;
	}

	private String scode;

	public String getScode() {
		return scode;
	}

	public void setScode(String scode) {
		this.scode = scode;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

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
		((SessionMap<String, Object>) session).invalidate();
		return SUCCESS;
	}

	public String cancelAdmission() {
		BoardDTO userDto = (BoardDTO) session.get("user");
		if (userDto != null)
			return SUCCESS;
		else
			return null;
	}

	public String ResultView() {
		String xForward = ServletActionContext.getRequest().getHeader(
				"X-Forwarded-For");
		String via = ServletActionContext.getRequest().getHeader("Via");
		String remoteAddress = ServletActionContext.getRequest()
				.getRemoteAddr();
		ResultLog rl = new ResultLog();
		rl.setXForward(xForward);
		rl.setVia(via);
		rl.setRemoteAddress(remoteAddress);
		rl.setOp("first");
		Thread thread = new Thread(rl);
		thread.start();

		return SUCCESS;
	}

	public String viewRegPayment() {
		return SUCCESS;
	}

	public String applicationInfoCheck() {
		// delwar task
		SscDataDAO sscDAO = new SscDataDAO();
		applicant = sscDAO.getApplication(ssc_roll, ssc_board, ssc_year,
				ssc_reg);
		if (applicant == null)
			return "invalid_info";
		else if (applicant.getApplication_id() != null
				&& !applicant.getApplication_id().equalsIgnoreCase(""))
			return "already_applied"; // here should applicant choices
		else
			return SUCCESS;
	}

	public String create() {
		// RegisterAction is the form bean of the current action and
		// captchaResponse is the field of user input

		String answer = (String) ActionContext.getContext().getSession()
				.get("CorrectAnswer");
		if (answer == null) {
			addFieldError("captchaResponse", getText("error.captcha"));
		}
		return SUCCESS;
	}

	// @Action(value = "captcha", fault = {@Result(type="stream", params =
	// {"contentType", "image/jpeg"})})
	private InputStream inputStream;

	public String captcha() {
		try {
			Captcha captcha = new Captcha.Builder(160, 40)
					.addText(new NumbersAnswerProducer())
					.gimp(new BlockGimpyRenderer()).build();

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			// write the image
			CaptchaServletUtil.writeImage(outputStream, captcha.getImage());
			// store the answer for this in session
			session.put("CorrectAnswer", captcha.getAnswer());
			// return image
			inputStream = new ByteArrayInputStream(outputStream.toByteArray());
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public String getResult() {

		String meritTable = " ";
		String checkSum = (String) session.get("CorrectAnswer");
		if (!user_captcha.equalsIgnoreCase(checkSum)) {
			try {
				meritTable += "<div class=\"box_container\" style=\"overflow: hidden; position: relative; height: 100%; margin-top: 8px;\" id=\"personal_result_div\"><div class=\"box_body\" style=\"padding: 12px;\"><div class=\"successMessageDiv red\" id=\"finalResultDiv\">";
				meritTable += "<div  class=\"successResultMessage red\">"
						+ "&#2438;&#2474;&#2472;&#2494;&#2480; &#2470;&#2503;&#2451;&#2527;&#2494; &#2477;&#2503;&#2480;&#2495;&#2475;&#2495;&#2453;&#2503;&#2486;&#2472; &#2453;&#2507;&#2465; &#2463;&#2495; &#2488;&#2464;&#2495;&#2453; &#2472;&#2527;&#2404;<br/><br/>The verification code you have provided is not correct.</div>";
				// meritTable +=
				// "<input type=\"button\" class=\"myButton green\" id=\"clearButton\" value=\"Refresh This Page\" onclick=\"clearButton()\"/>";
				meritTable += "</div></div></div>";
				try {
					response.setContentType("text/html");
					response.getWriter().write(meritTable);
				} catch (Exception e) {
				}
				return null;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		((SessionMap<String, Object>) session).invalidate();
		double startTime = System.nanoTime();
		Result tmp = null;
		try {
			String resultlocation = "none";
			double startTime1 = System.nanoTime();
			tmp = getResultFromRMI(ssc_roll.trim(), ssc_board.trim(),
					ssc_year.trim(), ssc_reg.trim());
			resultlocation = "memory";
			if (tmp == null) {
				tmp = ApplicationDAO.getResultByApplicant(ssc_roll.trim(),
						ssc_board.trim(), ssc_year.trim(), ssc_reg.trim());
				resultlocation = "database";
			}
			if (tmp == null)
				resultlocation = "none";
			double endTime1 = System.nanoTime();
			System.out.println("Duration1 : " + resultlocation + " : "
					+ (endTime1 - startTime1) / 1000000);

			String xForward = ServletActionContext.getRequest().getHeader(
					"X-Forwarded-For");
			String via = ServletActionContext.getRequest().getHeader("Via");
			String remoteAddress = ServletActionContext.getRequest()
					.getRemoteAddr();

			ResultLog rl = new ResultLog();
			rl.setXForward(xForward);
			rl.setVia(via);
			rl.setRemoteAddress(remoteAddress);
			rl.setRoll(ssc_roll);
			rl.setBoard(ssc_board);
			rl.setPyear(ssc_year);
			rl.setReg(ssc_reg);
			rl.setRtime((endTime1 - startTime1) / 1000000);
			rl.setResultlocation(resultlocation);
			rl.setOp("second");
			Thread thread = new Thread(rl);
			thread.start();

		} catch (Exception e) {
			// TODO: handle exception
		}

		if (tmp != null) {
			if (tmp.getMerit_type() == null) {
				// paid but not applied
				meritTable += "<div class=\"box_container\" style=\"overflow: hidden; position: relative; height: 100%; margin-top: 8px;\" id=\"personal_result_div\"><div class=\"box_header\">Your Result</div><div class=\"box_body\" style=\"padding: 12px;\"><div class=\"successMessageDiv red\" id=\"finalResultDiv\">";
				meritTable += "<div class=\"applicantDetails\"><b>Applicant Name</b> : "
						+ tmp.getName() + "</div>";
				meritTable += "<div  class=\"successResultMessage red\">"
						+ "&#2468;&#2497;&#2478;&#2495; &#2486;&#2503;&#2487; &#2474;&#2480;&#2509;&#2479;&#2494;&#2527;&#2503; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2495;&#2468; &#2489;&#2451;&#2472;&#2495;&#2404;</div>";
				// meritTable +=
				// "<div  class=\"successResultMessage smallSize\">" +
				// "&#2474;&#2480;&#2476;&#2480;&#2509;&#2468;&#2496;&#2468;&#2503; &#2476;&#2495;&#2477;&#2495;&#2472;&#2509;&#2472; &#2474;&#2509;&#2480;&#2468;&#2495;&#2488;&#2509;&#2464;&#2494;&#2472;&#2503; &#2438;&#2488;&#2472; &#2454;&#2494;&#2482;&#2495; &#2469;&#2494;&#2453;&#2494; &#2488;&#2494;&#2474;&#2503;&#2453;&#2509;&#2487;&#2503; &#2437;&#2472;-&#2482;&#2494;&#2439;&#2472;&#2503; &#2477;&#2480;&#2509;&#2468;&#2495;&#2480; &#2476;&#2509;&#2479;&#2476;&#2488;&#2509;&#2469;&#2494; &#2453;&#2480;&#2494; &#2489;&#2476;&#2503;&#2404;</div>";
				// meritTable +=
				// "<input type=\"button\" class=\"myButton green\" id=\"clearButton\" value=\"Refresh This Page\" onclick=\"clearButton()\"/>";
				meritTable += "</div></div></div>";
				try {
					response.setContentType("text/html");
					response.getWriter().write(meritTable);
				} catch (Exception e) {
				}
				return null;
			} else {
				if (!(tmp.getMerit_type().equalsIgnoreCase("2") || tmp
						.getMerit_type().equalsIgnoreCase("4"))) {
					String shift = "";
					if (tmp.getSHIFT_ID().equalsIgnoreCase("1"))
						shift = "Morning";
					else if (tmp.getSHIFT_ID().equalsIgnoreCase("2"))
						shift = "Day";
					else if (tmp.getSHIFT_ID().equalsIgnoreCase("3"))
						shift = "Evening";
					else if (tmp.getSHIFT_ID().equalsIgnoreCase("11"))
						shift = "Morning";
					else if (tmp.getSHIFT_ID().equalsIgnoreCase("12"))
						shift = "Day";
					String version = "";
					if (tmp.getVERSION_ID().equalsIgnoreCase("1"))
						version = "Bangla";
					else if (tmp.getVERSION_ID().equalsIgnoreCase("2"))
						version = "English";

					meritTable += "<div class=\"box_container\" style=\"overflow: hidden; position: relative; height: 100%; margin-top: 8px;\" id=\"personal_result_div\"><div class=\"box_header\">Your Result</div><div class=\"box_body\" style=\"padding: 12px;\"><div class=\"successMessageDiv\" id=\"finalResultDiv\">";
					meritTable += "<center><div class=\"applicantDetails\"><b>Applicant Name</b> : "
							+ tmp.getName() + "</div>";
					meritTable += "<div  class=\"successResultMessage\">"
							+ "&#2437;&#2477;&#2495;&#2472;&#2472;&#2509;&#2470;&#2472;! &#2438;&#2474;&#2472;&#2495; &#2472;&#2495;&#2458;&#2503;&#2480; &#2453;&#2482;&#2503;&#2460;&#2503; &#2477;&#2480;&#2509;&#2468;&#2495;&#2480; &#2460;&#2472;&#2509;&#2479; &#2478;&#2472;&#2507;&#2472;&#2496;&#2468; &#2489;&#2527;&#2503;&#2459;&#2503;&#2472;";
					meritTable += "</div><table class=\"CollegeDetailsTable\" id=\"selection_row_table\" style=\"width: 828px\">";

					meritTable += "<tr>"
							+ "<th width=\"40%\" >College Name</td>"
							+ "  <th width=\"10%\" >EIIN</td>"
							+ " <th width=\"10%\">Shift</td>"
							+ "<th width=\"10%\">Version</td>"
							+ "<th width=\"20%\">Group</td>"
							+ "<th width=\"10%\">Quota</td>" + "</tr>";
					meritTable += "<tr><td align=\"left\">"
							+ tmp.getCOLLEGE_NAME() + "<td align=\"center\">"
							+ tmp.getEIIN() + "<td align=\"center\">" + shift
							+ "<td align=\"center\">" + version
							+ "<td align=\"center\">" + tmp.getGROUP_NAME()
							+ "<td align=\"left\">" + tmp.getQUOTA_TYPE()
							+ "<tr>";

					meritTable += "</table></center>";
					meritTable += "</div></div></div>";
					try {
						response.setContentType("text/html");
						response.getWriter().write(meritTable);
					} catch (Exception e) {
					}
				} else {
					String shift = "";
					if (tmp.getSHIFT_ID().equalsIgnoreCase("1"))
						shift = "Morning";
					else if (tmp.getSHIFT_ID().equalsIgnoreCase("2"))
						shift = "Day";
					else if (tmp.getSHIFT_ID().equalsIgnoreCase("3"))
						shift = "Evening";
					else if (tmp.getSHIFT_ID().equalsIgnoreCase("11"))
						shift = "Morning";
					else if (tmp.getSHIFT_ID().equalsIgnoreCase("12"))
						shift = "Day";
					String version = "";
					if (tmp.getVERSION_ID().equalsIgnoreCase("1"))
						version = "Bangla";
					else if (tmp.getVERSION_ID().equalsIgnoreCase("2"))
						version = "English";

					meritTable += "<div class=\"box_container\" style=\"overflow: hidden; position: relative; height: 100%; margin-top: 8px;\" id=\"personal_result_div\"><div class=\"box_header\">Your Result</div><div class=\"box_body\" style=\"padding: 12px;\"><div class=\"successMessageDiv\" id=\"finalResultDiv\">";
					meritTable += "<center><div class=\"applicantDetails\"><b>Applicant Name</b> : "
							+ tmp.getName() + "</div>";
					meritTable += "<div  class=\"successResultMessage\">"
							+ "&#2437;&#2477;&#2495;&#2472;&#2472;&#2509;&#2470;&#2472;! &#2438;&#2474;&#2472;&#2495; &#2478;&#2494;&#2439;&#2455;&#2509;&#2480;&#2503;&#2486;&#2472;&#2503;&#2480; &#2478;&#2494;&#2471;&#2509;&#2479;&#2478;&#2503; &#2472;&#2495;&#2478;&#2509;&#2472;&#2503;&#2480; &#2453;&#2482;&#2503;&#2460;&#2503; &#2477;&#2480;&#2509;&#2468;&#2495;&#2480; &#2460;&#2472;&#2509;&#2479; &#2478;&#2472;&#2507;&#2472;&#2496;&#2468; &#2489;&#2527;&#2503;&#2459;&#2503;&#2472;";
					meritTable += "</div><table class=\"CollegeDetailsTable\" id=\"selection_row_table\" style=\"width: 828px\">";

					meritTable += "<tr>"
							+ "<th width=\"40%\" >College Name</td>"
							+ "  <th width=\"10%\" >EIIN</td>"
							+ " <th width=\"10%\">Shift</td>"
							+ "<th width=\"10%\">Version</td>"
							+ "<th width=\"20%\">Group</td>"
							+ "<th width=\"10%\">Quota</td>" + "</tr>";
					meritTable += "<tr><td align=\"left\">"
							+ tmp.getCOLLEGE_NAME() + "<td align=\"center\">"
							+ tmp.getEIIN() + "<td align=\"center\">" + shift
							+ "<td align=\"center\">" + version
							+ "<td align=\"center\">" + tmp.getGROUP_NAME()
							+ "<td align=\"left\">" + tmp.getQUOTA_TYPE()
							+ "<tr>";

					meritTable += "</table></center>";
					meritTable += "</div></div></div>";
					try {
						response.setContentType("text/html");
						response.getWriter().write(meritTable);
					} catch (Exception e) {
					}

				}
			}

			// else if((tmp.getAppliedsms().equalsIgnoreCase("Y") ||
			// tmp.getAppliedweb().equalsIgnoreCase("Y")) &&
			// (tmp.getMerit_type()==null ||
			// tmp.getMerit_type().equalsIgnoreCase("")))
			// {
			// // applied but no chance
			// meritTable +=
			// "<div class=\"box_container\" style=\"overflow: hidden; position: relative; height: 100%; margin-top: 8px;\" id=\"personal_result_div\"><div class=\"box_header\">Your Result</div><div class=\"box_body\" style=\"padding: 12px;\"><div class=\"successMessageDiv red\" id=\"finalResultDiv\">";
			// meritTable +=
			// "<div class=\"applicantDetails\"><b>Applicant Name</b> : "+tmp.getName()+"</div>";
			// meritTable += "<div  class=\"successResultMessage red\">" +
			// "&#2470;&#2497;&#2435;&#2454;&#2495;&#2468;, &#2468;&#2497;&#2478;&#2495; &#2453;&#2507;&#2472; &#2453;&#2482;&#2503;&#2460;&#2503; &#2477;&#2480;&#2509;&#2468;&#2495;&#2480; &#2460;&#2472;&#2509;&#2479; &#2478;&#2472;&#2507;&#2472;&#2496;&#2468; &#2489;&#2451; &#2472;&#2495;&#2404;</div>";
			// meritTable += "<div  class=\"successResultMessage smallSize\">" +
			// "&#2474;&#2480;&#2476;&#2480;&#2509;&#2468;&#2496;&#2468;&#2503; &#2476;&#2495;&#2477;&#2495;&#2472;&#2509;&#2472; &#2474;&#2509;&#2480;&#2468;&#2495;&#2488;&#2509;&#2464;&#2494;&#2472;&#2503; &#2438;&#2488;&#2472; &#2454;&#2494;&#2482;&#2495; &#2469;&#2494;&#2453;&#2494; &#2488;&#2494;&#2474;&#2503;&#2453;&#2509;&#2487;&#2503; &#2437;&#2472;-&#2482;&#2494;&#2439;&#2472;&#2503; &#2477;&#2480;&#2509;&#2468;&#2495;&#2480; &#2476;&#2509;&#2479;&#2476;&#2488;&#2509;&#2469;&#2494; &#2453;&#2480;&#2494; &#2489;&#2476;&#2503;&#2404;</div>";
			// //meritTable +=
			// "<input type=\"button\" class=\"myButton green\" id=\"clearButton\" value=\"Refresh This Page\" onclick=\"clearButton()\"/>";
			// meritTable += "</div></div></div>";
			// try {
			// response.setContentType("text/html");
			// response.getWriter().write(meritTable);
			// } catch (Exception e) {
			// }
			// return null;
			// }
			// else if(tmp.getStatus().equalsIgnoreCase("MIGRATED"))
			// {
			// // applied and chance in first merit + migration applied +
			// migration chance
			// String shift="";
			// if(tmp.getSHIFT_ID().equalsIgnoreCase("1"))shift="Morning";
			// else if(tmp.getSHIFT_ID().equalsIgnoreCase("2"))shift="Day";
			// else if(tmp.getSHIFT_ID().equalsIgnoreCase("3"))shift="Evening";
			// else if(tmp.getSHIFT_ID().equalsIgnoreCase("11"))shift="Morning";
			// else if(tmp.getSHIFT_ID().equalsIgnoreCase("12"))shift="Day";
			// String version="";
			// if(tmp.getVERSION_ID().equalsIgnoreCase("1"))version="Bangla";
			// else
			// if(tmp.getVERSION_ID().equalsIgnoreCase("2"))version="English";
			//
			// meritTable +=
			// "<div class=\"box_container\" style=\"overflow: hidden; position: relative; height: 100%; margin-top: 8px;\" id=\"personal_result_div\"><div class=\"box_header\">Your Result</div><div class=\"box_body\" style=\"padding: 12px;\"><div class=\"successMessageDiv\" id=\"finalResultDiv\">";
			// meritTable +=
			// "<center><div class=\"applicantDetails\"><b>Applicant Name</b> : "+tmp.getName()+"</div>";
			// meritTable += "<div  class=\"successResultMessage\">" +
			// "&#2437;&#2477;&#2495;&#2472;&#2472;&#2509;&#2470;&#2472;! &#2468;&#2497;&#2478;&#2495; &#2478;&#2494;&#2439;&#2455;&#2509;&#2480;&#2503;&#2486;&#2472;&#2503;&#2480; &#2478;&#2494;&#2471;&#2509;&#2479;&#2478;&#2503; &#2472;&#2495;&#2478;&#2509;&#2472;&#2503;&#2480; &#2453;&#2482;&#2503;&#2460;&#2503; &#2477;&#2480;&#2509;&#2468;&#2495;&#2480; &#2460;&#2472;&#2509;&#2479; &#2478;&#2472;&#2507;&#2472;&#2496;&#2468; &#2489;&#2527;&#2503;&#2459;&#2404;";
			// meritTable +=
			// "</div><table class=\"CollegeDetailsTable\" id=\"selection_row_table\" style=\"width: 828px\">";
			//
			//
			//
			//
			// meritTable += "<tr>" +
			// "<th width=\"40%\" >College Name</td>" +
			// "  <th width=\"10%\" >EIIN</td>" +
			// " <th width=\"10%\">Shift</td>" +
			// "<th width=\"10%\">Version</td>" +
			// "<th width=\"20%\">Group</td>" +
			// "<th width=\"10%\">Quota</td>" +
			// "</tr>";
			// meritTable +=
			// "<tr><td align=\"left\">"+tmp.getCOLLEGE_NAME()+"<td align=\"center\">"+tmp.getEIIN()
			// +"<td align=\"center\">"+shift+"<td align=\"center\">"+version+"<td align=\"center\">"+tmp.getGROUP_NAME()
			// +"<td align=\"left\">"+tmp.getQUOTA_TYPE()+"<tr>";
			//
			//
			// meritTable += "</table></center>";
			// meritTable += "</div></div></div>";
			// try {
			// response.setContentType("text/html");
			// response.getWriter().write(meritTable);
			// } catch (Exception e) {
			// }
			// }
			// else if((tmp.getMerit_type()!=null ||
			// !tmp.getMerit_type().equalsIgnoreCase("")) &&
			// !tmp.getStatus().equalsIgnoreCase("MIGRATED"))
			// //tmp.getStatus().equalsIgnoreCase("NEW SELECTION"))
			// {
			// // applied and chance in 2nd merit
			// String shift="";
			// if(tmp.getSHIFT_ID().equalsIgnoreCase("1"))shift="Morning";
			// else if(tmp.getSHIFT_ID().equalsIgnoreCase("2"))shift="Day";
			// else if(tmp.getSHIFT_ID().equalsIgnoreCase("3"))shift="Evening";
			// else if(tmp.getSHIFT_ID().equalsIgnoreCase("11"))shift="Morning";
			// else if(tmp.getSHIFT_ID().equalsIgnoreCase("12"))shift="Day";
			// String version="";
			// if(tmp.getVERSION_ID().equalsIgnoreCase("1"))version="Bangla";
			// else
			// if(tmp.getVERSION_ID().equalsIgnoreCase("2"))version="English";
			//
			// meritTable +=
			// "<div class=\"box_container\" style=\"overflow: hidden; position: relative; height: 100%; margin-top: 8px;\" id=\"personal_result_div\"><div class=\"box_header\">Your Result</div><div class=\"box_body\" style=\"padding: 12px;\"><div class=\"successMessageDiv\" id=\"finalResultDiv\">";
			// meritTable +=
			// "<center><div class=\"applicantDetails\"><b>Applicant Name</b> : "+tmp.getName()+"</div>";
			// meritTable += "<div  class=\"successResultMessage\">" +
			// "&#2437;&#2477;&#2495;&#2472;&#2472;&#2509;&#2470;&#2472;! &#2468;&#2497;&#2478;&#2495; &#2472;&#2495;&#2478;&#2509;&#2472;&#2503;&#2480; &#2453;&#2482;&#2503;&#2460;&#2503; &#2477;&#2480;&#2509;&#2468;&#2495;&#2480; &#2460;&#2472;&#2509;&#2479; &#2478;&#2472;&#2507;&#2472;&#2496;&#2468; &#2489;&#2527;&#2503;&#2459;&#2404;";
			// meritTable +=
			// "</div><table class=\"CollegeDetailsTable\" id=\"selection_row_table\" style=\"width: 828px\">";
			//
			//
			//
			//
			// meritTable += "<tr>" +
			// "<th width=\"40%\" >College Name</td>" +
			// "  <th width=\"10%\" >EIIN</td>" +
			// " <th width=\"10%\">Shift</td>" +
			// "<th width=\"10%\">Version</td>" +
			// "<th width=\"20%\">Group</td>" +
			// "<th width=\"10%\">Quota</td>" +
			// "</tr>";
			// meritTable +=
			// "<tr><td align=\"left\">"+tmp.getCOLLEGE_NAME()+"<td align=\"center\">"+tmp.getEIIN()
			// +"<td align=\"center\">"+shift+"<td align=\"center\">"+version+"<td align=\"center\">"+tmp.getGROUP_NAME()
			// +"<td align=\"left\">"+tmp.getQUOTA_TYPE()+"<tr>";
			//
			//
			// meritTable += "</table></center>";
			// meritTable += "</div></div></div>";
			// try {
			// response.setContentType("text/html");
			// response.getWriter().write(meritTable);
			// } catch (Exception e) {
			// }
			// }
			return null;
		} else {
			meritTable += "<div class=\"box_container\" style=\"overflow: hidden; position: relative; height: 100%; margin-top: 8px;\" id=\"personal_result_div\"><div class=\"box_body\" style=\"padding: 12px;\"><div class=\"successMessageDiv red\" id=\"finalResultDiv\">";
			meritTable += "<div  class=\"successResultMessage red\">"
					+ "&#2470;&#2497;&#2435;&#2454;&#2495;&#2468;, &#2438;&#2474;&#2472;&#2495; &#2477;&#2480;&#2509;&#2468;&#2495;&#2480; &#2460;&#2472;&#2509;&#2479; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2495;&#2468; &#2489;&#2472;&#2472;&#2495;</div>";
			// meritTable += "<div  class=\"successResultMessage smallSize\">" +
			// "&#2470;&#2497;&#2435;&#2454;&#2495;&#2468;, &#2468;&#2497;&#2478;&#2495; &#2453;&#2507;&#2472; &#2474;&#2509;&#2480;&#2468;&#2495;&#2488;&#2509;&#2464;&#2494;&#2472;&#2503; &#2472;&#2495;&#2480;&#2509;&#2476;&#2494;&#2458;&#2495;&#2468; &#2489;&#2451;&#2472;&#2495;&#2404; &#2474;&#2480;&#2476;&#2480;&#2509;&#2468;&#2496;&#2468;&#2503; &#2476;&#2495;&#2477;&#2495;&#2472;&#2509;&#2472; &#2474;&#2509;&#2480;&#2468;&#2495;&#2488;&#2509;&#2464;&#2494;&#2472;&#2503; &#2438;&#2488;&#2472; &#2454;&#2494;&#2482;&#2495; &#2469;&#2494;&#2453;&#2494; &#2488;&#2494;&#2474;&#2503;&#2453;&#2509;&#2487;&#2503; &#2437;&#2472;-&#2482;&#2494;&#2439;&#2472;&#2503; &#2477;&#2480;&#2509;&#2468;&#2495;&#2480; &#2476;&#2509;&#2479;&#2476;&#2488;&#2509;&#2469;&#2494; &#2453;&#2480;&#2494; &#2489;&#2476;&#2503;&#2404;</div>";
			// meritTable +=
			// "<input type=\"button\" class=\"myButton green\" id=\"clearButton\" value=\"Refresh This Page\" onclick=\"clearButton()\"/>";
			meritTable += "</div></div></div>";
			try {
				response.setContentType("text/html");
				response.getWriter().write(meritTable);
			} catch (Exception e) {
			}
			return null;
		}
	}

	public String getResult_Final() {

		String meritTable = " ";
		String checkSum = (String) session.get("CorrectAnswer");
		if (!user_captcha.equalsIgnoreCase(checkSum)) {
			try {
				meritTable += "<div class=\"box_container\" style=\"overflow: hidden; position: relative; height: 100%; margin-top: 8px;\" id=\"personal_result_div\"><div class=\"box_body\" style=\"padding: 12px;\"><div class=\"successMessageDiv red\" id=\"finalResultDiv\">";
				meritTable += "<div  class=\"successResultMessage red\">"
						+ "&#2438;&#2474;&#2472;&#2494;&#2480; &#2470;&#2503;&#2451;&#2527;&#2494; &#2477;&#2503;&#2480;&#2495;&#2475;&#2495;&#2453;&#2503;&#2486;&#2472; &#2453;&#2507;&#2465; &#2463;&#2495; &#2488;&#2464;&#2495;&#2453; &#2472;&#2527;&#2404;<br/><br/>The verification code you have provided is not correct.</div>";
				// meritTable +=
				// "<input type=\"button\" class=\"myButton green\" id=\"clearButton\" value=\"Refresh This Page\" onclick=\"clearButton()\"/>";
				meritTable += "</div></div></div>";
				try {
					response.setContentType("text/html");
					response.getWriter().write(meritTable);
				} catch (Exception e) {
				}
				return null;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		((SessionMap<String, Object>) session).invalidate();
		double startTime = System.nanoTime();
		Result tmp = null;
		try {
			String resultlocation = "none";
			double startTime1 = System.nanoTime();
			// tmp = getResultFromRMI(ssc_roll.trim(), ssc_board.trim(),
			// ssc_year.trim(), ssc_reg.trim());
			resultlocation = "memory";
			if (tmp == null) {
				tmp = ApplicationDAO.getResultByApplicant_Final(
						ssc_roll.trim(), ssc_board.trim(), ssc_year.trim(),
						ssc_reg.trim());
				resultlocation = "database";
			}
			if (tmp == null)
				resultlocation = "none";
			double endTime1 = System.nanoTime();
			System.out.println("Duration1 : " + resultlocation + " : "
					+ (endTime1 - startTime1) / 1000000);

			String xForward = ServletActionContext.getRequest().getHeader(
					"X-Forwarded-For");
			String via = ServletActionContext.getRequest().getHeader("Via");
			String remoteAddress = ServletActionContext.getRequest()
					.getRemoteAddr();

			ResultLog rl = new ResultLog();
			rl.setXForward(xForward);
			rl.setVia(via);
			rl.setRemoteAddress(remoteAddress);
			rl.setRoll(ssc_roll);
			rl.setBoard(ssc_board);
			rl.setPyear(ssc_year);
			rl.setReg(ssc_reg);
			rl.setRtime((endTime1 - startTime1) / 1000000);
			rl.setResultlocation(resultlocation);
			rl.setOp("fourth");
			Thread thread = new Thread(rl);
			thread.start();

		} catch (Exception e) {
			// TODO: handle exception
		}

		if (tmp != null) {
			String shift = "";
			if (tmp.getSHIFT_ID().equalsIgnoreCase("1"))
				shift = "Morning";
			else if (tmp.getSHIFT_ID().equalsIgnoreCase("2"))
				shift = "Day";
			else if (tmp.getSHIFT_ID().equalsIgnoreCase("3"))
				shift = "Evening";
			else if (tmp.getSHIFT_ID().equalsIgnoreCase("11"))
				shift = "Morning";
			else if (tmp.getSHIFT_ID().equalsIgnoreCase("12"))
				shift = "Day";
			String version = "";
			if (tmp.getVERSION_ID().equalsIgnoreCase("1"))
				version = "Bangla";
			else if (tmp.getVERSION_ID().equalsIgnoreCase("2"))
				version = "English";

			meritTable += "<div class=\"box_container\" style=\"overflow: hidden; position: relative; height: 100%; margin-top: 8px;\" id=\"personal_result_div\"><div class=\"box_header\">Your Result</div><div class=\"box_body\" style=\"padding: 12px;\"><div class=\"successMessageDiv\" id=\"finalResultDiv\">";
			meritTable += "<center><div class=\"applicantDetails\"><b>Applicant Name</b> : "
					+ tmp.getName() + "</div>";
			meritTable += "<div  class=\"successResultMessage\">"
					+ "&#2468;&#2507;&#2478;&#2494;&#2480; &#2472;&#2495;&#2478;&#2509;&#2472;&#2503;&#2480; &#2453;&#2482;&#2503;&#2460;&#2503; &#2472;&#2495;&#2486;&#2509;&#2458;&#2494;&#2527;&#2472; &#2489;&#2527;&#2503;&#2459;&#2404;";
			meritTable += "</div><table class=\"CollegeDetailsTable\" id=\"selection_row_table\" style=\"width: 828px\">";

			meritTable += "<tr>" + "<th width=\"40%\" >College Name</td>"
					+ "  <th width=\"10%\" >EIIN</td>"
					+ " <th width=\"10%\">Shift</td>"
					+ "<th width=\"10%\">Version</td>"
					+ "<th width=\"20%\">Group</td>"
					+ "<th width=\"10%\">Quota</td>" + "</tr>";
			meritTable += "<tr><td align=\"left\">" + tmp.getCOLLEGE_NAME()
					+ "<td align=\"center\">" + tmp.getEIIN()
					+ "<td align=\"center\">" + shift + "<td align=\"center\">"
					+ version + "<td align=\"center\">" + tmp.getGROUP_NAME()
					+ "<td align=\"left\">" + tmp.getQUOTA_TYPE() + "<tr>";

			meritTable += "</table></center>";
			meritTable += "</div></div></div>";
			try {
				response.setContentType("text/html");
				response.getWriter().write(meritTable);
			} catch (Exception e) {
			}
			return null;
		} else {
			meritTable += "<div class=\"box_container\" style=\"overflow: hidden; position: relative; height: 100%; margin-top: 8px;\" id=\"personal_result_div\"><div class=\"box_body\" style=\"padding: 12px;\"><div class=\"successMessageDiv red\" id=\"finalResultDiv\">";
			meritTable += "<div  class=\"successResultMessage red\">"
					+ "&#2468;&#2507;&#2478;&#2494;&#2480; &#2453;&#2507;&#2472; &#2453;&#2482;&#2503;&#2460;&#2503; &#2472;&#2495;&#2486;&#2509;&#2458;&#2494;&#2527;&#2472; &#2472;&#2494;&#2439;</div>";
			meritTable += "</div></div></div>";
			try {
				response.setContentType("text/html");
				response.getWriter().write(meritTable);
			} catch (Exception e) {
			}
			return null;
		}
	}

	public String getResult1() {
		double startTime = System.nanoTime();
		String checkSum = (String) session.get("finalSum");
		if (!user_captcha.equalsIgnoreCase(checkSum)) {
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
		if (user_captcha.equalsIgnoreCase(checkSum)) {
			((SessionMap<String, Object>) session).invalidate();
			Result tmp = null;
			try {
				double startTime1 = System.nanoTime();
				tmp = getResultFromRMI(ssc_roll, ssc_board, ssc_year, ssc_reg);
				double endTime1 = System.nanoTime();
				// System.out.println("Duration1 : "+(endTime1 -
				// startTime1)/1000000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			String meritTable = "";

			if (tmp != null)
				// for(int i=0;i<tmp.getEIIN().size();i++)
				// {
				// String shift="";
				// if(tmp.getSHIFT_ID().get(i).equalsIgnoreCase("1"))shift="Morning";
				// else
				// if(tmp.getSHIFT_ID().get(i).equalsIgnoreCase("2"))shift="Day";
				// else
				// if(tmp.getSHIFT_ID().get(i).equalsIgnoreCase("3"))shift="Evening";
				// String version="";
				// if(tmp.getVERSION_ID().get(i).equalsIgnoreCase("1"))version="Bangla";
				// else
				// if(tmp.getVERSION_ID().get(i).equalsIgnoreCase("2"))version="English";
				// meritTable +=
				// "<tr><td align=\"left\">"+tmp.getCOLLEGE_NAME().get(i)+"<td align=\"center\">"+tmp.getEIIN().get(i)
				// +"<td align=\"center\">"+shift+"<td align=\"center\">"+version+"<td align=\"left\">"+tmp.getGROUP_NAME().get(i)
				// +"<td align=\"left\">"+tmp.getQUOTA_TYPE()+"<tr>";
				// }
				for (int i = 0; i < tmp.getEIIN().split("##").length; i++) {
					String shift = "";
					if (tmp.getSHIFT_ID().split("##")[i].equalsIgnoreCase("1"))
						shift = "Morning";
					else if (tmp.getSHIFT_ID().split("##")[i]
							.equalsIgnoreCase("2"))
						shift = "Day";
					else if (tmp.getSHIFT_ID().split("##")[i]
							.equalsIgnoreCase("3"))
						shift = "Evening";
					String version = "";
					if (tmp.getVERSION_ID().split("##")[i]
							.equalsIgnoreCase("1"))
						version = "Bangla";
					else if (tmp.getVERSION_ID().split("##")[i]
							.equalsIgnoreCase("2"))
						version = "English";
					meritTable += "<tr><td align=\"left\">"
							+ tmp.getCOLLEGE_NAME().split("##")[i]
							+ "<td align=\"center\">"
							+ tmp.getEIIN().split("##")[i]
							+ "<td align=\"center\">" + shift
							+ "<td align=\"center\">" + version
							+ "<td align=\"left\">"
							+ tmp.getGROUP_NAME().split("##")[i]
							+ "<td align=\"left\">" + tmp.getQUOTA_TYPE()
							+ "<tr>";
				}
			request.setAttribute("meritTable", meritTable);
			double endTime = System.nanoTime();
			// System.out.println("Duration : "+(endTime - startTime)/1000000);
			return SUCCESS;
		} else
			return null;
	}

	static Registry registry10 = null;
	static Registry registry11 = null;
	static Registry registry12 = null;
	static Registry registry13 = null;
	static Registry registry14 = null;
	static Registry registry15 = null;
	static Registry registry16 = null;
	static Registry registry17 = null;
	static Registry registry18 = null;
	static Registry registry19 = null;
	static Registry registry20 = null;
	static Hello stub10 = null;
	static Hello stub11 = null;
	static Hello stub12 = null;
	static Hello stub13 = null;
	static Hello stub14 = null;
	static Hello stub15 = null;
	static Hello stub16 = null;
	static Hello stub17 = null;
	static Hello stub18 = null;
	static Hello stub19 = null;
	static Hello stub20 = null;
	static {
		try {
			registry10 = LocateRegistry.getRegistry("127.0.0.1", 2010);
			registry11 = LocateRegistry.getRegistry("127.0.0.1", 2011);
			registry12 = LocateRegistry.getRegistry("127.0.0.1", 2012);
			registry13 = LocateRegistry.getRegistry("127.0.0.1", 2013);
			registry14 = LocateRegistry.getRegistry("127.0.0.1", 2014);
			registry15 = LocateRegistry.getRegistry("127.0.0.1", 2015);
			registry16 = LocateRegistry.getRegistry("127.0.0.1", 2016);
			registry17 = LocateRegistry.getRegistry("127.0.0.1", 2017);
			registry18 = LocateRegistry.getRegistry("127.0.0.1", 2018);
			registry19 = LocateRegistry.getRegistry("127.0.0.1", 2019);
			registry20 = LocateRegistry.getRegistry("127.0.0.1", 2020);
			stub10 = (Hello) registry10.lookup("Hello");
			stub11 = (Hello) registry11.lookup("Hello");
			stub12 = (Hello) registry12.lookup("Hello");
			stub13 = (Hello) registry13.lookup("Hello");
			stub14 = (Hello) registry14.lookup("Hello");
			stub15 = (Hello) registry15.lookup("Hello");
			stub16 = (Hello) registry16.lookup("Hello");
			stub17 = (Hello) registry17.lookup("Hello");
			stub19 = (Hello) registry19.lookup("Hello");
			stub20 = (Hello) registry20.lookup("Hello");
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public synchronized static Result getResultFromRMI(String roll,
			String board, String year, String reg) {
		Result tmp = null;
		try {
			if (board.equalsIgnoreCase("10"))
				try {
					tmp = stub10.getResult(roll + board + year + reg);
				} catch (Exception e1) {
					try {
						registry10 = LocateRegistry.getRegistry("127.0.0.1",
								2010);
						stub10 = (Hello) registry10.lookup("Hello");
						tmp = stub10.getResult(roll + board + year + reg);
					} catch (Exception e2) {
						// e2.printStackTrace();
					}
				}
			else if (board.equalsIgnoreCase("11"))
				try {
					tmp = stub11.getResult(roll + board + year + reg);
				} catch (Exception e1) {
					try {
						registry11 = LocateRegistry.getRegistry("127.0.0.1",
								2011);
						stub11 = (Hello) registry11.lookup("Hello");
						tmp = stub11.getResult(roll + board + year + reg);
					} catch (Exception e2) {
						// e2.printStackTrace();
					}
				}
			else if (board.equalsIgnoreCase("12"))
				try {
					tmp = stub12.getResult(roll + board + year + reg);
				} catch (Exception e1) {
					try {
						registry12 = LocateRegistry.getRegistry("127.0.0.1",
								2012);
						stub12 = (Hello) registry12.lookup("Hello");
						tmp = stub12.getResult(roll + board + year + reg);
					} catch (Exception e2) {
						// e2.printStackTrace();
					}
				}
			else if (board.equalsIgnoreCase("13"))
				try {
					tmp = stub13.getResult(roll + board + year + reg);
				} catch (Exception e1) {
					try {
						registry13 = LocateRegistry.getRegistry("127.0.0.1",
								2013);
						stub13 = (Hello) registry13.lookup("Hello");
						tmp = stub13.getResult(roll + board + year + reg);
					} catch (Exception e2) {
						// e2.printStackTrace();
					}
				}
			else if (board.equalsIgnoreCase("14"))
				try {
					tmp = stub14.getResult(roll + board + year + reg);
				} catch (Exception e1) {
					try {
						registry14 = LocateRegistry.getRegistry("127.0.0.1",
								2014);
						stub14 = (Hello) registry14.lookup("Hello");
						tmp = stub14.getResult(roll + board + year + reg);
					} catch (Exception e2) {
						// e2.printStackTrace();
					}
				}
			else if (board.equalsIgnoreCase("15"))
				try {
					tmp = stub15.getResult(roll + board + year + reg);
				} catch (Exception e1) {
					try {
						registry15 = LocateRegistry.getRegistry("127.0.0.1",
								2015);
						stub15 = (Hello) registry15.lookup("Hello");
						tmp = stub15.getResult(roll + board + year + reg);
					} catch (Exception e2) {
						// e2.printStackTrace();
					}
				}
			else if (board.equalsIgnoreCase("16"))
				try {
					tmp = stub16.getResult(roll + board + year + reg);
				} catch (Exception e1) {
					try {
						registry16 = LocateRegistry.getRegistry("127.0.0.1",
								2016);
						stub16 = (Hello) registry16.lookup("Hello");
						tmp = stub16.getResult(roll + board + year + reg);
					} catch (Exception e2) {
						// e2.printStackTrace();
					}
				}
			else if (board.equalsIgnoreCase("17"))
				try {
					tmp = stub17.getResult(roll + board + year + reg);
				} catch (Exception e1) {
					try {
						registry17 = LocateRegistry.getRegistry("127.0.0.1",
								2017);
						stub17 = (Hello) registry17.lookup("Hello");
						tmp = stub17.getResult(roll + board + year + reg);
					} catch (Exception e2) {
						// e2.printStackTrace();
					}
				}
			else if (board.equalsIgnoreCase("18"))
				try {
					tmp = stub18.getResult(roll + board + year + reg);
				} catch (Exception e1) {
					try {
						registry18 = LocateRegistry.getRegistry("127.0.0.1",
								2018);
						stub18 = (Hello) registry18.lookup("Hello");
						tmp = stub18.getResult(roll + board + year + reg);
					} catch (Exception e2) {
						// e2.printStackTrace();
					}
				}
			else if (board.equalsIgnoreCase("19"))
				try {
					tmp = stub19.getResult(roll + board + year + reg);
				} catch (Exception e1) {
					try {
						registry19 = LocateRegistry.getRegistry("127.0.0.1",
								2019);
						stub19 = (Hello) registry19.lookup("Hello");
						tmp = stub19.getResult(roll + board + year + reg);
					} catch (Exception e2) {
						// e2.printStackTrace();
					}
				}
			else if (board.equalsIgnoreCase("20"))
				try {
					tmp = stub20.getResult(roll + board + year + reg);
				} catch (Exception e1) {
					try {
						registry20 = LocateRegistry.getRegistry("127.0.0.1",
								2020);
						stub20 = (Hello) registry20.lookup("Hello");
						tmp = stub20.getResult(roll + board + year + reg);
					} catch (Exception e2) {
						// e2.printStackTrace();
					}
				}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		// if(tmp!=null)System.out.println("In Memory");
		return tmp;
	}

	public String checkContact() {
		// contactno = "88"+contactno;

		if (session.get("contactno") != null
				&& contactno
						.equalsIgnoreCase((String) session.get("contactno"))) {
			try {
				response.setContentType("text/html");
				response.getWriter().write("yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.setContentType("text/html");
				response.getWriter().write((String) session.get("contactno"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * @author nasir
	 * @return
	 */
	public String applicationInfoCheck_TT() {

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

		if (checkCaptcha) {		
			((SessionMap<String, Object>) session).invalidate();
			session.put("CorrectAnswer", user_captcha);
			return "success";

		} else
			return "invalid_info";

	}

	public String sentSecurityCode() {
		// Raju task
		String checkSum = (String) session.get("CorrectAnswer");
		// if(!tx_captcha.equalsIgnoreCase(checkSum)){
		// try {
		// response.setContentType("text/html");
		// response.getWriter().write("ce");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// return null;
		// }
		SscDataDAO sscDAO = new SscDataDAO();
		sscDAO.sentSecurityCode((String) session.get("ssc_roll"),
				(String) session.get("ssc_board"),
				(String) session.get("ssc_year"),
				(String) session.get("ssc_reg"));
		try {
			response.setContentType("text/html");
			response.getWriter()
					.write("<font color=\"green\">Security code sent to your mobile. Please check your mobile number.</font>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String applicationInfoCheckTx_TT() {
		// Raju task
		String resultType = "";
		String checkSum = (String) session.get("CorrectAnswer");
		if (!tx_captcha.equalsIgnoreCase(checkSum)) {
			try {
				response.setContentType("text/html");
				response.getWriter().write("ce");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		if (!organization.equalsIgnoreCase("abcd"))
			return "wait";
		if (tx_captcha.equalsIgnoreCase(checkSum)) {
			// ///////////////////// get TT information
			// ////////////////////////////////////////
			if (organization.equalsIgnoreCase("TT")) {
				try {
					String generatedJSONString = "{\"apptype\" :\"web\",\"trxid\" : \""
							+ trxid + "\",\"organization\" : \"TT\"}";
					String myUrlgoeshere = "http://103.230.104.217:9999/CAD/buet2TTcheck.php";
					HttpClient httpClient = new DefaultHttpClient();
					HttpPost postRequest = new HttpPost(myUrlgoeshere);
					StringEntity input = new StringEntity(generatedJSONString);
					input.setContentType("application/json;charset=UTF-8");
					postRequest.setEntity(input);
					input.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
							"application/json;charset=UTF-8"));
					postRequest.setHeader("Accept", "application/json");
					postRequest.setEntity(input);
					HttpResponse response = httpClient.execute(postRequest);
					BufferedReader br = new BufferedReader(
							new InputStreamReader(
									(response.getEntity().getContent())));
					String output;
					String responseString = "";
					while ((output = br.readLine()) != null) {
						responseString = output;
					}
					httpClient.getConnectionManager().shutdown();
					JSONObject myResponse = new JSONObject(
							responseString.toString());
					System.out.println(myResponse.getString("status")
							.toLowerCase());
					if (myResponse.getString("status").toLowerCase()
							.contains(("yes"))) {
						ResponseDTO applicant = new ResponseDTO();
						if (!ssc_roll.equalsIgnoreCase(myResponse.getString(
								"roll").replaceAll("-", ""))
								|| !ssc_reg.equalsIgnoreCase(myResponse
										.getString("reg").replaceAll("-", ""))
								|| !ssc_year.equalsIgnoreCase(myResponse
										.getString("year"))) {
							return "trxidOthers";
						}
						if (!myResponse.getString("apptype").equalsIgnoreCase(
								"web")) {
							// joy

							/*
							 * ssc_roll=myResponse.getString("roll");
							 * ssc_roll=myResponse
							 * .getString("roll").replaceAll("-", "");
							 * 
							 * //ssc_board =
							 * boardHT.get(myResponse.getString("ssc_board"));
							 * ssc_year=myResponse.getString("year"); String
							 * ssc_regno
							 * =myResponse.getString("reg").replaceAll("-", "");
							 * String trackid=myResponse.getString("trxid");
							 * String
							 * contact_no=myResponse.getString("contactno");
							 * String quota=myResponse.getString("quota");
							 * eiin=myResponse.getString("eiin"); String
							 * shift=myResponse.getString("shift"); String
							 * medium=myResponse.getString("medium"); String
							 * group=myResponse.getString("group"); String
							 * pin=myResponse.getString("pin"); String
							 * paytime=myResponse.getString("paytime"); String
							 * paymobile=myResponse.getString("paymobile");
							 * 
							 * ApplicationDAO adao = new ApplicationDAO(); int
							 * code
							 * =adao.InsertSMSapplication_2018(ssc_roll,ssc_board
							 * ,ssc_year,ssc_regno,
							 * trackid,contact_no,quota,eiin
							 * ,shift,medium,group,pin,paytime,paymobile );
							 */
							return "wait";
						}
						applicant.setRoll(myResponse.getString("roll"));
						applicant.setReg(myResponse.getString("reg"));
						applicant.setYear(myResponse.getString("year"));
						applicant.setBoard(ssc_board);
						applicant.setContactno(myResponse
								.getString("contactno"));
						applicant.setTrxid(myResponse.getString("trxid"));
						applicant.setPaytime(myResponse.getString("paytime"));
						applicant.setPaymobile(myResponse
								.getString("paymobile"));
						applicant.setRoll(myResponse.getString("roll"));
						applicant.setOrganization(myResponse
								.getString("organization"));
						ApplicationDAO adao = new ApplicationDAO();
						ResponseDTO rspDTO = adao.makeWebPayment(applicant,
								getSecurityCode());
						if (rspDTO.getStatus().equalsIgnoreCase("OK"))
							return "wait";
						else {
							request.setAttribute("asdasdasd",
									rspDTO.getMessage());
							return "error";
						}
					} else {
						return "notwait";
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (organization.equalsIgnoreCase("SC")) {
				try {

					CadService cadService = new CadService(
							"https://pps.surecashbd.com/", "cad",
							"cad@SureCash");
					CadPaymentVerifyReq cadPaymentVerifyReq = CadPaymentVerifyReq
							.builder().txnId(trxid).organization("SC")
							.appType("web").build();
					CadPaymentVerifyResp cadPaymentVerifyResp = cadService
							.verifyPayment(cadPaymentVerifyReq);

					System.out.println(cadPaymentVerifyResp.getStatus());
					if (cadPaymentVerifyResp.getStatus()
							.equalsIgnoreCase("yes")) {
						Hashtable<String, String> boardHT = (Hashtable<String, String>) ServletActionContext
								.getServletContext().getAttribute("BoardTable");
						if (ssc_board.length() > 2)
							ssc_board = boardHT.get(cadPaymentVerifyResp
									.getBoard().toLowerCase());

						ssc_reg = ApplicationDAO.getREGNO(
								cadPaymentVerifyResp.getRoll(), ssc_board,
								cadPaymentVerifyResp.getYear());
						ResponseDTO applicant = new ResponseDTO();
						if (!ssc_roll.equalsIgnoreCase(cadPaymentVerifyResp
								.getRoll())
								|| !ssc_year
										.equalsIgnoreCase(cadPaymentVerifyResp
												.getYear())
								|| !ssc_board.equalsIgnoreCase(boardHT
										.get(cadPaymentVerifyResp.getBoard()
												.toLowerCase()))) {
							return "trxidOthers";
						}
						if (!cadPaymentVerifyResp.getAppType()
								.equalsIgnoreCase("web")) {
							return "notwait";
						}
						applicant.setRoll(cadPaymentVerifyResp.getRoll());
						applicant.setReg(ssc_reg);
						applicant.setYear(cadPaymentVerifyResp.getYear());
						applicant.setBoard(ssc_board);
						applicant.setContactno(cadPaymentVerifyResp
								.getContactNo());
						applicant.setTrxid(cadPaymentVerifyResp.getTxnId());
						applicant.setPaytime(cadPaymentVerifyResp.getPayTime());
						applicant.setPaymobile(cadPaymentVerifyResp
								.getPayMobile());
						applicant.setRoll(cadPaymentVerifyResp.getRoll());
						applicant.setOrganization(cadPaymentVerifyResp
								.getOrganization());
						ApplicationDAO adao = new ApplicationDAO();
						ResponseDTO rspDTO = adao.makeWebPayment(applicant,
								getSecurityCode());
						if (rspDTO.getStatus().equalsIgnoreCase("OK"))
							return "wait";
						else {
							request.setAttribute("asdasdasd",
									rspDTO.getMessage());
							return "error";
						}
					} else {
						return "notwait";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (organization.equalsIgnoreCase("Rocket")) {
				// HttpGet httpGet = new
				// HttpGet("http://mbsrv.dutchbanglabank.com/BillPayGWT/BillInfoService?shortcode=515&opcode=GT&txnid=492212828");
				// httpGet.setHeader("Content-Type", "application/http");
				// // httpGet.setHeader("Accept", "application/json");
				// httpGet.addHeader("Host", "10.10.200.142");
				// httpGet.addHeader(BasicScheme.authenticate(
				// new UsernamePasswordCredentials("BUETEDUBOARD101",
				// "j874hejduierunaigt"),

				try {
					try {
						URL url = new URL(
								"http://mbsrv.dutchbanglabank.com/BillPayGW/BillInfoService?shortcode=515&userid=BUETEDUBOARD101&password=j874hejduierunaigt&opcode=GT&txnid="
										+ trxid);
						// URL url = new URL
						// ("http://mbsrv.dutchbanglabank.com/BillPayGWT/BillInfoService?shortcode=100&userid=BUET&password=1234&opcode=GT&txnid=23013124");

						String encoding = new String(
								Base64.encodeBase64(("dbill:dBILL!23")
										.getBytes()));
						HttpURLConnection connection = (HttpURLConnection) url
								.openConnection();
						connection.setRequestMethod("GET");
						connection.setDoOutput(true);
						connection.setRequestProperty("host", "10.10.200.142");
						connection.setRequestProperty("Authorization", "Basic "
								+ encoding);
						InputStream content = (InputStream) connection
								.getInputStream();
						BufferedReader in = new BufferedReader(
								new InputStreamReader(content));
						String line;
						String line1 = "";
						while ((line = in.readLine()) != null) {
							line1 = line1 + line;
						}
						String[] parts = line1.split("\\|");
						System.out.println(line1);
						ResponseDTO applicant = new ResponseDTO();
						// ssc_board = parts[1].substring(0,3);
						ssc_year = parts[1].substring(3, 7);
						ssc_roll = parts[1].substring(7);
						if (!(ssc_roll.equalsIgnoreCase(session.get("ssc_roll")
								.toString()) && ssc_year
								.equalsIgnoreCase(session.get("ssc_year")
										.toString())))
							return "invalid_info";
						applicant.setRoll(ssc_roll);
						applicant.setReg(ssc_reg);
						applicant.setYear(ssc_year);
						applicant.setBoard(ssc_board);
						applicant.setContactno(parts[4]);
						applicant.setTrxid(trxid);
						applicant.setPaytime("");
						applicant.setPaymobile("11111111111111111111111");
						applicant.setOrganization("Rocket");
						ApplicationDAO adao = new ApplicationDAO();
						ResponseDTO rspDTO = adao.makeWebPayment(applicant,
								getSecurityCode());
						if (rspDTO.getStatus().equalsIgnoreCase("OK"))
							return "wait";
						else {
							request.setAttribute("asdasdasd",
									rspDTO.getMessage());
							return "error";
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else
				return "invalid_info";

		}

		else
			return "invalid_info";

		return null;
	}

	private String callByHttpGet(String URL, String authorization,
			String paramData, String contentType) throws Exception {
		String requestURL = "https://pps.surecashbd.com/api/cad/verifyPayment";
		// if(paramData.startsWith("?")){
		// paramData=paramData.substring(1);
		// }
		// if(URL.endsWith("?")){
		// requestURL=URL+paramData;
		// } else {
		// requestURL=URL+"?"+paramData;
		// }
		HttpPost request = new HttpPost(requestURL);
		String generatedJSONString = "{\"apptype\" :\"web\",\"trxid\" : \""
				+ trxid + "\",\"organization\" : \"SC\"}";
		StringEntity input = new StringEntity(generatedJSONString);
		input.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
				"application/json;charset=UTF-8"));
		request.setEntity(input);
		request.setHeader("Accept", contentType);
		request.setHeader("Content-Type", contentType);
		if (authorization != null) {
			request.setHeader(
					"Authorization",
					"Basic "
							+ Base64.encodeBase64String(authorization
									.getBytes()));
		}
		// LoggerUtils.printLog(LOG_HEADER, "--> REQ:",
		// request.getURI().toString());
		HttpResponse response = null;
		String strResponse = null;
		try {
			HttpClient client = new DefaultHttpClient();
			response = client.execute(request);
			strResponse = (response.getEntity() != null) ? EntityUtils
					.toString(response.getEntity()) : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strResponse;
	}

	public String showPersonalInfo_TT() {

		SscDataDAO sscDAO = new SscDataDAO();
		applicant = sscDAO.getApplicationWId_TT(ssc_roll, ssc_board, ssc_year,
				ssc_reg);
		String is_active = (String) session.get("is_active");

		return SUCCESS; // active when not receive any complain
		// return "complainwithPersonalInfo"; //active when receive any complain

	}

	public String showPersonalInfo_SMS() {

		SscDataDAO sscDAO = new SscDataDAO();
		applicant = sscDAO.getApplicationWId_TT(ssc_roll, ssc_board, ssc_year,
				ssc_reg);
		return SUCCESS;
	}

	public String PersonalInfoForChooseUpdate() {
		String is_active = (String) session.get("is_active");
		if (!is_active.equals("1")) {
			Gson gson = new Gson();
			String json = "";
			json = gson
					.toJson("<div class='msg_div' style='font-size: 24px; text-align: center; color: #EC0F18; background-color: #FDD0D0;	padding: 12px; border: 2px solid #FFCFD0;	border-radius: 5px;	box-shadow: 0px 0px 8px #888888;'>&#2438;&#2474;&#2472;&#2495; &#2438;&#2474;&#2472;&#2494;&#2480; &#2438;&#2476;&#2503;&#2470;&#2472; (&#2474;&#2494;&#2433;&#2458;) &#2476;&#2494;&#2480;&#2503;&#2480; &#2458;&#2503;&#2479;&#2492;&#2503; &#2476;&#2503;&#2486;&#2495; &#2474;&#2480;&#2495;&#2476;&#2480;&#2509;&#2468;&#2472; &#2453;&#2480;&#2468;&#2503; &#2474;&#2494;&#2480;&#2476;&#2503;&#2472; &#2472;&#2494; <br/>You can not Update your application more than 5 (five) times</div>");

			setJsonResponse(json);
			return null;
		}
		String appScode = (String) session.get("scode");
		String no_of_update = (String) session.get("no_of_update");
		String appContactno = (String) session.get("contactno");
		if (appScode.equals(scode) && appContactno.equals(contactno)
				&& no_of_update != null && Integer.parseInt(no_of_update) < 5) {
			SscDataDAO sscDAO = new SscDataDAO();
			applicant = sscDAO.getApplicationWId_TT(ssc_roll, ssc_board,
					ssc_year, ssc_reg);
			return SUCCESS;
		} else if (no_of_update != null && Integer.parseInt(no_of_update) >= 5) {
			Gson gson = new Gson();
			String json = "";
			json = gson
					.toJson("<div class='msg_div' style='font-size: 24px; text-align: center; color: #EC0F18; background-color: #FDD0D0;	padding: 12px; border: 2px solid #FFCFD0;	border-radius: 5px;	box-shadow: 0px 0px 8px #888888;'>&#2438;&#2474;&#2472;&#2495; &#2438;&#2474;&#2472;&#2494;&#2480; &#2438;&#2476;&#2503;&#2470;&#2472; (&#2474;&#2494;&#2433;&#2458;) &#2476;&#2494;&#2480;&#2503;&#2480; &#2458;&#2503;&#2479;&#2492;&#2503; &#2476;&#2503;&#2486;&#2495; &#2474;&#2480;&#2495;&#2476;&#2480;&#2509;&#2468;&#2472; &#2453;&#2480;&#2468;&#2503; &#2474;&#2494;&#2480;&#2476;&#2503;&#2472; &#2472;&#2494; <br/>You can not Update your application more than 5 (five) times</div>");

			setJsonResponse(json);
			return null;
		} else if (!appScode.equals(scode) || !appContactno.equals(contactno)) {
			Gson gson = new Gson();
			String json = "";
			json = gson
					.toJson("<div class='msg_div' style='font-size: 24px; text-align: center; color: #EC0F18; background-color: #FDD0D0;	padding: 12px; border: 2px solid #FFCFD0;	border-radius: 5px;	box-shadow: 0px 0px 8px #888888;'>Mobile number or security code is not valid<br/> &#2478;&#2507;&#2476;&#2494;&#2439;&#2482; &#2472;&#2478;&#2509;&#2476;&#2480; &#2476;&#2494; security code &#2488;&#2464;&#2495;&#2453; &#2472;&#2479;&#2492;</div>");

			// json =
			// gson.toJson("<div class='msg_div' style='height: 150px;color: blue;'><center> <font size='4' color='red'>Mobile number or security code is not valid</font></center></div>");
			setJsonResponse(json);
			return null;
		} else
			return null;
	}

	public boolean mobile_validation_TT(String confirm_mobile_number) {
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
				|| mobileNumber.substring(0, 3).equalsIgnoreCase("018") || mobileNumber
				.substring(0, 3).equalsIgnoreCase("019")))
			tmp = false;
		return tmp;
	}

	public String editChoiceList_TT() {

		CollegeDAO collegeDAO = new CollegeDAO();
		choice = collegeDAO.getChoiceList_TT(application_id);
		return SUCCESS;
	}

	// added by joy
	public String updateEditChoiceList() {

		CollegeDAO collegeDAO = new CollegeDAO();
		choice = collegeDAO.getChoiceList_TT(application_id);
		String no_of_update = (String) session.get("no_of_update");
		request.setAttribute("no_of_update", no_of_update);
		return SUCCESS;
	}

	public String editQuota_TT() {
		boolean response = false;
		CollegeDAO collegeDAO = new CollegeDAO();
		response = collegeDAO.editQuota(quota_ff, quota_eq, quota_bksp,
				quota_expatriate, application_id);

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
				response.getWriter().write(
						"<input type=\"hidden\" id=\"application_id\" value=\""
								+ applicant.getApplication_id() + "\">");
				response.getWriter().write(
						"<input type=\"hidden\" id=\"merit_type\" value=\""
								+ applicant.getMerit_type() + "\">");
				response.getWriter().write("<table width=\"600px\">");
				response.getWriter()
						.write("<tr><td>Application Id</td><td>Student Name</td><td>EIIN</td><td>Merit</td>");
				response.getWriter().write(
						"<tr><td>" + applicant.getApplication_id()
								+ "</td><td>" + applicant.getStudent_name()
								+ "</td><td>" + applicant.getEiin()
								+ "</td><td>" + applicant.getMerit() + "</td>");
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
		applicant = sscDAO.getApplicationNew(ssc_roll, ssc_board, ssc_year,
				ssc_reg, ssc_mother);
		if (applicant == null)
			return "invalid_info";
		else if (applicant.getApplication_id() != null
				&& !applicant.getApplication_id().equalsIgnoreCase(""))
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
		HashMap<String, ArrayList<CollegeDTO>> collegeMap = (HashMap<String, ArrayList<CollegeDTO>>) ServletActionContext
				.getServletContext().getAttribute("ALL_COLLEGE_MAP");
		HashMap<String, ArrayList<CollegeDTO>> collegeMapDist = (HashMap<String, ArrayList<CollegeDTO>>) ServletActionContext
				.getServletContext().getAttribute("ALL_COLLEGE_MAP_DIST");

		if (college_search_type.equalsIgnoreCase("by_eiin")) {
			HashMap<String, CollegeDTO> cMap = (HashMap<String, CollegeDTO>) ServletActionContext
					.getServletContext().getAttribute("EIIN_COLLEGE_MAP");
			CollegeDTO college = cMap.get(eiin);
			this.district_id = college.getDist_id();
			this.helper_board_id = college.getHelper_board_id();
		}

		/*** This Portion is for BTEB Second time Application Purpose ***/
		// if(!helper_board_id.equalsIgnoreCase("3")){
		// setJsonResponse("{\"colleges\":"+"{}"+",\"dist_id\" :\""+"{}"+"\",\"helper_board_id\":\""+"{}"+"\"}");
		// return null;
		// }
		/*----- End ----*/
		Gson gson;
		String json = null;
		if (college_search_type.equalsIgnoreCase("by_thana")) {
			ArrayList<CollegeDTO> collegeList = collegeMap.get(thana_id
					+ helper_board_id);
			gson = new Gson();
			json = gson.toJson(collegeList);
			json = "{\"colleges\":" + json + ",\"thana_id\" :\"" + thana_id
					+ "\",\"helper_board_id\":\"" + helper_board_id + "\"}";
		}
		if (college_search_type.equalsIgnoreCase("by_district")) {
			ArrayList<CollegeDTO> collegeList = collegeMapDist.get(district_id
					+ helper_board_id);
			gson = new Gson();
			json = gson.toJson(collegeList);
			json = "{\"colleges\":" + json + ",\"district_id\" :\""
					+ district_id + "\",\"helper_board_id\":\""
					+ helper_board_id + "\"}";
		}

		setJsonResponse(json);
		return null;

	}

	/**
	 * @author nasir
	 * @param boardId
	 *            and distId
	 * @return district list with eiin in json format to show on html view of
	 *         svg
	 */
	public String getCollegesForPdf() {
		Gson gson;
		String json = null;
		ApplicationStatBoardDAO collegeinfoDAO = new ApplicationStatBoardDAO();
		List<CollegeInfoDTO> collegeinfoList = collegeinfoDAO
				.getCollegeByDistrict(helper_board_id, district_id);
		gson = new Gson();
		json = gson.toJson(collegeinfoList);
		setJsonResponse(json);
		return null;
	}

	public String getCollegeSVG() {
		System.out.println(" Session Activity " + session.get("ssc_roll"));
		HashMap<String, SVGDTO> svgList = (HashMap<String, SVGDTO>) ServletActionContext
				.getServletContext().getAttribute("SVG_MAP");
		SVGDTO svg = svgList.get(eiin);

		String shift = "\"shift\":" + svg.getShiftList();
		String version = "\"version\":"
				+ svg.getShiftVersionList().toString().replaceAll("=", ":");
		version = version.replaceAll("\\[", "\"");
		version = version.replaceAll("\\]", "\"");
		version = version.replaceAll(":\\{", ":\\{\"");
		version = version.replaceAll(":\"", "\":\"");
		version = version.replaceAll("\", ", "\", \"");

		String group = "\"group\":"
				+ svg.getVersionGroupList().toString().replaceAll("=", ":");
		group = group.replaceAll("\\[", "\"");
		group = group.replaceAll("\\]", "\"");
		group = group.replaceAll(":\\{", ":\\{\"");
		group = group.replaceAll(":\"", "\":\"");
		group = group.replaceAll("\", ", "\", \"");

		String sqYN = "\"special_quota\":"
				+ svg.getGroupEQList().toString().replaceAll("=", ":");
		sqYN = sqYN.replaceAll("\\[", "\"");
		sqYN = sqYN.replaceAll("\\]", "\"");
		sqYN = sqYN.replaceAll(":\\{", ":\\{\"");
		sqYN = sqYN.replaceAll(":\"", "\":\"");
		sqYN = sqYN.replaceAll("\", ", "\", \"");

		String totalSeat = "\"total_seat\":"
				+ svg.getSvgTotalSeat().toString().replaceAll("=", ":");
		totalSeat = totalSeat.replaceAll("\\[", "\"");
		totalSeat = totalSeat.replaceAll("\\]", "\"");
		totalSeat = totalSeat.replaceAll(":\\{", ":\\{\"");
		totalSeat = totalSeat.replaceAll(":\"", "\":\"");
		totalSeat = totalSeat.replaceAll("\", ", "\", \"");

		String availableSeat = "\"available_seat\":"
				+ svg.getSvgAvailableSeat().toString().replaceAll("=", ":");
		availableSeat = availableSeat.replaceAll("\\[", "\"");
		availableSeat = availableSeat.replaceAll("\\]", "\"");
		availableSeat = availableSeat.replaceAll(":\\{", ":\\{\"");
		availableSeat = availableSeat.replaceAll(":\"", "\":\"");
		availableSeat = availableSeat.replaceAll("\", ", "\", \"");

		HashMap<String, String> college_eligiblity = (HashMap<String, String>) ServletActionContext
				.getServletContext().getAttribute("COLLEGE_ELIGIBILITY");
		String eligibility = "\"eligibility\": ["
				+ college_eligiblity.get(eiin) + "]";
		String svgString = "{" + shift + "," + version + "," + group + ","
				+ sqYN + "," + totalSeat + "," + availableSeat + ","
				+ eligibility + "}";
		// String
		// svgString="{"+shift+","+version+","+group+","+sqYN+","+availableSeat+","+eligibility+"}";

		System.out.println("JSON is here = " + group);

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
			response = applicationDAO.saveApplication(applicant, choice,
					getIpAddressDTO());
		// }

		System.out.println("Application Submission Status :<<"
				+ response.getStatus() + ">> || <<"
				+ response.getApplication_id() + ">>");
		String json = gson.toJson(response);
		setJsonResponse(json);
		return null;

	}

	public String submitApplication_TT() {
		ResponseDTO response = new ResponseDTO();
		Gson gson = new Gson();
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			String strDate = "01-07-1997";
			java.util.Date date1 = format.parse(applicant.getSsc_info()
					.getBirth_date());
			java.util.Date date2 = format.parse(strDate);
			if (date1.compareTo(date2) <= 0) {
				System.out.println("Birth date is not valid");
				response.setStatus("INVALID");
				response.setMessage("Birth date is not valid.!!!");
				String json = gson.toJson(response);
				setJsonResponse(json);
				return null;
			}
		} catch (Exception e) {

		}

		if (applicant.getApplication_id() == null) {
			System.out.println("Please reload and try again.!!!");
			response.setStatus("INVALID");
			response.setMessage("Please reload and try again.!!!");
			String json = gson.toJson(response);
			setJsonResponse(json);
			return null;
		}

		if (!isValidMobile(applicant.getApplication_info().getMobile_number())) {
			System.out.println("Please suuply valid mobile number.!!!");
			response.setStatus("INVALID");
			response.setMessage("Please suuply valid mobile number.!!!");
			String json = gson.toJson(response);
			setJsonResponse(json);
			return null;
		}
		try {
			if (!(session.get("ssc_roll").equals(
					applicant.getSsc_info().getRoll())
					&& session.get("ssc_board").equals(
							applicant.getSsc_info().getBoard_id())
					&& session.get("ssc_year").equals(
							applicant.getSsc_info().getPassing_year()) && session
					.get("ssc_reg").equals(applicant.getSsc_info().getReg_no()))) {
				System.out
						.println("Sorry, you have manipulated your SSC basic information. We can't accept your application.!!!");
				response.setStatus("INVALID");
				response.setMessage("Sorry, you have manipulated your SSC basic information. We can't accept your application.!!!");
				String json = gson.toJson(response);
				setJsonResponse(json);
				return null;
			}
		} catch (Exception e) {
			System.out.println(session.get("ssc_roll"));
			System.out.println(session.get("ssc_board"));
			System.out.println(session.get("ssc_year"));
			System.out.println(session.get("ssc_reg"));
			System.out.println(applicant.getSsc_info().getRoll());
			System.out.println(applicant.getSsc_info().getBoard_id());
			System.out.println(applicant.getSsc_info().getPassing_year());
			System.out.println(applicant.getSsc_info().getReg_no());
			System.out.println("Please try to submit again.!!!");
			response.setStatus("INVALID");
			response.setMessage("&#2470;&#2497;&#2435;&#2454;&#2495;&#2468;, &#2438;&#2474;&#2472;&#2494;&#2480; &#2488;&#2503;&#2486;&#2472;&#2503;&#2480; &#2478;&#2503;&#2527;&#2494;&#2470; &#2486;&#2503;&#2487; &#2489;&#2527;&#2503; &#2455;&#2495;&#2527;&#2503;&#2459;&#2503;&#2404; &#2438;&#2474;&#2472;&#2494;&#2453;&#2503; &#2447;&#2454;&#2472; &#2438;&#2476;&#2503;&#2470;&#2472; &#2474;&#2509;&#2480;&#2453;&#2509;&#2480;&#2495;&#2527;&#2494;&#2463;&#2495; &#2474;&#2497;&#2472;&#2480;&#2494;&#2527; &#2474;&#2509;&#2480;&#2469;&#2478; &#2489;&#2468;&#2503; &#2486;&#2497;&#2480;&#2497; &#2453;&#2480;&#2468;&#2503; &#2489;&#2476;&#2503;&#2404;<br/><br/>Sorry, your session has expired. You have to restart the application process from the beginning.");
			// /by joy
			e.printStackTrace();
			String json = gson.toJson(response);
			setJsonResponse(json);
			return null;
		}
		// for (int i = 0; i < choice.size(); i++)
		for (int i = choice.size() - 1; i >= 0; i--) {
			if (choice.get(i) == null)
				choice.remove(i);
		}
		Set<String> uniqueChoiceSet = new HashSet<String>();
		for (int i = 0; i < choice.size(); i++) {
			ChoiceDTO tmpchoice = choice.get(i);
			uniqueChoiceSet.add(tmpchoice.getEiin() + ""
					+ tmpchoice.getShift_id() + "" + tmpchoice.getVersion_id()
					+ "" + tmpchoice.getGroup_id() + ""
					+ tmpchoice.getSpecial_quota());
		}
		if (uniqueChoiceSet.size() != choice.size()) {
			System.out
					.println("Sorry, you have manipulated your Application Data. We can't accept your application.!!!");
			response.setStatus("ERROR");
			response.setMessage("Sorry, you have manipulated your Application Data. We can't accept your application.!!!");
			String json = gson.toJson(response);
			setJsonResponse(json);
			return null;
		}
		Set<String> choiceEiinSet = new HashSet<String>();
		for (int i = 0; i < choice.size(); i++) {
			choiceEiinSet.add(choice.get(i).getEiin());
		}
		/*
		 * if(choiceEiinSet.size() < 5){ System.out.println(
		 * "Application failed. You need to select at least 5 (five) different colleges."
		 * ); response.setMessage(
		 * "Application failed. You need to select at least 5 (five) different colleges."
		 * ); response.setStatus("INVALID"); String json =
		 * gson.toJson(response);setJsonResponse(json);return null; }
		 */
		if (choiceEiinSet.size() < 5) {
			System.out
					.println("Application failed. You need to select at least 5 (five) different colleges.");
			response.setMessage("Application failed. You need to select at least 5 (five) different colleges.");
			response.setStatus("INVALID");
			String json = gson.toJson(response);
			setJsonResponse(json);
			return null;
		}

		if (choiceEiinSet.size() > 10) {
			System.out
					.println("Application failed. You can not select more than 10 (ten) different colleges.");
			response.setMessage("Application failed. You can not select more than 10 (ten) different colleges.");
			response.setStatus("INVALID");
			String json = gson.toJson(response);
			setJsonResponse(json);
			return null;
		}

		if (applicant.getApplication_info().getQuota_ff() == null)
			applicant.getApplication_info().setQuota_ff("N");
		if (applicant.getApplication_info().getQuota_eq() == null)
			applicant.getApplication_info().setQuota_eq("N");
		if (applicant.getApplication_info().getQuota_bksp() == null)
			applicant.getApplication_info().setQuota_bksp("N");
		if (applicant.getApplication_info().getQuota_expatriate() == null)
			applicant.getApplication_info().setQuota_expatriate("N");

		ApplicationDAO applicationDAO = new ApplicationDAO();
		response = applicationDAO.validateApplication_TT(applicant, choice,
				"NEW");
		String scode = getSecurityCode();
		if (response.getStatus().equalsIgnoreCase("VALID")) {

			response = applicationDAO.saveApplication_TT(applicant, choice,
					getIpAddressDTO(), scode);
			if (response.getStatus().equalsIgnoreCase("OK")) {
				// response.setScode(scode);
				((SessionMap<String, Object>) session).invalidate();
			} else {
				System.out.println("New Submission Status :<<"
						+ response.getStatus() + ">> || << message:"
						+ response.getMessage() + ">>");
			}

		}

		System.out.println("New Submission Status :<<" + response.getStatus()
				+ ">> || << ID:" + response.getApplication_id() + ">>");
		String json = gson.toJson(response);
		setJsonResponse(json);
		return null;

	}

	public String submitApplicationEdit_TT() {
		ResponseDTO response = new ResponseDTO();
		Gson gson = new Gson();

		// if(!(session.get("ssc_roll").equals(applicant.getSsc_info().getRoll())
		// &&
		// session.get("ssc_board").equals(applicant.getSsc_info().getBoard_id())
		// &&
		// session.get("ssc_year").equals(applicant.getSsc_info().getPassing_year())
		// &&
		// session.get("ssc_reg").equals(applicant.getSsc_info().getReg_no())))
		// {
		// response.setStatus("INVALID");
		// response.setMessage("Sorry, you have manipulated your SSC basic information. We can't accept your application.!!!");
		// String json = gson.toJson(response);setJsonResponse(json);return
		// null;
		// }
		// String priority1 = "";
		// String priority2 = "";
		// int priority = 532;
		// for (int i = 0; i < choice.size(); i++)
		// {
		// if(((ChoiceDTO)choice.get(i)).getApplication_type()==null)
		// {
		// priority = i;
		// priority1 = ((ChoiceDTO)choice.get(i)).getPriority();
		// choice.set(i, null);
		// }
		// if(i > priority)
		// {
		// priority2 = ((ChoiceDTO)choice.get(i)).getPriority();
		// ((ChoiceDTO)choice.get(i)).setPriority(priority1);
		// priority1 = priority2;
		// }
		// }

		for (int i = 0; i < choice.size(); i++) {
			if (choice.get(i) == null)
				choice.remove(i);
		}
		Set<String> choiceEiinSet = new HashSet<String>();
		for (int i = 0; i < choice.size(); i++) {
			if (choice.get(i).getApplication_type() == null
					|| (choice.get(i).getApplication_type()
							.equalsIgnoreCase("WEB")))
				choiceEiinSet.add(choice.get(i).getEiin());
		}
		if (choiceEiinSet.size() > 10) {
			response.setMessage("You can select maximum 10 (Ten) different colleges.");
			response.setStatus("INVALID");
			String json = gson.toJson(response);
			setJsonResponse(json);
			return null;
		}
		for (int i = choice.size() - 1; i >= 0; i--) {
			if (choice.get(i) != null
					&& choice.get(i).getApplication_type() != null
					&& choice.get(i).getApplication_type()
							.equalsIgnoreCase("WEB"))
				applicant.getApplication_info().setMobile_number(
						choice.get(i).getMobile_no());
			// if(choice.get(i)==null ||
			// choice.get(i).getApplication_type()!=null)
			// {
			// choice.remove(i);
			// }
		}
		Set<String> uniqueChoiceSet = new HashSet<String>();
		for (int i = 0; i < choice.size(); i++) {
			ChoiceDTO tmpchoice = choice.get(i);
			uniqueChoiceSet.add(tmpchoice.getEiin() + ""
					+ tmpchoice.getShift_id() + "" + tmpchoice.getVersion_id()
					+ "" + tmpchoice.getGroup_id() + ""
					+ tmpchoice.getSpecial_quota());
		}
		if (uniqueChoiceSet.size() != choice.size()) {
			response.setStatus("ERROR");
			response.setMessage("Sorry, you have manipulated your Application Data. We can't accept your application.!!!");
			String json = gson.toJson(response);
			setJsonResponse(json);
			return null;
		}

		ApplicationDAO applicationDAO = new ApplicationDAO();
		response = applicationDAO.validateApplication_TT(applicant, choice,
				"UPDATE");

		if (response.getStatus().equalsIgnoreCase("VALID")) {
			String scode = "";
			if (session.get("webpayment") != null
					&& ((String) session.get("webpayment"))
							.equalsIgnoreCase("no"))
				scode = getSecurityCode();

			String contactno = (String) session.get("contactno");
			response = applicationDAO.saveApplicationEdit_TT(applicant, choice,
					getIpAddressDTO(), scode, contactno);
			if (session.get("contactno") != null
					&& response.getStatus().equalsIgnoreCase("OK")) {
				contactno = (String) session.get("contactno");
				if (scode.equals(""))
					response.setSms("no");
				else
					response.setSms("yes");
			}
		}
		System.out.println("App Edit Status :<<" + response.getStatus()
				+ ">> || <<" + response.getApplication_id() + ">>");
		if (!response.getStatus().equalsIgnoreCase("OK")) {
			System.out.println("App Edit Status :<<" + response.getStatus()
					+ ">> || <<" + response.getMessage() + ">>");
		}
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
		response = applicationDAO.validateApplicationNew(applicant, choice,
				"NEW");

		if (response.getStatus().equalsIgnoreCase("VALID")) {
			response = applicationDAO.saveNewApplication(applicant, choice,
					getIpAddressDTO());
			System.out.println("Application Submission Status(2nd Phase) :<<"
					+ response.getStatus() + ">> || <<"
					+ response.getApplication_id() + ">>");
		}
		String json = gson.toJson(response);
		setJsonResponse(json);
		return null;

	}

	public String choiceListReport_TT() {
		if (application_id != null) {
			CollegeDAO collegeDAO = new CollegeDAO();
			setApplicantInfo(application_id);
			choice = collegeDAO.getNewChoiceList(application_id);
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

	public String getTrxid() {
		return trxid;
	}

	public void setTrxid(String trxid) {
		this.trxid = trxid;
	}

	public String getThana_id() {
		return thana_id;
	}

	public void setThana_id(String thana_id) {
		this.thana_id = thana_id;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getRegPayment() {

		String meritTable = " ";
		String checkSum = (String) session.get("CorrectAnswer");
		if (!user_captcha.equalsIgnoreCase(checkSum)) {
			try {
				meritTable += "<div class=\"box_container\" style=\"overflow: hidden; position: relative; height: 100%; margin-top: 8px;\" id=\"personal_result_div\"><div class=\"box_body\" style=\"padding: 12px;\"><div class=\"successMessageDiv red\" id=\"finalResultDiv\">";
				meritTable += "<div  class=\"successResultMessage green\">"
						+ "&#2438;&#2474;&#2472;&#2494;&#2480; &#2470;&#2503;&#2451;&#2527;&#2494; &#2477;&#2503;&#2480;&#2495;&#2475;&#2495;&#2453;&#2503;&#2486;&#2472; &#2453;&#2507;&#2465; &#2463;&#2495; &#2488;&#2464;&#2495;&#2453; &#2472;&#2527;&#2404;<br/><br/>The verification code you have provided is not correct.</div>";
				// meritTable +=
				// "<input type=\"button\" class=\"myButton green\" id=\"clearButton\" value=\"Refresh This Page\" onclick=\"clearButton()\"/>";
				meritTable += "</div></div></div>";
				try {
					response.setContentType("text/html");
					response.getWriter().write(meritTable);
				} catch (Exception e) {
				}
				return null;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		((SessionMap<String, Object>) session).invalidate();
		double startTime = System.nanoTime();
		int tmp = 0;
		ResultDTO tmpResult = null;
		try {
			// tmp = ApplicationDAO.getRegPayment(ssc_roll.trim(),
			// ssc_board.trim(), ssc_year.trim(), ssc_reg.trim());
			tmpResult = ApplicationDAO.getRegPaymentRec(ssc_roll.trim(),
					ssc_board.trim(), ssc_year.trim(), ssc_reg.trim());
		} catch (Exception e) {
			// TODO: handle exception
		}

		// if(tmp>0)
		// {
		// meritTable +=
		// "<div class=\"box_container\" style=\"overflow: hidden; position: relative; height: 100%; margin-top: 8px;\" id=\"personal_result_div\"><div class=\"box_header\">Your Result</div><div class=\"box_body\" style=\"padding: 12px;\"><div class=\"successMessageDiv\" id=\"finalResultDiv\">";
		// meritTable += "<div  class=\"successResultMessage green\">" +
		// "&#2468;&#2507;&#2478;&#2494;&#2480; &#2472;&#2495;&#2486;&#2509;&#2458;&#2494;&#2527;&#2472; &#2488;&#2478;&#2509;&#2474;&#2472;&#2509;&#2472; &#2489;&#2527;&#2503;&#2459;&#2503;&#2404;</div>";
		// meritTable += "</div></div></div>";
		// try {
		// response.setContentType("text/html");
		// response.getWriter().write(meritTable);
		// } catch (Exception e) {
		// }
		// return null;
		// }
		// else
		// {
		// meritTable +=
		// "<div class=\"box_container\" style=\"overflow: hidden; position: relative; height: 100%; margin-top: 8px;\" id=\"personal_result_div\"><div class=\"box_header\">Your Result</div><div class=\"box_body\" style=\"padding: 12px;\"><div class=\"successMessageDiv red\" id=\"finalResultDiv\">";
		// meritTable += "<div  class=\"successResultMessage red\">" +
		// "&#2468;&#2507;&#2478;&#2494;&#2480; &#2472;&#2495;&#2486;&#2509;&#2458;&#2494;&#2527;&#2472; &#2488;&#2478;&#2509;&#2474;&#2472;&#2509;&#2472; &#2489;&#2527;&#2472;&#2495;&#2404;</div>";
		// meritTable += "</div></div></div>";
		// try {
		// response.setContentType("text/html");
		// response.getWriter().write(meritTable);
		// } catch (Exception e) {
		// }
		// return null;
		// }

		if (tmpResult != null) {
			if (tmpResult.getPaid().equalsIgnoreCase("NO")) {
				meritTable += "<div class=\"box_container\" style=\"overflow: hidden; position: relative; height: 100%; margin-top: 8px;\" id=\"personal_result_div\"><div class=\"box_header\">Your Result</div><div class=\"box_body\" style=\"padding: 12px;\"><div class=\"successMessageDiv red\" id=\"finalResultDiv\">";
				meritTable += "<div  class=\"successResultMessage red\">"
						+ "&#2438;&#2474;&#2472;&#2494;&#2480; &#2472;&#2495;&#2486;&#2509;&#2458;&#2494;&#2527;&#2472;&#2503;&#2480; &#2463;&#2494;&#2453;&#2494; &#2460;&#2478;&#2494; &#2470;&#2503;&#2527;&#2494; &#2489;&#2527;&#2472;&#2495;</div>";
				meritTable += "</div></div></div>";
				try {
					response.setContentType("text/html");
					response.getWriter().write(meritTable);
				} catch (Exception e) {
				}
				return null;
			} else if (tmpResult.getPaid().equalsIgnoreCase("YES")
					&& tmpResult.getCollegereceive().equalsIgnoreCase(
							"Not Received")) {
				meritTable += "<div class=\"box_container\" style=\"overflow: hidden; position: relative; height: 100%; margin-top: 8px;\" id=\"personal_result_div\"><div class=\"box_header\">Your Result</div><div class=\"box_body\" style=\"padding: 12px;\"><div class=\"successMessageDiv\" id=\"finalResultDiv\">";
				meritTable += "<div  class=\"successResultMessage green\">"
						+ "&#2438;&#2474;&#2472;&#2494;&#2480; &#2472;&#2495;&#2486;&#2509;&#2458;&#2494;&#2527;&#2472;&#2503;&#2480; &#2463;&#2494;&#2453;&#2494; &#2460;&#2478;&#2494; &#2489;&#2527;&#2503;&#2459;&#2503;&#2404; &#2477;&#2480;&#2509;&#2468;&#2495;&#2480; &#2460;&#2472;&#2509;&#2479; &#2536;&#2535;/&#2534;&#2541;/&#2536;&#2534;&#2535;&#2542; &#2489;&#2468;&#2503; &#2536;&#2537;/&#2534;&#2541;/&#2536;&#2534;&#2535;&#2542; &#2468;&#2494;&#2480;&#2495;&#2454;&#2503;&#2480; &#2478;&#2471;&#2509;&#2479;&#2503; &#2453;&#2482;&#2503;&#2460;&#2503; &#2479;&#2507;&#2455;&#2494;&#2479;&#2507;&#2455; &#2453;&#2480;&#2468;&#2503; &#2489;&#2476;&#2503;&#2404;	</div>";
				meritTable += "</div></div></div>";
				try {
					response.setContentType("text/html");
					response.getWriter().write(meritTable);
				} catch (Exception e) {
				}
				return null;
			} else {
				meritTable += "<div class=\"box_container\" style=\"overflow: hidden; position: relative; height: 100%; margin-top: 8px;\" id=\"personal_result_div\"><div class=\"box_header\">Your Result</div><div class=\"box_body\" style=\"padding: 12px;\"><div class=\"successMessageDiv red\" id=\"finalResultDiv\">";
				meritTable += "<div  class=\"successResultMessage red\">"
						+ "&#2468;&#2507;&#2478;&#2494;&#2480; &#2472;&#2495;&#2486;&#2509;&#2458;&#2494;&#2527;&#2472;&#2503;&#2480; &#2463;&#2494;&#2453;&#2494; &#2460;&#2478;&#2494; &#2470;&#2495;&#2527;&#2503;&#2459; &#2447;&#2476;&#2434; &#2453;&#2482;&#2503;&#2460; &#2472;&#2495;&#2486;&#2509;&#2458;&#2494;&#2527;&#2472; &#2438;&#2459;&#2503;&#2404;</div>";
				meritTable += "</div></div></div>";
				try {
					response.setContentType("text/html");
					response.getWriter().write(meritTable);
				} catch (Exception e) {
				}
				return null;
			}
		} else {
			meritTable += "<div class=\"box_container\" style=\"overflow: hidden; position: relative; height: 100%; margin-top: 8px;\" id=\"personal_result_div\"><div class=\"box_header\">Your Result</div><div class=\"box_body\" style=\"padding: 12px;\"><div class=\"successMessageDiv red\" id=\"finalResultDiv\">";
			meritTable += "<div  class=\"successResultMessage red\">"
					+ "&#2438;&#2474;&#2472;&#2494;&#2480; &#2468;&#2469;&#2509;&#2479; &#2474;&#2494;&#2451;&#2527;&#2494; &#2479;&#2494;&#2527;&#2472;&#2495;</div>";
			meritTable += "</div></div></div>";
			try {
				response.setContentType("text/html");
				response.getWriter().write(meritTable);
			} catch (Exception e) {
			}
			return null;
		}

	}

	private String getSecurityCode() {
		Random rand = new Random();
		int length = 6;// rand.nextInt(6) + 16;
		char[] password = new char[length];
		for (int x = 0; x < length; x++) {
			int randDecimalAsciiVal = 0;
			int cas = rand.nextInt(3);
			if (cas == 0)
				randDecimalAsciiVal = rand.nextInt(9) + 48;
			else if (cas == 1)
				randDecimalAsciiVal = rand.nextInt(26) + 65;
			else
				randDecimalAsciiVal = rand.nextInt(26) + 97;
			password[x] = String.valueOf(randDecimalAsciiVal / 10).charAt(0);
		}
		String result = String.valueOf(password);
		return result;
	}

	public String getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(String updateCount) {
		this.updateCount = updateCount;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

}