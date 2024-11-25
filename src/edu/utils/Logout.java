package edu.utils;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Logout extends ActionSupport{
	public String execute() throws Exception {
		ServletActionContext.getRequest().getSession().setAttribute("","");
		Cookie[] uCook = ServletActionContext.getRequest().getCookies();
		try
		{
			ServletActionContext.getRequest().getSession().removeAttribute("uID");
			ServletActionContext.getRequest().getSession().removeAttribute("sidebarlist");
			ServletActionContext.getRequest().getSession().invalidate();
		}
		catch(Exception e)
		{
			
		}
		return SUCCESS;
	}
	
	public String appLogout()throws Exception {
		ServletActionContext.getRequest().getSession().setAttribute("","");
		Cookie[] uCook = ServletActionContext.getRequest().getCookies();
		try
		{
			ServletActionContext.getRequest().getSession().removeAttribute("uID");
			ServletActionContext.getRequest().getSession().removeAttribute("sidebarlist");
			ServletActionContext.getRequest().getSession().invalidate();
		}
		catch(Exception e)
		{
			
		}
		return SUCCESS;
	}

}
