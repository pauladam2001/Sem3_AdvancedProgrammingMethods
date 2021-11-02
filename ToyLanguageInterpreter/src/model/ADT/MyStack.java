package model.ADT;

import model.ADT.StackInterface;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyStack<TElem> implements StackInterface<TElem> {
    private Deque<TElem> stack;         // Deque = related to the double-ended queue, can be used as a stack

    public MyStack() {
        this.stack = new ArrayDeque<TElem>();       // ArrayDeque = resizable array, no capacity restrictions
    }

    @Override
    public void push(TElem newElement) {
        this.stack.push(newElement);
    }

    @Override
    public TElem pop() {
        return this.stack.pop();
    }

    @Override
    public int size() {
        return this.stack.size();
    }

    @Override
    public String toString() {
        String str = "";
        for (TElem elem: stack)
            str += elem.toString() + "\n";
        return str;
    }
}
