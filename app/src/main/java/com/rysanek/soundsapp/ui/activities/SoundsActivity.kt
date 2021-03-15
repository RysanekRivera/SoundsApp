package com.rysanek.soundsapp.ui.activities

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.rysanek.soundsapp.R
import com.rysanek.soundsapp.ui.fragments.HomeFragment
import com.rysanek.soundsapp.ui.fragments.RecordingFragment
import com.rysanek.soundsapp.ui.fragments.SavedFragment
import com.rysanek.soundsapp.utils.setUpSystemWindow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.sounds_nav_host_fragment.*

@AndroidEntryPoint
class SoundsActivity: AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        setUpSystemWindow()
        super.onCreate(savedInstanceState)
        checkPermissions()
        setContentView(R.layout.sounds_nav_host_fragment)
        setCurrentFragment(HomeFragment())
        
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.miPlay -> setCurrentFragment(HomeFragment())
                R.id.miRecord -> setCurrentFragment(RecordingFragment())
                R.id.miSaved -> setCurrentFragment(SavedFragment())
            }
            true
        }
    }
    
    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }
    
    private fun checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 100)
            }
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    100
                )
            }
        }
    }
    
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //NO-OP
        } else {
            requestPermissions(permissions, requestCode)
        }
    }
}



