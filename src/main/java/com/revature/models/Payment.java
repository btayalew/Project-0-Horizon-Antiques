package com.revature.models;
import java.sql.Timestamp;
import java.util.Objects;
public class Payment {
	int paymentId;
	int itemId;
	int customerId;
	Timestamp paymentDate;
	double paymentAmount;
	double salePrice;
	double balance;
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(int paymentId, int itemId, int customerId, Timestamp paymentDate, 
			double paymentAmount, double salePrice, double balance) {
		this.paymentId = paymentId;
		this.itemId = itemId;
		this.customerId = customerId;
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
		this.salePrice = salePrice;
		this.balance = balance;
	}
	public Payment(int paymentId, int itemId, int customerId, 
			double paymentAmount, double salePrice, double balance) {
		this.paymentId = paymentId;
		this.itemId = itemId;
		this.customerId = customerId;
		this.paymentAmount = paymentAmount;
		this.salePrice = salePrice;
		this.balance = balance;
	}
	public Payment(int itemId, int customerId, double paymentAmount, double salePrice, 
			double balance) {
			
		this.itemId = itemId;
		this.customerId = customerId;
		this.paymentAmount = paymentAmount;
		this.salePrice = salePrice;
		this.balance = balance;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPayementId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Timestamp getPayementDate() {
		return paymentDate;
	}

	public void setPayementDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(balance, customerId, itemId, paymentDate, paymentId, paymentAmount, salePrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Payment))
			return false;
		Payment other = (Payment) obj;
		return Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& customerId == other.customerId && itemId == other.itemId
				&& Objects.equals(paymentDate, other.paymentDate) && paymentId == other.paymentId
				&& Double.doubleToLongBits(paymentAmount) == Double.doubleToLongBits(other.paymentAmount)
				&& Double.doubleToLongBits(salePrice) == Double.doubleToLongBits(other.salePrice);
	}
	
	
}
