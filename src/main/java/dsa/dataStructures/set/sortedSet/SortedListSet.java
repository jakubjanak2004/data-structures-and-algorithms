package dsa.dataStructures.set.sortedSet;

import dsa.dataStructures.list.LinkedList;
import dsa.dataStructures.list.linearLinkedList.SinglyLinkedList;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

// todo make faster
// todo optimisation can be done, instead of looping from beginning loop from where the linked lists point is, therefore on average will have to make less moves
// todo interpolation can be implementing, determine where the element is based on the maximum and minimum
// todo use faster search algo, interpolation/binary search
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
        sortedLinkedList.insert(element);
    }

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

    @Override
    public T min() {
        return null;
    }

    @Override
    public T max() {
        return null;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
