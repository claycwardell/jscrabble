package com.claycwardell.scrabble;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class BoardTest {

    private Board board;

    @Before
    public void before(){
        this.board = Board.fromLiteral("" +
                "---------------\n" +
                "---------------\n" +
                "---------------\n" +
                "---------------\n" +
                "---------------\n" +
                "------COOL-----\n" +
                "------R--------\n" +
                "-----TACKS-----\n" +
                "------B--------\n" +
                "------S--------\n" +
                "---------------\n" +
                "---------------\n" +
                "---------------\n" +
                "---------------\n" +
                "---------------"
        );
    }

    @After
    public void after(){
        System.out.println();
    }


    @Test
    public void testBoardInit(){
        Board board = new Board();
    }

    @Test
    public void testPrint() {
        assertEquals(new Board().toString(),
                " -  -  -  -  -  -  -  -  -  -  -  -  -  -  - \n" +
                " -  -  -  -  -  -  -  -  -  -  -  -  -  -  - \n" +
                " -  -  -  -  -  -  -  -  -  -  -  -  -  -  - \n" +
                " -  -  -  -  -  -  -  -  -  -  -  -  -  -  - \n" +
                " -  -  -  -  -  -  -  -  -  -  -  -  -  -  - \n" +
                " -  -  -  -  -  -  -  -  -  -  -  -  -  -  - \n" +
                " -  -  -  -  -  -  -  -  -  -  -  -  -  -  - \n" +
                " -  -  -  -  -  -  -  -  -  -  -  -  -  -  - \n" +
                " -  -  -  -  -  -  -  -  -  -  -  -  -  -  - \n" +
                " -  -  -  -  -  -  -  -  -  -  -  -  -  -  - \n" +
                " -  -  -  -  -  -  -  -  -  -  -  -  -  -  - \n" +
                " -  -  -  -  -  -  -  -  -  -  -  -  -  -  - \n" +
                " -  -  -  -  -  -  -  -  -  -  -  -  -  -  - \n" +
                " -  -  -  -  -  -  -  -  -  -  -  -  -  -  - \n" +
                " -  -  -  -  -  -  -  -  -  -  -  -  -  -  - \n"
        );
    }

//    @Test
//    public void testEdges() {
//        Board board = new Board();
//        Square middle = board.getSquare(5, 5);
//        Square leftEdge = board.getSquare(0, 5);
//        Square rightEdge = board.getSquare(14, 5);
//        Square topEdge = board.getSquare(5, 0);
//        Square bottomEdge = board.getSquare(5, 14);
//        Square left = middle.getLeft();
//        Square right = middle.getRight();
//        Square top = middle.getTop();
//        Square bottom = middle.getBottom();
//
//        assertEquals(left, board.getSquare(4, 5));
//        assertEquals(right, board.getSquare(6, 5));
//        assertEquals(top, board.getSquare(5,4));
//        assertEquals(bottom, board.getSquare(5, 6));
//
//        assertNull(leftEdge.getLeft());
//        assertNull(rightEdge.getRight());
//        assertNull(topEdge.getTop());
//        assertNull(bottomEdge.getBottom());
//
//    }

    @Test
    public void testFromLiteral() {

        System.out.print(this.board);

    }

    @Test
    public void testGetSquare() {

    }

    @Test
    public void testPlayOnSquare() {

        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile('C'));
        tiles.add(new Tile('A'));
        tiles.add(new Tile('T'));

        Player clay = new Player();
        System.out.println(clay.getTiles());
        this.board.checkAllSquares(clay.getTiles());
        System.out.println(this.board);

    }

    @Test
    public void testGetWordForSquare() {
        Square square = this.board.getSquare(7, 5);
        System.out.println(square);
        System.out.print(this.board.getWordFromSquare(square));
    }

    @Test
    public void testGetSquareArray() {
        assertEquals(this.board.getSquareArray().size(), 15*15);
    }

//    @Test
//    public void testRotate() {
//        System.out.print(this.board.getRotatedBoard());
//    }
}
