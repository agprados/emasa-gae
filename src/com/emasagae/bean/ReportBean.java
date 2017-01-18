package com.emasagae.bean;

import com.emasagae.dao.ObjectifyReportDAO;
import com.emasagae.entity.Report;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.io.Serializable;
import java.util.List;

@ManagedBean
@RequestScoped
public class ReportBean implements Serializable{
	
	private List<Report> reports;
	private ObjectifyReportDAO db;

	private static final long serialVersionUID = 1L;
	
	public ReportBean() {
    }
    
    @PostConstruct
    public void init() {
    	db = new ObjectifyReportDAO();
    	reports = db.findAll(); // <-- solo para ver que sale alguno, en la version final la linea de abajo, que trae ordenados
    	//reports = db.findAllSortedByCreationDate();
    }

	public List<Report> getReports() {
		return reports;
	}
	
	public String doVerAviso(Report report) {
		return "index";
	}
}