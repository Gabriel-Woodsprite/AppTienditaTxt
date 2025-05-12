package org.example.apptienditatxt.interfaces;

import java.util.List;

public interface DaoInterface<Item, ID> {
	void createItem();
	Item readItem(int itemId);
	List<Item> readAllItems();
	void updateItem();
	void deleteItem();
}
