package com.claycwardell.scrabble;

import java.util.ArrayList;

/**
 * Created by Clay on 2/20/17.
 */
public class Player {

    private ArrayList<Tile> tiles;
    private final GameConstants gameConstants = new GameConstants();

    public Player() {
        this.tiles = TileBag.getInstance().drawTiles(this.gameConstants.tilesPerHand);
    }

}
