package com.springfilterscourse.service;

import java.util.List;

import com.springfilterscourse.model.FilterModel;
import com.springfilterscourse.model.PageModel;

public interface IListService<T> {
	
	public List<T> list();
	
	public PageModel<T> list(FilterModel filter);
	
}
