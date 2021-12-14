package model.ADT;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface DictionaryInterface<TKey, TValue> {
    void add(TKey key, TValue value);
    TValue remove(TKey key);
    int size();
    boolean isDefined(TKey key);
    TValue getValue(TKey key);
    void update(TKey key, TValue newValue);
    void setContent(HashMap<TKey, TValue> newContent);
    HashMap<TKey,TValue> getContent();
    DictionaryInterface<TKey, TValue> clone();
    String toString();
}
