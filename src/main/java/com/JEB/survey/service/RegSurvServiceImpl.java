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
	
	//설문만들기
	@Override
	public void insertSurv(SurveyDto surveyDto) {
		System.out.println("===insertSurv ServiceImpl START===");
		
		regSurvMapper.insertSurv(surveyDto);
		
		List<SurvqustDto> survqustList = surveyDto.getSurvqustList();
		System.out.println(surveyDto.getSurvqustList());
		if (survqustList.isEmpty()) {
			System.out.println("survqustList  EMPTY!!");
		} else {
			System.out.println("survqustList  NOT EMPTY!!");
			
			for(SurvqustDto vo : survqustList) {
				System.out.println("SurvQust for문 vo ==> "+ vo);
				regSurvMapper.insertSurvqust(vo);
				
				List<QustoptDto> qustoptList = vo.getQustoptList();
				if(qustoptList==null || qustoptList.isEmpty()) {
					System.out.println("qustoptList EMPTY!!");
				} else {
					System.out.println("qustoptList NOT EMPTY!!");
					
					for(QustoptDto qo : qustoptList) {
						System.out.println("QustOpt for문 qo ==> " + qo);
						regSurvMapper.insertQustopt(qo);
						System.out.println("QustOpt for문 END");
					}
				}
				System.out.println("SurvQust for문 END");
			}
			
		}
		
		System.out.println("===insertSurv ServiceImpl END===");
	}

}
