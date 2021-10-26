package model.ADT;

public interface DictionaryInterface<TKey, TValue> {
    void add(TKey key, TValue value);
    TValue remove(TKey key);
    int size();
    boolean isDefined(TKey key);
    TValue getValue(TKey key);
    void update(TKey key, TValue newValue);
    String toString();
}
