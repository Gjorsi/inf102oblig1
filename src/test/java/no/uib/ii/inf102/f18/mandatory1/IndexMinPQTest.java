package no.uib.ii.inf102.f18.mandatory1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test
 */
class IndexMinPQTest {
    
    private IIndexPQ<String> pq;

    @BeforeEach
    void setup() {
        this.pq = new IndexMinPQ<String>(7);
    }

    @Test
    void sanityTest() {
        
        pq.add(0, "A");
        pq.add(3, "K");
        pq.add(6, "E");
        pq.add(2, "J");
        assertEquals(4, pq.size());
        assertEquals(0, pq.peek());
        assertEquals("A", pq.peekKey());
        assertEquals("K", pq.getKey(3));
        assertEquals("A", pq.getKey(0));
        assertEquals("J", pq.getKey(2));

        assertTrue(pq.contains(0));
        assertEquals(0, pq.poll());
        assertFalse(pq.contains(0));
        assertEquals(6, pq.poll());

        pq.changeKey(2, "Z");
        assertEquals("K", pq.peekKey());
        assertEquals(3, pq.poll());
        assertEquals(2, pq.poll());

        assertEquals(0, pq.size());
    }
}
