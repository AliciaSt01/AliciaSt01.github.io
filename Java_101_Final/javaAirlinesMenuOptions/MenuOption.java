package javaAirlinesMenuOptions;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javaAirlines.*;

/**
 * MenuOption Class
 * Used to describe a MenuOption object
 * Should be used to create subclasses
 */
public abstract class MenuOption {
	/**
	 * Holds customers of MenuOption subclasses
	 */
	protected static ArrayList<Customer> customers = new ArrayList<Customer>();
	/**
	 * Holds flightDates of MenuOption subclasses
	 */
	protected static ArrayList<FlightDate> flightDates = new ArrayList<FlightDate>();
	/**
	 * Holds planes of MenuOption subclasses
	 */
	protected static ArrayList<Airplane> planes = new ArrayList<Airplane>();
	/**
	 * Holds the option number of MenuOption
	 */
	protected int optionNum;
	
	
	/**
	 * Empty Constructor
	 */
	public MenuOption() {
		
	}
	
	
	/**
	 * Converts object to String output
	 * @see java.lang.Object#toString()
	 * Should be overriden in subclass, super.toString + concatenate
	 */
	public String toString() {
		return optionNum + ". ";
		
	}
	

	/**
	 * Used to perform operation of menuOption
	 * Should be implemented in subclass
	 */
	public abstract void executeOption();

	/**
	 * Used to print FlightDate objects
	 */
	public void printFlightDates() {
		for (int i = 0; i < flightDates.size(); i++) {
			System.out.println((i + 1) + ") " + flightDates.get(i).toString());
			
		}
		
	}
	
	/**
	 * Uses to print Airplane objects
	 */
	public void printAirplanes() {
		for (int i = 0; i < planes.size(); i++) {
			System.out.println((i + 1) + ") " + planes.get(i).toString());
			
		}
		
	}
	
	/**
	 * Used to see if FlightDate selection is valid
	 * @param int - FlightDate selection
	 * @return boolean - is selection valid
	 */
	public boolean validFlightDateSelection(int selection) {
		if (selection> 0 && selection <= flightDates.size()) {
			return true;
			
		}
		System.out.println("\nInvalid flight date selection \n");
		return false;
		
	}
	
	/**
	 * Used to see if Airplane selection is valid
	 * @param int - Airplane selection
	 * @return boolean - is selection valid
	 */
	public boolean validAirplaneSelection(int selection) {
		if (selection > 0 && selection <= planes.size()) {
			return true;
			
		}
		System.out.println("\nInvalid airplane selection \n");
		return false;
		
	}
	
	/**
	 * Used to select and Airplane object
	 * @return Airplane - selected Airplane object
	 */
	public Airplane selectAirplane() {
		Scanner scan = new Scanner(System.in);
		
		//Loops until a valid plane is selected
		int selection = 0;
		do {
			try {
				//Gets airplane
				printAirplanes();
				System.out.print("Select plane (Enter number next to plane): ");
				selection = scan.nextInt();
				System.out.println();
				
			} catch (InputMismatchException IME) {
				System.out.println("\nMust enter a number \n");
				scan.nextLine();
				
			}
			
		} while (!validAirplaneSelection(selection));
		return planes.get(selection - 1);
		
	}
	
	/**
	 * Used to remove Airplane object from ArrayList
	 * @param Airplane - Airplane object to remove
	 */
	public void removeAirplane(Airplane plane) {
		for (int i = 0; i < planes.size(); i++) {
			if (planes.get(i).equals(plane)) {
				planes.remove(i);
				break;
			}
			
		}
		return;
		
	}
	
	/**
	 * Used to select a FlightDate object
	 * @return FlightDate - selected FlightDate object
	 */
	public FlightDate selectFlightDate() {
		Scanner scan = new Scanner(System.in);
		//Loops until valid FlightDate is selected
		int selection = 0;
		do {
			try {
				//Gets selection
				printFlightDates();
				System.out.print("Select date of flight (Enter number next to date): ");
				selection = scan.nextInt();
				System.out.println();
			
			} catch (InputMismatchException IME) {
				System.out.println("\nMust enter a number \n");
				scan.nextLine();
				
			}
			
		} while (!validFlightDateSelection(selection));
		return flightDates.get(selection - 1);
		
	}
	
	/**
	 * Used to select a Customer object from ArrayList
	 * @return Customer - selected Customer object
	 */
	public Customer selectCustomer() {
		Scanner scan = new Scanner(System.in);
		//Loops until valid Customer is selected
		int selection = 0;
		do {
			try {
				//Gets Customer
				printCustomers();
				System.out.print("Select customer (Enter number next to customer): ");
				selection = scan.nextInt();
				scan.nextLine();
				System.out.println();
				
			} catch (InputMismatchException IME) {
				System.out.println("\nMust enter a number \n");
				scan.nextLine();
				
			}
			
		} while (!validCustomerSelection(selection));
		return customers.get(selection - 1);
		
	}
	
	/**
	 * Used to print Customer objects from ArrayList
	 */
	public void printCustomers() {
		for (int i = 0; i < customers.size(); i++) {
			System.out.println((i + 1) + ") " + customers.get(i).toString());
			
		}
		
	}
	
	/**
	 * Used to see if Customer selection is valid
	 * @param int - Customer selection
	 * @return boolean - is selection valid
	 */
	private boolean validCustomerSelection(int selection) {
		if (selection > 0 && selection <= customers.size()) {
			return true;
			
		}
		System.out.println("\nInvalid customer selection \n");
		return false;
		
	}
	
	/**
	 * Used to create a Customer object and add to ArrayList
	 * @return Customer - Customer object that was created
	 */
	public Customer createCustomer() {
		Scanner in = new Scanner(System.in);
        
        System.out.print("Enter First Name: ");
        String first = in.nextLine();
        
        System.out.print("\nEnter Last Name: ");
        String last = in.nextLine();
		
        Customer customer = new Customer(first, last);
        customers.add(customer);
        
        System.out.println("\n You have created customer " + first + " " + last);
        return customer;
        
	}
	

	/**
	 * Used to get optionNum
	 * @return int - option number of MenuOption
	 */
	public int getOptionNum() {
		return optionNum;
		
	}
	
	/**
	 * Used to set optionNum
	 * @param int - option number of MenuOption
	 */
	public void setOptionNum(int optionNum) {
		this.optionNum = optionNum;
		return;
		
	}

}