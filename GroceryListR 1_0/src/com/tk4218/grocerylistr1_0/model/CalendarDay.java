package com.tk4218.grocerylistr1_0.model;

public class CalendarDay {
	
	private String[] day = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};;
	
	public CalendarDay(){
	
	}
	
	public String getWeekDay(int index){
		return day[index];
	}
}
