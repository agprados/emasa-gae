package com.emasagae.dao;

import java.util.List;
import java.util.Map;

import com.googlecode.objectify.Key;

public interface IGenericDAO<T> {

	public Long save(T entity);
	public Map<Key<T>, T> saveAll(Iterable<T> entities);
	public void delete(T entity);
	public void deleteById(Long id);
	public void deleteByKey(Key<T> entityKey);
	public void deleteList(Iterable<T> entities);
	public void deleteListByKeys(Iterable<Key<T>> keys);
	public T findById(Long id);
	public T findByKey(Key<T> key);
	
	/*
	 * Convenience method to generate a typed Key<T> for a given id
	 */
	public Key<T> getKey(Long id);

	/*
	 * Get ALL entities of type <T> in the datastore. Potentially very inefficient!!
	 * 	Make sure you have a good reason to use this!!
	 */
	public List<T> findAll();

	/**
	 * Convenience method to get all objects matching a single property
	 *
	 * @param propName
	 * @param propValue
	 * @return T matching Object
	 */
	public T findByProperty(String propName, Object propValue);
	public List<T> findAllByProperty(String propName, Object propValue);
	public List<Key<T>> findKeysByProperty(String propName, Object propValue);
	
}
