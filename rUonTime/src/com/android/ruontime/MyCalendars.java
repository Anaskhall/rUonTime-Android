package com.android.ruontime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

class MyCalendar {
	public String name;
	public String id;
	public MyCalendar(String _name, String _id) {
		name = _name;
		id = _id;
	}
	@Override
	public String toString() {
		return name;
	}
}

public class MyCalendars extends Activity {

	private Spinner m_spinner_calender;
	private MyCalendar m_calendars[];
	private String m_selectedCalendarId = "0";
	private Button m_choose_button;
	String CALENDAR_CHOSEN = "chooseYourCalendar";
	SharedPreferences preferences ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendars_screen);
        preferences = getSharedPreferences(CALENDAR_CHOSEN, Context.MODE_PRIVATE);
        if (preferences.getString("chosen", "").toString().equals("chosen")) 
		{
			Intent i = new Intent(MyCalendars.this,Tabs.class);
			i.putExtra("CALENDAR_ID",m_selectedCalendarId);
			startActivity(i);			
		}
        /*get calendar list and populate the view*/
        getCalendars();
        populateCalendarSpinner();
        chooseCalendar();
    }
    
    private void chooseCalendar() {
    	m_choose_button = (Button) this.findViewById(R.id.choose_calendar);
    	m_choose_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				SharedPreferences.Editor editor = preferences.edit();
				editor.putString("calendarID", m_selectedCalendarId);
				editor.putString("chosen", "chosen");
				editor.commit();
				Intent i = new Intent(MyCalendars.this,Tabs.class);;
				i.putExtra("CALENDAR_ID",m_selectedCalendarId);
				startActivity(i);
			}
		});
    }
    private void populateCalendarSpinner() {
    	m_spinner_calender = (Spinner)this.findViewById(R.id.spinner_calendar);
    	ArrayAdapter l_arrayAdapter = new ArrayAdapter(this.getApplicationContext(), R.layout.spinner_item, m_calendars);
    	l_arrayAdapter.setDropDownViewResource(R.layout.spinner_item);
    	m_spinner_calender.setAdapter(l_arrayAdapter);
    	m_spinner_calender.setSelection(0);
    	m_spinner_calender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> p_parent, View p_view,
					int p_pos, long p_id) {
				m_selectedCalendarId = m_calendars[(int)p_id].id;
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
    }

   
    @SuppressLint("NewApi")
	private void getCalendars() {
    	String[] l_projection = new String[]{"_id", "calendar_displayName"};
    	Uri l_calendars;
    	if (Build.VERSION.SDK_INT >= 8) {
    		l_calendars = Uri.parse("content://com.android.calendar/calendars");
    	} else {
    		l_calendars = Uri.parse("content://calendar/calendars");
    	}
    	Cursor l_managedCursor = this.managedQuery(l_calendars, l_projection, null, null, null);	//all calendars
    	//Cursor l_managedCursor = this.managedQuery(l_calendars, l_projection, "selected=1", null, null);   //active calendars
    	if (l_managedCursor.moveToFirst()) {
    		m_calendars = new MyCalendar[l_managedCursor.getCount()];
    		String l_calName;
    		String l_calId;
    		int l_cnt = 0;
    		int l_nameCol = l_managedCursor.getColumnIndex(l_projection[1]);
    		int l_idCol = l_managedCursor.getColumnIndex(l_projection[0]);
    		do {
    			l_calName = l_managedCursor.getString(l_nameCol);
    			l_calId = l_managedCursor.getString(l_idCol);
    			m_calendars[l_cnt] = new MyCalendar(l_calName, l_calId);
    			++l_cnt;
    		} while (l_managedCursor.moveToNext());
    	}
    }

    @Override
	protected void onPause() {
		super.onPause();
		finish();
	}

}