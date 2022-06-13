package com.JEB.survey.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.JEB.survey.model.SearchVo;
import com.JEB.survey.model.SurveyDto;

@Mapper
public interface SurvListMapper {
	public List<SurveyDto> getSurvList(SearchVo searchVo);
	
	public int getListCnt(SearchVo searchVo);
	
	public List<SurveyDto> getMyList(SearchVo searchVo);
	
	public int getMyCnt(SearchVo searchVo);

}
