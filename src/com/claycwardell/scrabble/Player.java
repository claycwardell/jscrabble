package com.claycwardell.scrabble;

import java.util.ArrayList;

/**
 * Created by Clay on 2/20/17.
 */
public class Player {

    private ArrayList<Tile> tiles;
    private final GameConstants gameConstants = new GameConstants();
    private Integer score;

    public Player() {
        this.tiles = TileBag.getInstance().drawTiles(this.gameConstants.tilesPerHand);
        this.score = 0;
    }

    public ArrayList<Tile> getTiles() {
        return this.tiles;
    }

    public Integer getScore() {
        return this.score;
    }

    public Integer incrScore(Integer incr) {
        this.score += incr;
        return this.score;
    }

}
