package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
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
import java.util.TreeSet;

public class SimulationApplication extends Application {

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
    private static GridPane gridPane;


    public static void main(String[] args) {
        launch(args);
    }

    public void init(){

        try{
            SimulationEngine simulationEngine = new SimulationEngine();
            this.map = simulationEngine.getMap();
            Thread thread = new Thread( simulationEngine);
            thread.start();

        }catch (IllegalArgumentException e){
            System.out.println(e);
            System.exit(0);
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/showSimulation.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 699, 622);
        primaryStage.setMinHeight(622);
        primaryStage.setMinWidth(699);
        primaryStage.setTitle("Symulacja 2");
        primaryStage.setScene(scene);
        primaryStage.show();

        SimulationController simulationController = fxmlLoader.getController();
        simulationController.setMap(map);
        simulationController.prepareBackground();
    }
}
