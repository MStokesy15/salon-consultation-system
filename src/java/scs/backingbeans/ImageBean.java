package scs.backingbeans;

import java.io.IOException;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 * Image bean
 * Provides methods to store and 
 * retrieve photos from memory
 * 
 * @author matthewstokes
 */
@Named(value = "imageBean")
@ApplicationScoped
public class ImageBean {

    //Variables
    private byte[] clientPhoto;
    private Date lastModified;

    /**
     * Get the last modified date of this object
     * 
     * @return 
     */
    public Date getLastModified() {
        return lastModified;
    }

    /**
     * Set the client photo
     * 
     * @param clientPhoto 
     */
    public void setClientPhoto(byte[] clientPhoto) {
        this.clientPhoto = clientPhoto;
        Date date = new Date();
        this.lastModified = date;
    }

    /**
     * Get the client photo
     * 
     * @return clientPhoto
     * @throws IOException 
     */
    public byte[] getClientPhoto() throws IOException {
        if (clientPhoto != null) {
            return clientPhoto;
        }
        return null;
    }
}
