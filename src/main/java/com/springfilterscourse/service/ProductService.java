package com.springfilterscourse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springfilterscourse.domain.Product;
import com.springfilterscourse.repository.ProductRepository;

@Service
public class ProductService implements IListService<Product> {
	
	@Autowired
	private ProductRepository repository;

	@Override
	public List<Product> list() {
		List<Product> products = repository.findAll();
		return products;
	}

}
