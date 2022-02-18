package model.ADT;

import java.util.Map;

public interface LatchTableInterface<TKey, TValue> {
    void add(TKey key, TValue value);
    void update(TKey key, TValue newValue);
    TValue remove(TKey key);
    int getFirstFreeLocation();
    void setNextFreeLocation();
    int size();
    boolean isEmpty();
    void clear();
    boolean containsKey(TKey key);
    TValue getValue(TKey key);
    Map<TKey, TValue> getContent();
    String toString();
}

