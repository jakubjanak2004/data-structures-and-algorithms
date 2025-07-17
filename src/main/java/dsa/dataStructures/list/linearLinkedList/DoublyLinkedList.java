package dsa.dataStructures.list.linearLinkedList;

import dsa.dataStructures.list.ListNode;

public class DoublyLinkedList<T> extends LinearLinkedList<T> {

    @Override
    public void insert(T t, boolean before) {
        ListNode<T> help = new ListNode<>();
        if (head == null || point == null) {
            help.setValue(t);
            if (tail == null) {
                head = help;
            } else {
                tail.setNextNode(help);
                help.setPrevNode(tail);
            }
            tail = help;
        } else if (before) {
            // prepend before element
            help.setValue(point.getValue());
            if (point.getNextNode() == null) {
                tail = help;
            } else {
                help.setNextNode(point.getNextNode());
                point.getNextNode().setPrevNode(help);
            }
            point.setNextNode(help);
            help.setPrevNode(point);
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
            help.setPrevNode(point);
        }
        point = help;
        len++;
    }

    @Override
    public void delete() {
        ListNode<T> help;
        if (point != null) {
            help = point.getNextNode();
            if (head == point) {
                head = help;
            }
            if (tail == point) {
                tail = tail.getPrevNode();
            }
            if (help != null) {
                help.setPrevNode(point.getPrevNode());
            }
            if (point.getPrevNode() != null) {
                point.getPrevNode().setNextNode(help);
            }
            point = help;
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

    @Override
    public void prev() {
        if (point != head) {
            if (point == null)
                point = tail;
            else
                point = point.getPrevNode();
        }
    }
}
