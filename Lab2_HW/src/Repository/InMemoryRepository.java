package repository;

import exceptions.ElementNotFoundException;
import exceptions.FullArrayException;
import model.Food;

import java.util.Objects;

public class InMemoryRepository implements Repository {
    private Food[] data;
    private Integer currentSize;
    private final Integer size;

    public InMemoryRepository(Integer size) {
        this.size = size;
        this.data = new Food[this.size];
        this.currentSize = 0;
    }

    @Override
    public void addFood(Food newFood) throws FullArrayException {
        if (this.currentSize.equals(this.size))
            throw new FullArrayException("The array is full!");
        this.data[this.currentSize] = newFood;
        this.currentSize++;
    }

    @Override
    public void removeFood(Food foodToRemove) throws ElementNotFoundException {
        int ok = 0, position = 0;
        for (int i=0;i < currentSize; i++)
            if (this.data[i].getPrice() == foodToRemove.getPrice() && Objects.equals(this.data[i].getType(), foodToRemove.getType())) {
                ok = 1;
                position = i;
                break;
            }
        if (ok == 0)
            throw new ElementNotFoundException("Element is not in the repostory!");
        this.data[position] = this.data[this.currentSize - 1];
        this.data[this.currentSize - 1] = null;
        this.currentSize--;
    }

    @Override
    public Food[] getData() {
        return this.data;
    }
}
