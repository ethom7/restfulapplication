package com.primrose;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by evanpthompson on 10/28/2016.
 */
@XmlRootElement(name = "emergencycontact")
public class EmergencyContact {

    private String contactName;
    private String contactRelation;
    private String contactPhoneNumber;
    private String contactEmailAddress;

    public EmergencyContact() {}

    public EmergencyContact(String contactName, String contactRelation, String contactPhoneNumber) {
        this.contactName = contactName;
        this.contactRelation = contactRelation;
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public EmergencyContact(String contactName, String contactRelation, String contactPhoneNumber, String contactEmailAddress) {
        this.contactName = contactName;
        this.contactRelation = contactRelation;
        this.contactPhoneNumber = contactPhoneNumber;
        this.contactEmailAddress = contactEmailAddress;
    }

    @XmlElement
    public String getContactName() {
        return contactName;
    }

    @XmlElement
    public String getContactRelation() {
        return contactRelation;
    }

    @XmlElement
    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    @XmlElement
    public String getContactEmailAddress() {
        return contactEmailAddress;
    }

    public void setContactEmailAddress(String contactEmailAddress) {
        this.contactEmailAddress = contactEmailAddress;
    }

    private void setContactName(String contactName) {
        this.contactName = contactName;
    }

    private void setContactRelationship(String contactRelation) {
        this.contactRelation = contactRelation;
    }

    private void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }


    public String toString() {
        String contact = "";
        if (!(contactEmailAddress.isEmpty())) {
            contact += contactEmailAddress + " ";
        }

        contact += contactPhoneNumber;

        return String.format("contactName: %s contactRelation: %s  contact info: %s", contactName, contactRelation, contact);
    }
}