package se.kth.mobdev.ruontime.util;

import java.util.Calendar;
import java.util.Date;

public class CalendarHelper {

	public static Calendar DateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
}
