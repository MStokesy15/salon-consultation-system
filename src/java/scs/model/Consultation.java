package scs.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import javax.persistence.Basic;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.Part;

/**
 * Consultation model
 * All attributes are created in the database
 * 
 * 
 * @author matthewstokes
 */
@Entity(name = "clientconsultation")
public class Consultation implements Serializable {

    //Attributes
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String serviceType;
    private String clientLikes;
    private String clientDislikes;
    private String clientHomeStyle;
    private String clientHomeProducts;
    private String clientLikesStyles;
    private String clientPrevServices;
    private String clientAllergies;
    private String clientLastColour;
    private String clientColourWasLike;
    private String clientColourProblems;
    private String clientLikesTones;
    private String clientLikesCurrentColour;
    private String clientHasWhiteHair;
    @Temporal(TemporalType.TIMESTAMP)
    private Date appointmentDate;

    public Consultation() {
    }

    //Getters and setters
    @Lob
    @Basic(fetch = LAZY)
    private byte[] clientPhoto;

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

    public String getClientPrevServices() {
        return clientPrevServices;
    }

    public void setClientPrevServices(String clientPrevServices) {
        this.clientPrevServices = clientPrevServices;
    }

    public String getClientLikesStyles() {
        return clientLikesStyles;
    }

    public void setClientLikesStyles(String clientLikesStyles) {
        this.clientLikesStyles = clientLikesStyles;
    }

    public byte[] getClientPhoto() {
        return clientPhoto;
    }

    public void setClientPhoto(Part clientPhoto) {
        if (clientPhoto != null) {
            InputStream is = null;
            System.out.println(clientPhoto);
            try {
                is = clientPhoto.getInputStream();

                byte[] targetArray = new byte[is.available()];

                is.read(targetArray);

                this.clientPhoto = targetArray;

            } catch (IOException ioe) {
                System.out.println("Exception while reading file " + ioe);
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException ioe) {
                    System.out.println("Error while closing stream: " + ioe);
                }
            }
        } else {
            this.clientPhoto = null;
        }
    }

    public String getClientHomeProducts() {
        return clientHomeProducts;
    }

    public void setClientHomeProducts(String clientHomeProducts) {
        this.clientHomeProducts = clientHomeProducts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consultation)) {
            return false;
        }
        Consultation other = (Consultation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitybeans.HairStyling[ id=" + id + " ]";
    }

}
