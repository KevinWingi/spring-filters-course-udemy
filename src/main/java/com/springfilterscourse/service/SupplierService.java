package com.springfilterscourse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.springfilterscourse.domain.Supplier;
import com.springfilterscourse.model.FilterModel;
import com.springfilterscourse.model.PageModel;
import com.springfilterscourse.repository.SupplierRepository;

@Service
public class SupplierService implements IListService<Supplier> {

	@Autowired
	private SupplierRepository repository;
	
	@Override
	public List<Supplier> list() {
		List<Supplier> suppliers = repository.findAll();
		return suppliers;
	}
	
	@Override
	public PageModel<Supplier> list(FilterModel filter) {
		Page<Supplier> supplierPage = repository.findAll(filter.toSpringPegeable());
		PageModel<Supplier> pm = new PageModel<>(supplierPage);
		
		return pm;
	}

}
