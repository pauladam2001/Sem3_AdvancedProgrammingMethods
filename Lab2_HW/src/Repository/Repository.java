package Repository;

import Exceptions.ElementNotFoundException;
import Exceptions.FullArrayException;
import Model.Food;

public interface Repository {
    public void addFood(Food newFood) throws FullArrayException;
    public void removeFood(Food foodToRemove) throws ElementNotFoundException;
    public Food[] getData();
}
