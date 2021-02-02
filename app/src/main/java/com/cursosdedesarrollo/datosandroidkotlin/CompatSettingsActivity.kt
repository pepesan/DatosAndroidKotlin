package com.cursosdedesarrollo.datosandroidkotlin

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import java.net.MalformedURLException

class CompatSettingsActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener  {
    class MySettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences_jetpack, rootKey)
        }
    }

    var preferencesChanged = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compat_settings)
        PreferenceManager.getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this);
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings_fragment, MySettingsFragment())
                .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        PreferenceManager.getDefaultSharedPreferences(this)
            .unregisterOnSharedPreferenceChangeListener(this);
    }

    override fun onSharedPreferenceChanged(sharedPreference: SharedPreferences?, key: String?) {
        Log.d("app","Cambio en preferencias")
        //sólo si es de tipo String
        Log.d("App","Preference: "+sharedPreference?.getString(key,"").toString())
    }
}