package com.JEB.survey.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.JEB.survey.model.SurveyDto;
import com.JEB.survey.service.SurvListService;

@Controller
public class SurvListController {
	
	@Autowired
	private SurvListService survListService;
	
	@RequestMapping(value = "/survList", method=RequestMethod.GET)
	public ModelAndView hello(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		List<SurveyDto> survList = survListService.getSurvList();
		
		if(!survList.isEmpty()) mv.addObject("survList", survList);
		else mv.addObject("survList", null);
		
		mv.setViewName("survey/survList");
		
		return mv;
	}
	

}
