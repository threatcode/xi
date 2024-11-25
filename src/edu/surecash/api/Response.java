/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.surecash.api;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
public class Response{
    String description;
    String status;
    String statusCode;
    
    
    Response(String statusCode, String status,String description){

        this.description=description;
        this.status=status;
        this.statusCode=statusCode;
    }
    
    JSONObject createJSONObject() throws JSONException{
        JSONObject jsonData= new JSONObject();
        jsonData.put("Status",this.status);
        jsonData.put("StatusCode",this.statusCode);
        jsonData.put("Description",this.description);
        return jsonData;
    }
}
