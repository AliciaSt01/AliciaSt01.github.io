package javaAirlinesMenuOptions;

import java.util.InputMismatchException;
import java.util.Scanner;
import javaAirlines.Customer;
import javaAirlines.Flight;
import javaAirlines.FlightDate;
import javaAirlines.Reservation;
import javaAirlines.TravelClass;

public class NewReservation extends MenuOption {
	@Override
	public String toString() {
		return super.toString() + "New Reservation";
		
	}
	
	public void executeOption() {
		while(true) {
			if (flightDates.size() == 0) {
				System.out.println("There are no dates available \n");
				break;
				
			}
			
			boolean openSeat = false;
			for (int i = 0; i < flightDates.size(); i++)  {
				for (int j = 0; j < flightDates.get(i).getFlights().size(); j++) {
					if (flightDates.get(i).getFlights().get(j).getPlane().availableSeat() == true) {
						openSeat = true;
						break;
						
					}
					
				}
				if (openSeat == true) {
					break;
					
				}
				
			}
			
			if (openSeat == false) {
				System.out.println("No seats available on any flights \n");
				break;
				
			}
			
			//New or existing customer
			Scanner scan = new Scanner(System.in);
			int selection = 0;
			while(true) {
				try {
					System.out.print("1) Existing Customer or 2) New Customer (Enter number) : ");
					selection = scan.nextInt();
					scan.nextLine();
					System.out.println();
					
				} catch (InputMismatchException IME) {
					System.out.println("\n Must enter a number \n");
					scan.nextLine();
					
				}
				
				if (selection <= 0 || selection > 2) {
					System.out.println("Invalid selection \n");
					continue;
					
				}
				
				if (selection ==  1 && customers.size() == 0) {
					System.out.println("There are no existing customers \n");
					continue;
					
				}
				break;
				
			}
			
			//Gets customer
			Customer customer;
			switch (selection) {
				case 1:
					customer = selectCustomer();
					break;
				
				default:
					customer = createCustomer();
					break;
			}
			
			//Checks if any flightDates
			if (flightDates.size() == 0) {
				System.out.println("There are no dates with flights in the system \n");
			
			} else {
				//Gets and checks date of flight
				FlightDate date;
				while (true) {
					date = selectFlightDate();
				
					//Checks if any flights available
					if (date.getFlights().size() == 0) {
						System.out.println("There are no flights available on that date \n");
						continue;
						
					}
					
					//Checks if any seats available on any flights
					if (date.availableSeat() == false) {
						System.out.println("There are no seats available on any flight that day \n");
						continue;
					
					}
					break;
						
				}
				
				//Gets and checks flight
				Flight flight;
				while (true) {
					flight = date.selectFlight();
					
					if (flight.getPlane().availableSeat() == true) {
						break;
						
					}
					System.out.println("There is no available seat on that flight \n");
						
				}
				
				//Gets and checks class
				TravelClass travelClass;
				while (true) {
					travelClass = flight.getPlane().selectClass();
						
					if (travelClass.availableSeat() == true) {
						break;
						
					}
					System.out.println("There are no available seats in that class \n");
					
				}
				
				String seatName = travelClass.selectSeat();
				
				Reservation reservation = new Reservation(customer, seatName, flight.getName());
				
				flight.addReservation(reservation);
				break;
			
			}
			
		}
		
	}
	
}
