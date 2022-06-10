package com.JEB.survey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JEB.survey.model.SurveyDto;
import com.JEB.survey.repository.SurvListMapper;

@Service
public class SurvListServiceImpl implements SurvListService{
	
	@Autowired
	private SurvListMapper survListMapper;
	
	@Override
	public List<SurveyDto> getSurvList() {
		return survListMapper.getSurvList();
	}

	@Override
	public int getListCnt() {
		return survListMapper.getListCnt();
	}

}
