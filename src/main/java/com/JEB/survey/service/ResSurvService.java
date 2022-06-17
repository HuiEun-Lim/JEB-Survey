package com.JEB.survey.service;

import java.util.List;

import com.JEB.survey.model.AnswerDto;
import com.JEB.survey.model.SurveyDto;


public interface ResSurvService {
	
	//응답하기
	void insertAnswer(List<AnswerDto> answerList) ;
	
	// 접속 회원 설문 응답 여부 확인
	public int resSurvYn(SurveyDto surveyDto);
	
}
