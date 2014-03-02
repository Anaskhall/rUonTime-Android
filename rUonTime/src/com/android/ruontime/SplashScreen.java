package com.android.ruontime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class SplashScreen extends Activity {
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        Thread timer = new Thread(){
        	public void run(){
        		try{
        			sleep(2000);
        		}catch(InterruptedException e){
        			e.printStackTrace();
        		}finally{
        			Intent openRuontime = new Intent("com.android.ruontime.RUONTIME");
        			startActivity(openRuontime);
        		}
        }
        	
        };
        timer.start();
    }

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
    
      
} 