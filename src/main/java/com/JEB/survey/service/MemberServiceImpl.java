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
	public int loginMem(MemberDto member) {
		
		
		//비밀번호 암호화
		
		
		return 0;
	}

	
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

}
