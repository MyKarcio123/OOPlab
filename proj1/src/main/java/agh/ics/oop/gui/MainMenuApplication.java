package agh.ics.oop.gui;

import agh.ics.oop.DataParameters;
import agh.ics.oop.IWindow;
import agh.ics.oop.SimulationEngine;
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
import java.util.HashMap;
import java.util.List;

public class MainMenuApplication extends Application implements IWindow {


    private int simulationCounter = 0;

    @Override
    public void runApp(IWindow mainMenuApplication, Stage primaryStage, DataParameters currentConfig) throws IOException {

    }

    public int getSimulationCounterAndAdd(){
        simulationCounter += 1;
        return simulationCounter;
    }

    @Override
    public SimulationEngine getSimulationEngine() {
        return null;
    }


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
                simulationApplication.runApp((IWindow) this, stage,new DataParameters(menuController.getCurrentConfig()));

                /*Thread thread = new Thread(simulationApplication);
                thread.start();
                //simulationApplication.run((IWindow) this, stage,new DataParameters(menuController.getCurrentConfig())); */
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
