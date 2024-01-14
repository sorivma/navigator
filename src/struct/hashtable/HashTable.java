package struct.hashtable;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class HashTable<T> implements Iterable<KeyValue<Integer, T>> {
    private final HashTableKV<Integer, T> integerTHashTable = new HashTableKV<>();

    @NotNull
    @Override
    public Iterator<KeyValue<Integer, T>> iterator() {
        return integerTHashTable.iterator();
    }

    public void add(T value) {
        integerTHashTable.add(value.hashCode(), value);
    }

    public Integer getOrder(Integer hashCode) {
        return integerTHashTable.find(hashCode).getOrder();
    }

    public boolean addOrReplace(T value) {
        return integerTHashTable.addOrReplace(value.hashCode(), value);
    }

    public T get(Integer hashCode) {
        return integerTHashTable.get(hashCode);
    }

    public KeyValue<Integer, T> find(Integer hashCode) {
        return integerTHashTable.find(hashCode);
    }

    public boolean remove(Integer key) {
        return integerTHashTable.remove(key);
    }

    public void clear() {
        integerTHashTable.clear();
    }

    public Iterable<T> values() {
        return integerTHashTable.values();
    }

    public boolean containsKey(Integer key) {
        return integerTHashTable.containsKey(key);
    }

    public int size() {
        return integerTHashTable.size();
    }

    @Override
    public String toString() {
        return integerTHashTable.toString();
    }
}
