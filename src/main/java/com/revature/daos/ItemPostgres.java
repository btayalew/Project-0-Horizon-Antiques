package com.revature.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.Timestamp;

import com.revature.models.Item;
import com.revature.util.ConnectionUtil;

public class ItemPostgres implements GenericDao<Item> {

	public ItemPostgres() {
		super();
	}

	@Override
	public Item add(Item item) throws IOException {
		String sql = "insert into item (item_name, date_added, initial_price) "
				+ "values (?, CURRENT_TIMESTAMP, ?) returning item_id,item_name, date_added, initial_price;";
		Item newItem = null;
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, item.getItemName());
			ps.setDouble(2, item.getInitialPrice());
			
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
			int	genId = rs.getInt("item_id");
			String itemName = rs.getString("item_name");
			Timestamp dateAdded = rs.getTimestamp("date_added");
			Double initialPrice = rs.getDouble("initial_price");
			newItem = new Item(genId, itemName, dateAdded, initialPrice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return newItem;
	}

	@Override
	public int delete(int id) throws IOException {
		String sql = "delete from item where item_id = ? ";
		int result = 0;
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);	
			ps.setInt(1, id);
			if(ps.execute());
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Item getById(int id) throws IOException {
		String sql = "select * from item where item_id = ? ";
		Item singleItem = null;
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);	
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int itemId = rs.getInt("item_id");
				String itemName = rs.getString("item_name");
				Timestamp dateAdded = rs.getTimestamp("date_added");
				Double initialPrice = rs.getDouble("initial_price");		
				singleItem = new Item(itemId, itemName, dateAdded, initialPrice);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		return singleItem;
	}

	@Override
	public List<Item> getAll() throws IOException {
		String sql = "select * from item;";
		List<Item> items = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int itemId = rs.getInt("item_id");
				String itemName = rs.getString("item_name");
				Timestamp dateAdded = rs.getTimestamp("date_added");
				Double initialPrice = rs.getDouble("initial_price");
				
				Item newItem = new Item(itemId, itemName, dateAdded, initialPrice);
				items.add(newItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return items;
	}

	@Override
	public int update(Item t) {
		int result = 0;
		String sql = "update item set item_name = ?, item_price = ?, date_added = CURRENT_TIMESTAMP where item_id = ?;";
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);	
			ps.setString(1, t.getItemName());
			ps.setDouble(2, t.getInitialPrice());
			ps.setInt(3, t.getItemId());
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
