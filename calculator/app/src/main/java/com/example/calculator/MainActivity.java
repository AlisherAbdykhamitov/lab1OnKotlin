package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Brain calculator;
    private TextView text;

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
        };

        int[] actions = new int[]{
                R.id.delete,
                R.id.plus,
                R.id.minus,
                R.id.multiply,
                R.id.equals,
                R.id.division,
                R.id.dot,
        };

        text = findViewById(R.id.panel);

        calculator = new Brain();

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

        for (int i = 0; i < numbers.length; i++){
            findViewById(numbers[i]).setOnClickListener(numberClickListener);
        }

        for (int i = 0; i < actions.length; i++) {
            findViewById(actions[i]).setOnClickListener(actionClickListener);
        }

        findViewById(R.id.delete).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                calculator.deleteAll();
                return false;
            }
        });

    }
}
