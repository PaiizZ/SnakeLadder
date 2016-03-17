package com.ske.snakebaddesign.models;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by PaiizZ on 3/16/2016 AD.
 */
public class MoveToSevenSquare extends Square{
    MoveToSevenSquare(int number) {
        super(number, Color.parseColor("#F25192"));
        switch (number){
            case 2 : {
                super.setColorCell(Color.parseColor("#6FF251"));
                break;
            }
            case 7 : {
                super.setColorCell(Color.parseColor("#6FF251"));
                break;
            }
        }

    }

    @Override
    public int effectPlayer(int position) {
        return 7;
    }

    @Override
    public boolean checkEffect(int position) {
        if (position == 2) return true;
        else return false;
    }
}
