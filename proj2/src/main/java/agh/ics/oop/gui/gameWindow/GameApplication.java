package agh.ics.oop.gui.gameWindow;

import agh.ics.oop.Game;
import agh.ics.oop.entities.Player;
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
        Player player = new Player(1);
        gameController.makeMiniMap(player,new RoomMap(1,player,gameController));
    }
}
