package com.emasagae.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.emasagae.dao.ObjectifyOperationDAO;
import com.emasagae.dao.ObjectifyReportDAO;
import com.emasagae.dao.ObjectifyReportUserDAO;
import com.emasagae.entity.Operation;
import com.emasagae.entity.Report;
import com.emasagae.entity.ReportUser;
import com.googlecode.objectify.Key;

@ManagedBean
@RequestScoped
public class OperationBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{userBean}")
    private UserBean userBean;
	
	ObjectifyOperationDAO dao;
	private List<Operation> operations;
    private String type;
    private String description;
    private Date startDate;  
    private String errorOperation;

	
	public OperationBean() {}
    
    @PostConstruct
    public void init() {
    	dao = new ObjectifyOperationDAO();    	
    	operations = dao.findAll();
    	errorOperation = "";
    }
    
    public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
    
    public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getErrorOperation() {
		return errorOperation;
	}

	public void setErrorOperation(String errorOperation) {
		this.errorOperation = errorOperation;
	}
	   
    public String doSaveOperation() {         
        Operation operation = new Operation();
        if(startDate!=null) {
        	operation.setStartdate(startDate);        	
        }        
        	
        if(type == null || type.isEmpty()) {
            errorOperation = "El tipo no puede estar vacío";
            return "crearOperacion";
        }
        operation.setCreationdate(new Date());
        operation.setType(type);
        operation.setDescription(description);
        
        ObjectifyReportDAO d = new ObjectifyReportDAO();
		Key<Report> keyReport = d.getKey(userBean.getReportSelected().getId());
        operation.setReport(keyReport);
        
        ObjectifyReportUserDAO du = new ObjectifyReportUserDAO();
		Key<ReportUser> keyReportUser = du.getKey(userBean.getLoginUser().getId());		
		operation.setReportUser(keyReportUser);
                
        dao.save(operation);
               
        return "viewReport";
    }
    
}



