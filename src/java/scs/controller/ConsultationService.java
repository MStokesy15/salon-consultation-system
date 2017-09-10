package scs.controller;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.PersistenceContext;

import scs.model.Consultation;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.Part;

/**
 * Consultation Service Bean
 * Consultation Controller
 *
 * @author matthewstokes
 */
@Stateless
public class ConsultationService {

    //Variables
    @PersistenceContext(unitName = "userdetails")
    EntityManager em;

    /**
     * Creates a new entity in the clientconsultation model
     * for hairstyling consultations
     * 
     * @param username
     * @param aptDate
     * @param serviceType
     * @param clientLikes
     * @param clientDislikes
     * @param clientHomeStyle
     * @param clientHomeProducts
     * @param clientLikesStyles
     * @param clientPhoto 
     */
    public void submitHSConsultation(String username, Date aptDate, String serviceType, String clientLikes,
            String clientDislikes, String clientHomeStyle, String clientHomeProducts,
            String clientLikesStyles, Part clientPhoto) {
        Consultation consultation = new Consultation();
        consultation.setUsername(username);
        consultation.setAppointmentDate(aptDate);
        consultation.setServiceType(serviceType);
        consultation.setClientLikes(clientLikes);
        consultation.setClientDislikes(clientDislikes);
        consultation.setClientHomeStyle(clientHomeStyle);
        consultation.setClientHomeProducts(clientHomeProducts);
        consultation.setClientLikesStyles(clientLikesStyles);
        consultation.setClientPhoto(clientPhoto);
        em.persist(consultation);
    }

    /**
     * Creates a new entity in the clientconsultation model
     * for colour consultations
     * 
     * @param username
     * @param aptDate
     * @param serviceType
     * @param clientLikes
     * @param clientDislikes
     * @param clientHomeStyle
     * @param clientHomeProducts
     * @param clientLikesStyles
     * @param clientPhoto
     * @param clientPrevServices
     * @param clientAllergies
     * @param clientLastColour
     * @param clientColourWasLike
     * @param clientColourProblems
     * @param clientLikesTones
     * @param clientLikesCurrentColour
     * @param clientHasWhiteHair 
     */
    public void submitCConsultation(String username, Date aptDate, String serviceType, String clientLikes,
            String clientDislikes, String clientHomeStyle, String clientHomeProducts,
            String clientLikesStyles, Part clientPhoto, String clientPrevServices,
            String clientAllergies, String clientLastColour, String clientColourWasLike,
            String clientColourProblems, String clientLikesTones, String clientLikesCurrentColour,
            String clientHasWhiteHair) {
        Consultation consultation = new Consultation();
        consultation.setUsername(username);
        consultation.setAppointmentDate(aptDate);
        consultation.setServiceType(serviceType);
        consultation.setClientLikes(clientLikes);
        consultation.setClientDislikes(clientDislikes);
        consultation.setClientHomeStyle(clientHomeStyle);
        consultation.setClientHomeProducts(clientHomeProducts);
        consultation.setClientLikesStyles(clientLikesStyles);
        consultation.setClientPhoto(clientPhoto);
        consultation.setClientPrevServices(clientPrevServices);
        consultation.setClientAllergies(clientAllergies);
        consultation.setClientLastColour(clientLastColour);
        consultation.setClientColourWasLike(clientColourWasLike);
        consultation.setClientColourProblems(clientColourProblems);
        consultation.setClientLikesTones(clientLikesTones);
        consultation.setClientLikesCurrentColour(clientLikesCurrentColour);
        consultation.setClientHasWhiteHair(clientHasWhiteHair);
        em.persist(consultation);
    }

    /**
     * Returns the latest image for the specified username
     * from the clientconsultation model
     * 
     * @param username
     * @return client photo
     * @throws IOException 
     */
    public byte[] loadImage(String username) throws IOException {
        long i = 1;
        //return em.createQuery("SELECT p FROM ClientPhoto p WHERE p.user = :userName")
        //.setParameter("userName", "user@server.com");
        return em.find(Consultation.class, i).getClientPhoto();
    }

    /**
     * Returns all consultations for the specified username
     * 
     * @param username
     * @return consultations
     */
    public List<Consultation> getConsultation(String username) {
        System.out.println("consultation: " + username);
        return (List<Consultation>) em.createQuery("select c from clientconsultation c where c.username like :uname")
                .setParameter("uname", username)
                .getResultList();
    }

    /**
     * Returns all consultations in the clientconsultation model
     * 
     * @return consultations
     */
    public List<Consultation> getConsultations() {
        return (List<Consultation>) em.createQuery("select c from clientconsultation c")
                .getResultList();
    }

    /**
     * Returns all consultations where appointment date
     * is within 2 calendar days of the current date
     * 
     * @return consultations
     * @throws ParseException 
     */
    public List<Consultation> getDueAppointments() throws ParseException {
        Calendar c = Calendar.getInstance(); // starts with today's date and time
        c.add(Calendar.DAY_OF_YEAR, 2);  // advances day by 2
        Date endDate = c.getTime(); // gets modified time
        Date startDate = new Date();
        System.out.println("Pulling consultations from DB for appointments between " + startDate + " and " + endDate);
        return (List<Consultation>) em.createQuery("select c from clientconsultation c WHERE c.appointmentDate BETWEEN :startDate AND :endDate")
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    /**
     * Removes a consultation at the specified index
     * in the model
     * 
     * @param id 
     */
    public void remove(Long id) {
        Consultation c = em.find(Consultation.class, id);
        em.remove(c);
    }
}
