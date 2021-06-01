package com.springfilterscourse.model;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static com.springfilterscourse.constant.ApiConstants.*;

import lombok.Getter;

@Getter
public class FilterModel {
	private Integer limit;
	private Integer page;
	
	public FilterModel(Map<String, String> params) {
		this.limit = params.containsKey(LIMIT_KEY) ? Integer.valueOf(params.get(LIMIT_KEY)) : DEFAULT_LIMIT;
		this.page = params.containsKey(PAGE_KEY) ? Integer.valueOf(params.get(PAGE_KEY)) : DEFAULT_PAGE;
	}
	
	public Pageable toSpringPegeable() {
		return PageRequest.of(page, limit);
	}
 	
}
