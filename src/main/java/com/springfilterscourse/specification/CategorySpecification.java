package com.springfilterscourse.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.springfilterscourse.builder.ExpressionBuilder;
import com.springfilterscourse.domain.Category;
import com.springfilterscourse.model.EqualFilterModel;
import com.springfilterscourse.model.InFilterModel;

public class CategorySpecification {
	public static Specification<Category> equal(EqualFilterModel eq) {
		return new Specification<Category>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				ExpressionBuilder<Category> expBuilder = new ExpressionBuilder<>(Category.class);
				Expression<Category> expression = expBuilder.get(root, eq.getColumn());

				Predicate predicate = null;

				if (expression != null) {
					predicate = (eq.getIsEqual() ? cb.equal(expression, eq.getValue())
							: cb.notEqual(expression, eq.getValue()));
				}

				return predicate;
			}
		};
	}

	public static Specification<Category> in(InFilterModel inf) {
		return new Specification<Category>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				ExpressionBuilder<Category> expBuilder = new ExpressionBuilder<>(Category.class);
				Expression<Category> expression = expBuilder.get(root, inf.getColumn());

				Predicate predicate = null;

				if (expression != null) {
					predicate = inf.getIsIn() ? expression.as(String.class).in(inf.getValues())
							: expression.as(String.class).in(inf.getValues()).not();
				}

				return predicate;
			}
		};
	}
}
