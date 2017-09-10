package scs.backingbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Logout bean
 * Provides methods to log out users
 *
 * @author matthewstokes
 */
@ManagedBean
@SessionScoped
public class LogoutBean implements Serializable {

    
    public String logout() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redir‌​ect("../index.xhtml");
        return null;
    }
}
