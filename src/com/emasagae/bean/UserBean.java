package com.emasagae.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.emasagae.dao.ObjectifyEmasaDAO;
import com.emasagae.dao.ObjectifyReportUserDAO;
import com.emasagae.entity.Emasa;
import com.emasagae.entity.Report;
import com.emasagae.entity.ReportUser;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	private ReportUser loginUser;
	private String reportSelectedEmail;
	private Report reportSelected;
	
	public UserBean() {
		initEmasa();
    }
    
    @PostConstruct
    public void init() {
    	initEmasa();
    }

	public ReportUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(ReportUser loginUser) {
		this.loginUser = loginUser;
	}

	public String getReportSelectedEmail() {
		return reportSelectedEmail;
	}

	public void setReportSelectedEmail(String reportSelectedEmail) {
		this.reportSelectedEmail = reportSelectedEmail;
	}

	public Report getReportSelected() {
		return reportSelected;
	}

	public void setReportSelected(Report reportSelected) {
		this.reportSelected = reportSelected;
	}

	public String doLogin(){
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
	    HttpServletRequest request = (HttpServletRequest) ctx.getRequest();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		loginUser = new ReportUser();
		if (user != null) {			
			ObjectifyReportUserDAO d = new ObjectifyReportUserDAO();
			ReportUser u = d.findByProperty("email", user.getNickname());
			if (u == null) {	
				loginUser.setEmail(user.getNickname());
				Long id = d.save(loginUser);
				loginUser.setId(id);
			} else {
				loginUser = u;
				loginUser.setEmail(user.getNickname());
			}
		}
		
		return userService.createLoginURL(request.getRequestURI());
	}
	
	public String doLogout(){
        loginUser = new ReportUser();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		UserService userService = UserServiceFactory.getUserService();		
	    return userService.createLogoutURL("/faces/index.xhtml");
    }
	
	public void doCheckLogin() {
        try {
            if (loginUser == null || loginUser.getEmail() == null) {
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                context.redirect(context.getRequestContextPath() + "/faces/index.xhtml");
            }
        } catch (IOException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	private void initEmasa() {
		ObjectifyEmasaDAO dao = new ObjectifyEmasaDAO();
		Emasa emasa = dao.findById("EMASA");
		if (emasa == null) {
			emasa = new Emasa("EMASA");
			dao.save(emasa);
		}
	}
		
	
}
