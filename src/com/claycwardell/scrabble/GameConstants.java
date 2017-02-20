package com.claycwardell.scrabble;

import java.util.HashMap;

/**
 * Created by Clay on 2/20/17.
 */
public class GameConstants {
    private HashMap<Character, Integer> letterScores = new HashMap<Character, Integer>();
    private HashMap<Character, Integer> letterCounts = new HashMap<Character, Integer>();
    public final Integer tilesPerHand = 7;
    public final Integer playersPerGame = 2;


    public GameConstants() {

        letterScores.put("E".charAt(0) , 1);
        letterScores.put("A".charAt(0) , 1);
        letterScores.put("I".charAt(0) , 1);
        letterScores.put("O".charAt(0) , 1);
        letterScores.put("N".charAt(0) , 1);
        letterScores.put("R".charAt(0) , 1);
        letterScores.put("T".charAt(0) , 1);
        letterScores.put("L".charAt(0) , 1);
        letterScores.put("S".charAt(0) , 1);
        letterScores.put("U".charAt(0) , 1);
        letterScores.put("D".charAt(0) , 2);
        letterScores.put("G".charAt(0) , 2);
        letterScores.put("B".charAt(0) , 3);
        letterScores.put("C".charAt(0) , 3);
        letterScores.put("M".charAt(0) , 3);
        letterScores.put("P".charAt(0) , 3);
        letterScores.put("F".charAt(0) , 4);
        letterScores.put("H".charAt(0) , 4);
        letterScores.put("V".charAt(0) , 4);
        letterScores.put("W".charAt(0) , 4);
        letterScores.put("Y".charAt(0) , 4);
        letterScores.put("K".charAt(0) , 5);
        letterScores.put("J".charAt(0) , 8);
        letterScores.put("X".charAt(0) , 8);
        letterScores.put("Q".charAt(0) , 10);
        letterScores.put("Z".charAt(0) , 10);
        letterScores.put(" ".charAt(0) , 0);

        letterCounts.put("E".charAt(0) , 12);
        letterCounts.put("A".charAt(0) , 9);
        letterCounts.put("I".charAt(0) , 9);
        letterCounts.put("O".charAt(0) , 8);
        letterCounts.put("N".charAt(0) , 6);
        letterCounts.put("R".charAt(0) , 6);
        letterCounts.put("T".charAt(0) , 6);
        letterCounts.put("L".charAt(0) , 4);
        letterCounts.put("S".charAt(0) , 4);
        letterCounts.put("U".charAt(0) , 4);
        letterCounts.put("D".charAt(0) , 4);
        letterCounts.put("G".charAt(0) , 3);
        letterCounts.put("B".charAt(0) , 2);
        letterCounts.put("C".charAt(0) , 2);
        letterCounts.put("M".charAt(0) , 2);
        letterCounts.put("P".charAt(0) , 2);
        letterCounts.put("F".charAt(0) , 2);
        letterCounts.put("H".charAt(0) , 2);
        letterCounts.put("V".charAt(0) , 2);
        letterCounts.put("W".charAt(0) , 2);
        letterCounts.put("Y".charAt(0) , 2);
        letterCounts.put("K".charAt(0) , 1);
        letterCounts.put("J".charAt(0) , 1);
        letterCounts.put("X".charAt(0) , 1);
        letterCounts.put("Q".charAt(0) , 1);
        letterCounts.put("Z".charAt(0) , 1);
        letterCounts.put(" ".charAt(0) , 2);

    }

    public Integer getLetterScore(Character ch) {
        return this.letterScores.get(ch);
    }

    public Integer getLetterCount(Character ch) {
        return this.letterCounts.get(ch);
    }

    public HashMap<Character, Integer> getLetterScores() {
        // Do not return the actual, private data structure -- copy it
        return new HashMap<Character, Integer>(this.letterCounts);
    }

}
