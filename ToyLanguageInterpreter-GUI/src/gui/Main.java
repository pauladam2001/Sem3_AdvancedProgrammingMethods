package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(getClass().getResource("ProgramsListView.fxml"));
        Parent mainWindow = mainLoader.load();

        ProgramsListViewController controller = mainLoader.getController();
        controller.setProgramsListView();

        primaryStage.setTitle("Select Program");
        primaryStage.setScene(new Scene(mainWindow));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
