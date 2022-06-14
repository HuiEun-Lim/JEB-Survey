package com.JEB.survey.service;

import com.JEB.survey.model.MemberDto;

public interface MemberService {
	//로그인
	int loginMem(MemberDto member);
		
	//회원가입
	void insertMem(MemberDto member);
	
}
