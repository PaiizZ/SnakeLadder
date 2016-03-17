package com.ske.snakebaddesign.models;

import android.graphics.Color;

/**
 * Created by PaiizZ on 3/16/2016 AD.
 */
public class MoveToThirtyNineSquare extends Square {

    MoveToThirtyNineSquare(int number) {
        super(number, Color.parseColor("#F25192"));
        switch (number){
            case 23 : {
                super.setColorCell(Color.parseColor("#EE8707"));
                break;
            }
            case 39 : {
                super.setColorCell(Color.parseColor("#EE8707"));
                break;
            }
        }
    }

    @Override
    public int effectPlayer(int position) {
        return 39;
    }

    @Override
    public boolean checkEffect(int position) {
        if (position == 23) return true;
        else return false;
    }
}
