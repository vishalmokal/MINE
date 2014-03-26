package com.MOBIUSO.MINE.telephonedirectory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.MOBIUSO.MINE.databasehelper.DatabaseHelper;
import com.MOBIUSO.MINE.util.ContactControler;
import com.MOBIUSO.MINE.util.ResourceGeneator;
import com.example.telephonedirectory.R;

public class HomeScreenActivity extends Activity {

	DatabaseHelper databasehelper;
	ContactControler myContactControler;
	ContactControler controller;
	ResourceGeneator resourcegenerator;
	ListView categorylistview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		String applicationfilepath = Environment.getExternalStorageDirectory()
				+ "/MINE/";
		String resourcefilepath = applicationfilepath + "database.txt";
		File basedirectory = new File(applicationfilepath);
		File ResourceFIle = new File(resourcefilepath);
		databasehelper = new DatabaseHelper(HomeScreenActivity.this);
		controller = ContactControler.getContactControler();
		resourcegenerator = new ResourceGeneator(HomeScreenActivity.this);
		categorylistview = (ListView) findViewById(R.id.category);

		// check weather application base directory available or not
		// if base directory is not available then it will create base
		// directory.
		// it will create database in that directory and copy database.txt file.
		if (!basedirectory.exists()) {
			basedirectory.mkdir();
			copyAssets();
			databasehelper.openDAtabase();
		}

		// check if database.txt available in application basepath
		// if database.txt is available then read that file decode JSOn
		// and insert data in data base.

		if (ResourceFIle.exists()) {
			if (resourcegenerator.generateDataBase()) {
				Toast.makeText(
						HomeScreenActivity.this,
						"navi mumbai contact database is generated on your device.",
						Toast.LENGTH_LONG).show();
			}
		}

		
	}

	@Override
	protected void onResume() {
		super.onResume();
	
	}

	@Override
	protected void onStart() {
		super.onStart();
		resourcegenerator.generateCategoryList();
		resourcegenerator.generateCityList();
		ArrayList<String> categorylist = controller.getCategories();
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_2,
				android.R.id.text1, categorylist);
		categorylistview.setAdapter(arrayAdapter);
		categorylistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String value = (String) categorylistview
						.getItemAtPosition(position);
				controller.setSelectedCategory(value);
				Intent citylist = new Intent(HomeScreenActivity.this,
						CityListActivity.class);
				startActivity(citylist);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home_screen, menu);
		return true;
	}

	private void copyAssets() {
		AssetManager assetManager = getAssets();

		InputStream in = null;
		OutputStream out = null;
		try {
			in = assetManager.open("database.txt");
			File outFile = new File(Environment.getExternalStorageDirectory()
					+ "/MINE/", "database.txt");
			out = new FileOutputStream(outFile);
			copyFile(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} catch (IOException e) {
			Log.e("tag", "Failed to copy asset file: " + "database.txt", e);
		}

	}

	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}

}
