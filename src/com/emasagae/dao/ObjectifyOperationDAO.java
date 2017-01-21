package com.emasagae.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.emasagae.entity.Operation;
import com.emasagae.entity.Report;
import com.googlecode.objectify.Key;

public class ObjectifyOperationDAO extends ObjectifyGenericDAO<Operation> implements IOperationDAO {
	
	public List<Operation> findAllByReportSortedByCreationDate(Key<Report> k) {		
		return ofy().load().type(Operation.class).order("creationDate").ancestor(k).list();
	}
}
