package com.android.ruontime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.android.ruontime.database.Database;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;
 
public class Statistics extends Activity {
 
	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_screen);
 
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
 
        // preparing list data
        prepareListData();
 
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);
 
        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {
 
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
            	
            	
            	if (listDataChild.get(
                        listDataHeader.get(groupPosition)).get(
                        childPosition).equals("Show Record")){
                        		Intent i = new Intent("com.android.ruontime.SQLVIEW");
                    			startActivity(i);
            	}
            	
            	if(listDataChild.get(
                        listDataHeader.get(groupPosition)).get(
                        childPosition).equals("Delete Record")){
            		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            		    @Override
            		    public void onClick(DialogInterface dialog, int which) {
            		        switch (which){
            		        case DialogInterface.BUTTON_POSITIVE:
            		        	Database entry = new Database(Statistics.this);
            		        	entry.open();
            		        	entry.delete();
            		        	 Toast.makeText(
            		                        getApplicationContext(),"The record has been successfully deleted", Toast.LENGTH_SHORT)
            		                        .show();
            		            break;

            		        case DialogInterface.BUTTON_NEGATIVE:
            		            //No button clicked
            		            break;
            		        }
            		    }
            		};
            		AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            		builder.setMessage("Are you sure?").setNegativeButton("No", dialogClickListener)
            		.setPositiveButton("Yes", dialogClickListener).show();
            	}
                return false;
            }
        });
    }
    
    
    
    @Override
	protected void onResume() {
    	prepareListData();
		super.onResume();
	}
 
    /*
     * Preparing the list data
     */
    private void prepareListData() {
    	
    	Database entry = new Database(Statistics.this);
    	entry.open();
    	
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
 
        // Adding child data
        listDataHeader.add("Achievements");
        listDataHeader.add("Detailed Statistics");
        listDataHeader.add("Record");
 
        // Adding child data
        List<String> Achievements = new ArrayList<String>();
        Achievements.add("You've been early "+entry.getAverageEarly() + " minutes on average");
        Achievements.add("You've been late "+entry.getAverageLate() + " minutes on average");
 
        List<String> Det_Stats = new ArrayList<String>();
        Det_Stats.add("Average late : "+entry.getAverageLate());
        Det_Stats.add("Average early : "+entry.getAverageEarly());
        Det_Stats.add("Accumulated late : " + entry.getTotalLate());
        Det_Stats.add("Accumulated early : "+entry.getTotalEarly());
        Det_Stats.add("Total Average : "+entry.getAverage());
        
        List<String> Record = new ArrayList<String>();
        Record.add("Show Record");
        Record.add("Delete Record");
 
        listDataChild.put(listDataHeader.get(0), Achievements); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Det_Stats);
        listDataChild.put(listDataHeader.get(2), Record);
        entry.close();
    }
}



