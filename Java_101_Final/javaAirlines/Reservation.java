package javaAirlines;

import java.io.Serializable;

/**
 * Reservation Class
 * Used to describe a Reservation object
 */
public class Reservation implements Serializable {
	/**
	 * Used for Serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Holds the ID of Reservation
	 */
	private String resId;
	/**
	 * Holds the Customer object of Reservation
	 */
	private Customer cust;
	/**
	 * Holds the seat name of Reservation
	 */
	private String seat;

	
	/**
	 * Empty Constructor
	 */
	public Reservation() {
		
	}
	
	/**
	 * Full Constructor - Used to create new Reservation objects
	 * @param Customer - instance of Customer
	 * @param String - name of seat
	 * @param String - name of flight
	 */
	public Reservation(Customer cust, String seat, String flightName) {
		this.cust = cust;
		this.seat = seat;
		resId = flightName + "-" + cust.getId();
		
	}
	

	/**
	 * Converts object to String output
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Reservation ID: " + resId + ", " + cust.toString() + ", Seat: " + seat;
		
	}
	

	/**
	 * Used to get resID
	 * @return String - ID number of Reservation
	 */
	public String getResId() {
		return resId;
		
	}
	
	/**
	 * Used to set resID
	 * @param String - ID number of Reservation
	 */
	public void setResId(String resId) {
		this.resId = resId;
		return;
		
	}
	
	/**
	 * Used to get cust
	 * @return Customer - Customer object of Reservation
	 */
	public Customer getCust() {
		return cust;
		
	}
	
	/**
	 * Used to set cust
	 * @param Customer - instance of Customer
	 */
	public void setCust(Customer cust) {
		this.cust = cust;
		return;
		
	}
	
	/**
	 * Used to get seat
	 * @return String - name of Reservation seat
	 */
	public String getSeat() {
		return seat;
		
	}
	
	/**
	 * Used to set seat
	 * @param String - name of Reservation seat
	 */
	public void setSeat(String seat) {
		this.seat = seat;
		return;
		
	}

}