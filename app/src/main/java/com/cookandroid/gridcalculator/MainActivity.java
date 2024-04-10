package com.cookandroid.gridcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etNum1, etNum2;
    Button add, sub, mul, div;
    TextView calResult;

    Button[] btnNums = new Button[10];
    int[] btnNumsIds = {
            R.id.num0, R.id.num1, R.id.num2, R.id.num3, R.id.num4,
            R.id.num5, R.id.num6, R.id.num7, R.id.num8, R.id.num9,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon2);

        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        mul = findViewById(R.id.mul);
        div = findViewById(R.id.div);
        calResult = findViewById(R.id.calResult);

        for (int i = 0; i < btnNumsIds.length; i++) {
            btnNums[i] = findViewById(btnNumsIds[i]);
        }

        for (int i = 0; i < btnNumsIds.length; i++) {
            final int index = i;

            btnNums[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (etNum1.isFocused()) {
                        etNum1.setText(etNum1.getText().toString() + btnNums[index].getText());
                    } else if (etNum2.isFocused()) {
                        etNum2.setText(etNum2.getText().toString() + btnNums[index].getText());
                    } else {
                        Toast.makeText(MainActivity.this, "숫자 입력창 선택", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNum1.length() == 0) {
                    Toast.makeText(MainActivity.this, "숫자1 입력!", Toast.LENGTH_SHORT).show();
                    etNum1.requestFocus();
                    return;
                } else if (etNum2.length() == 0) {
                    Toast.makeText(MainActivity.this, "숫자2 입력!", Toast.LENGTH_SHORT).show();
                    etNum2.requestFocus();
                    return;
                }

                int num1 = Integer.parseInt(etNum1.getText().toString());
                int num2 = Integer.parseInt(etNum2.getText().toString());

                int result = 0;
                int id = view.getId();
                if (id == R.id.add) {
                    result = num1 + num2;
                } else if (id == R.id.sub) {
                    result = num1 - num2;
                } else if (id == R.id.mul) {
                    result = num1 * num2;
                } else if (id == R.id.div) {
                    if (num2 == 0) {
                        Toast.makeText(MainActivity.this, "0으로 나눌수 없음", Toast.LENGTH_SHORT).show();
                        etNum2.requestFocus();
                    }
                    result = num1 / num2;
                }

                calResult.setText("계산 결과 : " + result);
            }
        };
        add.setOnClickListener(operatorListener);
        sub.setOnClickListener(operatorListener);
        mul.setOnClickListener(operatorListener);
        div.setOnClickListener(operatorListener);
    }
}