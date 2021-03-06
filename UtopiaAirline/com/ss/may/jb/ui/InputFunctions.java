package com.ss.may.jb.ui;

import java.util.Scanner;

public class InputFunctions {
	//Checks to see if input is between bounds and returns a boolean
	public boolean validateInput(int upper, int lower, int input) {
		if(input > upper || input < lower) {
			System.out.println("Input is out of bounds! Must be between " + lower + " and " + upper);
			return false;
		}
		return true;
	}
	//Continuously prompts until user inputs a valid number
	public int getInput(int upper, int lower) {
		Scanner scan = new Scanner(System.in);
		int input = 0;
		boolean valid = false;

		while(valid == false) {
			//Error checking for an integer
			System.out.print("Input: ");
			while(!scan.hasNextInt()) {
				System.out.println("Invalid number, try again");
				valid = false;
				scan.nextLine();//Flush out invalid number
			}
			input = scan.nextInt();
			valid = validateInput(upper, lower, input);
		}
		
		return input;
	}
	//Continuously prompts until user inputs an integer
	public int getInput() {
		Scanner scan = new Scanner(System.in);
		int input = 0;
		
		//Error checking for an integer
		while(!scan.hasNextInt()) {
			System.out.println("Invalid number, try again");
			System.out.print("Input: ");
			scan.nextLine();//Flush out invalid number
		}
		input = scan.nextInt();
		return input;
	}
	//Continuously prompts until user inputs an float
	public float getFloatInput() {
		Scanner scan = new Scanner(System.in);
		float input = 0;
		
		//Error checking for an integer
		while(!scan.hasNextFloat()) {
			System.out.println("Invalid number, try again");
			System.out.print("Input: ");
			scan.nextLine();//Flush out invalid number
		}
		input = scan.nextFloat();
		return input;
	}
	//Continuously prompts until user inputs an integer
	public String getStringInput() {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		return input;
	}
	//Checks to see if user inputs the word quit, returns a boolean
	public boolean validateQuit(String input) {
		if(input.equalsIgnoreCase("quit")) {
			return true;
		}
		return false;
	}

}
