package javaAirlinesMenu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import javaAirlinesMenuOptions.MenuOption;

/**
 * Menu Class
 * Used to create Menu objects
 * Should be used to create subclasses
 */
public abstract class Menu {
	/**
	 * Holds menu options of menu
	 */
	protected ArrayList<MenuOption> menuOptions;
	
	
	/**
	 * Empty Constructor - Initializes Menu
	 */
	public Menu() {
		menuOptions = new ArrayList<MenuOption>();
		loadOptions();
		createOptionNums();
		
	}
	
	/**
	 * Used to load options of Menu
	 * Should be implemented in subclass
	 */
	protected abstract void loadOptions();
	
	/**
	 * Used to create option numbers of Menu
	 */
	protected void createOptionNums() {
		for (int i = 0; i < menuOptions.size(); i++) {
			menuOptions.get(i).setOptionNum(i + 1);
		
		}
		
	}
	

	/**
	 * Used to select option from Menu
	 */
	public void chooseOption() {
		Scanner scan = new Scanner(System.in);
		int option = 0;
		//Loops until a valid option is entered
		do {
			while(true) {
				try {
					//Print options
					System.out.println("Available options: ");
					displayOptions();
					
					//Get selection
					System.out.print("Enter number of desired operation: ");
					option = scan.nextInt();
					System.out.println();
					break;
					
				} catch (InputMismatchException IME) {
					System.out.println("\nMust enter a number \n");
					scan.nextLine();
					
				}
			
			}
		
		} while(validOption(option) == false);
		menuOptions.get(option - 1).executeOption();
		
	}
	
	
	/**
	 * Used to display available options of Menu
	 */
	protected void displayOptions() {
		//Loops through menuOptions, printing description
		for (int i = 0; i < menuOptions.size(); i++) {
			System.out.println(menuOptions.get(i).toString());
			
		}
		System.out.println();
		
	}
	
	/**
	 * Used to check if selected option is valid
	 * @param int - option selected by user
	 * @return boolean - if option is valid
	 */
	protected boolean validOption(int option) {
		if (option > 0 && option <= menuOptions.size()) {
			return true;
			
		} else {
			System.out.println("Invalid selection. \n");
			return false;
			
		}
		
	}

}