package com.ske.snakebaddesign.models;

import android.graphics.Color;

/**
 * Created by PaiizZ on 3/16/2016 AD.
 */
public class MoveToFortyTwo extends Square  {
    public MoveToFortyTwo(int number) {
        super(number, Color.parseColor("#F25192"));
        switch (number){
            case 37 : {
                super.setColorCell(Color.parseColor("#EE0713"));
                break;
            }
            case 42 : {
                super.setColorCell(Color.parseColor("#EE0713"));
                break;
            }
        }
    }

    @Override
    public int effectPlayer(int position) {
        return 42;
    }

    @Override
    public boolean checkEffect(int position) {
        if (position == 37) return true;
        else return false;
    }
}
