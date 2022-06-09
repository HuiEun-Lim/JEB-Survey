package com.JEB.survey.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//회원 컨트롤러
@Controller
public class MemberController {
	
	@GetMapping(value="/member")
	  public String blogForm(){
		  	 return "/member/login";
	 } 
}
