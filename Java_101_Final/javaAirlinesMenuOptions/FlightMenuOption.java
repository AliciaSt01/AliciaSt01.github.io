package javaAirlinesMenuOptions;

import javaAirlinesMenu.FlightMenu;

public class FlightMenuOption extends MenuOption{
	@Override
	public String toString() {
		return super.toString() + "Create, Reschedule, or Remove Flights";
		
	}
	
	public void executeOption() {
		FlightMenu menu = new FlightMenu();
		menu.chooseOption();
		
	}
	
}
