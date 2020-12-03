package ru.rgordeev.mytestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String LOGIN = "user";
    private static final String PASSWORD = "password";

    private EditText login;
    private EditText password;
    private Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        signin = findViewById(R.id.signin);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                String l = login.getText().toString();
                String p = password.getText().toString();
                if (LOGIN.equals(l) && PASSWORD.equals(p)) {
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Неверные логин или пароль", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}