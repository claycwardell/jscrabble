package com.claycwardell.scrabble;

/**
 * Created by Clay on 2/18/17.
 */

public class Scrabble {
    public static void main(String[] args) {
        Board board = new Board();
        TileBag tilebag = TileBag.getInstance();
        System.out.printf("Tiles remaining: %d\n", tilebag.getTilesRemaining());
        Player clay = new Player("Clay");
        Player sarah = new Player("Sarah");
        while (TileBag.getInstance().getTilesRemaining() > 0) {
            clay.play(board);
            sarah.play(board);

        }


    }
}
