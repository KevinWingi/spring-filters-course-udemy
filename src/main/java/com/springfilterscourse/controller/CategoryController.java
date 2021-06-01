package com.springfilterscourse.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springfilterscourse.domain.Category;
import com.springfilterscourse.model.FilterModel;
import com.springfilterscourse.model.PageModel;
import com.springfilterscourse.service.CategoryService;

@RestController
@RequestMapping(value = "categories")
public class CategoryController {

	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<PageModel<Category>> list(@RequestParam Map<String, String> params) {
		FilterModel filter = new FilterModel(params);
		PageModel<Category> pm = service.list(filter);
		return ResponseEntity.ok(pm);
	}
	
}
