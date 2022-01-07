package com.revature.daos;

import java.io.IOException;
import java.util.List;

public interface GenericDao<T> {

	T add(T t) throws IOException;
	int delete(int id) throws IOException; 
	T getById(int id) throws IOException;
	List<T> getAll() throws IOException;
	int update(T t);

}
