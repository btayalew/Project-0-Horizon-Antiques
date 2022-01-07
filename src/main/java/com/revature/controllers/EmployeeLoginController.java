package com.revature.controllers;

import java.io.IOException;
import java.util.Scanner;

import com.revature.exceptions.LoginException;
import com.revature.models.Employee;
import com.revature.services.EmployeeService;
import com.revature.services.PaymentService;

public class EmployeeLoginController {

	private static EmployeeService es = new EmployeeService();
	private static Employee emp;
	private static Scanner empScan;
	
	public static void run(Scanner scan) throws IOException, LoginException {
		empScan = scan;
		System.out.println("EMPLOYEE LOGIN PAGE");
		System.out.println();
		System.out.println("Please enter your username:");
		String username = empScan.nextLine();
		System.out.println("Please enter your password:");
		String password = empScan.nextLine();
		emp = es.empLogin(username, password);
		if(emp.getEmpRole().equals("Manager")) {
			System.out.println("Please select from the following options and enter the option number: "
								+ "\n"
								+ "\n1. Add item"
								+ "\n2. Remove item"
								+ "\n3. Approve offer"
								+ "\n4. View payment"
								+ "\n5. Add new employee"
								+ "\n6. Remove employee"
								+ "\n7. View sales history");
			
			String managerWantsTo = empScan.nextLine();
			switch(managerWantsTo) {
			case "1":
				ItemController.addItems(empScan);
				System.out.println();
				break;
			case "2":
				ItemController.removeItem(empScan);
				System.out.println();
				break;
			case "3":
				
				System.out.println();
				break;
			case "4":
				try {
					System.out.println();
					System.out.println(String.format("%1$45s","Sales History\n"));
					System.out.println();
					System.out.println(String.format("%-10s %-10s %-14s %-14s %-10s %-10s %-10s",
							"PaymentID","Item_id","CustomerID", "Date", "Amount", "Price", "Balance"));
					System.out.println("-".repeat(82));
					for(String payment: PaymentService.viewPayment()) {
						System.out.println(payment);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println();
				break;
			case "5":
				EmployeeService.addEmployee(empScan);
				System.out.println();
				break;
			case "6":
				EmployeeService.removeEmployee(empScan);
				System.out.println();
				break;
			case "7":
				
				System.out.println();
				break;
			}
		}
		else{
			System.out.println("Please select from the following options and enter the option number: "
					+ "\n"
					+ "\n1. Add item"
					+ "\n2. Remove item"
					+ "\n3. Approve offer"
					+ "\n4. View payment");
			String employeeWantsTo = empScan.nextLine();
			switch(employeeWantsTo) {
			case "1":
				ItemController.addItems(empScan);
				System.out.println();
				break;
			case "2":
				ItemController.removeItem(empScan);
				System.out.println();
				break;
			case "3":
				
				System.out.println();
				break;
			case "4":
				try {
					System.out.println();
					System.out.println(String.format("%1$45s","Sales History\n"));
					System.out.println();
					System.out.println(String.format("%-10s %-10s %-14s %-14s %-10s %-10s %-10s",
							"PaymentID","Item_id","CustomerID", "Date", "Amount", "Price", "Balance"));
					System.out.println("-".repeat(82));
					for(String payment: PaymentService.viewPayment()) {
						System.out.println(payment);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println();
				break;
			}
		}
	}
}
