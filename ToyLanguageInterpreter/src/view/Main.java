package view;

import controller.Controller;
import repository.Repository;
import repository.RepositoryInterface;

public class Main {
    public static void main(String[] args) {
        RepositoryInterface repository = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile.txt");
        Controller controller = new Controller(repository);
        View view = new View(controller);
        view.start();
    }
}
