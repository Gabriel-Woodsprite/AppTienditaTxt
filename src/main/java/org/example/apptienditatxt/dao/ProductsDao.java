package org.example.apptienditatxt.dao;

import org.example.apptienditatxt.interfaces.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductsDao implements ProductDaoInterface {
	String path = "productos";

	//////////////////
	//////CREATE//////
	//////////////////
	@Override
	public void create(org.example.apptienditatxt.model.Product product) throws Exception {
		List<org.example.apptienditatxt.model.Product> products = readAll();
		products.add(product);
		saveAll(products);
	}

	///////////////////
	///////READ////////
	///////////////////
	@Override
	public org.example.apptienditatxt.model.Product read(String barcode) throws Exception {
		return readAll().stream()
				.filter(p -> p.getBarcode().equals(barcode))
				.findFirst()
				.orElse(null);
	}

	///////////////////
	//////READ ALL/////
	///////////////////
	@Override
	public List<org.example.apptienditatxt.model.Product> readAll() throws Exception {
		File file = new File(path);
		if (!file.exists()) return new ArrayList<>();

		List<org.example.apptienditatxt.model.Product> products = new ArrayList<>();
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				products.add(parseLine(scanner.nextLine()));
			}
		}
		return products;
	}

	///////////////////
	//////UPDATE///////
	///////////////////
	@Override
	public void update(String barcode, org.example.apptienditatxt.model.Product updatedProduct) throws Exception {
		List<org.example.apptienditatxt.model.Product> all = readAll();
		for (int i = 0; i < all.size(); i++) {
			if (all.get(i).getBarcode().equals(barcode)) {
				all.set(i, updatedProduct);
				break;
			}
		}
		saveAll(all);
	}

	///////////////////
	//////DELETE///////
	///////////////////
	@Override
	public void delete(String barcode) throws Exception {
		List<org.example.apptienditatxt.model.Product> all = readAll();
		all.removeIf(p -> p.getBarcode().equals(barcode));
		saveAll(all);
	}

	///////////////////
	//////SAVE ALL/////
	///////////////////
	private void saveAll(List<org.example.apptienditatxt.model.Product> products) throws Exception {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
			for (org.example.apptienditatxt.model.Product p : products) {
				writer.write(String.join(",",
						p.getBarcode(), p.getName(), p.getBrand(), p.getDescription(),
						p.getCategory(), p.getMeasurementUnit(), p.getContent(),
						p.getPresentacion(), String.valueOf(p.isActive()), p.getImage()));
				writer.newLine();
			}
		}
	}

	private org.example.apptienditatxt.model.Product parseLine(String line) {
		String[] tokens = line.split(",");
		org.example.apptienditatxt.model.Product p = new org.example.apptienditatxt.model.Product();
		p.setBarcode(tokens[0]);
		p.setName(tokens[1]);
		p.setBrand(tokens[2]);
		p.setDescription(tokens[3]);
		p.setCategory(tokens[4]);
		p.setMeasurementUnit(tokens[5]);
		p.setContent(tokens[6]);
		p.setPresentacion(tokens[7]);
		p.setActive(Boolean.parseBoolean(tokens[8]));
		p.setImage(tokens[9]);
		return p;
	}
}
