package model.ADT;

import java.util.ArrayList;

public interface ListInterface<TElem> {
    void addToEnd(TElem newElement);
    TElem pop() throws Exception;
    int size();
    ArrayList<TElem> getList();
    String toString();
}
