package com.android.ruontime;


import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Tabs extends TabActivity {
	
	private TabHost mTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		
		mTabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;
		intent = new Intent(this, Ruontime.class);
		spec = mTabHost.newTabSpec("Home")
				.setIndicator("Home")
				.setContent(intent);
		mTabHost.addTab(spec);
		intent = new Intent(this, Settings.class);
		spec = mTabHost.newTabSpec("Settings")
				.setIndicator("Settings")
				.setContent(intent);
		mTabHost.addTab(spec);
		intent = new Intent(this, Statistics.class);
		spec = mTabHost.newTabSpec("Statistics")
				.setIndicator("Statistics")
				.setContent(intent);
		mTabHost.addTab(spec);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.help:
			Intent i1 = new Intent("com.android.ruontime.HELP");
			startActivity(i1);
			return true;
		case R.id.about_us:
			Intent i2 = new Intent("com.android.ruontime.ABOUT");
			startActivity(i2);
			return true;
		case R.id.exit:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	

	
}
