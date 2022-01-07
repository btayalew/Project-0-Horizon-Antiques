package com.revature.controllers;

import java.io.IOException;
import java.util.Scanner;

import com.revature.daos.ItemPostgres;
import com.revature.daos.OfferPostgres;
import com.revature.daos.PaymentPostgres;
import com.revature.daos.StatusPostgres;
import com.revature.models.Offer;
import com.revature.models.Payment;

public class PaymentController {
	
	public static PaymentPostgres pp = new PaymentPostgres();
	public static OfferPostgres op = new OfferPostgres();
	public static Offer offerByLoggedinCustomer = new Offer();
	public static ItemPostgres ip = new ItemPostgres();
	public static StatusPostgres sp = new StatusPostgres();
	
	public static void makePayment(Scanner sc) throws IOException {
		int custId = CustomerLoginController.cust.getId();
		Payment newPayment = new Payment();
		for(Offer o: op.getAll()) {
			if(o.getCustomerId() == custId) {
				System.out.println("Enter payment amount for item number: " + o.getItemId());
				Double payAmount = Double.parseDouble(sc.nextLine());
				newPayment.setItemId(o.getItemId());
				newPayment.setCustomerId(custId);
				newPayment.setPaymentAmount(payAmount);
				newPayment.setSalePrice(o.getOfferAmount());
				newPayment.getBalance();
				pp.add(newPayment);
			}
				
		}
		if(pp != null) {
			System.out.println("Payment receive. Thank you!");
		}else {
			System.out.println("You have not made offer to make payment for");
		}
	}
}
