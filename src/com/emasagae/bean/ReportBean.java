package com.emasagae.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.emasagae.dao.ObjectifyReportDAO;
import com.emasagae.dao.ObjectifyReportUserDAO;
import com.emasagae.entity.Report;
import com.emasagae.entity.ReportUser;
import com.google.gson.Gson;
import com.googlecode.objectify.Key;

@ManagedBean (name = "reportBean")
@RequestScoped
public class ReportBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{userBean}")
    private UserBean userBean;
	
	private List<Report> reports;	
    private String type;
    private String address;
    private Integer zip;
    private String description;
    private String priority;
    private Date startDate;
    private String email;   
    private String errorReport;
    private String label;	
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserBean getUserBean() {
		return userBean;
	}
	
	public void setUserBean(UserBean user) {
		this.userBean = user;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
	
	public String getErrorReport() {
		return errorReport;
	}

	public void setErrorReport(String errorReport) {
		this.errorReport = errorReport;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getReportsForMap() {
        return new Gson().toJson(reports);
	}
	
	public String getReportForMap() {
		return new Gson().toJson(userBean.getReportSelected(), Report.class);
	}
		
	public String doCreateReport(){
		Report r = new Report();
		ReportUser reportuser = new ReportUser();
		reportuser.setEmail(email);  
		r.setStartDate(startDate);
		r.setPriority(priority);
		r.setState("NUEVO");
		r.setType(type);		
		r.setAddress(address);
		r.setDescription(description);
		r.setCreationDate(new Date());
		r.setZip(zip);
		r.setLabel(label);
		
		ObjectifyReportUserDAO du = new ObjectifyReportUserDAO();
		Key<ReportUser> keyReportUser = du.save(reportuser);
		
		r.setReportUser(keyReportUser);
		
		ObjectifyReportDAO dao = new ObjectifyReportDAO();
		dao.save(r);
		
		return "index";
	}
	
	public String doEditReport() {
		ObjectifyReportDAO dao = new ObjectifyReportDAO();
		dao.save(userBean.getReportSelected());
		
		return doViewReport(userBean.getReportSelected());
	}
	
	public String doViewReport(Report report){
					
		ObjectifyReportUserDAO du = new ObjectifyReportUserDAO();
		ReportUser ru = du.findByKey(report.getReportUser());
		
		userBean.setReportSelectedEmail(ru.getEmail());
		userBean.setReportSelected(report);
		
		return "viewReport";
	}

	
	
	public String doDelete() {
		ObjectifyReportDAO db = new ObjectifyReportDAO();
		
		// eliminar de la lista aqui no tiene sentido porque el bean es de request y se actualiza la lista cada vez que pasamos por index
		// es ineficiente, deberiamos mover esa lista al session para no traerla mas veces de las necesarias
		//reports.remove(user.getReportSelected());
		db.delete(userBean.getReportSelected());
		
		return "index";
	}
	
}