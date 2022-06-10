package com.JEB.survey.model;

import lombok.Data;

@Data
public class QustoptDto {
	//제공 답변 번호
	private int optNo;
	//설문질문번호
	private int qustNo;
	//답변내용
	private String optCont;
	//삭제여부
	private String delYn;
	//표기순서
	private int optSeq;
}
