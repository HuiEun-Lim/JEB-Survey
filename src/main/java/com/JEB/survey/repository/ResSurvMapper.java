package com.JEB.survey.repository;

import org.apache.ibatis.annotations.Mapper;

import com.JEB.survey.model.AnswerDto;
import com.JEB.survey.model.SurveyDto;

@Mapper
public interface ResSurvMapper {
	
	//응답하기
	void insertAnswer(AnswerDto answerDto) ;
	
	// 접속 회원 설문 응답 여부 확인
	public int resSurvYn(SurveyDto surveyDto);
	
	
}
