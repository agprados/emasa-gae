package com.emasagae.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.emasagae.entity.Report;
import com.emasagae.entity.ReportUser;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;

public class ObjectifyReportDAO extends ObjectifyGenericDAO<Report> implements IReportDAO {
	
	public List<Report> findAllSortedByCreationDate() {
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Query query = new Query("Report").
		addSort("creationdate",Query.SortDirection.DESCENDING);
		
		List<Entity> entities = ds.prepare(query).asList(FetchOptions.Builder.withDefaults());
		
		List<Report> reports = new ArrayList<Report>();
		for (Entity e : entities) {
			Long id = new Long((String) e.getProperty("id"));
			String state = (String) e.getProperty("state");
			String type = (String) e.getProperty("type");
			String address = (String) e.getProperty("address");
			Integer zip = new Integer((String) e.getProperty("zip"));
			BigDecimal geolat = new BigDecimal((String) e.getProperty("geolat"));
			BigDecimal geolng = new BigDecimal((String) e.getProperty("geolng"));
			String description = (String) e.getProperty("description");
			String priority = (String) e.getProperty("priority");
			Date creationdate = new Date();//((String) e.getProperty("creationdate"));
			Date startdate = new Date();//((String) e.getProperty("startdate"));
			Date finishdate = new Date();//((String) e.getProperty("finishdate"));
			Long idReportUser = new Long((String) e.getProperty("reportUser"));
			ObjectifyReportUserDAO aux = new ObjectifyReportUserDAO();
			ReportUser reportUser = aux.findById(idReportUser);
			Report r = new Report(id, state, address, description, priority, creationdate);
			r.setType(type);
			r.setZip(zip);
			r.setGeolat(geolat);
			r.setGeolng(geolng);
			r.setStartdate(startdate);
			r.setFinishdate(finishdate);
			r.setReportUser(reportUser);
			reports.add(r);
		}
		
		return reports;
	}
	
}
