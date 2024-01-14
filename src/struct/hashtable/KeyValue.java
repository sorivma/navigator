package struct.hashtable;

import java.util.LinkedList;
import java.util.Objects;

public class KeyValue<Key, Value> {
    private Key key;
    private Value value;
    private Integer order;


    public KeyValue(Key key, Value value, Integer order) {
        this.key = key;
        this.value = value;
        this.order = order;
    }

    public Key getKey() {
        return key;
    }

    public Value getValue() {
        return value;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return this.combineHashCodes(this.getKey().hashCode(), this.getValue().hashCode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyValue<?, ?> keyValue = (KeyValue<?, ?>) o;
        return Objects.equals(key, keyValue.key) &&
                Objects.equals(value, keyValue.value);
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", this.getKey(), this.getValue()) + " "
                + String.format("Key hash: {%s}", key.hashCode());
    }

    private int combineHashCodes(int h1, int h2) {
        return ((h1 << 5) + h1) ^ h2;
    }
}
