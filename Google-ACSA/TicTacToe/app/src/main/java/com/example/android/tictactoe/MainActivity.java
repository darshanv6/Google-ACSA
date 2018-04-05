package com.example.android.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private final int FP = ViewGroup.LayoutParams.WRAP_CONTENT;

    private  Button button1;
    private  Button useAIButton;
    private TableLayout tableLayout;
    private TextView resultView;
    private Board gameBoard;

    public static int counter= 1;

    private boolean CPUCtrl = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGameBoard();

        gameBoard=new Board();
        resultView = (TextView) findViewById(R.id.textView);
        button1 = (Button) findViewById(R.id.button);
        useAIButton = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        useAIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CPUCtrl=true;
                int position= new AI().miniMax(gameBoard.copy()).position;
                Log.d("Main","Counter:"+counter);
                counter=0;
                Log.d("Main","AI will place at:"+position);
                if (position!=0){
                    (tableLayout.findViewWithTag(position)).callOnClick();
                }

                CPUCtrl=false;
            }
        });
    }

    private void initGameBoard(){

        tableLayout = (TableLayout) findViewById(R.id.table_layout);
        tableLayout.setStretchAllColumns(true);
        // tableLayout.setBackgroundResource(R.color.blue);

        int counter = 1;
        for(int row=0;row<3;row++) {
            TableRow tableRow=new TableRow(this);
            for(int col=0;col<3;col++) {

                Button button=new Button(this);

                button.setTag(counter);

                //button1.setText(row+","+col+"\nTag:"+button1.getTag());

                button.setOnClickListener(this);
                button.setWidth(200);
                button.setHeight(200);
                button.setTextSize(40);

                tableRow.addView(button);

                counter++;

            }

            tableLayout.addView(tableRow, new TableLayout.LayoutParams(FP, WC));
        }


    }


    @Override
    public void onClick(View v) {



        String Player1="O";
        String Player2="X";
        String place="";

        if (!gameBoard.gameOver){
            switch (gameBoard.getCurrentPlayer()){
                case 1: place=Player1;break;
                case 2: place=Player2;break;
            }


            int choice = Integer.valueOf(v.getTag().toString());
            gameBoard.placePiece(choice); ((Button)v).setText(place);
            updateUI();

            if (!CPUCtrl){
                useAIButton.callOnClick();
            }

        }

    }

    private void updateUI(){

        switch (gameBoard.getGameResult()){
            case 1:  resultView.setText("Player 1 Wins!"); break;
            case 2:  resultView.setText("Player 2 Wins!"); break;
            case 0:  resultView.setText("Game Draw!");    break;
            default: break;
        }


    }

    private void resetGame(){
        tableLayout.removeAllViews();
        resultView.setText(" ");
        initGameBoard();
        gameBoard=new Board();
    }



}
