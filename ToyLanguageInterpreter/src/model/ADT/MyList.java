package model.ADT;

import exceptions.EmptyADTException;
import model.ADT.ListInterface;

import java.util.ArrayList;
import java.util.List;

public class MyList<TElem> implements ListInterface<TElem> {
    private List<TElem> list;       // List = provides control over the position where you can insert an element. You can access elements by their index and also search elements in the list.

    public MyList() {
        this.list = new ArrayList<TElem>();     // ArrayList = in addition to implementing the List interface, this class provides methods to manipulate the size of the array that is used internally to store the list
    }

    @Override
    public void addToEnd(TElem newElement) {
        this.list.add(newElement);
    }

    @Override
    public TElem pop() throws Exception {
        int size = this.list.size();
        if (size > 0)
            return this.list.remove(size - 1);
        throw new EmptyADTException("MyList is empty!");
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public String toString() {
        String str = "Output:\n";
        for (TElem elem: list)
            str += elem.toString() + "\n";
        return str;
    }
}
