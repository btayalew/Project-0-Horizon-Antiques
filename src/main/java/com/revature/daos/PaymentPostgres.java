package com.revature.daos;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.revature.models.Payment;
import com.revature.util.ConnectionUtil;

public class PaymentPostgres implements GenericDao<Payment> {

	Calendar calendar = Calendar.getInstance();
	Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());

	@Override
	public Payment add(Payment payment) throws IOException {
		
		Payment newPayment = null;
		String sql = "insert into payment (item_id, customer_id, payment_date, "
				+ "paid_amount, sale_price, balance) values (?,?, ?, ?, ?, ?) "
				+ "returning payment_id, item_id, customer_id, payment_date, "
				+ "paid_amount, sale_price, balance;";
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, payment.getItemId());
			ps.setInt(2, payment.getCustomerId());
			ps.setTimestamp(3, currentTimestamp);
			ps.setDouble(4, payment.getPaymentAmount());
			ps.setDouble(5, payment.getSalePrice());
			ps.setDouble(6, (payment.getSalePrice() - payment.getPaymentAmount()));
			
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				int paymentId = rs.getInt("payment_id");
				int itemId = rs.getInt("item_id");
				int custId = rs.getInt("customer_id");
				Timestamp payDate = rs.getTimestamp("payment_date");
				Double payAmount = rs.getDouble("paid_amount");
				Double salePrice = rs.getDouble("sale_price");
				Double balance = rs.getDouble("balance");
				newPayment = new Payment(paymentId, itemId, custId, payDate, payAmount, salePrice,balance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return newPayment;
	}

	@Override
	public int delete(int id) throws IOException {
		String sql = "delete from item where payment_id = ? ";
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

	@Override
	public Payment getById(int id) throws IOException {
		String sql = "select * from payment where payment_id = ? ";
		Payment singlePayment = null;
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);	
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int paymentId = rs.getInt("payment_id");
				int itemId = rs.getInt("item_id");
				int customerId = rs.getInt("customer_id");
				Timestamp paymentDate = rs.getTimestamp("payment_date");
				Double paymentAmount = rs.getDouble("payment_amount");
				Double salePrice = rs.getDouble("sale_price");
				Double balance = rs.getDouble("balance");
				singlePayment = new Payment(paymentId, itemId, customerId, paymentDate, 
						paymentAmount, salePrice, balance);

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		return singlePayment;
	}

	@Override
	public List<Payment> getAll() throws IOException {
		String sql = "select * from payment;";
		List<Payment> payments = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int paymentId = rs.getInt("payment_id");
				int itemId = rs.getInt("item_id");
				int customerId = rs.getInt("customer_id");
				Timestamp paymentDate = rs.getTimestamp("payment_date");
				Double paymentAmount = rs.getDouble("paid_amount");
				Double salePrice = rs.getDouble("sale_price");
				Double balance = rs.getDouble("balance");
				
				Payment newPayment= new Payment(paymentId, itemId, customerId, 
						paymentDate, paymentAmount, salePrice, balance);
				payments.add(newPayment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return payments;
	}

	@Override
	public int update(Payment p) {
		int result = 0;
		
		String sql = "update payment set item_id = ?, customer_id = ?, payment_date = CURRENT_TIMESTAMP,"
				+ "payment_amount = ?, sale_price = ?, balance = ? where payment_id = ?;";
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);	
			
			ps.setDouble(1, p.getPaymentId());
			ps.setInt(2, p.getItemId());
			ps.setInt(3, p.getCustomerId());
			ps.setDouble(4, p.getPaymentAmount());
			ps.setDouble(5, p.getSalePrice());
			ps.setDouble(6, p.getBalance());
			
			
			result = ps.executeUpdate();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
