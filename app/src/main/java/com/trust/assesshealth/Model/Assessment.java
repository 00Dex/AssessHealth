package com.trust.assesshealth.Model;

public class Assessment
{
    private User u_ID;
    private Health h_ID;
public Assessment()
{}
    public User getU_ID()
    {
        return u_ID;
    }

    public Health getH_ID()
    {
        return h_ID;
    }

    public String getDay()
    {
        return day;
    }

    private String day;
    public Assessment(User u,Health h,String d)
    {
     u_ID = u;
     h_ID = h;
     day = d;
    }
}
