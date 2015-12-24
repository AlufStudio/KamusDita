package com.wim.dictionary;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnCheckedChangeListener{

	private SQLiteDatabase db;
	private Cursor kamusCursor;
	private DataKamus kamus;
	
	private String language="INDONESIA";
	
	EditText textCari;
	TextView textHasil;
	
	RadioGroup rg;
	RadioButton rb1, rb2;
	Button translate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		kamus = new DataKamus(this);
		db = kamus.getWritableDatabase();
		//kamus.onCreate(db);
		//kamus.generateData(db);
	
		setContentView(R.layout.activity_main);
		
		textCari = (EditText) findViewById(R.id.textCari);
		textHasil = (TextView) findViewById(R.id.textHasil);
		rg = (RadioGroup) findViewById(R.id.radioGroup1);
		translate = (Button) findViewById(R.id.button1);

		rg.setOnCheckedChangeListener( this);
		
		
	    translate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					getTerjemahan(v);
			}
		});
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkID) {
		// TODO Auto-generated method stub
		if(checkID==R.id.radio0){
			Toast.makeText(this, "Indonesia - Sumbawa",Toast.LENGTH_SHORT).show();
			language="INDONESIA";
		}else if(checkID==R.id.radio1){
			Toast.makeText(this, "Sumbawa - Indonesia",Toast.LENGTH_SHORT).show();
			language="SUMBAWA";
		}
	}
	
	public void getTerjemahan(View view){
		String result="";
		
		String Word=textCari.getText().toString();
		kamusCursor = db.rawQuery("SELECT ID,INDONESIA,SUMBAWA FROM kamus WHERE "+language+" LIKE '%"+Word+"%' order BY "+language+"", null);
		
		if(language.equals("INDONESIA")){
			if(kamusCursor.moveToFirst()){
				result = kamusCursor.getString(2);
				for(;!kamusCursor.isAfterLast();kamusCursor.moveToNext()){
					result = kamusCursor.getString(2);			
				}
			}
		}else if(language.equals("SUMBAWA")){
			if(kamusCursor.moveToFirst()){
				result = kamusCursor.getString(1);
				for(;!kamusCursor.isAfterLast();kamusCursor.moveToNext()){
					result = kamusCursor.getString(1);
				}
			}
		}
		
		if(result.equals("")){
			result = "-- result --";
			Toast.makeText(this, "tidak ditemukan",Toast.LENGTH_SHORT).show();
		}
		textHasil.setText(result);
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		kamusCursor.close();
		db.close();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
