import controller.Controller;
import repository.Repository;
import repository.RepositoryInterface;
import view.View;

public class Main {
    public static void main(String[] args) {
        RepositoryInterface repository = new Repository();
        Controller controller = new Controller(repository);
        View view = new View(controller);
        view.start();
    }
}
