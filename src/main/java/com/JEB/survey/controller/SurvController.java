package com.JEB.survey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SurvController {

	@RequestMapping("/regSurv")
	public String regSurv() {		
		return "RegSurv";
	}

}
