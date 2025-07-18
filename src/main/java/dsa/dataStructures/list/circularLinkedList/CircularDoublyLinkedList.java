package dsa.dataStructures.list.circularLinkedList;

import dsa.dataStructures.list.LinkedList;
import dsa.dataStructures.list.ListNode;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularDoublyLinkedList<T> extends LinkedList<T> {
    ListNode<T> dummyHead = new ListNode<>();

    @Override
    public boolean empty() {
        return dummyHead.getNextNode() == null || dummyHead.getNextNode() == dummyHead;
    }

    @Override
    public void insert(T t, boolean before) {
        ListNode<T> help = new ListNode<>(t);
        if (dummyHead.getNextNode() == null) {
            dummyHead.setPrevNode(help);
            dummyHead.setNextNode(help);
            help.setPrevNode(dummyHead);
            help.setNextNode(dummyHead);
        } else if (before) {
            help.setNextNode(point);
            help.setPrevNode(point.getPrevNode());
            point.getPrevNode().setNextNode(help);
            point.setPrevNode(help);
        } else {
            help.setNextNode(point.getNextNode());
            point.getNextNode().setPrevNode(help);
            point.setNextNode(help);
            help.setPrevNode(point);
        }
        point = help;
        len++;
    }

    @Override
    public void delete() {
        if (point != dummyHead) {
            point.getPrevNode().setNextNode(point.getNextNode());
            point.getNextNode().setPrevNode(point.getPrevNode());
            point = point.getNextNode();
            len--;
        }
    }

    @Override
    public T first() {
        ListNode<T> firstNode = dummyHead.getNextNode();
        if (firstNode != null) {
            point = firstNode;
            return firstNode.getValue();
        }
        return null;
    }

    @Override
    public T last() {
        ListNode<T> lastNode = dummyHead.getPrevNode();
        if (lastNode != null) {
            point = lastNode;
            return lastNode.getValue();
        }
        return null;
    }

    @Override
    public void next() {
        if (!atEnd()) {
            point = point.getNextNode();
        } else {
            point = dummyHead.getNextNode();
        }
    }

    @Override
    public void prev() {
        if (!atBegin()) {
            point = point.getPrevNode();
        } else {
            point = dummyHead.getPrevNode();
        }
    }

    private boolean atBegin() {
        return point == dummyHead.getNextNode();
    }

    private boolean atEnd() {
        return point == dummyHead.getPrevNode();
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return new CircularLinkedListIterator();
    }

    protected class CircularLinkedListIterator implements Iterator<T> {
        private ListNode<T> current = dummyHead.getNextNode();

        @Override
        public boolean hasNext() {
            return current != dummyHead;
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
