package com.JEB.survey.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.JEB.survey.model.SearchVo;
import com.JEB.survey.model.SurveyDto;
import com.JEB.survey.service.ResSurvService;
import com.JEB.survey.service.SurvListService;

@Controller
public class SurvListController {
	
	@Autowired
	private SurvListService survListService;
	
	@Autowired
	private ResSurvService resSurvService;
	
	// 설문 리스트 접속
	@RequestMapping(value = "/survList", method=RequestMethod.GET)
	public ModelAndView survList(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
            @RequestParam(value = "cntPerPage", required = false, defaultValue = "10") int cntPerPage,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @AuthenticationPrincipal  UserDetails userInfo,
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
		
		mv.addObject("memId", userInfo.getUsername());
		
		mv.setViewName("survey/survList");
		
		return mv;
	}
	
	// My Survey 접속
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
	
	// 설문 결과 접속
	@RequestMapping("/survRslt")
	public ModelAndView survRslt(@RequestParam(value = "survNo") int survNo,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
            @RequestParam(value = "cntPerPage", required = false, defaultValue = "10") int cntPerPage,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            HttpServletRequest request) {	
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("survey/survRslt");
		
		SearchVo searchVo = new SearchVo(currentPage, cntPerPage, pageSize);
		searchVo.setKeyword(request.getParameter("keyword"));
		searchVo.setSrchTyp(request.getParameter("srchTyp"));
		mv.addObject("pagination", searchVo);
		
		SurveyDto surveyDto = survListService.getSurvRslt(survNo);
		
		mv.addObject("survey", surveyDto);
		
		return mv;
	}
	
	// 해당 설문 응답 여부 확인
		@PostMapping(value="/checkRes")
		@ResponseBody
		public int checkRes(SurveyDto surveyDto) { 
			System.out.println(surveyDto);
			int cnt = resSurvService.resSurvYn(surveyDto);
			return cnt;
		}

}
