package com.claycwardell.scrabble;

import java.util.ArrayList;

/**
 * Created by Clay on 2/18/17.
 */
public class Square {
    private Tile tile;
    private Boolean frozen;
    private Integer x;
    private Integer y;

    private Square left;
    private Square top;
    private Square right;
    private Square bottom;

    public String toString() {
        if (this.tile == null) {
            return " - ";
        }
        return this.tile.toString();
    }

    public Square(Integer x, Integer y) {
        this.frozen = false;
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return this.x;
    }

    public Integer getY() {
        return this.y;
    }

    public Boolean isFrozen(){
        return this.frozen;
    }

    public void freeze() {
        if (this.tile == null) {
            throw new RuntimeException("Cannot freeze an empty square");
        }
        this.frozen = true;
    }

    public Square getRotatedCopy() {
        Square square = new Square(this.y, this.x);
        if (this.tile != null){
            square.setTile(this.tile);
        }

        if (this.frozen){
            square.freeze();
        }
        return square;

    }

//    public void setEdges(Square left, Square top, Square right, Square bottom) {
//        if (this.left != null || this.right != null || this.top != null || this.bottom != null) {
//            throw new RuntimeException("Can only set edges for a square once");
//        }
//        this.left = left;
//        this.top = top;
//        this.right = right;
//        this.bottom = bottom;
//    }
//
//    public Square getLeft(){
//        return this.left;
//    }
//
//    public Square getRight(){
//        return this.right;
//    }
//
//    public Square getTop(){
//        return this.top;
//    }
//
//    public Square getBottom(){
//        return this.bottom;
//    }


    public void setTile(Tile tile) {
        if (this.tile != null){
            throw new RuntimeException("Square is taken!");
        }
        this.tile = tile;
        tile.placeTile();
    }

    public Tile getTile() {
        return this.tile;
    }

    public Tile popTile() {
        if (this.frozen) {
            throw new RuntimeException("Cannot pop a frozen tile");
        }
        Tile tile = this.tile;
        this.tile = null;
        tile.unPlaceTile();
        return tile;
    }

}
