package agh.ics.oop.gui;

import agh.ics.oop.DataParameters;
import agh.ics.oop.IWindow;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainMenuApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainmenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 699, 622);
        primaryStage.setMinHeight(622);
        primaryStage.setMinWidth(699);
        primaryStage.setTitle("Symulacja Darwinowska");
        primaryStage.setScene(scene);
        primaryStage.show();
        MainMenuController menuController = fxmlLoader.getController();
        menuController.loadSaves();
        menuController.getStartButton().setOnAction(event -> {
            try {
                Stage stage = new Stage();
                IWindow simulationApplication = new SimulationApplication();
                simulationApplication.runApp(stage,new DataParameters(menuController.getCurrentConfig()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
