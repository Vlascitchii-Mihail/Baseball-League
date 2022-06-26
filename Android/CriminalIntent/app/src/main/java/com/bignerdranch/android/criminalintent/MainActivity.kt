package com.bignerdranch.android.criminalintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.UUID
import android.util.Log

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), CrimeListFragment.Callbacks {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_crime)

            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

            if (currentFragment == null) {
//            val fragment = CrimeFragment()
                val fragment = CrimeListFragment.newInstance()
                supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment)
                    .commit()
            }
        }

        override fun onCrimeSelected(crimeId: UUID) {
//            Log.d(TAG, "MainActivity.onCrimeSelected: $crimeId")
            val fragment = CrimeFragment.newInstance(crimeId)
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit()
        }
}