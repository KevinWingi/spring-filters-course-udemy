package com.springfilterscourse.specification;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.springfilterscourse.builder.ExpressionBuilder;
import com.springfilterscourse.constant.ApiConstants;
import com.springfilterscourse.model.DateFilterModel;
import com.springfilterscourse.model.EqualFilterModel;
import com.springfilterscourse.model.InFilterModel;

public class BasicSpecification<T> {
	
	private Class<T> currentClass;
	
	public BasicSpecification(Class<T> currentClass) {
		this.currentClass = currentClass;
	}
	
	public Specification<T> equal(EqualFilterModel eq) {
		return new Specification<T>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				ExpressionBuilder<T> expBuilder = new ExpressionBuilder<>(currentClass);
				Expression<T> expression = expBuilder.get(root, eq.getColumn());

				Predicate predicate = null;

				if (expression != null) {
					predicate = (eq.getIsEqual() ? cb.equal(expression, eq.getValue())
							: cb.notEqual(expression, eq.getValue()));
				}

				return predicate;
			}
		};
	}

	public Specification<T> in(InFilterModel inf) {
		return new Specification<T>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				ExpressionBuilder<T> expBuilder = new ExpressionBuilder<>(currentClass);
				Expression<T> expression = expBuilder.get(root, inf.getColumn());

				Predicate predicate = null;

				if (expression != null) {
					predicate = inf.getIsIn() ? expression.as(String.class).in(inf.getValues())
							: expression.as(String.class).in(inf.getValues()).not();
				}

				return predicate;
			}
		};
	}

	public Specification<T> dateBetween(DateFilterModel dfm) {
		return new Specification<T>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				SimpleDateFormat formatter = new SimpleDateFormat(ApiConstants.DATE_FORMAT);
				
				try {
					Date initialDate = formatter.parse(dfm.getInitialDate());
					Date finalDate = formatter.parse(dfm.getFinalDate());
					
					ExpressionBuilder<T> expBuilder = new ExpressionBuilder<>(currentClass);
					Expression<T> expression = expBuilder.get(root, dfm.getColumn());
					
					Expression<Date> exprDate = cb.function("DATE", Date.class, expression);
					
					return cb.between(exprDate, initialDate, finalDate);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		};
	}
	
}
