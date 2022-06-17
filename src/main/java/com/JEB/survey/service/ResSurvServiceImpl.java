package com.JEB.survey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JEB.survey.model.AnswerDto;
import com.JEB.survey.repository.ResSurvMapper;

@Service
public class ResSurvServiceImpl implements ResSurvService{
	
	@Autowired
	ResSurvMapper resSurvMapper;

	@Override
	public void insertAnswer(List<AnswerDto> answerList) {
		System.out.println("서비스에서 list"+answerList);

		for(AnswerDto answer: answerList) {
			System.out.println("서비스에서 하나씩"+answer);
			resSurvMapper.insertAnswer(answer);
		}
		
	}

}
