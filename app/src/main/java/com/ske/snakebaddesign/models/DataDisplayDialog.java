package com.ske.snakebaddesign.models;

import android.content.DialogInterface;

/**
 * Created by PaiizZ on 3/17/2016 AD.
 */
public class DataDisplayDialog {
    private String title;
    private String message;
    private DialogInterface.OnClickListener listener;

    public DataDisplayDialog(String title, String message, DialogInterface.OnClickListener listener) {
        this.title = title;
        this.message = message;
        this.listener = listener;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public DialogInterface.OnClickListener getListener() {
        return listener;
    }
}