package Model;

public class Flour implements Food {
    private Integer price;
    private final String type = "Flour";

    public Flour(Integer price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public String toString() {
        return "Type: " + this.type + ", price: " + this.price;
    }
}
