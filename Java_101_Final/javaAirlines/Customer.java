package javaAirlines;

import java.io.Serializable;

/**
 * Customer Class
 * Used to describe a Customer object
 */
public class Customer implements Serializable {
	/**
	 * Used for Serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Holds the ID of Customer
	 */
	private int id;
	/**
	 * Holds the first name of Customer
	 */
	private String first;
	/**
	 * Holds the last name of Customer
	 */
	private String last;
	/**
	 * Holds the total amount of Customer objects created
	 */
	protected static int custCount;
	

	/**
	 * Empty Constructor
	 */
	public Customer() {
		
	}
	
	/**
	 * Basic Constructor - used to create a new Customer
	 * @param String - first name of Customer
	 * @param String - last name of Customer
	 */
	public Customer(String first, String last) {
		this.first = first;
		this.last = last;
		custCount++;
		id = custCount;
		
	}

	
	/**
	 * Converts object to String output
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "ID: " + id + " Name: " + first + " " + last;
		
	}

	
	/**
	 * Used to get the id
	 * @return int - ID number of Customer
	 */
	public int getId() {
		return id;
		
	}
	
	/**
	 * Used to set the id
	 * @param int - ID number of Customer
	 */
	public void setId(int id) {
		this.id = id;
		return;
		
	}
	
	/**
	 * Used to get first
	 * @return String - first name of Customer
	 */
	public String getFirst() {
		return first;
		
	}
	
	/**
	 * Used to set first
	 * @param String - first name of Customer
	 */
	public void setFirst(String first) {
		this.first = first;
		return;
		
	}
	
	/**
	 * Used to get last
	 * @return String - last name of Customer
	 */
	public String getLast() {
		return last;
		
	}
	
	/**
	 * Used to set last
	 * @param String - last name of Customer
	 */
	public void setLast(String last) {
		this.last = last;
		return;
		
	}

}