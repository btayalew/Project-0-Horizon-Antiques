package com.revature.models;

import java.sql.Timestamp;
import java.util.Objects;

public class Status {
	int statusId;
	int itemId;
	String status;
	Double price;
	Timestamp date;
	int updatedBy;
	
	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Status(int statusId, int itemId, String status, Double price, Timestamp date, int updatedBy) {
		super();
		this.statusId = statusId;
		this.itemId = itemId;
		this.status = status;
		this.price = price;
		this.date = date;
		this.updatedBy = updatedBy;
	}
	public Status(int itemId, String status, Double price, Timestamp date, int updatedBy) {
		this.itemId = itemId;
		this.status = status;
		this.price = price;
		this.date = date;
		this.updatedBy = updatedBy;
	}
	public Status(int itemId, String status, Double price, int updatedBy) {
		this.itemId = itemId;
		this.status = status;
		this.price = price;
		this.updatedBy = updatedBy;
	}
	public Status(int statusId, int itemId, String status, Double price, int updatedBy) {
		this.statusId = statusId;
		this.itemId = itemId;
		this.status = status;
		this.price = price;
		this .updatedBy = updatedBy;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemId, date, price, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Status))
			return false;
		Status other = (Status) obj;
		return itemId == other.itemId && Objects.equals(date, other.date) && Objects.equals(price, other.price)
				&& Objects.equals(status, other.status);
	}
	
	
}
