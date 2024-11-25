package edu.utils.upload;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;


public class UploadToProvider {
	  private static Logger mLogger = Logger.getLogger(UploadToProvider.class);

	  private static UploadToProvider instance = null;

	  public synchronized UploadResponse upload(final File pFile, final String pCategory, final String fboard, final String board, final String pyear) throws Exception {
	    UploadResponse uploadResponse = new UploadResponse();
	    Configuration configuration = Configuration.getInstance();
	    HttpPost httppost = new HttpPost(configuration.getConfigurationMap().get(configuration.getEnvironment()+".image.uploader.url"));
	    MultipartEntity entity = new MultipartEntity();
	    entity.addPart("fileUpload", new FileBody(pFile));
	    httppost.setEntity(entity);
	    entity.addPart("category", new StringBody(pCategory));
	    httppost.setEntity(entity);
	    entity.addPart("fboard", new StringBody(fboard));
	    httppost.setEntity(entity);
	    entity.addPart("board", new StringBody(board));
	    httppost.setEntity(entity);
	    entity.addPart("pyear", new StringBody(pyear));
	    httppost.setEntity(entity);

	    HttpClient httpclient = HttpClientBuilder.create().build();
	    HttpResponse httpResponse = httpclient.execute(httppost);
	    HttpEntity resEntity = httpResponse.getEntity();

	    int statusCode = httpResponse.getStatusLine().getStatusCode();

	    if (mLogger.isDebugEnabled()) {
	      mLogger.debug("Status code of sending image " + statusCode);
	    }
	    // Get the contents of the response
	    InputStream input = resEntity.getContent();
	    String responseBody = IOUtils.toString(input);
	    input.close();

	    uploadResponse.setStatusCode(statusCode);
	    uploadResponse.setResponseText(responseBody);

	    return uploadResponse;
	  }

	  public static UploadToProvider getInstance() {
	    if (instance == null) {
	      instance = new UploadToProvider();
	    }

	    return instance;
	  }
	}
