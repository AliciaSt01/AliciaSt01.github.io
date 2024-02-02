package javaAirlinesMenuOptions;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javaAirlines.Airplane;
import javaAirlines.Customer;
import javaAirlines.Flight;
import javaAirlines.FlightDate;
import javaAirlines.Reservation;

public class CancelReservation extends MenuOption {
	@Override
	public String toString() {
		return super.toString() + "Cancel Reservation";
		
	}
	
	public void executeOption() {
		while (true) {
			//Checks if flight date exist
			if (flightDates.size() == 0) {
				System.out.println("There are no dates in the system \n");
				break;
				
			}
			
			//checks if any customers exist
			if (customers.size() == 0) {
				System.out.println("There are no customers in the system \n");
				break;
				
			}
			
			//Check if any reservations exist
			boolean reservationAvailable = false;
			//Loops through dates
			for (int i = 0; i < flightDates.size(); i++) {
				FlightDate date = flightDates.get(i);
				//Loops through flights looking for reservations
				for (int j = 0; j < date.getFlights().size(); j++) {
					Flight flight = date.getFlights().get(j);
					if (flight.getReservations().size() > 0) {
						reservationAvailable = true;
						break;
						
					}
					
				}
				
				if (reservationAvailable == true) {
					break;
					
				}
				
			}
			
			if (reservationAvailable == false) {
				System.out.println("There are no reservations available in the system \n");
				break;
				
			}
			
			//Get Customer
			Customer customer = selectCustomer();
			
			
			
			//Gathers all reservations made by customer
			ArrayList<Reservation> reservations = new ArrayList<Reservation>();
			//Loops through dates
			for (int i = 0; i < flightDates.size(); i++) {
				FlightDate date = flightDates.get(i);
				//Loops through flights
				for (int j = 0; j < date.getFlights().size(); j++) {
					Flight flight = date.getFlights().get(j);
					//Loops through reservations
					for (int k = 0; k < flight.getReservations().size(); k++) {
						Reservation res = flight.getReservations().get(k);
						//Gets customer reservations
						if (res.getCust().equals(customer)) {
							reservations.add(flight.getReservations().get(k));
							
						}
						
					}
					
				}
				
			}
			
			Scanner scan = new Scanner(System.in);
			//Loops until a valid reservation is selected
			int selection = 0;
			while (true) {
				try {
					for (int i = 0; i < reservations.size(); i++) {
						System.out.println((i + 1) + ") " + reservations.get(i).toString());
						
					}
					System.out.print("Select reservation (Enter number next to reservation): ");
					selection = scan.nextInt();
					System.out.println();
					if (selection > 0 && selection <= reservations.size()) {
						break;
					
					}
					System.out.println("Invalid selection \n");
				
				} catch (InputMismatchException IME) {
					System.out.println("\nMust enter a number \n");
					
				}
			
			}
			
			for (int i = 0; i < flightDates.size(); i++) {
				FlightDate date = flightDates.get(i);
				for (int j = 0; j < date.getFlights().size(); j++) {
					Flight flight = date.getFlights().get(j);
					for (int k = 0; k < flight.getReservations().size(); k++) {
						ArrayList<Reservation> reservation = flight.getReservations();
						if (reservation.get(k).equals(reservations.get(selection - 1))) {
							Airplane plane = flight.getPlane();
							plane.freeSeat(reservations.get(selection - 1).getSeat());
							flight.getReservations().remove(k);
							
						}
						
					}
					
				}
				
			}
			break;
		
		}
		
	}
	
}
