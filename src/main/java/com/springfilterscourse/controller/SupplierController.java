package com.springfilterscourse.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springfilterscourse.domain.Supplier;
import com.springfilterscourse.model.FilterModel;
import com.springfilterscourse.model.PageModel;
import com.springfilterscourse.service.SupplierService;

@RestController
@RequestMapping(value = "suppliers")
public class SupplierController {

	@Autowired
	private SupplierService service;
	
	@GetMapping
	public ResponseEntity<PageModel<Supplier>> list(@RequestParam Map<String, String> params) {
		FilterModel filter = new FilterModel(params);
		PageModel<Supplier> pm = service.list(filter);
		return ResponseEntity.ok(pm);
	}
	
}
