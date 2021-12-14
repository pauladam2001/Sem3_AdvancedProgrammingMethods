package model;

public class Cake implements Weightable{
    private final int weight;

    public Cake(int weight)
    {
        this.weight=weight;
    }
    @Override
    public int getWeight()
    {
        return weight;
    }

    public  Cake setWeight(int weight)
    {
        return new Cake(weight);
    }
    @Override
    public  String toString()
    {
        return "Cake("+weight+")";
    }
}
