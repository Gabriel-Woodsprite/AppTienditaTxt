package org.example.apptienditatxt.unitTests;

import org.example.apptienditatxt.controller.ProductsController;
import org.example.apptienditatxt.dao.ProductsDao;
import org.example.apptienditatxt.model.Product;

public class productUnitTest {
	public static void main(String[] args) throws Exception {
		ProductsController controller = new ProductsController(new ProductsDao());

		///////////////////
		//////CREAR////////
		///////////////////
		controller.createProduct(new Product());

		//////////////////////////
		//////CONSULTAR TODOS/////
		//////////////////////////
		controller.readAllProducts().forEach(System.out::println);

		/////////////////////////////
		//////Consultar Producto/////
		/////////////////////////////
		System.out.println(controller.readProduct("667788990011200"));

		/////////////////////////////
		//////Eliminar Producto/////
		/////////////////////////////
		controller.deleteProduct("667788990011200");
		controller.readAllProducts().forEach(System.out::println);

		/////////////////////////////
		//////Modificar Producto/////
		/////////////////////////////
		controller.updateProduct("667788990011200", new Product());
	}
}