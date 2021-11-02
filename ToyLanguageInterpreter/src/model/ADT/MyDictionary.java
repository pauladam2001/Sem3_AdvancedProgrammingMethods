package model.ADT;

import model.ADT.DictionaryInterface;

import java.util.Collection;
import java.util.HashMap;

public class MyDictionary<TKey, TValue> implements DictionaryInterface<TKey, TValue> {
    private HashMap<TKey, TValue> dictionary;

    public MyDictionary() {
        this.dictionary = new HashMap<TKey, TValue>();  // a HashMap store items in "key/value" pairs, and you can access them by an index of another type (e.g. a String).
    }

    @Override
    public void add(TKey key, TValue value) {
        this.dictionary.put(key, value);
    }

    @Override
    public TValue remove(TKey key) {
        return this.dictionary.remove(key);
    }

    @Override
    public int size() {
        return this.dictionary.size();
    }

    @Override
    public boolean isDefined(TKey key) {
        return this.dictionary.containsKey(key);
    }

    @Override
    public TValue getValue(TKey key) {
        return this.dictionary.get(key);
    }

    @Override
    public void update(TKey key, TValue newValue) {
        this.dictionary.replace(key, newValue);
    }

    @Override
    public String toString() {
        String str = "";
        Collection<TKey> allKeys = dictionary.keySet();    // keyset() creates a set out of the key elements contained in the hash table
        for (TKey key: allKeys)
//            str += "key " + key.toString() + ", value " + dictionary.get(key).toString() + "\n";
            str += key.toString() + " = " + dictionary.get(key).toString() + "\n";
        return str;
    }
}
