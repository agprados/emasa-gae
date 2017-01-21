package com.emasagae.entity;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;


@Entity
public class Operation implements Serializable {	

    private static final long serialVersionUID = 1L;
    
 	@Id	
 	private Long id;
 	@Parent @Index private Key<Emasa> report;
    private String type;
    private String description;
    private String IdReport;
    private Date creationdate;
    private Date startdate;
    private Date scheduleddate;
    private Key<ReportUser> reportUser;
    
    public Operation() {
    }

    public Operation(Long id) {
        this.id = id;
    }

    public Operation(Long id, String type, Date creationdate) {
        this.id = id;
        this.type = type;
        this.creationdate = creationdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Key<Emasa> getReport() {
        return report;
    }

    public void setReport(Key<Emasa> key) {
        this.report = key;
    }

    public Key<ReportUser> getReportUser() {
        return reportUser;
    }

    public void setReportUser(Key<ReportUser> reportUser) {
        this.reportUser = reportUser;
    }

	public String getIdReport() {
		return IdReport;
	}

	public void setIdReport(String idReport) {
		IdReport = idReport;
	}

	public Date getScheduleddate() {
		return scheduleddate;
	}

	public void setScheduleddate(Date scheduleddate) {
		this.scheduleddate = scheduleddate;
	}

	
    
}
