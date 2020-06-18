package com.trust.assesshealth.Model;

public class User
{
    public User()
    {}
    public User(User u)
    {
        this.userID = u.getUserID();
        this.email = u.getEmail();
        this.name = u.getName();
        this.surname = u.getSurname();
        this.org = u.getOrg();
        this.password =u.getPassword();
    }
    public User(String userName,String userPass)
    {
        email = userName;
        password = userPass;
    }
    public User(String N,String SN,String E,String P,String O,String id)
    {
        name = N;
        surname = SN;
        email = E;
        password = P;
        org = O;
        userID = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    private String name,surname,email,password,org,userID;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }
}
