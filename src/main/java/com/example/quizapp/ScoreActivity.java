package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    int score, maxQuestions;
    String playerName;
    TextView congratsText, scoreText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        congratsText = findViewById(R.id.congrats_txt);
        scoreText = findViewById(R.id.total_scores);

        Intent intent = getIntent();
        score = intent.getIntExtra("Score", 0);
        maxQuestions = intent.getIntExtra("MaxQuestions", 0);
        playerName = intent.getStringExtra("Name");

        congratsText.setText("Congratulations " + playerName + "!");
        scoreText.setText(score + "/" + maxQuestions);
    }

    public void OnClickRestart(View view) {
        finish();
    }

    public void OnClickFinish(View view) {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
