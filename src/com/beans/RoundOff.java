package com.beans;

import java.math.BigDecimal;

public class RoundOff {
	@SuppressWarnings("deprecation")
	public static BigDecimal round(float d, int decimalPlace) {
	    BigDecimal bd = new BigDecimal(Float.toString(d));
	    bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);       
	    return bd;
	}
}
