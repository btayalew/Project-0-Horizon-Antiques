package com.revature.models;

import java.sql.Timestamp;
import java.util.Objects;

public class Offer {
	int offerId;
	int customerId;
	int itemId;
	double offerAmount;
	Timestamp offerDate;
	

	
	public Offer() {
		super();
	}

	public Offer(int offerId, int customerId, int itemId, double offerAmount, 
			Timestamp offerDate) {
		this.offerId = offerId;
		this.customerId = customerId;
		this.itemId = itemId;
		this.offerAmount = offerAmount;
		this.offerDate = offerDate;
	}
	public Offer(int customerId, int itemId, double offerAmount, Timestamp offerDate) {
		this.customerId = customerId;
		this.itemId = itemId;
		this.offerAmount = offerAmount;
		this.offerDate = offerDate;
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public int setCustomerId(int customerId) {
		return this.customerId = customerId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Timestamp getOfferDate() {
		return offerDate;
	}

	public void setOfferDate(Timestamp offerDate) {
		this.offerDate = offerDate;
	}

	public double getOfferAmount() {
		return offerAmount;
	}

	public void setOfferAmount(double offerAmount) {
		this.offerAmount = offerAmount;
	}


	@Override
	public int hashCode() {
		return Objects.hash(customerId, itemId, offerAmount, offerDate, offerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Offer))
			return false;
		Offer other = (Offer) obj;
		return customerId == other.customerId && itemId == other.itemId
				&& Double.doubleToLongBits(offerAmount) == Double.doubleToLongBits(other.offerAmount)
				&& Objects.equals(offerDate, other.offerDate) && offerId == other.offerId;
	}
	
	
}
