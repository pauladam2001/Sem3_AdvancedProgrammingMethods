package Controller;

import Exceptions.ElementNotFoundException;
import Exceptions.FullArrayException;
import Exceptions.InvalidTypeException;
import Model.Flour;
import Model.Food;
import Model.Salt;
import Model.Sugar;
import Repository.Repository;
import Repository.InMemoryRepository;

public class Controller {
    private Repository repository;
    private Integer size;

    public Controller(Integer size) {
        this.size = size;
        this.repository = new InMemoryRepository(size);
    }

    public void addController(Integer price, String type) throws FullArrayException, InvalidTypeException {
        Food newFood;

        if (type.equals("Flour"))
            newFood = new Flour(price);
        else if (type.equals("Salt"))
            newFood = new Salt(price);
        else if (type.equals("Sugar"))
            newFood = new Sugar(price);
        else
            throw new InvalidTypeException("Invalid type!");

        this.repository.addFood(newFood);
    }

    public void removeController(Integer price, String type) throws ElementNotFoundException, InvalidTypeException {
        Food foodToBeRemoved;

        if (type.equals("Flour"))
            foodToBeRemoved = new Flour(price);
        else if (type.equals("Salt"))
            foodToBeRemoved = new Salt(price);
        else if (type.equals("Sugar"))
            foodToBeRemoved = new Sugar(price);
        else
            throw new InvalidTypeException("Invalid type!");

        this.repository.removeFood(foodToBeRemoved);
    }

    public Food[] getData() {
        return this.repository.getData();
    }

    public Food[] getProductsMoreExpensive() {
        Food[] data = this.getData();
        Food[] filteredProducts = new Food[data.length];
        int currSize = 0, i = 0;
        while (data[i] != null && i < data.length) {
            if (data[i].getPrice() > Food.MIN_PRICE) {
                filteredProducts[currSize] = data[i];
                currSize++;
            }
            i++;
        }
        return filteredProducts;
    }
}
