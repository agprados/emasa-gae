package com.emasagae.entity;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Operation implements Serializable {	

    private static final long serialVersionUID = 1L;
    
 	@Id	
 	private Long id;
 	@Parent 
 	private Key<Report> report;
    private String type;
    private String description;
    @Index
    private Date creationDate;
    private Date startDate;
    private Key<ReportUser> reportUser;
    
    public Operation() {
    }

    public Operation(Long id) {
        this.id = id;
    }

    public Operation(Long id, String type, Date creationDate) {
        this.id = id;
        this.type = type;
        this.creationDate = creationDate;
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

	public Key<Report> getReport() {
        return report;
    }

    public void setReport(Key<Report> report) {
        this.report = report;
    }

    public Key<ReportUser> getReportUser() {
        return reportUser;
    }

    public void setReportUser(Key<ReportUser> reportUser) {
        this.reportUser = reportUser;
    }
    
}
