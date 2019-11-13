package com.uvbrowser.browsermini.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.uvbrowser.browsermini.R;
import com.uvbrowser.browsermini.utils.PreferenceHelperUv;
import com.uvbrowser.browsermini.utils.ThemeUtilsUv;

/**
 * Copyright (c) 2016 Vlad Todosin
 */
public class ActivitySettingUv extends ActionBarActivity {
    private MenuItem itm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        ThemeUtilsUv theme = new ThemeUtilsUv(this);
        theme.setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference);
        Toolbar bar = (Toolbar)findViewById(R.id.settingsbar);
        setSupportActionBar(bar);
        setTitle(getResources().getString(R.string.action_settings));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getFragmentManager().beginTransaction().replace(R.id.layout, new SettingsFragmentUv()).commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return false;
    }
    @Override
    public void onBackPressed() {
        if(PreferenceHelperUv.getIsBrowser() || PreferenceHelperUv.getIsLook()){
            setTitle(getResources().getString(R.string.action_settings));
            getFragmentManager().beginTransaction().replace(R.id.layout, new SettingsFragmentUv()).commit();
            PreferenceHelperUv.setIsBrowserScreen(false);
            PreferenceHelperUv.setIsLookScreen(false);
        }
        else{
            Intent in = new Intent(this, ActivityMainUv.class);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        }
    }
}
