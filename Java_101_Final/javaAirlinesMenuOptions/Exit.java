package javaAirlinesMenuOptions;

import java.util.Scanner;

public class Exit extends MenuOption {
	//Ramon
    @Override
    public String toString() {
        return super.toString() + "Exit";
        
    }

    //Ramon
    @Override
    public void executeOption() {
    	String answer;
    	while (true) {
    	Scanner scan = new Scanner(System.in);
    	System.out.print("Are you sure you wish to exit? (yes or no): ");
    	answer = scan.nextLine();
    	System.out.println();
    	
    	if (answer.equals("Yes") || answer.equals("yes") || answer.equals("YES")) {
    		System.out.println("Exiting program");
            System.exit(0);
    		
    	} else if (answer.equals("No") || answer.equals("no") || answer.equals("NO")){
    		System.out.println("Returning to main menu. \n");
    		break;
    		
    	} else {
    		System.out.println("Invalid response. \n");
    		
    	}
    	
    	}
        
    }
    
}
