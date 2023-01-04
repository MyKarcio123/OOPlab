package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static javafx.scene.layout.GridPane.setHgrow;
import static javafx.scene.layout.GridPane.setVgrow;

public class SimulationController {

    @FXML
    private AnchorPane mapWrapper;
    private static GridPane mapVisualizer;
    private GridPane minimap;
    @FXML
    public Button startStopBT;

    @FXML
    public Button showStatsBT;

    @FXML
    public HBox buttonProperties;
    @FXML
    public Button minimapShow;

    private int xMin;
    private int yMin;
    private int xMax;
    private int yMax;
    private final int HEIGHT = 50;
    private final int WIDTH = 50;
    private AbstractWorldMap map;
    private int WIDTH_MAP;
    private int HEIGHT_MAP;
    private boolean biomesMode = false;
    private Stage minimapStage;

    public void setMap(AbstractWorldMap map,int biomeMode) {
        this.WIDTH_MAP = map.getDataParameters().getWidth();
        this.HEIGHT_MAP = map.getDataParameters().getHeight();
        this.map = map;
        if(biomeMode==2) {
            minimapShow.setVisible(true);
            biomesMode=true;
            miniMap();
            minimapStage = new Stage();
            minimapStage.setTitle("Minimapa");
            Scene test = new Scene(minimap,xMax*10,yMax*10);
            minimapStage.setScene(test);
            minimapStage.setMaxHeight(test.getHeight()+39);
            minimapStage.setMaxWidth(test.getWidth()+11);
        }
    }
    public void showMinimap(){
        if(minimapStage.isShowing()) minimapStage.close();
        else minimapStage.show();
    }
    public void miniMap(){
        minimap = new GridPane();
        updateBounds();
        for (int i = xMin; i <= xMax; i++){minimap.getColumnConstraints().add(new ColumnConstraints(10));}
        for (int i = yMax; i >= yMin; i--){minimap.getRowConstraints().add(new RowConstraints(10));}
        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMax; j >= yMin; j--) {
                AnchorPane biome = new AnchorPane();
                String value = map.getBiomeFromMap(new Vector2d(i - xMin + 1, yMax - j + 1)).toString();
                biome.setStyle("-fx-background-color: " + value);
                minimap.add(biome,i-xMin,j-yMin);
            }
        }
    }



    public void xyLabel(GridPane mapVisualizer){
        GridPane.setHalignment(new Label("y/x"), HPos.CENTER);
        mapVisualizer.getColumnConstraints().add(new ColumnConstraints(WIDTH));
        mapVisualizer.getRowConstraints().add(new RowConstraints(HEIGHT));
        mapVisualizer.add(new Label("y/x"), 0, 0);
    }

    public void columnsFunction(GridPane mapVisualizer){
        for (int i = xMin; i <= xMax; i++){
            Integer columnIndex = i-xMin+1;
            Label label = new Label(columnIndex.toString());
            GridPane.setHalignment(label, HPos.CENTER);
            mapVisualizer.getColumnConstraints().add(new ColumnConstraints(WIDTH));
            mapVisualizer.add(label, columnIndex, 0);

        }
    }

    public void updateBounds(){
        xMin = 1;
        yMin = 1;
        xMax = WIDTH_MAP;
        yMax = HEIGHT_MAP;
    }

    public void rowsFunction(GridPane mapVisualizer){
        for (int i = yMax; i >= yMin; i--){
            Integer rowIndex = yMax -i+1;
            Label label = new Label(rowIndex.toString());
            mapVisualizer.getRowConstraints().add(new RowConstraints(HEIGHT));
            mapVisualizer.add(label, 0, rowIndex);
        }
    }

    public void addElements(GridPane mapVisualizer) {
        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMax; j >= yMin; j--) {
                Vector2d position = new Vector2d(i,j);
                AnchorPane biome = new AnchorPane();
                String value = "#ffffff";
                if(biomesMode) value = map.getBiomeFromMap(new Vector2d(i-xMin+1,yMax-j+1)).toString();
                biome.setStyle("-fx-background-color: " + value);
                if(map.isOccupied(position)){
                    Set<Animal> animals = map.objectAt(position);
                    for (Animal animal : animals){
                        GuiElementBox elementBox = new GuiElementBox(animal);
                        biome.getChildren().add(elementBox.getvBox());
                        GridPane.setHalignment(elementBox.getvBox(),HPos.CENTER);
                    }
                    ScrollPane scrollPane = new ScrollPane(biome);
                    mapVisualizer.add(scrollPane,i-xMin+1,j-yMin+1);
                }
                else if(map.isOccupiedByGrass(position)){
                    System.out.println("HEY");
                    GuiElementBox elementBox = new GuiElementBox(new Grass(position));
                    biome.getChildren().add(elementBox.getvBox());
                    mapVisualizer.add(biome,i-xMin+1,j-yMin+1);
                    GridPane.setHalignment(elementBox.getvBox(),HPos.CENTER);
                }
                else {
                    mapVisualizer.add(biome,i-xMin+1,j-yMin+1);
                }
            }
        }
    }



    @FXML
    public void prepareBackground(){
        mapVisualizer = new GridPane();
        setHgrow(mapVisualizer,Priority.ALWAYS);
        setVgrow(mapVisualizer,Priority.ALWAYS);
        updateBounds();
        xyLabel(mapVisualizer);
        columnsFunction(mapVisualizer);
        rowsFunction(mapVisualizer);
        addElements(mapVisualizer);
        mapWrapper.getChildren().add(mapVisualizer);


        mapVisualizer.setGridLinesVisible(true);
    }

    public void refreshMap(){
        Platform.runLater(() ->{
            mapVisualizer.getChildren().clear();
            mapVisualizer.setGridLinesVisible(false);
            mapVisualizer.getColumnConstraints().clear();
            mapVisualizer.getRowConstraints().clear();
            prepareBackground();
        });
    }

    public Button getStartStopBT(){
        return startStopBT;}
    public Button getShowStatBT(){ return showStatsBT;}



}
