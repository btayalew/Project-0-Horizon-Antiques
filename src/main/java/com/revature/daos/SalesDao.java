package com.revature.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Sales;
import com.revature.util.ConnectionUtil;

public class SalesDao {
	public List<Sales> viewSales() throws SQLException, IOException {
		List<Sales> sales = new ArrayList<>();
	String sql = "Select item.item_name, item_status.status, payment.paid_amount"
			+ "From item Inner Join item_status"
			+ "ON item.item_id = item_status.item_id"
			+ "Inner Join payment ON payment.item_id = item.item_id"
			+ "Having status = 'sold';";
			
	try (Connection con = ConnectionUtil.getConnectionFromFile()){
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			String itemName = rs.getString("item.item_name");
			String status = rs.getString("item_status.status");
			Double paidAmount = rs.getDouble("payment.paid_amount");
			
			Sales newSales = new Sales(itemName, status, paidAmount);
			sales.add(newSales);
		}
	}
	return sales;
	}
}

