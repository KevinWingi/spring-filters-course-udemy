package com.springfilterscourse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springfilterscourse.domain.Product;
import com.springfilterscourse.service.ProductService;

@RestController
@RequestMapping(value = "products")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping
	public ResponseEntity<List<Product>> list() {
		List<Product> products = service.list();
		return ResponseEntity.ok(products);
	}
	
}
