package com.JEB.survey.model;

import java.util.Date;

import lombok.Data;

@Data
public class BaseVo {
	// 등록자 ID
	private String regId;
	// 회원 닉네임
	private String memNick;
	// 등록 날짜
	private Date regDate;
	// 최종 수정 날짜
	private Date modDate;
}
