package agh.ics.oop.gui;

import agh.ics.oop.engine.GameEngine;
import javafx.stage.Stage;

import java.io.IOException;

public interface IWindow {
    void runApp(IWindow mainMenuApplication, Stage primaryStage) throws IOException;

    int getSimulationCounterAndAdd();

    GameEngine getSimulationEngine();
}
