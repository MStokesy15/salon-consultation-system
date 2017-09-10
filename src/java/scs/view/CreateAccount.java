package scs.view;

import javax.enterprise.context.RequestScoped;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import scs.controller.UserService;
import javax.faces.application.FacesMessage;

/**
 * Create Account View
 * 
 * @author matthewstokes
 */
@Named
@RequestScoped
public class CreateAccount {

    //Variables
    @Inject
    UserService service;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String tel;

    /**
     * Checks for stylist flag and triggers entity creation
     * 
     * @throws IOException 
     */
    public void save() throws IOException {
        int splitPoint = username.lastIndexOf("#") + 1;
        String group;
        System.out.println(splitPoint);
        if (username.substring(splitPoint).equals("stylist")) {
            username = username.substring(0, splitPoint - 1);
            group = "stylists";
        } else {
            group = "users";
        }
        try {
            if (this.tel.length() != 11) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Invalid telephone number!"));
            } else {

                service.createUserAndGroup(username, password, group, firstName, lastName, tel);
                FacesContext.getCurrentInstance().getExternalContext().redirect("../account_creation/accountcreated.xhtml");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Account Already Exists!"));
        }
    }

    //Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
