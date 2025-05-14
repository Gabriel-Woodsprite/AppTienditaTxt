package org.example.apptienditatxt.unitTests;

import org.example.apptienditatxt.dao.ProductsDao;
import org.example.apptienditatxt.model.Product;

import java.util.List;

public class UnitTest1 {
	public static void main(String[] args) {
		ProductsDao dao = new ProductsDao();
		List<Product> productList = dao.readAllItems("productos");

		productList.forEach(System.out::println);
	}
}
