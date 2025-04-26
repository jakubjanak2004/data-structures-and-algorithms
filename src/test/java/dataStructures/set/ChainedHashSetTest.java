package dataStructures.set;

import dsa.dataStructures.Set.ChainedHashSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChainedHashSetTest {

    private ChainedHashSet<Integer> set;

    @BeforeEach
    void setUp() {
        set = new ChainedHashSet<>();
    }

    @Test
    void testAddAndContains() {
        assertFalse(set.contains(5));

        set.add(5);
        assertTrue(set.contains(5));

        set.add(10);
        assertTrue(set.contains(10));

        set.add(5); // duplicate add
        assertTrue(set.contains(5));
    }

    @Test
    void testRemove() {
        set.add(42);
        assertTrue(set.contains(42));

        set.remove(42);
        assertFalse(set.contains(42));

        // Removing non-existing element should do nothing
        set.remove(99);
        assertFalse(set.contains(99));
    }

    @Test
    void testMultipleElements() {
        for (int i = 0; i < 100; i++) {
            set.add(i);
        }

        for (int i = 0; i < 100; i++) {
            assertTrue(set.contains(i));
        }

        set.remove(50);
        assertFalse(set.contains(50));
    }

    @Test
    void testNegativeHashCodes() {
        set.add(-123);
        assertTrue(set.contains(-123));

        set.remove(-123);
        assertFalse(set.contains(-123));
    }
}
