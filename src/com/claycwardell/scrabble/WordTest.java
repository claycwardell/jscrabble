package com.claycwardell.scrabble;

import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Clay on 2/20/17.
 */
public class WordTest {

    private Word word;

    @Before
    public void before() {
        Square square_c = new Square(2,3);
        square_c.setTile(new Tile('C'));
        Square square_a = new Square(3,3);
        square_a.setTile(new Tile('A'));
        Square square_t = new Square(4,3);
        square_t.setTile(new Tile('T'));
        ArrayList<Square> squares = new ArrayList<Square>();
        squares.add(square_c);
        squares.add(square_a);
        squares.add(square_t);
        this.word = new Word(squares);

    }

    @Test
    public void testToString() {
        assertEquals(this.word.toString(), "CAT");
    }

    @Test
    public void testLookup() {
        assertTrue(this.word.hasNode());
    }



}