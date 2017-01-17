package com.emasagae.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

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
	@Id Long idObj;
	
	private Integer id;
	private String state;
    private String type;
    private String address;
    private Integer zip;
    private BigDecimal geolat;
    private BigDecimal geolng;
    private String description;
    private String priority;
    private Date creationdate;
    private Date startdate;
    private Date finishdate;
    private String notes;
    private String technicaldata;
    private Collection<Operation> operationCollection;
    private ReportUser reportUser;

    public Report() {
    }

    public Report(Integer id) {
        this.id = id;
    }

    public Report(Integer id, String state, String address, String description, String priority, Date creationdate) {
        this.id = id;
        this.state = state;
        this.address = address;
        this.description = description;
        this.priority = priority;
        this.creationdate = creationdate;
    }
    
    public Long getidObj() {
        return idObj;
    }

    public void setidObj(Long idObj) {
        this.idObj = idObj;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public BigDecimal getGeolat() {
        return geolat;
    }

    public void setGeolat(BigDecimal geolat) {
        this.geolat = geolat;
    }

    public BigDecimal getGeolng() {
        return geolng;
    }

    public void setGeolng(BigDecimal geolng) {
        this.geolng = geolng;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTechnicaldata() {
        return technicaldata;
    }

    public void setTechnicaldata(String technicaldata) {
        this.technicaldata = technicaldata;
    }

    @XmlTransient
    public Collection<Operation> getOperationCollection() {
        return operationCollection;
    }

    public void setOperationCollection(Collection<Operation> operationCollection) {
        this.operationCollection = operationCollection;
    }

    
    public ReportUser getReportUser() {
        return reportUser;
    }

    public void setReportUser(ReportUser reportUser) {
        this.reportUser = reportUser;
    }

    
    
}
	
	
