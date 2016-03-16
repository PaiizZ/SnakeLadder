package com.ske.snakebaddesign.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ske.snakebaddesign.R;
import com.ske.snakebaddesign.guis.BoardView;
import com.ske.snakebaddesign.models.Board;
import com.ske.snakebaddesign.models.Game;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private Game game ;

    private int boardSize;
    private int p1Position;
    private int p2Position;
    private int turn;

    private BoardView boardView;
    private Button buttonTakeTurn;
    private Button buttonRestart;
    private TextView textPlayerTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        resetGame();
        textPlayerTurn.setText(game.getPlayer1().getName() + "'s Turn");
        textPlayerTurn.setTextScaleX(2);
    }

    private void initComponents() {
        game = new Game();
        boardView = (BoardView) findViewById(R.id.board_view);
        buttonTakeTurn = (Button) findViewById(R.id.button_take_turn);
        buttonTakeTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeTurn();
            }
        });
        buttonRestart = (Button) findViewById(R.id.button_restart);
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
        boardView.setBoard(game.getBoard());
        textPlayerTurn = (TextView) findViewById(R.id.text_player_turn);
    }

    private void resetGame() {
        game.reset();
        boardView.setBoardSize(game.getBoard().getBoardSize());
        boardView.setP1Position(game.getPlayer1().getPiece().getPosition());
        boardView.setP2Position(game.getPlayer2().getPiece().getPosition());
    }

    private void takeTurn() {
        final int value = game.rollDie();
        String title = "You rolled a die";
        String msg = "You got " + value;
        OnClickListener listener = new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                moveCurrentPiece(value);
                dialog.dismiss();
            }
        };
        displayDialog(title, msg, listener);
    }

    private void moveCurrentPiece(int value) {

        if (game.getTurn() % 2 == 0) {
            game.getPlayer1().getPiece().setPosition(game.adjustPosition( game.getPlayer1().getPiece().getPosition() , value));
            boardView.setP1Position(game.getPlayer1().getPiece().getPosition());
            textPlayerTurn.setText(game.getPlayer2().getName()+"'s Turn");
            textPlayerTurn.setTextScaleX(2);
        } else {
            game.getPlayer2().getPiece().setPosition(game.adjustPosition( game.getPlayer2().getPiece().getPosition() , value));
            boardView.setP2Position(game.getPlayer2().getPiece().getPosition());
            textPlayerTurn.setText(game.getPlayer1().getName()+"'s Turn");
            textPlayerTurn.setTextScaleX(2);
        }
        Effect();
        checkWin();
        game.nextTurn(); // turn++
    }


    private void Effect(){
        String title = "Get Move Effect";
        String msg = "";
        OnClickListener listener = new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                boardView.setP1Position(game.getPlayer1().getPiece().getPosition());
                boardView.setP2Position(game.getPlayer2().getPiece().getPosition());
            }
        };
        if (game.checkEffect(game.getPlayer1())) {
            msg = game.getPlayer1().getName()+"'s move to "+game.getEffect(game.getPlayer1());
            game.getPlayer1().getPiece().setPosition(game.getEffect(game.getPlayer1()));

        }

        else if (game.checkEffect(game.getPlayer2())) {
            msg = game.getPlayer2().getName()+"'s move to "+game.getEffect(game.getPlayer2());
            game.getPlayer2().getPiece().setPosition(game.getEffect(game.getPlayer2()));

        }

        else {
            return;
        }
        displayDialog(title, msg, listener);
    }

    private void checkWin() {
        String title = "Game Over";
        String msg = "";
        OnClickListener listener = new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                resetGame();
                dialog.dismiss();
            }
        };
        if (game.getPlayer1().getPiece().getPosition() == game.getBoard().getBoardSize() * game.getBoard().getBoardSize() - 1) {
            msg = game.getPlayer1().getName()+"'s won!";
        } else if (game.getPlayer2().getPiece().getPosition() == game.getBoard().getBoardSize() * game.getBoard().getBoardSize() - 1) {
            msg = game.getPlayer2().getName()+"'s won!";
        } else {
            return;
        }
        displayDialog(title, msg, listener);
    }


    private void displayDialog(String title, String message, DialogInterface.OnClickListener listener) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", listener);
        alertDialog.show();
    }

}
