package javaAirlines;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Airplane Class
 * Used to describe a Airplane object
 */
public class Airplane implements Serializable {
	/**
	 * Used to serialize Airplane
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Holds the name of Airplane
	 */
	private String name;
	/**
	 * Holds the travel classes of Airplane
	 */
	private TravelClass[] classes;
	
	
	/**
	 * Empty Constructor
	 */
	public Airplane() {
		
	}

	/**
	 * Full Constructor - used to create an Airplane object
	 * @param String - name of Airplane
	 * @param TravelClass[] - classes of Airplane
	 */
	public Airplane(String name, TravelClass[] classes) {
		this.name = name;
		this.classes = classes;
		
	}
	
	/**
	 * Copy Constructor - used to make copies of an Airplane object
	 * @param Airplane - instance of class Airplane
	 */
	public Airplane(Airplane plane) {
		name = new String(plane.getName());
		classes = new TravelClass[plane.getClasses().length];
		//Iterates through classes, copying each one
		for (int i = 0; i < classes.length; i++) {
			classes[i] = new TravelClass(plane.getClasses()[i]);
			
		}
		
	}
	
	
	/**
	 * toString Method
	 * @return String - returns a string describing an Airplane instance
	 */
	public String toString() {
		return name + " with " + classes.length + " travel classes";
		
	}
	
	
	/**
	 * Used to print TravelClasses
	 */
	public void printClasses() {
		for (int i = 0; i < classes.length; i++) {
			System.out.println((i + 1) + ") " + classes[i].toString());
			
		}
		
	}
	
	/**
	 * Used to select a TravelClass from Airplane instance
	 * @return TravelClass - TravelClass that was selected
	 */
	public TravelClass selectClass() {
		Scanner scan = new Scanner(System.in);
		//Loops until user select a valid TravelClass
		int selection;
		do {
			while (true) {
				try {
					//Gets travel class from user
					printClasses();
					System.out.print("Select travel class (Enter number): ");
					selection = scan.nextInt();
					scan.nextLine();
					System.out.println();
					break;
					
				} catch (InputMismatchException IME) {
					System.out.println("\nMust enter a number \n");
					scan.nextLine();
					
				}
				
			}
			
		} while(!validClassSelection(selection));
		return classes[selection - 1];
		
	}
	
	/**
	 * Used to see if selected TravelClass is valid
	 * @param int - selection of TravelClass
	 * @return boolean - returns if selected TravelClass is valid
	 */
	private boolean validClassSelection(int selection) {
		//Checks if seat is valid
		if (selection > 0 && selection <= classes.length) {
			return true;
			
		} else {
			System.out.println("Invalid selection \n");
			return false;
			
		}
		
	}
	
	/**
	 * Prints all seats on plane and whether they are reserved or not
	 */
	public void printPlaneLoad() {
		//Loops through TravelClass printing seats and availability
		for (int i = 0; i < classes.length; i++) {
			System.out.println(classes[i].getName() + " class");
			classes[i].printClassLoad();
			
		}
		
	}
	
	/**
	 * Used to see if any seat is available
	 * @return boolean - returns if seat is available or not
	 */
	public boolean availableSeat() {
		//Loops through each TravelClass to see if a seat is available
		for (int i = 0; i < classes.length; i++) {
			if (classes[i].availableSeat() == true) {
				return true;
				
			}
			
		}
		return false;
		
	}
	
	/**
	 * Used to free a seat 
	 * @param String - name of seat to be removed
	 * @return boolean - returns if seat was removed successfully or not
	 */
	public boolean freeSeat(String seatName) {
		boolean removed = false;
		//Loops through each class checking for seat to remove
		for (int i = 0; i < classes.length; i++) {
			removed = classes[i].removeSeat(seatName);
			
			//Confirms seat was freed
			if (removed == true) {
				return true;
				
			}
			
		}
		return false;
		
	}
	

	/**
	 * Used to get name
	 * @return String - name of Airplane
	 */
	public String getName() {
		return name;
		
	}
	
	/**
	 * Used to set name
	 * @param String - name of Airplane
	 */
	public void setName(String name) {
		this.name = name;
		return;
		
	}
	
	/**
	 * Used to get classes
	 * @return TravelClass[] - travel classes of Airplane
	 */
	public TravelClass[] getClasses() {
		return classes;
		
	}
	
	/**
	 * Used to set classes
	 * @param TravelClass[] - travel classes of Airplane
	 */
	public void setClasses(TravelClass[] classes) {
		this.classes = classes;
		return;
		
	}

}
