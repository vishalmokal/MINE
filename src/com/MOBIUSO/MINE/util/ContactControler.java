package com.MOBIUSO.MINE.util;

import java.lang.reflect.Array;
import java.util.ArrayList;


// this is singleton class.
//which will give detail about contact 
// give list of cities and categories



public class ContactControler {

	public static final ContactControler ContactControler = null;
	private static ContactControler contactcontroller = null;
	private ArrayList<Contact> contactarray;
	private ArrayList<String> categories;
	private ArrayList<String> cities;
	private String selectedCategory;
	
	
	public String getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	private ContactControler() {
		contactarray = new ArrayList<Contact>();
		categories = new ArrayList<String>();
		cities = new ArrayList<String>();
		
	}

	public static ContactControler getContactControler() {
		if (contactcontroller == null) {
			contactcontroller = new ContactControler();
		}
		return contactcontroller;
	}

	public ArrayList<String> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}

	public ArrayList<String> getCities() {
		return cities;
	}

	public void setCities(ArrayList<String> cities) {
		this.cities = cities;
	}

	public ArrayList<Contact> getContactarray() {
		return contactarray;
	}

	public void setContactarray(ArrayList<Contact> contactarray) {
		this.contactarray = contactarray;
	}

}
