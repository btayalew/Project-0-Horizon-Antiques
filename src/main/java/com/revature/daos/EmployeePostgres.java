package com.revature.daos;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeePostgres implements GenericDao<Employee> {

	public Employee add(Employee employee) throws IOException {
		Employee newEmp = null;
		
		String sql = "insert into employee (emp_name, emp_username, emp_password, "
				+ "manager,emp_role) values (?, ?, ?, ?, ?) returning emp_id,"
				+ "emp_name, emp_username, emp_password, manager,emp_role;";
		
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, employee.getempName());
			ps.setString(2, employee.getempUsername());
			ps.setString(3, employee.getempPassword());
			ps.setInt(4, employee.getManager());
			ps.setString(5, employee.getEmpRole());
			
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				int genId = rs.getInt("emp_id");
				String empName = rs.getString("emp_name");
				String empUsername = rs.getString("emp_username");
				String empPassword = rs.getString("emp_password");
				int manager = rs.getInt("manager");
				String empRole = rs.getString("emp_role");
				
				newEmp = new Employee(genId, empName, empUsername, empPassword, manager, empRole);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		return newEmp;
	}

	public int delete(int id) {
		String sql = "delete from employee where emp_id = ? ";
		int result = 0;
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);	
			ps.setInt(1, id);
			if(ps.execute()) result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Employee getById(int id) {
		String sql = "select * from employee where emp_id = ? ";
		Employee emp = null;
		
		try (Connection con = ConnectionUtil.getHardCodedConnection()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);	
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int emp_id = rs.getInt("emp_id");
				String emp_name = rs.getString("emp_name");
				String emp_username = rs.getString("emp_username");
				String emp_password = rs.getString("emp_password");
				int manager = rs.getInt("manager");
				String empRole = rs.getString("emp_role");
				
				emp = new Employee(emp_id, emp_name, emp_username, emp_password, manager, empRole);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return emp;
	}

	public List<Employee> getAll() {
		String sql = "select * from employee;";
		List<Employee> employees = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int emp_id = rs.getInt("emp_id");
				String emp_name = rs.getString("emp_name");
				String emp_username = rs.getString("emp_username");
				String emp_password = rs.getString("emp_password");
				int manager = rs.getInt("manager");
				String empRole = rs.getString("emp_role");
				
				Employee newEmp = new Employee(emp_id, emp_name, emp_username, emp_password, manager, empRole);
				employees.add(newEmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return employees;
	}

	public int update(Employee employee) {

		int result = 0;
		String sql = "update employee set emp_name = ?, emp_username = ?, "
				+ "emp_password = ?, emp_role = ? where emp_id = ?;";
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);	
			ps.setInt(1, employee.getempId());
			ps.setString(2, employee.getempName());
			ps.setString(3, employee.getempUsername());
			ps.setString(4, employee.getempPassword());
			ps.setString(5, employee.getEmpRole());
			
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Employee getByUsernameAndPassword(String username, String password) {
		String sql = "select * from employee where emp_username = ? and emp_password = ?;"; 
				
		Employee emp = null;
		
		try (Connection con = ConnectionUtil.getHardCodedConnection()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int empId = rs.getInt("emp_id");
				String empName = rs.getString("emp_name");
				String empUsername = rs.getString("emp_username");
				String empPassword = rs.getString("emp_password");
				int manager = rs.getInt("manager");
				String empRole = rs.getString("emp_role");
				
				emp = new Employee(empId, empName, empUsername, empPassword, manager, empRole);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(emp.getempName());
		return emp;
	}


}
