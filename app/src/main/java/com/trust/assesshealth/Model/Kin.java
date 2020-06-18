package com.trust.assesshealth.Model;

public class Kin
{
    private String name;
    private String surname;
    private String address;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String phoneNumber;
    private String ID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public Kin()
    {}
    public Kin(String n, String sn, String addss,String KinId,String phone)
    {
        name = n;
        surname = sn;
        address = addss;
        ID = KinId;
        phoneNumber = phone;
    }

}
