package com.JEB.survey.service;

import java.util.List;

import com.JEB.survey.model.AnswerDto;


public interface ResSurvService {
	
	//응답하기
	void insertAnswer(List<AnswerDto> answerList) ;
	
}
