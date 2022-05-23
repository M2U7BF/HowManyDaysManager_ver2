package com.example.howmanydaysmanagerver2;

import android.app.Application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Common extends Application {
    public List<String> titleList = new ArrayList<>();
    public List<String> DateList = new ArrayList<>();
    private Calendar cal_today = Calendar.getInstance();
    public SimpleDateFormat sdf = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss:SSS", Locale.JAPAN);
    public SimpleDateFormat sdf2 = new SimpleDateFormat( "yyyy/MM/dd", Locale.JAPAN);
    public String today = sdf2.format(cal_today.getTime());
    public String today_for_Activity_SetData = sdf2.format(cal_today.getTime()) + " 00:00:00:000";;
    public int elem_num = 0;

    @Override
    public void onCreate(){
        super.onCreate();
    }
}
