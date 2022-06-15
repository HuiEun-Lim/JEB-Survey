package com.JEB.survey.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.JEB.survey.model.QustoptDto;
import com.JEB.survey.model.SurveyDto;
import com.JEB.survey.model.SurvqustDto;


@Mapper
public interface RegSurvMapper {
	//회원정보(닉네임) 가져오기
	public String getUserInfo(String memId);

	//설문만들기 Survey
	public void insertSurv(SurveyDto surveyDto);
	public void insertSurvqust(SurvqustDto survqustDto);
	public void insertQustopt(QustoptDto qustoptDto);
	
}