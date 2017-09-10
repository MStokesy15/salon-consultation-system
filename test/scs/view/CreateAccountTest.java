/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scs.view;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matthewstokes
 */
public class CreateAccountTest {

    private static EJBContainer container;
    
    EntityManager em;
    
    public CreateAccountTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(EJBContainer.MODULES, new File("build/jar"));
        container = EJBContainer.createEJBContainer(properties);
        System.out.println("Opening the container");

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
     * Test of save method, of class CreateAccount.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        CreateAccount instance = new CreateAccount();
        
        instance.setFirstName("a");
        instance.setLastName("b");
        instance.setPassword("c");
        instance.setTel("d");
        instance.setUsername("e");

        instance.save();
        
        CreateAccount found = em.find(CreateAccount.class, instance.getUsername());
        System.out.println(found.getUsername());
        assertEquals(instance, found);
    }

    /**
     * Test of getUsername method, of class CreateAccount.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        CreateAccount instance = new CreateAccount();
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsername method, of class CreateAccount.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "";
        CreateAccount instance = new CreateAccount();
        instance.setUsername(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class CreateAccount.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        CreateAccount instance = new CreateAccount();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class CreateAccount.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        CreateAccount instance = new CreateAccount();
        instance.setPassword(password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstName method, of class CreateAccount.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        CreateAccount instance = new CreateAccount();
        String expResult = "";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFirstName method, of class CreateAccount.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String firstName = "";
        CreateAccount instance = new CreateAccount();
        instance.setFirstName(firstName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastName method, of class CreateAccount.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        CreateAccount instance = new CreateAccount();
        String expResult = "";
        String result = instance.getLastName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLastName method, of class CreateAccount.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String lastName = "";
        CreateAccount instance = new CreateAccount();
        instance.setLastName(lastName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTel method, of class CreateAccount.
     */
    @Test
    public void testGetTel() {
        System.out.println("getTel");
        CreateAccount instance = new CreateAccount();
        String expResult = "";
        String result = instance.getTel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTel method, of class CreateAccount.
     */
    @Test
    public void testSetTel() {
        System.out.println("setTel");
        String tel = "";
        CreateAccount instance = new CreateAccount();
        instance.setTel(tel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
