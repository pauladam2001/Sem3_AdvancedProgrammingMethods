package repository;

import exceptions.ElementNotFoundException;
import exceptions.FullArrayException;
import model.Food;

public interface Repository {
    public void addFood(Food newFood) throws FullArrayException;
    public void removeFood(Food foodToRemove) throws ElementNotFoundException;
    public Food[] getData();
}
