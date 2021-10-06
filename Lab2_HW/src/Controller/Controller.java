package controller;

import exceptions.ElementNotFoundException;
import exceptions.FullArrayException;
import exceptions.InvalidTypeException;
import model.Flour;
import model.Food;
import model.Salt;
import model.Sugar;
import repository.Repository;
import repository.InMemoryRepository;

public class Controller {
    private Repository repository;
    private Integer size;

    public Controller(Integer size) {
        this.size = size;
        this.repository = new InMemoryRepository(size);
        this.addStart();
    }

    private void addStart() {
        try {
            this.addController(25, "Flour");
            this.addController(20, "Sugar");
            this.addController(20, "Salt");
            this.addController(18, "Flour");
            this.addController(5, "Salt");
        } catch (Exception ignored) {
        }
    }

    public void addController(Integer price, String type) throws FullArrayException, InvalidTypeException {
        Food newFood = switch (type) {
            case "Flour" -> new Flour(price);
            case "Salt" -> new Salt(price);
            case "Sugar" -> new Sugar(price);
            default -> throw new InvalidTypeException("Invalid type!");
        };

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