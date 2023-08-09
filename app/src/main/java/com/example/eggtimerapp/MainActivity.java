package com.example.eggtimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    Button button;
    TextView textView;
    boolean istimerActive=false;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=findViewById(R.id.seekBar);
        button=findViewById(R.id.button);
        textView=findViewById(R.id.textView);

        seekBar.setMax(600);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(istimerActive)
                {
                  reset();

                   istimerActive=false;
                }
                else {
                    istimerActive = true;
                    button.setText("Stop!");
                    seekBar.setEnabled(false);


                    countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                            updatetimer((int) millisUntilFinished / 1000);

                        }

                        @Override
                        public void onFinish() {

                            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound);
                            mediaPlayer.start();

                            reset();

                        }
                    }.start();
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updatetimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    public void updatetimer(int secondsleft)
    {
        int minutes = secondsleft/60;
        int seconds = secondsleft-(minutes*60);

        String secInstring =String.valueOf(seconds);
       String minInstring =String.valueOf(minutes);

        if(seconds <= 9)
        {
            secInstring="0"+seconds;
        }
//        else if(minutes <=9)
//        {
//            minInstring="0"+minutes;
//        }

        textView.setText(minutes+":"+ secInstring);


    }
    public void reset()
    {
        seekBar.setEnabled(true);
        button.setText("Start");
        countDownTimer.cancel();
        seekBar.setProgress(00);
        textView.setText("00:00");


    }
}