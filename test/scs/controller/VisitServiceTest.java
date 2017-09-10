/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scs.controller;

import java.util.Date;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import scs.model.Visit;

/**
 *
 * @author matthewstokes
 */
public class VisitServiceTest {
    
    public VisitServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of recordVisit method, of class VisitService.
     */
    @Test
    public void testRecordVisit() throws Exception {
        System.out.println("recordVisit");
        String username = "";
        Date date = null;
        String serviceType = "";
        String comments = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        VisitService instance = (VisitService)container.getContext().lookup("java:global/classes/VisitService");
        instance.recordVisit(username, date, serviceType, comments);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVisits method, of class VisitService.
     */
    @Test
    public void testGetVisits() throws Exception {
        System.out.println("getVisits");
        String username = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        VisitService instance = (VisitService)container.getContext().lookup("java:global/classes/VisitService");
        List<Visit> expResult = null;
        List<Visit> result = instance.getVisits(username);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasValidSkinTest method, of class VisitService.
     */
    @Test
    public void testHasValidSkinTest() throws Exception {
        System.out.println("hasValidSkinTest");
        String username = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        VisitService instance = (VisitService)container.getContext().lookup("java:global/classes/VisitService");
        Boolean expResult = null;
        Boolean result = instance.hasValidSkinTest(username);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
