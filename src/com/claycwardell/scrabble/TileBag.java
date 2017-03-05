package com.claycwardell.scrabble;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Clay on 2/20/17.
 */
public class TileBag {

    private final GameConstants constants = new GameConstants();
    private ArrayList<Tile> tiles = new ArrayList<Tile>();
    private static TileBag instance = null;

    private TileBag() {
        for (HashMap.Entry<Character, Integer> entry : this.constants.getLetterScores().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                Character key = entry.getKey();
                this.tiles.add(new Tile(key));
            }
        }
        Collections.shuffle(this.tiles);
    }

    public static TileBag getInstance() {
        if (instance == null) {
            instance = new TileBag();
        }
        return instance;
    }

    public ArrayList<Tile> drawTiles(int num_to_draw) {

        ArrayList<Tile> ret = new ArrayList<Tile>();
        int limit = Math.min(num_to_draw, this.getTilesRemaining());

        for (int i=0; i<limit; i++) {
            ret.add(this.tiles.remove(this.tiles.size()-1));
        }
        return ret;
    }

    public int getTilesRemaining() {
        return this.tiles.size();
    }

}
