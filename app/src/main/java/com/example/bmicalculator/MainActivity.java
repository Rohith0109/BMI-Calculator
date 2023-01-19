package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText edtHeight, edtWeight;
    TextView units,result;
    ConstraintLayout mainPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);

        mainPage = findViewById(R.id.mainPage);


    }

    public void bmiCalculate(View view){
        units = findViewById(R.id.units);
        edtHeight = findViewById(R.id.edtHeight);
        edtWeight = findViewById(R.id.edtWeight);
        result = findViewById(R.id.result);

        float weight = edtWeight.getText().toString().equals("")? 0 : Float.parseFloat(edtWeight.getText().toString());
        float height = edtHeight.getText().toString().equals("")? 0 : Float.parseFloat(edtHeight.getText().toString());
        if (weight==0 || height==0){
            Toast.makeText(this, "Enter valid values", Toast.LENGTH_SHORT).show();
            return;
        }
        double heightinM = 0;
        if (units.getText().equals("ft")){
            heightinM = height*0.3048;
        }
        else if (units.getText().equals("inch")){
            heightinM = height*0.0254;
        }
        else{
            heightinM = height*0.01;
        }
        try {
            DecimalFormat df2 = new DecimalFormat("###.##");
            double res = weight/(heightinM*heightinM);
            res = Double.valueOf(df2.format(res));
            result.setText("Your BMI is : "+res+"\n "+check(res));


        }
        catch (Exception e){
            edtHeight.setText("");
            edtWeight.setText("");
            Toast.makeText(this, "Please Enter Valid Details", Toast.LENGTH_SHORT).show();
        }

        edtWeight.setText("");
        edtHeight.setText("");
    }

    public void checkButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);
        edtHeight = findViewById(R.id.edtHeight);
        units = findViewById(R.id.units);
        String un = radioButton.getText().toString();
        String str = "You have selected ";
        str = str.concat(un);
        units.setText(un);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

    }

    public String check(Double d){
        if (d<18.5){
            return "Underweight";
        }
        else if (d>=18.5 && d<25.0){
            return "Normal";
        }
        else if (d>=25.0 && d<40.0){
            return "Overweight";
        }
        else{
            return "Obesity";
        }
    }



}