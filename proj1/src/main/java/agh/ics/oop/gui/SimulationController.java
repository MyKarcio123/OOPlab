package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static javafx.scene.layout.GridPane.setHgrow;
import static javafx.scene.layout.GridPane.setVgrow;

public class SimulationController {

    @FXML
    private GridPane mapWrapper;


    private int xMin;
    private int yMin;
    private int xMax;
    private int yMax;
    private final int HEIGHT = 50;
    private final int WIDTH = 50;
    private AbstractWorldMap map;

    public void init(){

        //String[] args = getParameters().getRaw().toArray(new String[0]);

        try{
            //directions = parse(args);
            map = new EarthMap(new SimulationEngine());
            //positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4)};

            //gridPane = new GridPane();

        }catch (IllegalArgumentException e){
            System.out.println(e);
            System.exit(0);
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
            Label label = new Label();
            GridPane.setHalignment(label, HPos.CENTER);
            mapVisualizer.getColumnConstraints().add(new ColumnConstraints(WIDTH));
            mapVisualizer.add(label, i-xMin+1, 0);

        }
    }

    public void updateBounds(){
        xMin = 0;
        yMin = 0;
        xMax = 20;
        yMax = 20;
    }

            ;
    public void rowsFunction(GridPane mapVisualizer){
        for (int i = yMax; i >= yMin; i--){
            Label label = new Label();
            mapVisualizer.getRowConstraints().add(new RowConstraints(HEIGHT));
            mapVisualizer.add(label, 0, yMax -i + 1);
        }
    }

    public void addElements(GridPane mapVisualizer) {
        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMax; j >= yMin; j--) {
                Vector2d pos = new Vector2d(i,j);
                //if(map.isOccupied(pos)){
                    //TreeSet obj = map.objectAt(pos);
                    //AbstractMapElement elem = (AbstractMapElement) obj.first();
                    List<Integer> genotype = new ArrayList<>();
                    genotype.add(1);
                    genotype.add(1);
                    AbstractMapElement elem = new Animal(new EarthMap(new SimulationEngine()),new Vector2d(2,4),genotype,0,new SimulationEngine() );
                    GuiElementBox elementBox = new GuiElementBox(elem);
                    mapVisualizer.add(elementBox.getvBox(),i-xMin+1,yMax-j+1);
                    GridPane.setHalignment(elementBox.getvBox(),HPos.CENTER);
               // }
                //else {
                    //mapVisualizer.add(new Label(" "),i-xMin+1,yMax-j+1);
                //}
            }
        }
    }



    @FXML
    public void prepareBackground(){
        GridPane mapVisualizer = new GridPane();
        updateBounds();
        xyLabel(mapVisualizer);
        columnsFunction(mapVisualizer);
        rowsFunction(mapVisualizer);
        addElements(mapVisualizer);
        mapWrapper.add(mapVisualizer,0,0);


        mapVisualizer.setGridLinesVisible(true);
    }



}
