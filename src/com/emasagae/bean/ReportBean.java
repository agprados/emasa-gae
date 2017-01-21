package com.emasagae.bean;

import com.emasagae.dao.ObjectifyReportDAO;
import com.emasagae.dao.ObjectifyReportUserDAO;
import com.emasagae.entity.Report;
import com.emasagae.entity.ReportUser;
import com.google.gson.Gson;
import com.googlecode.objectify.Key;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ManagedBean (name = "reportBean")
@RequestScoped
public class ReportBean implements Serializable{

	@ManagedProperty(value="#{userBean}")
    private UserBean user;
	private List<Report> reports;
	
	private String state;
    private String type;
    private String address;
    private Integer zip;
    private String description;
    private String priority;
    private Date creationdate;
    private Date startDate;
    private Date finishdate;
    private String email;   
    private String errorReport;
    private String label;
    
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
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Date getStartdate() {
		return startDate;
	}

	public void setStartdate(Date startdate) {
		this.startDate = startdate;
	}

	public Date getFinishdate() {
		return finishdate;
	}

	public void setFinishdate(Date finishdate) {
		this.finishdate = finishdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserBean getUser() {
		return user;
	}
	
	public void setUser(UserBean user) {
		this.user=user;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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
		return new Gson().toJson(user.getReportSelected(), Report.class);
	}
		
	public String doSaveReport(){
		Report r = new Report();
		ReportUser reportuser = new ReportUser();
		reportuser.setEmail(user.getEmail()); //cuando se implemente el API de identificación de GAE el mail se añadira automaticmente 
		r.setStartDate(this.startDate);
		r.setPriority(this.priority);
		r.setState(this.state);
		r.setType(this.type);		
		r.setAddress(this.address);
		r.setDescription(this.description);
		r.setCreationDate(new Date());
		r.setZip(this.zip);
		r.setCreationDate(new Date());
		
		ObjectifyReportUserDAO du = new ObjectifyReportUserDAO();
		du.save(reportuser);
				
		Key<ReportUser> keyReportUser = Key.create(reportuser);
		r.setReportUser(keyReportUser);
		
		ObjectifyReportDAO dao = new ObjectifyReportDAO();
		dao.save(r);
		
		return "index";
	}
	
	public String doViewReport(Report report){
		ObjectifyReportUserDAO du = new ObjectifyReportUserDAO();
		ReportUser ru = du.findByKey(report.getReportUser());
		
		user.setReportSelectedEmail(ru.getEmail());
		user.setReportSelected(report);
		
		return "viewReport";
	}

	public String getErrorReport() {
		return errorReport;
	}

	public void setErrorReport(String errorReport) {
		this.errorReport = errorReport;
	}
	
	public String doDelete() {
		ObjectifyReportDAO db = new ObjectifyReportDAO();
		
		// eliminar de la lista aqui no tiene sentido porque el bean es de request y se actualiza la lista cada vez que pasamos por index
		// es ineficiente, deberiamos mover esa lista al session para no traerla mas veces de las necesarias
		//reports.remove(user.getReportSelected());
		db.delete(user.getReportSelected());
		
		return "index";
	}
	
	public String doUpdate() {
		return "updateReport";
	}
	
	public String doSaveUpdate() {
		return doViewReport(user.getReportSelected());
	}
}