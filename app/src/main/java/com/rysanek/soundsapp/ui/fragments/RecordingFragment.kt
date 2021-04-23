package com.rysanek.soundsapp.ui.fragments

import android.content.ContentResolver
import android.content.ContentValues
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.os.SystemClock
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rysanek.soundsapp.R
import com.rysanek.soundsapp.db.entities.Recording
import com.rysanek.soundsapp.databinding.FragmentRecordingBinding
import com.rysanek.soundsapp.utils.showSnackBar
import com.rysanek.soundsapp.viewmodels.SoundsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject
import kotlin.Exception

@AndroidEntryPoint
class RecordingFragment: Fragment() {
    
    private var _binding: FragmentRecordingBinding? = null
    private val binding get() = _binding!!
    private val soundsViewModel: SoundsViewModel by viewModels()
    
    private lateinit var fileName: String
    private var audioUri: Uri? = null
    private var file: ParcelFileDescriptor? = null
    private var initialTime: Long? = null
    private var duration: Long? = null
    private var mediaRecorder: MediaRecorder? = null
    
    @Inject
    lateinit var mediaPlayer: MediaPlayer
    
    @Inject
    lateinit var values: ContentValues
    
    @Inject
    lateinit var contentResolver: ContentResolver
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("onCreate", "mediaRecorder status ${mediaRecorder.toString()}")
        _binding = FragmentRecordingBinding.inflate(layoutInflater)
        
        binding.btRecord.setOnClickListener {
            startRecording()
            showSnackBar("Recording Started")
        }
        
        binding.btStop.setOnClickListener {
            if (mediaRecorder != null){
                try {
                    duration = System.currentTimeMillis() - initialTime!!
                    Log.d("btStop", "media recorderStatus: $mediaRecorder")
                    mediaRecorder?.stop()
                    mediaRecorder?.reset()
                    mediaRecorder?.release()
                    mediaRecorder = null
                    
                    val recording = Recording(fileName, duration!!, audioUri.toString())
                    soundsViewModel.insertToDatabase(recording)
                    Log.d("btStop", "media recorderStatus: $mediaRecorder")
                } catch (e: IllegalStateException) {
                    Log.d("btStop", "IllegalStateException: ${e.message}")
                } catch (e: Exception) {
                    Log.d("btStop", "exception: ${e.message}")
                }
    
                Log.d("stopRecording", "Recording stopped, mediaRecorder: ${mediaRecorder.toString()}")
                showSnackBar("Recording Stopped")
            }
        }
        
        binding.btPlay.setOnClickListener {
            mediaPlayer.apply {
                setDataSource("")
                prepare()
                start()
            }
        }
        
        return binding.root
    }
    
    @Throws(IOException::class)
    private fun startRecording() {
        try {
            initialTime = System.currentTimeMillis()
            fileName = "sounds_rec_${date()}"
            Log.d("startRecording", "filename: $fileName")
            values.put(MediaStore.Audio.Media.TITLE, fileName)
            values.put(MediaStore.Audio.Media.DATE_ADDED, date())
            values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/mp4")
            values.put(MediaStore.Audio.Media.DISPLAY_NAME, fileName)
            Log.d("Title", values[MediaStore.Audio.Media.TITLE].toString())
            Log.d("Date", values[MediaStore.Audio.Media.DATE_ADDED].toString())
            Log.d("Mime", values[MediaStore.Audio.Media.MIME_TYPE].toString())
            Log.d("DisplayName", values[MediaStore.Audio.Media.DISPLAY_NAME].toString())
            
            audioUri = contentResolver.insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values)
            file = contentResolver.openFileDescriptor(audioUri!!, "w")
            
            file?.let {
                Log.d("startRecording", "File is not null")
                setupAndStartMediaRecorder(it)
            }
        } catch (e: IllegalArgumentException) {
            Log.d("startRecording", e.message.toString())
        } catch (e:Exception) {
            Log.d("startRecording", e.message.toString())
        }
    }
    
    private fun date(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("MMddyyyyHHmmss")
            val formattedDate: String = current.format(formatter)
//            Log.d("formattedDate", formattedDate)
            formattedDate
        } else {
            val date = Date()
            val formatter = SimpleDateFormat("MMMddyyyyHHmma", Locale.getDefault())
            val formattedDate: String = formatter.format(date)
//            Log.d("formattedDate", formattedDate)
            formattedDate
        }
    }
    
    private fun setupAndStartMediaRecorder(file: ParcelFileDescriptor) {
        
        Log.d("setupRecorder", "before: mediaRecorder: ${mediaRecorder.toString()}")
        
        mediaRecorder.let {
            if (it == null){
                mediaRecorder = MediaRecorder().apply {
                    setAudioSource(MediaRecorder.AudioSource.MIC)
                    setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                    setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                    setOutputFile(file.fileDescriptor)
                    setAudioChannels(1)
                    prepare()
                    start()
                }
                Log.d("setupRecorder", "after: mediaRecorder: ${mediaRecorder.toString()}")
            }
        }
    }
    
    override fun onPause() {
        super.onPause()
        Log.d("onPause", "MediaRecorder status: ${mediaRecorder.toString()}")
        mediaRecorder?.release()
        Log.d("onPause", "MediaRecorder status: ${mediaRecorder.toString()}")
    }
    
    override fun onStop() {
        super.onStop()
        Log.d("onStop", "MediaRecorder status: ${mediaRecorder.toString()}")
        mediaRecorder?.release()
        mediaRecorder = null
        Log.d("onStop", "MediaRecorder status: ${mediaRecorder.toString()}")
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
