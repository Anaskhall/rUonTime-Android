package com.android.ruontime;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.ruontime.service.JSONParser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity implements OnClickListener{
	
	private EditText user, pass;
	private Button  mRegister;
	private Boolean succ=false;
	private CheckBox remember;
    private ProgressDialog pDialog;
	SharedPreferences preferences ;
	String PREFS_NAME = "com.example.sp.LoginPrefs";
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_screen);
		
		user = (EditText)findViewById(R.id.UsernameEd);
		pass = (EditText)findViewById(R.id.PasswordEd);
		remember = (CheckBox)findViewById(R.id.remember_checkBox);
		
		preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		if (preferences.getString("logged", "").toString().equals("logged")) 
		{
			Intent i = new Intent(Register.this,MyCalendars.class);
			startActivity(i);			
		}
		
		mRegister = (Button)findViewById(R.id.register);
		mRegister.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
				new CreateUser().execute();
	}
	
	
	class CreateUser extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Register.this);
            pDialog.setMessage("Creating User...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... args) {
			
			String result ="";
			String success = "";
        	String word = pass.getText().toString();
        	String usern = user.getText().toString();
			InputStream isr = null;
	        try {
	        	List<NameValuePair> params = new ArrayList<NameValuePair>();
	            params.add(new BasicNameValuePair("username", usern));
	            params.add(new BasicNameValuePair("password", word));
	        	DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://130.229.129.70/webservice/register.php");
            	//HttpPost httpPost = new HttpPost("http://192.168.56.1/webservice/register.php");
                httpPost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
 
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                isr = httpEntity.getContent();

	        } catch (Exception e) {
	            Log.e("Error Connection",e.toString());
	        }
	        try {
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    isr, "utf-8"), 8);
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	            isr.close();
	            result = sb.toString();
	            Log.e("jsonnnnnnnnnnnnnnnnnn String", result);
	        } catch (Exception e) {
	            Log.e("Buffer Error", "Error converting result " + e.toString());
	        }
	        
	        try {
	        	JSONObject json = new JSONObject(result);
	        	success = json.getString("success");
	        	Log.d("Register GOOD!", success);
	        } catch (JSONException e) {
	            Log.e("JSON Parser", "Error parsing data " + e.toString());
	        }

	        if (success.equals("1")){
	        	Log.d("Register GOOD!", "working");
	        	succ=true;
	        	SharedPreferences.Editor editor = preferences.edit();
	        	editor.putString("username", user.getText().toString());
				editor.putString("password", pass.getText().toString());
				editor.commit();
				
	            if(remember.isChecked())
				{
					editor.putString("logged", "logged");
					editor.commit();
				} 
	            Intent i = new Intent(Register.this, MyCalendars.class);
				startActivity(i);
	        }
	        else {
				Log.d("Register Failure!", "shit");
			}		
              return null;
		}
		
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (!succ){
            	Toast.makeText(Register.this, "Username/Password already used, try again!", Toast.LENGTH_LONG).show();
            }
            /*if (file_url != null){
            	Toast.makeText(Register.this, file_url, Toast.LENGTH_LONG).show();
            }*/
 
        }		
	}	
	@Override
	protected void onPause() {
		super.onPause();

				finish();
	}
}

