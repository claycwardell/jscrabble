package com.claycwardell.scrabble;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by Clay on 2/20/17.
 */
public class SquareTest {

    @Test
    public void testGetRotatedCopy() {
        Square square = new Square(3, 7);
        square.setTile(new Tile('C'));
        square.freeze();
        Square rotated = square.getRotatedCopy();
        assertEquals(rotated.getTile(), square.getTile());
        assertEquals(rotated.isFrozen(), square.isFrozen());
        assertEquals(new Integer(rotated.getX()), new Integer(7));
        assertEquals(new Integer(rotated.getY()), new Integer(3));
    }


}