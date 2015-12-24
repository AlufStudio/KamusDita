package com.wim.dictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataKamus extends SQLiteOpenHelper{

	private static final String DB_NAME = "dbkamus";
	private static final String SUMBAWA = "sumbawa";
	private static final String INDONESIA = "indonesia";
	
	public DataKamus(Context context) {
		super(context, DB_NAME, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS kamus");
		db.execSQL("CREATE TABLE if not exists kamus (ID INTEGER PRIMARY KEY AUTOINCREMENT, INDONESIA TEXT, SUMBAWA TEXT);");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void generateData(SQLiteDatabase db){
		ContentValues cv = new ContentValues();
		cv.put(INDONESIA, "ikan");
		cv.put(SUMBAWA, "empa");
		db.insert("kamus", INDONESIA, cv);
		
		cv.put(INDONESIA, "rumah");
		cv.put(SUMBAWA, "bale");
		db.insert("kamus", INDONESIA, cv);
		
		cv.put(INDONESIA, "obat");
		cv.put(SUMBAWA, "mido");
		db.insert("kamus", INDONESIA, cv);
	}
}
