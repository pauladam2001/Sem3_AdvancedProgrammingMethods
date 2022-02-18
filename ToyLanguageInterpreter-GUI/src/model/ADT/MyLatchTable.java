package model.ADT;

import java.util.HashMap;
import java.util.Map;

public class MyLatchTable implements LatchTableInterface<Integer, Integer> {
    private Map<Integer, Integer> latchTable;
    private int freeLocation;

    public MyLatchTable() {
        latchTable = new HashMap<>();
        freeLocation = 0;
    }

    @Override
    public void add(Integer key, Integer value) {
        latchTable.put(key, value);
    }

    @Override
    public void update(Integer key, Integer newValue) {
        latchTable.replace(key, newValue);
    }

    @Override
    public Integer remove(Integer key) {
        return latchTable.remove(key);
    }

    @Override
    public int getFirstFreeLocation() {
        int auxLocation = freeLocation;
        setNextFreeLocation();
        return auxLocation;
    }

    @Override
    public void setNextFreeLocation() {
        freeLocation++;
    }

    @Override
    public int size() {
        return latchTable.size();
    }

    @Override
    public boolean isEmpty() {
        return latchTable.isEmpty();
    }

    @Override
    public void clear() {
        latchTable.clear();
    }

    @Override
    public boolean containsKey(Integer key) {
        return latchTable.containsKey(key);
    }

    @Override
    public Integer getValue(Integer key) {
        return latchTable.get(key);
    }

    @Override
    public Map<Integer, Integer> getContent() {
        return latchTable;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Integer key:latchTable.keySet())
            s.append(key.toString()+"->"+latchTable.get(key).toString()+" ");
        return s.toString();
    }
}
