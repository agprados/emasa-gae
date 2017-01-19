package com.emasagae.bean;

import com.emasagae.dao.ObjectifyReportDAO;
import com.emasagae.entity.Report;
import com.google.gson.Gson;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import java.io.Serializable;
import java.util.List;

@ManagedBean (name = "reportBean")
@RequestScoped
public class ReportBean implements Serializable{

	@ManagedProperty(value="#{userBean}")
    private UserBean user;
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

	public void setUser(UserBean user) {
		this.user = user;
	}

	public String doVerAviso(Report report) {
		user.setReportSelected(report);
		return "viewReport";
	}
	
	public String getAllReports() {
        return new Gson().toJson(reports);
	}
}