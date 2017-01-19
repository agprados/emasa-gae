package com.emasagae.bean;

import com.emasagae.dao.ObjectifyReportDAO;
import com.emasagae.entity.Report;
import com.google.gson.Gson;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.io.Serializable;
import java.util.List;

@ManagedBean
@RequestScoped
public class ReportBean implements Serializable{
	
	private List<Report> reports;

	private static final long serialVersionUID = 1L;
	
	public ReportBean() {
    }
    
    @PostConstruct
    public void init() {
    	ObjectifyReportDAO db = new ObjectifyReportDAO();
    	reports = db.findAllSortedByCreationDate();
    }

	public List<Report> getReports() {
		return reports;
	}
	
	public String doVerAviso(Report report) {
		return "index";
	}
	
	public String getAllReports() {
        return new Gson().toJson(reports);
	}
}