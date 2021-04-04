package com.dental;

<<<<<<< HEAD
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(super.getClass().getResource("/controllers/home_layout.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dental Lab");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
=======
public class Main {

    public static void main(String[] args) {

>>>>>>> origin/main
    }

}
