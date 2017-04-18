package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

/**
 * Created by massao on 15.04.17.
 */

public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_settings);

        PreferenceScreen prefScreen = getPreferenceScreen();
        SharedPreferences sharedPref = prefScreen.getSharedPreferences();
        int size = prefScreen.getPreferenceCount();
        for(int i = 0; i < size; i++) {
            Preference p = prefScreen.getPreference(i);
            if(!(p instanceof CheckBoxPreference)) {
                String value = sharedPref.getString(p.getKey(), "");
                setPreferenceSummary(p, value);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        PreferenceScreen prefScreen = getPreferenceScreen();
        Preference p = prefScreen.findPreference(key);
        String value = sharedPreferences.getString(key, "");
        if(!(p instanceof CheckBoxPreference)) {
            setPreferenceSummary(p, value);
        }
    }

    private void setPreferenceSummary(Preference p, String v) {
        if(p instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) p;
            int index = listPreference.findIndexOfValue(v);
            if(index >= 0) {
                listPreference.setSummary(listPreference.getEntries()[index]);
            }
        } else if(p instanceof EditTextPreference) {
            p.setSummary(v);
        }
    }
}
