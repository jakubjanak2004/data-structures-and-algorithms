package dsa.dataStructures.list.linearLinkedList;

import dsa.dataStructures.list.LinkedList;
import dsa.dataStructures.list.ListNode;

import java.util.Objects;

public class SinglyLinkedList<T> extends LinearLinkedList<T> {

    @Override
    public void insert(T t) {
        ListNode<T> help = new ListNode<>();
        if (point == null) {
            help.setValue(t);
            if (tail == null) {
                head = help;
            } else {
                tail.setNextNode(help);
            }
            tail = help;
        } else {
            help.setValue(point.getValue());
            help.setNextNode(point.getNextNode());
            point.setNextNode(help);
            point.setValue(t);
            point = help;
        }
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
