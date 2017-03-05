package com.claycwardell.scrabble;

/**
 * Created by Clay on 2/18/17.
 */



public class Tile {
    private Character ch;
    private final GameConstants constants = new GameConstants();
    private final String allowed = constants.allowedCharacters;
    private Boolean isPlaced;
    private Boolean isBlank;

    public Tile(Character ch) {
        if (this.allowed.indexOf(ch) < 0) {
            throw new RuntimeException("Character not allowed");
        }
        this.ch = ch;
        this.isPlaced = false;
        if (ch == ' ') {
            this.isBlank = true;
        } else {
            this.isBlank = false;
        }

    }

    public void placeTile() {
        this.isPlaced = true;
    }

    public void unPlaceTile() {
        this.isPlaced = false;
    }

    public Boolean getIsPlaced() {
        return this.isPlaced;
    }

    public Integer getScore() {
        return (this.isBlank) ? 0 : this.constants.getLetterScore(this.ch);
    }

    public Character getCh() {
        return this.ch;
    }

    public String toString() {
        String chString = (this.isBlank) ? " " : this.getCh().toString();
        return " " + chString + " ";
    }

    public void morph(Character ch) {
        if (!this.isBlank && ch != this.ch) {
            throw new RuntimeException("Cannot morph a non-blank tile");
        }
        this.ch = ch;
    }

    public Boolean getIsBlank(){
        return this.isBlank;
    }
}
