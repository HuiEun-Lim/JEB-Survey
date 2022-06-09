package com.JEB.survey.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ex_controller {

	@RequestMapping(value = "/hello", method=RequestMethod.GET)
	public ModelAndView hello(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("msg", "Hello Jetty");
		
		mv.setViewName("ex_list");
		
		return mv;
	}
	
	
}
