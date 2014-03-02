package com.android.ruontime;

import com.android.ruontime.service.Reminder;

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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		Preference pref = findPreference(REMINDER);
		SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		pref.setSummary("Reminder Set "+Long.valueOf(sharedPreferences.getString("reminder", "15"))+ " min before meeting");
	
		
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

		
		if (key.equals(ACTIVATE_SERVICE)) {
			boolean active = sharedPrefs.getBoolean(ACTIVATE_SERVICE, true);
			if (active) {
				Toast.makeText(getBaseContext(), 
		                "Service active",Toast.LENGTH_SHORT).show();
				Reminder.start(getApplicationContext());
			} else {
				Toast.makeText(getBaseContext(), 
		                "Service not active",Toast.LENGTH_SHORT).show();
				Reminder.stop(getApplicationContext());
			}
		} 
		if (key.equals(REMINDER)) {
			 Preference pref = findPreference(key);
			 pref.setSummary("Reminder Set "+Long.valueOf(sharedPrefs.getString("reminder", "15"))+ " min before meeting");
			boolean active = sharedPrefs.getBoolean(ACTIVATE_SERVICE, true);
			if (active) {
				Toast.makeText(getBaseContext(), 
		                "Servic active",Toast.LENGTH_SHORT).show();
				Reminder.restart(getApplicationContext());
			}
			else{
				Toast.makeText(getBaseContext(), 
		                "Service not active",Toast.LENGTH_SHORT).show();
			}
		} 
		
		

	}



	
	

}
