package com.springfilterscourse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.springfilterscourse.domain.Category;
import com.springfilterscourse.domain.Supplier;
import com.springfilterscourse.model.EqualFilterModel;
import com.springfilterscourse.model.FilterModel;
import com.springfilterscourse.model.PageModel;
import com.springfilterscourse.repository.SupplierRepository;
import com.springfilterscourse.specification.CategorySpecification;
import com.springfilterscourse.specification.SupplierSpecification;

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
		Pageable pageable = filter.toSpringPegeable();

		Specification<Supplier> spec = null;

		List<EqualFilterModel> equalFilters = filter.getEqualFilters();

		if (!equalFilters.isEmpty()) {
			EqualFilterModel firstEqf = equalFilters.get(0);
			spec = SupplierSpecification.equal(firstEqf);

			for (int i = 1; i < equalFilters.size(); i++)
				spec = spec.and(SupplierSpecification.equal(equalFilters.get(i)));
		}
		
		Page<Supplier> supplierPage = repository.findAll(spec, pageable);
		PageModel<Supplier> pm = new PageModel<>(supplierPage);
		
		return pm;
	}

}
