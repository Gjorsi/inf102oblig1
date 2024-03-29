package no.uib.ii.inf102.f18.mandatory1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test
 */
class BSTTest {
    
    private BinarySearchTree<Integer, String> bst;

    @BeforeEach
    void setup() {
        this.bst = new BinarySearchTree<>();
    }

    @Test
    void iteratorSanityTest() {
        
        bst.put(0, "v");
        bst.put(1, "h");
        bst.put(2, "a");
        bst.put(3, "t");
        
        int i = 0;
        for(int a : bst.keys()) {
            assertEquals(i++, a); 
        }
    }
    
    @Test
    void getSanityTest() {
        bst.put(3, "o");
        bst.put(2, "f");
        bst.put(6, "b");
        bst.put(0, "w");
        
        assertEquals("o",bst.get(3));
        assertEquals("b",bst.get(6));
        assertEquals("w",bst.get(0));
    }
}
