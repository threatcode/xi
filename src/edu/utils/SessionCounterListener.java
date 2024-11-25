package edu.utils;

import java.util.ArrayList;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.struts2.ServletActionContext;

import edu.dto.UserDTO;

public class SessionCounterListener implements HttpSessionListener  {
	
	  public void sessionCreated(HttpSessionEvent arg0) {

		System.out.println("sessionCreated - add one session into counter");
	  }
	 
	  public void sessionDestroyed(HttpSessionEvent arg0) {
		  if(arg0.getSession().getAttribute("user")!=null)
		  {
			  UserDTO user=(UserDTO)arg0.getSession().getAttribute("user");
			  if(user.getRoleid().equalsIgnoreCase("dc"))
			  {
				  ArrayList<UserDTO> login = (ArrayList<UserDTO>)ServletActionContext.getContext().getApplication().get("login");
				  for(int i=login.size()-1;i>=0;i--)
				  {
					  if(login.get(i).getUserid().equalsIgnoreCase(user.getUserid()))
						  login.remove(i);
				  }
				  ServletActionContext.getContext().getApplication().remove("login");
				  ServletActionContext.getContext().getApplication().put("login", login);
			  }
			  System.out.println(user.getRoleid());
		  }
		System.out.println("sessionDestroyed - deduct one session from counter");
	  }	

}
