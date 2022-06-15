package com.JEB.survey.service;

import com.JEB.survey.model.MemberDto;

public interface MemberService {
	//로그인
	int loginMem(MemberDto member);
		
	//회원가입
	void insertMem(MemberDto member);
	
	// 아이디에 따른 회원정보 
	MemberDto getUserInfo(String mem_id);
	
}
