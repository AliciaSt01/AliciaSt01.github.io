package javaAirlinesMenu;

import javaAirlinesMenuOptions.*;

public class MainMenu extends Menu {
	//Main Menu
	public void loadOptions() {
		menuOptions.add(new ReservationMenuOption());
		menuOptions.add(new PlaneLoad());
		menuOptions.add(new FlightMenuOption());
		menuOptions.add(new AddFlightDate());
		menuOptions.add(new AirplaneMenuOption());
		menuOptions.add(new FileMenuOption());
		menuOptions.add(new Exit());
		
	}
	
}