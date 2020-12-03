package ru.rgordeev.mytestapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        String resultText = getIntent().getExtras().getString("result");
        textView = findViewById(R.id.resultText);
        textView.setText(resultText);
    }
}