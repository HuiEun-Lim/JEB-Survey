package com.JEB.survey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JEB.survey.model.AnswerDto;
import com.JEB.survey.model.SearchVo;
import com.JEB.survey.model.SurveyDto;
import com.JEB.survey.model.SurvqustDto;
import com.JEB.survey.repository.SurvListMapper;

@Service
public class SurvListServiceImpl implements SurvListService{
	
	@Autowired
	private SurvListMapper survListMapper;
	
	@Override
	public List<SurveyDto> getSurvList(SearchVo searchVo) {
		return survListMapper.getSurvList(searchVo);
	}

	@Override
	public int getListCnt(SearchVo searchVo) {
		return survListMapper.getListCnt(searchVo);
	}

	@Override
	public List<SurveyDto> getMyList(SearchVo searchVo) {
		return survListMapper.getMyList(searchVo);
	}

	@Override
	public int getMyCnt(SearchVo searchVo) {
		return survListMapper.getMyCnt(searchVo);
	}

	@Override
	public SurveyDto getSurvRslt(int survNo) {
		SurveyDto surveyDto = survListMapper.getOneSurv(survNo);
		
		// 개행 마크업 변경하여 출력
		if(surveyDto.getSurvDesc() != null)
			surveyDto.setSurvDesc(surveyDto.getSurvDesc().replace("\n", "<br>"));
		
		// 설문 질문 리스트
		List<SurvqustDto> survqust = survListMapper.getSurvQust(survNo);
		
		// 설문 질문마다 응답한 결과 리스트 삽입
		for(SurvqustDto qust : survqust) {
			if("long".equals(qust.getQustType())) {
				List<AnswerDto> answer = survListMapper.getLongAnswer(qust.getQustNo());
				
				// 개행 마크업 변경하여 출력
				for(AnswerDto answ : answer) {
					answ.setAnswLong(answ.getAnswLong().replace("\n", "<br>"));
				}
				
				qust.setAnswerList(answer);
			} else {
				qust.setAnswerList(survListMapper.getAnswer(qust.getQustNo()));
			}
		}
		
		surveyDto.setSurvqustList(survqust);
		return surveyDto;
	}

}
