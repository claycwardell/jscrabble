package com.claycwardell.scrabble;

import java.util.ArrayList;

/**
 * Created by Clay on 2/20/17.
 */
public class Word {
    private ArrayList<Square> squares = new ArrayList<>();
    private ArrayList<Tile> tiles =  new ArrayList<>();
    private final TrieRoot root = TrieRoot.getInstance();

    public Word(ArrayList<Square> squares) {
        for (Square square: squares) {
            if (square.getTile() == null) {
                throw new RuntimeException("Cannot build word that includes empty squares");
            }
            this.squares.add(square);
            this.tiles.add(square.getTile());
        }
    }

//    public Integer getScore() {
//        Integer score = 0;
//    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Tile tile : tiles) {
            builder.append(tile.getCh());
        }
        return builder.toString();
    }

    private TrieNode getNode(){
        return this.root.getNodeForString(this.toString());
    }

    public Boolean hasNode() {
        TrieNode node = this.getNode();
        return (node != null);
    }

    public Boolean wordIsValid() {
        TrieNode node = this.getNode();
        if (node == null){
            return false;
        }

        if (!node.getIsTerminal()) {
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
}
