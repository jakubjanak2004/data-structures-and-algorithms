package dataStructures.multiset;

import dsa.dataStructures.multiset.MultiSet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MultisetTest {
    @Test
    public void emptyWhenNothingAdded() {
        MultiSet<Integer> multiset = new MultiSet<>();
        assertEquals(0, multiset.size());
    }

    @Test
    public void countEqualsTo0WhenElementNotPresent() {
        MultiSet<Integer> multiset = new MultiSet<>();
        // element 5 not present
        assertEquals(0, multiset.count(5));
    }

    @Test
    public void elementsCountIncreasesWhenAddedMultipleTimes() {
        MultiSet<Integer> multiset = new MultiSet<>();
        // adding the same element 3 times
        multiset.put(5);
        multiset.put(5);
        multiset.put(5);

        assertEquals(3, multiset.size());
        assertEquals(3, multiset.count(5));
    }

    @Test
    public void eachElementCountIsCorrect() {
        MultiSet<Integer> multiset = new MultiSet<>();

        multiset.put(1);
        multiset.put(1);
        multiset.put(1);
        multiset.put(2);
        multiset.put(2);

        assertEquals(5, multiset.size());
        assertEquals(3, multiset.count(1));
        assertEquals(2, multiset.count(2));
    }

    @Test
    public void countSetTo0AfterElementWasRemovedAndNotPresentAnyMore() {
        MultiSet<Integer> multiset = new MultiSet<>();
        // 5 added two times
        multiset.put(1);
        multiset.put(1);

        // check if two counts and present are correct
        assertEquals(2, multiset.size());
        assertEquals(2, multiset.count(1));
        assertTrue(multiset.contains(1));

        // remove 1 element
        multiset.remove(1);

        // check
        assertEquals(1, multiset.size());
        assertEquals(1, multiset.count(1));
        assertTrue(multiset.contains(1));

        // remove all elements, now there should be none
        multiset.remove(1);

        // check that there is no element
        assertEquals(0, multiset.size());
        assertEquals(0, multiset.count(1));
        assertFalse(multiset.contains(1));
    }
}
