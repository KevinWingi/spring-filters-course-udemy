package com.springfilterscourse.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter 
public class PageModel<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long totalElements;
	private Integer currentPage;
	private Integer totalPages;
	private Integer pageSize;
	private List<T> elements;
	
	public PageModel(Page<T> page) {
		this.elements = page.getContent();
		this.totalElements = page.getTotalElements();
		this.currentPage = page.getNumber();
		this.totalPages = page.getTotalPages();
		this.pageSize = page.getSize();
	}
	
}
