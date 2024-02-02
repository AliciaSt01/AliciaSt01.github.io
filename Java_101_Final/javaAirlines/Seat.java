package javaAirlines;

import java.io.Serializable;

/**
 * Seat Class
 * Used to describe a Seat object
 */
public class Seat implements Serializable {
	/**
	 * Used for Serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Holds the name of Seat
	 */
	private String name;
	/**
	 * Holds the availability of Seat
	 */
	private boolean available;
	

	/**
	 * Empty Constructor
	 */
	public Seat() {
		
	}
	
	/**
	 * Basic Constructor - used to a create named, empty Seat object
	 * @param String - name of Seat
	 */
	public Seat(String name) {
		this.name = name;
		available = true;
		
	}
	
	/**
	 * Copy Constructor - used to create copies of a Seat object
	 * @param Seat - Seat object to copy
	 */
	public Seat(Seat seat) {
		name = new String(seat.getName());
		available = seat.getAvailable();
		
	}
	
	
	/**
	 * Used to get name
	 * @return String - name of Seat
	 */
	public String getName() {
		return name;
		
	}
	
	/**
	 * Used to set name
	 * @param String - name of Seat
	 */
	public void setName(String name) {
		this.name = name;
		return;
		
	}
	
	/**
	 * Used to get available
	 * @return boolean - availability of Seat
	 */
	public boolean getAvailable() {
		return available;
		
	}
	
	/**
	 * Used to set available
	 * @param boolean - availability of Seat
	 */
	public void setAvailable(boolean available) {
		this.available = available;
		return;
		
	}

}