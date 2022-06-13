package com.JEB.survey.repository;

import org.apache.ibatis.annotations.Mapper;

import com.JEB.survey.model.MemberDto;

@Mapper
public interface MemberMapper {
	
	//로그인
	int loginMem(MemberDto member);
	
	//회원가입
	void insertMem(MemberDto member);
}
