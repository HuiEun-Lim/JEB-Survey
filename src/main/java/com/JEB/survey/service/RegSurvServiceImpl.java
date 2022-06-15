package com.JEB.survey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JEB.survey.model.QustoptDto;
import com.JEB.survey.model.SurveyDto;
import com.JEB.survey.model.SurvqustDto;
import com.JEB.survey.repository.RegSurvMapper;

@Service
public class RegSurvServiceImpl implements RegSurvService {
	
	@Autowired
	RegSurvMapper regSurvMapper;
	
	@Override
	public String getUserInfo(String memId) {
		
		return regSurvMapper.getUserInfo(memId);
	}
	
	//설문만들기
	@Override
	public void insertSurv(SurveyDto surveyDto) {
		System.out.println("===insertSurv ServiceImpl START===");
		
		regSurvMapper.insertSurv(surveyDto);
		
		List<SurvqustDto> survqustList = surveyDto.getSurvqustList();
		
		
		if (survqustList.isEmpty()) {
			System.out.println("survqustList  EMPTY!!");
		} else {
			System.out.println("survqustList  NOT EMPTY!!");
			
			for(SurvqustDto vo : survqustList) {
				regSurvMapper.insertSurvqust(vo);
				
				List<QustoptDto> qustoptList = vo.getQustoptList();
				
				if(qustoptList==null || qustoptList.isEmpty()) {
					System.out.println("qustoptList EMPTY!!");
				} else {
					System.out.println("qustoptList NOT EMPTY!!");
					
					for(QustoptDto qo : qustoptList) {
						regSurvMapper.insertQustopt(qo);
					}
				}
				
			}
			
		}
		
		System.out.println("===insertSurv ServiceImpl END===");
	}

}