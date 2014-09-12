package model;

public class CalendarDay {
	
	private static String[] day = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};;
	
	public static String getWeekDay(int index){
		return day[index];
	}
	public static int dayIndex(String d){
		for(int i = 0; i < day.length; i++){
			if(day[i].equals(d))
				return i;
		}
		return 0;
	}
}
