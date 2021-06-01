package com.springfilterscourse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.springfilterscourse.domain.Product;
import com.springfilterscourse.model.FilterModel;
import com.springfilterscourse.model.PageModel;
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
	
	@Override
	public PageModel<Product> list(FilterModel filter) {
		Page<Product> productPage = repository.findAll(filter.toSpringPegeable());
		PageModel<Product> pm = new PageModel<>(productPage);
		
		return pm;
	}

}
