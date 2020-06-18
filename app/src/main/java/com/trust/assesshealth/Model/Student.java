package com.trust.assesshealth.Model;



public class Student
{
    private String name,surname,gender;
    private String studentNum;



    public Kin getNxtKin()
    {
        return nxtKin;
    }

    public void setNxtKin(Kin nxtKin)
    {
        this.nxtKin = nxtKin;
    }

    private Kin nxtKin;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public Student(String N, String SN, String G, String StdNum,Kin nKin)
    {
        name = N;
        surname = SN;
        gender = G;
        studentNum = StdNum;
        nxtKin = nKin;
    }
    public  Student(String N, String SN, String G, String StdNum)
    {
        name = N;
        surname = SN;
        gender = G;
        studentNum = StdNum;
        nxtKin = null;
    }
    public Student( String N , String StudentNumber)
    {
        name = N;
        studentNum = StudentNumber;
    }

    public Student ()
    { }
}
