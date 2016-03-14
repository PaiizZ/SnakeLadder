package com.ske.snakebaddesign.models;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PaiizZ on 3/14/2016 AD.
 */
public class Board {
    private View view ;
    private int boardSize;
    private List<Square> squareList;

    public Board(int boardSize){
        this.boardSize = boardSize ;
        squareList = new ArrayList<Square>();
        for (int i = 0 ; i < Math.pow(boardSize,2) ; i++ ){
            squareList.add(new Square(i));
        }
    }

    public void setBoardSize(int boardSize){
        this.boardSize = boardSize ;
    }
    public int getBoardSize(){
        return this.boardSize ;
    }
}
