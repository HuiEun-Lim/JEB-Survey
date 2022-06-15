package com.JEB.survey.model;

import java.util.List;

import lombok.Data;

@Data
public class SurvqustDto {
	//설문질문번호
	private int qustNo;
	//설문조사번호
	private int survNo;
	//질문내용
	private String qustCont;
	//질문유형
	private String qustType;
	//표기순서
	private int qustSeq;
	//옵션내용
	private List<QustoptDto> qustoptList;
	// 응답 리스트
	private List<AnswerDto> answerList;
}
