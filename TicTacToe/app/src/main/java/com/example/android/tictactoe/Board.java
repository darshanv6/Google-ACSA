package com.example.android.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static int GAME_RESULT_WIN=1;
    private static int GAME_RESULT_FAIL=2;
    private static int GAME_RESULT_DRAW=0;

    int[][] gameBoardArray =new int[3][3];

    private int playerCounter =1;

    public boolean gameOver = false;

    public Board copy(){

        Board copy=new Board();

        if (this.gameOver){
            copy.gameOver=true;
        }else {
            copy.gameOver=false;
        }

        copy.playerCounter =Integer.valueOf(this.playerCounter);

        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                copy.gameBoardArray[i][j]=this.gameBoardArray[i][j];
            }
        }

        return copy;
    }

    public void placePiece(int position){

        if (!gameOver){

            int score=0;
            switch (getCurrentPlayer()){
                case 1:score=1;break;
                case 2:score=5;break;
            }

            switch (position){
                case 1: gameBoardArray[0][0]=score; break;
                case 2: gameBoardArray[0][1]=score; break;
                case 3: gameBoardArray[0][2]=score; break;
                case 4: gameBoardArray[1][0]=score; break;
                case 5: gameBoardArray[1][1]=score; break;
                case 6: gameBoardArray[1][2]=score; break;
                case 7: gameBoardArray[2][0]=score; break;
                case 8: gameBoardArray[2][1]=score; break;
                case 9: gameBoardArray[2][2]=score; break;
                default: break;
            }
            playerCounter++;

            if (getGameResult()!=4){
                gameOver=true;
                // Log.d("Board","Game Over +Player"+getCurrentPlayer()+"Wins");
            }

        }

    }

    public int getCurrentPlayer() {
        int currentPlayer;
        if (this.playerCounter %2==0)
            currentPlayer=2;
        else
            currentPlayer=1;
        return currentPlayer;
    }

    public int getGameResult(){

        int gameResult=4;

        boolean hasZero=false;
        int rowSum=0;
        int columnSum=0;
        int diagonalSum=0;
        int anotherDiagonalSum=0;

        //Test Row
        for (int i=0;i<3;i++){
            rowSum= gameBoardArray[i][0]+ gameBoardArray[i][1]+ gameBoardArray[i][2];
            if (rowSum==15){
                return GAME_RESULT_FAIL;
            }else if (rowSum==3){
                return GAME_RESULT_WIN;
            }
        }

        //Test Column
        for (int i=0;i<3;i++){
            columnSum= gameBoardArray[0][i]+ gameBoardArray[1][i]+ gameBoardArray[2][i];
            if (columnSum==15){
                return GAME_RESULT_FAIL;
            }else if (columnSum==3){
                return GAME_RESULT_WIN;
            }
        }

        //test diagonalSum
        diagonalSum= gameBoardArray[0][0]+ gameBoardArray[1][1]+ gameBoardArray[2][2];
        anotherDiagonalSum= gameBoardArray[2][0]+ gameBoardArray[1][1]+ gameBoardArray[0][2];

        if (diagonalSum==3||anotherDiagonalSum==3){
            return GAME_RESULT_WIN;
        }else if (diagonalSum==15||anotherDiagonalSum==15){
            return GAME_RESULT_FAIL;
        }



        //check for draw
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (gameBoardArray[i][j]==0){
                    hasZero=true;
                }
            }
        }

        if (!hasZero){
            return GAME_RESULT_DRAW;
        }


        return gameResult;
    }

    public List getAvailableSlots(){

        List<Integer> list =new ArrayList<>();

        int flag=1;
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (gameBoardArray[i][j]==0){
                    list.add(flag);
                }
                flag++;
            }
        }

        //Log.d("Board","AvaliableSolts: "+list.toString());

        return list;
    }

}
