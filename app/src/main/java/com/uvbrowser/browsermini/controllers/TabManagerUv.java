/*
   Copyright 2016 Vlad Todosin
*/

package com.uvbrowser.browsermini.controllers;



import android.content.Context;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.uvbrowser.browsermini.view.HeView;


import android.webkit.CookieManager;
import java.util.ArrayList;
import java.util.List;

public class TabManagerUv {
   private static List<HeView> mViewsList = new ArrayList<HeView>();
   private static PreferenceManager MANAGER;
   static HeView currentTab;
   private static NavigationView VIEW;
   public static void addTab(HeView view){
       mViewsList.add(view);
   }
   @Nullable
    public static List<HeView> getList(){
        return mViewsList;
    }

    public static void removeTab(HeView view){
        int index = mViewsList.indexOf(view);
        if(index != 0){
          mViewsList.remove(view);
        }
        else {
            HeView behe = mViewsList.get(index + 1);
            mViewsList.set(0,behe);
            mViewsList.remove(index + 1);
            mViewsList.remove(view);
            setCurrentTab(behe);
        }
        view.destroy();
    }
    public static HeView getCurrentTab(){
        if(currentTab != null) {
            return currentTab;
        }
        else{
            return mViewsList.get(0);
        }
    }
    public static void setNavigationView(NavigationView view){
        VIEW = view;
    }
    public static void setCookie(boolean cookie){
        for (HeView view : mViewsList){
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                CookieManager.getInstance().setAcceptThirdPartyCookies(view,cookie);
            }
        }
    }
    public static void updateTabView(){
        VIEW.getMenu().clear();
        for(int i = 0;i < mViewsList.size();i++) {
            HeView view = mViewsList.get(i);
            VIEW.getMenu().add(view.getTitle());
            if(view == TabManagerUv.currentTab){
                VIEW.getMenu().getItem(i).setChecked(true);
            }
            else{
                VIEW.getMenu().getItem(i).setChecked(false);
            }
        }
        for(int i = 0;i< VIEW.getMenu().size();i++){
            ColorGenerator gen = ColorGenerator.MATERIAL;
            int col = gen.getRandomColor();
            TextDrawable drawable = TextDrawable.builder().buildRound(String.valueOf(i + 1),col);
            VIEW.getMenu().getItem(i).setIcon(drawable);
        }
    }
    public static void setCurrentTab(HeView view){
        for(HeView behe : getList()){
            behe.setIsCurrentTab(false);
        }
        view.setIsCurrentTab(true);
        currentTab = view;
    }
    public static HeView getTabByTitle(String title){
        for(HeView view : getList()){
            String web = view.getTitle();
            if(web.matches(title)){
                return view;
            }
            else{
                return null;
            }
        }
        return null;
    }
    public static HeView getTabAtPosition(MenuItem menuItem){
        List<MenuItem> items = new ArrayList<>();
        Menu menu = VIEW.getMenu();
        for(int i = 0; i < menu.size();i++){
            MenuItem item = menu.getItem(i);
            items.add(item);
        }
        int index = items.indexOf(menuItem);
        HeView view = getList().get(index);
        return view;
    }
    public static void removeAllTabs(){
       mViewsList.clear();
    }
    public static void resetAll(ActionBarActivity act, ProgressBar pBar, boolean pvt, EditText txt){
          for(HeView view : mViewsList){
              view.setNewParams(txt,pBar,act,pvt);
          }
    }
    public static void stopPlayback(){
        for(HeView view : mViewsList){
            view.onPause();
        }
    }
   public static void resume(){
       for(HeView view : mViewsList){
           view.onResume();
       }
   }
    public static String getSearchEngine(Context cnt) {
        String searchEngine;
        searchEngine = MANAGER.getDefaultSharedPreferences(cnt).getString("search","1");
        int e = Integer.parseInt(searchEngine);
        switch (e) {
            case 1:
                String google = "https://www.google.com/search?q=";
               return google;
            case 2:
                String bing = "http://www.bing.com/search?q=";
               return bing;
            case 3:
                String yahoo = "https://search.yahoo.com/search?p=";
               return yahoo;

            case 4:
                String duck = "https://duckduckgo.com/?q=";
               return duck;

            case 5:
                String ask = "http://www.ask.com/web?q=";
                return ask;

            case 6:
                String wow = "http://www.wow.com/search?s_it=search-thp&v_t=na&q=";
                return wow;

            case 7:
                String aol = "https://search.aol.com/aol/search?s_chn=prt_ticker-test-g&q=";
                return aol;

            case 8:
                String crawler = "https://www.webcrawler.com/serp?q=";
                return crawler;

            case 9:
                String myweb = "http://int.search.mywebsearch.com/mywebsearch/GGmain.jhtml?searchfor=";
               return myweb;

            case 10:
                String info = "http://search.infospace.com/search/web?q=";
               return  info;

            case 11:
                String yandex = "https://www.yandex.com/search/?text=";
               return yandex;

            case 12:
                String startpage = "https://www.startpage.com/do/search?q=";
                return startpage;

            case 13:
                String searx = "https://searx.me/?q=";
               return searx;

            default:
                String goole = "https://www.google.com/search?q=";
                return goole;

        }
    }
    public static void deleteAllHistory(){
        for(HeView view : mViewsList){
            view.clearHistory();
        }
    }
}

