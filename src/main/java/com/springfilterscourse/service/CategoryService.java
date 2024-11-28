package com.springfilterscourse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.springfilterscourse.domain.Category;
import com.springfilterscourse.model.EqualFilterModel;
import com.springfilterscourse.model.FilterModel;
import com.springfilterscourse.model.InFilterModel;
import com.springfilterscourse.model.PageModel;
import com.springfilterscourse.repository.CategoryRepository;
import com.springfilterscourse.specification.BasicSpecification;

@Service
public class CategoryService implements IListService<Category> {

	@Autowired
	private CategoryRepository repository;

	@Override
	public List<Category> list() {
		List<Category> categories = repository.findAll();
		return categories;
	}

	@Override
	public PageModel<Category> list(FilterModel filter) {
		Pageable pageable = filter.toSpringPegeable();

		Specification<Category> spec = buildSpecification(filter);

		Page<Category> categoryPage = repository.findAll(spec, pageable);
		PageModel<Category> pm = new PageModel<>(categoryPage);

		return pm;
	}

	@Override
	public Specification<Category> buildSpecification(FilterModel filter) {
		BasicSpecification<Category> bs = new BasicSpecification<>(Category.class);
		Specification<Category> spec = Specification.where(null);

		List<EqualFilterModel> equalFilters = filter.getEqualFilters();
		List<InFilterModel> inFilters = filter.getInFilters();

		for (EqualFilterModel eq : equalFilters)
			spec = spec.and(bs.equal(eq));

		for (InFilterModel in : inFilters)
			spec = spec.and(bs.in(in));
		
		return spec;
	}

}
