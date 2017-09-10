package scs.view;

import scs.backingbeans.ImageBean;
import javax.inject.Inject;
import javax.inject.Named;
import scs.controller.ConsultationService;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.Part;

/**
 * Hairstyling Consultation View
 * 
 * @author matthewstokes
 */
@Named(value = "hairStylingConsultation")
@ViewScoped
public class HairStylingConsultation implements Serializable {

    //Variables
    @Inject
    ConsultationService service;

    @Inject
    ImageBean iBean;

    private String serviceType;
    private String clientLikes;
    private String clientDislikes;
    private String clientHomeStyle;
    private String clientHomeProducts;
    private String clientLikesStyles;
    private Date appointmentDate;
    private Part clientPhoto;

    /**
     * Submits a hairstyling consultation to the consultation service
     * 
     * @throws IOException 
     */
    public void submitHSConsultation() throws IOException {
        //check if an entry exists for user, if so update rather than persist??
        System.out.println("Submitting consultation");
        String username = (FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        service.submitHSConsultation(username, appointmentDate, serviceType, clientLikes, clientDislikes, clientHomeStyle,
                clientHomeProducts, clientLikesStyles, clientPhoto);
        iBean.setClientPhoto(null);
        System.out.println("Redirecting...");
    }
    
    public Part getClientPhoto() {
        return clientPhoto;
    }

    public void setClientPhoto(Part clientPhoto) throws IOException {
        this.clientPhoto = clientPhoto;
        InputStream is = clientPhoto.getInputStream();
        byte[] targetArray = new byte[is.available()];
        is.read(targetArray);
        iBean.setClientPhoto(targetArray);
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        System.out.println("The appointment date is: " + appointmentDate);
        this.appointmentDate = appointmentDate;
    }

    public String getClientLikesStyles() {
        return clientLikesStyles;
    }

    public void setClientLikesStyles(String clientLikesStyles) {
        this.clientLikesStyles = clientLikesStyles;
    }

    public String getClientHomeProducts() {
        return clientHomeProducts;
    }

    public void setClientHomeProducts(String clientHomeProducts) {
        this.clientHomeProducts = clientHomeProducts;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getClientLikes() {
        return clientLikes;
    }

    public void setClientLikes(String clientLikes) {
        this.clientLikes = clientLikes;
    }

    public String getClientDislikes() {
        return clientDislikes;
    }

    public void setClientDislikes(String clientDislikes) {
        this.clientDislikes = clientDislikes;
    }

    public String getClientHomeStyle() {
        return clientHomeStyle;
    }

    public void setClientHomeStyle(String clientHomeStyle) {
        this.clientHomeStyle = clientHomeStyle;
    }

}
