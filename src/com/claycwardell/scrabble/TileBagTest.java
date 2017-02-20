package com.claycwardell.scrabble;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Clay on 2/20/17.
 */
public class TileBagTest {

    @Test
    public void testInit() {
        TileBag tileBag = TileBag.getInstance();
        TileBag tilebag1 = TileBag.getInstance();
        assertEquals(tileBag, tilebag1);
    }

    @Test
    public void testDraw() {
        TileBag tilebag = TileBag.getInstance();
        ArrayList<Tile> tiles = tilebag.drawTiles(4);
        assertEquals(tiles.size(), 4);
        System.out.println(tiles);
    }

}