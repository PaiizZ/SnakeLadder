package com.ske.snakebaddesign.models;

import java.util.Random;

/**
 * Created by PaiizZ on 3/14/2016 AD.
 */
public class Die {

    private int point ;
    private Random random ;
    private static Die instance ;

    public static Die getInstance(){
        if (instance == null){
            instance = new Die();
        }
        return instance ;
    }
    public Die(){
        random = new Random();
    }
    public void roll(){
        this.point = random.nextInt(6)+1 ;
    }
    public int getPoint(){
        return this.point;
    }
}
