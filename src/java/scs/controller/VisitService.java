package scs.controller;

import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import scs.model.Visit;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * Visit Service
 * Visit Controller
 * 
 * @author matthewstokes
 */
@Stateless
public class VisitService {

    //Variables
    @PersistenceContext(unitName = "userdetails")
    EntityManager em;

    /**
     * Creates a new entity in the visit model
     * 
     * @param username
     * @param date
     * @param serviceType
     * @param comments
     * @throws ParseException 
     */
    public void recordVisit (String username, Date date, String serviceType, String comments)  throws ParseException{
        Visit visit = new Visit();
        visit.setUsername(username);
        visit.setServiceType(serviceType);
        visit.setDate(date);
        visit.setComments(comments);
        em.persist(visit);
    }

    /**
     * Returns all visits for the specified user
     * 
     * @param username
     * @return visits
     */
    public List<Visit> getVisits(String username) {
        return (List<Visit>) em.createQuery("select v from visit v where v.username like :user",
                Visit.class)
                .setParameter("user", username)
                .getResultList();
    }

    /**
     * Checks if the user has a skin test within 
     * 6 months of the current date
     * 
     * @param username
     * @return boolean
     */
    public Boolean hasValidSkinTest(String username) {
        Calendar c = Calendar.getInstance(); // starts with today's date and time
        c.add(Calendar.MONTH, -6);  // subtracts 6 months
        Date startDate = c.getTime(); // gets modified time
        Date endDate = new Date();
        System.out.println("Pulling visits from DB for valid skin tests.");

        List<Visit> visits = (List<Visit>) em.createQuery("select v from visit v WHERE v.username like :uname AND v.date BETWEEN :startDate AND :endDate")
                .setParameter("uname", username)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList(); //testing only!
        if (!visits.isEmpty()) {
            System.out.println("Skin test is valid");
            return true;
        }
        System.out.println("Skin test is invalid");
        return false; //Needs to check if skin test within last 6 months.
    }
    

}
