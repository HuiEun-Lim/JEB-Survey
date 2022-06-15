package com.JEB.survey.service;

import com.JEB.survey.model.SurveyDto;

public interface RegSurvService {
	//회원정보(닉네임) 가져오기
	public String getUserInfo(String memId);
	//설문 만들기
	public void insertSurv(SurveyDto surveyDto);

}
