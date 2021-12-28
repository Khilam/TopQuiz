package com.example.topquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    TextView mGameQuestionTextView;
    Button mGameButton1;
    Button mGameButton2;
    Button mGameButton3;
    Button mGameButton4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mGameQuestionTextView=findViewById(R.id.activity_game_question_text);
        mGameButton1=findViewById(R.id.activity_game_answer1_btn);
        mGameButton1=findViewById(R.id.activity_game_answer2_btn);
        mGameButton1=findViewById(R.id.activity_game_answer3_btn);
        mGameButton1=findViewById(R.id.activity_game_answer4_btn);

    }
}