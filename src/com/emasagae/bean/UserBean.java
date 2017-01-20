package com.emasagae.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.datatype.XMLGregorianCalendar;

import com.emasagae.entity.Report;
import static com.emasagae.utils.UtilsMix.convertDateToXMLGregorianCalendar;
import java.io.Serializable;
import java.util.Date;

@ManagedBean (name = "userBean")
@SessionScoped
public class UserBean implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	private String email;
	private Report reportSelected;
	
	public UserBean() {
    }
    
    @PostConstruct
    public void init() {
    	reportSelected = new Report();
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
    public Date convertToDate(XMLGregorianCalendar cal){
        if (cal != null) {
            return cal.toGregorianCalendar().getTime();
        }
        return null;
    }

}
