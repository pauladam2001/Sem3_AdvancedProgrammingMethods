package playerPackage;

public class BasketballPlayer extends Player {
//    private String itWorks;

    public BasketballPlayer(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Integer getAge() {
        return this.age;
    }

    public String toString() {
        return "BASKET: name: " + this.name + ", age: " + this.age;
    }

    @Override
    public Integer maybeException() throws Exception {
        Integer rand1 = rand.nextInt(10);
        if (rand1 != 8) //different than 8 => exception
            throw new Exception("Basketball not 8");
        return rand1;
    }
}
