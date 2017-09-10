package scs.backingbeans;

import java.io.IOException;
import scs.model.User;
import scs.controller.UserService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * User profile bean
 * For managing customer profiles
 *
 * @author matthewstokes
 */
@Named(value = "userProfileBean")
@ManagedBean
@ViewScoped
public class UserProfileBean implements Serializable {

    //Variables
    private String username;

    private String firstName;
    private String lastName;
    private String tel;
    private User user;

    //Inject service   
    @Inject
    UserService service;

    //Constructor
    @PostConstruct
    public void init() {
        String currentUser = (FacesContext.getCurrentInstance()
                .getExternalContext().getRemoteUser());
        if (currentUser != null) {
            user = service.find(currentUser);
            this.username = currentUser;
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.tel = user.getTel();
        }
    }

    /**
     * Saves the profile updates.
     * 
     * @throws IOException 
     */
    public void saveUpdatedProfile() throws IOException {
        if (this.tel.length() != 11) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Invalid telephone number!"));
        } else {
            service.save(user, this.username, 
                    this.firstName, this.lastName, this.tel);
            ExternalContext ec = FacesContext
                    .getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest())
                    .getRequestURI());
        }
    }

    /**
     * Checks if the current user is in the stylist role
     * 
     * @return boolean
     */
    public boolean getIsStylist() {
        return FacesContext.getCurrentInstance()
                .getExternalContext().isUserInRole("stylists");
    }

    /**
     * Checks if a user is logged in
     * 
     * @return boolean
     */
    public boolean getIsLoggedIn() {
        return FacesContext.getCurrentInstance()
                .getExternalContext().getRemoteUser() != null;
    }

    /**
     * Returns the user's username
     * 
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username
     * 
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the user's first name
     * 
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name
     * 
     * @param firstName 
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the user's last name
     * 
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's last name
     * 
     * @param lastName 
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the user's telephone number
     * 
     * @return tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * Sets the user's telephone number
     * 
     * @param tel 
     */
    public void setTel(String tel) {
        this.tel = tel;
    }
}
