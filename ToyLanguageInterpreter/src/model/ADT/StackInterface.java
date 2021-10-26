package model.ADT;


public interface StackInterface<TElem> {
    void push(TElem newElement);
    TElem pop();
    int size();
    String toString();
}
