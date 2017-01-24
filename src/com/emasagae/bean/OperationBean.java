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
    	findOperations();
		
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
        if(startDate == null){
        	errorOperation = "La fecha no puede estar vacía";
            return "createOperation";        	
        }  
        if(type == null || type.isEmpty()){
            errorOperation = "El tipo no puede estar vacío";
            return "createOperation";
        }
        operation.setStartDate(startDate);
        operation.setCreationDate(new Date());
        operation.setType(type);
        operation.setDescription(description);
        
        ObjectifyReportDAO d = new ObjectifyReportDAO();
		Key<Report> keyReport = d.getKey(userBean.getReportSelected().getId());
        operation.setReport(keyReport);
        
		ObjectifyReportUserDAO du = new ObjectifyReportUserDAO();
		Key<ReportUser> keyReportUser = du.getKey(userBean.getLoginUser().getId());		
		operation.setReportUser(keyReportUser);
                
        dao.save(operation);
        findOperations();
               
        return "viewReport?faces-redirect=true";
    }
    
    private void findOperations() {
    	if(userBean.getReportSelected() != null) {
    		ObjectifyReportDAO d = new ObjectifyReportDAO();
    		Key<Report> keyReport = d.getKey(userBean.getReportSelected().getId());
        	operations = dao.findAllByReportSortedByCreationDate(keyReport);
    	}
    }
    
    public String doEditOperation(Operation operation) {
        userBean.setOperationSelected(operation);
        return "updateOperation?faces-redirect=true";
    }
    
    public String doDeleteOperation(Operation operation) {  
        dao.delete(operation);
        operations.remove(operation);
        
    	return "viewReport";    
    } 
    
    public String doConfirmChanges() {   
        if(userBean.getOperationSelected().getType() == null || userBean.getOperationSelected().getType().isEmpty()) {
            errorOperation = "El tipo no puede estar vacío";
            return "updateOperation";
        }
        if(startDate == null){
        	errorOperation = "La fecha no puede estar vacía";
            return "updateOperation";        	
        }         
        userBean.getOperationSelected().setStartDate(startDate);
        
        dao.save(userBean.getOperationSelected());
        
        return "viewReport?faces-redirect=true";
    }
    
}



