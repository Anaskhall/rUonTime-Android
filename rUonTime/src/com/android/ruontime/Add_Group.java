package com.android.ruontime;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Group extends Activity implements OnClickListener{
	
	private EditText title, message;
	private Button  mSubmit;
	
    private ProgressDialog pDialog;
    private static final String POST_COMMENT_URL = "http://10.0.2.2:1234/webservice/addcomment.php";
    
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_group);
		
		title = (EditText)findViewById(R.id.title);
		
		mSubmit = (Button)findViewById(R.id.submit);
		mSubmit.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
				new AddGroup().execute();
	}
	
	
	class AddGroup extends AsyncTask<String, String, String> {
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Add_Group.this);
            pDialog.setMessage("Adding a Group...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... args) {

            String post_title = title.getText().toString();
            

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("groupname", post_title));
 
                Log.d("request!", "starting");
            
            String result ="";
			String success = "";
			InputStream isr = null;
	        try {
	        	DefaultHttpClient httpClient = new DefaultHttpClient();
                //HttpPost httpPost = new HttpPost("http://130.229.129.70/webservice/addGroup.php");
	        	HttpPost httpPost = new HttpPost("http://192.168.56.1/webservice/addGroup.php");
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
	        	Log.d("Add GOOD!", "working");
	        	Intent i = new Intent(Add_Group.this, Get_Groups.class);
	    		startActivity(i);
	        }
	        else {
				Log.d("Adding Failure!", "shit");
			}		
              return null;
                  
			
		}
		
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            if (file_url != null){
            	Toast.makeText(Add_Group.this, file_url, Toast.LENGTH_LONG).show();
            }
 
        }
		
	}
		 

}
