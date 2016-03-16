package com.ske.snakebaddesign.models;

/**
 * Created by PaiizZ on 3/14/2016 AD.
 */
public class Player {
    private String name ;
    private Piece piece ;
    private Die die ;
    public Player(String name){
        piece = new Piece();
        this.die = Die.getInstance();
        this.name = name ;
    }

    public Die getDie(){
        return this.die;
    }
    public int rollDie(){
        die.roll();
        return die.getPoint();
    }

    public Piece getPiece() {
        return this.piece;
    }

    public String getName(){
        return this.name ;

    }

}
