package com.example.manng.csc_25_07_navigationdrawerlayout2.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manng.csc_25_07_navigationdrawerlayout2.R;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingActivity extends AppCompatActivity {
    ProgressBar progressBar;
    int status;
    ImageView imageView;
    TextView textViewLoading, textViewLoadingNumber;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imageView = (ImageView) findViewById(R.id.imgOneClick);
        textViewLoading = (TextView) findViewById(R.id.textLoading);
        textViewLoadingNumber = (TextView) findViewById(R.id.textLoadingNumber);

        // alpha animation event
        Animation alpha = AnimationUtils.loadAnimation(LoadingActivity.this,R.anim.alpha_animation);
        imageView.startAnimation(alpha);
        textViewLoading.startAnimation(alpha);


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }
        }, 0, 1000);



        new ParseProgressBar().execute();
    }
    // loading event
    class ParseProgressBar extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            status = 0;
            progressBar.setProgress(status);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            while (status < 100) {
                status += 1;
                publishProgress(status);
                SystemClock.sleep(10);
                if (status == 100) {
                    Intent intent = new Intent(LoadingActivity.this, WellComeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            progressBar.setProgress(values[0]);
            textViewLoadingNumber.setText(values[0]+"%");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            Toast.makeText(LoadingActivity.this, "Completed!!!", Toast.LENGTH_SHORT).show();
        }
    }

}
