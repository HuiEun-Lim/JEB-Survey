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

	@Override
	public SurveyDto getSurvey(int survNo) {
		System.out.println("===getSurvey ServiceImpl START===");
		
		System.out.println("survNo : "+survNo);
		SurveyDto surveyDto = regSurvMapper.getSurvey(survNo);
		
		List<SurvqustDto> survqustList = regSurvMapper.getSurvqustList(survNo);
		surveyDto.setSurvqustList(survqustList);
	
		
		for(SurvqustDto dto:survqustList) {
			System.out.println("SurvqustDto ==>  "+dto);
			
			int qustNo = dto.getQustNo();
			List<QustoptDto> qustoptList = regSurvMapper.getQustoptList(qustNo);
			System.out.println("qustoptList ==> "+qustoptList);
			
			for(QustoptDto vo:qustoptList) {		
				System.out.println("qustoptDto ==>  "+vo);
			}
			
			dto.setQustoptList(qustoptList);
		}
		
		System.out.println("===getSurvey ServiceImpl END===");
		
		return surveyDto;
	}

	@Override
	public void delOneSurvey(int survNo) {
		System.out.println("delSurvey Service START");
		
		regSurvMapper.delOneSurvey(survNo);
		
		System.out.println("delSurvey Service END");
		
	}

	@Override
	public void delOldSurvey(int survNo) {
		System.out.println("delOldSurv Service START");
		
		regSurvMapper.delQustopt(survNo);
		regSurvMapper.delSurvqust(survNo);
				
		System.out.println("delOldSurv Service END");
		
	}

	@Override
	public void insertNewSurv(SurveyDto surveyDto) {
		System.out.println("===insertNewSurv ServiceImpl START===");
		
		regSurvMapper.updateNewSurv(surveyDto);
		System.out.println("surveyDto update완료!!");
		System.out.println("=> "+surveyDto.toString());
		
		List<SurvqustDto> survqustList = surveyDto.getSurvqustList();
		
		if (survqustList.isEmpty()) {
			System.out.println("survqustList  EMPTY!!");
		} else {
			System.out.println("survqustList  NOT EMPTY!!");
			
			for(SurvqustDto vo : survqustList) {
				vo.setSurvNo(surveyDto.getSurvNo());
				System.out.println("새로 insert할 SurvqustDto vo => "+vo);
				regSurvMapper.insertNewSurvqust(vo);
				System.out.println("새로 insert할 SurvqustDto vo insert 완료!!");
				
				List<QustoptDto> qustoptList = vo.getQustoptList();
				
				if(qustoptList==null || qustoptList.isEmpty()) {
					System.out.println("qustoptList EMPTY!!");
				} else {
					System.out.println("qustoptList NOT EMPTY!!");
					
					for(QustoptDto qo : qustoptList) {
						System.out.println("새로 insert할 QustoptDto qo => "+qo);
						regSurvMapper.insertQustopt(qo);
						System.out.println("새로 insert할 QustoptDto qo insert 완료!!");
					}
				}
				
			}
			
		}
		
		System.out.println("===insertNewSurv ServiceImpl END===");
		
	}


}
