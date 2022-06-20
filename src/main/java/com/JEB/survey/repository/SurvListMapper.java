package com.JEB.survey.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.JEB.survey.model.AnswerDto;
import com.JEB.survey.model.SearchVo;
import com.JEB.survey.model.SurveyDto;
import com.JEB.survey.model.SurvqustDto;

@Mapper
public interface SurvListMapper {
	public List<SurveyDto> getSurvList(SearchVo searchVo);
	
	public int getListCnt(SearchVo searchVo);
	
	public List<SurveyDto> getMyList(SearchVo searchVo);
	
	public int getMyCnt(SearchVo searchVo);
	
	public SurveyDto getOneSurv(int survNo);
	
	public List<SurvqustDto> getSurvQust(int survNo);
	
	public List<AnswerDto> getAnswer(int qustNo);
	
	public List<AnswerDto> getLongAnswer(int qustNo);
	
	public String getRegNick(SurveyDto surveyDto);

}
