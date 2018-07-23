package com.khanosman.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    public static String firstPlayer,secondPlayer;
    public EditText playerOne,playertwo;
    public Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOne = (EditText)findViewById(R.id.playerOneText);
        playertwo = (EditText)findViewById(R.id.playerTwoText);
        startBtn = (Button)findViewById(R.id.btnStart);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               final String pOne = playerOne.getText().toString().trim();
                final String pTwo = playertwo.getText().toString().trim();

                if(!TextUtils.isEmpty(pOne)&&!TextUtils.isEmpty(pTwo)) {

                    firstPlayer  = pOne;
                    secondPlayer = pTwo;

                    playerOne.setText("");
                    playertwo.setText("");

                    Intent newIntent = new Intent(MainActivity.this, GameActivity.class);
                    startActivity(newIntent);

                }else{
                    Toast.makeText(MainActivity.this,"Enter player's name,Please!!!",Toast.LENGTH_LONG).show();
                }

            }
        });




    }






}
