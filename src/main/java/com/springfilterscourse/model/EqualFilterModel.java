package com.springfilterscourse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter @Getter
public class EqualFilterModel {
	private String column;
	private String value;
	private Boolean isEqual;
}
