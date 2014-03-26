package com.MOBIUSO.MINE.telephonedirectory;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.MOBIUSO.MINE.util.ContactControler;
import com.example.telephonedirectory.R;

public class CityListActivity extends Activity {

	ContactControler controller;
	ListView citylist;
	ArrayList<String> cities;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_citylist);
		controller = ContactControler.getContactControler();
		citylist = (ListView) findViewById(R.id.citylist);
		cities = controller.getCities();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onStart() {
		super.onStart();

		ArrayAdapter<String> cityadapter = new ArrayAdapter<String>(
				CityListActivity.this, android.R.id.text1,
				android.R.layout.simple_expandable_list_item_2,
				controller.getCities());
		citylist.setAdapter(cityadapter);
	}

}
