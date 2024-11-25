package edu.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 */
public class Configuration extends HttpServlet  implements Serializable {
  private static final long serialVersionUID = 24356590656845L;

  private static Logger mLogger = Logger.getLogger(Configuration.class);

  private static Configuration instance = null;
  private String file = "configuration.properties";
//  private Map<String, String> configurationMap = new HashMap<>();
     private Map<String, String> configurationMap = new HashMap<String, String>();
//    private Map<String, String> configurationMap = new HashMap();

  public Map<String, String> getConfigurationMap() {
    return configurationMap;
  }

  public void setConfigurationMap(final Map<String, String> pConfigurationMap) {
    configurationMap = pConfigurationMap;
  }

  public Configuration()  {
    try {
      InputStream inputStream = this.getClass().getResourceAsStream(file);
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

      String value;
      while ((value = bufferedReader.readLine()) != null) {
        if (value.contains("=")) {
          String key = value.split("=")[0];
          String keyValue = value.split("=")[1];

          if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(keyValue)) {
            configurationMap.put(key.trim(), keyValue.trim());
          }
        }
      }

    }
    catch (IOException ie) {
      mLogger.error("Resource file load exception: ", ie);
    }
    catch (Exception re) {
      mLogger.error("Resource not found", re);
    }
    // Exists only to defeat instantiation.

  }

  public String getEnvironment() {
  //  String environment = "production";
    String environment = "dev";
    try {
      Context ctx = new InitialContext();
      Context envContext = (Context) ctx.lookup("java:/comp/env");

      // Look up a data source
      environment
          = (String) envContext.lookup("image-server");
/*      if(StringUtils.isEmpty(environment)){
        environment = "dev";
       // environment = "production";
      }*/
    }
    catch (Exception e) {
      mLogger.error(e);
    }
    return environment;
  }


  public static Configuration getInstance() {
    if (instance == null) {
      instance = new Configuration();
    }

    return instance;
  }
}
