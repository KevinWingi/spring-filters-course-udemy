package com.springfilterscourse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.springfilterscourse.domain.Category;
import com.springfilterscourse.model.FilterModel;
import com.springfilterscourse.model.PageModel;
import com.springfilterscourse.repository.CategoryRepository;

@Service
public class CategoryService implements IListService<Category> {

	@Autowired
	private CategoryRepository repository;
	
	@Override
	public List<Category> list() {
		List<Category> categories = repository.findAll();
		return categories;
	}
	
	@Override
	public PageModel<Category> list(FilterModel filter) {
		Page<Category> categoryPage = repository.findAll(filter.toSpringPegeable());
		PageModel<Category> pm = new PageModel<>(categoryPage);
		
		return pm;
	}

}
