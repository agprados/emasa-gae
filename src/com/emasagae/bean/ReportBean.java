package com.emasagae.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
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
import com.emasagae.flickr.FlickrResponse;
import com.emasagae.flickr.Photo;
import com.google.gson.Gson;
import com.googlecode.objectify.Key;

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
    private Date creationDate;
    private Date startDate;
    private Date finishDate;
    private String email;   
    private String errorReport;
    private String label;
    private List<String> imagesUrl;
    
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
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
		this.user = user;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<String> getImagesUrl() {
		return imagesUrl;
	}

	public void setImagesUrl(List<String> imagesUrl) {
		this.imagesUrl = imagesUrl;
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
		reportuser.setEmail(user.getEmail()); //cuando se implemente el API de identificaci�n de GAE el mail se a�adira automaticmente 
		r.setStartDate(this.startDate);
		r.setPriority(this.priority);
		r.setState(this.state);
		r.setType(this.type);		
		r.setAddress(this.address);
		r.setDescription(this.description);
		r.setCreationDate(new Date());
		r.setZip(this.zip);
		r.setLabel(this.label);
		
		ObjectifyReportUserDAO du = new ObjectifyReportUserDAO();
		Key<ReportUser> keyReportUser = du.save(reportuser);
		
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
		
		if(user.getReportSelected().getLabel() != null && !user.getReportSelected().getLabel().isEmpty()) {
			String line, reply = "";
			try {
				URL url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&format=json&api_key=48f2342467e41b00b8e55c9896d81629&tags=" + user.getReportSelected().getLabel());
				BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			
				while ((line = reader.readLine()) != null) { 
					reply += line; 
				}
			
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			reply = reply.replace("jsonFlickrApi(", "");
			reply = reply.substring(0, reply.length()-1);
			Gson gson = new Gson();
			FlickrResponse fr = gson.fromJson(reply, FlickrResponse.class);
			imagesUrl = new ArrayList<String>();
			Photo p;
			
			for(int i=0;i<fr.getPhotos().getPhoto().size();i++) {
				p = fr.getPhotos().getPhoto().get(i);
				imagesUrl.add("http://farm"+p.getFarm()+".staticflickr.com/"+p.getServer()+"/"+p.getId()+"_"+p.getSecret()+"_z.jpg");
			}
		}
		
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