package edu.utils.upload;

public class UploadResponse {
	  private String responseText;
	  private int statusCode;

	  public String getResponseText() {
	    return responseText;
	  }

	  public void setResponseText(final String pResponseText) {
	    responseText = pResponseText;
	  }

	  public int getStatusCode() {
	    return statusCode;
	  }

	  public void setStatusCode(final int pStatusCode) {
	    statusCode = pStatusCode;
	  }
	}
