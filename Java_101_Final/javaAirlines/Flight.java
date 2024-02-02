package javaAirlines;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Flight Class
 * Used to describe a Flight object
 */
public class Flight implements Serializable {
	/**
	 * Used for Serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Holds the name of Flight
	 */
	private String name;
	/**
	 * Holds the Airplane object of Flight
	 */
	private Airplane plane;
	/**
	 * Holds the ArrayList of Reservation of Flight
	 */
	private ArrayList<Reservation> reservations;

	
	/**
	 * Empty Constructor
	 */
	public Flight() {
		
	}
	
	/**
	 * Basic Constructor - used to create new Flight objects
	 * @param Airplane - instance of Airplane
	 * @param LocalDate - instance of LocalDate
	 */
	public Flight(Airplane plane, LocalDate date) {
		this.plane = plane;
		name = date.toString() + " " + plane.getName();
		reservations = new ArrayList<Reservation>();
				
	}

	
	/**
	 * Converts object to String output
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Flight " + name + " with " + reservations.size() + " reservations";
		
	}
	
	/**
	 * Used to add a reservation to a Flight object
	 * @param Reservation - Reservation object to add to Flight object
	 */
	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
		return;
		
	}
	
	/**
	 * Used to print the reservations in a Flight object
	 */
	public void printReservations() {
		//Loops through reservations, printing
		for (int i = 0; i < reservations.size(); i++) {
			System.out.println((i + 1) + ") "+ reservations.get(i).toString());
			
		}
		
	}
	
	/**
	 * Used to remove a reservation from a Flight object
	 */
	public void removeReservation() {
		Scanner scan = new Scanner(System.in);
		
		int selection = 0;
		while (true) {
			try {
				//Gets reservation to remove from user
				printReservations();
				System.out.print("Select reservation (enter number next to reservation): ");
				selection = scan.nextInt();
				System.out.println();
				
			} catch (InputMismatchException IME) {
				System.out.println("\nMust enter a number \n");
				
			}
			
			if (selection <= 0 || selection >= reservations.size()) {
				System.out.println("Invalid selection \n");
				continue;
				
			}
			break;
		
		}
		//Removes reservation
		reservations.remove(selection - 1);
		
	}
	
	
	/**
	 * Used to get name
	 * @return Sting - name of Flight
	 */
	public String getName() {
		return name;
		
	}
	
	/**
	 * Used to set name
	 * @param String - name of Flight
	 */
	public void setName(String name) {
		this.name = name;
		return;
		
	}
	
	/**
	 * Used to get plane
	 * @return Airplane - Airplane object of Flight
	 */
	public Airplane getPlane() {
		return plane;
		
	}
	
	/**
	 * Used to set plane
	 * @param Airplane - Airplane object of Flight
	 */
	public void setPlane(Airplane plane) {
		this.plane = plane;
		return;
		
	}
	
	/**
	 * Used to get reservations
	 * @return ArrayList - Reservation objects of Flight
	 */
	public ArrayList<Reservation> getReservations() {
		return reservations;
		
	}
	
	/**
	 * Used to set reservations
	 * @param ArrayList - Reservation objects of Flight
	 */
	public void setReservations(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
		return;
		
	}

}