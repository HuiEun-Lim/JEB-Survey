package com.JEB.survey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.JEB.survey.model.MemberDto;
import com.JEB.survey.repository.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public void insertMem(MemberDto member) {
		MemberDto memberDto = new MemberDto();
		memberDto.setMemId( member.getMemId());
		memberDto.setMemPw(member.getMemPw());
		memberDto.setMemNick(member.getMemNick());
		
		System.out.println("서비스에서 나와"+member);
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setMemPw(passwordEncoder.encode(member.getMemPw()));
		
		memberMapper.insertMem(member);
		
		System.out.println("서비스에서 암호화된 비밀번호야"+member.getMemPw());
		System.out.println("서비스에서 암호화하구 나와"+member);
	}


	@Override
	public MemberDto getUserInfo(String mem_id) {
		MemberDto user = memberMapper.getUserInfo(mem_id);
		System.out.println("로그인한 친구 정보야"+user);
		return user;
	}


	@Override
	public int checkId(String memId) {
		int cnt = memberMapper.checkId(memId);
		System.out.println("serviceImpl >> " + cnt);
		return cnt;
	}
}
