package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class StatsApplication implements IWindow {

    private AbstractWorldMap map;
    private StatsController statsController;
    private SimulationEngine simulationEngine;


    public void runApp(IWindow simulationApplication, Stage primaryStage, DataParameters currentConfig) throws IOException {
        this.map = simulationApplication.getSimulationEngine().getMap();
        this.simulationEngine = simulationApplication.getSimulationEngine();
        init(primaryStage, currentConfig);
        start((SimulationApplication) simulationApplication, primaryStage);
    }

    public void init(Stage primaryStage,DataParameters currentConfig) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/stats.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            primaryStage.setMinHeight(600);
            primaryStage.setMinWidth(800);
            primaryStage.setTitle("Statystyki");
            primaryStage.setScene(scene);
            statsController = fxmlLoader.getController();
            statsController.setMap(map);


    }

    public void start(SimulationApplication simulationApplication, Stage primaryStage) {
        primaryStage.show();
        statsController.prepareBackground(simulationApplication, new ArrayList<>(Arrays.asList(0,0,0,0)));
        primaryStage.setOnCloseRequest(event -> {
            getSimulationEngine().setStatsApplication(null);
        });

    }


    @Override
    public int getSimulationCounterAndAdd() {
        return 0;
    }

    @Override
    public SimulationEngine getSimulationEngine() {
        return this.simulationEngine;
    }

    public void refreshStats() {
        Platform.runLater(() ->{
            statsController.showNumericStats();
            statsController.showPlotStats();
        });

    }
}
