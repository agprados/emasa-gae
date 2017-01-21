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
	public Long save(T entity) {
		return ofy().save().entity(entity).now().getId();
	}

	@Override
	public Map<Key<T>, T> saveAll(Iterable<T> entities) {		
		return ofy().save().entities(entities).now();
	}

	@Override
	public void delete(T entity) {
		ofy().delete().entity(entity).now();
	}
	
	@Override
	public void deleteById(Long id) {
		deleteByKey(getKey(id));
	}

	@Override
	public void deleteByKey(Key<T> entityKey) {
		ofy().delete().key(entityKey).now();
	}

	@Override
	public void deleteList(Iterable<T> entities) {
		ofy().delete().entities(entities).now();		
	}

	@Override
	public void deleteListByKeys(Iterable<Key<T>> keys) {
		ofy().delete().keys(keys).now();
	}

	@Override
	public T findById(Long id) {
		return ofy().load().type(clazz).id(id).now();
	}

	@Override
	public T findByKey(Key<T> key) {
		return ofy().load().key(key).now();
	}

	@Override
	public Key<T> getKey(Long id) {
		return Key.create(clazz, id);
	}

	@Override
	public List<T> findAll() {
		return ofy().load().type(clazz).list();
	}

	@Override
	public T findByProperty(String propName, Object propValue) {
		T obj =  ofy().load().type(clazz).filter(propName, propValue).first().now();
        return obj;
	}

	@Override
	public List<T> findAllByProperty(String propName, Object propValue) {
		return ofy().load().type(clazz).filter(propName, propValue).list();
	}

	@Override
	public List<Key<T>> findKeysByProperty(String propName, Object propValue) {
		return ofy().load().type(clazz).filter(propName, propValue).keys().list();
	}

}
