package com.revature.services;

import java.io.IOException;
import java.util.Scanner;

import com.revature.controllers.ItemController;
import com.revature.controllers.PaymentController;
import com.revature.controllers.RegisterController;
import com.revature.daos.CustomerPostgres;
import com.revature.exceptions.LoginException;
import com.revature.exceptions.UsernameAlreadyExistsException;
import com.revature.models.Customer;


public class CustomerService {
	private static CustomerPostgres cp = new CustomerPostgres();
	static Scanner scan = new Scanner(System.in);
	
	public Customer customerLogin(String uname, String pword) throws LoginException, IOException {
		Customer cust = cp.getByUsernameAndPassword(uname, pword);
		if(cust == null) {
			RegisterController.run(scan);
		}
		else if(cust.setUsername(uname).equals(uname) && cust.getPassword().equals(pword)) {
			return cust;
		}
		throw new LoginException();
	}
	
	public Customer register(Customer c) throws UsernameAlreadyExistsException, IOException{
		for(Customer cust: cp.getAll()) {
			if(!cust.getUsername().equals(c.getUsername())) {
				return cp.add(c);
			}
		}
		throw new UsernameAlreadyExistsException();
	}
	public static void services() throws IOException {
		System.out.println();
		System.out.println("Please choose from the following options.");
		System.out.println("\n\n1. View items and shop\n2. Make payment");
		String choice = scan.nextLine();
		switch(choice) {
			case "1":
				ItemController.printListOfItems(scan);
				System.out.println();
				break;
			case "2":
				PaymentController.makePayment(scan);
				System.out.println();
				break;
		}
	}
	

}