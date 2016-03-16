package com.ske.snakebaddesign.models;

import android.graphics.Color;

/**
 * Created by PaiizZ on 3/16/2016 AD.
 */
public class NormalSquare extends Square{
    NormalSquare(int number ) {
        super(number , Color.parseColor("#F25192"));

    }
    @Override
    public int effectPlayer(int position) {
        return position;
    }

    @Override
    public boolean checkEffect(int position) {
        return false;
    }
}
