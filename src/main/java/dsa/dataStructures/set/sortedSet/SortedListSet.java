package dsa.dataStructures.set.sortedSet;

import dsa.dataStructures.list.LinkedList;
import dsa.dataStructures.list.linearLinkedList.SinglyLinkedList;

import java.util.Comparator;
import java.util.Objects;

public class SortedListSet<T extends Comparable<T>> extends SortedSet<T> {
    private final LinkedList<T> sortedLinkedList = new SinglyLinkedList<>();

    @Override
    public int size() {
        return sortedLinkedList.size();
    }

    @Override
    public T get(T element) {
        return sortedLinkedList.get(element);
    }

    @Override
    public void add(T element) {
        sortedLinkedList.first();
        while (sortedLinkedList.read() != null) {
            T redElement = sortedLinkedList.read();
            int comparison = Objects.compare(element, redElement, Comparator.naturalOrder());
            if (Objects.equals(element, redElement)) return;
            if (comparison < 0) {
                sortedLinkedList.prev();
                break;
            }
            sortedLinkedList.next();
        }
        sortedLinkedList.insert(element);
    }

    @Override
    public void addOrReplace(T element) {
        sortedLinkedList.first();
        while (sortedLinkedList.read() != null) {
            T redElement = sortedLinkedList.read();
            int comparison = Objects.compare(element, redElement, Comparator.naturalOrder());
            if (Objects.equals(element, redElement)) {
                sortedLinkedList.delete();
                sortedLinkedList.insert(element);
                return;
            }
            if (comparison < 0) {
                sortedLinkedList.prev();
                break;
            }
            sortedLinkedList.next();
        }
        sortedLinkedList.insert(element);

    }

    @Override
    public boolean contains(T element) {
        sortedLinkedList.first();
        while (sortedLinkedList.read() != null) {
            int comparison = Objects.compare(element, sortedLinkedList.read(), Comparator.naturalOrder());
            if (comparison < 0) return false;
            if (Objects.equals(element, sortedLinkedList.read())) return true;
            sortedLinkedList.next();
        }
        return false;
    }

    @Override
    public void remove(T element) {
        sortedLinkedList.first();
        while (sortedLinkedList.read() != null) {
            int comparison = Objects.compare(element, sortedLinkedList.read(), Comparator.naturalOrder());
            if (comparison < 0) return;
            if (Objects.equals(element, sortedLinkedList.read())) {
                sortedLinkedList.delete();
                return;
            }
            sortedLinkedList.next();
        }
    }
}
