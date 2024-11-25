
package edu.utils;

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


import java.io.File;
import java.io.InputStream;

public class UploadToProvider {
  private static Logger mLogger = Logger.getLogger(UploadToProvider.class);

  private static UploadToProvider instance = null;

  public synchronized UploadResponse upload(final File pFile, final String pCategory) throws Exception {
    UploadResponse uploadResponse = new UploadResponse();
    Configuration configuration = Configuration.getInstance();
    String env=configuration.getEnvironment()+".image.uploader.url";
    String filePath =configuration.getConfigurationMap().get(configuration.getEnvironment()+".image.uploader.url");
    HttpPost httppost = new HttpPost(configuration.getConfigurationMap().get(configuration.getEnvironment()+".image.uploader.url"));
    MultipartEntity entity = new MultipartEntity();
    entity.addPart("fileUpload", new FileBody(pFile));
    httppost.setEntity(entity);
    entity.addPart("category", new StringBody(pCategory));
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


