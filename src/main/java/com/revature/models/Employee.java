package com.revature.models;

import java.util.Objects;

public class Employee {

	private int empId;
	private String empName;
	private String empUsername;
	private String empPassword;
	private int manager;
	private String empRole;
	
	public Employee() {
		super();
	}

	public Employee(int empId, String empName, String empUsername, String empPassword, int manager, String empRole) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empUsername = empUsername;
		this.empPassword = empPassword;
		this.manager = manager;
		this.empRole = empRole;
	}

	public Employee(String empName, String empUsername, String empPassword, int manager, String empRole) {
		super();
		this.empName = empName;
		this.empUsername = empUsername;
		this.empPassword = empPassword;
		this.manager = manager;
		this.empRole = empRole;
	}

	public Employee(int manager) {
		// TODO Auto-generated constructor stub
	}

	public int getempId() {
		return empId;
	}

	public void setempId(int empId) {
		this.empId = empId;
	}

	public String getempName() {
		return empName;
	}

	public void setempName(String empName) {
		this.empName = empName;
	}

	public String getempUsername() {
		return empUsername;
	}

	public String setempUsername(String empUsername) {
		return this.empUsername = empUsername;
	}

	public String getempPassword() {
		return empPassword;
	}

	public String setempPassword(String empPassword) {
		return this.empPassword = empPassword;
	}

	public int getManager() {
		return manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

	public String getEmpRole() {
		return empRole;
	}

	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}

	@Override
	public int hashCode() {
		return Objects.hash(empName, empPassword, empRole, empUsername);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Employee))
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(empName, other.empName) && Objects.equals(empPassword, other.empPassword)
				&& Objects.equals(empRole, other.empRole) && Objects.equals(empUsername, other.empUsername);
	}
	
}
