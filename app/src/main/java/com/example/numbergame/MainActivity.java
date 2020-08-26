package com.example.numbergame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button go;
    LinearLayout ll;
    GridLayout gl;
    Button[] options;
    TextView resultTV;
    TextView timerTV;
    TextView mathsTV;
    TextView scoreTV;
    Random rand;
    int a,b;
    int nQues = 0, correct = 0;
    String mathsTVPrint;
    int ansButton;
    Bundle bun;
    Button playAgainB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bun = savedInstanceState;
        setContentView(R.layout.activity_main);
        //Variables Allocation
        nQues = 0;
        correct = 0;
        go = findViewById(R.id.startB);
        ll = findViewById(R.id.linearLayout1);
        gl = findViewById(R.id.gridLayout);
        options = new Button[4];
        options[0] = findViewById(R.id.ansB1);
        options[1] = findViewById(R.id.ansB2);
        options[2] = findViewById(R.id.ansB3);
        options[3] = findViewById(R.id.ansB4);
        playAgainB = findViewById(R.id.playAgainB);
        resultTV = findViewById(R.id.resultTV);
        timerTV = findViewById(R.id.timerTV);
        mathsTV = findViewById(R.id.mathsTV);
        scoreTV = findViewById(R.id.scoreTV);
        rand = new Random();
        //Changing the visibility of controls on app start
        ll.setVisibility(View.INVISIBLE);
        go.setVisibility(View.VISIBLE);
        gl.setVisibility(View.INVISIBLE);
        resultTV.setVisibility(View.INVISIBLE);
        playAgainB.setVisibility(View.INVISIBLE);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goClick(v);
                timer();
            }
        });

    }
    //Play Again Button
    public void playAgain(View v){
        nQues = 0;
        correct = 0;
        ll.setVisibility(View.INVISIBLE);
        go.setVisibility(View.VISIBLE);
        gl.setVisibility(View.INVISIBLE);
        resultTV.setVisibility(View.INVISIBLE);
        playAgainB.setVisibility(View.INVISIBLE);

    }
    //go button on click event code
    public void goClick(View view)
    {
        ll.setVisibility(View.VISIBLE);
        go.setVisibility(View.INVISIBLE);
        gl.setVisibility(View.VISIBLE);
        a = rand.nextInt(21);
        b = rand.nextInt(21);
        mathsTVPrint = a + " + " + b;
        mathsTV.setText(mathsTVPrint);
        ansButton = rand.nextInt(4);
        String result = "" + correct + "/" + nQues;
        scoreTV.setText(result);
        for(int i = 0; i<4; i++)
        {
            String ans = Integer.toString((a+b));
            String ran;
            if (i != ansButton) {
                ran = Integer.toString(rand.nextInt(41));
                if(ran.equals(ans))
                {
                    i--;
                }
                else
                {
                    options[i].setText(ran);
                }
            }
            else {
                options[i].setText(ans);
            }
        }
    }
    public void check(View view)
    {
        gl.setEnabled(false);
        nQues++;
        Button chkButton = (Button) view;
        String cor = "Incorrect";
        if(options[ansButton] == chkButton)
        {
            correct++;
            cor = "Correct";
        }
        resultTV.setText(cor);
        goClick(view);
        gl.setEnabled(true);
    }
    public void timer(){
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                millisUntilFinished /= 1000;
                String time = Long.toString(millisUntilFinished) + 's';
                if(time.length()<3)
                {
                    time = '0' + time;
                }
                timerTV.setText(time);
            }

            @Override
            public void onFinish() {
                float acc = (float) (correct)/(float) nQues;
                acc *= 100;
                ll.setEnabled(false);
                gl.setEnabled(false);
                @SuppressLint("DefaultLocale") String str = String.format("%.2f", acc);
                if(correct == 0 || nQues == 0)
                {
                    str = "0";
                }
                String result = "Your Percent:"+ str + "%";
                resultTV.setText(result);
                resultTV.setVisibility(View.VISIBLE);
                playAgainB.setVisibility(View.VISIBLE);
            }
        }.start();
    }
}