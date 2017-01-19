package com.emasagae.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;
import java.util.List;
import com.emasagae.entity.Report;

public class ObjectifyReportDAO extends ObjectifyGenericDAO<Report> implements IReportDAO {
	
	public List<Report> findAllSortedByCreationDate() {
		return ofy().load().type(Report.class).order("-creationdate").list();
	}
	
}
