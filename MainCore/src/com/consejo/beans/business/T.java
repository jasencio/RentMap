package com.consejo.beans.business;

import java.text.DecimalFormat;

public class T {
	private static DecimalFormat df2 = new DecimalFormat(".##");
public static void main(String[] args) {
	double input = 32.123456;
	System.out.println("double : " + input);
	System.out.println("double : " + df2.format(input));
	
	input = Double.valueOf(df2.format(input).replace(",", "."));
}
}
