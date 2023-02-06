package agh.ics.oop.gui.gameWindow;

import agh.ics.oop.Direction;
import agh.ics.oop.RoomType;
import agh.ics.oop.Vector2d;
import agh.ics.oop.entities.Player;
import agh.ics.oop.gui.IMainMenuControllerObserver;
import agh.ics.oop.gui.MainMenuMethods;
import agh.ics.oop.rooms.Room;
import agh.ics.oop.rooms.RoomMap;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import javafx.scene.image.ImageView;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

import static javafx.scene.layout.GridPane.setHgrow;
import static javafx.scene.layout.GridPane.setVgrow;


public class GameController {
    public Player playerPos;
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
    public ImageView fourthlw;
    @FXML
    public ImageView tlw;
    @FXML
    public ImageView slw;
    @FXML
    public ImageView flw;
    @FXML
    public ImageView fourthrw;
    @FXML
    public ImageView trw;
    @FXML
    public ImageView srw;
    @FXML
    public ImageView frw;
    @FXML
    public ImageView backwall;
    @FXML
    public ImageView Dfourthlw;
    @FXML
    public ImageView Dtlw;
    @FXML
    public ImageView Dslw;
    @FXML
    public ImageView Dflw;
    @FXML
    public ImageView Dfourthrw;
    @FXML
    public ImageView Dtrw;
    @FXML
    public ImageView Dsrw;
    @FXML
    public ImageView Dfrw;
    @FXML
    public ImageView Dbackwall;
    @FXML
    public GridPane minimap;
    @FXML
    public AnchorPane minimapAnchorPane;
    @FXML
    public Label playerDir;
    private List<ImageView> activeDoors = new ArrayList<>();
    @FXML
    public void makeMiniMap(Player playerPos,RoomMap roomMap) {
        this.playerPos = playerPos;
        this.roomMap = roomMap;
        minimap = new GridPane();
        widthMap = roomMap.getMapSize();
        heightMap = roomMap.getMapSize();
        updateBounds(playerPos);
        xyLabel(minimap);
        columnsFunction(minimap);
        rowsFunction(minimap);
        addElements(minimap);
        AnchorPane.setTopAnchor(minimap,0.0);
        minimapAnchorPane.getChildren().add(minimap);
        fixWalls();
    }
    private void updateMinimap(){
        minimap = new GridPane();
        widthMap = roomMap.getMapSize();
        heightMap = roomMap.getMapSize();
        updateBounds(playerPos);
        xyLabel(minimap);
        columnsFunction(minimap);
        rowsFunction(minimap);
        addElements(minimap);
        AnchorPane.setTopAnchor(minimap,0.0);
        minimapAnchorPane.getChildren().remove(0);
        minimapAnchorPane.getChildren().add(minimap);
    }

