package com.JEB.survey.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.JEB.survey.model.AnswerDto;
import com.JEB.survey.model.SurveyDto;
import com.JEB.survey.model.SurvqustDto;
import com.JEB.survey.service.RegSurvService;
import com.JEB.survey.service.ResSurvService;

@Controller
public class SurvController {
	
	@Autowired
	private RegSurvService regSurvService;
	
	@Autowired
	private ResSurvService resSurvService;
	
	/*
	 * 설문 만들기 View
	 */
	@RequestMapping("/regSurvForm")
	public String regSurvForm(@AuthenticationPrincipal UserDetails userInfo, Model model) {
		String memNick = regSurvService.getUserInfo(userInfo.getUsername());
		System.out.println("==memNick ==> "+memNick);
		model.addAttribute("memNick",memNick);
		return "survey/regSurv";
	}

	/*
	 * 설문 만들기 
	*/
	@RequestMapping("/regSurv")
	@ResponseBody
	public String regSurv(@RequestBody SurveyDto surveyDto
			, @AuthenticationPrincipal UserDetails userInfo) throws Exception {	
		System.out.println("===regSurv Controller START");
		
		surveyDto.setRegId(userInfo.getUsername());
		System.out.println("surveyDto ==> "+surveyDto.toString());
		regSurvService.insertSurv(surveyDto);
		
		System.out.println("===regSurv Controller END===");
		return "survey/regSurv";
	}
	
	/*
	 * My Survey > 설문 수정하기 View
	 */
	@RequestMapping("/modSurvForm")
	public String modSurv(@RequestParam(value="survNo") Integer survNo
						 , @AuthenticationPrincipal UserDetails userInfo
						 , Model model) {
		System.out.println("===modSurv Controller START===");
		String memNick = regSurvService.getUserInfo(userInfo.getUsername());
		
		System.out.println("수정할 survNo : "+survNo+" memNick :"+memNick);
		
		SurveyDto surveyDto = regSurvService.getSurvey(survNo);
		System.out.println("뿌려줄 surveyDto ==> "+surveyDto.toString());
		
		surveyDto.setMemNick(memNick);
		
		model.addAttribute("surveyDto",surveyDto);
		
		System.out.println("===modSurv Controller END===");
		return "survey/modSurv";
	}
	
	/*
	 * 설문 삭제하기
	 */
	@RequestMapping("/delSurv")
	public String delSurv(@AuthenticationPrincipal UserDetails userInfo
						  ,@RequestParam Map<String,String> param) {
		System.out.println("delSurv Controller START");
		
		int survNo = Integer.parseInt(param.get("survNo"));
		
		String memNick = regSurvService.getUserInfo(userInfo.getUsername());
		System.out.println("survNo : "+survNo+"  memNick : "+memNick);

		regSurvService.delOneSurvey(survNo);
		
		return "redirect:myList";
	}
	
	/*
	 * 설문 수정하기
	 */
	@RequestMapping("/updateSurv")
	@ResponseBody
	public void updateSurv(@AuthenticationPrincipal UserDetails userInfo
						  ,@RequestBody SurveyDto surveyDto) {
		System.out.println("updateSurv controller START");
		surveyDto.setRegId(userInfo.getUsername());
		
		int survNo = surveyDto.getSurvNo();
		regSurvService.delOldSurvey(survNo);
		regSurvService.insertNewSurv(surveyDto);
		
		System.out.println("updateSurv controller END");
	}
	
	/*
	 * 작성자 : Bonnie 
	 * 리스트 응답 폼 보여주기 View
	 * */
	@RequestMapping("/resForm")
	public String resForm(@RequestParam(value="survNo") Integer survNo
			, @AuthenticationPrincipal UserDetails userInfo,
			HttpServletRequest request , Model model) {
		SurveyDto surveyDto = regSurvService.getSurvey(survNo);
		String memNick = regSurvService.getUserInfo(surveyDto.getRegId());
		
		surveyDto.setMemNick(memNick);
		if(surveyDto.getSurvDesc() != null) {
			surveyDto.setSurvDesc(surveyDto.getSurvDesc().replace("\n","<br>"));
		}
		
		model.addAttribute("surveyDto",surveyDto);
		model.addAttribute("memId", userInfo.getUsername());
		model.addAttribute("currentPage",request.getParameter("currentPage"));
		model.addAttribute("cntPerPage",request.getParameter("cntPerPage"));
		model.addAttribute("pageSize",request.getParameter("pageSize"));
		model.addAttribute("srchTyp",request.getParameter("srchTyp"));
		model.addAttribute("keyword",request.getParameter("keyword"));
		
		return "survey/resSurv";
	}
	
	/*
	 * 작성자 : Bonnie 
	 * 응답하기 
	 * */ 
	
	@RequestMapping("/resSurv")
	@ResponseBody
	public String resSurv(@RequestBody SurvqustDto qustDto) {	
		List<AnswerDto> answerList = qustDto.getAnswerList();
		
		System.out.println(answerList);
		
		resSurvService.insertAnswer(answerList);
		
		return "redirect:/survList";
	}
	
	
}
