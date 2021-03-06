package com.JEB.survey.model;

import java.util.List;

import lombok.Data;

@Data
public class SurveyDto extends BaseVo {
	// 설문 조사 번호
	private int survNo;
	// 설문 제목
	private String survTitle;
	// 설문 설명
	private String survDesc;
	// 사용 여부
	private String useYn;
	// 삭제 여부
	private String delYn;
	// 질문내용
	private List<SurvqustDto> survqustList;
}
