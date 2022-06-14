package com.JEB.survey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.JEB.survey.model.SurvFormDto;
import com.JEB.survey.model.SurveyDto;
import com.JEB.survey.model.SurvqustDto;

@Controller
public class SurvController {
	
	@RequestMapping("/regSurvForm")
	public String regSurvForm() {
		return "RegSurv";
	}

	@RequestMapping("/regSurv")
	@ResponseBody
	public String regSurv(@RequestBody SurvFormDto survFormDto) throws Exception {	
		System.out.println("===regSurv Controller START");
		
		
		System.out.println(" --->survFormDto.getSurveyDto()  : "+survFormDto.getSurveyDto().toString());
		System.out.println(" --->survFormDto.getSurvqustDto()  : "+survFormDto.getSurvqustDto().toString());
		System.out.println(" --->survFormDto.getQustoptDto()  : "+survFormDto.getQustoptDto().toString());

		System.out.println("===regSurv Controller END");
		return "RegSurv";
	}

}
