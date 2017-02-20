package com.claycwardell.scrabble;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * Created by Clay on 2/20/17.
 */
public class TileTest {

    private Tile tile;

    @Before
    public void before() {
        this.tile = new Tile('C');
    }

    @Test
    public void testInit() {
        System.out.println(this.tile);

    }

    @Test
    public void testGetScore() {
        assertEquals(this.tile.getScore(), new Integer(3));
    }

    @Test
    public void testToString() {
        assertEquals(this.tile.toString(), " C ");
    }


}