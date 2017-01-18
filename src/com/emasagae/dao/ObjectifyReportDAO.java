package com.emasagae.dao;

import java.util.List;

import com.emasagae.entity.Report;

public class ObjectifyReportDAO extends ObjectifyGenericDAO<Report> implements IReportDAO {
	
	public List<Report> findAllSortedByCreationDate() {
		return null;
	}
	
}
