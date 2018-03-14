package classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/*
 * Called for in the SRS
 * 
 * It is used to keep track of and to return the current time and date
 */
public class Calendar {
	Long time;
	DateTimeFormatter date;
	LocalDate localdate;
	LocalTime localtime;
	
	//Returns the time
	public Long displayTime() {
		return(time);
	}
	
	//Returns the date
	public String displayDate() {
		return(date.format(localdate));
	}
	
	//Obtains the current time
	public void recordTime() {
		time = System.currentTimeMillis();
	}
	
	//Obtains the current date
	public void recordDate() {
		date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		localdate = LocalDate.now();
	}
}
