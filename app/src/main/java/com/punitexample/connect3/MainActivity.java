package com.punitexample.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//0: yellow, 1: red, 2:empty
    int activate = 0;
    int []  gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winningPosition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;
    Button playAgain;
    TextView ed;
    GridLayout mygridLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playAgain = (Button) findViewById(R.id.playAgain);
        ed = (TextView) findViewById(R.id.winnertextView);
        mygridLayout = (GridLayout) findViewById(R.id.gridLayout);
        try{
            playAgain(findViewById(R.id.playAgain));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void connect(View view) {
        ImageView counter = (ImageView) view;
        int tag = Integer.parseInt(counter.getTag().toString());
            if (gameState[tag] == 2 && gameActive) {
                gameState[tag] = activate;
                counter.animate().alpha(0);
                if (activate == 0) {
                    counter.setImageResource(R.drawable.yellow);
                    activate = 1;
                } else if (activate == 1) {
                    counter.setImageResource(R.drawable.red);
                    activate = 0;
                }
                Log.d("tag","Hii");
                counter.animate().alpha(1);
                for (int[] winningPosition : winningPosition) {
                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[2]] != 2) {
                        gameActive = false;
                        String winner = "";
                        if (activate == 1) {
                            winner = "Yellow";
                        } else {
                            winner = "Red";
                        }
                        Button playAgain = (Button) findViewById(R.id.playAgain);
                        TextView ed = (TextView) findViewById(R.id.winnertextView);
                        ed.setText(winner + " has won");
                        playAgain.animate().alpha(1).setDuration(300);
                        ed.animate().alpha(1).setDuration(300);

                    }
                }
            }
    }

    public void playAgain(View view) throws Exception {
        playAgain.animate().alpha(0).setDuration(300);
        ed.animate().alpha(0).setDuration(300);
        for(int i=0 ; i<mygridLayout.getChildCount();i++){
            ImageView counter = (ImageView) mygridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i =0 ; i<gameState.length;i++){
            gameState[i] = 2;
        }
        gameActive = true;
        activate = 1;
    }
}
