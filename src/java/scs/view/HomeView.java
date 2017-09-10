package scs.view;

import scs.model.Consultation;
import scs.controller.ConsultationService;
import scs.controller.UserService;
import scs.controller.VisitService;
import java.io.Serializable;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import scs.utilities.DTConverter;

/**
 * Home View
 * 
 * @author matthewstokes
 */
@Named(value = "homeView")
@ViewScoped
public class HomeView implements Serializable {

    //Variables
    private Map<String, String> overdueSkinTests;

    @Inject
    VisitService vService;

    @Inject
    ConsultationService cService;

    @Inject
    UserService uService;

    /**
     * Creates a new instance of HomeView
     */
    public HomeView() {
        overdueSkinTests = new HashMap<>();
    }

    /**
     * Returns all overdue skin tests
     * 
     * @return full names, appointment dates
     * @throws ParseException 
     */
    public Map<String, String> getOverdueSkinTests() throws ParseException { //String fullName, String appointmentDate
        List<Consultation> consultations = cService.getDueAppointments();
        System.out.println("Getting due appointments, found: " + consultations.size());

        for (Consultation consultation : consultations) {

            String fullName = uService.getFullName(consultation.getUsername());
            System.out.println("Checking validity of skin test for " + fullName);
            Boolean hasValidSkinTest = vService.hasValidSkinTest(consultation.getUsername());
            System.out.println("Validity = " + hasValidSkinTest);
            String aptDate = DTConverter.date2string(consultation.getAppointmentDate());
            System.out.println("aptDate = " + aptDate);
            if (!hasValidSkinTest) {
                overdueSkinTests.put(fullName, aptDate);
            }
        }

        return overdueSkinTests;

    }

    /**
     * Sets the overdue skin tests
     * 
     * @param overdueSkinTests 
     */
    public void setOverdueSkinTests(Map<String, String> overdueSkinTests) {
        this.overdueSkinTests = overdueSkinTests;
    }

}
