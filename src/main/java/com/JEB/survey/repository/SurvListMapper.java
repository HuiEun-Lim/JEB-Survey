package com.JEB.survey.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.JEB.survey.model.SurveyDto;

@Mapper
public interface SurvListMapper {
	public List<SurveyDto> getSurvList();
	public int getListCnt();

}
