package com.trust.assesshealth.Model;

public class Health
{
    private Student student_ID;

    public void setStudent_ID(Student student_ID) {
        this.student_ID = student_ID;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    private String health_ID;

    public String getHealth_ID()
    {
        return health_ID;
    }

    public void setHealth_ID(String health_ID) {
        this.health_ID = health_ID;
    }

    private String temperature;
    private String dryCough;
    private String tiredNess;
    private String breathShortness;
    private String Healthsummary;

    public String getHealthsummary() {
        return Healthsummary;
    }

    public void setHealthsummary(String healthsummary) {
        Healthsummary = healthsummary;
    }

    public Health(String h_ID,Student s_ID, String trmp, String dC, String T, String shortBreath, String Healthsummary)
    {
        health_ID = h_ID;
        student_ID = s_ID;
        temperature = trmp;
        dryCough = dC;
        tiredNess = T;
        breathShortness = shortBreath;
        this.Healthsummary = Healthsummary;
    }

public Health()
{ }
    public Student getStudent_ID() {
        return student_ID;
    }

    public void setCrrStu(Student crrStu) {
        this.student_ID = crrStu;
    }

    public String getDryCough() {
        return dryCough;
    }

    public void setDryCough(String dryCough) {
        this.dryCough = dryCough;
    }

    public String getTiredNess() {
        return tiredNess;
    }

    public void setTiredNess(String tiredNess) {
        this.tiredNess = tiredNess;
    }

    public String getBreathShortness() {
        return breathShortness;
    }

    public void setBreathShortness(String breathShortness) {
        this.breathShortness = breathShortness;
    }
}
