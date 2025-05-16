package org.example.apptienditatxt.interfaces;

import org.example.apptienditatxt.model.Costumer;

import java.util.List;

public interface CostumerDaoInterface {
	void create(Costumer costumer) throws Exception;

	Costumer read(String costumerID) throws Exception;

	List<Costumer> readAll() throws Exception;

	void update(String costumerID, Costumer updatedInfo) throws Exception;

	void delete(String costumerID) throws Exception;
}
