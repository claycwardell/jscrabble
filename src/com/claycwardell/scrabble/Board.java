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
    }

    public Square[][] getMatrix() {
        return this.square_matrix;
    }

    public Square left(Square square) {
        if (square.getX() <= 1) {
//            throw new RuntimeException(String.format("No square to left of %s", square));
            return null;
        }
        return this.getSquare(square.getX()-1, square.getY());
    }

    public Square right(Square square) {
        if (square.getX() >= this.getWidth()-1) {
//            throw new RuntimeException(String.format("No square to right of %s", square));
            return null;
        }
        return this.getSquare(square.getX()+1, square.getY());
    }

    public Square above(Square square) {
        if (square.getY() <= 1) {
//            throw new RuntimeException(String.format("No square above %s", square));
            return null;
        }
        return this.getSquare(square.getX(), square.getY()-1);

    }

    public Square below(Square square) {
        if (square.getY() >= this.height-1) {
//            throw new RuntimeException(String.format("No square below %s", square));
            return null;
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
        board.rotatedAndInjectMatrix(this.square_matrix, false);
        return board;
    }

    public void rotateBoard() {
        Board board = new Board();
        board.rotatedAndInjectMatrix(this.square_matrix, true);
        this.square_matrix = board.getMatrix();
    }

    public void rotatedAndInjectMatrix(Square[][] square_matrix, Boolean rotateInPlace) {
        for (int i=0; i<square_matrix.length; i++) {
            for (int j = 0; j < square_matrix[i].length; j++) {
                Square otherSquare = square_matrix[j][i];
                if (rotateInPlace) {
                    otherSquare.rotate();

                } else {
                    otherSquare = otherSquare.getRotatedCopy();
                }
                this.square_matrix[i][j] = otherSquare;
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

            board.playTile(x, y, new Tile(ch));
        }
        return board;

    }

    public Boolean isFirstTurn() {
        for (Square square : this.getSquareArray()) {
            if (square.isFrozen()) {
                return false;
            }
        }
        return true;
    }

    public Word getWordFromSquare(Square square, Boolean allowMiddleOut) {
        if (square.getTile() == null) {
            return null;
        }

        if (!allowMiddleOut && !(this.left(square) == null || this.left(square).getTile() == null)) {
            return null;
        }

        Square pointer = square;
        ArrayList<Square> word = new ArrayList<>();

        while (pointer != null && pointer.getTile() != null) {
            word.add(0, pointer);
            pointer = this.left(pointer);
        }

        if (square.getX() < this.width-1) {
            pointer = this.right(square);

            while (pointer != null && pointer.getTile() != null) {
                word.add(pointer);
                pointer = this.right(pointer);
            }
        }

        if (this.isFirstTurn()) {
            return new FirstWord(word);
        } else {
            return new Word(word);
        }
    }

    public Word getBestWord(ArrayList<Tile> tiles) {
        ArrayList<Word> legalWordList = new ArrayList<Word>();
        for (Square square : this.getSquareArray()) {
            this.playOnSquare(square, tiles, legalWordList);
        }

        this.rotateBoard();

        for (Square square : this.getSquareArray()) {
            this.playOnSquare(square, tiles, legalWordList);
        }

        this.rotateBoard();

        Integer highest = 0;
        Word highestWord = null;
        for (Word word : legalWordList) {
            if (word.getCombinedScore() > highest) {
                highest = word.getCombinedScore();
                highestWord = word;
            }
        }

        return highestWord;

    }

    public Integer validateWholeBoard(Boolean rotated) {
        Integer score = 0;
        for (Square square : this.getSquareArray()) {
            Word word = this.getWordFromSquare(square, false);
            if (word != null) {
                if (!word.wordIsValid()) {
                    score = -1;
                    break;
                } else {
//                    System.out.printf("%s -- %d\n", word, word.getScore());
                    score += word.getScore();
                }
            }

        }
        if (score >= 0 && !rotated) {
            Integer new_score = this.getRotatedBoard().validateWholeBoard(true);
            if (new_score < 0) {
                score = -1;
            } else {
                score += new_score;
            }
        }
        return score;

    }

    public void playOnSquare(Square square, ArrayList<Tile> tiles, ArrayList<Word> legalWordList) {
        if (square.getTile() != null) {
            return;
        }

        this.buildFromSquare(legalWordList, square, tiles);
//        Board rotated = this.getRotatedBoard();
//        rotated.buildFromSquare(legalWordList, rotated.getSquare(square.getY(), square.getX()), tiles);



//        System.out.println(legalWordList);

    }

    private void buildFromSquare(ArrayList<Word> legalMoveList, Square square, ArrayList<Tile> tiles) {
        if (square == null) {
            return;
        }

        if (square.getTile() != null) {
            this.buildFromSquare(legalMoveList, this.right(square), tiles);
        }


        for (int i=0; i<tiles.size(); i++) {
            if (square.getTile() != null) {
                return;
            }
            Tile currentTile = tiles.get(i);
            square.setTile(currentTile);
            Word currentWord = this.getWordFromSquare(square, true);
            if (currentWord.hasNode()){
                if (currentWord.wordIsValidToPlay()) {

                    Integer score = this.validateWholeBoard(false);
                    if (score > 0) {

                        currentWord.setCombinedScore(score);
                        legalMoveList.add(currentWord);
                    }
                }
                ArrayList<Tile> unplacedTiles = new ArrayList<Tile>();
                for (Tile tile : tiles) {
                    if (!tile.getIsPlaced()) {
                        unplacedTiles.add(tile);
                    }
                }
                this.buildFromSquare(legalMoveList, this.right(square), unplacedTiles);
            }
            if (!square.isFrozen()) {
                square.popTile();
            }

        }


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

    public void playTile(int x, int y, Tile tile) {
        Square square = this.getSquare(x, y);
        square.setTile(tile);
        square.freeze();
    }



}
