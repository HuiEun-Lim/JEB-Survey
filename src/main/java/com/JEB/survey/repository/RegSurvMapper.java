package com.JEB.survey.repository;

import java.util.List;

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
	
	//설문수정시 기존정보 가져오기
	public SurveyDto getSurvey(int survNo);
	public List<SurvqustDto> getSurvqustList(int survNo);
	public List<QustoptDto> getQustoptList(int qustNo);

	//설문 삭제하기
	public void delOneSurvey(int survNo);
	
	//설문 수정 전 내용 delete
	public void delSurvqust(int survNo);
	public void delQustopt(int survNo);
	
	//설문 수정 후 insert
	public void updateNewSurv(SurveyDto surveyDto);
	public void insertNewSurvqust(SurvqustDto survqustDto);
	
	
	
	
}
