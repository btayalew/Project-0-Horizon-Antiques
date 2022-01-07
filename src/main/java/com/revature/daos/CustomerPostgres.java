package com.revature.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;
import com.revature.util.ConnectionUtil;

public class CustomerPostgres implements GenericDao<Customer>{
	
	public Customer add(Customer customer) throws IOException {
		Customer newCustomer = null;
		String sql = "insert into customer (customer_name, customer_username, customer_password) "
				+ "values (?, ?, ?) returning *;";
		
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getUsername());
			ps.setString(3, customer.getPassword());
			
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				int genId = rs.getInt("customer_id");
				String custName = rs.getString("customer_name");
				String custUsername = rs.getString("customer_username");
				String custPassword = rs.getString("customer_password");
				newCustomer = new Customer(genId, custName, custUsername, custPassword);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		return newCustomer;
	}

	public int delete(int id) {
		String sql = "delete from customer where customer_id = ? ";
		int result = 0;
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);	
			ps.setInt(1, id);
			result = ps.executeUpdate(sql);
	        con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public Customer getById(int id) {
		String sql = "select * from customer where customer_id = ? ";
		Customer cust = null;
		
		try (Connection con = ConnectionUtil.getHardCodedConnection()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);	
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int cust_id = rs.getInt("customer_id");
				String name = rs.getString("customer_name");
				String username = rs.getString("customer_username");
				String password = rs.getString("customer_password");
				
				cust = new Customer(cust_id, name, username, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return cust;
	}
	public Customer getByUsernameAndPassword(String username, String password) {
		String sql = "select * from customer where customer_username = ? and " 
				+" customer_password = ?;";
		Customer cust = null;
		
		try (Connection con = ConnectionUtil.getHardCodedConnection()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int cust_id = rs.getInt("customer_id");
				String name = rs.getString("customer_name");
				String username1 = rs.getString("customer_username");
				String password1 = rs.getString("customer_password");
				
				cust = new Customer(cust_id, name, username1, password1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return cust;
	}
	public List<Customer> getAll() {
		String sql = "select * from customer ;";
		List<Customer> customers = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int customer_id = rs.getInt("customer_id");
				String customer_name = rs.getString("customer_name");
				String customer_username = rs.getString("customer_username");
				String customer_password = rs.getString("customer_password");
				
				Customer newCust = new Customer(customer_id, customer_name, customer_username, customer_password);
				customers.add(newCust);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return customers;
	}

	public int update(Customer customer) {

		int result = 0;
		// replace the values of the following variables to modify the respective fields
				
		String sql = "update item set customer_name = ?, customer_username = ?, customer_password = ?;";
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);	
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getUsername());
			ps.setString(3, customer.getPassword());
			
			result = ps.executeUpdate(sql);
	        con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
