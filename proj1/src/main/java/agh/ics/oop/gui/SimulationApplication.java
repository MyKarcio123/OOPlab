package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class SimulationApplication implements IWindow/*, Runnable */ {

    @FXML
    private VBox mapVis;

    private AbstractWorldMap map;
    private int xMin;
    private int yMin;
    private int xMax;
    private int yMax;
    private final int WIDTH = 50;
    private final int HEIGHT = 50;
    private Vector2d[] positions;
    private GridPane gridPane;
    private SimulationController simulationController;
    private int simulationNumber;
    private Thread thread;
    private SimulationEngine simulationEngine;


    public void runApp(IWindow mainMenuApplication, Stage primaryStage, DataParameters currentConfig) throws IOException {
        simulationNumber = mainMenuApplication.getSimulationCounterAndAdd();
        init(primaryStage, currentConfig);
        start(primaryStage);
    }

    public void init(Stage primaryStage,DataParameters currentConfig) throws IOException {
        try{

            this.simulationEngine = new SimulationEngine(this, currentConfig);
            this.map = simulationEngine.getMap();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/showSimulation.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1100, 622);
            primaryStage.setMinHeight(622);
            primaryStage.setMinWidth(699);
            primaryStage.setTitle("Symulacja "+ Integer.toString(simulationNumber));
            primaryStage.setScene(scene);
            SimulationController simulationController = fxmlLoader.getController();
            simulationController.setMap(map);
            this.simulationController = simulationController;


            Thread thread = new Thread(simulationEngine);
            thread.start();
            this.thread = thread;



        }catch (IllegalArgumentException e){
            System.out.println(e);
            System.exit(0);
        }
    }

    public void start(Stage primaryStage) {
        primaryStage.show();
        simulationController.prepareBackground();
        simulationController.getStartStopBT().setOnAction(event -> {
            if(thread.isAlive()){
                stopSimulation();
            }else{
                continueSimulation();
            }
            });
        simulationController.getShowStatBT().setOnAction(event -> {
            try {
                Stage stage = new Stage();
                StatsApplication statsApplication = new StatsApplication();
                /*simulationEngine.setStatsApplication(statsApplication);*/
                statsApplication.runApp((IWindow) this, stage,this.map.getDataParameters());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        primaryStage.setOnCloseRequest(event -> {
            simulationEngine.setExit(true);
            System.out.println("Koniec symulacji " + simulationNumber);
        });
    }

    public void refreshMap(){
        simulationController.refreshMap();
    }

    public void stopSimulation(){
            //thread.suspend();
    }

    public void continueSimulation(){
        thread.start();
    }

    public SimulationEngine getSimulationEngine(){return this.simulationEngine;}

    @Override
    public int getSimulationCounterAndAdd() {
        return 0;
    }
}
