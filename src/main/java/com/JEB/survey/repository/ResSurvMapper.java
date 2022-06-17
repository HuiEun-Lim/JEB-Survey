package com.JEB.survey.repository;

import org.apache.ibatis.annotations.Mapper;

import com.JEB.survey.model.AnswerDto;

@Mapper
public interface ResSurvMapper {
	
	//응답하기
	void insertAnswer(AnswerDto answerDto) ;
	
	
}
