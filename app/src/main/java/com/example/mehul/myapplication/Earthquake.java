package com.example.mehul.myapplication;

/**
 * Created by Mehul on 12-05-2018.
 */

public class Earthquake
{
    private String mMagnitude;
    private String mLocatiion;
    private String mDate;
    public Earthquake(String magnitude, String location, String date)
    {
        mMagnitude=magnitude;
        mLocatiion=location;
        mDate=date;
    }
    public String getmagnitude()
    {
        return mMagnitude;
    }
    public String getlocation()
    {
        return mLocatiion;
    }
    public String getdate()
    {
        return mDate;
    }
}
