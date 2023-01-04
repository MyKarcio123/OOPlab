package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import javafx.fxml.FXML;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javafx.scene.layout.GridPane.setHgrow;
import static javafx.scene.layout.GridPane.setVgrow;

public class StatsController {

    private int xMin;
    private int yMin;
    private int xMax;
    private int yMax;
    private final int HEIGHT = 50;
    private final int WIDTH = 50;
    private AbstractWorldMap map;
    private int WIDTH_MAP;
    private int HEIGHT_MAP;
    private List<Integer> dayHistory ;
    private List<Integer> aliveAnimalsHistory;
    private List<Integer> grassHistory;
    private List<Integer> deadAnimalsHistory;

    @FXML
    private AnchorPane numericStats;
    @FXML
    private AnchorPane plotStats;


    public void setMap(AbstractWorldMap map) {
        this.map = map;
    }

    @FXML
    public void prepareBackground(SimulationApplication simulationApplication,ArrayList<Integer> stats){
        dayHistory = simulationApplication.getDayHistory();
        aliveAnimalsHistory = simulationApplication.getAliveAnimalsHistory();
        deadAnimalsHistory = simulationApplication.getDeadAnimalsHistory();
        grassHistory = simulationApplication.getGrassHistory();
        showNumericStats();
        showPlotStats();
    }

    public void showNumericStats() {
        numericStats.getChildren().clear();
        Text text0 = new Text();
        text0.setText("Numer dnia:                         "+dayHistory.get(dayHistory.size()-1));
        text0.setX(5);
        text0.setY(17);
        numericStats.getChildren().add(text0);

        Text text1 = new Text();
        text1.setText("Liczba zwierzat zywych:        "+ aliveAnimalsHistory.get(aliveAnimalsHistory.size()-1));
        text1.setY(34);
        text1.setX(5);
        numericStats.getChildren().add(text1);

        Text text2 = new Text();
        text2.setText("Liczba zwierzat martwych:    "+deadAnimalsHistory.get(deadAnimalsHistory.size()-1));
        text2.setY(51);
        text2.setX(5);
        numericStats.getChildren().add(text2);

        Text text3 = new Text();
        text3.setText("Ilosc trawy:                            "+grassHistory.get(grassHistory.size()-1));
        text3.setY(68);
        text3.setX(5);
        numericStats.getChildren().add(text3);
    }

    public void showPlotStats(){


        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Numer dnia");
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis,yAxis);

        lineChart.setTitle("Ilosc zwierzat i traw");

        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        for (int i = 0; i<dayHistory.size(); i++){
            series1.getData().add(new XYChart.Data<>(dayHistory.get(i), aliveAnimalsHistory.get(i)));
        }
        series1.setName("Zywe zwierzaki");



        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        for (int i = 0; i<dayHistory.size(); i++){
            series2.getData().add(new XYChart.Data<>(dayHistory.get(i), deadAnimalsHistory.get(i)));
        }
        series2.setName("Martwe zwierzaki");

        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
        for (int i = 0; i<dayHistory.size(); i++){
            series3.getData().add(new XYChart.Data<>(dayHistory.get(i), grassHistory.get(i)));
        }
        series3.setName("Trawka");

        //Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().addAll(series1, series2, series3);

        plotStats.getChildren().clear();
        plotStats.getChildren().add(lineChart);


    }
}
