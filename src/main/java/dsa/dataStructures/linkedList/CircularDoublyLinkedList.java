package dsa.dataStructures.linkedList;

public class CircularDoublyLinkedList<T> extends LinkedList<T> {
    ListNode<T> dummyHead = new ListNode<>();

    @Override
    public void insert(T t) {
        ListNode<T> help = new ListNode<>(t);
        if (dummyHead.getNextNode() == null) {
            dummyHead.setPrevNode(help);
            dummyHead.setNextNode(help);
            help.setPrevNode(dummyHead);
            help.setNextNode(dummyHead);
        } else {
            help.setPrevNode(dummyHead.getPrevNode());
            dummyHead.getPrevNode().setNextNode(help);
            dummyHead.setPrevNode(help);
            help.setNextNode(dummyHead);
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

    public ListNode<T> getDummyHead() {
        return dummyHead;
    }
}
