package com.JEB.survey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.JEB.survey.model.SurveyDto;
import com.JEB.survey.model.SurvqustDto;
import com.JEB.survey.service.RegSurvService;
import com.JEB.survey.service.RegSurvServiceImpl;

@Controller
public class SurvController {
	
	@Autowired
	private RegSurvService regSurvService;
	
	@RequestMapping("/regSurvForm")
	public String regSurvForm() {
		return "RegSurv";
	}

	@RequestMapping("/regSurv")
	@ResponseBody
	public String regSurv(@RequestBody SurveyDto surveyDto) throws Exception {	
		System.out.println("===regSurv Controller START");
		
		System.out.println("surveyDto ==> "+surveyDto.toString());
		regSurvService.insertSurv(surveyDto);
		
		System.out.println("===regSurv Controller END===");
		return "RegSurv";
	}

}
