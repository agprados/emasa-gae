package com.emasagae.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.emasagae.dao.ObjectifyReportDAO;
import com.emasagae.dao.ObjectifyReportUserDAO;
import com.emasagae.entity.Report;
import com.emasagae.entity.ReportUser;

import java.io.Serializable;

@ManagedBean
@RequestScoped
public class ResetBean implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	public String doResetDB() {
		ObjectifyReportDAO objReport = new ObjectifyReportDAO();
		ObjectifyReportUserDAO objReportUser = new ObjectifyReportUserDAO();
		/*
		Report r = new Report();
		//...
		objReport.save(r);
		
		ReportUser ru = new ReportUser();
		//...
		objReportUser.save(ru);
		*/
		return "index";
	}

}
