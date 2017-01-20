package com.emasagae.entity;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class ReportUser implements Serializable {
	 
    private static final long serialVersionUID = 1L;
    
    @Id
    private Long id;
    @Index
    private String email;

    public ReportUser() {
    }

    public ReportUser(Long id) {
        this.id = id;
    }

    public ReportUser(Long id, String email, Boolean operator) {
        this.id = id;
        this.email = email;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}