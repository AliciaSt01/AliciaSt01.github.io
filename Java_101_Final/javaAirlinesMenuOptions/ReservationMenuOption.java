package javaAirlinesMenuOptions;

import javaAirlinesMenu.ReservationMenu;

public class ReservationMenuOption extends MenuOption {
	@Override
	public String toString() {
		return super.toString() + "Add or cancel reservation";
		
	}
	
	public void executeOption() {
		ReservationMenu menu = new ReservationMenu();
		menu.chooseOption();
		
	}
	
}
