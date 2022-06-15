package com.JEB.survey.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;


public class LoginSuccessHandler implements AuthenticationSuccessHandler{


	private String defaultUrl;
	
	
	public String getDefaultUrl() {
		return defaultUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}
	
	// Constructor
		public LoginSuccessHandler(String defaultUrl){
			this.defaultUrl = defaultUrl;
		} 
	
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    
 // redirectUrl 지정 메서드
 	protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response, 
 			Authentication authentication) throws IOException, ServletException {
 		
 		SavedRequest savedRequest = requestCache.getRequest(request, response);
 		
 		if ( savedRequest != null ) {
 			String targetUrl = savedRequest.getRedirectUrl();
 			
 			redirectStrategy.sendRedirect(request, response, targetUrl);
 		}else {
 			
 			redirectStrategy.sendRedirect(request, response, defaultUrl);
 		}
 	}
 	
	// 남아있는 에러세션이 있다면 지워준다.
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if( session == null ) return ;
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
    
     

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
    		Authentication authentication) throws IOException, ServletException {
    	//String requestUserName = request.getParameter(username);
    	// 에러세션 지우기
    			clearAuthenticationAttributes(request);
    			// Redirect URL 작업.
    			resultRedirectStrategy(request, response, authentication);
        
    }
    

    
    
    
  
}
