/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scs.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import scs.model.User;

/**
 *
 * @author matthewstokes
 */
public class UserServiceTest {

    private static EJBContainer container;

    @PersistenceContext(unitName = "userdetails")
    EntityManager em;

    public UserServiceTest() {
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
     * Test of createUserAndGroup method, of class UserService.
     */
    @Test
    public void testCreateUserAndGroup() throws Exception {
        System.out.println("createUserAndGroup");
        String username = "testCreateUserAndGroup";
        String password = "";
        String groupname = "";
        String firstName = "";
        String lastName = "";
        String tel = "";
        UserService instance = (UserService) container.getContext().lookup("java:global/classes/UserService");
        instance.createUserAndGroup(username, password, groupname, firstName, lastName, tel);
        User foundUser = instance.find(username);
        System.out.println("The user was found: " + foundUser.getUsername());
        instance.remove(username);
        assertEquals(foundUser.getUsername(), username);
        //assertEquals(foundUser.getPassword(), instance.encodePassword(password));
        //assertEquals(foundUser.getFirstName(), firstName);
    }

    /**
     * Test of createUserAndGroup method, of class UserService.
     */
    @Test
    public void testDuplicateCreateUserAndGroup() throws Exception {
        System.out.println("createUserAndGroup");
        String username = "testDuplicateCreateUserAndGroup";
        String password = "";
        String groupname = "";
        String firstName = "";
        String lastName = "";
        String tel = "";
        UserService instance = (UserService) container.getContext().lookup("java:global/classes/UserService");
        try {
            System.out.println("Adding user.");
            instance.createUserAndGroup(username, password, groupname, firstName, lastName, tel);
            System.out.println("Attempting to add user again");
            instance.createUserAndGroup(username, password, groupname, firstName, lastName, tel);
        } catch (Exception e) {
        }
        List<User> users = instance.getUsers(username);
        instance.remove(username);

        assert users.size() == 1;
    }

    /**
     * Test of save method, of class UserService.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        //set up the before user
        String username = "testSave";
        String firstName = "b";
        String lastName = "c";
        String tel = "d";
        //set up the after user
        String firstName2 = "e";

        //create the service and save it
        UserService instance = (UserService) container.getContext().lookup("java:global/classes/UserService");
        //persist the user entity
        instance.createUserAndGroup(username, "", "", firstName, lastName, tel);
        User user = instance.find(username);
        String before = user.getFirstName();
        System.out.println("User before update: " + user.getFirstName());
        instance.save(user, username, firstName2, lastName, tel);
        
        System.out.println("Retrieving updated user");
        user = instance.find(username);
        String after = user.getFirstName();
        System.out.println("Before: " + before + ", After: " + after);

        instance.remove(username);

        assertNotEquals(before, after);
    }

    /**
     * Test of getUsers method, of class UserService.
     */
    @Test
    public void testGetUsers() throws Exception {
        System.out.println("getUsers");
        List<String> inputs = new ArrayList<>(Arrays.asList("1", "2", "3"));
        UserService instance = (UserService) container.getContext().lookup("java:global/classes/UserService");
        instance.createUserAndGroup("1", "", "", "", "", "");
        instance.createUserAndGroup("2", "", "", "", "", "");
        instance.createUserAndGroup("3", "", "", "", "", "");

        List<User> users = instance.getUsers();
        int counter = 0;
        for (User user: users){
            String username = user.getUsername();
            if (inputs.contains(username)){
                counter++;
                instance.remove(username);
            }
        }
        
        assert counter == 3;
    }

    /**
     * Test of getFullName method, of class UserService.
     */
    @Test
    public void testGetFullName() throws Exception {
        System.out.println("getFullName");
        String username = "testGetFullName";
        String firstName = "a";
        String lastName = "b";
        String fullName = "a b";
        UserService instance = (UserService) container.getContext().lookup("java:global/classes/UserService");
        instance.createUserAndGroup(username, "", "", firstName, lastName, "");
        User user = instance.find(username);
        instance.remove(username);
        assertEquals(user.getFullName(), fullName);
    }

    /**
     * Test of find method, of class UserService.
     */
    @Test
    public void testFind_userNotFound() throws Exception {
        System.out.println("find");
        String username = "";
        UserService instance = (UserService) container.getContext().lookup("java:global/classes/UserService");
        User expResult = null;
        User result = instance.find(username);

        assertEquals(expResult, result);
    }

    /**
     * Test of find method, of class UserService.
     */
    @Test
    public void testFind_userFound() throws Exception {
        System.out.println("find");
        String username = "testFind_userFound";
        UserService instance = (UserService) container.getContext().lookup("java:global/classes/UserService");
        instance.createUserAndGroup(username, "", "", "", "", "");
        User user = instance.find(username);
        instance.remove(username);
        assertEquals(user.getUsername(), username);
    }

}
