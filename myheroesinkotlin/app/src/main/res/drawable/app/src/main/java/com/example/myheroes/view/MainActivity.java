package com.example.myheroes.view;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myheroes.R;

public class MainActivity extends AppCompatActivity {

    Button button;
    VideoView videoView;
    MediaPlayer mediaPlayer;
    int currentVidPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        videoView = (VideoView) findViewById( R.id.videoView );
        button = findViewById( R.id.enter );

        videoView.setVideoURI( Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.spiderman_vid) );
        videoView.start();


        videoView.setOnPreparedListener( new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer = mp;
                mediaPlayer.setLooping( true );
                if (currentVidPos != 0){
                    mediaPlayer.seekTo( currentVidPos );
                    mediaPlayer.start();
                }
            }
        } );

        final Intent myIntent = new Intent(MainActivity.this, SecondActivity.class);
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity( myIntent );
                }
        } );


    }

    @Override
    protected void onPause() {
        super.onPause();

        currentVidPos = mediaPlayer.getCurrentPosition();
        videoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
