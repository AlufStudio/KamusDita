package com.wim.dictionary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Intro extends Activity{

	@Override
	protected void onCreate(Bundle TravisIsAwesome) {
		// TODO Auto-generated method stub
		super.onCreate(TravisIsAwesome);
		setContentView(R.layout.intro);
		
		Thread logoTimer = new Thread(){
			public void run(){
				try{
					short logoTimer = 0;
					while(logoTimer < 5000){
						sleep(100);
						logoTimer = (short) (logoTimer + 100);
					}
					startActivity(new Intent("com.wim.dictionary.CLEARSCREEN"));
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					finish();
				}
			}
		};
		logoTimer.start();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
}
