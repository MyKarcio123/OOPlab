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



    public void init(){

        String[] args = getParameters().getRaw().toArray(new String[0]);

        try{
            //directions = parse(args);
            map = new EarthMap(new SimulationEngine());
            positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4)};

            gridPane = new GridPane();

        }catch (IllegalArgumentException e){
            System.out.println(e);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void xyLabel(){
        GridPane.setHalignment(new Label("y/x"), HPos.CENTER);
        gridPane.getColumnConstraints().add(new ColumnConstraints(WIDTH));
        gridPane.getRowConstraints().add(new RowConstraints(HEIGHT));
        gridPane.add(new Label("y/x"), 0, 0);
    }

    public void columnsFunction(){
        for (int i = xMin; i <= xMax; i++){
            Label label = new Label(Integer.toString(i));
            GridPane.setHalignment(label, HPos.CENTER);
            gridPane.getColumnConstraints().add(new ColumnConstraints(WIDTH));
            gridPane.add(label, i-xMin+1, 0);
        }
    }

    public void updateBounds(){
        xMin = map.getMapLowerLeft().getX();
        yMin = map.getMapLowerLeft().getY();
        xMax = map.getMapUpperRight().getX();
        yMax = map.getMapUpperRight().getY();
    }

    public void rowsFunction(){
        for (int i = yMax; i >= yMin; i--){
            Label label = new Label(Integer.toString(i));
            GridPane.setHalignment(label, HPos.CENTER);
            gridPane.getRowConstraints().add(new RowConstraints(HEIGHT));
            gridPane.add(label, 0, yMax -i + 1);
        }
    }

    public void addElements() {
        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMax; j >= yMin; j--) {
                Vector2d pos = new Vector2d(i,j);
                if(map.isOccupied(pos)){
                    TreeSet obj = map.objectAt(pos);
                    AbstractMapElement elem = (AbstractMapElement) obj.first();
                    GuiElementBox elementBox = new GuiElementBox(elem);
                    gridPane.add(elementBox.getvBox(),i-xMin+1,yMax-j+1);
                    GridPane.setHalignment(elementBox.getvBox(),HPos.CENTER);
                }
                else {
                    gridPane.add(new Label(" "),i-xMin+1,yMax-j+1);
                }
            }
        }
    }

    public void prepareScene(){
        this.updateBounds();
        this.xyLabel();
        this.columnsFunction();
        this.rowsFunction();
        this.addElements();
        gridPane.setGridLinesVisible(true);
    }

    @Override
    public void start(Stage primaryStage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/showSimulation.fxml"));


        VBox mapAndButtons = mapVis;

        prepareScene();
        Scene scene = new Scene(fxmlLoader.load(),600,600);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(600);
        primaryStage.setTitle("Symulacja Darwinowska");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
