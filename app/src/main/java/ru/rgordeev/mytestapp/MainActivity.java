package ru.rgordeev.mytestapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int[][] field = new int[3][3];
    private int turn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("LIFECIRCLE", "onCreate was called");
        init(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("LIFECIRCLE", "onSaveInstanceState was called");
        outState.putSerializable("field", field);
        outState.putInt("turn", turn);
    }

    private void init(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.turn = 0;
            this.field = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    field[i][j] = 0;
                }
            }
        } else {
            this.turn = savedInstanceState.getInt("turn", 0);
            if (savedInstanceState.containsKey("field")) {
                this.field = (int[][]) savedInstanceState.getSerializable("field");
            } else {
                this.field = new int[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        field[i][j] = 0;
                    }
                }
            }
        }
        LinearLayout linearLayout = findViewById(R.id.field);
        for (int index = 0; index < linearLayout.getChildCount(); index++) {
            LinearLayout nextChild = (LinearLayout) linearLayout.getChildAt(index);
            for (int buttonIndex = 0; buttonIndex < nextChild.getChildCount(); buttonIndex++) {
                Button button = (Button) nextChild.getChildAt(buttonIndex);
                String tag = button.getTag().toString();
                switch (tag) {
                    case "11":
                        print(button, 0, 0);
                        break;
                    case "12":
                        print(button, 0, 1);
                        break;
                    case "13":
                        print(button, 0, 2);
                        break;
                    case "21":
                        print(button, 1, 0);
                        break;
                    case "22":
                        print(button, 1, 1);
                        break;
                    case "23":
                        print(button, 1, 2);
                        break;
                    case "31":
                        print(button, 2, 0);
                        break;
                    case "32":
                        print(button, 2, 1);
                        break;
                    case "33":
                        print(button, 2, 2);
                        break;
                    default:
                        // do nothing
                }
            }
        }
    }

    public void myMethod(View v) {
        Button button = (Button) v;
        String tag = button.getTag().toString();
        switch (tag) {
            case "11":
                turn(0, 0);
                print(button, 0, 0);
                break;
            case "12":
                turn(0, 1);
                print(button, 0, 1);
                break;
            case "13":
                turn(0, 2);
                print(button, 0, 2);
                break;
            case "21":
                turn(1, 0);
                print(button, 1, 0);
                break;
            case "22":
                turn(1, 1);
                print(button, 1, 1);
                break;
            case "23":
                turn(1, 2);
                print(button, 1, 2);
                break;
            case "31":
                turn(2, 0);
                print(button, 2, 0);
                break;
            case "32":
                turn(2, 1);
                print(button, 2, 1);
                break;
            case "33":
                turn(2, 2);
                print(button, 2, 2);
                break;
            default:
                // do nothing
        }
        if (win() != 0) {
            init(null);
            ((Button) findViewById(R.id.button11)).setText("");
            ((Button) findViewById(R.id.button12)).setText("");
            ((Button) findViewById(R.id.button13)).setText("");
            ((Button) findViewById(R.id.button21)).setText("");
            ((Button) findViewById(R.id.button22)).setText("");
            ((Button) findViewById(R.id.button23)).setText("");
            ((Button) findViewById(R.id.button31)).setText("");
            ((Button) findViewById(R.id.button32)).setText("");
            ((Button) findViewById(R.id.button33)).setText("");
        }
    }

    private int win() {
        if (winO()) {
            Toast.makeText(this, "Выйграл О", Toast.LENGTH_LONG).show();
            return 2;
        } else if (winX()) {
            Toast.makeText(this, "Выйграл X", Toast.LENGTH_LONG).show();
            return 1;
        }
        return 0;
    }

    private boolean winX() {
        boolean result = false;
        for (int i = 0; i < 3; i++) {
            int[] row = extractRow(i);
            result = result || all(1, row);
        }

        for (int i = 0; i < 3; i++) {
            int[] column = extractColumn(i);
            result = result || all(1, column);
        }

        int[] diag1 = extractDiag1();
        result = result || all(1, diag1);

        int[] diag2 = extractDiag2();
        result = result || all(1, diag2);

        return result;
    }

    private boolean winO() {
        boolean result = false;
        for (int i = 0; i < 3; i++) {
            int[] row = extractRow(i);
            result = result || all(2, row);
        }

        for (int i = 0; i < 3; i++) {
            int[] column = extractColumn(i);
            result = result || all(2, column);
        }

        int[] diag1 = extractDiag1();
        result = result || all(2, diag1);

        int[] diag2 = extractDiag2();
        result = result || all(2, diag2);

        return result;
    }

    private boolean all(int user, int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != user) return false;
        }
        return true;
    }

    private int[] extractRow(int i) {
        return field[i];
    }

    private int[] extractColumn(int j) {
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            result[i] = field[i][j];
        }
        return result;
    }

    private int[] extractDiag1() {
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            result[i] = field[i][i];
        }
        return result;
    }

    private int[] extractDiag2() {
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            result[i] = field[i][2 - i];
        }
        return result;
    }

    private boolean check(int i, int j) {
        if (field[i][j] != 0) {
            return false;
        }
        return true;
    }

    private void print(Button button, int i, int j) {
        if (field[i][j] == 1) {
            button.setText("X");
        } else if (field[i][j] == 2) {
            button.setText("O");
        } else {
            button.setText("");
        }
    }

    private void turn(int i, int j) {
        if (!check(i, j)) {
            Toast.makeText(this, "Недопустимый ход", Toast.LENGTH_LONG).show();
            return;
        }
        if (turn == 0) {
            field[i][j] = 1;
            turn = 1;
        } else {
            field[i][j] = 2;
            turn = 0;
        }
    }
}