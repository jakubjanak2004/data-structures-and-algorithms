package dsa.dataStructures.set.hashSet.openAddressingHashSet;

import dsa.dataStructures.set.hashSet.LineEntry;

import java.util.Objects;

public class DoubleHashingHashSet<T> extends OpenAddressingHashSet<T> {
    private int containerSizePrime;

    public DoubleHashingHashSet() {
        super();
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
        containerSizePrime = 3;
        // look for first prime smaller than the size of the newValues
        for (int i = newValues.length - 1; i >= 2; i--) {
            boolean isPrime = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                containerSizePrime = i;
                break;
            }
        }
    }

    private int hash2(T element, int containerSize) {
        int myhashVal2 = Math.abs(element.hashCode());
        myhashVal2 %= containerSize;
        return containerSizePrime - myhashVal2 % containerSizePrime;
    }
}
