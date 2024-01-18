package com.springfilterscourse.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springfilterscourse.domain.Product;
import com.springfilterscourse.model.FilterModel;
import com.springfilterscourse.model.PageModel;
import com.springfilterscourse.service.ProductService;

@RestController
@RequestMapping(value = "products")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping
	public ResponseEntity<PageModel<Product>> list(@RequestParam Map<String, String> params) {
		FilterModel filter = new FilterModel(params);
		PageModel<Product> pm = service.list(filter);
		return ResponseEntity.ok(pm);
	}
	
}
