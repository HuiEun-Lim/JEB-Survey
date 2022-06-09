package com.JEB.survey.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {
	 @RequestMapping("/")
	  public String home(ModelAndView mav) {
	    return "/member/home";  
	  }
}
