package com.JEB.survey.repository;

import org.apache.ibatis.annotations.Mapper;

import com.JEB.survey.model.MemberDto;

@Mapper
public interface MemberMapper {
	
	//로그인
	int loginMem(MemberDto member);
	
	//회원가입
	void insertMem(MemberDto member);
	
	// 아이디에 따른 회원정보 
	MemberDto getUserInfo(String mem_id);
	
	//아이디 중복 체크
	public int checkId(String memId);
}
