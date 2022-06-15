package com.JEB.survey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String regSurvForm(@AuthenticationPrincipal UserDetails userInfo, Model model) {
		String memNick = regSurvService.getUserInfo(userInfo.getUsername());
		System.out.println("==memNick ==> "+memNick);
		model.addAttribute("memNick",memNick);
		return "RegSurv";
	}

	@RequestMapping("/regSurv")
	@ResponseBody
	public String regSurv(@RequestBody SurveyDto surveyDto
			, @AuthenticationPrincipal UserDetails userInfo) throws Exception {	
		System.out.println("===regSurv Controller START");
		
		surveyDto.setRegId(userInfo.getUsername());
		System.out.println("surveyDto ==> "+surveyDto.toString());
		regSurvService.insertSurv(surveyDto);
		
		System.out.println("===regSurv Controller END===");
		return "RegSurv";
	}

}
