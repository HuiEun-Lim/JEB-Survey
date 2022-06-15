package com.JEB.survey.config;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler{


	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		 String msg;
	        
	        if (exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException){
	        	msg="아이디 또는 비밀번호가 맞지 않습니다.";
	        	
	        }else if (exception instanceof UsernameNotFoundException){
	        	msg="존재하지 않는 아이디 입니다.";
	        	
	        }
	        else{
	        	msg="알 수 없는 이유로 로그인이 안되고 있습니다.";
	        	
	        }
	        

	        System.out.println("핸들러?"+"request"+request+"\n"+"response"+response+"\n"+"exception"+exception+"\n"+msg);  
	        msg = URLEncoder.encode(msg,"UTF-8");
	  
	        setDefaultFailureUrl("/loginForm?error=true&exception="+msg);

	        super.onAuthenticationFailure(request,response,exception);
	    }
	
}
