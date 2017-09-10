package scs.view;

import scs.model.Visit;
import scs.controller.UserService;
import scs.controller.VisitService;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.faces.view.ViewScoped;

/**
 * Visit View
 * 
 * @author matthewstokes
 */
@Named
@ViewScoped
public class VisitView implements Serializable {

    //Variables
    @Inject
    VisitService service;

    @Inject
    UserService uService;

    private String username;
    private Date date;
    private String serviceType;
    private String comments;
    private List<String> serviceTypes;

    /**
     * Creates a new instance of VisitView
     */
    public VisitView() {
        serviceTypes = new ArrayList<>(Arrays.asList("Skin Test",
                "Blowdry", "Haircut", "Hairup", "Braid", "Highlights",
                "Root Regrowth", "Full Head Colour", "Ballayage"));
        date = new Date();
    }

    /**
     * Updates a user's visit history.
     */
    public void recordVisit() {
        try {
            System.out.println(date + "   " + serviceType);
            service.recordVisit(username, date, serviceType, comments);
            System.out.println("Visit Recorded for " + username + " with Service Type = " + serviceType);
            this.serviceType = null;
            this.comments = null;
            this.date = null;
            FacesContext.getCurrentInstance().getExternalContext().redirect("../stylist_portal/viewuserprofiles.xhtml");
        } catch (Exception e) {
            System.out.println("Error occured in class: " + this.getClass() + e);
        }
    }

    //Getters and Setters
    public List<String> getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(List<String> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        //If the username is instantiated in the view then the user is a stylist.
        if (username == null) {
            //use the clients login (They are a customer).
            return uService.find(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()).getFullName();
        }
        return uService.find(username).getFullName();
    }

    public List<Visit> getVisits() {
        //If the username is instantiated in the view then the user is a stylist.
        if (username == null) {
            //use the clients login (They are a customer).
            return service.getVisits(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        }
        return service.getVisits(username);
    }
}
