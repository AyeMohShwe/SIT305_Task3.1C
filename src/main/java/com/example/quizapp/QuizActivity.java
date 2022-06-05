package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    int current_question = 1;
    int max_questions = 5;
    int score = 0;
    int completed = 0;
    int button_pressed;
    String player_name;
    TextView welcomeText, questionTitle, questionText, quizCount;
    ProgressBar progressBar;
    Button ans1, ans2, ans3;

    ArrayList<String> QuestionTitle = new ArrayList<String>();
    ArrayList<String> QuestionText = new ArrayList<String>();
    ArrayList<String[]> Answers = new ArrayList<String[]>();
    ArrayList<int[]> AnswerCheck = new ArrayList<int[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        player_name = intent.getStringExtra("Name");

        welcomeText = findViewById(R.id.welcome_text);
        quizCount = findViewById(R.id.quiz_count);
        progressBar = findViewById(R.id.progress_bar);
        questionTitle= findViewById(R.id.q_title);
        questionText = findViewById(R.id.q_txt);
        ans1 = findViewById(R.id.newQ_btn);
        ans2 = findViewById(R.id.finish_btn);
        ans3 = findViewById(R.id.ans3);

        progressBar.setMax(max_questions);
        welcomeText.setText("Welcome " + player_name + "!");

        QuestionTitle.add("Q1: Activity Lifecycle");
        QuestionText.add("Which of the following is the first callback method that is invoked by the system during an activity life-cycle?");
        Answers.add(new String[]{"onStart()","onCreate()","onClick()"});
        AnswerCheck.add(new int[]{0,1,0});

        QuestionTitle.add("Q2: Android Basics");
        QuestionText.add("Which of the following kernel is used in Android?");
        Answers.add(new String[]{"Windows","MAC","Linux"});
        AnswerCheck.add(new int[]{0,0,1});

        QuestionTitle.add("Q3: Android Intro");
        QuestionText.add("What is Android?");
        Answers.add(new String[]{"an operating system","a web server","a web browser"});
        AnswerCheck.add(new int[]{1,0,0});

        QuestionTitle.add("Q4: Android Terms");
        QuestionText.add("What does API stand for?");
        Answers.add(new String[]{"Application Programming Interface","Android Page Interface","Application Page Interface"});
        AnswerCheck.add(new int[]{1,0,0});

        QuestionTitle.add("Q5: Controls");
        QuestionText.add("Which of the followings is a user input control in Android Development?");
        Answers.add(new String[]{"Constraints","LinearLayout","TextView"});
        AnswerCheck.add(new int[]{0,0,1});

        progressBar.setProgress(current_question - 1);
        quizCount.setText(current_question + "/" + max_questions);

        Display();

    }

    public void OnClickSubmit(View view) {

        Button b = (Button)view;
        String buttonText = b.getText().toString();

        //Toast.makeText(this, "Submit pressed" + button_pressed, Toast.LENGTH_SHORT).show();

        if (buttonText.equals("Submit")){

            if (button_pressed == 0){
                CheckAnswers(0, ans1);
            }
            else if (button_pressed == 1){
                CheckAnswers(1, ans2);
            }
            else if (button_pressed == 2){
                CheckAnswers(2, ans3);
            }

            b.setText("Next");
        }
        else{
            if (current_question + 1 > max_questions) {
                //Toast.makeText(this, "Score " + score, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ScoreActivity.class);
                intent.putExtra("Score", score);
                intent.putExtra("MaxQuestions", max_questions);
                intent.putExtra("Name", player_name);
                startActivity(intent);
                finish();
            }
            else{
                current_question += 1;
                progressBar.setProgress(current_question - 1);
                quizCount.setText(current_question + "/" + max_questions);
                b.setText("Submit");

                Display();
            }
        }
    }

    public void OnClickAnsOne(View view){
        ans1.setBackgroundColor(Color.parseColor("#FFFF99")); //yellow
        button_pressed = 0;
        //Toast.makeText(this, "Ans one pressed " + button_pressed, Toast.LENGTH_SHORT).show();

    }

    public void OnClickAnsTwo(View view){
        ans2.setBackgroundColor(Color.parseColor("#FFFF99")); //yellow
        button_pressed = 1;
        //Toast.makeText(this, "Ans 2 pressed " + button_pressed, Toast.LENGTH_SHORT).show();
    }
    public void OnClickAnsThree(View view){
        ans3.setBackgroundColor(Color.parseColor("#FFFF99")); //yellow
        button_pressed = 2;
        //Toast.makeText(this, "Ans 2 pressed " + button_pressed, Toast.LENGTH_SHORT).show();
    }

    private void Display() {
        ans1.setBackgroundColor(Color.parseColor("#D3D3D3")); //grey
        ans2.setBackgroundColor(Color.parseColor("#D3D3D3"));
        ans3.setBackgroundColor(Color.parseColor("#D3D3D3"));

        int index = current_question - 1;

        questionTitle.setText(QuestionTitle.get(index));
        questionText.setText(QuestionText.get(index));
        ans1.setText(Answers.get(index)[0]);
        ans2.setText(Answers.get(index)[1]);
        ans3.setText(Answers.get(index)[2]);
    }

    private void CheckAnswers(int choice, Button b){

        //Toast.makeText(this, "Check answers entered " + choice + b.getText(), Toast.LENGTH_SHORT).show();
        ans1.setBackgroundColor(Color.parseColor("#D3D3D3")); //grey
        ans2.setBackgroundColor(Color.parseColor("#D3D3D3"));
        ans3.setBackgroundColor(Color.parseColor("#D3D3D3"));

        if (completed == current_question) return;

        completed += 1;

        int idx = current_question - 1;
        if (AnswerCheck.get(idx)[choice] == 1){

            score += 1;
            b.setBackgroundColor(Color.parseColor("#90ee90")); //green
        }
        else {
            //Toast.makeText(this, "Choice before green " + choice, Toast.LENGTH_SHORT).show();
            b.setBackgroundColor(Color.parseColor("#FF0000")); //red
            //Toast.makeText(this, "Choice before red " + choice, Toast.LENGTH_SHORT).show();
            if (AnswerCheck.get(idx)[0] == 1)
                ans1.setBackgroundColor(Color.parseColor("#90ee90")); //green
            if (AnswerCheck.get(idx)[1] == 1)
                ans2.setBackgroundColor(Color.parseColor("#90ee90"));
            if (AnswerCheck.get(idx)[2] == 1)
                ans3.setBackgroundColor(Color.parseColor("#90ee90"));
        }
    }
}
