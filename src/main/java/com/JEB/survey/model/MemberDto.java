package com.JEB.survey.model;

import lombok.Data;

@Data
public class MemberDto {
	//회원 아이디
	private String memId;	
	//비밀번호
	private String memPw;
	//닉네임
	private String memNick;
}
