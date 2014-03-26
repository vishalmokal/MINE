package com.MOBIUSO.MINE.util;

import java.io.File;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.widget.Toast;

import com.MOBIUSO.MINE.databasehelper.DatabaseHelper;
import com.MOBIUSO.MINE.telephonedirectory.HomeScreenActivity;

public class ResourceGeneator {
	SQLiteDatabase db;
	DatabaseHelper databasehelper;
	Activity context;
	ContactControler controler;
	public ResourceGeneator(Activity activitycontext)
	{
		context = activitycontext;
		databasehelper = new DatabaseHelper(activitycontext);
		db = databasehelper.getWritableDatabase();
		controler = ContactControler.getContactControler();
	}
	// this data function will generate database if not exsist 
	public Boolean generateDataBase() {
		String applicationfilepath = Environment.getExternalStorageDirectory()
				+ "/MINE/";
		String resourcefilepath = applicationfilepath + "database.txt";
		
		File basedirectory = new File(applicationfilepath);
		File ResourceFIle = new File(resourcefilepath);
		
		try {
			String filedata = new FileReader().readFile(resourcefilepath);
			JSONObject contactlist = new JSONObject(filedata);
			JSONArray contacts = contactlist.getJSONArray("contactList");
			int contact_count_in_file = contacts.length();
			
			for (int index = 0; index <= contact_count_in_file; index++) {
				JSONObject contact = contacts.getJSONObject(index);
				String name = contact.getString("name");
				String Address = contact.getString("address");
				String city = contact.getString("city");
				String contactno = contact.getString("contactNo");
				String category = contact.getString("category");

				databasehelper.insertdata(db, name, Address, city, contactno,
						category);

			}
			ResourceFIle.delete();
			return true;
		} catch (IOException e) {
			Toast.makeText(context, "Error while Reading Data from file",
					Toast.LENGTH_LONG).show();
			return false;
		} catch (JSONException e) {
			Toast.makeText(context, "Error while reading data from json ",
					Toast.LENGTH_LONG).show();
			return false;
		}
	}
	
	
	
	public void generateCityList()
	{
		controler.setCities(databasehelper.getUniqueListFromCriteria("city", db));
	}
	
	public void generateCategoryList()
	{
		controler.setCategories(databasehelper.getUniqueListFromCriteria("category", db));
	}
}
