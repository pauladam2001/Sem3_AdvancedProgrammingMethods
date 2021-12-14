package model;

public class Book implements Weightable{
    private final int weight;

    public Book(int weight)
    {
        this.weight=weight;
    }
    @Override
    public int getWeight()
    {
        return weight;
    }

    public  Book setWeight(int weight)
    {
        return new Book(weight);
    }
    @Override
    public  String toString()
    {
        return "Book("+weight+")";
    }
}
