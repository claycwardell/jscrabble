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
    public void testGetBestWord() {

        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile('C'));
        tiles.add(new Tile('A'));
        tiles.add(new Tile('T'));

        Player clay = new Player("Clay");
        System.out.println(clay.getTiles());
        Word word = this.board.getBestWord(clay.getTiles());
        System.out.println(this.board);
        word.play();
        System.out.println(this.board);
        System.out.println(String.format("%s -- %d, %d", word, word.getScore(), word.getCombinedScore()));

    }

    @Test
    public void testFirstPlay() {
        Player clay = new Player("Clay");
        this.board = new Board();
        System.out.println(clay.getTiles());
        Word word = this.board.getBestWord(clay.getTiles());
        System.out.println(this.board);
        word.play();
        System.out.println(this.board);
        System.out.println(String.format("%s -- %d, %d", word, word.getScore(), word.getCombinedScore()));
    }

    @Test
    public void testGetWordForSquare() {
        Square square = this.board.getSquare(7, 5);
        System.out.println(square);
        System.out.print(this.board.getWordFromSquare(square, true));
    }

    @Test
    public void testValidateAllSquares() {
        System.out.print(this.board);
        this.board.validateWholeBoard(false);
    }

    @Test
    public void testGetSquareArray() {
        assertEquals(this.board.getSquareArray().size(), 15*15);
    }

    @Test
    public void testRotateBoard(){
        System.out.print(this.board+"\n\n");
        this.board.rotateBoard();
        System.out.print(this.board+"\n\n");
        this.board.rotateBoard();
        System.out.print(this.board);
    }

    @Test
    public void testGetRotated() {
        System.out.print(this.board + "\n\n");
        System.out.print(this.board.getRotatedBoard());
    }
}
