package edu.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import edu.action.common.BaseAction;
import edu.dao.PaymentDAO;

import edu.helpers.SmsSender;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import com.opensymphony.xwork2.ActionContext;

public class Payment extends BaseAction implements Runnable {
	public void run() {
		// sendOapplication(app_id);
	}

	public String sendNotSendApplication() {
		PaymentDAO pdao = new PaymentDAO();
		List<String> listAppliDTO = pdao.getApplicantIdNotSend();
		for (int i = 0; i < listAppliDTO.size(); i++) {
			if (i % 100 == 0)
				try {
					Thread.sleep(5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			;
			// sendOapplication(listAppliDTO.get(i));
		}
		return null;
	}

	private String TXN_ID;
	private String BILL_NO;
	private String PAYMENT_AMOUNT;
	private String PAYMENT_DATE;
	private String PAYER_MOBILE_NO;

	public String getTXN_ID() {
		return TXN_ID;
	}

	public void setTXN_ID(String tXN_ID) {
		TXN_ID = tXN_ID;
	}

	public String getBILL_NO() {
		return BILL_NO;
	}

	public void setBILL_NO(String bILL_NO) {
		BILL_NO = bILL_NO;
	}

	public String getPAYMENT_AMOUNT() {
		return PAYMENT_AMOUNT;
	}

	public void setPAYMENT_AMOUNT(String pAYMENT_AMOUNT) {
		PAYMENT_AMOUNT = pAYMENT_AMOUNT;
	}

	public String getPAYMENT_DATE() {
		return PAYMENT_DATE;
	}

	public void setPAYMENT_DATE(String pAYMENT_DATE) {
		PAYMENT_DATE = pAYMENT_DATE;
	}

	public String getPAYER_MOBILE_NO() {
		return PAYER_MOBILE_NO;
	}

	public void setPAYER_MOBILE_NO(String pAYER_MOBILE_NO) {
		PAYER_MOBILE_NO = pAYER_MOBILE_NO;
	}

	/*
	 * public String getTransaction_id() {
	 * return transaction_id;
	 * }
	 * public void setTransaction_id(String transaction_id) {
	 * this.transaction_id = transaction_id;
	 * }
	 * public String getFrom() {
	 * return from;
	 * }
	 * public void setFrom(String from) {
	 * this.from = from;
	 * }
	 * public String getDate() {
	 * return date;
	 * }
	 * public void setDate(String date) {
	 * this.date = date;
	 * }
	 * public String getAmount() {
	 * return amount;
	 * }
	 * public void setAmount(String amount) {
	 * this.amount = amount;
	 * }
	 * public String getBill_no() {
	 * return bill_no;
	 * }
	 * public void setBill_no(String bill_no) {
	 * this.bill_no = bill_no;
	 * }
	 */
	public String DDBL_Payment() {
		String LocalIP = request.getHeader("X-Forwarded-For");
		String via = request.getHeader("Via");
		String remoteIP = request.getRemoteAddr();
		remoteIP = remoteIP.trim();
		System.out.println("LocalIP: " + LocalIP + " remoteIP: " + remoteIP);
		System.out.println(TXN_ID + " - " + PAYER_MOBILE_NO + " - " + PAYMENT_DATE + " - " + BILL_NO);
		if (remoteIP.equalsIgnoreCase("103.11.136.153"))
			if (true) {
				PaymentDAO pdao = new PaymentDAO();
				String BILLER_ID = "220";
				boolean tmp = pdao.DDBL_Payment(TXN_ID, PAYER_MOBILE_NO, PAYMENT_DATE, PAYMENT_AMOUNT, BILL_NO,
						BILLER_ID);
				try {
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setCharacterEncoding("UTF-8");
					if (tmp)
						response.getWriter().write("<reply>success</reply>");
					else
						response.getWriter().write("<reply>fail</reply>");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Invalid IP");
				try {
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write("<reply>Invalid IP</reply>");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		return null;

	}

	public String DDBL_Payment_bmet() {
		String LocalIP = request.getHeader("X-Forwarded-For");
		String via = request.getHeader("Via");
		String remoteIP = request.getRemoteAddr();
		remoteIP = remoteIP.trim();
		System.out.println("LocalIP: " + LocalIP + " remoteIP: " + remoteIP);
		System.out.println(TXN_ID + " - " + PAYER_MOBILE_NO + " - " + PAYMENT_DATE + " - " + BILL_NO);
		if (remoteIP.equalsIgnoreCase("103.11.136.153") || remoteIP.equalsIgnoreCase("10.10.200.142")) {
			PaymentDAO pdao = new PaymentDAO();
			String BILLER_ID = "225";
			boolean tmp = pdao.DDBL_Payment(TXN_ID, PAYER_MOBILE_NO, PAYMENT_DATE, PAYMENT_AMOUNT, BILL_NO, BILLER_ID);
			try {
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("UTF-8");
				if (tmp)
					response.getWriter().write("<reply>success</reply>");
				else
					response.getWriter().write("<reply>fail</reply>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Invalid IP");
			try {
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("<reply>Invalid IP</reply>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String DBBLbillNumberCheck() {
		String LocalIP = request.getHeader("X-Forwarded-For");
		String via = request.getHeader("Via");
		String remoteIP = request.getRemoteAddr();
		remoteIP = remoteIP.trim();
		System.out.println("LocalIP: " + LocalIP + " remoteIP: " + remoteIP);
		System.out.println("Check Bill no.: " + BILL_NO);

		if (true)
		// if(remoteIP.equalsIgnoreCase("103.11.136.153")
		// ||remoteIP.equalsIgnoreCase("10.10.200.142"))
		{
			PaymentDAO pdao = new PaymentDAO();
			boolean tmp = pdao.DDBLCheckBillNo(BILL_NO);
			try {
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("UTF-8");
				if (tmp)
					response.getWriter().write("<reply>success</reply>");
				else
					response.getWriter().write("<reply>fail</reply>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Invalid IP");
			try {
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("<reply>Invalid IP</reply>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public String DBBLbillNumberAsAppid() {
		String LocalIP = request.getHeader("X-Forwarded-For");
		String via = request.getHeader("Via");
		String remoteIP = request.getRemoteAddr();
		remoteIP = remoteIP.trim();
		System.out.println("LocalIP: " + LocalIP + " remoteIP: " + remoteIP);
		System.out.println("Check Bill no.: " + BILL_NO);

		PaymentDAO pdao = new PaymentDAO();
		boolean tmp = pdao.DDBLCheckBillNoAsAppid(BILL_NO, PAYMENT_AMOUNT);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			if (tmp)
				response.getWriter().write("success");
			else
				response.getWriter().write("fail");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String DDBL_BTEB_Registration() {
		final String LocalIP = request.getHeader("X-Forwarded-For");
		String via = request.getHeader("Via");
		String remoteIP = request.getRemoteAddr();
		remoteIP = remoteIP.trim();
		System.out.println("LocalIP: " + LocalIP + " remoteIP: " + remoteIP);
		System.out.println(TXN_ID + " - " + PAYER_MOBILE_NO + " - " + PAYMENT_DATE + " - " + BILL_NO);
		if (remoteIP.equalsIgnoreCase("103.11.136.153") || remoteIP.equalsIgnoreCase("10.10.200.142"))
		// if(true)
		{
			PaymentDAO pdao = new PaymentDAO();
			String BILLER_ID = "245";
			boolean tmp = pdao.DDBL_Registration_Payment(TXN_ID, PAYER_MOBILE_NO, PAYMENT_DATE, PAYMENT_AMOUNT, BILL_NO,
					BILLER_ID);
			try {
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("UTF-8");
				if (tmp) {
					response.getWriter().write("success");
					// sendConfirmSMS(BILL_NO);
				} else
					response.getWriter().write("fail");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Invalid IP");
			try {
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("<reply>Invalid IP</reply>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private String app_id;

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
}
