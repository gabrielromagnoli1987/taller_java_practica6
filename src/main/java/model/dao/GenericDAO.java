package model.dao;

import java.io.Serializable;

/**
 * Generic CRUD Operations
 * @param <T>
 */
public interface GenericDAO<T> {

	T create(T t);

	T retrieve(Serializable serializable);

	void delete(T t);

	T update(Serializable serializable, T t);

}
