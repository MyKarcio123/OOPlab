package agh.ics.oop.gui;

import agh.ics.oop.SimulationEngine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class MainMenu extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("mainmenu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 699, 622);
        stage.setMinHeight(622);
        stage.setMinWidth(699);
        stage.setTitle("Symulacja Darwinowska");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
