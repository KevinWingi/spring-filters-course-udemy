package com.springfilterscourse.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter @Getter
public class InFilterModel {
	private String column;
	private List<String> values;
	private Boolean isIn;
}
