package com.JEB.survey.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//회원 컨트롤러
@Controller
public class MemberController {
	
	@GetMapping(value="/loginForm")
	  public String login(){
		  	 return "member/login";
	 } 
	
	@GetMapping(value="/joinForm")
	  public String join(){
		  	 return "member/join";
	 } 
	
}
