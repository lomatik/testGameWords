package com.lomatik.words;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    TextView textView;
    CountDownTimer countDownTimer;
    int countCorrectWords;
    boolean isDoneChallenge = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editTextNumber);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int seconds = Integer.valueOf(editText.getText().toString());
                countDownTimer = new CountDownTimer(seconds * 1000, 1000) {
                    @Override
                    public void onTick(long l) {
                        NumberFormat f = new DecimalFormat("00");
                        long hour = (l / 3600000) % 24;
                        long min = (l / 60000) % 60;
                        long sec = (l / 1000) % 60;
                        textView.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                        if (sec == 3 || isDoneChallenge) {
                            Toast.makeText(getApplicationContext(),"stop",Toast.LENGTH_SHORT).show();
                            cancel();
                        }
                    }

                    @Override
                    public void onFinish() {
                        textView.setText("Finished!");
                    }
                };
                countDownTimer.start();
            }
        });
    }
}