package playerPackage;

public class VolleyballPlayer extends Player {
    public VolleyballPlayer(String name, Integer age) {
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
        return "VOLLEY: name: " + this.name + ", age: " + this.age;
    }

    @Override
    public Integer maybeException() throws Exception {
        Integer rand1 = rand.nextInt(10);
        if (rand1 != 9) //different than 8 => exception
            throw new Exception("Volleyball not 9");
        return rand1;
    }
}
