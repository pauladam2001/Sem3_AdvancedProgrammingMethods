import playerPackage.BasketballPlayer;
import playerPackage.Player;
import playerPackage.VolleyballPlayer;

public class MainApp {
    public static void main(String[] args) {        //or with predefined List
        Player[] players = new Player[5];
        players[0] = new BasketballPlayer("Paul", 20);
        players[1] = new VolleyballPlayer("Dan", 21);
        players[2] = new VolleyballPlayer("Ionut", 18);
        players[3] = new BasketballPlayer("Daria", 19);
        players[4] = new VolleyballPlayer("Andrei", 18);

        for (Player p: players) {
            System.out.println(p.getName());
            System.out.println(p.getAge());
            System.out.println(p);
            System.out.println("const " + p.getPlayoffCareer());
            try {
                System.out.println(p.maybeException() + "\n");
            }
            catch (Exception exception) {
                System.out.println(exception.getMessage() + "\n");
            }
        }
    }
}
