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
        this.board = new Board(7);
        this.turn = 0 ;
    }

    public Die getDie(){
        return this.die;
    }


    public int adjustPosition(int current, int distance) {
        current = current + distance;
        int maxSquare = (int)(Math.pow(board.getBoardSize(), 2)) - 1;
        if(current > maxSquare) {
            current = maxSquare - (current - maxSquare);
        }
        return current;
    }


    public int rollDie(){
        die.roll();
        return die.getPoint();
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public Player getPlayer2() { return this.player2; }

    public Board getBoard() {
        return this.board;
    }

    public int getTurn() {
        return this.turn;
    }
    public boolean checkEffect(Player player){
        return board.getSquare(player.getPiece().getPosition()).checkEffect(player.getPiece().getPosition());
    }
    public int getEffect(Player player){
        return  board.getSquare(player.getPiece().getPosition()).effectPlayer(player.getPiece().getPosition());
    }

    public void nextTurn(){
        this.turn++;
    }

    public void reset(){
        this.player1.getPiece().setPosition(0);
        this.player2.getPiece().setPosition(0);
        this.board.setBoardSize(7);
        this.turn = 0;
    }




}
