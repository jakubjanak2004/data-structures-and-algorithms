package dsa.dataStructures.list.stackList;

import dsa.dataStructures.list.List;
import dsa.dataStructures.stack.ListStack;
import dsa.dataStructures.stack.Stack;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class StackList<T extends Comparable<T>> implements List<T>, Iterable<T> {
    private final Stack<T> leftStack = new ListStack<>();
    private final Stack<T> rightStack = new ListStack<>();

    @Override
    public int size() {
        return leftStack.length() + rightStack.length();
    }

    @Override
    public boolean empty() {
        return leftStack.isEmpty() && rightStack.isEmpty();
    }

    @Override
    public T read() {
        if (empty()) return null;
        return leftStack.top();
    }

    @Override
    public boolean contains(T t) {
        first();
        while (!rightStack.isEmpty()) {
            if (Objects.equals(read(), t)) return true;
            next();
        }
        return false;
    }

    @Override
    public void remove(T t) {
        first();
        while (!rightStack.isEmpty()) {
            if (Objects.equals(read(), t)) {
                delete();
            }
            next();
        }
    }

    @Override
    public T get(T t) {
        return leftStack.top();
    }

    @Override
    public void insert(T t) {
        leftStack.push(t);
    }

    @Override
    public void delete() {
        leftStack.pop();
    }

    @Override
    public T first() {
        while (leftStack.length() > 1) {
            rightStack.push(leftStack.top());
            leftStack.pop();
        }
        return leftStack.top();
    }

    @Override
    public T last() {
        while (!rightStack.isEmpty()) {
            leftStack.push(rightStack.top());
            rightStack.pop();
        }
        return leftStack.top();
    }

    @Override
    public void next() {
        if (!rightStack.isEmpty()) {
            leftStack.push(rightStack.top());
            rightStack.pop();
        }
    }

    @Override
    public void prev() {
        if (!leftStack.isEmpty()) {
            rightStack.push(leftStack.top());
            leftStack.pop();
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new StackListIterator();
    }

    protected class StackListIterator implements Iterator<T> {
        T current;

        public StackListIterator() {
            first();
            current = read();
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the list.");
            }
            T value = current;
            boolean lastElement = rightStack.isEmpty();
            StackList.this.next();
            current = read();
            if (lastElement) {
                current = null;
            }
            return value;
        }
    }
}
