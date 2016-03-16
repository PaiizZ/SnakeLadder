package com.ske.snakebaddesign.models;

import android.content.DialogInterface;
import android.util.Log;

import java.util.Observable;
import java.util.Observer;

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
    public void decreaseTurn(){
        this.turn--;
    }


//    public void reset(){
//        this.player1.getPiece().setPosition(0);
//        this.player2.getPiece().setPosition(0);
//        this.board.setBoardSize(7);
//        this.turn = 0;
//    }

    public void resetGame() {
        this.player1.getPiece().setPosition(0);
        this.player2.getPiece().setPosition(0);
        //this.board.setBoardSize(7);
        this.turn = 0;
        setChanged();
        notifyObservers();
//        boardView.setBoardSize(game.getBoard().getBoardSize());
//        boardView.setP1Position(game.getPlayer1().getPiece().getPosition());
//        boardView.setP2Position(game.getPlayer2().getPiece().getPosition());
    }

    public void takeTurn() {

        if (getTurn() % 2 == 0) {
            final int value = getPlayer1().rollDie();
            String title = getPlayer1().getName() + " rolled a die";
            String msg = getPlayer1().getName() + " got " + value;
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    moveCurrentPiece(value);
                    dialog.dismiss();
                }
            };
            DataDialog d = new DataDialog(title, msg, listener);
            setChanged();
            notifyObservers(d);
            //displayDialog(title, msg, listener);
        } else {
            final int value = getPlayer2().rollDie();
            String title = getPlayer2().getName() + " rolled a die";
            String msg = getPlayer2().getName() + " got " + value;
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    moveCurrentPiece(value);
                    dialog.dismiss();
                }
            };
            DataDialog d = new DataDialog(title, msg, listener);
            setChanged();
            notifyObservers(d);
        }
    }

    public void moveCurrentPiece(int value) {
        //  Log.e("eiei","");
        if (getTurn() % 2 == 0) {
//            Log.e("In1",getPlayer1().getPiece().getPosition()+"");

            getPlayer1().getPiece().setPosition(adjustPosition( getPlayer1().getPiece().getPosition() , value));
//            boardView.setP1Position(game.getPlayer1().getPiece().getPosition());
//            textPlayerTurn.setText(game.getPlayer2().getName()+"'s Turn");
//            textPlayerTurn.setTextScaleX(2);
            setChanged();
            notifyObservers();
            Effect();
            checkWin();
            notifyObservers(getPlayer2().getName()+"'s Turn");

        } else {
            //        Log.e("In2",getPlayer2().getPiece().getPosition()+"");
            getPlayer2().getPiece().setPosition(adjustPosition( getPlayer2().getPiece().getPosition() , value));
//            boardView.setP2Position(game.getPlayer2().getPiece().getPosition());
//            textPlayerTurn.setText(game.getPlayer1().getName()+"'s Turn");
//            textPlayerTurn.setTextScaleX(2);
            setChanged();
            notifyObservers();
            Effect();
            checkWin();
            notifyObservers(getPlayer1().getName()+"'s Turn");
        }

        nextTurn(); // turn++
    }


    public void Effect(){
        //final int value = getEffect(getPlayer());
        String title = " Get move effect";
        String msg = "";
        //    String msg = getPlayer().getName()+"'s move to " + value;
        // decreaseTurn();

        if (checkEffect(getPlayer1())) {
            msg = getPlayer1().getName()+"'s move to " + getEffect(getPlayer1());
            getPlayer1().getPiece().setPosition(getEffect(getPlayer1()));

        } else if (checkEffect(getPlayer2())) {
            msg = getPlayer2().getName()+"'s move to " + getEffect(getPlayer2());
            getPlayer2().getPiece().setPosition(getEffect(getPlayer2()));

        }
        else {
            return;
        }
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //moveCurrentPiece(value);
                // getPlayer().getPiece().setPosition(getEffect(getPlayer()));
                dialog.dismiss();
            }
        };
        DataDialog d = new DataDialog(title,msg,listener);
        setChanged();
        notifyObservers(d);


//        String title = "Get Move Effect";
//        String msg = "";
//        OnClickListener listener = new OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//
//                dialog.dismiss();
//                boardView.setP1Position(game.getPlayer1().getPiece().getPosition());
//                boardView.setP2Position(game.getPlayer2().getPiece().getPosition());
//            }
//        };
//        if (game.checkEffect(game.getPlayer1())) {
//            msg = game.getPlayer1().getName()+"'s move to "+game.getEffect(game.getPlayer1());
//            game.getPlayer1().getPiece().setPosition(game.getEffect(game.getPlayer1()));
//
//        }
//
//        else if (game.checkEffect(game.getPlayer2())) {
//            msg = game.getPlayer2().getName()+"'s move to "+game.getEffect(game.getPlayer2());
//            game.getPlayer2().getPiece().setPosition(game.getEffect(game.getPlayer2()));
//
//        }
//
//        else {
//            return;
//        }
//        displayDialog(title, msg, listener);
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
        } else if (getPlayer2().getPiece().getPosition() == getBoard().getBoardSize() * getBoard().getBoardSize() - 1) {
            msg = getPlayer2().getName()+"'s won!";
        } else {
            return;
        }
        DataDialog d = new DataDialog(title,msg,listener);
        setChanged();
        notifyObservers(d);
    }



}
