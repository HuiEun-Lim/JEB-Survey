package com.JEB.survey.model;

import lombok.Data;

@Data
public class SearchVo extends Pagination {
	public SearchVo(int currentPage, int cntPerPage, int pageSize) {
		super(currentPage, cntPerPage, pageSize);
		
	}
	// 검색 타입
	private String srchTyp;
	// 검색 키워드
	private String keyword;
	// 작성자ID (My Survey에서 사용)
	private String regId;
}
