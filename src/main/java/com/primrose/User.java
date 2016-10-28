package com.primrose;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by evanpthompson on 10/22/2016.
 * User is the java class for object user.
 */

@XmlRootElement(name = "user")
public class User {

    private int id;
    private String givenName;
    private String userName;
    private String password;
    private String emailAddress;

    public User() {}

    User(String userName) {
        this.userName = userName;
    }


    public User(String givenName, String password) {

        this.givenName = givenName;
        this.userName = generateUserName(givenName);
        this.password = password;
        this.emailAddress = generateEmailAddress(userName);


    }

    @XmlElement
    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getGivenName() {
        return this.givenName;
    }

    void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    @XmlElement
    public String getUserName() {
        return this.userName;
    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    @XmlElement
    public String getPassword() {
        return this.password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    @XmlElement
    public String getEmailAddress() {
        return this.emailAddress;
    }

    void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /* Requirement 1.0.4 */
    public static String generateUserName(String givenName) {
        String[] names = givenName.split(" ");  // separate out the names, last name defined as last word in string
        String user = "";
        int minLength = 8;

		/*  to implement unique ids a query to the db will have to be run  */

        // acount for names that are too short

        // if the given name is not 8 letters in length, add "a" to the end
        if (givenName.length() < 8) {
            user = givenName;
            while (user.length() < 8) {
                user += "a";
            }



            return user;
        }

        // if first name is less than 2 add more last name
        if (names[0].length() < 2) {

            int len = names[0].length();  // get length of last name

            user += names[0] + names[names.length-1].substring(0, (8-len));

            return user;
        }

        // if last name is less than 6 add more first name
        if (names[names.length-1].length() < 7) {
            int len = names[names.length-1].length();  // get length of last name

            user += names[0].substring(0, (8-len)) + names[names.length-1];

            return user;
        }

        user += names[0].substring(0, 2) + names[names.length-1].substring(0, 6);

        return user.toLowerCase();
    }

    /*  method to generate an email address from userName  */
    public static String generateEmailAddress(String userName) {
        String user = userName + "@companyA.com";

        return user.toLowerCase();
    }

    /*  method that instantiates a PublicUser  */
    public PublicUser getPublicUser() {
        return new PublicUser(id, givenName, userName, emailAddress);
    }


    @Override
    public String toString() {
        return String.format("givenName: %s  userName: %s", givenName, userName);
    }



}
