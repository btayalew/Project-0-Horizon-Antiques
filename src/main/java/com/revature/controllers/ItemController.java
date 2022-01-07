package com.revature.controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Scanner;

import com.revature.daos.ItemPostgres;
import com.revature.daos.OfferPostgres;
import com.revature.models.Item;
import com.revature.models.Offer;
import com.revature.services.ItemService;

public class ItemController {

	public static Scanner sc = new Scanner(System.in);
	public static ItemService is = new ItemService();
	public static ItemPostgres ip = new ItemPostgres();
	public static OfferPostgres newOffer = new OfferPostgres();
	public static Timestamp CURRENT_TIMESTAMP;
	
	public static void printListOfItems(Scanner sc) throws IOException {
		for(String item: is.availableItems()) {
			System.out.println(item);
		}
		System.out.println();
		Offer offer = new Offer();
		offer.setCustomerId(CustomerLoginController.cust.getId());
		System.out.println("Enter item number: ");
		offer.setItemId(Integer.parseInt(sc.nextLine()));
		System.out.println("Enter offer amount: ");
		offer.setOfferAmount(Double.parseDouble(sc.nextLine()));
		offer.setOfferDate(CURRENT_TIMESTAMP);
		newOffer.add(offer);
	}
	
	public static void addItems(Scanner sc) throws IOException {
		System.out.println("Please enter name and initial price of items");
		System.out.println("Name of item: ");
		String itemName = sc.nextLine();
		System.out.println("Initial price: ");
		Double initialPrice = Double.parseDouble(sc.nextLine());
		Item addedItem = new Item(itemName, initialPrice);
		ip.add(addedItem);
	}
	public static void removeItem(Scanner sc) throws IOException {
		System.out.println("Please enter the id of item you want to remove from the list");
		System.out.println("Id of item: ");
		int itemId = Integer.parseInt(sc.nextLine());
		ip.delete(itemId);
	}
}
