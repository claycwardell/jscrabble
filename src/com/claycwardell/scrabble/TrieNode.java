package com.claycwardell.scrabble;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class TrieNode {
    private Character val;
    private HashMap<Character, TrieNode> nextNodeMap;
    private Boolean isTerminal;
    private TrieNode parent;

    public TrieNode(Character ch, TrieNode parent) {
        this.val = ch;
        this.isTerminal = false;
        this.nextNodeMap = new HashMap<Character, TrieNode>();
        this.parent = parent;
    }

    public Character getVal(){
        return val;
    }

    private void setIsTerminal(Boolean isTerminal) {
        this.isTerminal = isTerminal;
    }

    public Boolean getIsTerminal(){
        return this.isTerminal;
    }

    private void addNode(TrieNode node) {
        this.nextNodeMap.put(node.getVal(), node);

    }

    private TrieNode getNode(Character ch){
        return this.nextNodeMap.get(ch);
    }

    public void loadString(String str){
        str = str.toUpperCase();
        Character firstCharacter = TrieNode.getFirstcharacter(str);
        String restOfString = TrieNode.getRestOfString(str);

        TrieNode nextNode = this.getNode(firstCharacter);

        if (nextNode == null) {
            nextNode = new TrieNode(firstCharacter, this);
            this.addNode(nextNode);
        }

        if (restOfString != null) {
            nextNode.loadString(restOfString);
        } else {
            nextNode.setIsTerminal(true);
        }

    }

    public TrieNode getNodeForString(String str){
        if (str == null) {
            return this;
        }
        str = str.toUpperCase();
        Character firstCharacter = TrieNode.getFirstcharacter(str);
        String restOfString = TrieNode.getRestOfString(str);

        TrieNode nextNode = this.getNode(firstCharacter);
        if (nextNode == null) {
            return null;
        }

        return nextNode.getNodeForString(restOfString);

    }

    public String unwind() {
        TrieNode node = this;
        StringBuilder builder = new StringBuilder();
        while (node.getParent() != null) {
            builder.insert(0, node.getVal());
            node = node.getParent();

        }
        return builder.toString();
    }

    public TrieNode getParent() {
        return this.parent;
    }


    static Character getFirstcharacter(String str){
        return str.charAt(0);
    }

    static String getRestOfString(String str){
        String restOfString = null;
        if (str.length() > 1) {
            restOfString = str.substring(1);
        }
        return restOfString;
    }

    private void loadWordList(ArrayList<String> wordList){
        for (String word : wordList) {
            this.loadString(word);
        }

    }

    static ArrayList<String> readWordListFromFile(String filename) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            Scanner s = new Scanner(new File(filename));

            while (s.hasNextLine()) {
                list.add(s.nextLine());
            }
            s.close();

        } catch (FileNotFoundException fne) {
            System.out.println("File not found");

        }
        return list;
    }

    public void bootTrie() {
        this.loadWordList(TrieNode.readWordListFromFile("resources/enable.txt"));
    }


}

class TrieRoot extends TrieNode {
    static private TrieRoot root;


    private TrieRoot(){
        super(null, null);
    }

    public static TrieRoot getInstance(){
        if (root == null) {
            root = new TrieRoot();
            root.bootTrie();
        }
        return root;
    }

}


