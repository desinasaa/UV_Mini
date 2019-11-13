/*
 Copyright 2016 Vlad Todosin
*/
package com.uvbrowser.browsermini.utils;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;

import com.uvbrowser.browsermini.R;


public class ThemeUtilsUv {
    private static ActionBarActivity THEME_ACTIVITY;
    public ThemeUtilsUv(ActionBarActivity activity){
        THEME_ACTIVITY = activity;
    }
    private static boolean isBlackTheme = false;
    public  void setTheme() {
        PreferenceUtilsUv utils = new PreferenceUtilsUv(THEME_ACTIVITY);
        switch (utils.getTheme()) {
            case 1:
                THEME_ACTIVITY.setTheme(R.style.WhiteTheme);
                isBlackTheme = false;
                break;
            case 2:
                THEME_ACTIVITY.setTheme(R.style.GreyTheme);
                isBlackTheme = false;
                break;
            case 3:
                THEME_ACTIVITY.setTheme(R.style.RedTheme);
                isBlackTheme = false;
                break;
            case 4:
                THEME_ACTIVITY.setTheme(R.style.BlueTheme);
                isBlackTheme = false;
                break;
            case 5:
                THEME_ACTIVITY.setTheme(R.style.GreenTheme);
                isBlackTheme = false;
                break;
            case 6:
                THEME_ACTIVITY.setTheme(R.style.BlackTheme);
                isBlackTheme = true;
                break;
            case 7:
                THEME_ACTIVITY.setTheme(R.style.IndigoTheme);
                isBlackTheme = false;
                break;
            case 8:
                THEME_ACTIVITY.setTheme(R.style.CyanTheme);
                isBlackTheme = false;
                break;
            case 9:
                THEME_ACTIVITY.setTheme(R.style.OrangeTheme);
                isBlackTheme = false;
                break;
            case 10:
                THEME_ACTIVITY.setTheme(R.style.LimeTheme);
                isBlackTheme = false;
                break;
        }
    }
    public static boolean isBlack(){
        return  isBlackTheme;
    }
    public   void setIncognitoTheme(){
        THEME_ACTIVITY.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2F4F4F")));
    }

}
