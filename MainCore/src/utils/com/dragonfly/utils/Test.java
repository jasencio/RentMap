package com.dragonfly.utils;

import java.util.Calendar;

public class Test {

	
	public static void main(String[] args) {
	
		Calendar calendar =	Calendar.getInstance();
		
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		
		
		
		System.out.println(calendar.getTimeInMillis());
		
		System.out.println("Day"+calendar.getMinimum(Calendar.DAY_OF_MONTH));
		System.out.println("Day"+calendar.getMaximum(Calendar.DAY_OF_MONTH));
		
		int i=0;
		for (int a = 0; a<10;a++)
		{i++;
			System.out.println(i);
		}
		}
}
