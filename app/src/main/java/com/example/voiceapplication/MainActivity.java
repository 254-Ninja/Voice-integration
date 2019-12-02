package com.example.voiceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

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
        btnPlay = (ImageButton) findViewById(R.id.mainp);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRadio();
            }
        });
        btnPlay.setImageResource(android.R.drawable.ic_media_play);

        seekBarStuff();
    }
    private void seekBarStuff() {
        bar = (SeekBar) findViewById(R.id.seekBar);
        bar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        bar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //Pressing play button
    public void startRadio() {
        if (CheckNetwork.isNetwrokAvailable(context)) {
            dialog.show();
            if (!isPlaying) {
                btnPlay.setImageResource(android.R.drawable.ic_media_pause);
                playMusic();
            } else {
                stopRadio();
            }
        } else {
            if (isPlaying) {
                stopRadio();
            }
            Toast.makeText(context, context.getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
            return;
        }
    }


}
