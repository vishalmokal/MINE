package com.MOBIUSO.MINE.databasehelper;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "contact.db";
	public static final int DATABASE_VERSION =  1;
	public static final String TABLE_NAME = "contact";

	public static final String SR_NO = "srNo";
	public static final String NAME = "name";
	public static final String ADDRESS = "address";
	public static final String CITY = "city";
	public static final String CONATCT_NO = "conatctno";
	public static final String CATEGORY = "category";
	static String databasename = Environment.getExternalStorageDirectory() + "/MINE/" + DATABASE_NAME;

	public DatabaseHelper(Context context) {
		
		super(context, databasename, null, DATABASE_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String CREATE_CONTACT_DATABASE = ("CREATE TABLE " + TABLE_NAME + "("
				+ SR_NO + "  integer primary key autoincrement, " + NAME
				+ " text not null, " + ADDRESS + " text not null, " + CITY
				+ " text not null, " + CONATCT_NO + " text not null, "
				+ CATEGORY + " text not null" + ")");
		db.execSQL(CREATE_CONTACT_DATABASE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	//this function will open database
	public void openDAtabase()
	{
		getWritableDatabase();
	}
	
	//this function will enter data in data base.
	public void insertdata(SQLiteDatabase db , String name , String Address , String city ,String contactno , String category)
	{
		ContentValues values = new ContentValues();
		values.put(NAME, name);
		values.put(ADDRESS, Address);
		values.put(CITY, city);
		values.put(CONATCT_NO, contactno);
		values.put(CATEGORY, category);
		try{
		db.insert(TABLE_NAME, null, values);
		}
		catch (Exception e) {
		Log.d("Error", e.toString());
		}	
	}
	
	
	public ArrayList<String> getUniqueListFromCriteria(String criteria , SQLiteDatabase db)
	{
		String SQL_QUERY;
		if(criteria.equalsIgnoreCase("category"))
		{
			SQL_QUERY = "SELECT DISTINCT " + CATEGORY + " FROM " + TABLE_NAME;
		}
		else
		{
			SQL_QUERY = "SELECT DISTINCT " + CITY + " FROM " + TABLE_NAME;
		}
		Cursor cursor = db.rawQuery(SQL_QUERY, null);
		ArrayList<String> datalist = new ArrayList<String>();
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			datalist.add(cursor.getString(cursor.getColumnIndex(criteria)));
			cursor.moveToNext();
		}
		return datalist;
	}

}
