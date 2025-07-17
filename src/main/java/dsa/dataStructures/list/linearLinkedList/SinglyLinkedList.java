package dsa.dataStructures.list.linearLinkedList;

import dsa.dataStructures.list.ListNode;

import java.util.Objects;

public class SinglyLinkedList<T> extends LinearLinkedList<T> {

    @Override
    public void insert(T t, boolean before) {
        ListNode<T> help = new ListNode<>();
        if (head == null || point == null) {
            help.setValue(t);
            if (tail == null) {
                head = help;
            } else {
                tail.setNextNode(help);
            }
            tail = help;
        } else if (before) {
            // prepend before point
            help.setValue(point.getValue());
            if (point.getNextNode() == null) {
                tail = help;
            } else {
                help.setNextNode(point.getNextNode());
            }
            point.setNextNode(help);
            point.setValue(t);
        } else {
            // append after point
            help.setValue(t);
            help.setPrevNode(point);
            if (point.getNextNode() == null) {
                tail = help;
            } else {
                point.getNextNode().setPrevNode(help);
                help.setNextNode(point.getNextNode());
            }
            point.setNextNode(help);
        }
        point = help;
        len++;
    }

    @Override
    public void delete() {
        ListNode<T> help;
        if (point != null) {
            if (Objects.equals(point, head)) {
                head = tail = point = null;
            } else if (point.getNextNode() == null) {
                help = head;
                while (help.getNextNode() != point)
                    help = help.getNextNode();
                help.setNextNode(null);
                point = null;
                tail = help;
            } else {
                help = point.getNextNode();
                point.setNextNode(help.getNextNode());
                point.setValue(help.getValue());
                if (Objects.equals(help, tail))
                    tail = point;
            }
            len--;
        }
    }

    @Override
    public T first() {
        point = head;
        if (head != null) {
            return head.getValue();
        }
        return null;
    }

    @Override
    public T last() {
        point = tail;
        if (tail != null) {
            return tail.getValue();
        }
        return null;
    }

    @Override
    public void next() {
        point = point.getNextNode();
    }

    /**
     * Moves the pointer to the previous node in the list.
     * <p>
     * In the case of a {@code SinglyLinkedList}, the time complexity of this operation is {@code O(n)},
     * because the structure does not store a reference to the previous node. Therefore, to find the previous node,
     * it is necessary to start from the head of the list and traverse until reaching the node whose {@code next} points
     * to the current {@code point}.
     * </p>
     * <p>
     * If the {@code point} is currently at the head of the list, calling {@code prev()} will have no effect,
     * because there is no node before the head.
     * </p>
     * <p>
     * Note: In general, singly linked lists are designed for efficient forward-only traversal.
     * The {@code prev()} method is an additional convenience, but it is not efficient for frequent backward traversals.
     * For efficient backward traversal, consider using a doubly linked list.
     * </p>
     */
    @Override
    public void prev() {
        ListNode<T> help;
        if (point != head) {
            help = head;
            while (help.getNextNode() != point)
                help = help.getNextNode();
            point = help;
        }
    }
}