    public void updateBounds(Player playerPos) {
        WIDTH = (int) (minimap.getWidth() / widthMap);
        HEIGHT = (int) (minimap.getHeight() / heightMap);
        xMin = Math.max(playerPos.getPosition().getX() - 4, 1);
        yMin = Math.max(playerPos.getPosition().getY() - 4, 1);
        xMax = xMin+10;
        yMax = yMin+10;
        System.out.println(playerPos.getPosition());
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
    @FXML
    private void up(){
        roomMap.movePlayer(Direction.NORTH);
        updateBounds(playerPos);
        updateMinimap();
        fixWalls();
    }
    @FXML
    private void down(){
        roomMap.movePlayer(Direction.SOUTH);
        updateBounds(playerPos);
        updateMinimap();
        fixWalls();
    }
    @FXML
    private void left(){
        roomMap.movePlayer(Direction.EAST);
        updateBounds(playerPos);
        updateMinimap();
        fixWalls();
    }
    @FXML
    private void right(){
        roomMap.movePlayer(Direction.WEST);
        updateBounds(playerPos);
        updateMinimap();
        fixWalls();
    }
    @FXML
    private void rotLeft(){
        playerPos.setDirection(playerPos.getDir().previous());
        playerDir.setText(playerPos.getDir().toString());
        fixWalls();
    }
    @FXML
    private void rotRight(){
        playerPos.setDirection(playerPos.getDir().next());
        playerDir.setText(playerPos.getDir().toString());
        fixWalls();
    }
    @FXML
    public void fixWalls(){
        clearDoors();
        Direction playerDir = playerPos.getDir();
        Vector2d playerCoords = playerPos.getPosition();
        Vector2d playerVector = playerDir.toUnitVector().opposite();
        Vector2d playerRightVector = playerVector.rotRight();
        Vector2d playerLeftVector = playerVector.rotRight().rotRight().rotRight();
        Vector2d rightPos = playerCoords.add(playerRightVector);
        Vector2d leftPos = playerCoords.add(playerLeftVector);
        Vector2d startRightCheck = rightPos.add(playerVector.multiplyByScalar(3));
        Vector2d startLeftCheck = leftPos.add(playerVector.multiplyByScalar(3));
        ImageView[] leftWalls = {flw,slw,tlw,fourthlw};
        ImageView[] rightWalls = {frw,srw,trw,fourthrw};
        ImageView[] leftDoors = {Dflw,Dslw,Dtlw,Dfourthlw};
        ImageView[] rightDoors = {Dfrw,Dsrw,Dtrw,Dfourthrw};
        boolean firstFlag = false;
        boolean secondFlag = false;
        boolean doorFlag = false;
        String name = "file:src/main/resources/";
        String[] names = {"f","s","t","fourth"};
        Vector2d[] vector2ds = {startLeftCheck,startRightCheck};
        int iter=0;
        for(Vector2d vec : vector2ds) {
            Vector2d current = vec;
            for (int i = 3; i >= 0; --i) {
                name = names[i] + "lw";
                if (roomMap.getRoomTypeAt(current) != RoomType.CORRIDOR) {
                    name += ".png";
                    if(iter==0) {
                        leftWalls[i].setImage(new Image(name));
                        if(roomMap.getRoomTypeAt(current)!=null && roomMap.getRoomTypeAt(current).isRoom()){
                            leftDoors[i].setOpacity(1);
                            leftDoors[i].setImage(new Image("Dl"+name));
                            activeDoors.add(leftDoors[i]);
                        }
                    }
                    else {
                        rightWalls[i].setImage(new Image(name));
                        if(roomMap.getRoomTypeAt(current) != null && roomMap.getRoomTypeAt(current).isRoom()){
                            rightDoors[i].setOpacity(1);
                            rightDoors[i].setImage(new Image("Dl"+name));
                            activeDoors.add(rightDoors[i]);
                        }
                    }
                    current = current.substract(playerVector);
                    continue;
                }
                if (roomMap.getRoomTypeAt(current.add(playerVector)) == RoomType.CORRIDOR) firstFlag = true;
                if (roomMap.getRoomTypeAt(current.add(playerVector)) != null && roomMap.getRoomTypeAt(current.add(playerVector)).isRoom()) doorFlag = true;
                Vector2d bodyVector = leftPos;
                if(iter==1){
                    bodyVector=rightPos;
                }
                if (roomMap.getRoomTypeAt(current.add(bodyVector)) == RoomType.CORRIDOR) secondFlag = true;
                if (firstFlag && secondFlag) name += "BothOpen";
                else if (!firstFlag && secondFlag) name += "HalfOpen";
                else if (firstFlag && !secondFlag) name += "BackOpen";
                else if (!firstFlag && !secondFlag) name += "Closed";

                name += ".png";
                String doorsName = "D" + names[i] + "lw.png";
                if(iter==0) {leftWalls[i].setImage(new Image(name));
                    if(doorFlag) {
                        leftDoors[i].setOpacity(1);
                        leftDoors[i].setImage(new Image(doorsName));
                        activeDoors.add(leftDoors[i]);
                    }
                }
                else {rightWalls[i].setImage(new Image(name));
                    if(doorFlag) {
                        rightDoors[i].setOpacity(1);
                        rightDoors[i].setImage(new Image(doorsName));
                        activeDoors.add(rightDoors[i]);
                    }
                }
                current = current.substract(playerVector);
                firstFlag = false;
                secondFlag = false;
                doorFlag = false;
            }
            iter++;
        }
        for(int i=0;i<4;++i){
            if(roomMap.getRoomTypeAt(playerCoords.add(playerVector))!=RoomType.CORRIDOR){
                backwall.setOpacity(1);
                name=names[i]+"bw.png";
                backwall.setImage(new Image(name));
                if(roomMap.getRoomTypeAt(playerCoords.add(playerVector)) != null && roomMap.getRoomTypeAt(playerCoords.add(playerVector)).isRoom()){
                    Dbackwall.setOpacity(1);
                    Dbackwall.setImage(new Image("D"+name));
                }else{Dbackwall.setOpacity(0);}
                break;
            }
            backwall.setOpacity(0);
            Dbackwall.setOpacity(0);
            playerCoords = playerCoords.add(playerVector);
        }
        String[] verNames = {"l","r"};

    }
    private void clearDoors(){
        for(ImageView imageView : activeDoors){
            imageView.setOpacity(0);
        }
    }
}
