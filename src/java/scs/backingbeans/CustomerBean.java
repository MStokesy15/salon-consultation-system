package scs.backingbeans;

import scs.model.Consultation;
import scs.model.User;
import scs.controller.ConsultationService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import scs.controller.UserService;

/**
 * Customer management bean
 *
 * @author matthewstokes
 */
@Named(value = "customerBean")
@ViewScoped
public class CustomerBean implements Serializable {

    //Variables
    private List<User> users;
    private User selectedEntity;
    private List<Consultation> consultations = null;

    //Injection of services
    @Inject
    UserService uService;
    
    //Constructor
    @PostConstruct
    public void init() {
        users = uService.getUsers();
        System.out.println("Initialisation of CustomerBean successful");
    }

    //Injection of services
    @Inject
    ConsultationService cService;

    @Inject
    ImageBean iBean;

    /**
     * Get all users from the users table
     *
     * @return users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Gets the current selected user
     *
     * @return selected entity
     */
    public User getSelectedEntity() {
        iBean.setClientPhoto(null);
        getHSConsultation();
        return selectedEntity;
    }

    /**
     * Re-initialise the consultation on change of user
     */
    public void getHSConsultation() {
        if (this.selectedEntity == null) { //Initialise to null
            consultations = null;
            iBean.setClientPhoto(null);
        } else { //Initialise consultations and clientPhoto
            System.out.println(selectedEntity);
            consultations = cService.getConsultation(this.getUsername());
            if (consultations == null || consultations.isEmpty()) {
                iBean.setClientPhoto(null);
            } else {
                iBean.setClientPhoto(consultations.get(consultations.size() - 1).getClientPhoto());
                System.out.print("getting consultation... qty: ");
                System.out.println(consultations.size() - 1);

            }
        }
    }

    /**
     * Get the selected user's dislikes
     *
     * @return client dislikes
     */
    public String getClientDislikes() {
        if (consultations == null || consultations.isEmpty()) {
            return "";
        } else {
            String values = consultations.get(consultations.size() - 1).getClientDislikes();
            String[] splitValues = values.split(",");
            StringBuilder outputString = new StringBuilder();
            for (int i = 0; i < splitValues.length; i++) {
                outputString.append(splitValues[i]);
                if (i < splitValues.length - 1) {
                    outputString.append(", ");
                }
            }
            return outputString.toString();
        }
    }

    /**
     * Get the selected user's likes
     *
     * @return client likes
     */
    public String getClientLikes() {
        if (consultations == null || consultations.isEmpty()) {
            return "";
        } else {
            String values = consultations.get(consultations.size() - 1).getClientLikes();
            String[] splitValues = values.split(",");
            StringBuilder outputString = new StringBuilder();
            for (int i = 0; i < splitValues.length; i++) {
                outputString.append(splitValues[i]);
                if (i < splitValues.length - 1) {
                    outputString.append(", ");
                }
            }
            return outputString.toString();
        }
    }

    /**
     * Get the selected user's consultation service type
     *
     * @return consultation service type
     */
    public String getServiceType() {
        if (consultations == null || consultations.isEmpty()) {
            return "";
        } else {
            return consultations.get(consultations.size() - 1).getServiceType();
        }
    }

    /**
     * Get the selected user's home style
     *
     * @return client home style
     */
    public String getClientHomeStyle() {
        if (consultations == null || consultations.isEmpty()) {
            return "";
        } else {
            return consultations.get(consultations.size() - 1).getClientHomeStyle();
        }
    }

    /**
     * Get the selected user's home products
     *
     * @return client home products
     */
    public String getClientHomeProducts() {
        if (consultations == null || consultations.isEmpty()) {
            return "";
        } else {
            return consultations.get(consultations.size() - 1).getClientHomeProducts();
        }
    }

    /**
     * Get the selected user's styles they like
     *
     * @return client likes styles
     */
    public String getClientLikesStyles() {
        if (consultations == null || consultations.isEmpty()) {
            return "";
        } else {
            return consultations.get(consultations.size() - 1).getClientLikesStyles();
        }
    }

    /**
     * Get the selected user's username
     *
     * @return client username
     */
    public String getUsername() {
        if (selectedEntity == null) {
            return "";
        } else {
            return (String) (selectedEntity.getEmail());
        }
    }

    /**
     * Get the selected user's first name
     *
     * @return client first name
     */
    public String getFirstName() {
        if (selectedEntity == null) {
            return "";
        } else {
            return (String) (selectedEntity.getFirstName());
        }
    }

    /**
     * Get the selected user's last name
     *
     * @return client last name
     */
    public String getLastName() {
        if (selectedEntity == null) {
            return "";
        } else {
            return (String) (selectedEntity.getLastName());
        }
    }

    /**
     * Get the selected user's telephone number
     *
     * @return client telephone number
     */
    public String getTel() {
        if (selectedEntity == null) {
            return "";
        } else {
            return (String) (selectedEntity.getTel());
        }
    }

    /**
     * Set the selected entity
     * 
     * @param selectedEntity
     */
    public void setSelectedEntity(User selectedEntity) {
        this.selectedEntity = selectedEntity;
    }

    /**
     * Destroy the iBean service before this is garbage collected
    */
    @PreDestroy
    public void kill() {
        iBean = null;
    }
}
