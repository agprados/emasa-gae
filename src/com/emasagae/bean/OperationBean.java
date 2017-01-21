package com.emasagae.bean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.emasagae.dao.ObjectifyOperationDAO;
import com.emasagae.entity.Operation;
import com.emasagae.entity.Report;

@ManagedBean (name = "operationBean")
@RequestScoped
public class OperationBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{userBean}")
    private UserBean userBean;

	
	private int id;
    private String type;
    private String state;
    private String description;
    private Date startDate;   
    private Date scheduledDate;
    private String notes;
    private Report report;
    private String errorOperation;

	
	public OperationBean() {}
    
    @PostConstruct
    public void init() {
    
    }
    
    public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public String getErrorOperation() {
		return errorOperation;
	}

	public void setErrorOperation(String errorOperation) {
		this.errorOperation = errorOperation;
	}

	
    
    public String doSaveOperation() {         
        Operation operation = new Operation();
        if(startDate!=null) {
            //operation.setStartdate(convertDateToXMLGregorianCalendar(startDate));
        	operation.setStartdate(startDate);
        	
        }
        if(scheduledDate!=null) {
            //operation.setScheduleddate(convertDateToXMLGregorianCalendar(scheduledDate));
        	operation.setScheduleddate(scheduledDate);
        	
        if(type == null || type.isEmpty()) {
            errorOperation = "El tipo no puede estar vacío";
            return "crearOperacion";
        }
        operation.setType(type);
        operation.setDescription(description);
        operation.setReport(userBean.getReportSelected().getEmasa());
        
        ObjectifyOperationDAO daop = new ObjectifyOperationDAO();
        daop.save(operation);
        
        }
        
        return "viewReport";
    }
    
}



