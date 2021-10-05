package playerPackage;
import java.util.Random;

public abstract class Player {
    String name;
    Integer age;
    static Random rand = new Random();
    final static Integer PLAYOFF_CAREER = 5;

    public abstract String  getName();
    public abstract Integer getAge();
    public abstract Integer maybeException() throws Exception;
    public Integer getPlayoffCareer() {
        return PLAYOFF_CAREER;
    }
}
