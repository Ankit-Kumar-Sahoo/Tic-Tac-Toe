package com.ankitkumarsahoo.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
//     0 = blue, 1 = red
    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
//    2 means unplayed

    boolean gameIsActive = true;

    int [][] winningPositions = {{0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}};

    public void dropIn(View v) {

        ImageView counter = (ImageView) v;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameIsActive) {


            counter.setTranslationY(-1000f);
            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.blue);
                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).alpha(1).rotation(180f).setDuration(300);
        }

        for (int [] winningPosition : winningPositions) {

            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                String winner = "Red";

                if (gameState[winningPosition[0]] == 0) {

                    winner = "Blue";
                }

//        Someone won

                gameIsActive = true;
                TextView winnerMessage = findViewById(R.id.winnerMessage);
                winnerMessage.setText(winner + " has won!");

                LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                layout.startAnimation(slideUp);

                layout.setVisibility(View.VISIBLE);
            }
            else {

                boolean gameOver = true;

                for (int counterState : gameState) {

                    if (counterState == 2) {

                        gameOver = false;
                    }
                }

                if (gameOver) {

                    TextView winnerMessage = findViewById(R.id.winnerMessage);
                    winnerMessage.setText("It's a draw!");

                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                    layout.startAnimation(slideUp);

                    layout.setVisibility(View.VISIBLE);
                }
            }
        }


    }

    public void playAgain(View view) {

        gameIsActive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        layout.startAnimation(slideDown);

        layout.setVisibility(View.GONE);


        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) gameState[i] = 2;

        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}