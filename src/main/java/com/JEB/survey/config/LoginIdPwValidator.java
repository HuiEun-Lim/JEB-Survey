package com.JEB.survey.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.JEB.survey.model.MemberDto;
import com.JEB.survey.repository.MemberMapper;

@Service
public class LoginIdPwValidator implements UserDetailsService{
	  
	  @Autowired private MemberMapper memberMapper;
		
	 @Bean 
	 public PasswordEncoder passwordEncoder() { 
		 return new BCryptPasswordEncoder(); 
	 }

	  @Override
	  public UserDetails loadUserByUsername(String memId) throws UsernameNotFoundException{
		  MemberDto user = memberMapper.getUserInfo(memId);
		  
		  if(user == null) {
			 System.out.println("유저가 없습니다."+user);
			 throw new UsernameNotFoundException(memId);
		  }
		  
		  String pw = user.getMemPw();
		  String roles = "USER";
		  
		  return User.builder()
				  .username(memId)
				  .password(pw)
				  .roles(roles)
				  .build();
	  }
	  
}
