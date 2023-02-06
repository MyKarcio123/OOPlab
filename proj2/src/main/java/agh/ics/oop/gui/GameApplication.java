package agh.ics.oop.gui;

import agh.ics.oop.engine.GameEngine;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApplication {

    private int gameNumber;
    private GameEngine gameEngine;
    private Thread gameEngineThread;
    private GameController gameController;

    public void runApp(IWindow mainMenuApplication, Stage primaryStage) throws IOException {
        gameNumber = mainMenuApplication.getSimulationCounterAndAdd();
        init(primaryStage);
        start(primaryStage);
    }

    public void start(Stage primaryStage) {
        primaryStage.show();
        gameController.prepareBackground();
//        gameController.getStartStopBT().setOnAction(event -> {
//            if (!gameEngine.getStop()) {
//                gameEngine.setStop(true);
//
//            } else {
//                gameEngine.setStop(false);
//                gameEngine.resumeEngine();
//            }
//        });

    }

    public void init(Stage primaryStage) throws IOException {
        try {

            this.gameEngine = new GameEngine();
            //this.map = gameEngine.getMap();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/showSimulation.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 622);
            primaryStage.setMinHeight(622);
            primaryStage.setMinWidth(699);
            primaryStage.setTitle("Gra " + gameNumber);
            primaryStage.setScene(scene);
            GameController gameController = fxmlLoader.getController();
            gameController.setMap();
            this.gameController = gameController;


            Thread thread = new Thread(gameEngine);
            thread.start();
            gameEngineThread = thread;


        } catch (IllegalArgumentException e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}
