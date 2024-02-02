package javaAirlines;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

	/**
	 * TravelClass Class
	 * Used to describe a TravelClass object
	 */
public class TravelClass implements Serializable {
	/**
	 * Used for Serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Holds the name of TravelClass
	 */
	private String name;
	/**
	 * Holds the seats of TravelClass
	 */
	private Seat[][] seats;
	
	
	/**
	 * Empty Constructor
	 */
	public TravelClass() {
		
	}

	/**
	 * Copy Constructor - Used to create a copy of a TravelClass object
	 * @param tClass
	 */
	public TravelClass(TravelClass travelClass) {
		name = new String(travelClass.getName());
		seats = new Seat[travelClass.getSeats().length][travelClass.getSeats()[0].length];
		//Loops through Seat[][], copying each Seat object
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				seats[i][j] = new Seat(travelClass.getSeats()[i][j]);
				
			}
			
		}
		
	}
	
	/**
	 * Full Constructor - Used to create a TravelClass object
	 * @param String - name of TravelClass
	 * @param Seat[][] - seats of TravelClass
	 */
	public TravelClass(String name, Seat[][] seats) {
		this.name = name;
		this.seats = seats;
		
	}
	
	/**
	 * Converts object to String output
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name + " class with " + (seats.length * seats[0].length) + " seats";
		
	}
	
	/**
	 * Used to get the number of available seats from a TravelClass object
	 * @return int - number of available seats
	 */
	public int numAvailableSeats() {
		int counter = 0;
		//Loops through Seat[][] counting open seats
		for (int i = 0; i < seats.length; i++) {
    		for (int j = 0; j < seats[i].length; j++) {
    			if (seats[i][j].getAvailable() == true) {
    				counter++;
    				
    			}
    			
    		}
    		
		}
		return counter;
		
	}
	
	/**
	 * Used to remove Seat from a TravelClass object
	 * @param String - name of Seat to remove
	 * @return boolean - if seat was removed
	 */
	public boolean removeSeat(String seatName) {
		//Loops through Seat[][] looking for a match for seatName
    	for (int i = 0; i < seats.length; i++) {
    		for (int j = 0; j < seats[i].length; j++) {
    			if (seats[i][j].getName().equals(seatName)) {
    				seats[i][j].setAvailable(true);;
    				return true;
    				
    			}
    			
    		}
    		
    	}
    	return false;
    	
    }
	
	/**
	 * Used to print all Seats in TravelClass object and their availability
	 */
	public void printClassLoad() {
		//Loops through Seat[][] printing each seat and it's availability
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				//Prints Seat name
				System.out.print(seats[i][j].getName() + "-");
				//Prints Seat availability
				if (seats[i][j].getAvailable() == false) {
					System.out.print("Booked ");
					
				} else {
					System.out.print("Open ");
					
				}
				
				
			}
			System.out.println();
			
		}
		
	}
	
	/**
	 * Used to select a Seat object from a TravelClass object
	 * @return String - name of selected Seat
	 */
	public String selectSeat() {
		Scanner scan = new Scanner(System.in);
		
		//Gets window, aisle or any
		int selection;
		while (true) {
			//Gets selection from user
			while (true) {
				try {
					System.out.print("1) Window, 2) Aisle, 3) Any Seat (Select number) : ");
					selection = scan.nextInt();
					scan.nextLine();
					System.out.println();
					break;
					
				} catch (InputMismatchException IME) {
					System.out.println("\nMust enter a number \n");
					
				}
				
			}
			
			//Check if selection is in range
			if (selection < 1 || selection > 3) {
				System.out.println("Invalid selection \n");
				continue;
				
			}
			
			//Checks if selection has available seat
			boolean valid = true;
			switch (selection) {
			case 1:
				if (availableWindowSeat() == false) {
					System.out.println("There are no window seats available \n");
					valid = false;
					
				}
				break;
				
			case 2:
				if (availableAisleSeat() == false) {
					System.out.println("There are no available aisle seats available \n");
					valid = false;
					
				}
				break;
				
			default:
				break;
			
			}
			
			if (valid == true) {
				break;
				
			}
		
		}
		
		//Loops to get seat from user
		String seatSelection;
		boolean validSeat = false;
		do {
			switch (selection) {
				case 1:
					printAvailableWindowSeats();
					break;
					
				case 2:
					printAvailableAisleSeats();
					break;
					
				case 3:
					printAvailableSeats();
					break;
			
			}
			
			//Gets seat
			System.out.print("Enter desired seat name: ");
			seatSelection = scan.nextLine();
			System.out.println();
			
			//Loops through Seat[][] checking if seat exists and is available
			for (int i = 0; i < seats.length; i++) {
				for (int j = 0; j < seats[i].length; j++) {
					if (seats[i][j].getName().equals(seatSelection) && seats[i][j].getAvailable() == true) {
						validSeat = true;
						seats[i][j].setAvailable(false);
						break;
						
					}
					
				}
				
				if (validSeat == true) {
					break;
					
				}
				
			}
			if (validSeat == false) {
				System.out.println("Seat unavailable \n");
				
			}
		
		} while (validSeat == false);
		return seatSelection;
		
	}
	

	/**
	 * Used to print all available seats
	 */
	public void printAvailableSeats() {
		//Loops through rows of seats
		for (int i = 0; i < seats.length; i++) {
			//Loops through all seats in row
			for (int j = 0; j < seats[i].length; j++) {
				//Checks if seat is available
				if (seats[i][j].getAvailable() == true) {
					System.out.print(seats[i][j].getName() + " ");
					
				}
				
			}
			System.out.println();
			
		}
		return;
		
	}
	
	/**
	 * Used to print all available window seats
	 */
	public void printAvailableWindowSeats() {
		//Loops through rows of seats
		for (int i = 0; i < seats.length; i++) {
			//Checks if first seat is available
			if (seats[i][0].getAvailable() == true) {
				System.out.print(seats[i][0].getName() + " ");
			
			}
			
			//Checks if last seat is available
			if (seats[i][seats[i].length - 1].getAvailable() == true ) {
				System.out.print(seats[i][seats[i].length - 1].getName() + " ");
				
			}
			System.out.println();
			
		}
		return;
		
	}
	
	/**
	 * Used to print all available aisle seats
	 */
	public void printAvailableAisleSeats() {
		//Loops through rows of seats
		for (int i = 0; i < seats.length; i++) {
			//Loops through seats in row, skipping first and last
			for (int j = 1; j < seats[i].length - 1; j++) {
				//Checks if seat is available
				if (seats[i][j].getAvailable() == true) {
					System.out.print(seats[i][j].getName() + " ");
					
				}
				
			}
			System.out.println();
			
		}
		return;
		
	}

	/**
	 * Used to see if any Seat object is available for a TravelClass object
	 * @return boolean - is a Seat available
	 */
	public boolean availableSeat() {
		//Loops through rows of seats
		for (int i = 0; i < seats.length; i++) {
			//Loops through all seats in row
			for (int j = 0; j < seats[i].length; j++) {
				//Checks if seat is available
				if (seats[i][j].getAvailable() == true) {
					return true;
					
				}
				
			}
			
		}
		return false;
		
	}
	
	/**
	 * Used to see if a window Seat is available for a TravelClass object
	 * @return boolean - is a window Seat available
	 */
	public boolean availableWindowSeat() {
		//Loops through rows of seats
		for (int i = 0; i < seats.length; i++) {
			//Checks if first seat is available
			if (seats[i][0].getAvailable() == true) {
				return true;
			
			}
			
			//Checks if last seat is available
			if (seats[i][seats[i].length - 1].getAvailable() == true ) {
				return true;
				
			}
			
		}
		return false;
		
	}
	
	/**
	 * Used to see if and aisle Seat is available for a TravelClass object
	 * @return boolean - is an aisle Seat available
	 */
	public boolean availableAisleSeat() {
		//Loops through rows of seats
		for (int i = 0; i < seats.length; i++) {
			//Loops through seats in row, skipping first and last
			for (int j = 1; j < seats[i].length - 1; i++) {
				//Checks if any aisle seat is available
				if (seats[i][j].getAvailable() == true) {
					return true;
					
				}
				
			}
			
		}
		return false;
		
	}
	
	
	/**
	 * Used to get name
	 * @return String - name of TravelClass
	 */
	public String getName() {
		return name;
		
	}
	
	/**
	 * Used to set name
	 * @param String - name of TravelClass
	 */
	public void setName(String name) {
		this.name = name;
		return;
		
	}
	
	/**
	 * Used to set seats
	 * @return Seat[][] - seats of TravelClass
	 */
	public Seat[][] getSeats() {
		return seats;
		
	}
	
	/**
	 * Used to get seats
	 * @param Seat[][] - seats of TravelClass
	 */
	public void setSeats(Seat[][] seats) {
		this.seats = seats;
		return;
		
	}

}