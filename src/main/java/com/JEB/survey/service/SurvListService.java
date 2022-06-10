package com.JEB.survey.service;

import java.util.List;

import com.JEB.survey.model.SearchVo;
import com.JEB.survey.model.SurveyDto;

public interface SurvListService {
	public List<SurveyDto> getSurvList(SearchVo searchVo);
	public int getListCnt(SearchVo searchVo);
}
