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
    private List<Integer> dayHistory = new ArrayList<>();
    private List<Integer> aliveAnimalsHistory = new ArrayList<>();
    private List<Integer> grassHistory = new ArrayList<>();
    private List<Integer> deadAnimalsHistory = new ArrayList<>();

    @FXML
    private AnchorPane numericStats;
    @FXML
    private AnchorPane plotStats;


    public void setMap(AbstractWorldMap map) {
        this.map = map;
    }

    @FXML
    public void prepareBackground(ArrayList<Integer> stats){
        showNumericStats(stats);
        dayHistory.addAll(new ArrayList<>(Arrays.asList(1,2,3,4,5)));
        aliveAnimalsHistory.addAll(new ArrayList<>(Arrays.asList(10,11,12,13,14)));
        deadAnimalsHistory.addAll(new ArrayList<>(Arrays.asList(0,2,4,6,8)));
        grassHistory.addAll(new ArrayList<>(Arrays.asList(9,9,9,9,9)));
        showPlotStats(stats);
    }

    public void showNumericStats(ArrayList<Integer> stats) {
        Text text0 = new Text();
        text0.setText("Numer dnia:                         "+stats.get(0));
        text0.setX(5);
        text0.setY(17);
        numericStats.getChildren().add(text0);

        Text text1 = new Text();
        text1.setText("Liczba zwierzat zywych:        "+ stats.get(1));
        text1.setY(34);
        text1.setX(5);
        numericStats.getChildren().add(text1);

        Text text2 = new Text();
        text2.setText("Liczba zwierzat martwych:    "+stats.get(2));
        text2.setY(51);
        text2.setX(5);
        numericStats.getChildren().add(text2);

        Text text3 = new Text();
        text3.setText("Ilosc trawy:                            "+stats.get(3));
        text3.setY(68);
        text3.setX(5);
        numericStats.getChildren().add(text3);
    }

    public void showPlotStats(ArrayList<Integer> stats){
        dayHistory.add(stats.get(0));
        aliveAnimalsHistory.add(stats.get(1));
        deadAnimalsHistory.add(stats.get(2));
        grassHistory.add(stats.get(3));

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

        plotStats.getChildren().add(lineChart);


    }
}
