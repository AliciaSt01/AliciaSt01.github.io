package javaAirlinesMenuOptions;

import javaAirlines.Airplane;
import javaAirlines.Flight;
import javaAirlines.FlightDate;

public class CreateFlight extends MenuOption {
	@Override
	public String toString() {
		return super.toString() + "Create a new flight";
		
	}
	
	public void executeOption() {
		
		while (true) {
			
			if (flightDates.size() == 0) {
				System.out.println("There are no dates available \n");
				break;
				
			}
			
			boolean planeAvailable = false;
			for (int i = 0; i < flightDates.size(); i++) {
				if (flightDates.get(i).getFlights().size() < planes.size()) {
					planeAvailable = true;
					break;
					
				}
				
			}
			
			if (planeAvailable == false) {
				System.out.println("There are no planes available for any date in the system \n");
				break;
				
			}
		
			//Gets date for flight
			FlightDate date;
			while (true) {
				date = selectFlightDate();
				
				if (date.getFlights().size() != planes.size()) {
					break;
					
				}
				System.out.println("There are no planes available for that date (One flight per plane per day) \n");
			
			}
			
			//Gets airplane for flight
			Airplane plane;
			while (true) {
				plane = selectAirplane();
				
				boolean validPlane = true;
				for (int i = 0; i < date.getFlights().size(); i++) {
					if (date.getFlights().get(i).getPlane().getName().equals(plane.getName())) {
						validPlane = false;
						break;
						
					}
					
				}
				
				if (validPlane == true) {
					break;
					
				}
				System.out.println("Plane already booked for that date \n");
				
			}
			//Creates flight
			Flight flight = new Flight(new Airplane(plane), date.getDate());
			
			//Adds flight to flightDate
			date.addFlight(flight);
			System.out.println("Flight has been created \n");
			
			return;
			
		}
		
	}
	
}
