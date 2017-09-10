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
 * Colour Consultation View
 * 
 * @author matthewstokes
 */
@Named(value = "colourConsultation")
@ViewScoped
public class ColourConsultation implements Serializable{

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
    private Part clientPhoto;
    private String clientPrevServices;
    private String clientAllergies;
    private String clientLastColour;
    private String clientColourWasLike;
    private String clientColourProblems;
    private String clientLikesTones;
    private String clientLikesCurrentColour;
    private String clientHasWhiteHair;
    private Date appointmentDate;

    /**
     * Submits a colour consultation to the consultation service
     * 
     * @throws IOException 
     */
    public void submitCConsultation() throws IOException {
        System.out.println("Submitting consultation");
        String username = (FacesContext.getCurrentInstance()
                .getExternalContext().getRemoteUser());
        service.submitCConsultation(username, appointmentDate, serviceType,
                clientLikes, clientDislikes, clientHomeStyle,
                clientHomeProducts, clientLikesStyles, clientPhoto,
                clientPrevServices, clientAllergies, clientLastColour, 
                clientColourWasLike, clientColourProblems, clientLikesTones, 
                clientLikesCurrentColour, clientHasWhiteHair);
        iBean.setClientPhoto(null);
        System.out.println("Redirecting...");
    }
    
    //Getters and Setters
    public String getClientPrevServices() {
        return clientPrevServices;
    }

    public void setClientPrevServices(String clientPrevServices) {
        this.clientPrevServices = clientPrevServices;
    }

    public String getClientAllergies() {
        return clientAllergies;
    }

    public void setClientAllergies(String clientAllergies) {
        this.clientAllergies = clientAllergies;
    }

    public String getClientLastColour() {
        return clientLastColour;
    }

    public void setClientLastColour(String clientLastColour) {
        this.clientLastColour = clientLastColour;
    }

    public String getClientColourWasLike() {
        return clientColourWasLike;
    }

    public void setClientColourWasLike(String clientColourWasLike) {
        this.clientColourWasLike = clientColourWasLike;
    }

    public String getClientColourProblems() {
        return clientColourProblems;
    }

    public void setClientColourProblems(String clientColourProblems) {
        this.clientColourProblems = clientColourProblems;
    }

    public String getClientLikesTones() {
        return clientLikesTones;
    }

    public void setClientLikesTones(String clientLikesTones) {
        this.clientLikesTones = clientLikesTones;
    }

    public String getClientLikesCurrentColour() {
        return clientLikesCurrentColour;
    }

    public void setClientLikesCurrentColour(String clientLikesCurrentColour) {
        this.clientLikesCurrentColour = clientLikesCurrentColour;
    }

    public String getClientHasWhiteHair() {
        return clientHasWhiteHair;
    }

    public void setClientHasWhiteHair(String clientHasWhiteHair) {
        this.clientHasWhiteHair = clientHasWhiteHair;
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

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        System.out.println(appointmentDate);
        this.appointmentDate = appointmentDate;
    }

}
