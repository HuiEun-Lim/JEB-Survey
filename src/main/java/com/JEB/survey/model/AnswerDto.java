package com.JEB.survey.model;

import java.util.Date;

import lombok.Data;

@Data
public class AnswerDto {
	// 응답 번호
	private int answNo;
	// 설문 질문 번호
	private int qustNo;
	// 응답한 회원 ID
	private String memId;
	// 응답 내용
	private String answCont;
	// 긴 응답 내용
	private String answLong;
	// 응답 날짜
	private Date answDate;
}
