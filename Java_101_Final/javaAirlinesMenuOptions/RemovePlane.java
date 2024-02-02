package javaAirlinesMenuOptions;

import javaAirlines.Airplane;

public class RemovePlane extends MenuOption {
	@Override
	public String toString() {
		return super.toString() + "Remove Airplane from inventory (Prevents new flights from being made, but keeps current schedule)";
		
	}
	
	public void executeOption() {
		if (planes.size() == 0) {
			System.out.println("There are no planes in the system \n");
			return;
			
		}
		
		//Get airplane to remove
		Airplane plane = selectAirplane();
		
		//Remove plane from inventory
		removeAirplane(plane);
		System.out.println("Airplane has been removed from inventory \n");
		
		return;
		
	}
	
}
