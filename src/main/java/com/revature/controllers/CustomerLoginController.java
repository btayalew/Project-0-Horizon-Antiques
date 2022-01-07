package com.revature.controllers;

import java.io.IOException;
import java.util.Scanner;
import com.revature.exceptions.LoginException;
import com.revature.models.Customer;
import com.revature.services.CustomerService;

public class CustomerLoginController {
	
	private static CustomerService cs = new CustomerService();
	public static Customer cust = new Customer();
	public static void login(Scanner sc) throws IOException, LoginException {	
		System.out.println();
		System.out.println("CUSTOMER LOGIN PAGE");
		System.out.println("====================");
		System.out.println();
		System.out.println("Please enter your username:");
		String username = sc.nextLine();
		System.out.println("Please enter your password:");
		String password = sc.nextLine();
		cust =cs.customerLogin(username, password);
		if(cust != null) {
			CustomerService.services();
		}
	}
}
