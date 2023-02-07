package agh.ics.oop.gui.choseCharacter;

//biome.getChildren().add(elementBox.getvBox());


import agh.ics.oop.engine.GameEngine;
import agh.ics.oop.entities.Archer;
import agh.ics.oop.entities.Magus;
import agh.ics.oop.entities.Player;
import agh.ics.oop.entities.Warrior;
import agh.ics.oop.gui.IWindow;
import agh.ics.oop.gui.gameWindow.GameApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChoseCharacterApplication implements IWindow {

    private int gameNumber;
    private ChoseCharacterController choseCharacterController;
    private int character = 0;

    public void runApp(IWindow mainMenuApplication, Stage primaryStage) throws IOException {
        gameNumber = mainMenuApplication.getSimulationCounterAndAdd();
        init(primaryStage);
        start(primaryStage);
    }

    @Override
    public int getSimulationCounterAndAdd() {
        return 0;
    }

    @Override
    public GameEngine getSimulationEngine() {
        return null;
    }

    private void init(Stage primaryStage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/choseCharacter.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 622);
            primaryStage.setMinHeight(622);
            primaryStage.setMinWidth(699);
            primaryStage.setTitle("Gra " + gameNumber);
            primaryStage.setScene(scene);
            ChoseCharacterController choseCharacterController = fxmlLoader.getController();
            //choseCharacterController.setMap();
            this.choseCharacterController = choseCharacterController;
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            System.exit(0);
        }
    }


    public void start(Stage primaryStage) {
        primaryStage.show();
        choseCharacterController.prepareBackground();
        choseCharacterController.getMagButton().setOnAction(event -> {
            character = 1;
        });
        choseCharacterController.getWojButton().setOnAction(event -> {
            character = 2;
        });
        choseCharacterController.getLucznikButton().setOnAction(event -> {
            character = 3;
        });
        choseCharacterController.getStartButton().setOnAction(event -> {
            try {
                Player chosenCharacter = null;
                switch (character) {
                    case 1 -> chosenCharacter = new Magus();
                    case 2 -> chosenCharacter = new Warrior();
                    case 3 -> chosenCharacter = new Archer();
                    default -> System.out.println("nie wybrano postaci");
                }
                Stage stage = new Stage();
                GameApplication gameApplication = new GameApplication();
                gameApplication.runApp(stage, chosenCharacter);
            } catch (IOException e) {
                System.out.println("cos nie ten teges");
            }
        });

        //TODO dopisać działanie pod ten przycisk, żeby wystartowała gra

    }
}
