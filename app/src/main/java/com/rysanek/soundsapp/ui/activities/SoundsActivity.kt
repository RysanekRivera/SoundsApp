package com.rysanek.soundsapp.ui.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.rysanek.soundsapp.R
import com.rysanek.soundsapp.databinding.ActivitySoundsBinding
import com.rysanek.soundsapp.utils.setUpSystemWindow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SoundsActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySoundsBinding
    private lateinit var navController: NavController
    
    override fun onCreate(savedInstanceState: Bundle?) {
        setUpSystemWindow()
        super.onCreate(savedInstanceState)
        binding = ActivitySoundsBinding.inflate(layoutInflater)
        checkPermissions()
        setContentView(binding.root)
        
        navController = findNavController(R.id.navHostFragment)
        
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.miPlay -> navController.navigate(R.id.homeFragment)
                R.id.miRecord -> navController.navigate(R.id.recordingFragment)
                R.id.miSaved -> navController.navigate(R.id.savedFragment)
            }
            true
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



