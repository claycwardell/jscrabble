package com.claycwardell.scrabble;


import java.util.ArrayList;

/**
 * Created by Clay on 2/18/17.
 */
public class Board {

    private final Integer width=15;
    private final Integer height=15;
    private Square[][] square_matrix =new Square[this.width][this.height];

    public Board() {
        for (int i=0; i<this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.square_matrix[i][j] = new Square(j, i);
            }
        }

//        for (int i=0; i<this.height; i++) {
//            for (int j = 0; j < this.width; j++) {
//                Square left = null;
//                Square top = null;
//                Square right = null;
//                Square bottom = null;
//
//                if (i>0) {
//                    top = this.square_matrix[i-1][j];
//                }
//                if (i < this.height-1) {
//                    bottom = this.square_matrix[i+1][j];
//                }
//                if (j > 0) {
//                    left = this.square_matrix[i][j-1];
//                }
//
//                if (j < this.width-1 ) {
//                    right = this.square_matrix[i][j+1];
//                }
//
//                this.square_matrix[i][j].setEdges(left, top, right, bottom);
//            }
//        }

    }

    public Square left(Square square) {
        if (square.getX() < 1) {
            throw new RuntimeException(String.format("No square to left of %s", square));
        }
        return this.getSquare(square.getX()-1, square.getY());
    }

    public Square right(Square square) {
        if (square.getX() > this.getWidth()-1) {
            throw new RuntimeException(String.format("No square to right of %s", square));
        }
        return this.getSquare(square.getX()+1, square.getY());
    }

    public Square above(Square square) {
        if (square.getY() < 1) {
            throw new RuntimeException(String.format("No square above %s", square));
        }
        return this.getSquare(square.getX(), square.getY()-1);

    }

    public Square below(Square square) {
        if (square.getY() > this.height-1) {
            throw new RuntimeException(String.format("No square below %s", square));
        }
        return this.getSquare(square.getX(), square.getY()+1);
    }



    public ArrayList<Square> getSquareArray() {
        ArrayList<Square> ret = new ArrayList<>();
        for (int i=0; i<this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                ret.add(this.square_matrix[i][j]);
            }
        }
        return ret;
    }

    public Integer getWidth() {
        return this.width;
    }

    public Integer getHeight() {
        return this.height;
    }

    public Board getRotatedBoard() {
        Board board = new Board();
        board.rotatedAndInjectMatrix(this.square_matrix);
        return board;
    }

    public void rotatedAndInjectMatrix(Square[][] square_matrix) {
        for (int i=0; i<square_matrix.length; i++) {
            for (int j = 0; j < square_matrix[i].length; j++) {
                this.square_matrix[i][j] = square_matrix[j][i].getRotatedCopy();
            }
        }
    }


    static Board fromLiteral(String str){
        str = str.replaceAll("\\s","");
        Board board = new Board();
        for (int i=0; i<str.length(); i++) {
            Character ch = str.charAt(i);
            if (ch == '-') {
                continue;
            }
            int y = i / board.getWidth();
            int x = i % board.getWidth();

            board.placeTile(x, y, new Tile(ch));
        }
        return board;


    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i<this.square_matrix.length; i++) {
            for (int j = 0; j< square_matrix[i].length; j++) {
                builder.append(square_matrix[i][j].toString());
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public Square getSquare(int x, int y) {
        return this.square_matrix[y][x];
    }

    public void placeTile(int x, int y, Tile tile) {
        Square square = this.getSquare(x, y);
        square.setTile(tile);
    }

//    public void removeTile(int x, int y) {
//
//    }


}
