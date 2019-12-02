package com.example.voiceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements AudioManager.OnAudioFocusChangeListener {

    private ProgressDialog dialog;
    private static Context context;
    private static String STREAM_URL;
    private ImageButton btnPlay;
    private MediaPlayer player;
    private SeekBar bar;
    private AudioManager audioManager;
    private static boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        init();
    }
}
