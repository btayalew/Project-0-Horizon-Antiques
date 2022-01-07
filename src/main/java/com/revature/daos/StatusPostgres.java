package com.revature.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Status;
import com.revature.util.ConnectionUtil;

public class StatusPostgres  implements GenericDao<Status> {

	@Override
	public Status add(Status s) throws IOException {
		
		Status newStatus = null;
		String sql = "insert into item_status (item_id, status, price, date_updated, udated_by) "
				+ "values (?, ?, ?, CURRENT_TIMESTAMP,?) returning status_id, item_id, status,"
				+ "price, date_updated, udated_by;";

		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, s.getItemId());
			ps.setString(2, s.getStatus());
			ps.setDouble(3, s.getPrice());
			
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				int genId = rs.getInt("status_id");
				int itemId = rs.getInt("item_id");
				String status = rs.getString("status");
				Double price = rs.getDouble("price");
				Timestamp dateUpdated = rs.getTimestamp("date_updated");
				int updatedBy = rs.getInt("updated_by");
				
				newStatus = new Status(genId, itemId, status, price, dateUpdated, updatedBy);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return newStatus;
	}

	@Override
	public int delete(int id) throws IOException {
		String sql = "delete from item_status where status_id = ? ";
		int result = 0;
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);	
			ps.setInt(1, id);
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Status getById(int id) throws IOException {
		String sql = "select * from item_status where status_id = ? ";
		Status singleItemStatus = null;
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);	
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int statusId = rs.getInt("status_id");
				int itemId = rs.getInt("item_id");
				String status = rs.getString("status");
				Double price = rs.getDouble("price");
				Timestamp date = rs.getTimestamp("date_updated");
				int updatedBy = rs.getInt("updated_by");

		
				singleItemStatus = new Status(statusId, itemId, status, price, date, updatedBy);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		return singleItemStatus;
	}

	@Override
	public List<Status> getAll() throws IOException {
		
		List<Status> newStatus = new ArrayList<>();
		String sql = "select * from item_status;";
		Status state = null;
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int statusId = rs.getInt("status_id");
				int itemId = rs.getInt("item_id");
				String status = rs.getString("status");
				Double price = rs.getDouble("price");
				Timestamp date = rs.getTimestamp("date_updated");
				int updatedBy = rs.getInt("updated_by");
				
				
				state = new Status(statusId, itemId, status, price, date, updatedBy);
				newStatus.add(state);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return newStatus;
	}

	@Override
	public int update(Status s) {
		int result = 0;		
		String sql = "update item_status set status = ?, price = ?, date_updated = CURRENT_TIMESTAMP, updated_by = ?;";
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);	
			ps.setString(1, s.getStatus());
			ps.setDouble(2, s.getPrice());
			ps.setInt(3, s.getUpdatedBy());
			
			result = ps.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
