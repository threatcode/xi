package edu.utils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class UserActionMangerInterceptor implements Interceptor{

	private static final long serialVersionUID = 1L;
	 
    public String intercept(ActionInvocation invocation) throws Exception {
 
    	
    	/*
        UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		UserActionManager objUserActionManager=new UserActionManager();
		if(loggedInUser==null)
			return "login";
		
		
        String className = invocation.getAction().getClass().getName();
        long startTime = System.currentTimeMillis();
        String ActionName=invocation.getInvocationContext().getName();
        //System.out.println("Before calling action: " + invocation.getInvocationContext().getName());
 
        if(!objUserActionManager.isValidAction(loggedInUser.getRoleid(), ActionName))
			return "home";
        */
        String result = invocation.invoke();
 
        long endTime = System.currentTimeMillis();
       // System.out.println("After calling action: " + className
        //        + " Time taken: " + (endTime - startTime) + " ms");
 
        return result;
    }
 
    public void destroy() {
        //System.out.println("Destroying UserActionMangerInterceptor...");
    }
    public void init() {
        //System.out.println("Initializing UserActionMangerInterceptor...");
    }
}
