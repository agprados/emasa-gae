package com.emasagae.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.emasagae.dao.ObjectifyEmasaDAO;
import com.emasagae.entity.Emasa;
import com.emasagae.entity.Report;
import java.io.Serializable;

@ManagedBean (name = "userBean")
@SessionScoped
public class UserBean implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String reportSelectedEmail;
	private Report reportSelected;
	
	public UserBean() {
    }
    
    @PostConstruct
    public void init() {
    	initEmasa();
    	reportSelected = new Report();
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getReportSelectedEmail() {
		return reportSelectedEmail;
	}

	public void setReportSelectedEmail(String reportSelectedEmail) {
		this.reportSelectedEmail = reportSelectedEmail;
	}

	public Report getReportSelected() {
		return reportSelected;
	}

	public void setReportSelected(Report reportSelected) {
		this.reportSelected = reportSelected;
	}

	public String doLogout() {
		return "index";
	}
	
	private void initEmasa() {
		ObjectifyEmasaDAO dao = new ObjectifyEmasaDAO();
		Emasa emasa = dao.findById("EMASA");
		if (emasa == null) {
			emasa = new Emasa("EMASA");
			dao.save(emasa);
		}
	}
		
	
}
