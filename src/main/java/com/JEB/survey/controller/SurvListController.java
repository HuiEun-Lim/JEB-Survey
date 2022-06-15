package com.JEB.survey.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.JEB.survey.model.SearchVo;
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
            HttpServletRequest request) throws UnsupportedEncodingException {
		ModelAndView mv = new ModelAndView();
		
		SearchVo searchVo = new SearchVo(currentPage, cntPerPage, pageSize);
		
		request.setCharacterEncoding("utf-8");
	    String keyword = request.getParameter("keyword");
	    
		if(keyword != null) {
			searchVo.setKeyword(keyword);
			searchVo.setSrchTyp(request.getParameter("srchTyp"));
		}
		
		int total = survListService.getListCnt(searchVo);
		
		mv.addObject("total", total);
		
		searchVo.setTotalRecordCount(total);
		
		if(total != 0) {
			mv.addObject("survList", survListService.getSurvList(searchVo));
		}
		else {
			mv.addObject("survList", null);
		}
		mv.addObject("pagination", searchVo);
		
		mv.setViewName("survey/survList");
		
		return mv;
	}
	
	@RequestMapping(value = "/myList", method=RequestMethod.GET)
	public ModelAndView myList(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
            @RequestParam(value = "cntPerPage", required = false, defaultValue = "10") int cntPerPage,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @AuthenticationPrincipal  UserDetails userInfo,
            HttpServletRequest request) throws UnsupportedEncodingException {
		ModelAndView mv = new ModelAndView();
		
		SearchVo searchVo = new SearchVo(currentPage, cntPerPage, pageSize);
		
		// Id 세팅
		searchVo.setRegId(userInfo.getUsername());
		
		request.setCharacterEncoding("utf-8");
	    
		searchVo.setKeyword(request.getParameter("keyword"));
		searchVo.setSrchTyp(request.getParameter("srchTyp"));
		
		int total = survListService.getMyCnt(searchVo);
		
		mv.addObject("total", total);
		
		searchVo.setTotalRecordCount(total);
		
		if(total != 0) {
			mv.addObject("survList", survListService.getMyList(searchVo));
		}
		else {
			mv.addObject("survList", null);
		}
		mv.addObject("pagination", searchVo);
		
		mv.setViewName("survey/myList");
		
		return mv;
	}
	
	@RequestMapping("/survRslt")
	public ModelAndView survRslt(@RequestParam(value = "survNo") int survNo) {	
		ModelAndView mv = new ModelAndView();
		mv.setViewName("survey/survRslt");
		
		mv.addObject("survNo", survNo);
		
		return mv;
	}
	

}
