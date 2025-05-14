package org.example.apptienditatxt.dao;

import javafx.scene.control.Alert;
import org.example.apptienditatxt.interfaces.*;
import org.example.apptienditatxt.model.Product;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.apptienditatxt.utils.UserMessage.message;

public class ProductsDao implements DaoInterface {

	//////////////////
	//////CREATE//////
	//////////////////
	@Override
	public void createItem(String path, List<Product> productos) throws Exception {
		File file = new File(path);
		PrintWriter writer = new PrintWriter(file);
		for (Product producto : productos) {
			String salida = producto.getBarcode() + ", " +
					producto.getName() + ", " +
					producto.getBrand() + ", " +
					producto.getDescription() + ", " +
					producto.getCategory() + ", " +
					producto.getMeasurementUnit() + ", " +
					producto.getContent() + ", " +
					producto.getPresentacion() + ", " +
					producto.isActive() + ", " +
					producto.getImage();
			writer.println(salida);
			writer.flush();
		}
		writer.close();
	}

	///////////////////
	///////READ////////
	///////////////////
	@Override
	public Product readItem(String line) {
		Product producto = new Product();
		String[] items = line.split(", ");
		for (int i = 0; i < items.length; i++) {
			producto.setBarcode(items[0]);
			producto.setName(items[1]);
			producto.setBrand(items[2]);
			producto.setDescription(items[3]);
			producto.setCategory(items[4]);
			producto.setMeasurementUnit(items[5]);
			producto.setContent(items[6]);
			producto.setPresentacion(items[7]);
			producto.setActive(Boolean.parseBoolean(items[8]));
			producto.setImage(items[9]);
		}
		return producto;
	}

	///////////////////
	//////READ ALL/////
	///////////////////
	@Override
	public List<Product> readAllItems(String path) {
		InputStream inputStream = ProductsDao.class.getClassLoader().getResourceAsStream(path);
		if (inputStream != null) {
			Scanner entrada = new Scanner(inputStream);
			List<Product> data = new ArrayList<>();
			while (entrada.hasNext()) {
				String line = entrada.nextLine();
				data.add(readItem(line));
			}
			entrada.close();
			return data;
		}
		System.out.println("No se encontro el archivo");

		return null;
	}

	///////////////////
	//////UPDATE///////
	///////////////////
	@Override
	public void updateItem() {
	}

	///////////////////
	//////DELETE///////
	///////////////////

	@Override
	public void deleteItem() {

	}

	public static void saveData(String path, List<Product> catalogue) {
		try {
			URL resourceUrl = ProductsDao.class.getClassLoader().getResource(path);

			if (resourceUrl == null) {
				throw new FileNotFoundException("Archivo no encontrado" + path);
			}

			File file = new File(resourceUrl.toURI());

			try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING)) {
				for (Product producto : catalogue) {
					writer.write(String.format(
							"%s,%s,%s,%s,%s,%s,%s,%s,%s,%s%n",
							producto.getBarcode(),
							producto.getName(),
							producto.getBrand(),
							producto.getDescription(),
							producto.getCategory(),
							producto.getMeasurementUnit(),
							producto.getContent(),
							producto.getPresentacion(),
							producto.isActive(),
							producto.getImage()));
				}
			}
			message("Salida", "Datos guardados exitosamente", Alert.AlertType.INFORMATION);
		} catch (Exception e) {
			message("Error", "Hubo un error al guardar datos del archivo" + e.getMessage(), Alert.AlertType.ERROR);
		}
	}
}
