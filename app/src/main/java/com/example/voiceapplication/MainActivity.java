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

    private void init() {
        STREAM_URL = context.getResources().getString(R.string.stream_url);

        TextView desc = (TextView) findViewById(R.id.textView);
        desc.setText(Html.fromHtml(getString(R.string.by_ercan_duman)));

        dialog = new ProgressDialog(context);
        dialog.setMessage(context.getString(R.string.loading));
        dialog.setCancelable(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        player = new MediaPlayer();
        audioManager = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);
        btnPlay = (ImageButton) findViewById(R.id.btnPlayPause);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRadio();
            }
        });
        btnPlay.setImageResource(android.R.drawable.ic_media_play);

        seekBarStuff();
    }

}
