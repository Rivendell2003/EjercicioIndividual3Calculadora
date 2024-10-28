package com.example.ejercicioindividual3calculadora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private StringBuilder currentInput = new StringBuilder();
    private double operand1 = 0;
    private String currentOperator = "";
    private boolean isNewInput = true;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        clearDisplay();  // display con 0 al iniciar
        initializeButtons();
    }

    private void initializeButtons() {
        int[] numberButtonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9
        };

        for (int id : numberButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(v -> appendToDisplay(button.getText().toString()));
        }

        findViewById(R.id.button_clear).setOnClickListener(v -> clearDisplay());
        findViewById(R.id.button_sum).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.button_resta).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.button_multiplicacion).setOnClickListener(v -> setOperator("*"));
        findViewById(R.id.button_division).setOnClickListener(v -> setOperator("/"));
        findViewById(R.id.button_equals).setOnClickListener(v -> calculateResult());
    }

    private void appendToDisplay(String value) {
        if (isNewInput) {
            currentInput.setLength(0);
            isNewInput = false;
        }
        //  valor inicial     es "0", lo reemplazamos
        if (currentInput.toString().equals("0")) {
            currentInput.setLength(0);
        }
        currentInput.append(value);
        display.setText(currentInput.toString());
    }

    private void clearDisplay() {
        currentInput.setLength(0);
        currentInput.append("0"); // Muestra 0 al iniciar
        display.setText(currentInput.toString());
        operand1 = 0;
        currentOperator = "";
        isNewInput = true;
    }

    private void setOperator(String operator) {
        if (currentInput.length() == 0) {
            display.setText(getString(R.string.error_empty_inputs)); // error si no hay entrada
            return;
        }
        try {
            operand1 = Double.parseDouble(currentInput.toString());
            currentOperator = operator;
            currentInput.setLength(0);
            isNewInput = true;
        } catch (NumberFormatException e) {
            display.setText(getString(R.string.error_invalid_input));
            clearDisplay();
        }
    }

    private void calculateResult() {
        if (currentOperator.isEmpty() || currentInput.length() == 0) return;

        double operand2;
        try {
            operand2 = Double.parseDouble(currentInput.toString());
        } catch (NumberFormatException e) {
            display.setText(getString(R.string.error_invalid_input));
            clearDisplay();
            return;
        }

        double result = 0;

        switch (currentOperator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                if (operand2 == 0) {
                    display.setText(getString(R.string.error_division_by_zero));
                    clearDisplay();
                    return;
                }
                result = operand1 / operand2;
                break;
        }

        display.setText(decimalFormat.format(result));
        currentInput.setLength(0);
        currentOperator = "";
        isNewInput = true;
    }
}
