package dsa.dataStructures.linkedList;

public class DoublyLinkedList<T> extends LinkedList<T> {

    @Override
    public void insert(T t) {
        ListNode<T> help = new ListNode<>();
        if (point == null) {
            help.setValue(t);
            if (tail == null) {
                head = help;
            } else {
                help.setPrevNode(tail);
                tail.setNextNode(help);
            }
            tail = help;
        } else {
            // setting the prev and next node
            help.setValue(point.getValue());
            help.setNextNode(point.getNextNode());
            point.getNextNode().setPrevNode(help);
            // setting the prev and next node
            point.setValue(t);
            point.setNextNode(help);
            help.setPrevNode(point);
            point = help;
        }
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
