package model.ADT;

public interface ListInterface<TElem> {
    void addToEnd(TElem newElement);
    TElem pop() throws Exception;
    int size();
    String toString();
}
