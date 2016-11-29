import java.io.*;
import java.util.*;
public abstract class Appointment {
	String apptDesc;
	int apptYear;
	int apptMonth;
	int apptDay;
	public Appointment(String desc, int year, int month, int day){
		apptDesc = desc;
		apptYear = year;
		apptMonth = month;
		apptDay = day;
	}
	public abstract boolean occursOn(int year, int month, int day);
	public abstract void save() throws IOException;
}
