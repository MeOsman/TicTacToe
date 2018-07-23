package com.khanosman.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.khanosman.tictactoe.MainActivity.firstPlayer;
import static com.khanosman.tictactoe.MainActivity.secondPlayer;

/**
 * Created by osman on 1/05/2018.
 */

public class GameActivity extends AppCompatActivity {

    private  TextView currentPlayer, playerWinOne,playerWinTwo;

    String playerOneWinning="",playerTwoWinning="";
    int playerOneWinningScore=0,playerTwoWinningScore=0;

    // 0 = yellow, 1 = red

    int activePlayer = 0;

    boolean gameIsActive = true;

    // 2 means unplayed

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view) {



        ImageView counter = (ImageView) view;



        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                currentPlayer.setText(secondPlayer+"'s turn");
                counter.setImageResource(R.drawable.tic);

                activePlayer = 1;


            } else {

                counter.setImageResource(R.drawable.tac);
                currentPlayer.setText(firstPlayer+"'s turn");
                activePlayer = 0;


            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    // Someone has won!

                    gameIsActive = false;

                    String winner = secondPlayer;

                    if (gameState[winningPosition[0]] == 0) {

                        winner =  firstPlayer;
                        playerOneWinningScore++;
                        playerOneWinning = Integer.toString(playerOneWinningScore);
                        playerWinOne.setText(firstPlayer+" "+playerOneWinning);

                    }else{
                        playerTwoWinningScore++;
                        playerTwoWinning = Integer.toString(playerTwoWinningScore);
                        playerWinTwo.setText(secondPlayer+" "+playerTwoWinning);

                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner + " has won!");

                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                    layout.setVisibility(View.VISIBLE);

                } else {

                    boolean gameIsOver = true;

                    for (int counterState : gameState) {

                        if (counterState == 2) gameIsOver = false;

                    }

                    if (gameIsOver) {

                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                        winnerMessage.setText("It's a draw");

                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                        layout.setVisibility(View.VISIBLE);

                    }

                }

            }
        }


    }

    public void playAgain(View view) {

        gameIsActive = true;


        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        currentPlayer.setText(firstPlayer+"'s turn");
        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 2;

        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        currentPlayer = (TextView)findViewById(R.id.currentPlayer);
        currentPlayer.setText(firstPlayer+"'s turn");
        playerWinOne = (TextView)findViewById(R.id.winOne);
        playerWinOne.setText(firstPlayer+ " 0");
        playerWinTwo = (TextView)findViewById(R.id.winTwo);
        playerWinTwo.setText(secondPlayer+ " 0");

    }


}
