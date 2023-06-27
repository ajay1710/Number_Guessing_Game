package com.company.numberguessinggame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
  private  TextView textViewLast,textViewRight,textViewHint;
  private  EditText editTextGuess;
  private  Button buttonConfirm;
  Random r=new Random();
  int random;

  boolean twoDigits,threeDigits,fourDigits;

  int remainingRight =10;

  ArrayList<Integer> guessList=new ArrayList<>();
  int userAttempts=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewLast=findViewById(R.id.textViewLast);
        textViewHint=findViewById(R.id.textViewHint);
        textViewRight=findViewById(R.id.textViewRight);
        buttonConfirm=findViewById(R.id.buttonConfirm);
        editTextGuess=findViewById(R.id.editTextTextGuess);

        twoDigits=getIntent().getBooleanExtra("one",false);
        threeDigits=getIntent().getBooleanExtra("two",false);
        fourDigits=getIntent().getBooleanExtra("three",false);

        if(twoDigits){
            random=r.nextInt(90)+10;
        }
        if(threeDigits){
            random=r.nextInt(900)+100;
        }
        if(fourDigits){
            random=r.nextInt(9000)+1000;
        }

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guess=editTextGuess.getText().toString();
                if(guess.equals("")){
                    Toast.makeText(GameActivity.this,"Please enter a guess.",Toast.LENGTH_LONG).show();
                }else{
                    textViewHint.setVisibility(View.VISIBLE);
                    textViewLast.setVisibility(View.VISIBLE);
                    textViewRight.setVisibility(View.VISIBLE);
                    userAttempts++;
                    remainingRight--;
                    int userGuess=Integer.parseInt(guess);
                    guessList.add(userGuess);
                    textViewLast.setText("Your last guess is: "+guess);
                    textViewRight.setText("Your reamaining right: "+remainingRight);
                    if(random==userGuess){
                        AlertDialog.Builder builder=new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Game");
                        builder.setCancelable(false);
                        builder.setMessage("Congratulation. My guess was "+random
                        +"\n\n You know my number in "+userAttempts
                        +" attempts. \n\n Your guesses : "+guessList
                        +"\n\n Would you like to play again?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent= new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();

                    }
                    if(random<userGuess){
                        textViewHint.setText("Decrease your number");
                    }
                    if(random>userGuess){
                        textViewHint.setText("Increase your number");
                    }
                    if(remainingRight==0){
                        AlertDialog.Builder builder=new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Game");
                        builder.setCancelable(false);
                        builder.setMessage("Sorry, your right to guess is over."
                                + " \n\n My guess was "+random
                                +" \n\n Your guesses : "+guessList
                                +" \n\n Would you like to play again?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent= new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();


                    }
                    editTextGuess.setText("");
                }
            }
        });


    }
}