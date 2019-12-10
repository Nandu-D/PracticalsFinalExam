package com.example.practicalsfinalexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private int score1 = 0, score2 = 0, score3 = 0, incrementValue = 1;

    private TextView team1ScoreTextView, team2ScoreTextView, team3ScoreTextView;
    private Button team1PlusButton, team1MinusButton, team2PlusButton, team2MinusButton,
            team3PlusButton, team3MinusButton;
    private Spinner incrementSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        provideHandlesAndInitialValuesIfAny();
        setUpListenersForButtons();
        setUpSpinnerAndItsListener();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_menu_item:
                Toast.makeText(this, "Name : Nandu Dharmapalan", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settings_menu_item:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.team1_minus_button:
                if (score1 - incrementValue >= 0) {
                    score1 = score1 - incrementValue;
                    team1ScoreTextView.setText(Integer.toString(score1));
                }
                break;
            case R.id.team1_plus_button:
                score1 = score1 + incrementValue;
                team1ScoreTextView.setText(Integer.toString(score1));
                break;
            case R.id.team2_minus_button:
                if (score2 - incrementValue >= 0) {
                    score2 = score2 - incrementValue;
                    team2ScoreTextView.setText(Integer.toString(score2));
                }
                break;
            case R.id.team2_plus_button:
                score2 = score2 + incrementValue;
                team2ScoreTextView.setText(Integer.toString(score2));
                break;
            case R.id.team3_minus_button:
                if (score3 - incrementValue >= 0) {
                    score3 = score3 - incrementValue;
                    team3ScoreTextView.setText(Integer.toString(score3));
                }
                break;
            case R.id.team3_plus_button:
                score3 = score3 + incrementValue;
                team3ScoreTextView.setText(Integer.toString(score3));
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final int ADD_OR_SUBTRACT_BY_1 = 0, ADD_OR_SUBTRACT_BY_2 = 1, ADD_OR_SUBTRACT_BY_3 = 2,
                ADD_OR_SUBTRACT_BY_4 = 3, ADD_OR_SUBTRACT_BY_5 = 4;

        switch (position) {
            case ADD_OR_SUBTRACT_BY_1:
                incrementValue = 1;
                break;
            case ADD_OR_SUBTRACT_BY_2:
                incrementValue = 2;
                break;
            case ADD_OR_SUBTRACT_BY_3:
                incrementValue = 3;
                break;
            case ADD_OR_SUBTRACT_BY_4:
                incrementValue = 4;
                break;
            case ADD_OR_SUBTRACT_BY_5:
                incrementValue = 5;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void provideHandlesAndInitialValuesIfAny() {
        team1ScoreTextView = (TextView) findViewById(R.id.team1_score_text_view);
        team2ScoreTextView = (TextView) findViewById(R.id.team2_score_text_view);
        team3ScoreTextView = (TextView) findViewById(R.id.team3_score_text_view);

        team1MinusButton = (Button) findViewById(R.id.team1_minus_button);
        team1PlusButton = (Button) findViewById(R.id.team1_plus_button);
        team2MinusButton = (Button) findViewById(R.id.team2_minus_button);
        team2PlusButton = (Button) findViewById(R.id.team2_plus_button);
        team3MinusButton = (Button) findViewById(R.id.team3_minus_button);
        team3PlusButton = (Button) findViewById(R.id.team3_plus_button);

        incrementSpinner = (Spinner) findViewById(R.id.increment_spinner);

        team1ScoreTextView.setText("0");
        team2ScoreTextView.setText("0");
        team3ScoreTextView.setText("0");
    }

    private void setUpListenersForButtons() {
        team1MinusButton.setOnClickListener(this);
        team2MinusButton.setOnClickListener(this);
        team3MinusButton.setOnClickListener(this);
        team1PlusButton.setOnClickListener(this);
        team2PlusButton.setOnClickListener(this);
        team3PlusButton.setOnClickListener(this);
    }

    private void setUpSpinnerAndItsListener() {
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.increment_choices, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        incrementSpinner.setAdapter(arrayAdapter);

        incrementSpinner.setOnItemSelectedListener(this);
    }
}
