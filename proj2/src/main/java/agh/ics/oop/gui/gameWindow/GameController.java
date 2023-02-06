package agh.ics.oop.gui.gameWindow;

import agh.ics.oop.RoomType;
import agh.ics.oop.Vector2d;
import agh.ics.oop.rooms.RoomMap;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import static javafx.scene.layout.GridPane.setHgrow;
import static javafx.scene.layout.GridPane.setVgrow;


public class GameController {
    private int widthMap;
    private int heightMap;
    private int WIDTH;
    private int HEIGHT;
    private int xMin;
    private int yMin;
    private int xMax;
    private int yMax;
    private RoomMap roomMap;
    @FXML
    private GridPane minimap;
    @FXML
    private AnchorPane minimapAnchorPane;
    @FXML
    public void makeMiniMap(RoomMap roomMap) {
        this.roomMap = roomMap;
        minimap = new GridPane();
        setHgrow(minimap, Priority.ALWAYS);
        setVgrow(minimap,Priority.ALWAYS);
        widthMap = roomMap.getMapSize();
        heightMap = roomMap.getMapSize();
        updateBounds();
        xyLabel(minimap);
        columnsFunction(minimap);
        rowsFunction(minimap);
        addElements(minimap);
        AnchorPane.setTopAnchor(minimap,30.0);
        minimapAnchorPane.getChildren().add(minimap);
    }

    public void updateBounds() {
        WIDTH = (int) (minimap.getWidth() / widthMap);
        HEIGHT = (int) (minimap.getHeight() / heightMap);
        xMin = 1;
        yMin = 1;
        xMax = heightMap;
        yMax = widthMap;
    }

    public void xyLabel(GridPane minimap) {
        GridPane.setHalignment(new Label("y/x"), HPos.CENTER);
        minimap.getColumnConstraints().add(new ColumnConstraints(20));
        minimap.getRowConstraints().add(new RowConstraints(20));
        minimap.add(new Label("y/x"), 0, 0);
    }

    public void columnsFunction(GridPane minimap) {
        for (int i = xMin; i <= xMax; i++) {
            Integer columnIndex = i - xMin + 1;
            Label label = new Label(columnIndex.toString());
            GridPane.setHalignment(label, HPos.CENTER);
            minimap.getColumnConstraints().add(new ColumnConstraints(20));
            minimap.add(label, columnIndex, 0);
        }
    }

    public void rowsFunction(GridPane minimap) {
        for (int i = yMax; i >= yMin; i--) {
            Integer rowIndex = yMax - i + 1;
            Label label = new Label(rowIndex.toString());
            minimap.getRowConstraints().add(new RowConstraints(20));
            minimap.add(label, 0, rowIndex);
        }
    }

    public void addElements(GridPane minimap) {
        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMax; j >= yMin; j--) {
                Vector2d position = new Vector2d(i, j);
                AnchorPane background = new AnchorPane();
                String value = "#ffffff";
                RoomType roomVal = roomMap.haveRoom(position);
                Vector2d[] movesX = {new Vector2d(1,0),new Vector2d(-1,0)};
                Vector2d[] movesY = {new Vector2d(0,-1),new Vector2d(0,1)};
                for(Vector2d moveX : movesX) {
                    for(Vector2d moveY : movesY) {
                        if (roomVal == RoomType.UNWALKABLE) {
                            RoomType newVal = roomMap.haveRoom(position.add(moveX));
                            RoomType secondVal = roomMap.haveRoom(position.add(moveY));
                            if (newVal == RoomType.CORRIDOR) roomVal = RoomType.UNWALKABLE;
                            else if (newVal == secondVal) roomVal = newVal;
                        }
                    }
                }
                if(roomVal!= RoomType.UNWALKABLE) value = roomVal.toString();
                background.setStyle("-fx-background-color: " + value);
                minimap.add(background,i-xMin+1,j-yMin+1);
            }
        }
    }
}
