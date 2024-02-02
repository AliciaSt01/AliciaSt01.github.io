package javaAirlines;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * FlightDate Class
 * Used to describe a FlightDate object
 */
public class FlightDate implements Serializable {
	/**
	 * Used for Serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Hold the LocalDate object of a FlightDate
	 */
	private LocalDate date;
	/**
	 * Hold an ArrayList of Flight objects for a FlightDate
	 */
	private ArrayList<Flight> flights;
	
	
	/**
	 * Empty Constructor
	 */
	public FlightDate() {
		
	}
	
	/**
	 * Basic Constructor to create new FlightDate object
	 * @param LocalDate - instance of LocalDate
	 */
	public FlightDate(LocalDate date) {
		this.date = date;
		flights = new ArrayList<Flight>();
		
	}

	
	/**
	 * Converts object to String output
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "On " + date.toString() + " there are " + flights.size() + " flights";
		
	}
	
	
	/**
	 * Used to see if any Seats are available for FlightDate object
	 * @return boolean - Is Seat available
	 */
	public boolean availableSeat() {
		//Loops through flights checking if any Seats are available
		for (int i = 0; i < flights.size(); i++) {
			if (flights.get(i).getPlane().availableSeat() == true) {
				return true;
				
			}
			
		}
		return false;
		
	}
	
	/**
	 * Used to add a Flight object to FlightDate object
	 * @param Flight - Flight object to add
	 */
	public void addFlight(Flight flight) {
		flights.add(flight);
		return;
		
	}
	
	/**
	 * Used to remove a Flight object from a FlightDate object
	 * @param Flight - Flight object to remove
	 */
	public void removeFlight(Flight flight) {
		//Loops through Flights, looking for Flight to remove
		for (int i = 0; i < flights.size(); i++) {
			if (flight.equals(flights.get(i))) {
				flights.remove(i);

			}
			
		}
		return;
		
	}
	
	/**
	 * Used to print Flights of FlightDate object
	 */
	private void printFlights() {
		for (int i = 0; i < flights.size(); i++) {
			System.out.println((i + 1) + ") " + flights.get(i).toString());
			
		}
		
	}
	
	/**
	 * Used to select a Flight object from a FlightDate object
	 * @return Flight - Flight object that was selected
	 */
	public Flight selectFlight() {
		Scanner scan = new Scanner(System.in);
		//Loops until user selects valid Flight
		int selection;
		do {
			while (true) {
				try {
					//Gets Flight selection
					printFlights();
					System.out.print("Select desired flight (Enter number next to flight): ");
					selection = scan.nextInt();
					System.out.println();
					break;
					
				} catch (InputMismatchException IME) {
					System.out.println("\nMust enter a number \n");
					
				}
				
			}
			
		} while (!validFlightSelection(selection));
		return flights.get(selection - 1);
		
	}
	
	/**
	 * Used to see if Flight selection is valid
	 * @param int - Flight object selection
	 * @return boolean - is Flight selection valid
	 */
	private boolean validFlightSelection(int selection) {
		if (selection > 0 && selection <= flights.size()) {
			return true;
			
		}
		System.out.println("Invalid airplane selection \n");
		return false;
		
	}
	
	
	/**
	 * Used to get date
	 * @return LocalDate - used to get LocalDate object
	 */
	public LocalDate getDate() {
		return date;
		
	}
	
	/**
	 * Used to set date
	 * @param LocalDate - use to set LocalDate object
	 */
	public void setDate(LocalDate date) {
		this.date = date;
		return;
		
	}
	
	/**
	 * Used to get flights
	 * @return ArrayList<Flight> - ArrayList of Flight objects
	 */
	public ArrayList<Flight> getFlights() {
		return flights;
		
	}
	
	/**
	 * Used to set flights
	 * @param ArrayList<Flight> - ArrayList of Flight objects
	 */
	public void setFlights(ArrayList<Flight> flights) {
		this.flights = flights;
		return;
		
	}
	
}