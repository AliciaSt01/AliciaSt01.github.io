package javaAirlinesMenuOptions;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javaAirlines.*;

public class AddPlane extends MenuOption{
	@Override
	public String toString() {
		return super.toString() + "Add plane to inventory";
		
	}
	
	//Used to add plane
	public void executeOption() {
		Scanner scan = new Scanner(System.in);
		
		//Gets name of Airplane
		System.out.print("What is the name of this airplane (Serial or Model number): ");
		String planeName = scan.nextLine();
		System.out.println();
		
		//Gets how many classes airplane has
		int numClasses;
		while (true) {
			try {
				System.out.print("How many classes does this airplane have: ");
				numClasses = scan.nextInt();
				System.out.println();
				break;
				
			} catch (InputMismatchException IME) {
				System.out.println("\nMust enter a number \n");
				scan.nextLine();
				
			}
			
		}

		TravelClass classes[] = new TravelClass[numClasses];
		
		//Arrays to hold travel class name, rows, and number of seats for each class
		String className[] = new String[numClasses];
		int rows[] = new int[numClasses];
		int numSeats[] = new int[numClasses];
		
		//Loops to get name of each travel class, and number of rows and seats per row
		int maxRowLength = 0;
		for (int i = 0; i < numClasses; i++) {
			//Gets travel class names
			scan.nextLine();
			System.out.print("What is name of class #" + (i + 1) + ": ");
			className[i] = scan.nextLine();
			System.out.println();
			
			//Gets how many rows
			while (true) {
				try {
					System.out.print("How many rows are in this class: ");
					rows[i] = scan.nextInt();
					System.out.println();
					break;
					
				} catch (InputMismatchException IME) {
					System.out.println("\nMust enter a number \n");
					scan.nextLine();
					
				}
				
			}
			
			//Gets how many seats in each row
			while (true) {
				try {
					System.out.print("How many seats in each row: ");
					numSeats[i] = scan.nextInt();
					System.out.println();
					break;
					
				} catch (InputMismatchException IME) {
					System.out.println("\nMust enter a number \n");
					scan.nextLine();
					
				}
				
			}
			
			//Sets maxRowLength
			if (numSeats[i] > maxRowLength) {
				maxRowLength = numSeats[i];
				
			}
			
		}
		
		int rowCounter = 0;
		//Creates each TravelClass and creates empty Seats with names
		for (int i = 0; i < classes.length; i++) {
			//Creates seats object to hold each Seat
			Seat seats[][] = new Seat[rows[i]][numSeats[i]];
			
			//Loops through each row
			for (int r = 0; r < rows[i]; r++) {
				rowCounter++;
				char letter = 'A';
				
				//Loops through each seat, naming it
				for (int s = 0; s < numSeats[i]; s++) {
					seats[r][s] = new Seat("" + rowCounter + letter);
					letter += 1;
					
				}
				
				//Renames back half of shorter rows
				if (numSeats[i] < maxRowLength) {
					letter = 'A';
					letter += maxRowLength - 1;
					for (int s = numSeats[i] -1; s >= numSeats[i] / 2; s--) {
						seats[r][s].setName("" + rowCounter + letter);
						letter -= 1;
						
					}
					
				}	
				
			}
			classes[i] = new TravelClass(className[i], seats);
			
		}
		//Adds plane
		planes.add(new Airplane(planeName, classes));
		return;
		
	}
	
}
