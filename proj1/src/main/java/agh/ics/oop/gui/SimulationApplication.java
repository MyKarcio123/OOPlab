package agh.ics.oop.gui;

import agh.ics.oop.*;
import com.opencsv.CSVWriter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private SimulationEngine simulationEngine;

    public List<Integer> getDayHistory() {
        return dayHistory;
    }

    public List<Integer> getAliveAnimalsHistory() {
        return aliveAnimalsHistory;
    }

    public List<Integer> getGrassHistory() {
        return grassHistory;
    }

    public List<Integer> getDeadAnimalsHistory() {
        return deadAnimalsHistory;
    }

    private List<Integer> dayHistory = new ArrayList<>();
    private List<Integer> aliveAnimalsHistory = new ArrayList<>();
    private List<Integer> grassHistory = new ArrayList<>();
    private List<Integer> deadAnimalsHistory = new ArrayList<>();
    private List<Integer> freePlacesHistory = new ArrayList<>();
    private List<List<Integer>> popularGenotypeHistory = new ArrayList<>();

    public List<Integer> getFreePlacesHistory() {
        return freePlacesHistory;
    }

    public List<List<Integer>> getPopularGenotypeHistory() {
        return popularGenotypeHistory;
    }

    public List<Float> getAverageEnergyHistory() {
        return averageEnergyHistory;
    }

    public List<Float> getAverageLifetimeHistory() {
        return averageLifetimeHistory;
    }

    private List<Float> averageEnergyHistory = new ArrayList<>();
    private List<Float> averageLifetimeHistory = new ArrayList<>();

    private boolean saveToCSV;


    public void runApp(IWindow mainMenuApplication, Stage primaryStage, DataParameters currentConfig, boolean saveStats) throws IOException {
        saveToCSV = saveStats;
        simulationNumber = mainMenuApplication.getSimulationCounterAndAdd();
        init(primaryStage, currentConfig);
        start(primaryStage);
    }

    public void init(Stage primaryStage,DataParameters currentConfig) throws IOException {
        try{

            this.simulationEngine = new SimulationEngine(this, currentConfig);
            this.map = simulationEngine.getMap();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/showSimulation.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 622);
            primaryStage.setMinHeight(622);
            primaryStage.setMinWidth(699);
            primaryStage.setTitle("Symulacja "+ Integer.toString(simulationNumber));
            primaryStage.setScene(scene);
            SimulationController simulationController = fxmlLoader.getController();
            simulationController.setMap(map,currentConfig.getGrassGrowVariant());
            this.simulationController = simulationController;


            Thread thread = new Thread(simulationEngine);
            thread.start();



        }catch (IllegalArgumentException e){
            System.out.println(e);
            System.exit(0);
        }
    }

    public boolean getSaveToCSV(){
        return  saveToCSV;
    }
    public void start(Stage primaryStage) {
        primaryStage.show();
        simulationController.prepareBackground();
        simulationController.getStartStopBT().setOnAction(event -> {
            if(!simulationEngine.getStop()){
                simulationEngine.setStop(true);
            }else{
                simulationEngine.setStop(false);
            }
            });
        simulationController.getShowStatBT().setOnAction(event -> {
            try {
                Stage stage = new Stage();
                StatsApplication statsApplication = new StatsApplication();
                simulationEngine.setStatsApplication(statsApplication);
                statsApplication.runApp((IWindow) this, stage,this.map.getDataParameters(), false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        simulationController.getSaveToCSV().setOnAction(event -> {
           saveStats();

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

    public void saveStats(){
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/stats" + simulationNumber+ ".csv", false);
            CSVWriter writer = new CSVWriter(fileWriter);
            for (int i =0 ; i<dayHistory.size();i++){
                String[] output = new String[]{dayHistory.get(i).toString(), aliveAnimalsHistory.get(i).toString(), deadAnimalsHistory.get(i).toString(), grassHistory.get(i).toString()};
                writer.writeNext(output);
            }
            writer.close();
            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public SimulationEngine getSimulationEngine(){return this.simulationEngine;}

    @Override
    public int getSimulationCounterAndAdd() {
        return 0;
    }
}
