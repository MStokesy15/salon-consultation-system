/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scs.controller;

import com.sun.mail.iap.ByteArray;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Part;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import scs.model.Consultation;

/**
 *
 * @author matthewstokes
 */
public class ConsultationServiceTest {

    private static EJBContainer container;

    @PersistenceContext(unitName = "userdetails")
    EntityManager em;

    public ConsultationServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(EJBContainer.MODULES, new File("build/jar"));
        container = EJBContainer.createEJBContainer(properties);
        System.out.println("Opening the container");
        System.out.println("Creating entity manager");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        container.close();
        System.out.println("Closing the container");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of submitHSConsultation method, of class ConsultationService.
     */
    @Test
    public void testSubmitHSConsultation() throws Exception {
        System.out.println("submitHSConsultation");
        String username = "testSubmitHSConsultation";
        Date aptDate = null;
        String serviceType = "";
        String clientLikes = "";
        String clientDislikes = "";
        String clientHomeStyle = "";
        String clientHomeProducts = "";
        String clientLikesStyles = "";
        Part clientPhoto = null;
        ConsultationService instance = (ConsultationService) container.getContext().lookup("java:global/classes/ConsultationService");
        instance.submitHSConsultation(username, aptDate, serviceType, clientLikes, clientDislikes, clientHomeStyle,
                clientHomeProducts, clientLikesStyles, clientPhoto);
        String foundUsername = "";
        Consultation foundConsultation;
        List<Consultation> foundConsultations = instance.getConsultation(username);
        System.out.println(foundConsultations.size());
        if (foundConsultations.size() == 1) {
            foundConsultation = foundConsultations.get(0);
            foundUsername = foundConsultation.getUsername();
            instance.remove(foundConsultation.getId());
        }
        assertEquals(foundUsername, username);
    }

    /**
     * Test of submitCConsultation method, of class ConsultationService.
     */
    @Test
    public void testSubmitCConsultation() throws Exception {
        System.out.println("submitCConsultation");
        String username = "";
        Date aptDate = null;
        String serviceType = "";
        String clientLikes = "";
        String clientDislikes = "";
        String clientHomeStyle = "";
        String clientHomeProducts = "";
        String clientLikesStyles = "";
        Part clientPhoto = null;
        String clientPrevServices = "";
        String clientAllergies = "";
        String clientLastColour = "";
        String clientColourWasLike = "";
        String clientColourProblems = "";
        String clientLikesTones = "";
        String clientLikesCurrentColour = "";
        String clientHasWhiteHair = "";
        ConsultationService instance = (ConsultationService) container.getContext().lookup("java:global/classes/ConsultationService");
        instance.submitCConsultation(username, aptDate, serviceType, clientLikes, clientDislikes, clientHomeStyle, clientHomeProducts, clientLikesStyles, clientPhoto, clientPrevServices, clientAllergies, clientLastColour, clientColourWasLike, clientColourProblems, clientLikesTones, clientLikesCurrentColour, clientHasWhiteHair);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadImage method, of class ConsultationService.
     */
    @Test
    public void testLoadImage() throws Exception {
        System.out.println("loadImage");
        String username = "";
        ConsultationService instance = (ConsultationService) container.getContext().lookup("java:global/classes/ConsultationService");
        byte[] expResult = null;
        byte[] result = instance.loadImage(username);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConsultation method, of class ConsultationService.
     */
    @Test
    public void testGetConsultation() throws Exception {
        System.out.println("getConsultation");
        String username = "testGetConsultation";
        Date aptDate = null;
        String serviceType = "";
        String clientLikes = "";
        String clientDislikes = "";
        String clientHomeStyle = "";
        String clientHomeProducts = "";
        String clientLikesStyles = "";
        Part clientPhoto = null;
        ConsultationService instance = (ConsultationService) container.getContext().lookup("java:global/classes/ConsultationService");
        instance.submitHSConsultation(username, aptDate, serviceType, clientLikes, clientDislikes, clientHomeStyle,
                clientHomeProducts, clientLikesStyles, clientPhoto);
        String foundUsername = "";
        Consultation foundConsultation;
        List<Consultation> foundConsultations = instance.getConsultation(username);
        System.out.println(foundConsultations.size());
        if (foundConsultations.size() == 1) {
            foundConsultation = foundConsultations.get(0);
            foundUsername = foundConsultation.getUsername();
            instance.remove(foundConsultation.getId());
        }
        assertEquals(foundUsername, username);
    }

    /**
     * Test of getConsultations method, of class ConsultationService.
     */
    @Test
    public void testGetConsultations() throws Exception {
        System.out.println("getConsultations");
        ConsultationService instance = (ConsultationService) container.getContext().lookup("java:global/classes/ConsultationService");
        List<Consultation> expResult = null;
        List<Consultation> result = instance.getConsultations();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDueAppointments method, of class ConsultationService.
     */
    @Test
    public void testGetDueAppointments() throws Exception {
        System.out.println("getDueAppointments");
        ConsultationService instance = (ConsultationService) container.getContext().lookup("java:global/classes/ConsultationService");
        List<Consultation> expResult = null;
        List<Consultation> result = instance.getDueAppointments();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
