package com.revature.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import com.revature.daos.ItemPostgres;
import com.revature.models.Item;

public class ItemService {
	private static ItemPostgres ip = new ItemPostgres();
	Formatter formatter = new Formatter();
	public List<String> availableItems() throws IOException {
		List<Item> items = ip.getAll();
		List<String> listOfItems = new ArrayList<>();
		for(Item i : items) {
			int itemId = i.getItemId();
			String itemName = i.getItemName();
			Double itemPrice = i.getInitialPrice();
			String singleItem = String.format("%3d %20s %23f", itemId, itemName, itemPrice);
			listOfItems.add(singleItem);	
		}
		return listOfItems;
	}

}
