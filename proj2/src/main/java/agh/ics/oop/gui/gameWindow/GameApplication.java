package agh.ics.oop.gui.gameWindow;

import agh.ics.oop.Game;
import agh.ics.oop.entities.AbstractEntity;
import agh.ics.oop.rooms.RoomMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    private Game game;
    private AbstractEntity player;

    public void runApp(Stage primaryStage, AbstractEntity entity) throws IOException{
        player = entity;
        start(primaryStage);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.game = new Game();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gameUi.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 699, 622);
        primaryStage.setMinHeight(622);
        primaryStage.setMinWidth(699);
        primaryStage.setTitle("DungeonRPG");
        primaryStage.setScene(scene);
        primaryStage.show();
        GameController gameController = fxmlLoader.getController();
        gameController.makeMiniMap(new RoomMap(1));
    }
}
