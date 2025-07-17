package dsa.dataStructures.set.sortedSet;

import dsa.dataStructures.list.LinkedList;
import dsa.dataStructures.list.linearLinkedList.SinglyLinkedList;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

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
            int comparison = element.compareTo(redElement);
            if (comparison == 0) return;
            if (comparison < 0) {
                break;
            }
            sortedLinkedList.next();
        }
        sortedLinkedList.insert(element, true);
    }

    // todo test add or replace
    @Override
    public void addOrReplace(T element) {
        sortedLinkedList.first();
        while (sortedLinkedList.read() != null) {
            T redElement = sortedLinkedList.read();
            int comparison = element.compareTo(redElement);
            if (comparison == 0) {
                sortedLinkedList.delete();
                sortedLinkedList.insert(element);
                return;
            }
            if (comparison < 0) {
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
            int comparison = element.compareTo(sortedLinkedList.read());
            if (comparison < 0) return false;
            if (comparison == 0) return true;
            sortedLinkedList.next();
        }
        return false;
    }

    @Override
    public void remove(T element) {
        sortedLinkedList.first();
        while (sortedLinkedList.read() != null) {
            int comparison = element.compareTo(sortedLinkedList.read());
            if (comparison < 0) return;
            if (comparison == 0) {
                sortedLinkedList.delete();
                return;
            }
            sortedLinkedList.next();
        }
    }

    // todo
    @Override
    public T min() {
        return null;
    }

    // todo
    @Override
    public T max() {
        return null;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return sortedLinkedList.iterator();
    }
}
