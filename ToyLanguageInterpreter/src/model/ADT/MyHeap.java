package model.ADT;

public class MyHeap<TKey, TValue> extends MyDictionary<TKey, TValue> {      // MyDictionary is also a hashmap, so we don't need an interface for the heap
    private int firstFreeLocation = 1;

    public MyHeap() {
        super();
    }

    private int setNextFreeLocation() {
        return this.firstFreeLocation + 1;
    }

    public int getFirstFreeLocation() {
        int copy = firstFreeLocation;
        firstFreeLocation = setNextFreeLocation();
        return copy;
    }
}
