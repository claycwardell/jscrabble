package com.claycwardell.scrabble;

import java.util.ArrayList;

/**
 * Created by Clay on 2/20/17.
 */
public class Word {
    protected ArrayList<Square> squares = new ArrayList<>();
    protected ArrayList<Tile> tiles =  new ArrayList<>();
    private final TrieRoot root = TrieRoot.getInstance();
    private Integer combinedScore = 0;

    public Word(ArrayList<Square> squares) {
        for (Square square: squares) {
            if (square.getTile() == null) {
                throw new RuntimeException("Cannot build word that includes empty squares");
            }
            this.squares.add(square);
            this.tiles.add(square.getTile());
        }
    }

    public Integer getScore() {
        Integer score = 0;
        Integer wordMult = 1;
        if (!this.isNewlyPlayed() || this.length() == 1) {
            return score;
        }

        for (Square square : this.squares) {
            score += square.getScore();
            if (!square.isFrozen()) {
                wordMult *= square.getWordMult();
            }
        }

        return score*wordMult;

    }

    public void setCombinedScore(Integer combinedScore) {
        this.combinedScore = combinedScore;
    }

    public Integer getCombinedScore() {
        return this.combinedScore;
    }

    public Boolean isNewlyPlayed() {
        if (this.length() < 2) {
            return false;
        }

        Boolean have_frozen = false;
        Boolean have_not_frozen = false;

        for (Square square : this.squares) {
            if (square.isFrozen()) {
                have_frozen = true;
            }
            if (!square.isFrozen()) {
                have_not_frozen = true;
            }

        }

        return (have_frozen && have_not_frozen);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Tile tile : tiles) {
            builder.append(tile.getCh());
        }
        return builder.toString();
    }

    public Integer length() {
        return this.squares.size();
    }

    private TrieNode getNode(){
        return this.root.getNodeForString(this.toString());
    }

    public Boolean hasNode() {
        TrieNode node = this.getNode();
        return (node != null);
    }

    public Boolean wordIsValidToPlay() {
        return (this.wordIsValid() && this.isNewlyPlayed());
    }

    public Boolean wordIsValid() {
        if (this.length() == 1) {
            return true;
        }
        TrieNode node = this.getNode();
        if (node == null){
            return false;
        }

        if (!node.getIsTerminal()) {
            return false;
        }

        return true;

    }

    public ArrayList<Tile> getTiles(){
        return this.tiles;
    }

    public void play() {
        for (int i=0; i < this.squares.size(); i++){
            Square square = this.squares.get(i);
            Tile tile = this.tiles.get(i);
            if (!square.isFrozen()) {
                square.playTile(tile);
            }
        }
    }
}


class FirstWord extends Word {
    public FirstWord(ArrayList<Square> squares) {
        super(squares);
    }

    public Boolean wordIsValidToPlay() {
        Boolean ret = false;
        for (Square square : this.squares) {
            if (square.getX() == 7 && square.getY() == 7) {
                ret = true;
            }
        }
        return (ret && super.wordIsValidToPlay());

    }

    public Boolean isNewlyPlayed() {
        return true;
    }
}
