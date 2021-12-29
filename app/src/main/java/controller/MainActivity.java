package controller;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.topquiz.R;

import java.text.BreakIterator;

import model.User;

public class MainActivity extends AppCompatActivity {
    private static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    TextView mGreetingTextView;
    EditText mNameInput;
    Button mPlayButton;

    private SharedPreferences mPreferences;
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";
   
    public static final int GAME_ACTIVITY_REQUEST_CODE = 42;

    private User mUser;
    private TextView mGreetingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("MainActivity::onCreate()");

        mUser = new User();


        mPreferences = getPreferences(MODE_PRIVATE);

        mPreferences = getPreferences(MODE_PRIVATE);
        mGreetingText =  findViewById(R.id.activity_main_greeting_txt);
        mGreetingTextView = findViewById(R.id.activity_main_greeting_txt);
        mNameInput = findViewById(R.id.activity_main_name_input);
        mPlayButton = findViewById(R.id.activity_main_play_btn);
        mPlayButton.setEnabled(false);
        greetUser();
        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPlayButton.setEnabled(s.toString().length() != 0);

            }
        });
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstName = mNameInput.getText().toString();
                mUser.setFirstName(firstName);


                mPreferences.edit().putString(PREF_KEY_FIRSTNAME, mUser.getFirstName()).apply();
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(gameActivityIntent);


            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            // Fetch the score from the Intent
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            mPreferences.edit().putInt(PREF_KEY_SCORE, score).apply();
            greetUser();
        }
        }


    private void greetUser() {
        String firstName = mPreferences.getString(PREF_KEY_FIRSTNAME, null);
        if (null != firstName) {
            int score = mPreferences.getInt(PREF_KEY_SCORE, 0);
            String fulltext = "Welcome back,"
                    + firstName + "!\nYour last score was" + score
                    + ", will you do better this time?";
            mGreetingText.setText(fulltext);
            mNameInput.setText(firstName);
            mNameInput.setSelection(firstName.length());
            mPlayButton.setEnabled(true);
        }
    }
}
