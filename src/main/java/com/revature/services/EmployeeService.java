package com.revature.services;

import java.io.IOException;
import java.util.Scanner;
import com.revature.daos.EmployeePostgres;
import com.revature.exceptions.LoginException;
import com.revature.models.Employee;

public class EmployeeService {

	public static Scanner sc = new Scanner(System.in);
	public static EmployeePostgres ep = new EmployeePostgres();
		
	public Employee empLogin(String username, String password) throws LoginException {
		Employee emp = ep.getByUsernameAndPassword(username,password);
		if(emp.setempUsername(username).equals(username) && emp.setempPassword(password).equals(password)) {
			return emp;
		}
		throw new LoginException();
	}

	public static void addEmployee(Scanner sc) throws IOException{
		System.out.println("To add new employee enter the name of the employee: ");
		String empName = sc.nextLine();
		System.out.println("Assign username to the employee: ");
		String empUsername = sc.nextLine();
		System.out.println("Assign password to the employee: ");
		String empPassword = sc.nextLine();
		System.out.println("Assign manager the employee. Enter manager's Employee Id: ");
		int manager = Integer.parseInt(sc.nextLine());
		System.out.println("Assign role to the employee: ");
		String empRole = sc.nextLine();
		Employee newEmp = new Employee(empName,empUsername,empPassword,manager,empRole);
		ep.add(newEmp);
	}
	
	public static int removeEmployee(Scanner sc) throws IOException{
		System.out.println("To remove an employee enter the id of the employee: ");
		int id = Integer.parseInt(sc.nextLine());
		return ep.delete(id);
	}
	
}

