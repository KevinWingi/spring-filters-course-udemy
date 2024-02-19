package com.springfilterscourse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.springfilterscourse.domain.Product;
import com.springfilterscourse.model.DateFilterModel;
import com.springfilterscourse.model.EqualFilterModel;
import com.springfilterscourse.model.FilterModel;
import com.springfilterscourse.model.InFilterModel;
import com.springfilterscourse.model.PageModel;
import com.springfilterscourse.repository.ProductRepository;
import com.springfilterscourse.specification.ProductSpecification;

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
		Pageable pageable = filter.toSpringPegeable();

		Specification<Product> spec = buildSpecification(filter);

		Page<Product> productPage = repository.findAll(spec, pageable);
		PageModel<Product> pm = new PageModel<>(productPage);

		return pm;
	}

	@Override
	public Specification<Product> buildSpecification(FilterModel filter) {
		Specification<Product> spec = Specification.where(null);

		List<EqualFilterModel> equalFilters = filter.getEqualFilters();
		List<InFilterModel> inFilters = filter.getInFilters();
		List<DateFilterModel> dateFilters = filter.getDateFilters();

		for (EqualFilterModel eq : equalFilters)
			spec = spec.and(ProductSpecification.equal(eq));

		for (InFilterModel in : inFilters)
			spec = spec.and(ProductSpecification.in(in));
		
		for (DateFilterModel df : dateFilters)
			spec = spec.and(ProductSpecification.dateBetween(df));
		
		return spec;
	}

}
