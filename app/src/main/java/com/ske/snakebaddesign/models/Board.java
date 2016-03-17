package com.ske.snakebaddesign.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PaiizZ on 3/14/2016 AD.
 */
public class Board {

    private int boardSize;
    private List<Square> squareList;
    private int arr[] = new int[] {2,7,18,27,23,39,37,42};
    public Board(int boardSize){
        this.boardSize = boardSize ;
        squareList = new ArrayList<Square>();

        for(int i = 0 ; i < boardSize*boardSize; i++){
                if (i == arr[0] || i == arr[1]){
                    squareList.add(new MoveToSevenSquare(i));
                }
                else if (i == arr[2] || i == arr[3]){
                    squareList.add(new MoveToTwentySevenSquare(i));
                }
                else if (i == arr[4] || i == arr[5]){
                    squareList.add(new MoveToThirtyNineSquare(i));
                }
                else if (i == arr[6] || i == arr[7]){
                    squareList.add(new MoveToFortyTwoSquare(i));
                }
                else{
                    squareList.add(new NormalSquare(i));
                }
        }
    }
    public Square getSquare(int i){
        return squareList.get(i);
    }
    public void setBoardSize(int boardSize){
        this.boardSize = boardSize ;
    }
    public int getBoardSize(){
        return this.boardSize ;
    }
}
