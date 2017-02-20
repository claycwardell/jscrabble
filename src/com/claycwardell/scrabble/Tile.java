package com.claycwardell.scrabble;

/**
 * Created by Clay on 2/18/17.
 */



public class Tile {
    private Character ch;
    private final GameConstants constants = new GameConstants();
    private final String allowed = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
    private Boolean isPlaced;

    public Tile(Character ch) {
        if (this.allowed.indexOf(ch) < 0) {
            throw new RuntimeException("Character not allowed");
        }
        this.ch = ch;
        this.isPlaced = false;

    }

    public void placeTile() {
        this.isPlaced = true;
    }

    public void unPlaceTile() {
        this.isPlaced = false;
    }

    public Integer getScore() {
        return this.constants.getLetterScore(this.ch);
    }

    public Character getCh() {
        return this.ch;
    }

    public String toString() {
        return " " + this.getCh().toString() + " ";
    }
}
