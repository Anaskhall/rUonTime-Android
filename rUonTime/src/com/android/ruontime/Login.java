package com.android.ruontime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	
	EditText username , password ; 
	Button login,reset ;
	CheckBox remember;
	SharedPreferences preferences ;
	String PREFS_NAME = "com.example.sp.LoginPrefs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen);
	
		username = (EditText)findViewById(R.id.UsernameEd);
		password = (EditText)findViewById(R.id.PasswordEd);
		login = (Button)findViewById(R.id.login_button);
		remember = (CheckBox)findViewById(R.id.remember_checkBox);
		
		preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		if (preferences.getString("logged", "").toString().equals("logged")) 
		{
			Intent i = new Intent(Login.this,MyCalendars.class);
			i.putExtra("USERNAME",preferences.getString("username", "").toString());
			i.putExtra("PASSWORD",preferences.getString("password", "").toString());
			i.putExtra("CHECK", true);
			startActivity(i);			
		}
		
		login.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!username.getText().toString().equals("") &&  (!password.getText().equals("")))
				{
					if(remember.isChecked())
					{
						SharedPreferences.Editor editor = preferences.edit();
						editor.putString("username", username.getText().toString());
						editor.putString("password", password.getText().toString());
						editor.putString("logged", "logged");
						editor.commit();
					}
					Intent i = new Intent(Login.this,MyCalendars.class);
					i.putExtra("USERNAME", username.getText().toString());
					i.putExtra("PASSWORD", password.getText().toString());
					i.putExtra("CHECK", remember.isChecked());
					startActivity(i);	
					
				}
				else
				{
					Toast.makeText(Login.this,"Please enter both the fields.", Toast.LENGTH_SHORT).show();
				}
				
			}
		});

}
	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
}
