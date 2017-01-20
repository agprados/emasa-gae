package com.emasagae.dao;

import com.emasagae.entity.Emasa;
import com.googlecode.objectify.Key;

public class ObjectifyEmasaDAO extends ObjectifyGenericDAO<Emasa> implements IEmasaDAO {

	public Key<Emasa> getKey() {
		return Key.create(Emasa.class, "EMASA"); 
	}
}
