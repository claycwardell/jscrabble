package com.claycwardell.scrabble;

import org.junit.Test;

import static org.junit.Assert.*;


public class TrieNodeTest {

    @Test
    public void testTrieNode() {
        TrieNode node = new TrieNode(null, null);
        node.loadString("CLAY");
        assertNotNull(node.getNodeForString("CLA"));
        assertTrue(node.getNodeForString("CLAY").getIsTerminal());
        assertNull(node.getNodeForString("WARDELL"));
    }

    @Test
    public void testBoot() {
        TrieNode rootNode = TrieNode.bootTrie();
        assertNotNull(rootNode.getNodeForString("incredib"));
        assertNull(rootNode.getNodeForString("asfasdfasdfasdfasdfasdfasdfasdfs"));
        assertTrue(rootNode.getNodeForString("incredible").getIsTerminal());
    }

    @Test
    public void testUnwind(){
        TrieNode node = new TrieNode(null, null);
        node.loadString("CLAY");
        TrieNode clay = node.getNodeForString("CLAY");
        assertEquals(clay.unwind(), "CLAY");
    }
}