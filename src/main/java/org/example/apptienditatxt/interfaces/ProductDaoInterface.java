package org.example.apptienditatxt.interfaces;

import java.util.List;

public interface ProductDaoInterface {
	void create(org.example.apptienditatxt.model.Product product) throws Exception;

	org.example.apptienditatxt.model.Product read(String barcode) throws Exception;

	List<org.example.apptienditatxt.model.Product> readAll() throws Exception;

	void update(String barcode, org.example.apptienditatxt.model.Product updatedProduct) throws Exception;

	void delete(String barcode) throws Exception;
}
