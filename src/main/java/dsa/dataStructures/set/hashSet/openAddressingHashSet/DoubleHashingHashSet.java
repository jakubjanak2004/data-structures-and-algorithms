package dsa.dataStructures.set.hashSet.openAddressingHashSet;

import dsa.dataStructures.set.hashSet.LineEntry;

import java.lang.reflect.Array;
import java.util.Objects;

// todo optimise, container size prime must be changing, doesnt need to calculate that
public class DoubleHashingHashSet<T> extends OpenAddressingHashSet<T> {
    private static final int INITIAL_PRIME_TABLE_CAPACITY = 17;
    private int containerSizePrime;

    public DoubleHashingHashSet() {
        super(INITIAL_PRIME_TABLE_CAPACITY);
        // calculate the container size prime for values array
        calcContainerPrimeForContainer(values);
    }

    @Override
    public T get(T element) {
        int hash1 = hash(element, values.length);
        int hash2 = hash2(element, values.length);
        while (values[hash1] != null) {
            if (Objects.equals(element, values[hash1].getValue())) {
                return values[hash1].getValue();
            } else {
                hash1 = (hash1 + hash2) % values.length;
            }
        }
        return null;
    }

    @Override
    public void add(T element) {
        int hash1 = hash(element, values.length);
        int hash2 = hash2(element, values.length);
        while (values[hash1] != null) {
            if (Objects.equals(element, values[hash1].getValue())) {
                return;
            }
            hash1 = (hash1 + hash2) % values.length;
        }
        values[hash1] = new LineEntry<>(element);
        numOfUniqueEntries++;
        super.add(element);
    }

    @Override
    public void addOrReplace(T element) {
        int hash1 = hash(element, values.length);
        int hash2 = hash2(element, values.length);
        while (values[hash1] != null) {
            if (Objects.equals(element, values[hash1].getValue())) {
                values[hash1] = new LineEntry<>(element);
                return;
            }
            hash1 = (hash1 + hash2) % values.length;
        }
        values[hash1] = new LineEntry<>(element);
        numOfUniqueEntries++;
         super.add(element);
    }

    @Override
    public boolean contains(T element) {
        int hash1 = hash(element, values.length);
        int hash2 = hash2(element, values.length);
        while (values[hash1] != null) {
            if (Objects.equals(element, values[hash1].getValue())) {
                return !values[hash1].isDeleted();
            } else {
                hash1 = (hash1 + hash2) % values.length;
            }
        }
        return false;
    }

    @Override
    public void remove(T element) {
        int hash1 = hash(element, values.length);
        int hash2 = hash2(element, values.length);
        while (values[hash1] != null) {
            if (Objects.equals(element, values[hash1].getValue())) break;
            else hash1 = (hash1 + hash2) % values.length;
        }
        if (values[hash1] == null) return;
        values[hash1].setDeleted(true);
        numOfUniqueEntries--;
         super.remove(element);
    }

    @Override
    public void growValuesArray() {
        LineEntry<T>[] newValues = (LineEntry<T>[]) Array.newInstance(LineEntry.class, nextPrime(values.length * 2));
        rehash(newValues);
    }

    @Override
    public void shrinkValuesArray() {
        LineEntry<T>[] newValues = (LineEntry<T>[]) Array.newInstance(LineEntry.class, previousPrime(values.length / 2));
        rehash(newValues);
    }

    @Override
    protected void rehash(LineEntry<T>[] newValues) {
        calcContainerPrimeForContainer(newValues);
        // rehash the table
        for (LineEntry<T> lineEntry : values) {
            if (lineEntry == null || lineEntry.isDeleted()) continue;
            int hash1 = hash(lineEntry.getValue(), newValues.length);
            int hash2 = hash2(lineEntry.getValue(), newValues.length);
            while (newValues[hash1] != null)
                hash1 = (hash1 + hash2) % newValues.length;
            newValues[hash1] = lineEntry;
        }
        values = newValues;
    }

    private void calcContainerPrimeForContainer(LineEntry<T>[] newValues) {
        containerSizePrime = previousPrime(newValues.length);
    }

    private int nextPrime(int current) {
        int prime;
        for (int i = current + 1;; i++) {
            boolean isPrime = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                prime = i;
                break;
            }
        }
        return prime;
    }

    private int previousPrime(int current) {
        int prime = 2;
        for (int i = current - 1; i >= 2; i--) {
            boolean isPrime = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                prime = i;
                break;
            }
        }
        return prime;
    }

    private int hash2(T element, int containerSize) {
        int hash2 = Math.abs(element.hashCode()) % containerSize;
        return containerSizePrime - hash2 % containerSizePrime;
    }
}
