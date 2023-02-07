package agh.ics.oop.gui.startWindow;


import agh.ics.oop.engine.GameEngine;
import agh.ics.oop.gui.choseCharacter.ChoseCharacterApplication;
import agh.ics.oop.gui.IWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.spi.ImageReaderWriterSpi;
import java.io.IOException;

public class MainMenuApplication extends Application implements IWindow {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainmenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 699, 622);
        primaryStage.setMinHeight(622);
        primaryStage.setMinWidth(699);
        primaryStage.setTitle("Menu Główne");
        primaryStage.setScene(scene);
        primaryStage.show();
        MainMenuController mainMenuController = fxmlLoader.getController();
        mainMenuController.getPlayButton().setOnAction(event -> {
            try {
                Stage stage = new Stage();
                IWindow simulationApplication = new ChoseCharacterApplication();
                simulationApplication.runApp((IWindow) this, stage);
            } catch (IOException e) {
                System.out.println("startbt problem");
            }
        });
    }

    @Override
    public void runApp(IWindow mainMenuApplication, Stage primaryStage) throws IOException {

    }

    @Override
    public int getSimulationCounterAndAdd() {
        return 0;
    }

    @Override
    public GameEngine getSimulationEngine() {
        return null;
    }
}
