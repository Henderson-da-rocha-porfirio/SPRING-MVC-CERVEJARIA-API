package com.algaworks.brewer.config.format;

import org.springframework.format.Formatter;

import java.math.*;
import java.text.*;
import java.util.*;

public class BigDecimalFormatter implements Formatter<BigDecimal> {

	private DecimalFormat decimalFormat;
	
	public BigDecimalFormatter(String pattern) {
		NumberFormat format = NumberFormat.getInstance(new Locale("pt", "BR"));
		decimalFormat = (DecimalFormat) format;
		decimalFormat.setParseBigDecimal(true);
		decimalFormat.applyPattern(pattern);
	}
	
	@Override
	public String print(BigDecimal object, Locale locale) {
		return decimalFormat.format(object);
	}

	@Override
	public BigDecimal parse(String text, Locale locale) throws ParseException {
		return (BigDecimal) decimalFormat.parse(text);
	}

}
