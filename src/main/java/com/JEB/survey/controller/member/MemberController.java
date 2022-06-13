package com.JEB.survey.controller.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.JEB.survey.model.MemberDto;
import com.JEB.survey.service.MemberService;

//회원 컨트롤러
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	//로그인 폼 보여주기
	@GetMapping(value="/loginForm")
	  public String login(){
		  	 return "member/login";
	 }
	
	//로그인 처리
	@PostMapping(value="/member/login")
	public String loginMember(MemberDto memberDto) {
		System.out.println("컨틀롤러에서 로그인"+memberDto);
		return "redirect:/main";
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
		return "redirect:/main";
	}
	
	
}
