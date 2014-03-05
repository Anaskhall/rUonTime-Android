package com.android.ruontime;

import com.android.ruontime.service.Reminder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class Settings extends PreferenceActivity implements OnSharedPreferenceChangeListener{

	public static final String ACTIVATE_SERVICE = "activateService";
	public static final String REMINDER = "reminder";
	public static final String LOGOUT = "log_out";
	public static final String GROUPS = "groups";
	public static final String DROP = "drop";
	public static final String CHANGE = "change";
	
	public static final String DIGEST_LIST = "digest_list";
	public static final String DIGEST_CHECKBOX = "activateDigest";
	
	SharedPreferences pr ;
	String PREFS_NAME = "com.example.sp.LoginPrefs";
	String CALENDAR_CHOSEN = "chooseYourCalendar";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		Preference pref = findPreference(REMINDER);
		SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		pref.setSummary("Reminder Set "+Long.valueOf(sharedPreferences.getString("reminder", "15"))+ " min before meeting");
		
		ListPreference pref1 = (ListPreference)findPreference(DIGEST_LIST);
		pref1.setSummary(pref1.getEntry());
		
		Preference logout = (Preference)findPreference(LOGOUT);
		logout.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
		                @Override
		                public boolean onPreferenceClick(Preference arg0) { 
		                	pr= getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		                	SharedPreferences.Editor editor = pr.edit();
		                	editor.putString("logged", "not logged");
							editor.commit();
		                	Intent i = new Intent(Settings.this,Login.class);
		            			startActivity(i); 
		                    return true;
		                }
		            });
		
		Preference dropgroup = (Preference)findPreference(DROP);
		dropgroup.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
		                @Override
		                public boolean onPreferenceClick(Preference arg0) { 
		                    //code for what you want it to do   
		                    return true;
		                }
		            });
		Preference displaygroup = (Preference)findPreference(GROUPS);
		displaygroup.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
		                @Override
		                public boolean onPreferenceClick(Preference arg0) { 
		                	Intent i = new Intent(Settings.this,Get_Groups.class);
	            			startActivity(i);   
		                    return true;
		                }
		            });
		Preference changecalendar = (Preference)findPreference(CHANGE);
		changecalendar.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
		                @Override
		                public boolean onPreferenceClick(Preference arg0) { 
		                	pr = getSharedPreferences(CALENDAR_CHOSEN, Context.MODE_PRIVATE);
		                	SharedPreferences.Editor editor = pr.edit();
		                	editor.putString("chosen", "not chosen");
							editor.commit();
		                	Intent i = new Intent(Settings.this,MyCalendars.class);
		            		startActivity(i);    
		                    return true;
		                }
		            });
		
	}
	@Override
	protected void onStart() {
		super.onStart();
		this.getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onStop() {
		// Unregister the listener whenever a key changes
		this.getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
		super.onStop();
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPrefs, String key) {

		// Redundant with a switchpreference.
		if (key.equals(ACTIVATE_SERVICE)) {
			boolean active = sharedPrefs.getBoolean(ACTIVATE_SERVICE, true);
			if (active) {
				Reminder.start(getApplicationContext());
			} else {
				Reminder.stop(getApplicationContext());
			}
		} 
		
		if (key.equals(DIGEST_LIST)) {
			 ListPreference pref = (ListPreference)findPreference(key);
			 pref.setSummary(pref.getEntry());
			 
			boolean active = sharedPrefs.getBoolean(DIGEST_CHECKBOX, true);
			if (!active) {
				Toast.makeText(getBaseContext(), 
		                "Digest off",Toast.LENGTH_SHORT).show();
			}
		}
		if (key.equals(REMINDER)) {
			 Preference pref = findPreference(key);
			 pref.setSummary("Reminder Set "+Long.valueOf(sharedPrefs.getString("reminder", "15"))+ " min before meeting");
			boolean active = sharedPrefs.getBoolean(ACTIVATE_SERVICE, true);
			if (active) {
				Reminder.restart(getApplicationContext());
			}
			else{
				Toast.makeText(getBaseContext(), 
		                "Notification off",Toast.LENGTH_SHORT).show();
			}
		} 
		
		

	}



	
	

}
