package com.emasagae.entity;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;


@Entity
public class Report implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id 
	private Long id;	
	private String state;
    private String type;
    private String address;
    private Integer zip;
    private String description;
    private String priority;
    @Index
    private Date creationdate;
    private Date startDate;
    private Date finishdate;
    private String label;
	@Parent
    private Key<ReportUser> reportUser;
    

    public Report() {
    }

    public Report(Long id) {
        this.id = id;
    }

    public Report(Long id, String state, String address, String description, String priority, Date creationdate) {
        this.id = id;
        this.state = state;
        this.address = address;
        this.description = description;
        this.priority = priority;
        this.creationdate = creationdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    public Key<ReportUser> getReportUser() {
        return reportUser;
    }

    public void setReportUser(Key<ReportUser> reportUser) {
        this.reportUser = reportUser;
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
}
	
	
