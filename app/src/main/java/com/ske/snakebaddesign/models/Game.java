package com.ske.snakebaddesign.models;

import android.util.Log;

/**
 * Created by PaiizZ on 3/14/2016 AD.
 */
public class Game {
    private Board board ;
    private Player player1 ;
    private Player player2 ;
    private Die die ;
    private int turn ;

    public Game(){
        initComponents();
    }
    private void initComponents(){
        this.player1 = new Player("Pai");
        this.player2 = new Player("Gun");
        this.die = new Die();
        this.board = new Board(64);
        this.turn = 0 ;
    }
    public void resetGame(){

    }
    public Die getDie(){
        return this.die;
    }
    public void moveCurrentPiece(){

    }
    public void checkWin(){

    }
    private int adjustPosition(int current, int distance) {
        current = current + distance;
        int maxSquare = (int)Math.pow(board.getBoardSize(), 2) - 1;
        if(current > maxSquare) {
            current = maxSquare - (current - maxSquare);
        }
        return current;
    }
    public void  movePlayer(Player player,int distance){
        player.setPosition(adjustPosition(player.getPosition(),distance));
        Log.e(player.getName(), player.getPosition() + "");
    }
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Board getBoard() {
        return board;
    }

    public int getTurn() {
        return turn;
    }
    public void nextTurn(){
        this.turn++;
        Log.e("nexturn"," call ");
    }
    public void reset(){
        this.player1.setPosition(0);
        this.player2.setPosition(0);
        this.board.setBoardSize(8);
        this.turn = 0;
    }




}
