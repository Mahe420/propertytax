package com.project.propertytax.util;

import java.util.Calendar;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class YearValidator implements ConstraintValidator<YearContraint,String> {

@Override
public boolean isValid(String value, ConstraintValidatorContext context) {
	int year = Calendar.getInstance().get(Calendar.YEAR);
	return value.length()==4 && Integer.valueOf(value)<=year;
}
}
