package com.example.braintimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    TextView sumTextView;
    TextView resultTextView;
    TextView pointsTextView;
    TextView timerTextView;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;
    ArrayList<Integer> answers =new ArrayList<Integer>();
    int locationOfCorrecAnswer;
    int numberofquestions =0;
    int score =0;


    public void playAgain(View view){
        score=0;
        numberofquestions=0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(view.INVISIBLE);


        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");

            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("your score:" + Integer.toString(score) +"/"+ Integer.toString(numberofquestions));

            }
        }.start();

    }

    public void generateNewQuestion(){
        //first step is to creat a random number generator
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));

        locationOfCorrecAnswer = rand.nextInt(4);
        answers.clear();
        int incorrectAnswer;

        for(int i =0; i<4;i++){
            if(i == locationOfCorrecAnswer){

                answers.add(a+b);
            }else{
                incorrectAnswer=rand.nextInt(41);

                while(incorrectAnswer == a+b){

                    incorrectAnswer=rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        button2.setText(Integer.toString(answers.get(0)));
        button3.setText(Integer.toString(answers.get(1)));
        button4.setText(Integer.toString(answers.get(2)));
        button5.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view){

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrecAnswer))){
            score++;
            resultTextView.setText("correct!");
        }else {
            resultTextView.setText("wrong");
        }
        numberofquestions++;
        pointsTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberofquestions));
        generateNewQuestion();
    }

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgainButton =(Button)findViewById(R.id.playAgainButton);
        gameRelativeLayout =(RelativeLayout)findViewById(R.id.gameRelativeLayout);
        button2 =(Button)findViewById(R.id.button2);
        button3 =(Button)findViewById(R.id.button3);
        button4 =(Button)findViewById(R.id.button4);
        button5 =(Button)findViewById(R.id.button5);



        generateNewQuestion();

        }
    }

