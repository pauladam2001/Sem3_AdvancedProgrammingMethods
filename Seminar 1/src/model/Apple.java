package model;

public class Apple implements Weightable {
    private final int weight;

    public Apple(int weight)
    {
        this.weight=weight;
    }
    @Override
    public int getWeight()
    {
        return weight;
    }

    public  Apple setWeight(int weight)
    {
        return new Apple(weight);
    }
    @Override
    public  String toString()
    {
        return "Apple("+weight+")";
    }
}
