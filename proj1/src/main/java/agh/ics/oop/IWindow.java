package agh.ics.oop;

import agh.ics.oop.gui.MainMenuApplication;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public interface IWindow {
    void runApp(IWindow mainMenuApplication, Stage primaryStage, DataParameters currentConfig, boolean saveStats) throws IOException;

    int getSimulationCounterAndAdd();

    SimulationEngine getSimulationEngine();
}
