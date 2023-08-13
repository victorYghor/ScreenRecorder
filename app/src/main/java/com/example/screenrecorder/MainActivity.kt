package com.example.screenrecorder

import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE = 1000
    private val REQUEST_PERMISSION = 1001
    // this things already exist
//    private lateinit var mediaProjection: MediaProjectionManager
//    private var mediaProjection: MediaProjection? = null
//    private lateinit var mediaProjectionCallBack: MediaProjectionCallBack
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mediaProjectionManager = getSystemService(MediaProjectionManager::class.java)
        var mediaProjection : MediaProjection

        val startMediaProjection = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                mediaProjection = mediaProjectionManager
                    .getMediaProjection(result.resultCode, result.data!!)
            }
        }

        startMediaProjection.launch(mediaProjectionManager.createScreenCaptureIntent())
    }
}