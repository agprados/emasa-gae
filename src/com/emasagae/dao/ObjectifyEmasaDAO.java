package com.emasagae.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.emasagae.entity.Emasa;
import com.googlecode.objectify.Key;

public class ObjectifyEmasaDAO extends ObjectifyGenericDAO<Emasa> implements IEmasaDAO {
	
	@Override
	public Emasa findById(String id) {
		return ofy().load().type(Emasa.class).id(id).now();
	}

	public Key<Emasa> getKey() {
		return Key.create(Emasa.class, "EMASA"); 
	}
}
