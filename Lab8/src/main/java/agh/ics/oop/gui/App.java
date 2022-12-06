package agh.ics.oop.gui;

import agh.ics.oop.*;
import agh.ics.oop.renderEngine.Game;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.List;

import static agh.ics.oop.OptionsParser.parse;

public class App extends Application {
    private AbstractWorldMap map;
    private int xMin;
    private int yMin;
    private int xMax;
    private int yMax;
    private final int WIDTH = 50;
    private final int HEIGHT = 50;
    private Vector2d[] positions;
    private String[] objects;
    private String[] textures;
    private List<MoveDirection> directions;
    private static GridPane gridPane;
    private Game game;

    public void init(){

        String[] args = getParameters().getRaw().toArray(new String[0]);

        try{
            directions = parse(args);
            map = new GrassField(10);
            positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4)};
            objects = new String[]{"12221_Cat_v1_l3", "12268_banjofrog_v1_L3"};
            textures = new String[]{"res/Cat_diffuse.jpg", "res/12268_banjofrog_diffuse.jpg"};

            gridPane = new GridPane();

        }catch (IllegalArgumentException e){
            System.out.println(e);
            System.exit(0);
        }
    }
    public void updateBounds(){
        xMin = map.getLeftBottomCorner().getX();
        yMin = map.getLeftBottomCorner().getY();
        xMax = map.getRightTopCorner().getX();
        yMax = map.getRightTopCorner().getY();
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
                    GuiElementBox elementBox = new GuiElementBox(map.objectAt(pos));
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

    public void refreshMap(){
        Platform.runLater(() ->{
            gridPane.getChildren().clear();
            gridPane.setGridLinesVisible(false);
            gridPane.getColumnConstraints().clear();
            gridPane.getRowConstraints().clear();
            prepareScene();
        });
    }

    //zrobić start
    @Override
    public void start(Stage primaryStage){
        Button button = new Button("Start");
        TextField textField = new TextField();
        HBox hbox = new HBox(textField, button);

        button.setOnAction(event -> {
            String[] args = textField.getText().split(" ");
            directions = parse(args);
            game = new Game(1280, 720, "World Simulation", directions, map, positions, objects, textures,this);
            Thread thread = new Thread(game);
            thread.start();
        });

        prepareScene();
        VBox mapAndButtons = new VBox(hbox, gridPane);

        Scene scene = new Scene(mapAndButtons,600,600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
