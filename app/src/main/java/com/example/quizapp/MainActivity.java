package com.example.quizapp;

import static android.graphics.Color.MAGENTA;
import static android.graphics.Color.WHITE;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView NbrofQuestions;
    TextView Question;
    Button A,B,C,D;
    Button Submit;
    int score = 0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NbrofQuestions = findViewById(R.id.total_question);
        Question = findViewById(R.id.question);
        A = findViewById(R.id.ans_A);
        B = findViewById(R.id.ans_B);
        C = findViewById(R.id.ans_C);
        D = findViewById(R.id.ans_D);
        Submit = findViewById(R.id.Submit);
        A.setOnClickListener(this);
        B.setOnClickListener(this);
        C.setOnClickListener(this);
        D.setOnClickListener(this);
        Submit.setOnClickListener(this);

        NbrofQuestions.setText("Antal frågor: " + totalQuestion);
        loadNewQuestion();

    }

    @Override
    public void onClick(View view) {
        A.setBackgroundColor(WHITE);
        B.setBackgroundColor(WHITE);
        C.setBackgroundColor(WHITE);
        D.setBackgroundColor(WHITE);

        Button clickedButton = (Button) view;

        if(clickedButton.getId() == R.id.Submit){
            if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex+=1;
            loadNewQuestion();


        }else{

            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(MAGENTA);
        }
    }
    void loadNewQuestion(){
        if(currentQuestionIndex == totalQuestion){
            finsh();
            return;
        }
        Question.setText(QuestionAnswer.question[currentQuestionIndex]);
        A.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        B.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        C.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        D.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }
    void finsh(){
        String result="";
        if(score > totalQuestion -1){
            result = "Passed";
        }else{
            result = "Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(result)
                .setMessage("Antal rätta svar:" + score)
                .setPositiveButton("Restart",(dialogInterface,i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }
    void restartQuiz(){
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
    }
}