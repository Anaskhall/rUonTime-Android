package com.android.ruontime;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.ruontime.database.Database;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Get_Groups extends Activity {

	private ProgressDialog pDialog;

	private static final String GROUP = "groupname";
	private static final String TAG_POSTS = "posts";
	
	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    public static List<String> listDataHeader = new ArrayList<String>();
    public static List<String> members = new ArrayList<String>();
    public static HashMap<String, List<String>> listDataChild= new HashMap<String, List<String>>();

	private JSONArray mGroups = null;
	private JSONArray mMembers = null;

	private ArrayList<HashMap<String, String>> mGroupList;
	SharedPreferences preferences ;
	String PREFS_NAME = "com.example.sp.LoginPrefs";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manage_groups);
		
	      
	}

	@Override
	protected void onResume() {
		super.onResume();
		new LoadGroups().execute();
	}

	/*public void addGroup(View v) {
		Intent i = new Intent(Get_Groups.this, Add_Group.class);
		startActivity(i);
	}*/

	public void updateJSONdata() {
		
		preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		String username = preferences.getString("username", "").toString();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", username));

		mGroupList = new ArrayList<HashMap<String, String>>();
		String result ="";
		String success = "";
		InputStream isr = null;
        try {

        	DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://130.229.129.70/webservice/myGroups.php");
        	//HttpPost httpPost = new HttpPost("http://192.168.56.1/webservice/myGroups.php");
            httpPost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            isr = httpEntity.getContent();
            Log.e("json String", "try1");

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
            Log.e("json String", "try2");
            isr.close();
            result = sb.toString();
            Log.e("json String", result);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        
        try {
        	Log.e("json String", "try3");
        	JSONObject json = new JSONObject(result);
        	mGroups = json.getJSONArray(TAG_POSTS);
        	Log.d("Register GOOD!", success);
        	listDataHeader = new ArrayList<String>();
        	listDataChild = new HashMap<String, List<String>>();
        	for (int i = 0; i < mGroups.length(); i++) {
				JSONObject c = mGroups.getJSONObject(i);
				String group = c.getString(GROUP);
				String id = c.getString("group_id");
				listDataHeader.add(group);
				List<String> members = new ArrayList<String>();
				members = getMembers(id);
	 	      //  Achievements.add("You've been early  minutes on average");
	 	       listDataChild.put(listDataHeader.get(i), members);
			} 	 
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
	}

/*	private void updateList() {

		ListAdapter adapter = new SimpleAdapter(this, mGroupList,
		R.layout.single_group, new String[] { GROUP}, new int[] { R.id.title});
		setListAdapter(adapter);
		ListView lv = getListView();	
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				// This method is triggered if an item is click within our

			}
		});
	}*/
	public class LoadGroups extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Get_Groups.this);
			pDialog.setMessage("Loading Groups...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {
			updateJSONdata();
			return null;

		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			pDialog.dismiss();
			expListView = (ExpandableListView) findViewById(R.id.lvExpe);
		    listAdapter = new ExpandableListAdapter(Get_Groups.this, listDataHeader, listDataChild);
		    expListView.setAdapter(listAdapter);
		}
	}
	public List<String> getMembers(String id){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("group_id", id));
        List<String> Mymembers = new ArrayList<String>();
		String result ="";
		InputStream isr = null;
        try {

        	DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://130.229.129.70/webservice/myMembers.php");
        	//HttpPost httpPost = new HttpPost("http://192.168.56.1/webservice/myMembers.php");
            httpPost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            isr = httpEntity.getContent();
            Log.e("json String", "try1");

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
            Log.e("json String", "try2");
            isr.close();
            result = sb.toString();
            Log.e("json String", result);
        } catch (Exception e) {
            Log.e("Buffer Error Members", "Error converting result " + e.toString());
        }
        
        try {
        	Log.e("json String", "try3");
        	JSONObject json = new JSONObject(result);
        	mMembers = json.getJSONArray(TAG_POSTS);
        	for (int i = 0; i < mMembers.length(); i++) {
				JSONObject c = mMembers.getJSONObject(i);
				String user = c.getString("username");
	 	        Mymembers.add(user);
			} 	
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return Mymembers;
	}
}
