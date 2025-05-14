package org.example.apptienditatxt.interfaces;

import org.example.apptienditatxt.model.Product;

import java.util.List;

public interface DaoInterface {
	void createItem(String path, List<Product> productos) throws Exception;
	Product readItem(String path);
	List<Product> readAllItems(String path);
	void updateItem();
	void deleteItem();
}
