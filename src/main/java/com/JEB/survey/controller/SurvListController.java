package com.JEB.survey.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.JEB.survey.model.Pagination;
import com.JEB.survey.service.SurvListService;

@Controller
public class SurvListController {
	
	@Autowired
	private SurvListService survListService;
	
	@RequestMapping(value = "/survList", method=RequestMethod.GET)
	public ModelAndView survList(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
            @RequestParam(value = "cntPerPage", required = false, defaultValue = "10") int cntPerPage,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		int total = survListService.getListCnt();
		
		mv.addObject("total", total);
		
		Pagination pagination = new Pagination(currentPage, cntPerPage, pageSize);
        pagination.setTotalRecordCount(total);
		
		if(total != 0) {
			mv.addObject("survList", survListService.getSurvList(pagination));
			mv.addObject("pagination", pagination);
		}
		else {
			mv.addObject("survList", null);
			mv.addObject("pagination", null);
		}
		
		mv.setViewName("survey/survList");
		
		return mv;
	}
	

}
