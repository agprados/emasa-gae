package com.emasagae.dao;

import com.emasagae.entity.Emasa;
import com.googlecode.objectify.Key;

public interface IEmasaDAO extends IGenericDAO<Emasa>{
	
	public Emasa findById(String id);
	
	public Key<Emasa> getKey();
	
}
