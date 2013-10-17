package com.nttdata.boccia.model.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {

	List<T> findAll();

	/** Persist the newInstance object into database */
	void save(T entity);

	/**
	 * Retrieve an object that was previously persisted to the database using
	 * the indicated id as primary key
	 */
	T findById(ID id);

	/** Save changes made to a persistent object. */
	void update(T entity);

	/** Remove an object from persistent storage in the database */
	void delete(T entity);

}
