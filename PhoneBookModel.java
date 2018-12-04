import java.io.*;
import java.util.*;

/*
 * Model data for the phone book application.  
 */

public class PhoneBookModel {

	private PhoneBookView phonebookview;

	// The following are various states captured by the model
	public static String ADD_NAME_STATE = "ADD_NAME";
	public static String ADD_NUMBER_STATE = "ADD_NUMBER";
	public static String SEARCH_STATE = "SEARCH";
	public static String IDLE_STATE = "IDLE";
	public static String SEARCH_RESULT_STATE = "SEARCH_RESULT";
	public static String ERROR_STATE = "ERROR";
	public static String EXIT_STATE = "EXIT";

	// [ADD] Edit
	public static String EDIT_NAME_STATE = "EDIT_NAME";
	public static String EDIT_NUMBER_STATE = "EDIT_NUMBER";
	public static String EDIT_RESULT_STATE = "EDIT_RESULT";

	// [ADD] Delete
	public static String DELETE_STATE = "DELETE_NAME";
	public static String DELETE_RESULT_STATE = "DELETE_RESULT";

	// Private fields used to track various model data
	private String state = IDLE_STATE;
	private String searchResult = null;
	// private String editResult = null;
	// private String deleteResult = null;

	private DAO phoneBook;

	/**
	 * set the state
	 * 
	 * @param aState
	 */
	public void setState(String aState) {
		state = aState;
		phonebookview.stateHasChanged(this, state);
	}

	/**
	 * add a phone entry
	 * 
	 * @param name
	 * @param number
	 */
	public void addAnEntry(String name, String number) {
		phoneBook.put(name, number);
	}

	/**
	 * search the phone number and set the searchResult field
	 * 
	 * @param name
	 */
	public void searchPhoneNumber(String name) {
		searchResult = (String) phoneBook.get(name);
	}

	/**
	 * return the search result
	 */
	public String getSearchResult() {
		return searchResult;
	}

	/**
	 * edit a phonebook data
	 * 
	 * @param name
	 * @param number
	 */
	public void editData(String name, String number) {
		phoneBook.edit(name, number);
	}

	/**
	 * delete a phone entry
	 * 
	 * @param name
	 */
	public void deleteData(String name) {
		phoneBook.delete(name);
	}

	/**
	 * get the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * constructor
	 * 
	 * @param view
	 */
	public PhoneBookModel(PhoneBookView view) {
		phonebookview = view;

		phoneBook = new SQLiteDB("phonebook.db");
		// phoneBook = new PlainTextDB("phonebook.txt");
	}
}
