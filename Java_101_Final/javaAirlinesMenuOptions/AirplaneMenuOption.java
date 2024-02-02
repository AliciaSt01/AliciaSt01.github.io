package javaAirlinesMenuOptions;

import javaAirlinesMenu.AirplaneMenu;

public class AirplaneMenuOption extends MenuOption {
	@Override
	public String toString() {
		return super.toString() + "Add or Remove Airplanes from inventory";
	}
	
	//Airplane submenu
	public void executeOption() {
		AirplaneMenu menu = new AirplaneMenu();
		menu.chooseOption();
		
	}

}
