package com.rysanek.soundsapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rysanek.soundsapp.R
import com.rysanek.soundsapp.ui.fragments.HomeFragment
import com.rysanek.soundsapp.ui.fragments.RecordingFragment
import com.rysanek.soundsapp.ui.fragments.SavedFragment
import kotlinx.android.synthetic.main.sounds_nav_host_fragment.*

class SoundsActivity: AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sounds_nav_host_fragment)
        
        setCurrentFragment(HomeFragment())
        
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.miPlay -> setCurrentFragment(HomeFragment())
                R.id.miRecord -> setCurrentFragment(RecordingFragment())
                R.id.miSaved -> setCurrentFragment(SavedFragment())
            }
            true
        }
    }
    
    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }
    
    
    
    
}

