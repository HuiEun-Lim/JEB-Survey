package com.JEB.survey.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.JEB.survey.model.MemberDto;
import com.JEB.survey.service.MemberService;

//회원 컨트롤러
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	//로그인 폼 보여주기
	  @GetMapping(value="/loginForm") 
	  public String login(@AuthenticationPrincipal  UserDetails userInfo, Model model) throws Exception{			 
		  System.out.println("로그인컨트롤러야 "+userInfo); 	  
		  return "member/login"; 
	  }
	  
	//회원가입 폼 보여주기
	@GetMapping(value="/joinForm")
	  public String join(){
		  	 return "member/join";
	 } 
	
	//회원가입 처리
	@PostMapping(value="/member/join")
	public String joinMember(MemberDto memberDto) {
		System.out.println("memberDto >> " + memberDto);
		memberService.insertMem(memberDto); 
		return "redirect:/loginForm";
	}
	

	
}
