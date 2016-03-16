package com.ske.snakebaddesign.models;

import java.util.Random;

/**
 * Created by PaiizZ on 3/14/2016 AD.
 */
public class Die {

//    private final int minPoint = 1 ;
//    private final int maxPoint = 6 ;

    private int point ;
    private Random random ;

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
