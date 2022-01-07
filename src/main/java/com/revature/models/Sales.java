package com.revature.models;

import java.sql.Timestamp;

public class Sales {
	
	String item_name;
	String item_status;
	Double payment;
	Timestamp date;
	
	
	public Sales() {
		super();
	}

	public Sales(String itemName, String status, Double paidAmount) {

	}
}
