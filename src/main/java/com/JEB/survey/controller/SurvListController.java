package com.JEB.survey.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SurvListController {
	
	@RequestMapping(value = "/survList", method=RequestMethod.GET)
	public ModelAndView hello(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("survey/survList");
		
		return mv;
	}
	

}
