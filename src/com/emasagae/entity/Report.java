package com.emasagae.entity;

import java.io.Serializable;
import java.util.Date;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

/**
 * The @Entity tells Objectify about our entity.  We also register it in {@link OfyHelper}
 * Our primary key @Id is set automatically by the Google Datastore for us.
 *
 * We add a @Parent to tell the object about its ancestor. We are doing this to support many
 * guestbooks.  Objectify, unlike the AppEngine library requires that you specify the fields you
 * want to index using @Index.  Only indexing the fields you need can lead to substantial gains in
 * performance -- though if not indexing your data from the start will require indexing it later.
 *
 * NOTE - all the properties are PUBLIC so that we can keep the code simple.
 **/

@Entity
public class Report implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Objectify auto-generates Long IDs just like JDO / JPA
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
    private Date startdate;
    private Date finishdate;
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
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
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
    
}
	
	
