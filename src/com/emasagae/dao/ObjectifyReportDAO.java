package com.emasagae.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;
import java.util.List;

import com.emasagae.entity.Emasa;
import com.emasagae.entity.Report;
import com.googlecode.objectify.Key;

public class ObjectifyReportDAO extends ObjectifyGenericDAO<Report> implements IReportDAO {
	
	private static final String EMASA_GROUP = "EMASA";
	
	public List<Report> findAllSortedByCreationDate() {
		Key<Emasa> k = Key.create(Emasa.class, EMASA_GROUP);
		return ofy().load().type(Report.class).order("-creationDate").ancestor(k).list();
	}
	
	@Override
	public Long save(Report entity) {
		Key<Emasa> k = Key.create(Emasa.class, EMASA_GROUP);
		entity.setEmasa(k);
		return ofy().save().entity(entity).now().getId();
	} 
	
}
