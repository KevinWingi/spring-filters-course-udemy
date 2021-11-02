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
import com.springfilterscourse.model.PageModel;
import com.springfilterscourse.repository.CategoryRepository;
import com.springfilterscourse.specification.CategorySpecification;

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

		Specification<Category> spec = null;

		List<EqualFilterModel> equalFilters = filter.getEqualFilters();

		if (!equalFilters.isEmpty()) {
			EqualFilterModel firstEqf = equalFilters.get(0);
			spec = CategorySpecification.equal(firstEqf);

			for (int i = 1; i < equalFilters.size(); i++)
				spec = spec.and(CategorySpecification.equal(equalFilters.get(i)));
		}

		Page<Category> categoryPage = repository.findAll(spec, pageable);
		PageModel<Category> pm = new PageModel<>(categoryPage);

		return pm;
	}

}
