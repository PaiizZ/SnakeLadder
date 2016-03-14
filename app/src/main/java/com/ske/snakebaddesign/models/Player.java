package com.ske.snakebaddesign.models;

/**
 * Created by PaiizZ on 3/14/2016 AD.
 */
public class Player {
    private String name ;
    private int position ;

    public Player(String name){
        this.name = name ;
    }
    public void setPosition(int position){
        this.position = position ;
    }
    public int getPosition(){
        return this.position ;
    }
    public String getName(){
        return this.name ;
    }
}
