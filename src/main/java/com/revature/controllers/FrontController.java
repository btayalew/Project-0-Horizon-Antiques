package com.revature.controllers;

import java.io.IOException;
import java.util.Scanner;

import com.revature.exceptions.LoginException;

public class FrontController {
	
	private static Scanner sc = new Scanner(System.in);
	public static void welcome(Scanner sc) throws IOException, LoginException {
		boolean run = true;
		while(run) {
			System.out.println("Welcome to Horizon Antiques! Please select from the following options:"
					+ "\n\nCUSTOMERS				EMPLOYEES"
					+ "\n==============================          ==========="
					+ "\n1: Returning customer? Login		4: Login"
					+ "\n2: New customer? Register		5: Exit"
					+ "\n3: Exit");
			System.out.println();
			String choice = sc.nextLine();
			switch(choice) {
			case "1":
				CustomerLoginController.login(sc);
				System.out.println();
				break;
			case "2":
				RegisterController.run(sc);
				System.out.println();
				break;
			case "3":
				System.out.println("Thanks for visiting! Come again.");
				run = false;
				break;
			case "4":
				EmployeeLoginController.run(sc);
				break;
			case "5":
				System.out.println("Goodbye!");
				run = false;
				break;
			default:
				System.out.println("Invalid input.");
				System.out.println();
			}
		}
		sc.close();
	}
}
