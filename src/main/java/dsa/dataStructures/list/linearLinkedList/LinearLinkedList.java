package dsa.dataStructures.list.linearLinkedList;

import dsa.dataStructures.list.LinkedList;
import dsa.dataStructures.list.ListNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class LinearLinkedList<T> extends LinkedList<T> {
    @Override
    public Iterator<T> iterator() {
        return new LinearLinkedListIterator();
    }

    protected class LinearLinkedListIterator implements Iterator<T> {
        private ListNode<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the list.");
            }
            T value = current.getValue();
            current = current.getNextNode();
            return value;
        }
    }
}
