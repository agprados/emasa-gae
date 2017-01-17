package com.emasagae.dao;

import java.util.List;
import java.util.Map;
import java.lang.reflect.ParameterizedType;

import com.googlecode.objectify.Key;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class ObjectifyGenericDAO<T> implements IGenericDAO<T>{
	
	protected Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public ObjectifyGenericDAO() {
		clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	/**
	 * We've got to get the associated domain class somehow
	 *
	 * @param clazz
	 */
	protected ObjectifyGenericDAO(Class<T> clazz)
	{
		this.clazz = clazz;
	}


	@Override
	public Key<T> save(T entity) {
		return ofy().save().entity(entity).now();
	}

	@Override
	public Map<Long, T> saveAll(Iterable<T> entities) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void update(T entity) {
		
	}
	
	@Override
	public void updateList(Iterable<T> entities){
		
	}

	@Override
	public boolean delete(T entity) {
		ofy().delete().entity(entity).now();
		return false;		
	}

	@Override
	public boolean deleteByKey(Key<T> entityKey) {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteList(Iterable<T> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteListByKeys(Iterable<Key<T>> keys) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findByKey(Key<T> key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Key<T> getKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll() {
		return ofy().load().type(clazz).list();
	}

	@Override
	public T findByProperty(String propName, Object propValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAllByProperty(String propName, Object propValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Key<T>> findKeysByProperty(String propName, Object propValue) {
		// TODO Auto-generated method stub
		return null;
	}

}
