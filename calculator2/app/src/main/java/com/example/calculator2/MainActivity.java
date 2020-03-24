package com.example.calculator2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Work calculator;
    private EditText text;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("state", calculator.getState());
        outState.putString("inputStr", calculator.getText());
        outState.putInt("actionSelected", calculator.getActionSelected());
        outState.putDouble("firstNum", calculator.getFirstNum());
        outState.putDouble("secondNum", calculator.getSecondNum());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int[] numbers = new int[]{
                R.id.num0,
                R.id.num1,
                R.id.num2,
                R.id.num3,
                R.id.num4,
                R.id.num5,
                R.id.num6,
                R.id.num7,
                R.id.num8,
                R.id.num9,
                R.id.pi,
                R.id.exponent,
                R.id.negative
        };

        int[] actions = new int[]{
                R.id.delete,
                R.id.plus,
                R.id.minus,
                R.id.multiply,
                R.id.equals,
                R.id.division,
                R.id.dot,
                R.id.sin,
                R.id.cos,
                R.id.tan,
                R.id.ln,
                R.id.log,
                R.id.percent,
                R.id.degree,
                R.id.factorial,
                R.id.root
        };

        text = findViewById(R.id.panel);

        if (savedInstanceState != null) {
            calculator = new Work(savedInstanceState.getString("inputStr"), savedInstanceState.getString("state"), savedInstanceState.getInt("actionSelected"), savedInstanceState.getDouble("firstNum"), savedInstanceState.getDouble("secondNum"));
        } else
            calculator = new Work();

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onNumPressed(v.getId());
                text.setText(calculator.getText());
            }
        };

        View.OnClickListener actionClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onActionsPressed(v.getId());
                text.setText(calculator.getText());
            }


        };

        for (int i = 0; i < numbers.length; i++) {
            try {
                findViewById(numbers[i]).setOnClickListener(numberClickListener);
            } catch (Exception e) {

            }
        }

        for (int i = 0; i < actions.length; i++) {
            try {
                findViewById(actions[i]).setOnClickListener(actionClickListener);
            } catch (Exception e) {

            }
        }

        findViewById(R.id.delete).setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                calculator.deleteAll();
                return false;
            }
        });

    }

}
