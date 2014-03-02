package com.android.ruontime.model;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.CalendarContract;

public class NextEvent {
	
	private  String event_title;
	private Long event_date;
	
	public NextEvent (ContentResolver contentResolver){
		Cursor mCursor = null;
		String[] COLS = new String[] { CalendarContract.Events.TITLE, CalendarContract.Events.DTSTART};
		mCursor  =contentResolver.query(CalendarContract.Events.CONTENT_URI, COLS,  CalendarContract.Events.DTSTART + " > " + (new Date()).getTime(), null, CalendarContract.Events.DTSTART);	
        mCursor.moveToFirst();
        this.event_title = mCursor.getString(0);
    	this.event_date = mCursor.getLong(1);
	}

	public String getEvent_title() {
		return event_title;
	}

	public void setEvent_title(String event_title) {
		this.event_title = event_title;
	}

	public Long getEvent_date() {
		return event_date;
	}

	public void setEvent_date(Long event_date) {
		this.event_date = event_date;
	}
	public Long timeRemaining(){
		
		long remaining = this.event_date - System.currentTimeMillis();
        return remaining / 60000;
	}
	
	

}
