package com.primrose;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by evanpthompson on 10/26/2016.
 * A specialized type of User that does not include password.
 */

@XmlRootElement(name = "publicuser")
public class PublicUser extends User {

    /*  attributes  */
    private int id;
    private String givenName;
    private String userName;
    private String emailAddress;

    /*  no-args constructor  */
    public PublicUser() {}

    /*  all-args constructor  */
    public PublicUser(int id, String givenName, String userName, String emailAddress) {
        this.id = id;
        this.givenName = givenName;
        this.userName = userName;
        this.emailAddress = emailAddress;
    }


    /*  getters and setters  */
    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    @XmlElement
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @XmlElement
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /*  overridden toString() method  */
    @Override
    public String toString() {
        return String.format("ID: %d, Given name: %s, Username: %s, Email Address: %s", id, givenName, userName, emailAddress);
    }
}
