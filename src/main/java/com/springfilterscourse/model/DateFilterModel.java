package com.springfilterscourse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter @Getter
public class DateFilterModel {
	
	private String column;
	private String initialDate;
	private String finalDate;
	
}
