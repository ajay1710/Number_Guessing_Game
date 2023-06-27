package com.company.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
   private RadioButton radio,radio2,radio3;
   private Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart=findViewById(R.id.buttonStart);
        radio=findViewById(R.id.radioButton);
        radio2=findViewById(R.id.radioButton2);
        radio3=findViewById(R.id.radioButton3);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,GameActivity.class);
                if(!radio.isChecked() && !radio2.isChecked() && !radio3.isChecked()) {
                    Snackbar.make(v,"Please select a number of digits.",Snackbar.LENGTH_LONG).show();
                }else{
                    if(radio.isChecked()){
                        intent.putExtra("one",true);
                    }
                    if(radio2.isChecked()){
                        intent.putExtra("two",true);
                    }
                    if(radio3.isChecked()){
                        intent.putExtra("three",true);
                    }
                    startActivity(intent);
                }


            }
        });
    }
}