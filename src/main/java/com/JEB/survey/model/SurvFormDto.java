package com.JEB.survey.model;

import java.util.List;

import lombok.Data;

@Data
public class SurvFormDto {
	private SurveyDto surveyDto;
	private List<SurvqustDto> survqustDto;
	private List<List<QustoptDto>> qustoptDto;

}
