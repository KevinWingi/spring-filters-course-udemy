package com.springfilterscourse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springfilterscourse.domain.Category;
import com.springfilterscourse.service.CategoryService;

@RestController
@RequestMapping(value = "categories")
public class CategoryController {

	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<Category>> list() {
		List<Category> categories = service.list();
		return ResponseEntity.ok(categories);
	}
	
}
