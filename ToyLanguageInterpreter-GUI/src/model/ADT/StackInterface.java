package model.ADT;


import java.util.Deque;

public interface StackInterface<TElem> {
    void push(TElem newElement);
    TElem pop();
    int size();
    Deque<TElem> getStack();
    String toString();
}
