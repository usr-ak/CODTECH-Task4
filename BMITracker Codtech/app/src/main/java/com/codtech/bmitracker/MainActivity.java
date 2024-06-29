package com.codtech.bmitracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextHeight, editTextWeight;
    private Button buttonCalculate;
    private TextView textViewBMIResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewBMIResult = findViewById(R.id.textViewBMIResult);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String heightStr = editTextHeight.getText().toString().trim();
        String weightStr = editTextWeight.getText().toString().trim();

        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please enter both height and weight.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float height = Float.parseFloat(heightStr) / 100;
            float weight = Float.parseFloat(weightStr);

            float bmi = weight / (height * height);

            displayBMIResult(bmi);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers for height and weight.", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayBMIResult(float bmi) {
        String bmiResult;
        if (bmi < 16) {
            bmiResult = "Severe Thinness";
        } else if (bmi < 17) {
            bmiResult = "Moderate Thinness";
        } else if (bmi < 18.5) {
            bmiResult = "Mild Thinness";
        } else if (bmi < 25) {
            bmiResult = "Normal";
        } else if (bmi < 30) {
            bmiResult = "Overweight";
        } else if (bmi < 35) {
            bmiResult = "Obese Class I";
        } else if (bmi < 40) {
            bmiResult = "Obese Class II";
        } else {
            bmiResult = "Obese Class III";
        }
        textViewBMIResult.setText(String.format("BMI: %.2f\n%s", bmi, bmiResult));
    }
}
