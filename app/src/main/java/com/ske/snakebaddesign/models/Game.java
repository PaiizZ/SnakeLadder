package com.ske.snakebaddesign.models;

import android.content.DialogInterface;

import java.util.Observable;

/**
 * Created by PaiizZ on 3/14/2016 AD.
 */
public class Game extends Observable{
    private Board board ;
    private Player player1 ;
    private Player player2 ;

    private int turn ;

    public Game(){
        initComponents();
    }
    private void initComponents(){
        this.player1 = new Player("Pai");
        this.player2 = new Player("Gun");
        this.board = new Board(7);
        this.turn = 0 ;
    }

    public int adjustPosition(int current, int distance) {
        current = current + distance;
        int maxSquare = (int)(Math.pow(board.getBoardSize(), 2)) - 1;
        if(current > maxSquare) {
            current = maxSquare - (current - maxSquare);
        }
        return current;
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

    public void resetGame() {
        this.player1.getPiece().setPosition(0);
        this.player2.getPiece().setPosition(0);
        this.turn = 0;
        setChanged();
        notifyObservers();
        setChanged();
        notifyObservers(getPlayer1().getName() + "'s Turn");

    }

    public void takeTurn() {
        if (getTurn() % 2 == 0) {
            final int value = getPlayer1().rollDie();
            String title = getPlayer1().getName() + "'s rolled a die";
            String msg = getPlayer1().getName() + "'s got " + value;
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    moveCurrentPiece(value);
                }
            };
            DataDisplayDialog d = new DataDisplayDialog(title, msg, listener);
            setChanged();
            notifyObservers(d);
        } else {
            final int value = getPlayer2().rollDie();
            String title = getPlayer2().getName() + "'s rolled a die";
            String msg = getPlayer2().getName() + "'s got " + value;
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    moveCurrentPiece(value);
                }
            };
            DataDisplayDialog d = new DataDisplayDialog(title, msg, listener);
            setChanged();
            notifyObservers(d);
        }
    }

    public void moveCurrentPiece(int value) {
        if (getTurn() % 2 == 0) {
            getPlayer1().getPiece().setPosition(adjustPosition(getPlayer1().getPiece().getPosition(), value));
            setChanged();
            notifyObservers();
            Effect();
            checkWin();
            setChanged();
            notifyObservers(getPlayer2().getName() + "'s Turn");

        } else {
            getPlayer2().getPiece().setPosition(adjustPosition( getPlayer2().getPiece().getPosition() , value));
            setChanged();
            notifyObservers();
            Effect();
            checkWin();
            setChanged();
            notifyObservers(getPlayer1().getName() + "'s Turn");
        }
        nextTurn(); // turn++
    }

    public void Effect(){
        String title = " Get move effect";
        String msg = "";

        if (checkEffect(getPlayer1())) {
            msg = getPlayer1().getName()+"'s move to " + getEffect(getPlayer1());

            getPlayer1().getPiece().setPosition(getEffect(getPlayer1()));
            setChanged();
            notifyObservers();

        } else if (checkEffect(getPlayer2())) {
            msg = getPlayer2().getName()+"'s move to " + getEffect(getPlayer2());

            getPlayer2().getPiece().setPosition(getEffect(getPlayer2()));
            setChanged();
            notifyObservers();

        }
        else {
            return;
        }
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
        DataDisplayDialog d = new DataDisplayDialog(title,msg,listener);
        setChanged();
        notifyObservers(d);

    }

    public void checkWin() {
        String title = "Game Over";
        String msg = "";
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                resetGame();
                dialog.dismiss();
            }
        };
        if (getPlayer1().getPiece().getPosition() == getBoard().getBoardSize() * getBoard().getBoardSize() - 1) {
            msg = getPlayer1().getName()+"'s won!";
        }
        else if (getPlayer2().getPiece().getPosition() == getBoard().getBoardSize() * getBoard().getBoardSize() - 1) {
            msg = getPlayer2().getName()+"'s won!";
        }
        else {
            return;
        }
        DataDisplayDialog d = new DataDisplayDialog(title,msg,listener);
        setChanged();
        notifyObservers(d);
    }



}
