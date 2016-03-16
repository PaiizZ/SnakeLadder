package com.ske.snakebaddesign.models;

import android.graphics.Color;

/**
 * Created by PaiizZ on 3/14/2016 AD.
 */
public abstract class Square implements Effective{

    private int number;
    private int colorCell ;
    Square(int number ,int colorCell ){
        this.number = number;
        this.colorCell = colorCell;
    }

    public int getNumber() {
        return number;
    }

    public int getColorCell() {

        return colorCell;
    }

    public void setColorCell(int colorCell) {
        this.colorCell = colorCell;
    }

}
