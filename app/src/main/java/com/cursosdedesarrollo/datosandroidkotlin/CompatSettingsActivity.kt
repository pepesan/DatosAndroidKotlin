package com.cursosdedesarrollo.datosandroidkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class CompatSettingsActivity : AppCompatActivity() {
    class MySettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences_jetpack, rootKey)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compat_settings)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings_fragment, MySettingsFragment())
                .commit()
    }
}