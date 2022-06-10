package com.JEB.survey.model;

import lombok.Data;

@Data
public class SearchVo extends Pagination {
	public SearchVo(int currentPage, int cntPerPage, int pageSize) {
		super(currentPage, cntPerPage, pageSize);
		
	}
	private String srchTyp;
	private String keyword;
}
