package org.example.apptienditatxt.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.example.apptienditatxt.dao.ProductsDao;
import org.example.apptienditatxt.model.Product;

import java.util.ArrayList;
import java.util.List;

import static org.example.apptienditatxt.utils.UserMessage.userInput;

public class MainViewController {

	@FXML
	public ListView productsListView;

	ProductsDao productsDao = new ProductsDao();
	ObservableList<String> products = FXCollections.observableArrayList();


	@FXML
	private void insertProductList() throws Exception {
		List<Product> productsList = productsDao.readAll();
		productsListView.getItems().clear();
		for (Product product : productsList) {
			products.add(product.getBarcode() + " | " + product.getName());
		}
		productsListView.setItems(products);
	}

	@FXML
	private void removeProductButton() throws Exception {
		String barcode = userInput("Eliminar Producto", "ingresa el codigo de barras del producto a eliminar");
		productsDao.delete(barcode);
		productsDao.readAll().forEach(System.out::println);
		insertProductList();
	}

	@FXML
	public void initialize() throws Exception {
		insertProductList();
	}
}