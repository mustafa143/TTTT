package com.example.mustafa.timer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button startBtn;
    TextView timerTextView;
    SeekBar seekBar;
    Boolean isActive = true;
    CountDownTimer countDownTimer;
    public void resetTimer(){
        startBtn.setText("GO PLEASE!");
        countDownTimer.cancel();
        seekBar.setProgress(30);
        timerTextView.setText( "0:30");
        seekBar.setEnabled(true);
        isActive = true;
    }
    public void startTimer(View view){
        if(isActive){
            isActive = false;
            seekBar.setEnabled(false);
            startBtn.setText("STOP ONCE YESS!");
            countDownTimer = new CountDownTimer(seekBar.getProgress()*1000 + 10, 1000){

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int)millisUntilFinished/1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.rooster_rumble);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }else{
            resetTimer();
        }

    }

    public void updateTimer(int secondLeft){
        int minutes = secondLeft / 60;
        int seconds = secondLeft - (minutes*60);

        String secondString = Integer.toString(seconds);
        if(seconds <= 9){
            secondString = "0"+ secondString;
        }

        timerTextView.setText(Integer.toString(minutes)+ ":" + secondString);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.startButton);
        timerTextView = findViewById(R.id.timerTextView);
        seekBar = findViewById(R.id.seekBar);

        seekBar.setMax(600);
        seekBar.setProgress(30);
        timerTextView.setText( "0:30");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
