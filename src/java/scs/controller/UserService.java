package scs.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.PersistenceContext;
import javax.xml.bind.DatatypeConverter;
import scs.model.Group;
import scs.model.User;
import java.util.List;

/**
 * User Service User Controller
 *
 * @author matthewstokes
 */
@Stateless
public class UserService {

    //Variables
    private MessageDigest md;

    @PersistenceContext(unitName = "userdetails")
    EntityManager em;

    /**
     * Creates a new entity in the user model for new users
     *
     * @param username
     * @param password
     * @param groupname
     * @param firstName
     * @param lastName
     * @param tel
     */
    public void createUserAndGroup(String username, String password, String groupname,
            String firstName, String lastName, String tel) {
        User user = new User();
        user.setUsername(username);
        String encodedPassword = encodePassword(password);
        user.setPassword(encodedPassword);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setFullName(firstName + " " + lastName);
        user.setTel(tel);
        user.setEmail(username);
        em.persist(user);
        Group group = new Group();
        group.setGroupname(groupname);
        group.setUsername(username);
        em.persist(group);
    }

    /**
     * Updates an existing user in the user model
     *
     * @param user
     * @param username
     * @param firstName
     * @param lastName
     * @param tel
     */
    public void save(User user, String username, String firstName, String lastName, String tel) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setFullName(firstName + " " + lastName);
        user.setTel(tel);
        user.setEmail(username);
        em.merge(user);
    }

    /**
     * Clear the entity manager's cache
     */
    public void clear() {
        em.clear();
    }

    /**
     * Returns all users from the user model
     *
     * @return users
     */
    public List<User> getUsers() {
        return (List<User>) em.createQuery("select u from users u",
                User.class).getResultList();
    }

    /**
     * Returns the selected user from the user model
     * 
     * @param username
     * @return user
     */
    public List<User> getUsers(String username) {
        return (List<User>) em.createQuery("select u from users u where u.username like :user",
                User.class)
                .setParameter("user", username)
                .getResultList();
    }

    /**
     * Returns the full name of the specified user
     * 
     * @param username
     * @return full name
     */
    public String getFullName(String username) {
        User u = this.find(username);
        return u.getFullName();
    }

    /**
     * Returns the specified user
     * 
     * @param username
     * @return user
     */
    public User find(String username) {
        return em.find(User.class, username);
    }

    /**
     * Removes the specified user from the model
     * 
     * @param username 
     */
    public void remove(String username) {
        User user = em.find(User.class, username);
        em.remove(user);
    }

    /**
     * Encrypts a password
     * 
     * @param password
     * @return encoded password
     */
    private String encodePassword(String password) {
        if (md == null) {
            try {
                md = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            md.update(password.getBytes("UTF-8"));
            byte[] digest = md.digest();
            return DatatypeConverter.printBase64Binary(digest).toString();

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
