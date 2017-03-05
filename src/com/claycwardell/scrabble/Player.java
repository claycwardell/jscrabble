package com.claycwardell.scrabble;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * Created by Clay on 2/20/17.
 */
public class Player {

    private ArrayList<Tile> tiles;
    private final GameConstants gameConstants = new GameConstants();
    private Integer score;
    private String name;

    public Player(String name) {
        this.name = name;
        this.tiles = new ArrayList<Tile>();
        this.score = 0;
        this.replaceTiles();
    }

    public void replaceTiles() {
        Integer toDraw = this.gameConstants.tilesPerHand - this.tiles.size();
        this.tiles.addAll(TileBag.getInstance().drawTiles(toDraw));
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

    public void play(Board board) {
        try {
            sleep(1000);
        } catch (InterruptedException ie) {
            System.out.println("interrupted");
        }
        System.out.println(board);
        Word word = board.getBestWord(this.tiles);
        if (word != null) {
            this.incrScore(word.getCombinedScore());
            word.play();
            for (Tile tile : word.getTiles()) {
                if (this.tiles.contains(tile)) {
                    this.tiles.remove(tile);
                }
            }
            this.replaceTiles();
            System.out.printf("Tiles remaining: %d\n", TileBag.getInstance().getTilesRemaining());
        }
        System.out.println(this);

    }

    public String toString() {
        return String.format("%s - %s - %d", this.name, this.tiles, this.getScore());
    }

}
