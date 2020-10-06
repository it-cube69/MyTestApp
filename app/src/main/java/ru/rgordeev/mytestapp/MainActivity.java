package ru.rgordeev.mytestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // переменная для связывания компонента текстового поля вывода на активити
    private TextView textView;
    // переменная для связывания компонента кнопки на активити
    private Button button;
    // начальный цвет
    private int color = 0xFFFFFFFF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
        button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // выводим в поток ввода/выводв логи со значением переменной color в двоичном представлении
                Log.d("color", "COLOR = " + Integer.toBinaryString(color));
                // изменяем цвет фона текстового поля вывода
                textView.setBackgroundColor(color);
                // меняем значение цвета сдвигом влево на 1
                color = color << 1;
            }
        });
    }
}