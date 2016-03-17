package com.ske.snakebaddesign.models;

import android.graphics.Color;

/**
 * Created by PaiizZ on 3/16/2016 AD.
 */
public class MoveToTwentySevenSquare extends Square {
    MoveToTwentySevenSquare(int number) {
        super(number, Color.parseColor("#F25192"));
        switch (number) {
            case 18: {
                super.setColorCell(Color.parseColor("#E9F42D"));
                break;
            }
            case 27: {
                super.setColorCell(Color.parseColor("#E9F42D"));
                break;
            }
        }
    }

    @Override
    public int effectPlayer(int position) {
        return 27;
    }

    @Override
    public boolean checkEffect(int position) {
        if (position == 18) return true;
        else return false;
    }
}
