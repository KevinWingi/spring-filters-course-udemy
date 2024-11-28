package com.springfilterscourse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.springfilterscourse.domain.Supplier;
import com.springfilterscourse.model.EqualFilterModel;
import com.springfilterscourse.model.FilterModel;
import com.springfilterscourse.model.InFilterModel;
import com.springfilterscourse.model.PageModel;
import com.springfilterscourse.repository.SupplierRepository;
import com.springfilterscourse.specification.BasicSpecification;

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

		Specification<Supplier> spec = buildSpecification(filter);

		Page<Supplier> supplierPage = repository.findAll(spec, pageable);
		PageModel<Supplier> pm = new PageModel<>(supplierPage);

		return pm;
	}

	@Override
	public Specification<Supplier> buildSpecification(FilterModel filter) {
		BasicSpecification<Supplier> bs = new BasicSpecification<>(Supplier.class);
		Specification<Supplier> spec = Specification.where(null);

		List<EqualFilterModel> equalFilters = filter.getEqualFilters();
		List<InFilterModel> inFilters = filter.getInFilters();

		for (EqualFilterModel eq : equalFilters)
			spec = spec.and(bs.equal(eq));

		for (InFilterModel in : inFilters)
			spec = spec.and(bs.in(in));
		
		return spec;
	}

}
