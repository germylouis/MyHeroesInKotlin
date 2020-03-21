package com.example.myheroesinkotlin.view

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.myheroesinkotlin.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var button : Button
    lateinit var videoView : VideoView
    lateinit var mediaPlayer: MediaPlayer
     var currentVidPos : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoView = findViewById(R.id.videoView)
        button = findViewById(R.id.enter)

        videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.spiderman_vid))
        videoView.start()

        videoView.setOnPreparedListener { mp ->
            mediaPlayer = mp
            mediaPlayer.isLooping = true
            if (currentVidPos.equals(0)) {
                if (!currentVidPos.equals(0)) {
                    mediaPlayer.seekTo(currentVidPos)
                    mediaPlayer.start()
                }
                mediaPlayer.start()
            }
        }

        var mIntent = Intent(this, SecondActivity::class.java)
        button.setOnClickListener { startActivity(mIntent) }


    }

    override fun onPause() {
        super.onPause()

        currentVidPos = mediaPlayer.currentPosition
        videoView.pause()
    }

    override fun onResume() {
        super.onResume()
        videoView.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        mediaPlayer ?: null
    }
}
